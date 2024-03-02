package com.dmedelacruz.jirafy.ui.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

public class ToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, com.intellij.openapi.wm.@NotNull ToolWindow toolWindow) {

        // Get the content manager
        ContentManager contentManager = toolWindow.getContentManager();

        //Add Todos Tab
        TodosTab todosTab = new TodosTab();
        Content todosTabContent = contentManager.getFactory().createContent(todosTab, todosTab.getTabName(), true);
        contentManager.addContent(todosTabContent);

        //Add Stories Tab
        StoriesTab storiesTab = new StoriesTab();
        Content storiesTabContent = contentManager.getFactory().createContent(storiesTab, storiesTab.getTabName(), true);
        contentManager.addContent(storiesTabContent);


        // Add content to the tool window.
    }
}
