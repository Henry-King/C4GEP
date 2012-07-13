package ui.algconfiguration.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ui.alginputdataprocess.view.MainFrame;

public class HostView extends JPanel {

	int count = 0;
	public JComboBox jcomboBoxConfiguration;
	public JButton btnNext0 = new JButton(), btnSetConfig = new JButton();
	int jcount = 1;// ���jcomboBoxConfiguration��editor�¼�����ItemSelectedchange�¼�
	int flag = 0;// �Ƿ��ȡ�����ļ�
	
	MainFrame parent;
	
	
	
	
	
	public HostView(MainFrame parent) {
		this.parent = parent;
		setBounds(155, 115, 631, 447);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		JLabel lblNewLabel_28 = new JLabel("\u7B97\u6CD5\u540D\u79F0");
		lblNewLabel_28.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_28.setBounds(20, 58, 83, 33);
		add(lblNewLabel_28);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		jcomboBoxConfiguration.setSelectedIndex(-1);
		jcomboBoxConfiguration.setFont(new Font("����", Font.PLAIN, 14));
		jcomboBoxConfiguration.setEditable(true);
		jcomboBoxConfiguration.setBackground(Color.WHITE);
		jcomboBoxConfiguration.setBounds(140, 60, 362, 33);
		final ComboBoxEditor editor = jcomboBoxConfiguration.getEditor();
		jcomboBoxConfiguration.configureEditor(editor, null);
		add(jcomboBoxConfiguration);
		jcomboBoxConfiguration.setVisible(true);

		btnSetConfig = new JButton("\u914D\u7F6E\u53C2\u6570");
		
		
		
		btnSetConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) tree_1.getModel()).getPathToRoot(node1));
				tree_1.setSelectionPath(visiblePath);
				setVisible(false);
				stopSettingPanel.setVisible(true);
				card.next(panel_0);
				treePanel.tree_1.setSelectionPath(visiblePath);
				jcount = 2;
				footPanel.btnRun.setEnabled(false);
				count = 1;
				flag = 0;
			}

		});
		
		
		
		btnSetConfig.setBounds(515, 67, 93, 23);
		btnSetConfig.setVisible(true);
		add(btnSetConfig);

		jcomboBoxConfiguration = new JComboBox(configurations);
		// ����������Ϣ
		Component scrollBtn = jcomboBoxConfiguration.getComponent(0);
		scrollBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				jcount = 1;
			}
		});

		jcomboBoxConfiguration.getEditor().getEditorComponent()
				.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnSetConfig.setVisible(true);
						}
						footPanel.btnRun.setEnabled(false);
						// ������е������������
						initialiseController(stopSettingPanel, populationPanel,
								genePanel, functionPanel, inputFilePanel);

						jcount = 2;
						flag = 0;
					}

				});

		jcomboBoxConfiguration.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if (jcount != 2) {
					myConfigurationFromDB = configurationsOfHistory
							.get(jcomboBoxConfiguration.getSelectedIndex());
					System.out.println(myConfigurationFromDB.toString());
					flag = -1;
					setTitle(myConfigurationFromDB.toString());
					footPanel.btnRun.setEnabled(true);
					if (ie.getStateChange() == ItemEvent.SELECTED) {

						footerPanel.setVisible(true);
						configurationPanel.btnSetConfig.setVisible(false);
						configurationPanel.setVisible(false);
						stopSettingPanel.setVisible(true);
					}
					// �������ļ������ݼ��ص��������
					readConfigHandler(configurationPanel, stopSettingPanel,
							populationPanel, genePanel, functionPanel,
							inputFilePanel, myConfigurationFromDB,
							configurationsOfHistory, myGepService);
					jcount = 1;
				}
			}
		});

		JLabel lblNewLabel_27 = new JLabel(
				"\u8F93\u5165\u7B97\u6CD5\u540D\u79F0/\u9009\u62E9\u7B97\u6CD5");
		lblNewLabel_27.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_27.setBounds(20, 20, 305, 25);
		add(lblNewLabel_27);
		
	}

	
	
	

	
	
	
}