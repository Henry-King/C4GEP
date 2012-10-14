package ui.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.TabbedPaneUI;

import twaver.PopupMenuGenerator;
import twaver.TView;
import ui.conf.view.ConfPanel;
import ui.images.ImageHelper;

public class MainTabbedPane extends JTabbedPane{
	
	private MainWnd mainWnd;
	private JPopupMenu popupMenu;
	private int currentSelectTabIndex = -1;
	
	public MainTabbedPane(int left,MainWnd mainWnd) {
		super(left);
		this.mainWnd = mainWnd;
		//Font defaultFont = new Font("迷你简琥珀",Font.BOLD,14);
		Color defaultColor = new Color(233, 233, 233);
		setForeground(defaultColor);
		
		/*默认欢迎界面*/
		ui.conf.WelcomePanel welcomePanel = new ui.conf.WelcomePanel(mainWnd);
		VTextIcon welcomeVTextIcon=new VTextIcon(welcomePanel, "Welcome",VTextIcon.ROTATE_LEFT);
		addTab(null,welcomeVTextIcon, welcomePanel, null);
		
		
		
		
		
		/*ConfPanel confPanel = new ConfPanel(mainWnd);
		VTextIcon confPanelVTextIcon=new VTextIcon(confPanel, "new",VTextIcon.ROTATE_LEFT);
		addTab(null, confPanelVTextIcon, confPanel, null);*/
		
		popupMenu = new JPopupMenu();
        JMenuItem closeMenuItem = new JMenuItem("Close");
        closeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(currentSelectTabIndex != -1&&currentSelectTabIndex!=0) {
            		MainTabbedPane.this.removeTabAt(currentSelectTabIndex);
            		System.out.println(currentSelectTabIndex);
   			 	}
            }
        });
        popupMenu.add(closeMenuItem);
        
        
        
        
        /**
         * 为主Tab面板添加右键菜单响应事件
         */
        addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			//返回此鼠标事件是否为该平台的弹出菜单触发事件。
    			if(e.isPopupTrigger()) {
	    			 //只在选项卡上显示鼠标右键的提示
	    			//在相对于初始组件的 x、y 位置上显示弹出式菜单。
	    			int i = MainTabbedPane.this.indexAtLocation(e.getX(), e.getY());
	    			 if(i != -1&&i!=0) {
	    				 MainTabbedPane.this.setSelectedIndex(i);
	    				 popupMenu.show(MainTabbedPane.this, e.getX(), e.getY());
	    				 currentSelectTabIndex = i;
	    			 }
    			}

    		}
    	});
        
    }
	/*public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(new RotatePanel());
        jf.setPreferredSize(new Dimension(100, 200));
        jf.pack();
        jf.setVisible(true);
    }*/

	
	
}

class RotatePanel extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        String s = "Welcome";
        g.setFont(new Font("Kartika", Font.BOLD, 14));
        g.translate(this.getWidth() / 2, this.getHeight() / 2);
        ((Graphics2D) g).rotate(-0.5 * Math.PI);//旋转
        g.drawString(s, 0, 0);
        
        
    }
}
