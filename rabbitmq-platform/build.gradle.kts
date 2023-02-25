import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly

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
        nativeMain {
            dependencies {
                implementation("com.autodesk:coroutineworker:0.8.3")
            }
        }
    }

    fun KotlinNativeTarget.librabbitmqCInterop(target: String) {
        compilations["main"].cinterops {
            val librabbitmq by creating {
                tasks[interopProcessingTaskName].dependsOn(
                    ":rabbitmq-platform:native:buildLibrabbitmq${target.capitalized()}",
                )
                includeDirs.headerFilterOnly(
                    project.file("native/rabbitmq-c/build/include/"),
                    project.file("native/rabbitmq-c/include"),
                    project.file("/usr/include/"),
                )
            }
        }
    }

    val setupNative: KotlinNativeTargetWithHostTests.() -> Unit = {
        compilations["main"].defaultSourceSet.dependsOn(sourceSets["nativeMain"])
        librabbitmqCInterop("host")
        compilations["main"].kotlinOptions.freeCompilerArgs += listOf(
            "-include-binary",
            "$projectDir/native/build/linux/librabbitmq.a",
        )
    }

    when (val hostOs = System.getProperty("os.name").trim().toLowerCaseAsciiOnly()) {
        "linux" -> linuxX64(setupNative)
        "mac os x" -> macosX64(setupNative)
        "windows", "windows server 2022" -> mingwX64(setupNative)
        else -> throw GradleException(
            "Host OS '$hostOs' is not supported in Kotlin/Native.",
        )
    }
}
