package com.andro.spreadi18ngradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


public class LocalizePlugin implements Plugin<Project> {
    public void apply(Project project) {
        LocalizePluginExtension extension = project.getExtensions().create("localization", LocalizePluginExtension.class);
        project.getTasks().register("localize", task -> {
            task.doLast(s -> System.out.println("Hello from plugin: : "+extension.spreadsheetSourcePath));
            //task.doLast(s -> System.out.println("Hello from plugin"));
        });
    }
}
