package ui.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class SplashWnd extends JWindow{
	
	
	public SplashPanel splashPanel = null;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	private ProgressBarPanel loadProgressBarPanel;
	
	
	public SplashWnd() {
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		//mainPanel.setLayout(new BorderLayout(0, 0));
		
		
		//JPanel panel = new JPanel();
		splashPanel = new SplashPanel();
		
		
		
		
		mainPanel.add(splashPanel);
		getContentPane().add(mainPanel);
		
		
/*		boundedRangeModel = new DefaultBoundedRangeModel();
		boundedRangeModel.setValue(10);
		
		
		loadProgressBarPanel = new JProgressBar(boundedRangeModel);
		loadProgressBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		loadProgressBarPanel.setStringPainted(false);
		loadProgressBarPanel.setFont(new Font("����", Font.PLAIN, 15));
		*/
		loadProgressBarPanel = new ProgressBarPanel();
		mainPanel.add(loadProgressBarPanel, BorderLayout.SOUTH);
		
		this.pack();
        Dimension size = this.getSize();
        this.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
	}

	public JProgressBar getLoadBar() {
		return loadProgressBarPanel.getLoadBar();
	}


}
