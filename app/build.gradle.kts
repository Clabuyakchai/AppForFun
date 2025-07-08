import com.kuki.buildsrc.Modules
import com.kuki.buildsrc.SDK

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.kuki.testapp"
    compileSdk = SDK.COMPILE_SDK

    defaultConfig {
        applicationId = "com.kuki.testapp"
        minSdk = SDK.MIN_SDK
        targetSdk = SDK.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(Modules.CORE_PRESENTATION))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.DATA))
    implementation(project(Modules.FEATURE_1))
    implementation(project(Modules.CONTACTS))

    //dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // Coil
    implementation(libs.bundles.coil)

    //Coroutines
    implementation(libs.bundles.coroutines)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.fragment)

    //Compose
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}