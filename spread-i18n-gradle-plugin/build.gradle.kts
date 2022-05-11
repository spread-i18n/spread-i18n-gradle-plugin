plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    //`kotlin-gradle-plugin`
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    implementation("com.andro.spreadi18ncore:spread-i18n-core:1.0-SNAPSHOT")
    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    // Define the plugin
    val greeting by plugins.creating {
        id = "com.andro.spreadi18ngradleplugin"
        implementationClass = "com.andro.spreadi18ngradleplugin.LocalizePlugin"
    }
}
