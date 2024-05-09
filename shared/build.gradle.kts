import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform.plugin)
    alias(libs.plugins.com.android.library.plugin)
    alias(libs.plugins.org.jetbrains.compose.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.serialization.plugin)
    id("com.codingfeline.buildkonfig")
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget {
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
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.animation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.bundles.sqldelight)
                implementation(libs.org.jetbrains.kotlinx.datetime)
                implementation(libs.bundles.ktor)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.media.kamel.image)
                implementation(libs.bundles.datastore)
                implementation(libs.io.insert.koin.core)
                implementation(libs.androidx.navigation.compose)

                api(libs.bundles.moko.mvvm)
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
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.androidx.compose.ui.tooling)
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

buildkonfig {
    packageName = "com.rodrigoguerrero.myweather"
    val apiKey: String = gradleLocalProperties(rootDir).getProperty("apiKey")

    require(apiKey.isNotEmpty()) {
        "Register your api key from https://www.weatherapi.com/ and place it in local.properties as `apiKey`"
    }
    defaultConfigs {
        buildConfigField(STRING, "API_KEY", apiKey)
    }
}
