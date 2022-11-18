package io.github.rojarand.spreadi18ngradleplugin;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskContainer;

import java.nio.file.Path;


public class LocalizePlugin implements Plugin<Project> {

    static final String IMPORT_LOCALIZATIONS_TASK_NAME = "importLocalizations";
    static final String EXPORT_LOCALIZATIONS_TASK_NAME = "exportLocalizations";
    public void apply(Project project) {
        LocalizePluginExtension ext = project.getExtensions().create("localization", LocalizePluginExtension.class);
        TaskContainer taskContainer = project.getTasks();
        taskContainer.register(IMPORT_LOCALIZATIONS_TASK_NAME, task -> task.doLast(t -> importLocalizations(ext)));
        taskContainer.register(EXPORT_LOCALIZATIONS_TASK_NAME, task -> task.doLast(t -> exportLocalizations(ext)));
    }

    private void importLocalizations(LocalizePluginExtension ext) {
        try {
            Path projectPath = Path.of(ext.projectPath);
            Path spreadsheetPath = Path.of(ext.spreadsheetPath);
            com.andro.spreadi18ncore.Project.onPath(projectPath).importFrom(spreadsheetPath, ext.valueTransformations);
        } catch (Throwable e) {
            throw new GradleException("Error occurred during import: "+e.getMessage(), e);
        }
    }

    private void exportLocalizations(LocalizePluginExtension ext) {
        try {
            Path projectPath = Path.of(ext.projectPath);
            Path spreadsheetPath = Path.of(ext.spreadsheetPath);
            com.andro.spreadi18ncore.Project.onPath(projectPath).exportTo(spreadsheetPath, ext.valueTransformations);
        } catch (Throwable e) {
            throw new GradleException("Error occurred during export: "+e.getMessage(), e);
        }
    }
}