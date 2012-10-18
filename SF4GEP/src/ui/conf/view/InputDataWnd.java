package ui.conf.view;

import java.awt.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algconfiguration.GepAlgConfiguration;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.List;

import ui.conf.controller.InputDataController;



public class InputDataWnd extends JWindow{
	
	
	//private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	private InputDataWnd thisWnd;
	private Timer timer;
	private boolean inWnd = false;
	public boolean inBtn = false;
	public boolean inTextField = false;
	private JTextField textField;
	private ConfPanel confPanel;
	private DataSet inputDataSet;
	public JButton btnNewButton;
	public JButton btn_chsNewData;
	public JButton btn_viewInputData;
	public InputDataWnd(ConfPanel confPanel) {
		thisWnd = this;
		this.confPanel = confPanel;
		this.setSize(400,400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		
		
		
		
		
		
		
		
		
		
		timer= new Timer(500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!inBtn&&!inTextField) {
					thisWnd.setVisible(false);
					thisWnd.dispose();
				}
				
				
			}
		});
		 
		
		
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inWnd = true;
				if (timer.isRunning()) {
					timer.stop();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inWnd = false;
				if (!timer.isRunning()) {
					timer.start();
				}
				
			}
		});
		mainPanel.setBackground(Color.WHITE);
		
		
		
		
		
		
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lbl_Title = new JLabel("Input Data Setting");
		lbl_Title.setForeground(SystemColor.windowText);
		lbl_Title.setFont(new Font("Kartika", Font.BOLD, 16));
		lbl_Title.setBounds(15, 7, 242, 26);
		mainPanel.add(lbl_Title);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 31, 380, 2);
		mainPanel.add(separator);
		
		JLabel lbl_currentSatus = new JLabel("Current Input Data Status:");
		lbl_currentSatus.setFont(new Font("Century", Font.PLAIN, 14));
		lbl_currentSatus.setBounds(20, 36, 186, 20);
		mainPanel.add(lbl_currentSatus);
		
		JLabel info_currentStatus = new JLabel("no set");
		info_currentStatus.setFont(new Font("Century", Font.PLAIN, 14));
		info_currentStatus.setBounds(217, 36, 123, 20);
		mainPanel.add(info_currentStatus);
		
		JPanel noSetPanel = new JPanel();
		noSetPanel.setVisible(false);
		noSetPanel.setBackground(Color.WHITE);
		noSetPanel.setBounds(10, 64, 380, 326);
		
		noSetPanel.setLayout(null);
		
		JLabel lbl_chooseTip = new JLabel(" You can choose a way to load the input data!");
		lbl_chooseTip.setFont(new Font("Century", Font.PLAIN, 14));
		lbl_chooseTip.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_chooseTip.setBounds(0, 0, 381, 24);
		lbl_chooseTip.setBackground(new Color(255, 255, 102));
		lbl_chooseTip.setOpaque(true);
		noSetPanel.add(lbl_chooseTip);
		
		JLabel lblChooseA = new JLabel("#1 Choose a input file in your computer");
		lblChooseA.setFont(new Font("Century", Font.PLAIN, 14));
		lblChooseA.setBounds(24, 35, 270, 20);
		noSetPanel.add(lblChooseA);
		
		JLabel lblFilePath = new JLabel("File Path:");
		lblFilePath.setFont(new Font("Century", Font.PLAIN, 14));
		lblFilePath.setBounds(10, 60, 77, 15);
		noSetPanel.add(lblFilePath);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inTextField = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inTextField = false;
			}
		});
		textField.setBounds(97, 57, 197, 21);
		noSetPanel.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("brower");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				inBtn = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inBtn = false;
			}
		});
		btnNewButton.addActionListener(new OpenHandler());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButton.setBounds(305, 56, 66, 23);
		noSetPanel.add(btnNewButton);
		
		JLabel lblChooseA_1 = new JLabel("#2 Choose a input file you used before");
		lblChooseA_1.setFont(new Font("Century", Font.PLAIN, 14));
		lblChooseA_1.setBounds(24, 99, 270, 20);
		noSetPanel.add(lblChooseA_1);
		
		
		/**
		 * 查看当前操作的数据集
		 */
		
		
		/**
		 * 重新选择输入数据集
		 */
		
		JPanel settedPanel = new JPanel();
		settedPanel.setBounds(10, 64, 380, 326);
		
		settedPanel.setVisible(false);
		settedPanel.setBackground(Color.WHITE);
		
		
		
		mainPanel.add(settedPanel);
		mainPanel.add(noSetPanel);
		
		settedPanel.setLayout(null);
		
		JLabel lblInputDataName = new JLabel("Input Data Name:");
		lblInputDataName.setFont(new Font("Century", Font.PLAIN, 14));
		lblInputDataName.setBounds(11, 4, 131, 20);
		settedPanel.add(lblInputDataName);
		
		JLabel info_inputDataName = new JLabel("xxx");
		info_inputDataName.setFont(new Font("Century", Font.PLAIN, 14));
		info_inputDataName.setBounds(147, 4, 204, 20);
		settedPanel.add(info_inputDataName);
		
		JLabel lblDatarows = new JLabel("Data File Path:");
		lblDatarows.setFont(new Font("Century", Font.PLAIN, 14));
		lblDatarows.setBounds(11, 28, 131, 20);
		settedPanel.add(lblDatarows);
		
		JLabel lblColumnrows = new JLabel("DataRows:");
		lblColumnrows.setFont(new Font("Century", Font.PLAIN, 14));
		lblColumnrows.setBounds(11, 53, 94, 20);
		settedPanel.add(lblColumnrows);
		
		JLabel label = new JLabel("DataColumns:");
		label.setFont(new Font("Century", Font.PLAIN, 14));
		label.setBounds(11, 78, 94, 20);
		settedPanel.add(label);
		
		JLabel info_dataPath = new JLabel("xxx");
		info_dataPath.setFont(new Font("Century", Font.PLAIN, 14));
		info_dataPath.setBounds(147, 29, 204, 15);
		settedPanel.add(info_dataPath);
		
		JLabel info_dataRowNum = new JLabel("xxx");
		info_dataRowNum.setFont(new Font("Century", Font.PLAIN, 14));
		info_dataRowNum.setBounds(147, 53, 204, 15);
		settedPanel.add(info_dataRowNum);
		
		JLabel info_dataColumnNum = new JLabel("xxx");
		info_dataColumnNum.setFont(new Font("Century", Font.PLAIN, 14));
		info_dataColumnNum.setBounds(147, 78, 204, 15);
		settedPanel.add(info_dataColumnNum);
		
		
		//this.pack();
		//Dimension size = this.getSize();
		//this.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
		//setLocation(p);
		
		
		
		
		
		JLabel lblIWantChoose = new JLabel("  You also can do things below");
		lblIWantChoose.setFont(new Font("Century", Font.PLAIN, 14));
		lblIWantChoose.setBounds(11, 106, 360, 24);
		lblIWantChoose.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblIWantChoose.setBackground(new Color(255, 255, 102));
		lblIWantChoose.setOpaque(true);
		settedPanel.add(lblIWantChoose);
		
		JLabel lblChoooseANew = new JLabel("View the input data that deal with now");
		lblChoooseANew.setFont(new Font("Century", Font.PLAIN, 14));
		lblChoooseANew.setBounds(21, 140, 255, 20);
		settedPanel.add(lblChoooseANew);
		btn_viewInputData = new JButton("brower");
		btn_viewInputData.addActionListener(new OpenHandler());
		btn_viewInputData.setContentAreaFilled(false);
		btn_viewInputData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				inBtn = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inBtn = false;
			}
		});
		btn_viewInputData.setBounds(286, 140, 73, 23);
		settedPanel.add(btn_viewInputData);
		
		JLabel label_1 = new JLabel("Chooose a new input data");
		label_1.setFont(new Font("Century", Font.PLAIN, 14));
		label_1.setBounds(21, 169, 255, 20);
		settedPanel.add(label_1);
		btn_chsNewData = new JButton("brower");
		btn_chsNewData.setContentAreaFilled(false);
		btn_chsNewData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				inBtn = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inBtn = false;
			}
		});
		btn_chsNewData.addActionListener(new OpenHandler());
		btn_chsNewData.setBounds(286, 169, 73, 23);
		settedPanel.add(btn_chsNewData);
		
		JLabel lblSetTheInput = new JLabel("Set input data profile");
		lblSetTheInput.setFont(new Font("Century", Font.PLAIN, 14));
		lblSetTheInput.setBounds(21, 198, 255, 20);
		settedPanel.add(lblSetTheInput);
		
		JButton btn_setProfile = new JButton("brower");
		btn_setProfile.setContentAreaFilled(false);
		btn_setProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				inBtn = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inBtn = false;
			}
		});
		btn_setProfile.setBounds(286, 198, 73, 23);
		settedPanel.add(btn_setProfile);
		
		
		
		
		inputDataSet = confPanel.getInputData();
		if (inputDataSet!=null) {
			noSetPanel.setVisible(false);
			settedPanel.setVisible(true);
			File file = confPanel.getInputFile();
			info_currentStatus.setText("has setted");
			info_inputDataName.setText(file.getName());
			info_dataPath.setText(file.getPath());
			info_dataRowNum.setText(inputDataSet.getRowNum().toString());
			info_dataColumnNum.setText(((int)inputDataSet.getColumnNum()+1) + "");
			
			
		}else{
			noSetPanel.setVisible(true);
			settedPanel.setVisible(false);
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	public void setLocation(Point p){
		this.setLocation((int)p.getX(), (int)p.getY());
	}
	
	public boolean isInWnd() {
		return inWnd;
	}
	
	
	class OpenHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser jc = new JFileChooser();
			int rVal = jc.showOpenDialog(InputDataWnd.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File dir = jc.getCurrentDirectory();
				File file = jc.getSelectedFile();
				//textField.setText(file.toString());
				
				if (e.getSource()==btnNewButton||e.getSource()==btn_chsNewData) {
					InputDataController inputDataController = new InputDataController(confPanel.mainWnd.getHibernateDataContext());
					inputDataSet = inputDataController.getInputSet(file.toString());
					confPanel.setInputData(inputDataSet);
					confPanel.setInputFile(file);
					confPanel.outputStatusLabel.setText("You can run now,and it will show the result with two pictures.");
				}else if(e.getSource()==btn_viewInputData){
					//
				}
				
				
				
				
				
                 /*System.out.println("inputdata");
                 List<DataRow> rows = inputDataSet.getDataRows();
                 for (int i = 0; i < rows.size()-1; i++) {
						DataRow row = rows.get(i);
						List<DataColumn> dc =row.getDataColumns();
						
						for (int j = 0; j < dc.size(); j++) {
							System.out.print(dc.get(j).getValue()+ " ");
						}
						DataColumn dcr = row.getResultColumn();
						System.out.println(dcr.getValue());
						System.out.println();
				}
				*/
				
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				textField.setText("You pressed cancel");
			}
		}
	}
}
