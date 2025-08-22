pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13617490/artifacts/repository")
        }
    }
}

rootProject.name = "TestApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // enable feature like this implementation(projects.core.common)
include(":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:utils")
include(":core:di")
include(":data")
include(":features:contacts")
include(":features:contactdetail")
