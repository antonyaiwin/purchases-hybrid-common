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
        maven {
            name = "GitHubPackagesPurchasesAndroid"
            url = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY_OWNER") ?: "antonyaiwin"}/purchases-android")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: providers.gradleProperty("gpr.user").orNull ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: providers.gradleProperty("gpr.key").orNull ?: ""
            }
        }
    }
}

rootProject.name = "purchases-hybrid-common"
include(":api-tests")
include(":hybridcommon")
include(":hybridcommon-ui")
include(":hybridcommon-store-galaxy")

// Run enableLocalBuild task to enable building purchases-android from your local copy
if (file(".composite-enable").exists()) {
    val path = file(".composite-enable").readText().trim()
    includeBuild(path)
}
