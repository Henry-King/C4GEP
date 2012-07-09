package ui.input.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.alg.view.JPanelForStopSetting;
import ui.input.controller.ModelForDownLoadInterface;
import ui.input.controller.UploadInterfaceController;
import ui.input.model.ModelForUploadInterface;
import ui.input.view.JPanelForUploadInterface.OpenHandler;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class JPanelForInputFile extends JPanel {
	public JTextField txtInputPath = new JTextField();
	public JButton btnInputBrowse = new JButton("\u6D4F\u89C8");
	public JPanelForInputFile() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(155, 115, 631, 532);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		
		 JLabel lblNewLabel_26 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		 lblNewLabel_26.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		 lblNewLabel_26.setBounds(20, 20, 208, 25);
		 add(lblNewLabel_26);
		
		 JLabel lblNewLabel_25 = new JLabel("\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		 lblNewLabel_25.setBackground(SystemColor.inactiveCaptionBorder);
		 lblNewLabel_25.setFont(new Font("ËÎÌו", Font.PLAIN, 17));
		 lblNewLabel_25.setBounds(20, 60, 120, 33);
		 add(lblNewLabel_25);
		    
		   
		 txtInputPath.setBounds(160, 60, 362, 33);
		 add(txtInputPath);
		 txtInputPath.setColumns(10);
		    
		    
		   
		 btnInputBrowse.setBounds(532, 66, 93, 23);
		 btnInputBrowse.addActionListener(new OpenHandler());
		 add(btnInputBrowse);
	}
	 class OpenHandler implements  ActionListener{
         public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(JPanelForInputFile.this);
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
