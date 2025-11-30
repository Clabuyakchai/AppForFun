plugins {
    alias(libs.plugins.appforfun.android.application.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.appforfun.android.dagger)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.kuki.testapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.kuki.testapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.kuki.testapp.runner.TestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.ui)
    implementation(projects.core.domain)
    implementation(projects.core.utils)
    implementation(projects.core.di)
    implementation(projects.data)
    implementation(projects.features.contacts)
    implementation(projects.features.contactdetail)

    // Coil
    implementation(libs.bundles.coil)

    //Coroutines
    implementation(libs.bundles.coroutines)

    //Nav3
    implementation(libs.bundles.nav3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // End2End testing
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}