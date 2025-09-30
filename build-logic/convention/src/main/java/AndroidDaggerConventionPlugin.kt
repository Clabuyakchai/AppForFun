package com.kuki.buildsrc

import com.kuki.buildsrc.com.kuki.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.kapt")

            dependencies {
                val dagger = libs.findLibrary("dagger").get()
                val compiler = libs.findLibrary("dagger-compiler").get()
                "implementation"(dagger)
                "kapt"(compiler)
            }
        }
    }
}