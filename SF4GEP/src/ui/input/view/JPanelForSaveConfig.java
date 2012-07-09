package ui.input.view;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JPanelForSaveConfig extends JPanel {

	JButton btnSave = new JButton("\u4FDD\u5B58");
	JTextField txtConfigurationName = new JTextField();
	public JPanelForSaveConfig() {
		
		setBounds(155, 115, 631, 447);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel_33 = new JLabel("\u8BF7\u4E3A\u66F4\u6539\u7684\u914D\u7F6E\u6587\u4EF6\u91CD\u547D\u540D");
		lblNewLabel_33.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_33.setBounds(20, 20, 216, 25);
		add(lblNewLabel_33);
		
		JLabel lblNewLabel_34 = new JLabel("\u914D\u7F6E\u6587\u4EF6\u540D");
		lblNewLabel_34.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel_34.setBounds(20, 60, 106, 25);
		add(lblNewLabel_34);
		
		
		txtConfigurationName.setBounds(132, 62, 271, 25);
		add(txtConfigurationName);
		txtConfigurationName.setColumns(10);
		
		
		
		btnSave.setBounds(423, 62, 78, 23);
		add(btnSave);
	}

}
