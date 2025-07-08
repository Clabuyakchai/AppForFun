// buildSrc/build.gradle.kts
plugins {
    `java-library`
    `kotlin-dsl`  // для Kotlin DSL поддержки
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi()) // Доступ к Gradle API
}