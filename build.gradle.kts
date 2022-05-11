import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") version "1.6.21"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev679"
}

group = "de.jossiwolf"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://maven.pkg.jetbrains.space/data2viz/p/maven/public")
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
                dceOptions.devMode = true
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation("io.data2viz.geojson:core:0.6.4")
                //implementation(npm("mapbox-gl", "2.8.2", generateExternals = true))
                //implementation(npm("@types/mapbox-gl", "2.7.1", generateExternals = true))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
