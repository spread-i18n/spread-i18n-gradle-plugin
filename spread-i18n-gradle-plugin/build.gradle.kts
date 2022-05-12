
plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.18.0"
}

group = "com.andro.spreadi18ngradleplugin"
version = "0.0.1"

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    implementation("com.andro.spreadi18ncore:spread-i18n-core:1.0-SNAPSHOT")
    testImplementation("junit:junit:4.13")
}

pluginBundle {
    website = "https://github.com/rojarand/spread-i18n-gradle-plugin"
    vcsUrl = "https://github.com/rojarand/spread-i18n-gradle-plugin"
    tags = listOf("internationalization", "localization", "automation", "import", "iOS", "Android", "Excel")
}

gradlePlugin {
    plugins {
        create("spreadI18nGradlePlugin") {
            id = "com.andro.spreadi18ngradleplugin"
            displayName = "Plugin for automation of project internationalization"
            description = """Provides functionality to automate import of translations 
            | stored in an Excel sheet to projects (iOS, Android) using Gradle Build Tool.""".trimMargin()
            implementationClass = "com.andro.spreadi18ngradleplugin.LocalizePlugin"
        }
    }
}
