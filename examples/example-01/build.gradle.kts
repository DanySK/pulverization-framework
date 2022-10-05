kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("com.rabbitmq:amqp-client:5.9.0")
            }
        }
        val jvmTest by getting { }
    }
}
