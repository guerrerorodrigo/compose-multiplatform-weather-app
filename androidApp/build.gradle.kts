plugins {
    alias(libs.plugins.com.android.application.plugin)
    kotlin("android")
    alias(libs.plugins.org.jetbrains.compose.plugin)
}

android {
    namespace = "com.rodrigoguerrero.myweather.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.rodrigoguerrero.myweather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.androidx.compose.animation)
    implementation(libs.io.insert.koin.android)
}
