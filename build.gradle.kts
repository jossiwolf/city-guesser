import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform") version "1.7.10"
    id("org.jetbrains.compose") version "1.2.0-beta01"
}

group = "de.jossiwolf"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.pkg.jetbrains.space/data2viz/p/maven/public")
}

class SkiaDependencyHandler(private val rootDependencyHandler: KotlinDependencyHandler) {
    val osName by lazy { System.getProperty("os.name") }
    val targetOs by lazy {
        when {
            osName == "Mac OS X" -> "macos"
            osName.startsWith("Win") -> "windows"
            osName.startsWith("Linux") -> "linux"
            else -> error("Unsupported OS: $osName")
        }
    }
    val osArch by lazy { System.getProperty("os.arch") }
    val targetArch by lazy { when (osArch) {
        "x86_64", "amd64" -> "x64"
        "aarch64" -> "arm64"
        else -> error("Unsupported arch: $osArch")
    } }
    val target by lazy { "${targetOs}-${targetArch}" }

    fun skiko(target: String = this.target, version: String = "0.5.3") = with (rootDependencyHandler) {
        implementation("org.jetbrains.skiko:skiko-awt-runtime-$target:$version")
    }
}

fun KotlinDependencyHandler.skia(builder: SkiaDependencyHandler.() -> Unit) {
    SkiaDependencyHandler(this).builder()
}

kotlin {
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                }
            }
            dceTask {
                dceOptions {
                    verbose = true
                    devMode = true
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("io.data2viz.geojson:core:0.6.4")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("io.data2viz.geojson:core:0.6.4")
                //implementation(npm("mapbox-gl", "2.8.2", generateExternals = true))
                //implementation(npm("@types/mapbox-gl", "2.7.1", generateExternals = true))

                implementation("org.jetbrains.skiko:skiko:0.7.32")
                implementation("org.jetbrains.skiko:skiko-js-wasm-runtime:0.7.32")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
            }
        }
    }
}

compose.experimental {
    web.application {

    }
}
