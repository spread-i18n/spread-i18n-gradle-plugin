package com.andro.spreadi18ngradle;

import com.andro.spreadi18ncore.Import;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.nio.file.Path;


public class LocalizePlugin implements Plugin<Project> {
    public void apply(Project project) {
        LocalizePluginExtension ext = project.getExtensions().create("localization", LocalizePluginExtension.class);
        project.getTasks().register("localize", task -> {
            task.doLast( t -> {
                System.out.println("Hello from plugin: : "+ext.sourceSpreadsheetPath);
                Path sourcePath = Path.of(ext.sourceSpreadsheetPath);
                Path targetPath = Path.of(ext.targetProjectPath);
                Import.perform(sourcePath, targetPath);
            } );
        });
    }
}
