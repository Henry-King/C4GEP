package ui.alginputdataprocess.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import ui.alginputdataprocess.controller.InputFileController;

public class InputFileView extends JPanel{
	
	public JTextField txtInputPath = new JTextField();
	public JButton btnInputBrowse = new JButton("\u6D4F\u89C8");
	
	InputFileController inputFileController;
	MainFrame parent;
	
	public InputFileView(MainFrame parent) {
		
		this.parent = parent;
		inputFileController = new InputFileController(parent);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(160, 115, 631, 455);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		
		JLabel lblNewLabel_26 = new JLabel(
				"\u586B\u5199\u6240\u9700\u7684\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		lblNewLabel_26.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_26.setBounds(20, 20, 208, 25);
		add(lblNewLabel_26);
		
		JLabel lblNewLabel_25 = new JLabel(
				"\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		lblNewLabel_25.setBackground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_25.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel_25.setBounds(20, 60, 120, 33);
		add(lblNewLabel_25);

		txtInputPath.setBounds(160, 60, 362, 33);
		add(txtInputPath);
		txtInputPath.setColumns(10);

		btnInputBrowse.setBounds(532, 66, 93, 23);
		btnInputBrowse.addActionListener(new OpenHandler());
		add(btnInputBrowse);
	}

	class OpenHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser jc = new JFileChooser();
			int rVal = jc.showOpenDialog(InputFileView.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File dir = jc.getCurrentDirectory();
				File file = jc.getSelectedFile();
				txtInputPath.setText(file.toString());
				
				
				/**
				 * 赋值参数给主面板
				 */
				inputFileController.setInputSet(txtInputPath.getText().toString(), parent);
				
				
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				txtInputPath.setText("You pressed cancel");
			}
		}
	}

	
	
	
	
	public void refresh(){
		
	}
	
	
	public void upload(){
		//parent.inputSet = inputFileController.getInputSet(inputPath);
	}
	

	
}
