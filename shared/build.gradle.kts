plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform.plugin)
    alias(libs.plugins.com.android.library.plugin)
    alias(libs.plugins.org.jetbrains.compose.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.serialization.plugin)
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(libs.dev.icerock.moko.mvvm.core.get())
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(libs.dev.icerock.moko.resources)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.bundles.sqldelight)
                implementation(libs.org.jetbrains.kotlinx.datetime)
                implementation(libs.bundles.ktor)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.media.kamel.image)
                implementation(libs.bundles.voyager)
                implementation(libs.bundles.datastore)
                implementation(libs.io.insert.koin.core)
                api(libs.bundles.moko.mvvm)
                api(libs.bundles.moko.resources)
                api(libs.bundles.moko.permissions)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activity.compose)
                implementation(libs.io.ktor.client.android)
                implementation(libs.io.insert.koin.android)
                implementation(libs.com.google.android.gms.play.services.location)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.com.squareup.sqldelight.native.driver)
                implementation(libs.io.ktor.client.darwin)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
    namespace = "com.rodrigoguerrero.myweather.android"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.rodrigoguerrero.mywheather.database"
        sourceFolders = listOf("sqldelight")
    }
}

dependencies {
    implementation(libs.androidx.core)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.rodrigoguerrero.mywheather" // required
}
