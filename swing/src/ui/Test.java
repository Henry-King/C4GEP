package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JLabel;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("算法输入", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("算法配置", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane_1);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel_8.add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_8.add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("算法输出", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
	}
}
