package it.nicolasfarabegoli.pulverization.platforms.rabbitmq

import Channel
import Connection
import Replies.AssertQueue
import buffer.global.Buffer
import connect
import it.nicolasfarabegoli.pulverization.core.show
import it.nicolasfarabegoli.pulverization.runtime.communication.Binding
import it.nicolasfarabegoli.pulverization.runtime.communication.Communicator
import it.nicolasfarabegoli.pulverization.runtime.communication.RemotePlace
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Implement the [Communicator] interface relying on RabbitMQ as a platform for communications.
 */
@Suppress("UnusedPrivateMember")
actual class RabbitmqCommunicator actual constructor(
    private val hostname: String,
    private val port: Int,
    private val username: String,
    private val password: String,
    private val virtualHost: String,
) : Communicator {

    private lateinit var sendQueueName: String
    private lateinit var receiveQueueName: String
    private lateinit var sendRoutingKey: String
    private lateinit var receiveRoutingKey: String

    private lateinit var connection: Connection
    private lateinit var sendChannel: Channel
    private lateinit var receiveChannel: Channel

    private lateinit var sendQueue: AssertQueue
    private lateinit var receiveQueue: AssertQueue

    companion object {
        private const val EXCHANGE = "pulverization.exchange"
        private val QUEUE_OPTIONS = object : Options.AssertQueue {
            override var durable: Boolean? = true
        }
    }

    override suspend fun setup(binding: Binding, remotePlace: RemotePlace?) {
        requireNotNull(remotePlace) { "To initialize Rabbitmq the RemotePlace should not be null" }
        connection = connect("amqp://$username:$password@$hostname:$port/$virtualHost").await()
        sendChannel = connection.createChannel().await()
        receiveChannel = connection.createChannel().await()

        sendChannel.assertExchange(EXCHANGE, "topic").await()

        sendQueueName = "${binding.first.show()}/${remotePlace.where}/${remotePlace.who}"
        receiveQueueName = "${remotePlace.where}/${binding.first.show()}/${remotePlace.who}"
        sendRoutingKey = "${remotePlace.who}.${remotePlace.where}.${binding.first.show()}"
        receiveRoutingKey = "${remotePlace.who}.${binding.first.show()}.${remotePlace.where}"

        with(sendChannel) {
            sendQueue = assertQueue(sendQueueName, QUEUE_OPTIONS).await()
            receiveQueue = assertQueue(receiveQueueName, QUEUE_OPTIONS).await()

            bindQueue(sendQueue.queue, EXCHANGE, sendRoutingKey).await()
            bindQueue(receiveQueue.queue, EXCHANGE, receiveRoutingKey).await()
        }
    }

    override suspend fun finalize() {
        sendChannel.close().await()
        receiveChannel.close().await()
    }

    override suspend fun fireMessage(message: ByteArray) {
        sendChannel.publish(EXCHANGE, sendRoutingKey, Buffer.from(message))
    }

    override fun receiveMessage(): Flow<ByteArray> = callbackFlow {
        receiveChannel.consume(
            receiveQueue.queue,
            { it?.content?.toString()?.encodeToByteArray()?.let { m -> trySend(m) } ?: error("Message is null") },
        )
    }
}
