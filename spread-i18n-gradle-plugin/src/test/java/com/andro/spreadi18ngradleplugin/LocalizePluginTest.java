package com.andro.spreadi18ngradleplugin;

import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.api.Project;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class LocalizePluginTest {
    @Test
    public void pluginRegistersATask() {

        // Create a test project and apply the plugin
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.andro.spreadi18ngradleplugin");

        // Verify the result
        assertNotNull(project.getTasks().findByName("localize"));
    }
}
