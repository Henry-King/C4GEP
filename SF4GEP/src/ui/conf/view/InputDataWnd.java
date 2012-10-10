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
	private JTextField textField;
	private ConfPanel confPanel;
	private DataSet inputDataSet;

	public InputDataWnd(ConfPanel confPanel,Point p) {
		thisWnd = this;
		this.confPanel = confPanel;
		this.setSize(400,400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		mainPanel.setFont(new Font("Arial", Font.PLAIN, 13));
		
		
		
		
		
		
		
		
		
		
		
		
		timer= new Timer(500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thisWnd.setVisible(false);
				thisWnd.dispose();
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
		lbl_Title.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_Title.setFont(new Font("ËÎÌו", Font.BOLD, 16));
		lbl_Title.setBounds(10, 0, 242, 26);
		mainPanel.add(lbl_Title);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 27, 380, 2);
		mainPanel.add(separator);
		
		JLabel lbl_currentSatus = new JLabel("Current Input Data Status:");
		lbl_currentSatus.setFont(new Font("Arial", Font.PLAIN, 13));
		lbl_currentSatus.setBounds(20, 36, 157, 15);
		mainPanel.add(lbl_currentSatus);
		
		JLabel info_currentStatus = new JLabel("no set");
		info_currentStatus.setFont(new Font("Arial", Font.PLAIN, 13));
		info_currentStatus.setBounds(187, 36, 157, 15);
		mainPanel.add(info_currentStatus);
		
		JPanel noSetPanel = new JPanel();
		noSetPanel.setVisible(false);
		noSetPanel.setBackground(Color.WHITE);
		noSetPanel.setBounds(10, 54, 381, 336);
		
		noSetPanel.setLayout(null);
		
		JLabel lbl_chooseTip = new JLabel(" You can choose a way to load the input data!");
		lbl_chooseTip.setFont(new Font("Arial", Font.PLAIN, 13));
		lbl_chooseTip.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lbl_chooseTip.setBounds(0, 0, 381, 24);
		lbl_chooseTip.setBackground(new Color(255, 255, 102));
		lbl_chooseTip.setOpaque(true);
		noSetPanel.add(lbl_chooseTip);
		
		JLabel lblChooseA = new JLabel("#1 Choose a input file in your computer");
		lblChooseA.setFont(new Font("Arial", Font.PLAIN, 13));
		lblChooseA.setBounds(24, 35, 270, 15);
		noSetPanel.add(lblChooseA);
		
		JLabel lblFilePath = new JLabel("File Path:");
		lblFilePath.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFilePath.setBounds(10, 60, 56, 15);
		noSetPanel.add(lblFilePath);
		
		textField = new JTextField();
		textField.setBounds(70, 57, 224, 21);
		noSetPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("brower");
		btnNewButton.addActionListener(new OpenHandler());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 10));
		btnNewButton.setBounds(305, 56, 66, 23);
		noSetPanel.add(btnNewButton);
		
		JLabel lblChooseA_1 = new JLabel("#2 Choose a input file you used before");
		lblChooseA_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblChooseA_1.setBounds(24, 99, 270, 15);
		noSetPanel.add(lblChooseA_1);
		
		JPanel settedPanel = new JPanel();
		settedPanel.setVisible(false);
		settedPanel.setBounds(10, 54, 381, 336);
		settedPanel.setBackground(Color.WHITE);
		
		
		
		
		
		settedPanel.setLayout(null);
		
		JLabel lblInputDataName = new JLabel("Input Data Name:");
		lblInputDataName.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInputDataName.setBounds(11, 4, 107, 15);
		settedPanel.add(lblInputDataName);
		
		JLabel info_inputDataName = new JLabel("xxx");
		info_inputDataName.setFont(new Font("Arial", Font.PLAIN, 13));
		info_inputDataName.setBounds(120, 4, 231, 15);
		settedPanel.add(info_inputDataName);
		
		JLabel lblDatarows = new JLabel("Data File Path:");
		lblDatarows.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDatarows.setBounds(11, 28, 93, 15);
		settedPanel.add(lblDatarows);
		
		JLabel lblColumnrows = new JLabel("DataRows:");
		lblColumnrows.setFont(new Font("Arial", Font.PLAIN, 13));
		lblColumnrows.setBounds(10, 53, 94, 15);
		settedPanel.add(lblColumnrows);
		
		JLabel label = new JLabel("DataColumns:");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setBounds(11, 78, 94, 15);
		settedPanel.add(label);
		
		JLabel info_dataPath = new JLabel("xxx");
		info_dataPath.setFont(new Font("Arial", Font.PLAIN, 13));
		info_dataPath.setBounds(120, 29, 231, 15);
		settedPanel.add(info_dataPath);
		
		JLabel lbl_dataRowNum = new JLabel("xxx");
		lbl_dataRowNum.setFont(new Font("Arial", Font.PLAIN, 13));
		lbl_dataRowNum.setBounds(120, 53, 231, 15);
		settedPanel.add(lbl_dataRowNum);
		
		JLabel lbl_dataColumnNum = new JLabel("xxx");
		lbl_dataColumnNum.setFont(new Font("Arial", Font.PLAIN, 13));
		lbl_dataColumnNum.setBounds(120, 78, 231, 15);
		settedPanel.add(lbl_dataColumnNum);
		
		
		//this.pack();
		//Dimension size = this.getSize();
		//this.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
		setLocation(p);
		
		
		mainPanel.add(noSetPanel);
		mainPanel.add(settedPanel);
		
		inputDataSet = confPanel.getInputData();
		if (inputDataSet!=null) {
			noSetPanel.setVisible(false);
			settedPanel.setVisible(true);
			
		}else{
			noSetPanel.setVisible(true);
			settedPanel.setVisible(false);
		}
		
		
		
		
		
		
		
	}
	
	
	
	public void setLocation(Point p){
		this.setLocation((int)p.getX()+80, (int)p.getY()+90);
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
				InputDataController inputDataController = new InputDataController(confPanel.mainWnd.getHibernateDataContext());
				inputDataSet = inputDataController.getInputSet(file.toString());
				confPanel.setInputData(inputDataSet);
				
                 System.out.println("inputdata");
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
				
				
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				textField.setText("You pressed cancel");
			}
		}
	}
}
