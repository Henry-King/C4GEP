package ui.conf;

import java.awt.*;
import java.awt.event.*;

import javassist.tools.framedump;

import javax.swing.*;
import javax.swing.border.*;

import com.jtattoo.plaf.JTattooUtilities;

import ui.app.MainFrame;
import ui.app.MainWnd;
import ui.conf.view.ConfPanel;
import ui.images.ImageHelper;

public class NewGEPDialog extends JDialog {



	private static final long serialVersionUID = 3843396641685701638L;
	private MainWnd mainWnd;
	private NewGEPDialog curDialog;
	
	public NewGEPDialog(MainFrame owner, String title,final MainWnd mainWnd) {
		 	super(owner, title, false);
		 	this.mainWnd = mainWnd;
		 	this.setResizable(false);
		 	curDialog = this;
		 	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        setSize(550, 500);
	        setLocationRelativeTo(owner);
	        getContentPane().setLayout(new BorderLayout());
	        
	        
	        
            
            /*主内容面板*/
            JPanel contentPanel = new JPanel();
    		contentPanel.setLayout(null);
    		
    		
    		/*Logo Content*/
    		JLabel logoLabel = new JLabel(ImageHelper.loadImage("NewProjectLogo.jpg"));
            logoLabel.setBounds(0, 0, 550, 100);
            logoLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
            
            JLabel logoTipLabel = new JLabel("Please enter a project name and a input path");
            logoTipLabel.setFont(new Font("Century", Font.PLAIN, 14));
            
            logoTipLabel.setBounds(31, 48, 385, 24);
            
            
            
            /*Project Content*/
            JLabel lbl_projectName = new JLabel("Project Name:");
    		lbl_projectName.setBounds(31, 128, 100, 30);
    		lbl_projectName.setFont(new Font("宋体", Font.PLAIN, 13));
    		
    		
    		final JTextField txt_projectName = new JTextField();
    		txt_projectName.setBounds(139, 132, 345, 24);
    		
    		txt_projectName.setColumns(25);
    		txt_projectName.setMaximumSize(new Dimension(126, 21));
            
            
            
    		JLabel lbl_savePath = new JLabel("Save Path:");
    		lbl_savePath.setFont(new Font("宋体", Font.PLAIN, 13));
    		lbl_savePath.setBounds(31, 168, 100, 30);
            
            
            JTextField txt_savePath = new JTextField();
            txt_savePath.setMaximumSize(new Dimension(126, 21));
            txt_savePath.setColumns(25);
            txt_savePath.setBounds(111, 172, 289, 24);
            
            
            JButton btn_browsePath = new JButton("Browse...");
            btn_browsePath.setContentAreaFilled(false);
            btn_browsePath.setFont(new Font("Calibri", Font.PLAIN, 14));
            btn_browsePath.setBounds(419, 170, 100, 28);
            
            
            
            
            
            
            /*按钮面板*/
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBounds(0, 424, 550, 48);
            
            JButton btn_Back = new JButton("< Back");
            btn_Back.setContentAreaFilled(false);
            btn_Back.setBounds(67, 10, 100, 30);
            btn_Back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //setTitle("New Title");
                }
            });
            
            JButton btn_Next = new JButton("Next >");
            btn_Next.setContentAreaFilled(false);
            btn_Next.setBounds(177, 10, 100, 30);
            btn_Next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	final NewGEPDialog2 newGEPDialog2 = new NewGEPDialog2(mainWnd.frame,curDialog);
                	curDialog.setVisible(false);
                	newGEPDialog2.setVisible(true);
                }
            });
            
            
            JButton btn_Finish = new JButton("Finish");
            btn_Finish.setContentAreaFilled(false);
            btn_Finish.setBounds(317, 10, 100, 30);
            btn_Finish.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if (JTattooUtilities.getJavaVersion() >= 1.6) {
                		JTabbedPane jtp = mainWnd.mainTabbedPane;
                		String title = txt_projectName.getText().toString();
                        int tabCount = jtp.getTabCount();
                        jtp.add("Tab", new ConfPanel());
                        jtp.setTabComponentAt(tabCount, new CloseableTabComponent(jtp,title));
                        jtp.setToolTipTextAt(tabCount, "This is tab No. " + (tabCount + 1));
                    }
                	
                	
                }
            });
            
            
            
            
            JButton btn_Cancel = new JButton("Cancel");
            btn_Cancel.setContentAreaFilled(false);
            btn_Cancel.setBounds(427, 10, 100, 30);
            btn_Cancel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            
            
            
            
            
            buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
            buttonPanel.setLayout(null);
            
            buttonPanel.add(btn_Back);
            buttonPanel.add(btn_Next);
            buttonPanel.add(btn_Finish);
            buttonPanel.add(btn_Cancel);
            
            
            
            
            
            contentPanel.add(logoTipLabel);
            contentPanel.add(logoLabel);
            contentPanel.add(lbl_projectName);
            contentPanel.add(txt_projectName);
            contentPanel.add(lbl_savePath);
            contentPanel.add(txt_savePath);
            contentPanel.add(btn_browsePath);
            contentPanel.add(buttonPanel);
            setContentPane(contentPanel);
            setVisible(true);
            
            
            
            
	 }
	
	
	public static class CloseableTabComponent extends JPanel {
        private static ImageIcon closerImage = ImageHelper.loadImage("closer.gif");
        private static ImageIcon closerRolloverImage = ImageHelper.loadImage("closer_rollover.gif");
        private static ImageIcon closerPressedImage = ImageHelper.loadImage("closer_pressed.gif");
        private JLabel titleLabel = null;
        private JButton closeButton = null; 
        private JTabbedPane tabbedPane = null;
        
        public CloseableTabComponent(JTabbedPane aTabbedPane,String title) {
            super(new BorderLayout());
            tabbedPane = aTabbedPane;
            
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
            
            titleLabel = new JLabel(title);
            titleLabel.setOpaque(false);

            closeButton = new JButton(closerImage);
            closeButton.setRolloverIcon(closerRolloverImage);
            closeButton.setPressedIcon(closerPressedImage);
            closeButton.setBorderPainted(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setFocusPainted(false);
            closeButton.setRolloverEnabled(true);
            closeButton.setOpaque(false);
            closeButton.setContentAreaFilled(false);
            closeButton.setPreferredSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.setSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        if (CloseableTabComponent.this.equals(tabbedPane.getTabComponentAt(i))) {
                            tabbedPane.removeTabAt(i);
                            break;
                        }
                    }
                }
            });
            
            add(titleLabel, BorderLayout.CENTER);
            add(closeButton, BorderLayout.EAST);
        }
        
    }
}
