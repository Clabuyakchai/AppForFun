import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    `kotlin-dsl`
}

group = "com.kuki.build-logic"

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
//    compileOnly(gradleApi())
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.appforfun.android.application.compose.get().pluginId
            implementationClass = "com.kuki.buildsrc.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.appforfun.android.library.compose.get().pluginId
            implementationClass = "com.kuki.buildsrc.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryDagger") {
            id = libs.plugins.appforfun.android.dagger.get().pluginId
            implementationClass = "com.kuki.buildsrc.AndroidDaggerConventionPlugin"
        }
    }
}
