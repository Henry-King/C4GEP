package ui.app;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JLabel;


import ui.conf.ConfPanel;
import ui.images.ImageHelper;
import ui.output.OutputPanel;

public class MainWnd {

	private MainFrame frame;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWnd window = new MainWnd();
					window.frame.setTitle("GEP Framework");
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWnd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame();
		frame.setBounds(100, 100, 630, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(ImageHelper.loadImage("logo.png").getImage());
		
		/*样式设置*/
		Font defaultFont = new Font("Courier",Font.PLAIN,14);
		
		
		/*主菜单*/
		MainMenuBar menuBar = new MainMenuBar(this.frame);
		frame.setJMenuBar(menuBar);
		
		
		
		
		/*主要的Tabbed面板*/
		JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		frame.getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
		mainTabbedPane.setFont(defaultFont);
		
		
		/*默认欢迎界面*/
		JPanel welcomePanel = new JPanel();
		mainTabbedPane.addTab("Welcome", null, welcomePanel, null);
		
		
		ConfPanel confPanel = new ConfPanel();
		mainTabbedPane.addTab("new", null, confPanel, null);
		
	}
	 


}
