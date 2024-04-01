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
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = "sk.eyJ1IjoiY3JhdmVuMTIzIiwiYSI6ImNsdTcwNnBhODAweGYydm5yMjNkcHM3bjUifQ.t5L4dFccmFDO-3Dg85uE7g"
            }
        }

    }
}

rootProject.name = "TransitApp"
include(":app")
