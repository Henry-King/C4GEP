package ui.input.view;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;


import ui.input.controller.UploadInterfaceController;
import ui.input.model.ModelForUploadInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class JPanelForUploadInterface extends JPanel {
    public JTextField textInterfaceName = new JTextField();
	public JLabel lblNewLabel = new JLabel("\u63A5\u53E3\u540D");
	public JButton button = new JButton("\u6D4F\u89C8");
	public JButton btnUpload = new JButton("\u786E\u5B9A");
	public JTextField textInterfacePath = new JTextField();
	public JLabel lblNewLabel_2 = new JLabel("\u63A5\u53E3\u8DEF\u5F84");
	public File dir,file;
	/**
	 * Create the panel.
	 */
	public JPanelForUploadInterface() {
		setBounds(155, 115, 631, 447);
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 60, 76, 23);
		add(lblNewLabel);
		
		
		textInterfaceName.setBounds(143, 60, 262, 25);
		add(textInterfaceName);
		textInterfaceName.setColumns(10);
		
		
		button.addActionListener(
				new OpenHandler()
		);
		button.setBounds(425, 60, 93, 23);
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
		
		
		btnUpload.setBounds(425, 212, 93, 23);
		add(btnUpload);
		
		
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 122, 76, 25);
		add(lblNewLabel_2);
		
		
		textInterfacePath.setBounds(143, 124, 262, 25);
		add(textInterfacePath);
		textInterfacePath.setColumns(10);
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
}
