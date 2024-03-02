package com.dmedelacruz.jirafy.ui.toolwindow;

import com.intellij.ui.components.JBLabel;

import javax.swing.*;

public class StoriesTab extends JPanel implements Tab {

    public StoriesTab() {
        this.add(new JBLabel("Stories"));
    }

    @Override
    public String getTabName() {
        return "Stories";
    }
}
