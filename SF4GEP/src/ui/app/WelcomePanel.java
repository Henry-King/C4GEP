package ui.app;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.images.ImageHelper;

public class WelcomePanel extends JPanel {

	
	JList<String> listForMenuPanleInWelc = new JList<String>();
	JList<String> listForBrowsePro=new JList<String>();
	JPanel MenuPanleInWelcome=new JPanel();
	JPanel browseProInWelcome=new JPanel();
	
	private MainWnd mainWnd;
	
	/**
	 * Create the panel.
	 */
	public WelcomePanel(MainWnd mainWnd) {
		this.mainWnd = mainWnd;
		setLayout(new BorderLayout(0, 0));
		JPanel title_Panel = new JPanel();
		//FlowLayout flowLayout = (FlowLayout) title_Panel.getLayout();
		//flowLayout.setAlignment(FlowLayout.LEFT);
		
		
		add(title_Panel, BorderLayout.NORTH);
		
		
		{
			initListForMenuPanleInWelc();
		}
		{
			initListForBrowsePro();
		}
		
		{
			browseProInWelcome.setLayout(new BorderLayout(0, 0));
			browseProInWelcome.add(listForBrowsePro);
			browseProInWelcome.setPreferredSize(new Dimension(250, 340));
			browseProInWelcome.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("tree.gif"), "最近使用的项目", 0, 0));
			
			
			MenuPanleInWelcome.setPreferredSize(new Dimension(170, 80));
			MenuPanleInWelcome.setLayout(new BorderLayout(0, 0));
			MenuPanleInWelcome.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("books.png"), "AF-PGB", 0, 0));
			MenuPanleInWelcome.add(listForMenuPanleInWelc);
			JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, MenuPanleInWelcome,browseProInWelcome);
			add(splitPane,BorderLayout.WEST);
		}
		
		
		
		
		
		
		
		
		
		
		
		{
			JPanel helpPanel = new JPanel();
			helpPanel.setPreferredSize(new Dimension(370, 280));
			helpPanel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "帮助", 0, 0));
			JPanel examplePanel = new JPanel();
			examplePanel.setPreferredSize(new Dimension(370, 140));
			
			JSplitPane splitPane_1 = new JSplitPane();
		    splitPane_1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,helpPanel,examplePanel);
		    examplePanel.setLayout(new BorderLayout(0, 0));
		    examplePanel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("copy.gif"), "示例", 0, 0));
		    helpPanel.setLayout(new BorderLayout(0, 0));
			add(splitPane_1, BorderLayout.CENTER);
		}
		
		
		
		
	}
	
	
	private void initListForBrowsePro() {
		listForBrowsePro.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForBrowsePro.setSelectedIndex(0);
		listForBrowsePro.setFont(new Font("宋体", Font.PLAIN, 16));
		listForBrowsePro.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                   //updateIntroduction();
                }
            }
        });
		listForBrowsePro.setListData(new String[]{"Test1","Test2","Test3","Test4"});
	}

	private void initListForMenuPanleInWelc() {
		listForMenuPanleInWelc.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForMenuPanleInWelc.setBackground(Color.WHITE);
		listForMenuPanleInWelc.setSelectedIndex(0);
		listForMenuPanleInWelc.setFont(new Font("宋体", Font.PLAIN, 13));
		listForMenuPanleInWelc.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                   updateIntroduction();
                }
            }
        });
		listForMenuPanleInWelc.setListData(new String[]{"新建项目","打开项目"});
	}

	private void updateIntroduction() {
		   ProjectCreateDialog pro=new ProjectCreateDialog(mainWnd.frame);
		   pro.setVisible(true);
	}
	
	
	

}
