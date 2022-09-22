package io.github.rojarand.spreadi18ngradleplugin;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.api.Project;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class LocalizePluginTests {

    private static final String pluginId = "io.github.rojarand.spreadi18ngradleplugin";
    @Test
    public void plugin_registers_importLocalizations_task() {

        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply(pluginId);

        // Verify the result
        assertNotNull(project.getTasks().findByName(LocalizePlugin.IMPORT_LOCALIZATIONS_TASK_NAME));
    }

    @Test
    public void plugin_registers_exportLocalizations_task() {

        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply(pluginId);

        // Verify the result
        assertNotNull(project.getTasks().findByName(LocalizePlugin.EXPORT_LOCALIZATIONS_TASK_NAME));
    }
}
