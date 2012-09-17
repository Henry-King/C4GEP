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
		VTextIcon welcomeVTextIcon=new VTextIcon(welcomePanel, "Welcome",VTextIcon.ROTATE_LEFT);
		addTab(null,welcomeVTextIcon, welcomePanel, null);
		
		
		
		ConfPanel confPanel = new ConfPanel();
		VTextIcon confPanelVTextIcon=new VTextIcon(confPanel, "new",VTextIcon.ROTATE_LEFT);
		addTab(null, confPanelVTextIcon, confPanel, null);
		


        
    }
}
