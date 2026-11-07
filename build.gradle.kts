plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.10.5"
}

group = "dev.despical"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform { defaultRepositories() }
}

dependencies {
    intellijPlatform {
        // Use a local IDE when -PideaPath is given, otherwise download IntelliJ IDEA 2026.2.
        val local = providers.gradleProperty("ideaPath").orNull
        if (local != null) local(local) else intellijIdeaUltimate("2026.2.1")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

intellijPlatform {
    buildSearchableOptions = false
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "262"
            untilBuild = provider { null }
        }
    }
}
