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
)

package it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js

external interface Message {
    var content: ByteArray
    var fields: MessageFields
    var properties: MessageProperties
}

external interface GetMessage : Message

external interface ConsumeMessage : Message

external interface CommonMessageFields {
    var deliveryTag: Number
    var redelivered: Boolean
    var exchange: String
    var routingKey: String
}

external interface MessageFields : CommonMessageFields {
    var messageCount: Number?
        get() = definedExternally
        set(value) = definedExternally
    var consumerTag: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GetMessageFields : CommonMessageFields {
    var messageCount: Number
}

external interface ConsumeMessageFields : CommonMessageFields {
    var consumerTag: String
}

external interface MessageProperties {
    var contentType: Any?
    var contentEncoding: Any?
    var headers: MessagePropertyHeaders
    var deliveryMode: Any?
    var priority: Any?
    var correlationId: Any?
    var replyTo: Any?
    var expiration: Any?
    var messageId: Any?
    var timestamp: Any?
    var type: Any?
    var userId: Any?
    var appId: Any?
    var clusterId: Any?
}

external interface MessagePropertyHeaders

external interface `T$92` {
    var t: String /* "timestamp" */
    var value: Number
}

external interface XDeath

external interface ServerProperties
