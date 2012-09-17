package ui.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ui.conf.ConfPanel;
import ui.images.ImageHelper;

public class MainTabbedPane extends JTabbedPane{
	
	private MainWnd mainWnd;
	public MainTabbedPane(int left,MainWnd mainWnd) {
		super(left);
		this.mainWnd = mainWnd;
		
		
		
		
		/*Ä¬ÈÏ»¶Ó­½çÃæ*/
		WelcomePanel welcomePanel = new WelcomePanel(mainWnd);
		addTab(null,new VTextIcon(welcomePanel, "Welcome"), welcomePanel, null);
		
		
		
		ConfPanel confPanel = new ConfPanel();
		addTab(null, new VTextIcon(confPanel, "new"), confPanel, null);
		


        
    }
}
