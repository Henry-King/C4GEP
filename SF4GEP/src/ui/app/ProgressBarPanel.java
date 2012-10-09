/*
 * Copyright 2011 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import com.jtattoo.plaf.JTattooUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author  Michael Hagen
 */
public class ProgressBarPanel extends JPanel{
    private JProgressBar loadBar = null;
   

	public ProgressBarPanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        setName("LoadBar");
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(createHorProgressbars(), BorderLayout.CENTER);
        
        
    }


    private JProgressBar createHorProgressbars() {
        loadBar = new JProgressBar(0, 100);
        loadBar.setPreferredSize(new Dimension(140, 16));
        loadBar.setForeground(Color.GREEN);
        loadBar.setString("Loading...");
        loadBar.setStringPainted(true);
        loadBar.setIndeterminate(false);
        loadBar.setValue(20);
        loadBar.putClientProperty("selectionForeground", Color.yellow);
        loadBar.putClientProperty("selectionBackground", Color.gray);
        loadBar.setPreferredSize(new Dimension(300,20));
        
        
        
        return loadBar;
    }

	public JProgressBar getLoadBar() {
		return loadBar;
	}

    
    
}
