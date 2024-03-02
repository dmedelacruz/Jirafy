package com.dmedelacruz.jirafy.ui.toolwindow;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TodosTab extends JPanel implements Tab {

    private JProgressBar spinner = new JProgressBar();
    private JBPanel todosPanel = new JBPanel();
    private JButton refreshButton = new JButton("Refresh");
    private JBScrollPane scrollPane;
    private JPanel overlayPanel = new JPanel();

    public TodosTab() {
        this.setLayout(new BorderLayout());

        this.scrollPane = new JBScrollPane(this.todosPanel);

        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.add(spinner);
        overlayPanel.setVisible(false);
        this.todosPanel.add(overlayPanel, BorderLayout.CENTER);
        this.todosPanel.setLayout(new BoxLayout(todosPanel, BoxLayout.Y_AXIS));

        this.add(this.scrollPane, BorderLayout.CENTER);
        this.add(buttonsPanel(), BorderLayout.SOUTH);

        spinner.setIndeterminate(true);

        this.refreshButton.addActionListener(refreshTodos());

    }

    @Override
    public String getTabName() {
        return "TODOs";
    }

    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(refreshButton);
        return buttonsPanel;
    }

    private ActionListener refreshTodos() {
        return actionEvent -> {

            showSpinner();
            SwingWorker worker = new SwingWorker() {
                @Override
                protected List<String> doInBackground() {
                    return getTodos();
                }

                @Override
                protected void done() {
                    setOverlayVisible(false);
                    try {
                        List<String> todos = (List<String>) get();

                        todosPanel.removeAll();
                        todos.forEach(todo -> todosPanel.add(new JBLabel(todo)));

                        todosPanel.revalidate();
                        todosPanel.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        overlayPanel.setVisible(false);
                    }

                    todosPanel.revalidate();
                    todosPanel.repaint();
                }
            };

            worker.execute();
        };
    }

    private void showSpinner() {
        this.todosPanel.removeAll();
        this.todosPanel.add(overlayPanel);
        setOverlayVisible(true);
        this.todosPanel.paintImmediately(0, 0, todosPanel.getWidth(), todosPanel.getHeight());
    }


    private void setOverlayVisible(boolean isVisible) {
        SwingUtilities.invokeLater(() -> {
            overlayPanel.setVisible(isVisible);
            overlayPanel.revalidate();
            overlayPanel.repaint();
        });
    }

    private List<String> getTodos() {
        try {
            Thread.sleep(5000);
            List<String> todos = new ArrayList<>();
            int i = new Random().nextInt(1000);
            for(int x = i; x < 1000; x++) {
                todos.add(String.valueOf(x));
            }
            return todos;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
