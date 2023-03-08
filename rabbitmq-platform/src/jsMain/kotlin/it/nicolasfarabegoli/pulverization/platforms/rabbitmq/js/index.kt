@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "MaxLineLength",
    "WildcardImport",
    "ClassNaming",
    "VariableNaming",
    "PackageNaming",
    "UnusedPrivateMember",
    "UndocumentedPublicProperty",
    "UndocumentedPublicClass",
    "UndocumentedPublicFunction",
    "TooManyFunctions",
)

package it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js

import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.AssertExchange
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.AssertQueue
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.Connect
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.Consume
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.DeleteExchange
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.DeleteQueue
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.Get
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options.Publish
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.Empty
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.PurgeQueue
import kotlin.js.Promise
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.AssertExchange as _Replies_AssertExchange
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.AssertQueue as _Replies_AssertQueue
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.Consume as _Replies_Consume
import it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies.DeleteQueue as _Replies_DeleteQueue

external interface `T$0` {
    var serverProperties: ServerProperties
}

external interface Connection {
    fun close(): Promise<Unit>
    fun createChannel(): Promise<Channel>
    fun createConfirmChannel(): Promise<ConfirmChannel>
    var connection: `T$0`
}

external interface Channel {
    var connection: Connection
    fun close(): Promise<Unit>
    fun assertQueue(queue: String, options: AssertQueue = definedExternally): Promise<_Replies_AssertQueue>
    fun checkQueue(queue: String): Promise<_Replies_AssertQueue>
    fun deleteQueue(queue: String, options: DeleteQueue = definedExternally): Promise<_Replies_DeleteQueue>
    fun purgeQueue(queue: String): Promise<PurgeQueue>
    fun bindQueue(queue: String, source: String, pattern: String, args: Any = definedExternally): Promise<Empty>
    fun unbindQueue(queue: String, source: String, pattern: String, args: Any = definedExternally): Promise<Empty>
    fun assertExchange(
        exchange: String,
        type: String, /* "direct" | "topic" | "headers" | "fanout" | "match" | String */
        options: AssertExchange = definedExternally,
    ): Promise<_Replies_AssertExchange>

    fun checkExchange(exchange: String): Promise<Empty>
    fun deleteExchange(exchange: String, options: DeleteExchange = definedExternally): Promise<Empty>
    fun bindExchange(
        destination: String,
        source: String,
        pattern: String,
        args: Any = definedExternally,
    ): Promise<Empty>

    fun unbindExchange(
        destination: String,
        source: String,
        pattern: String,
        args: Any = definedExternally,
    ): Promise<Empty>

    fun publish(exchange: String, routingKey: String, content: ByteArray, options: Publish = definedExternally): Boolean
    fun sendToQueue(queue: String, content: ByteArray, options: Publish = definedExternally): Boolean
    fun consume(
        queue: String,
        onMessage: (msg: ConsumeMessage?) -> Unit,
        options: Consume = definedExternally,
    ): Promise<_Replies_Consume>

    fun cancel(consumerTag: String): Promise<Empty>
    fun get(queue: String, options: Get = definedExternally): Promise<dynamic /* GetMessage | Boolean */>
    fun ack(message: Message, allUpTo: Boolean = definedExternally)
    fun ackAll()
    fun nack(message: Message, allUpTo: Boolean = definedExternally, requeue: Boolean = definedExternally)
    fun nackAll(requeue: Boolean = definedExternally)
    fun reject(message: Message, requeue: Boolean = definedExternally)
    fun prefetch(count: Number, global: Boolean = definedExternally): Promise<Empty>
    fun recover(): Promise<Empty>
}

external interface ConfirmChannel : Channel {
    fun publish(
        exchange: String,
        routingKey: String,
        content: ByteArray,
        options: Publish = definedExternally,
        callback: (err: Any, ok: Empty) -> Unit = definedExternally,
    ): Boolean

    fun sendToQueue(
        queue: String,
        content: ByteArray,
        options: Publish = definedExternally,
        callback: (err: Any, ok: Empty) -> Unit = definedExternally,
    ): Boolean

    fun waitForConfirms(): Promise<Unit>
}

external interface `T$1` {
    var mechanism: String
    fun response(): ByteArray
    var username: String
    var password: String
}

external interface `T$2` {
    var mechanism: String
    fun response(): ByteArray
}

external object credentials {
    fun amqplain(username: String, password: String): `T$1`
    fun external(): `T$2`
    fun plain(username: String, password: String): `T$1`
}

external fun connect(url: String, socketOptions: Any = definedExternally): Promise<Connection>

external fun connect(url: String): Promise<Connection>

external fun connect(url: Connect, socketOptions: Any = definedExternally): Promise<Connection>

external fun connect(url: Connect): Promise<Connection>
