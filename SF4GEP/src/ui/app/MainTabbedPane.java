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
		//Font defaultFont = new Font("���������",Font.BOLD,14);
		Color defaultColor = new Color(233, 233, 233);
		setForeground(defaultColor);
		
		/*Ĭ�ϻ�ӭ����*/
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
         * Ϊ��Tab�������Ҽ��˵���Ӧ�¼�
         */
        addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			//���ش�����¼��Ƿ�Ϊ��ƽ̨�ĵ����˵������¼���
    			if(e.isPopupTrigger()) {
	    			 //ֻ��ѡ�����ʾ����Ҽ�����ʾ
	    			//������ڳ�ʼ����� x��y λ������ʾ����ʽ�˵���
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
        ((Graphics2D) g).rotate(-0.5 * Math.PI);//��ת
        g.drawString(s, 0, 0);
        
        
    }
}
