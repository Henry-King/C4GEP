package ui.alg.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class HostPanel extends JPanel {

	
	int count=0;
	public JComboBox jcomboBoxConfiguration;
	public JButton btnRunButton = new JButton("\u6267\u884C\u7B97\u6CD5"),
			       btnChangeConfig = new JButton("\u66F4\u6539\u914D\u7F6E"),
                   btnNext0=new JButton(),
                   btnSetConfig=new JButton();
	
	public HostPanel(JComboBox jcomboBoxConfiguration) {
		
		setBounds(155, 115, 631, 447);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		JLabel lblNewLabel_28 = new JLabel("\u7B97\u6CD5\u540D\u79F0");
		lblNewLabel_28.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_28.setBounds(20, 58, 83, 33);
		add(lblNewLabel_28);
		setBorder(new LineBorder(new Color(0, 0, 0)));
        jcomboBoxConfiguration.setSelectedIndex(-1);
		jcomboBoxConfiguration.setFont(new Font("ËÎÌו", Font.PLAIN, 14));
		jcomboBoxConfiguration.setEditable(true);
		jcomboBoxConfiguration.setBackground(Color.WHITE);
		jcomboBoxConfiguration.setBounds(140, 60, 362, 33);
		final ComboBoxEditor editor=jcomboBoxConfiguration.getEditor();
	    jcomboBoxConfiguration.configureEditor(editor,null);
		add(jcomboBoxConfiguration);
	    jcomboBoxConfiguration.setVisible(true);
	   
	    
	    
	
	
	    
	    
	   
        btnSetConfig = new JButton("\u914D\u7F6E\u53C2\u6570");
		btnSetConfig.setBounds(515, 67, 93, 23);
		btnSetConfig.setVisible(true);
		add(btnSetConfig);
		
	   
	   
		btnRunButton.setBounds(193, 120, 93, 23);
		btnRunButton.setVisible(false);
		add(btnRunButton);
		
	    
	    
		btnChangeConfig.setBounds(398, 120, 93, 23);
		btnChangeConfig.setVisible(false);
		add(btnChangeConfig);
		
		
		JLabel lblNewLabel_27 = new JLabel("\u8F93\u5165\u7B97\u6CD5\u540D\u79F0/\u9009\u62E9\u7B97\u6CD5");
		lblNewLabel_27.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_27.setBounds(20, 20, 305, 25);
		add(lblNewLabel_27);
		btnNext0 = new JButton("\u4E0B\u4E00\u6B65");
		btnNext0.setBounds(528, 414, 93, 23);
		add(btnNext0);
		
		
		
	    
		
	}

}
