import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":platform"))
            }
        }
        jvmMain {
            dependencies {
                implementation(rootProject.libs.kotlinx.coroutines.reactor)
                api(rootProject.libs.rabbitmq.reactor)
            }
        }
        jvmTest {
            dependencies {
                implementation(rootProject.libs.kotlinx.coroutines.reactor)
            }
        }
        jsMain {
            dependencies {
                implementation(npm("amqplib", "0.10.3"))
                implementation(npm("@types/amqplib", "0.10.1"))
            }
        }
    }
}

ktlint {
    filter {
        exclude("**/js/**")
        exclude {
            it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
        }
    }
}

tasks {
    withType<Detekt>().configureEach {
        exclude("**/build/**")
        exclude {
            it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
        }
    }
    withType<DetektCreateBaselineTask>().configureEach {
        exclude("**/build/**")
        exclude {
            it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
        }
    }
    jsGenerateExternalsIntegrated {
        dependsOn(dokkaHtml)
        dependsOn(detekt)
        dependsOn(runKtlintCheckOverJsMainSourceSet)
    }
}
