package ui.app;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

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
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import data.dao.HibernateDataContext;
import domain.service.algConfiguration.GepConfigurationService;


import ui.algoutput.view.OutputView;
import ui.conf.view.ConfPanel;
import ui.images.ImageHelper;

public class MainWnd {
	
	public MainWnd mainWnd;
	public MainFrame frame;
	public SplashWnd splashScreen = null;
	//public MainTabbedPane mainTabbedPane;
	
	private HibernateDataContext hibernateDataContext;
	private KernelLink ml = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainWnd window = new MainWnd();
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
		// create splash screen
		mainWnd = this;
        splashScreen = new SplashWnd();
        splashScreen.setVisible(true);
        
		
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	
            	hibernateDataContext =  GepConfigurationService.initSystem();
            	
            	initKernel();
            	
            	
            	
            }
        });
		
		
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame(mainWnd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		frame.setBounds(100, 100, 630, 448);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new BorderLayout(0, 0));
//		frame.setIconImage(ImageHelper.loadImage("logo.png").getImage());
		
		/*样式设置*/
		
		
		/*主菜单*/
		//MainMenuBar menuBar = new MainMenuBar(this);
		//frame.setJMenuBar(menuBar);
		
		
		
		/*主要的Tabbed面板*/
		//mainTabbedPane = new MainTabbedPane(JTabbedPane.LEFT,this);
		//frame.getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
		
		
		//mainTabbedPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		
		//mainTabbedPane.setForeground(defaultColor);
		
		
	}
	 
	private void initKernel(){	
		try {
			ml = MathLinkFactory
			.createKernelLink("-linkmode launch -linkname 'D:\\program files\\wolfram research\\mathematica\\8.0\\mathkernel.exe'");
			ml.discardAnswer();
		} catch (MathLinkException e1) {
			System.out.println("An error occurred connecting to the kernel.");
			if (ml != null)
				ml.close();
			return;
		}
		
		ml.evaluateToInputForm("Needs[\"" + KernelLink.PACKAGE_CONTEXT + "\"]", 0);
		ml.evaluateToInputForm("ConnectToFrontEnd[]", 0);
	}

	

	public void closeMl() {
		ml.close();
	}

	public HibernateDataContext getHibernateDataContext() {
		return hibernateDataContext;
	}

	public void setHibernateDataContext(HibernateDataContext hibernateDataContext) {
		this.hibernateDataContext = hibernateDataContext;
	}
	
	

}
