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

package it.nicolasfarabegoli.pulverization.platforms.rabbitmq.js.Replies

external interface Empty

external interface AssertQueue {
    var queue: String
    var messageCount: Number
    var consumerCount: Number
}

external interface PurgeQueue {
    var messageCount: Number
}

external interface DeleteQueue {
    var messageCount: Number
}

external interface AssertExchange {
    var exchange: String
}

external interface Consume {
    var consumerTag: String
}
