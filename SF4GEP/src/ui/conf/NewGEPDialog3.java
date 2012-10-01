package ui.conf;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.jtattoo.plaf.JTattooUtilities;

import ui.app.MainWnd;
import ui.images.ImageHelper;

public class NewGEPDialog3 extends JDialog {

	public MainWnd mainWnd;
	private NewGEPDialog2 pre;
	private NewGEPDialog3 cur;
	
	 JLabel lbl_profileName;
     JTextField txt_profileName;
    JCheckBox chckbxNewCheckBox;
    JLabel lbl_profilePath;
    JTextField txt_profilePath;
    JButton btn_browseProfilePath;
    
    JCheckBox checkBox;
    JLabel lbl_InputPath;
    JTextField txt_InputPath;
    JButton btn_browseInputDataPath;
	
	
	
	
	
	
	public NewGEPDialog3(final MainWnd mainWnd,NewGEPDialog2 newGEPDialog2) {
		super(mainWnd.frame, "Other Parameter", false);
	 	this.mainWnd = mainWnd;
	 	this.pre = newGEPDialog2;
	 	this.cur = this;
	 	this.setResizable(false);
	 	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(mainWnd.frame);
        getContentPane().setLayout(new BorderLayout());
        
        
        
        /*主内容面板*/
        JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		
		/*Logo Content*/
		JLabel logoLabel = new JLabel(ImageHelper.loadImage("NewProjectLogo.jpg"));
        logoLabel.setBounds(0, 0, 550, 100);
        logoLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
        
        JLabel logoTipLabel = new JLabel("Please set your profile and input data setting.");
        logoTipLabel.setFont(new Font("Century", Font.PLAIN, 14));
        
        logoTipLabel.setBounds(31, 48, 385, 24);
        
        
        
        
        /*Profile Setting*/
        
        
        /*Input Data Setting*/
        
        
        
        
        
        
        
        
        
		 
        /*按钮面板*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 424, 550, 48);
        
        JButton btn_Back = new JButton("< Back");
        btn_Back.setContentAreaFilled(false);
        btn_Back.setBounds(67, 10, 100, 30);
        btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	pre.setVisible(true);
            	cur.setVisible(false);
            }
        });
        
        JButton btn_Next = new JButton("Next >");
        btn_Next.setEnabled(false);
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
            	if (JTattooUtilities.getJavaVersion() >= 1.6) {
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
        contentPanel.add(buttonPanel);
        setContentPane(contentPanel);
        
        JPanel profilePanel = new JPanel();
        //profilePanel.setFont(new Font("Courier",Font.PLAIN,20));
        
        profilePanel.setBounds(10, 110, 524, 143);
        contentPanel.add(profilePanel);
        profilePanel.setBorder(BorderFactory.createTitledBorder("Profile:"));
        profilePanel.setLayout(null);
        
                {
                 lbl_profileName = new JLabel("Profile Name:");
                lbl_profileName.setFont(new Font("宋体", Font.PLAIN, 13));
                lbl_profileName.setBounds(20, 50, 100, 30);
                
                
                 txt_profileName = new JTextField();
                 txt_profileName.addFocusListener(new FocusAdapter() {
                 	@Override
                 	public void focusLost(FocusEvent e) {
                 		pre.pre.data.setProjectPath(txt_profileName.getText());
                 	}
                 });
                txt_profileName.setMaximumSize(new Dimension(126, 21));
                txt_profileName.setColumns(25);
                txt_profileName.setBounds(116, 54, 385, 24);
                
                
                 chckbxNewCheckBox = new JCheckBox("Use default profile setting");
                 chckbxNewCheckBox.setSelected(true);
                 chckbxNewCheckBox.addActionListener(new ActionListener() {
                 	public void actionPerformed(ActionEvent arg0) {
                 		if (chckbxNewCheckBox.isSelected()) {
                 			setProfileSettingChosed(true);
						}else{
							setProfileSettingChosed(false);
						}
                 	}
                 });
                 
                chckbxNewCheckBox.setFont(new Font("宋体", Font.PLAIN, 13));
                chckbxNewCheckBox.setBounds(17, 21, 227, 23);
                
                
                 lbl_profilePath = new JLabel("Profile Path:");
                lbl_profilePath.setFont(new Font("宋体", Font.PLAIN, 13));
                lbl_profilePath.setBounds(20, 90, 100, 30);
                
                
                 txt_profilePath = new JTextField();
                 txt_profilePath.addFocusListener(new FocusAdapter() {
                 	@Override
                 	public void focusLost(FocusEvent e) {
                 		pre.pre.data.setProjectPath(txt_profilePath.getText());
                 	}
                 });
                txt_profilePath.setMaximumSize(new Dimension(126, 21));
                txt_profilePath.setColumns(25);
                txt_profilePath.setBounds(116, 94, 294, 24);
                
                
                 btn_browseProfilePath = new JButton("Browse...");
                btn_browseProfilePath.setContentAreaFilled(false);
                btn_browseProfilePath.setFont(new Font("Calibri", Font.PLAIN, 14));
                btn_browseProfilePath.setBounds(420, 92, 81, 28);
                
                
                
                profilePanel.add(lbl_profileName);
                profilePanel.add(txt_profileName);
                profilePanel.add(chckbxNewCheckBox);
                profilePanel.add(lbl_profilePath);
                profilePanel.add(txt_profilePath);
                profilePanel.add(btn_browseProfilePath);
                
                }//end of Profile content
                
                
                
                
                
                
                JPanel inputDataPanel = new JPanel();
                inputDataPanel.setBounds(11, 266, 523, 141);
                contentPanel.add(inputDataPanel);
                inputDataPanel.setBorder(BorderFactory.createTitledBorder("Input Data:"));
                inputDataPanel.setLayout(null);
                {
                	JLabel InputDataWarnInfo = new JLabel("Your Can import input data here. Click here for help");
                	InputDataWarnInfo.setFont(new Font("宋体", Font.PLAIN, 13));
                	InputDataWarnInfo.setBounds(17, 17, 459, 30);
                    inputDataPanel.add(InputDataWarnInfo);
                    
                     checkBox = new JCheckBox("Use default input data path or set later");
                     checkBox.setSelected(true);
                     checkBox.addActionListener(new ActionListener() {
                     	public void actionPerformed(ActionEvent e) {
                     		if (checkBox.isSelected()) {
                     			setInputDataSettingChosed(true);
    						}else{
    							setInputDataSettingChosed(false);
    						}
                     		
                     	}
                     });
                    checkBox.setFont(new Font("宋体", Font.PLAIN, 13));
                    checkBox.setBounds(13, 52, 342, 23);
                    inputDataPanel.add(checkBox);
                    
                    
                     lbl_InputPath = new JLabel("Input Data Path:");
                    lbl_InputPath.setFont(new Font("宋体", Font.PLAIN, 13));
                    lbl_InputPath.setBounds(18, 85, 133, 30);
                    inputDataPanel.add(lbl_InputPath);
                    
                    txt_InputPath = new JTextField();
                    txt_InputPath.addFocusListener(new FocusAdapter() {
                    	@Override
                    	public void focusLost(FocusEvent e) {
                    		pre.pre.data.setProjectPath(txt_InputPath.getText());
                    	}
                    });
                    txt_InputPath.setMaximumSize(new Dimension(126, 21));
                    txt_InputPath.setColumns(25);
                    txt_InputPath.setBounds(134, 88, 284, 24);
                    inputDataPanel.add(txt_InputPath);
                    
                     btn_browseInputDataPath = new JButton("Browse...");
                    btn_browseInputDataPath.setFont(new Font("Calibri", Font.PLAIN, 14));
                    btn_browseInputDataPath.setContentAreaFilled(false);
                    btn_browseInputDataPath.setBounds(433, 86, 81, 28);
                    inputDataPanel.add(btn_browseInputDataPath);
                	
                }
        
        
        
        //contentPanel.add(chckbxNewCheckBox);
        setProfileSettingChosed(true);
        setInputDataSettingChosed(true);
        setVisible(true);
	}
	
	
	    
	   
	
	private void setProfileSettingChosed(boolean chosed){
		if (chosed) {
			 
			 //chckbxNewCheckBox.setSelected(true);
			  lbl_profileName.setEnabled(false);
		      txt_profileName.setEnabled(false);
		     lbl_profilePath.setEnabled(false);
		     txt_profilePath.setEnabled(false);
		     btn_browseProfilePath.setEnabled(false);
		}else{
			 //chckbxNewCheckBox.setSelected(false);
			  lbl_profileName.setEnabled(true);
		      txt_profileName.setEnabled(true);
		     lbl_profilePath.setEnabled(true);
		     txt_profilePath.setEnabled(true);
		     btn_browseProfilePath.setEnabled(true);
		}
		     
	}
	private void setInputDataSettingChosed(boolean chosed){
		     if (chosed) {
		    	// checkBox.setSelected(true);
		    	 lbl_InputPath.setEnabled(false);
		    	 txt_InputPath.setEnabled(false);
		    	 btn_browseInputDataPath.setEnabled(false);
			}else{
				//checkBox.setSelected(false);
				lbl_InputPath.setEnabled(true);
				txt_InputPath.setEnabled(true);
				btn_browseInputDataPath.setEnabled(true);
			}
	}
}
