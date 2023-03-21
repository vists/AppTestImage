import com.codingfeline.buildkonfig.compiler.FieldSpec.Type

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("io.realm.kotlin") version "1.6.0"
    id("com.codingfeline.buildkonfig")
}
private object Versions {
    const val koin = "3.2.0"
    const val ktor = "2.2.4"
    const val coroutines = "1.6.4"
    const val serialization = "2.2.4"
    const val negotiation = "2.2.4"
    const val realm = "1.6.0"
}

object Libs {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }
    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-darwin:${Versions.ktor}"
    }
    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }
    object KotlinxSerialization{
        const val core = "io.ktor:ktor-serialization-kotlinx-json:${Versions.serialization}"
    }
    object Realm{
        const val core = "io.realm.kotlin:library-base:${Versions.realm}"
    }
    object Negotiation{
        const val core = "io.ktor:ktor-client-content-negotiation:${Versions.negotiation}"
    }


}
kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Koin.core)
                implementation(Libs.Ktor.core)
                implementation(Libs.Coroutines.core)
                implementation(Libs.Realm.core)
                implementation(Libs.KotlinxSerialization.core)
                implementation(Libs.Negotiation.core)
                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(Libs.Koin.android)
                implementation(Libs.Ktor.android)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Libs.Ktor.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

}

android {
    namespace = "app.test.payback.group"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }

    buildkonfig {
        packageName = "app.test.payback.group"
        val baseUrl = "BASE_URL"
        val clientKey = "CLIENT_KEY"

        defaultConfigs {
            buildConfigField(Type.STRING, baseUrl, "https://pixabay.com/api/?lang=en&image_type=all")
            buildConfigField(Type.STRING, clientKey, "34533789-2ee08996dc9746ddf8fd36d96")
        }
    }
}
