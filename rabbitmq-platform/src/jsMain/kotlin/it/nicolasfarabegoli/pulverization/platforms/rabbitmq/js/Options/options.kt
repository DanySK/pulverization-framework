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

package it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Options

external interface Connect {
    var protocol: String?
        get() = definedExternally
        set(value) = definedExternally
    var hostname: String?
        get() = definedExternally
        set(value) = definedExternally
    var port: Number?
        get() = definedExternally
        set(value) = definedExternally
    var username: String?
        get() = definedExternally
        set(value) = definedExternally
    var password: String?
        get() = definedExternally
        set(value) = definedExternally
    var locale: String?
        get() = definedExternally
        set(value) = definedExternally
    var frameMax: Number?
        get() = definedExternally
        set(value) = definedExternally
    var heartbeat: Number?
        get() = definedExternally
        set(value) = definedExternally
    var vhost: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AssertQueue {
    var exclusive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var durable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var autoDelete: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var arguments: Any?
        get() = definedExternally
        set(value) = definedExternally
    var messageTtl: Number?
        get() = definedExternally
        set(value) = definedExternally
    var expires: Number?
        get() = definedExternally
        set(value) = definedExternally
    var deadLetterExchange: String?
        get() = definedExternally
        set(value) = definedExternally
    var deadLetterRoutingKey: String?
        get() = definedExternally
        set(value) = definedExternally
    var maxLength: Number?
        get() = definedExternally
        set(value) = definedExternally
    var maxPriority: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeleteQueue {
    var ifUnused: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var ifEmpty: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface AssertExchange {
    var durable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var internal: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var autoDelete: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var alternateExchange: String?
        get() = definedExternally
        set(value) = definedExternally
    var arguments: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface DeleteExchange {
    var ifUnused: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Publish {
    var expiration: dynamic /* String? | Number? */
        get() = definedExternally
        set(value) = definedExternally
    var userId: String?
        get() = definedExternally
        set(value) = definedExternally
    var CC: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var mandatory: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var persistent: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var deliveryMode: dynamic /* Boolean? | Number? */
        get() = definedExternally
        set(value) = definedExternally
    var BCC: dynamic /* String? | Array<String>? */
        get() = definedExternally
        set(value) = definedExternally
    var contentType: String?
        get() = definedExternally
        set(value) = definedExternally
    var contentEncoding: String?
        get() = definedExternally
        set(value) = definedExternally
    var headers: Any?
        get() = definedExternally
        set(value) = definedExternally
    var priority: Number?
        get() = definedExternally
        set(value) = definedExternally
    var correlationId: String?
        get() = definedExternally
        set(value) = definedExternally
    var replyTo: String?
        get() = definedExternally
        set(value) = definedExternally
    var messageId: String?
        get() = definedExternally
        set(value) = definedExternally
    var timestamp: Number?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
    var appId: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Consume {
    var consumerTag: String?
        get() = definedExternally
        set(value) = definedExternally
    var noLocal: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var noAck: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var exclusive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var priority: Number?
        get() = definedExternally
        set(value) = definedExternally
    var arguments: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external interface Get {
    var noAck: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}
