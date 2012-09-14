package ui.conf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import ui.images.ImageHelper;

public class NewGEPDialog extends JDialog {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3843396641685701638L;
	private JTextField textField;

	public NewGEPDialog(Frame owner, String title) {
		 	super(owner, title, false);
		 	this.setResizable(false);
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
    		
    		
    		JTextField txt_projectName = new JTextField();
    		txt_projectName.setBounds(139, 132, 345, 24);
    		
    		txt_projectName.setColumns(25);
    		txt_projectName.setMaximumSize(new Dimension(126, 21));
            
            
            
    		JLabel lbl_dataPath = new JLabel("Data Path:");
    		lbl_dataPath.setFont(new Font("宋体", Font.PLAIN, 13));
    		lbl_dataPath.setBounds(31, 168, 100, 30);
            
            
            JTextField txt_dataPath = new JTextField();
            txt_dataPath.setMaximumSize(new Dimension(126, 21));
            txt_dataPath.setColumns(25);
            txt_dataPath.setBounds(111, 172, 289, 24);
            
            
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
                }
            });
            
            JButton btn_Finish = new JButton("Finish");
            btn_Finish.setContentAreaFilled(false);
            btn_Finish.setBounds(317, 10, 100, 30);
            btn_Finish.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
            contentPanel.add(lbl_dataPath);
            contentPanel.add(txt_dataPath);
            contentPanel.add(btn_browsePath);
            contentPanel.add(buttonPanel);
            setContentPane(contentPanel);
            setVisible(true);
            
            
            
            
	 }
}
