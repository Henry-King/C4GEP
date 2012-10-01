/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ui.conf.NewGEPDialog;
import ui.images.ImageHelper;

/**
 * @author  Michael Hagen
 */
public class MainMenuBar extends JMenuBar {
    private static final ImageIcon newIcon = ImageHelper.loadImage("new.png");
    private static final ImageIcon openIcon = ImageHelper.loadImage("open.png");
    private static final ImageIcon saveIcon = ImageHelper.loadImage("save.png");
    private static final ImageIcon filterIcon = ImageHelper.loadImage("filter.png");

    private MainWnd parent = null;
    private IDemoApp demoApp = null;
    private ButtonGroup plafGroup = null;

    public MainMenuBar(MainWnd aParent) {
        parent = aParent;
        demoApp = (IDemoApp)parent.frame;
        plafGroup = new ButtonGroup();
        //setBorderPainted(true);
        
        
        
        /*子菜单Demo*/
        JMenu subMenu = new JMenu("Submenu");
        JMenuItem subMenuItem = new JMenuItem("Submenu one");
        subMenu.add(subMenuItem);
        subMenuItem = new JMenuItem("Submenu two");
        subMenu.add(subMenuItem);

        
        
        /*文件*/
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        
        
        
        
        JMenuItem menuItem = new JMenuItem("New", newIcon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	final NewGEPDialog newGEPDialog = new NewGEPDialog("New GEP Project",parent);
            }
        });
        menuItem.setMnemonic('N');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
        menu.add(menuItem);
        
        
        
        menuItem = new JMenuItem("Open", openIcon);
        menuItem.setMnemonic('O');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
        menu.add(menuItem);
        
        
        menuItem = new JMenuItem("Save", saveIcon);
        menuItem.setMnemonic('S');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        menu.add(menuItem);
        menu.addSeparator();
        menu.add(subMenu);
        menu.addSeparator();
        
        
        
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic('x');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.performExit();
            }
        });
        menu.add(menuItem);
        add(menu);

        
        
        
        
        
        
        
        
        
        
        /**/
        menu = new JMenu("Check");
        menu.setMnemonic('C');
        JCheckBoxMenuItem checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem selected", true);
        menu.add(checkMenuItem);
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem unselected", false);
        menu.add(checkMenuItem);
        menu.addSeparator();
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem selected disabled", true);
        checkMenuItem.setEnabled(false);
        menu.add(checkMenuItem);
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem unselected disabled", false);
        checkMenuItem.setEnabled(false);
        menu.add(checkMenuItem);
        menu.addSeparator();
        
        
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem selected", filterIcon, true);
        menu.add(checkMenuItem);
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem unselected", filterIcon, false);
        menu.add(checkMenuItem);
        menu.addSeparator();
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem selected disabled", filterIcon, true);
        checkMenuItem.setEnabled(false);
        menu.add(checkMenuItem);
        checkMenuItem = new JCheckBoxMenuItem("CheckBoxMenuItem unselected disabled", filterIcon, false);
        checkMenuItem.setEnabled(false);
        menu.add(checkMenuItem);
        add(menu);

        
        
        
        
        
        
        
        
        
        /**/
        menu = new JMenu("Radio");
        menu.setMnemonic('R');
        JRadioButtonMenuItem radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected", true);
        menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected", false);
        menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected disabled", true);
        radioMenuItem.setEnabled(false);
        menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected disabled", false);
        radioMenuItem.setEnabled(false);
        menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected", filterIcon, true);
        menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected", filterIcon, false);
        menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected disabled", filterIcon, true);
        radioMenuItem.setEnabled(false);
        menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected disabled", filterIcon, false);
        radioMenuItem.setEnabled(false);
        menu.add(radioMenuItem);

        add(menu);
        
        
        
        
        menu = new JMenu("Help");
        menu.setMnemonic('H');
        menuItem = new JMenuItem("Content...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpDialog dlg = new HelpDialog(parent.frame);
            }
        });
        menuItem.setMnemonic('C');
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("About...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutDialog dlg = new AboutDialog(parent.frame);
            }
        });
        menuItem.setMnemonic('A');
        menu.add(menuItem);
        add(menu);
    }

    

}
