/*
 * Copyright 2006-2008 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package ui.app;

import com.jtattoo.plaf.AbstractLookAndFeel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
//import com.jtattoo.swing.extensions.*;

import ui.images.ImageHelper;


public class MainFrame extends JFrame implements IDemoApp {
	
 	public static MainFrame app = null;
    public static GUIProperties guiProps = new GUIProperties();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension appSize = new Dimension(880, 660);
    private static final int appPosX = (screenSize.width / 2) - (appSize.width / 2);
    private static final int appPosY = (screenSize.height / 2) - (appSize.height / 2);
    private static Rectangle appBounds = new Rectangle(appPosX, appPosY, appSize.width, appSize.height);
    private static final String appTitle = "GEP Framework (µ¥»ú°æ)";
   
    private MainMenuBar menuBar = null;
    public JTabbedPane mainTabbedPane = null;
    private MainWnd mainWnd;
    

    public MainFrame() {
    	super(appTitle);
        init();
    }
    
    public MainFrame(MainWnd mainWnd) {
    	super(appTitle);
    	this.mainWnd = mainWnd;
        init();
    }
    
    public MainFrame(Rectangle bounds) {
        super(appTitle);
        appBounds = bounds;
        init();
    }
    
    private void init() {
    	
        
       

        /*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        initMenuBar();
        initMainTabbedPane();
        initListeners();
        

        
        
        
        // Show the demo and take down the splash screen. Note that we again must do this on the GUI thread using invokeLater.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	showApp();
                if (mainWnd.splashScreen != null) {
                	mainWnd.splashScreen.setVisible(false);
                }
                
            }
        });
        
    }
    

    private void initMenuBar() {
        menuBar = new MainMenuBar(mainWnd);
        setJMenuBar(menuBar);
    }

    private void initMainTabbedPane() {
    	getContentPane().setLayout(new BorderLayout(0, 0));
    	mainTabbedPane = new MainTabbedPane(JTabbedPane.LEFT,mainWnd);
    	setMainTabbedPane(mainTabbedPane);
    	getContentPane().add(mainTabbedPane, BorderLayout.CENTER);
    }

   

    private void initListeners() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                performExit();
                mainWnd.closeMl();
            }
        });
    }

    private void showApp() {
    	setBounds(100, 100, 630, 448);
    	setTitle(appTitle);
    	setMinimumSize(new Dimension(JFrame.MAXIMIZED_BOTH, JFrame.MAXIMIZED_BOTH));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(ImageHelper.loadImage("logo.png").getImage());
        setBounds(appBounds);
        setVisible(true);
    }
    
    

	@Override
	public GUIProperties getGuiProps() {
		return guiProps;
	}

	@Override
	public void setMainTabbedPane(JTabbedPane tabPane) {
		 mainTabbedPane = tabPane;
	}

	@Override
	public JTabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	@Override
	public void updateLookAndFeel(String lf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTheme(String theme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTextAntiAliasing(boolean taa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBackgroundPattern(boolean pattern) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performExit() {
		System.exit(0);
	}

}
