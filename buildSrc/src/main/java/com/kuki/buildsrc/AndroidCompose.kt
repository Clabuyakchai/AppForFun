package com.kuki.buildsrc

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtensions: CommonExtension<*, *, *, *, *, *>
) {

    commonExtensions.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val composeBundle = libs.findBundle("compose").get()
            val composeBom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(composeBundle)
            "implementation"(platform(composeBom))
        }
    }
}