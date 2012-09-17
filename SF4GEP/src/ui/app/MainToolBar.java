/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.Insets;
import javax.swing.*;

import ui.images.ImageHelper;

/**
 * @author Michael Hagen
 */
public class MainToolBar extends JToolBar {
    private ImageIcon newImage = null;
    private ImageIcon openImage = null;
    private ImageIcon saveImage = null;
    private ImageIcon cutImage = null;
    private ImageIcon copyImage = null;
    private ImageIcon pasteImage = null;
    private ImageIcon undoImage = null;
    private ImageIcon redoImage = null;
    private ImageIcon runImage = null;
    private ImageIcon clearImage = null;
    private ImageIcon debugImage = null;
    private ImageIcon cfgImage = null;
    
    private ToolButton newButton = null;
    private ToolButton openButton = null;
    private ToolButton saveButton = null;
    private ToolButton cutButton = null;
    private ToolButton runButton = null;
    private ToogleToolButton copyButton = null;
    private ToolButton pasteButton = null;
    private ToolButton undoButton = null;
    private ToolButton redoButton = null;
    private ToolButton clearButton = null;
    private ToolButton debugButton = null;
    private ToolButton cfgButton = null;
//    private JButton defaultBorderButton = null;

    public MainToolBar() {
        super();
        setFloatable(false);
        setMargin(new Insets(2, 0, 2, 0));
        newImage = ImageHelper.loadImage("new.png");
        newButton = new ToolButton(newImage);
        newButton.setToolTipText("�½�");
        
        openImage = ImageHelper.loadImage("open.png");
        openButton = new ToolButton(openImage);
        openButton.setToolTipText("��");
        
        saveImage = ImageHelper.loadImage("save.png");
        saveButton = new ToolButton(saveImage);
        saveButton.setToolTipText("����");
        
        
        
        
        cutImage = ImageHelper.loadImage("cut.png");
        cutButton = new ToolButton(cutImage);
        cutButton.setToolTipText("����");
        
        copyImage = ImageHelper.loadImage("copy.png");
        copyButton = new ToogleToolButton(copyImage);
        copyButton.setToolTipText("����");
        
        pasteImage = ImageHelper.loadImage("paste.png");
        pasteButton = new ToolButton(pasteImage);
        pasteButton.setToolTipText("ճ��");
        pasteButton.setEnabled(false);
        
        clearImage = ImageHelper.loadImage("clear.png");
        clearButton = new ToolButton(clearImage);
        clearButton.setToolTipText("���");
        
        undoImage = ImageHelper.loadImage("undo.png");
        undoButton = new ToolButton(undoImage);
        undoButton.setToolTipText("����");
        
        redoImage = ImageHelper.loadImage("redo.png");
        redoButton = new ToolButton(redoImage);
        redoButton.setToolTipText("����");
        
        
        
        runImage = ImageHelper.loadImage("run.png");
        runButton = new ToolButton(runImage);
        runButton.setToolTipText("ִ���㷨");
        
        
        debugImage = ImageHelper.loadImage("debug.png");
        debugButton = new ToolButton(debugImage);
        debugButton.setToolTipText("����");
        
        
        cfgImage = ImageHelper.loadImage("config.png");
        cfgButton = new ToolButton(cfgImage);
        cfgButton.setToolTipText("����");

//        defaultBorderButton = new JButton("DefaultBorder");
//        defaultBorderButton.setFocusable(false);
//        defaultBorderButton.putClientProperty("paintToolBarBorder", Boolean.FALSE);

        add(newButton);
        add(openButton);
        add(saveButton);
        
        addSeparator();
        add(cutButton);
        add(copyButton);
        add(pasteButton);
        add(clearButton);
        addSeparator();
        add(undoButton);
        add(redoButton);
        addSeparator();
        add(runButton);
        //add(debugButton);
        add(cfgButton);

//        addSeparator();
//        add(defaultBorderButton);

    }

    private class ToolButton extends JButton {

        public ToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }

    private class ToogleToolButton extends JToggleButton {

        public ToogleToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }
}
