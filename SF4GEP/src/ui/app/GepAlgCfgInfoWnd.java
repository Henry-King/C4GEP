package ui.app;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import domain.core.algconfiguration.GepAlgConfiguration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GepAlgCfgInfoWnd extends JWindow{
	
	
	public SplashPanel splashPanel = null;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	private ProgressBarPanel loadProgressBarPanel;
	private GepAlgCfgInfoWnd thisWnd;
	
	public GepAlgCfgInfoWnd(GepAlgConfiguration gepAlgConfiguration,Point p) {
		thisWnd = this;
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				thisWnd.dispose();
			}
		});
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		splashPanel = new SplashPanel();
		
		
		
		
		mainPanel.add(splashPanel);
		getContentPane().add(mainPanel);
		
		
		this.pack();
		setLocation(p);
	}
	public void setLocation(Point p){
		this.setLocation((int)p.getX()+90, (int)p.getY()+190);
	}

	public JProgressBar getLoadBar() {
		return loadProgressBarPanel.getLoadBar();
	}


}
