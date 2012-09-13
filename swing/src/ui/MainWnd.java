package ui;

import images.ImageHelper;

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

public class MainWnd {

	private MainFrame frame;

	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWnd window = new MainWnd();
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
		
		Font defaultFont = new Font("Courier",Font.PLAIN,14);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setFont(defaultFont);
		
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel OutputPanel = new JPanel();
		tabbedPane.addTab("Output", null, OutputPanel, null);
		OutputPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		OutputPanel.add(TLPanel, BorderLayout.NORTH);
		
		MainToolBar toolBar = new MainToolBar();
		toolBar.setBorder(BorderFactory.createEmptyBorder(10,20,10,0));
		TLPanel.add(toolBar,BorderLayout.NORTH);
		
		JLabel outputStatusLabel = new JLabel("这里显示状态信息");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
		TLPanel.add(outputStatusLabel,BorderLayout.SOUTH);
		
		JPanel OutputPicturePanel = new JPanel();
		OutputPanel.add(OutputPicturePanel, BorderLayout.CENTER);
		OutputPicturePanel.setLayout(new GridLayout(1,2));
		
		
		
		
		JPanel FittingCurvePanel = new JPanel();
		OutputPicturePanel.add(FittingCurvePanel);
		
		
		JPanel EvolutionGraphPanel = new JPanel();
		OutputPicturePanel.add(EvolutionGraphPanel);
		
		
		
		
		
		MainMenuBar menuBar = new MainMenuBar(this.frame);
		frame.setJMenuBar(menuBar);
		
		
		
		
		
	}
	 


}
