# spread-i18n-gradle-plugin

The plugin provides a simple interface for:

- Import of translations from an Excel file to an Android or iOS project,
- Export of translations from a project to an Excel file.

The plugin is built on top of the [spread-i18n-core](https://github.com/rojarand/spread-i18n-core) library.

## Integration

- Add plugin id in the module `build.gradle` file.

```groovy
 plugins {
    ...
    id 'io.github.rojarand.spread-i18n-gradle-plugin'
    ...
 }
 ```

 - Create `localization` function and set the following parameters: 
    - `projectPath: String` - absolute path to a project,
    - `spreadsheetPath: String` - absolute path to a sheet file,
    - [valueTransformations: Map<String, String>](https://github.com/rojarand/spread-i18n-core#string-transformation)

For example:
 ```groovy
 localization {
       projectPath = projectDir.getAbsolutePath()
       spreadsheetPath = projectDir.toPath().resolve("sheet.xlsx").toString()
       valueTransformations = ["%s":"%@"] //replace %s with %@
       //or leave empty: valueTransformations = ["":""]
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
You can test a plugin manually by placing it in the `buildSrc` directory of the test project. The test project must be an Android or iOS project with localization support.

Before starting the tests, it is necessary to prepare the plugin project. The plugin uses `spread-i18n-core` library as a dependency. This dependency is a module dependency. Currenly, it is not possible to test locally plugins which uses module dependencies. If you put the plugin to the `buildSrc` directory of the test project and run `./gradlew importLocalizations` you will have the error as follows:

```console
FAILURE: Build failed with an exception.

* What went wrong:
Cannot include build 'spread-i18n-core' in build 'buildSrc'. This is not supported yet.
```

To workaround this limitation the plugin has to rely on a binary dependency of the `spread-i18n-core` library.

Follow the steps below to prepare the plugin project for testing.

**spread-i18n-core project**

- Go to the `spread-i18n-core` library project and build it.

```console
$ cd spread-i18n-core
$ ./gradlew build
```
> ##### In case of a successfull build, a `jar` binary is placed in `$project/build/libs/` directory. The binary name starts with `spread-i18n-core-`. A postfix changes depending on the library version. For example if the library version is `version = "1.0-SNAPSHOT"` the produced binary is `spread-i18n-core-1.0-SNAPSHOT.jar`.


**spread-i18n-gradle-plugin project**

- Go to the `spread-i18n-gradle-plugin` project and copy the `jar` file to the `libs` directory.
```console
$ cd ../spread-i18n-gradle-plugin/spread-i18n-gradle-plugin
$ mkdir libs
$ cp ../../spread-i18n-core/build/libs/*.jar libs
```
- Remove `spread-i18n-core` module dependencies
 
1. In the plugin `settings.gradle.kts` file, comment out the module dependency:
 ```
 //includeBuild("../spread-i18n-core")
 ```

2. Make sure that build file `spread-i18n-gradle-plugin/build.gradle.kts` of the plugin uses the jar file dependency.

 ```kotlin
 dependencies {
    //Comment out the dependency
    //implementation("com.andro.spreadi18ncore:spread-i18n-core:1.0-SNAPSHOT")
    
    //Add the file dependency
    implementation(files("libs/spread-i18n-core-1.0-SNAPSHOT.jar"))
}
 ```

 **The test project**

Create the `buildSrc` directory in the test project if not exists.
```console
$ mkdir buildSrc
```

- Go to the `buildSrc` directory and place the plugin content by creating symbolic links.
```console
ln -s /path/to/spread-i18n/spread-i18n-gradle-plugin/spread-i18n-gradle-plugin/* .
```

- Folow the steps from the [Integration](#integration) section
- Run the tasks described in the [Usage](#usage) section

