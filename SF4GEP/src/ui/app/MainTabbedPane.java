package ui.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ui.conf.view.ConfPanel;
import ui.images.ImageHelper;

public class MainTabbedPane extends JTabbedPane{
	
	private MainWnd mainWnd;
	public MainTabbedPane(int left,MainWnd mainWnd) {
		super(left);
		this.mainWnd = mainWnd;
		Font defaultFont = new Font("Courier",Font.BOLD,14);
		Color defaultColor = new Color(233, 233, 233);
		setForeground(defaultColor);
		
		/*Ä¬ÈÏ»¶Ó­½çÃæ*/
		ui.conf.WelcomePanel welcomePanel = new ui.conf.WelcomePanel(mainWnd);
		VTextIcon welcomeVTextIcon=new VTextIcon(welcomePanel, "Welcome",VTextIcon.ROTATE_LEFT);
		addTab(null,welcomeVTextIcon, welcomePanel, null);
		
		
		
		/*ConfPanel confPanel = new ConfPanel(mainWnd);
		VTextIcon confPanelVTextIcon=new VTextIcon(confPanel, "new",VTextIcon.ROTATE_LEFT);
		addTab(null, confPanelVTextIcon, confPanel, null);*/
		
		
        
    }
}
