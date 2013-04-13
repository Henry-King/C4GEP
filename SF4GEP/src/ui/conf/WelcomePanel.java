package ui.conf;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import ui.app.MainWnd;
public class WelcomePanel extends JPanel {

	
	private MainWnd mainWnd;
	public WelcomeContent_HistoryProject hp;
	/**
	 * Create the panel.
	 */
	public WelcomePanel(MainWnd mainWnd) {
		setLayout(new GridLayout(1, 2,3,3));
		this.mainWnd = mainWnd;
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		hp = new WelcomeContent_HistoryProject(mainWnd);
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
