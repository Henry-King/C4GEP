package ui.conf;

import java.awt.*;
import java.awt.event.*;

import javassist.tools.framedump;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.multi.*;

import ui.app.*;
public class WelcomePanel extends JPanel {

	
	private MainWnd mainWnd;
	
	/**
	 * Create the panel.
	 */
	public WelcomePanel(MainWnd mainWnd) {
		setLayout(new GridLayout(1, 2,3,3));
		this.mainWnd = mainWnd;
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		WelcomeContent_HistoryProject hp = new WelcomeContent_HistoryProject();
		WelcomeContent_ProfileUsed  pu= new WelcomeContent_ProfileUsed();
		leftPanel.add(hp);
		leftPanel.add(pu);
		
		
		
		
		JPanel rightPanel = new JPanel();
		WelcomeContent_Help help = new WelcomeContent_Help();
		rightPanel.add(help);
		
		add(leftPanel);
		add(rightPanel);
	}
	
	
	
	
	
	
	

}
