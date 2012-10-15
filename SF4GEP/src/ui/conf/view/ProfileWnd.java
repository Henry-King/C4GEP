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

import ui.app.TablePanel;
import ui.conf.controller.InputDataController;
import ui.conf.view.TypeHelper.TableType;



public class ProfileWnd extends JWindow{
	
	
	//private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	private ProfileWnd thisWnd;
	private Timer timer;
	private boolean inWnd = false;
	private JTextField textField;
	private ConfPanel confPanel;
	private DataSet inputDataSet;
	private TablePanel table;
	private boolean inElement = false;
	

	public ProfileWnd(ConfPanel confPanel) {
		thisWnd = this;
		this.confPanel = confPanel;
		this.setSize(600,450);
		mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		
		timer= new Timer(500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!inElement) {
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
		
		JLabel lbl_Title = new JLabel("Profile Setting");
		lbl_Title.setForeground(SystemColor.windowText);
		lbl_Title.setFont(new Font("Kartika", Font.BOLD, 16));
		lbl_Title.setBounds(16, 7, 242, 26);
		mainPanel.add(lbl_Title);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 580, 1);
		mainPanel.add(separator);
		
		
		//this.pack();
		//Dimension size = this.getSize();
		//this.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
		//setLocation(p);
		
		JLabel lblChooseA = new JLabel("Setting here will help you to handle parameters well");
		lblChooseA.setBounds(27, 46, 551, 15);
		mainPanel.add(lblChooseA);
		lblChooseA.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inElement = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inElement = false;
			}
		});
		textField.setBounds(125, 109, 340, 21);
		mainPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilePath = new JLabel("Profile Path:");
		lblFilePath.setBounds(27, 111, 98, 15);
		mainPanel.add(lblFilePath);
		lblFilePath.setFont(new Font("Century", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("brower");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inElement = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inElement = false;
			}
		});
		btnNewButton.setBounds(482, 104, 85, 30);
		mainPanel.add(btnNewButton);
		btnNewButton.addActionListener(new OpenHandler());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Century", Font.PLAIN, 12));
		
		JLabel lblChooseAProfile = new JLabel("Choose a profile to fille the view");
		lblChooseAProfile.setFont(new Font("Century", Font.PLAIN, 14));
		lblChooseAProfile.setBounds(27, 79, 232, 15);
		mainPanel.add(lblChooseAProfile);
		
		JLabel lblChooseAHistory = new JLabel("Choose a history profile in the table bolows");
		lblChooseAHistory.setFont(new Font("Century", Font.PLAIN, 14));
		lblChooseAHistory.setBounds(27, 156, 349, 15);
		mainPanel.add(lblChooseAHistory);
		
		table = new TablePanel(confPanel,TableType.LoadProfileTable);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inElement = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inElement = false;
			}
		});
		table.setBounds(27, 181, 551, 132);
		table.setBackground(Color.WHITE);
		mainPanel.add(table);
		
		JLabel lblIfYouWant = new JLabel("If you want load a profile above,please click it,then,system will load the profile ");
		lblIfYouWant.setFont(new Font("Century", Font.PLAIN, 14));
		lblIfYouWant.setBounds(27, 352, 551, 15);
		mainPanel.add(lblIfYouWant);
		
		JLabel lblSetting = new JLabel("setting automatically");
		lblSetting.setFont(new Font("Century", Font.PLAIN, 14));
		lblSetting.setBounds(27, 377, 551, 15);
		mainPanel.add(lblSetting);
		
		/*inputDataSet = confPanel.getInputData();
		if (inputDataSet!=null) {
			noSetPanel.setVisible(false);
			settedPanel.setVisible(true);
			
		}else{
			noSetPanel.setVisible(true);
			settedPanel.setVisible(false);
		}*/
		
		
		
		
		
		
		
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
			int rVal = jc.showOpenDialog(ProfileWnd.this);
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


	public boolean isInElement() {
		return inElement;
	}



	public void setInElement(boolean inElement) {
		this.inElement = inElement;
	}
}
