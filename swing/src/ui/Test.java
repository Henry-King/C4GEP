package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.JButton;

import ui.conf.ConfPanel;

public class Test {

	private JFrame frame;

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
					Test window = new Test();
					window.frame.setVisible(true);
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 637, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		Font myFont = new Font("@Courier",Font.PLAIN,14);
		
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmSava = new JMenuItem("Save");
		mnNewMenu.add(mntmSava);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnProject = new JMenu("Project");
		menuBar.add(mnProject);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Welcome", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		tabbedPane.setFont(myFont);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Configuration", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		ConfPanel tabbedPane_1 = new ConfPanel();
		panel_2.add(tabbedPane_1);
		
		JPanel panel_5 = new JPanel();
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_8.add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_8.add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Output", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		//toolBar_1.setLayout(new BorderLayout());
		toolBar_1.setBorderPainted(false);
		toolBar_1.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		toolBar_1.setRollover(true);
		toolBar_1.setMargin(new Insets(0, 0, 0, 0));
		
		
		JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
		sep.setPreferredSize(new Dimension(10,10));
		sep.setMaximumSize(new Dimension(0,100));
		sep.setMinimumSize(new Dimension(0,10));
		
		panel_4.add(toolBar_1, BorderLayout.NORTH);
		panel_4.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		JButton openBtn = new JButton(new ImageIcon("img/open.png"));
		openBtn.setSize(30, 35);
		openBtn.setVerticalTextPosition(JButton.BOTTOM);
		openBtn.setHorizontalTextPosition(JButton.CENTER);
		openBtn.setBorderPainted(false);
		
		
		
		JButton saveBtn = new JButton(new ImageIcon("img/save.png"));
		saveBtn.setSize(10, 10);
		saveBtn.setVerticalTextPosition(JButton.BOTTOM);
		saveBtn.setHorizontalTextPosition(JButton.CENTER);
		saveBtn.setBorderPainted(false);
		
		
		
		
		
		
		
		
		toolBar_1.add(openBtn);//,BorderLayout.WEST);
		toolBar_1.add(sep);
		toolBar_1.add(saveBtn);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		panel_4.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_2, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_2.addTab("New tab", null, panel_9, null);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(null);
		
	
		
		JButton btnNewButton = new JButton(new ImageIcon("img/open.png"));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(25, 12, 60, 66);
		btnNewButton.setEnabled(true);
		btnNewButton.setVisible(true);
		panel_10.add(btnNewButton);
		
		
		
	}
	
	
	

	
	
	
	
	
	
	
	
	class TestPanel extends JPanel { 

		@Override 
		protected void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		tt(g); 
		} 

		private void tt(Graphics g) { 
		Graphics2D g2 = (Graphics2D)g; 
		        
		java.awt.font.FontRenderContext frc = g2.getFontRenderContext(); 
		java.awt.Font f = new java.awt.Font("宋体", java.awt.Font.PLAIN, 80); 
		java.awt.font.TextLayout tl = new java.awt.font.TextLayout("i序号_i", f, frc); 
		AffineTransform translateInstance = AffineTransform.getTranslateInstance(1, 200);
		 java.awt.Shape sha = tl.getOutline(translateInstance); 
		g2.setColor(java.awt.Color.BLUE); 
		g2.draw(sha); 
		//g2.fill(sha);//如果去掉这行就是空心字了 
		} 
	}

}
