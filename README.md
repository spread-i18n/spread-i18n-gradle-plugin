# spread-i18n-gradle-plugin

The plugin provides a simple interface for:

- Import of translations from an Excel file to an Android or iOS project,
- Export of translations from a project to an Excel file.

The plugin is built on top of the [spread-i18n-core](https://github.com/rojarand/spread-i18n-core) library.

The plugin assumes that your iOS project uses [Gradle build tool](https://gradle.org/).

## Integration

- Add plugin id in the module *build.gradle* file.

```groovy
//Groovy
plugins {
    ...
    id "io.github.rojarand.spread-i18n-gradle-plugin" version "0.0.9"
    ...
}
 ```

```kotlin
//Kotlin
plugins {
    ...
    id("io.github.rojarand.spread-i18n-gradle-plugin") version "0.0.9"
    ...
}
 ```


 - Create `localization` function and set the following parameters: 
    - `projectPath`: **String** - absolute path to a project,
    - `spreadsheetPath`: **String** - absolute path to a sheet file,
    - [valueTransformations](https://github.com/rojarand/spread-i18n-core#string-transformation): **Map<String, String>**

For example:
```groovy
localization {
    projectPath = projectDir.getAbsolutePath()
    spreadsheetPath = projectDir.toPath().resolve("sheet.xlsx").toString()
    valueTransformations = [:]
}
```

For Android project add following `classpath` dependency in the project level *build.gradle* file:

```gradle
buildscript { 
    
    repositories {
        ...
    }
    dependencies {
	    classpath 'commons-io:commons-io:2.11.0'
        ...
    }
    ...
}
```

## Usage

The plugin exposes two tasks: `importLocalizations` and `exportLocalizations`

### Importing localizations to a project
```console
$ ./gradlew importLocalizations
```

### Exporting localizations from a project
```console
$ ./gradlew exportLocalizations
```

## Building
```
$ ./gradlew build
```

## Publishing
```console
$ ./gradlew publishPlugins -Pgradle.publish.key=<key> -Pgradle.publish.secret=<secret>
```

More info available [here](https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html#publish_your_plugin_to_the_plugin_portal).

## Testing

### Unit testing

```console
$ ./gradlew test
```

### Manual testing

Create the *buildSrc* directory in an Android or iOS project if not exists.
```console
$ mkdir buildSrc
```

- Go to the *buildSrc* directory and place the plugin content by creating symbolic links.
```console
ln -s /path/to/spread-i18n/spread-i18n-gradle-plugin/spread-i18n-gradle-plugin/* .
```

- Folow the steps from the [Integration](#integration) section
- Run the tasks described in the [Usage](#usage) section

