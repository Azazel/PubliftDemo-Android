pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://dl.cloudsmith.io/BrnMk9bbufLlX4Vd/publift/fuseapp/maven/")
    }
}

rootProject.name = "Publift Demo"
include(":app")
