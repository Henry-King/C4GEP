package ui.app;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class testSpl extends JFrame{
	private SplashPanel splashPanel = null;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	
	public testSpl() {
		//this.pack();
		
        //Dimension size = this.getSize();
        //this.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
        
		mainPanel = new JPanel();
		//mainPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(mainPanel);
		
		
		
		//splashPanel = new SplashPanel();
		mainPanel.add(splashPanel);
	
	
	}
	
}
