
plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.18.0"
}

group = "io.github.rojarand.spreadi18ngradleplugin"
version = "0.0.2"

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    //implementation("com.andro.spreadi18ncore:spread-i18n-core:1.0-SNAPSHOT")
    implementation(files("libs/spread-i18n-core-1.0-SNAPSHOT.jar"))
    testImplementation("junit:junit:4.13")
}

pluginBundle {
    website = "https://github.com/rojarand/spread-i18n-gradle-plugin"
    vcsUrl = "https://github.com/rojarand/spread-i18n-gradle-plugin"
    tags = listOf("internationalization", "localization", "automation", "import", "export", "iOS", "Android", "Excel")
}

gradlePlugin {
    plugins {
        create("spreadI18nGradlePlugin") {
            id = "io.github.rojarand.spread-i18n-gradle-plugin"
            displayName = "Plugin for automation of project internationalization"
            description = """Provides functionality to automate import of translations 
            | stored in an Excel sheet to projects (iOS, Android) using Gradle Build Tool.
            | The plugin also supports export of translations from a project to an Excel file,""".trimMargin()
            implementationClass = "io.github.rojarand.spreadi18ngradleplugin.LocalizePlugin"
        }
    }
}

val jar by tasks.getting(org.gradle.jvm.tasks.Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get()
        .filter { with(it.name) { contains("spread-i18n-core") && endsWith(".jar")} }
        .map { if (it.isDirectory) it else { zipTree(it) }
    })
}
