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

// Add a source set and a task for a functional test suite
val functionalTest by sourceSets.creating
gradlePlugin.testSourceSets(functionalTest)

configurations[functionalTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())

val functionalTestTask = tasks.register<Test>("functionalTest") {
    testClassesDirs = functionalTest.output.classesDirs
    classpath = configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
}

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTestTask)
}
