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
    }
}

rootProject.name = "FilmVault"
include(":app")
include(":feature:home:ui")
include(":core:common")
include(":core:navigation_api")
include(":feature:home:data")
include(":feature:home:domain")
include(":core:network")
include(":feature:movie_detail")
include(":feature:movie_detail:ui")
include(":feature:movie_detail:domain")
include(":feature:movie_detail:data")
include(":feature:search")
include(":feature:search:data")
include(":feature:search:domainsss")
include(":feature:search:ui")
include(":feature:search:domain")
