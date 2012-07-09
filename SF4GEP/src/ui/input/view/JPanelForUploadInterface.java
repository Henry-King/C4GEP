package ui.input.view;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;


import ui.input.controller.DownLoadInterfaceController;
import ui.input.controller.ModelForDownLoadInterface;
import ui.input.controller.UploadInterfaceController;
import ui.input.model.ModelForUploadInterface;
import ui.input.view.JPanelForInputFile.OpenHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.border.LineBorder;

public class JPanelForUploadInterface extends JPanel {
    public JTextField textInterfaceName = new JTextField();
	public JLabel lblNewLabel = new JLabel("\u63A5\u53E3\u540D");
	public JButton button = new JButton("\u6D4F\u89C8");
	public JButton btnUpload = new JButton("\u4E0A\u4F20");
	public JTextField textInterfacePath = new JTextField();
	public JLabel lblNewLabel_2 = new JLabel("\u63A5\u53E3\u8DEF\u5F84");
	public File dir,file;
	//
	public JLabel lblNewLabel2 = new JLabel("\u53EF\u4F9B\u4E0B\u8F7D\u7684\u63A5\u53E3");
	public JButton buttonSaveDirBrowse = new JButton("\u6D4F\u89C8");
	public JButton btnDownLoad = new JButton("\u4E0B\u8F7D");
	public JTextField textInterfaceSavePath = new JTextField();
	public JLabel lblNewLabel3 = new JLabel("\u4FDD\u5B58\u8DEF\u5F84");
	public File dirForSavePath,fileForSeleInterface;
	String[] interfaceArr=new String[]{"Function","Calculator","Creator","Modifying","Selector"};
	JComboBox interfaceComboBox = new JComboBox(interfaceArr);
	/**
	 * Create the panel.
	 */
	public JPanelForUploadInterface() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(155, 115, 673, 479);
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 80, 76, 23);
		add(lblNewLabel);
		
		
		textInterfaceName.setBounds(192, 80, 262, 25);
		add(textInterfaceName);
		textInterfaceName.setColumns(10);
		
		
		button.addActionListener(
				new OpenHandler()
		);
		button.setBounds(505, 80, 93, 23);
		add(button);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelForUploadInterface uploadInterface=new ModelForUploadInterface(file,dir,textInterfaceName.getText(),textInterfacePath.getText());
				try {
					int result=UploadInterfaceController.btnUploadController(uploadInterface);
					if(result==-1){
						JOptionPane.showMessageDialog(null, "该文件已存在，请重新选择上传文件");
						textInterfaceName.setText("");
						textInterfacePath.setText("");
					}
					else if(result==0){
						JOptionPane.showMessageDialog(null, "上传成功");
					}
					else if(result==-2){
						JOptionPane.showMessageDialog(null, "该文件没有实现指定接口，上传失败");
						textInterfaceName.setText("");
						textInterfacePath.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, "该文件没有缺省的构造函数，上传失败");
						textInterfaceName.setText("");
						textInterfacePath.setText("");
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnUpload.setBounds(505, 140, 93, 23);
		add(btnUpload);
		
		
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(30, 140, 76, 25);
		add(lblNewLabel_2);
		
		
		textInterfacePath.setBounds(192, 140, 262, 25);
		add(textInterfacePath);
		textInterfacePath.setColumns(10);
		setVisible(true);
		
		
		
		
		
		
		
		
		//------------------------------------------------------
		lblNewLabel2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel2.setBounds(30, 260, 141, 23);
		add(lblNewLabel2);
		
		
		buttonSaveDirBrowse.addActionListener(
				new SaveHandler()
		);
		buttonSaveDirBrowse.setBounds(505, 320, 93, 23);
		add(buttonSaveDirBrowse);
		btnDownLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceComboBox.setSelectedItem(interfaceComboBox.getSelectedItem());
				System.out.println(interfaceComboBox.getSelectedItem().toString());
				ModelForDownLoadInterface downLoadInterface=new ModelForDownLoadInterface(dirForSavePath,interfaceComboBox.getSelectedItem().toString(),textInterfaceSavePath.getText());
				try {
					DownLoadInterfaceController.btnDownLoadController(downLoadInterface);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnDownLoad.setBounds(505, 390, 93, 23);
		add(btnDownLoad);
		
		
		lblNewLabel3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel3.setBounds(30, 320, 76, 25);
		add(lblNewLabel3);
		
		
		textInterfaceSavePath.setBounds(192, 320, 262, 25);
		add(textInterfaceSavePath);
		textInterfaceSavePath.setColumns(10);
		
		
		interfaceComboBox.setBounds(192, 260, 262, 25);
		add(interfaceComboBox);
		
		JLabel label = new JLabel("\u4E0A\u4F20\u63A5\u53E3");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(30, 25, 84, 23);
		add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E0B\u8F7D\u63A5\u53E3");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(30, 203, 72, 30);
		add(lblNewLabel_1);
		setVisible(true);
	}
	class OpenHandler implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(JPanelForUploadInterface.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
			    dir=jc.getCurrentDirectory();
				file=jc.getSelectedFile();
				textInterfaceName.setText(file.getName());
				textInterfacePath.setText(dir.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 textInterfaceName.setText("You pressed cancel");
			
			}
		}
	}
	class SaveHandler implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(JPanelForUploadInterface.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
			    dirForSavePath=jc.getCurrentDirectory();
				fileForSeleInterface=jc.getSelectedFile();
				textInterfaceSavePath.setText(dirForSavePath.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 textInterfaceSavePath.setText("You pressed cancel");
			
			}
		}
	}
}
