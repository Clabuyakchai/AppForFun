import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// buildSrc/build.gradle.kts
plugins {
//    `java-library`
    `kotlin-dsl`  // для Kotlin DSL поддержки
}

group = "com.kuki.buildsrc"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.android.tools.common)
//    implementation(gradleApi()) // Доступ к Gradle API
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibraryCompose") {
            id = libs.plugins.appforfun.android.library.compose.get().pluginId
            implementationClass = "com.kuki.buildsrc.AndroidLibraryComposeConventionPlugin"
        }
    }
}