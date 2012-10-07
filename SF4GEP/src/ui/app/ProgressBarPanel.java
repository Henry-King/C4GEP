/*
 * Copyright 2011 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import com.jtattoo.plaf.JTattooUtilities;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  Michael Hagen
 */
public class ProgressBarPanel extends JPanel {
    private JProgressBar horStandardBar = null;
    //private JProgressBar horTextBar = null;
    //private JProgressBar horDisabledBar = null;
    //private JProgressBar horColoredBar = null;
    //private JProgressBar horIndeterminatedBar = null;
    public ProgressBarPanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        setName("ProgressBar");
        //setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(createHorProgressbars(), BorderLayout.CENTER);
        
        //JScrollPane scrollPane = new JScrollPane(contentPanel);
       // scrollPane.setBorder(BorderFactory.createEmptyBorder());
       // add(scrollPane, BorderLayout.CENTER);
    }

    /*public void updateLookAndFeel() {
        if (JTattooUtilities.getJavaVersion() >= 1.4) {
            horIndeterminatedBar.setIndeterminate(false);
            horIndeterminatedBar.setIndeterminate(true);
        }
    }*/

    private JPanel createHorProgressbars() {
        //GridBagLayout layout = new GridBagLayout();
        JPanel progressBarPanel = new JPanel();
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        horStandardBar = new JProgressBar(0, 100);
        horStandardBar.setValue(33);
        horStandardBar.setPreferredSize(new Dimension(140, 16));
        /*horTextBar = new JProgressBar(0, 100);
        horTextBar.setValue(50);
        horTextBar.setString("50%");
        horTextBar.setStringPainted(true);
        horTextBar.setPreferredSize(new Dimension(140, 16));*/
        /*horDisabledBar = new JProgressBar(0, 100);
        horDisabledBar.setValue(66);
        horDisabledBar.setEnabled(true);
        horDisabledBar.setPreferredSize(new Dimension(503, 16));*/
        /*horColoredBar = new JProgressBar(0, 100);
        horColoredBar.setValue(50);
        horColoredBar.setString("50%");
        horColoredBar.setStringPainted(true);
        horColoredBar.setPreferredSize(new Dimension(140, 16));
        horColoredBar.setForeground(Color.yellow);
        horColoredBar.setBackground(Color.blue);
        horColoredBar.putClientProperty("selectionForeground", Color.red);
        horColoredBar.putClientProperty("selectionBackground", Color.green);*/

        /*if (JTattooUtilities.getJavaVersion() >= 1.4) {
            horIndeterminatedBar = new JProgressBar();
            horIndeterminatedBar.setString("indeterminated");
            horIndeterminatedBar.setStringPainted(true);
            horIndeterminatedBar.setIndeterminate(true);
            horIndeterminatedBar.setPreferredSize(new Dimension(140, 16));
        }*/

        GridBagHelper.addComponent(progressBarPanel, horStandardBar,       0, 1, 1, 1, 4, 4,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        //GridBagHelper.addComponent(progressBarPanel, horTextBar,           0, 2, 1, 1, 4, 4,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        //GridBagHelper.addComponent(progressBarPanel, horDisabledBar,       0, 3, 1, 1, 4, 4,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
       // progressBarPanel.add(horDisabledBar);
        
        /*if (JTattooUtilities.getJavaVersion() >= 1.4) {
            GridBagHelper.addComponent(progressBarPanel, horIndeterminatedBar, 0, 4, 1, 1, 4, 4,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        }*/
        //GridBagHelper.addComponent(progressBarPanel, horColoredBar,       0, 5, 1, 1, 4, 4,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        return progressBarPanel;
    }

//    private void changeColors() {
//        textBar.setForeground(Color.YELLOW);
//        textBar.setBackground(Color.BLUE);
//        textBar.putClientProperty("selectionForeground", Color.BLACK);
//        textBar.putClientProperty("selectionForeground", Color.RED);
//        textBar.putClientProperty("selectionBackground", Color.BLACK);
//        textBar.putClientProperty("selectionBackground", Color.GREEN);
//
//        indeterminatedBar.setForeground(Color.YELLOW);
//        indeterminatedBar.setBackground(Color.BLUE);
//        indeterminatedBar.putClientProperty("selectionForeground", Color.BLACK);
//        indeterminatedBar.putClientProperty("selectionForeground", Color.RED);
//        indeterminatedBar.putClientProperty("selectionBackground", Color.BLACK);
//        indeterminatedBar.putClientProperty("selectionBackground", Color.GREEN);
//    }
}
