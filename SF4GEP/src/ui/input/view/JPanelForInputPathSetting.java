package ui.input.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;






public class JPanelForInputPathSetting extends JPanel {

	
	public JTextField txtInputPath = new JTextField(),
	                  txtMaxGeneration=new JTextField(),
	                  txtAccuracy=new JTextField();
	
	public JButton btnInputBrowse = new JButton("\u6D4F\u89C8");
	

	public JPanelForInputPathSetting() {
		   
		    setBounds(155, 115, 631, 447);
		    setVisible(false);
		    setBorder(new LineBorder(new Color(0, 0, 0)));
		    setBackground(Color.WHITE);
		    setLayout(null);
		    
		    JLabel lblNewLabel_25 = new JLabel("\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		    lblNewLabel_25.setBackground(SystemColor.inactiveCaptionBorder);
		    lblNewLabel_25.setFont(new Font("宋体", Font.PLAIN, 17));
		    lblNewLabel_25.setBounds(20, 60, 120, 33);
		    add(lblNewLabel_25);
		    
		   
		    txtInputPath.setBounds(160, 60, 362, 33);
		    add(txtInputPath);
		    txtInputPath.setColumns(10);
		    
		    
		   
		    btnInputBrowse.setBounds(532, 60, 93, 23);
		    btnInputBrowse.addActionListener(new OpenHandler());
		    add(btnInputBrowse);
		    
		    JLabel lblNewLabel_26 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		    lblNewLabel_26.setFont(new Font("宋体", Font.PLAIN, 18));
		    lblNewLabel_26.setBounds(20, 20, 208, 25);
		    add(lblNewLabel_26);
		    
		    JPanel panel = new JPanel();
		    panel.setBounds(0, 561, 813, 87);
		    
		    panel.setLayout(null);
		    
		    JLabel lblNewLabel_38 = new JLabel("-----------1.0\u7248\u672C\uFF08c\uFF09Miscrosoft Cooperation------------");
		    lblNewLabel_38.setBounds(162, 35, 463, 28);
		    lblNewLabel_38.setFont(new Font("宋体", Font.PLAIN, 13));
		    lblNewLabel_38.setForeground(Color.RED);
		    panel.add(lblNewLabel_38);
		    JLabel lblNewLabel_40 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u7B97\u6CD5\u7EC8\u6B62\u6761\u4EF6");
		    lblNewLabel_40.setFont(new Font("宋体", Font.PLAIN, 18));
		    lblNewLabel_40.setBounds(20, 130, 208, 25);
		    add(lblNewLabel_40);
		    
		    JLabel lblNewLabel_41 = new JLabel("\u6F14\u5316\u4EE3\u6570");
		    lblNewLabel_41.setFont(new Font("宋体", Font.PLAIN, 17));
		    lblNewLabel_41.setBounds(20, 170, 120, 25);
		    add(lblNewLabel_41);
		    
		    txtMaxGeneration.setBounds(160, 170, 160, 25);
		    add(txtMaxGeneration);
		    txtMaxGeneration.setColumns(10);
		    
		    JLabel lblNewLabel_42 = new JLabel("\u7B97\u6CD5\u7ED3\u679C\u7684\u7CBE\u5EA6");
		    lblNewLabel_42.setFont(new Font("宋体", Font.PLAIN, 17));
		    lblNewLabel_42.setBounds(20, 220, 120, 25);
		    add(lblNewLabel_42);
		    
		    txtAccuracy = new JTextField();
		    txtAccuracy.setBounds(160, 220, 160, 25);
		    add(txtAccuracy);
		    txtAccuracy.setColumns(10);
		
		}
	   class OpenHandler implements  ActionListener{
         public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(JPanelForInputPathSetting.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
				File dir=jc.getCurrentDirectory();
				File file=jc.getSelectedFile();
				txtInputPath.setText(file.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 txtInputPath.setText("You pressed cancel");
			
			}
		}
	}
}
