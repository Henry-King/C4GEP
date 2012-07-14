package ui.algconfiguration.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import domain.core.algconfiguration.GepAlgConfiguration;

import ui.algconfiguration.controller.ConfigController;
import ui.algconfiguration.model.ConfigModel;
import ui.alginputdataprocess.view.MainFrame;

public class ConfigView extends JPanel {

	
	public JComboBox jcomboBoxConfiguration = new JComboBox();
	public JButton btnNext0 = new JButton(), btnSetConfig = new JButton();
	int jcount = 1;// 标记jcomboBoxConfiguration的editor事件还是ItemSelectedchange事件
    public ConfigController configController = new ConfigController();
	ConfigModel configModel = new ConfigModel();
	GepAlgConfiguration myConfigurationFromDB;
	//GepAlgConfiguration newConfiguration;
	
	MainFrame parent;
	
	public ConfigView(final MainFrame parent) {
		
		this.parent = parent;
		setBounds(155, 115, 631, 447);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		JLabel lblNewLabel_28 = new JLabel("\u7B97\u6CD5\u540D\u79F0");
		lblNewLabel_28.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_28.setBounds(20, 58, 83, 33);
		add(lblNewLabel_28);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		jcomboBoxConfiguration.setSelectedIndex(-1);
		jcomboBoxConfiguration.setFont(new Font("宋体", Font.PLAIN, 14));
		jcomboBoxConfiguration.setEditable(true);
		jcomboBoxConfiguration.setBackground(Color.WHITE);
		jcomboBoxConfiguration.setBounds(140, 60, 362, 33);
		final ComboBoxEditor editor = jcomboBoxConfiguration.getEditor();
		jcomboBoxConfiguration.configureEditor(editor, null);
		add(jcomboBoxConfiguration);
		jcomboBoxConfiguration.setVisible(true);
        refresh();
        
		btnSetConfig = new JButton("\u914D\u7F6E\u53C2\u6570");
		
		
		
		btnSetConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node1));
				parent.treePanel.tree_1.setSelectionPath(visiblePath);
				setVisible(false);
				parent.stopSettingPanel.setVisible(true);
				parent.card.next(parent.panel_0);
				parent.treePanel.tree_1.setSelectionPath(visiblePath);
				jcount = 2;
				parent.footPanel.btnRun.setEnabled(false);
				parent.count = 1;
				parent.flag = 0;
			}

		});
		
		
		
		btnSetConfig.setBounds(515, 67, 93, 23);
		btnSetConfig.setVisible(true);
		add(btnSetConfig);
		
		// 加载配置信息
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
						parent.footPanel.btnRun.setEnabled(false);
						
						configController.resetConfiguration(parent);
						jcount = 2;
						parent.flag = 0;
					}
				});

		
		jcomboBoxConfiguration.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				System.out.println("有没有运行到这");
				if (jcount != 2) {
					System.out.println("有");
					myConfigurationFromDB = parent.configurationsOfHistory.get(jcomboBoxConfiguration.getSelectedIndex());
					System.out.println(myConfigurationFromDB.toString());
					parent.flag = -1;
					parent.setTitle(myConfigurationFromDB.toString());
					parent.footPanel.btnRun.setEnabled(true);
					if (ie.getStateChange() == ItemEvent.SELECTED) {

						parent.footPanel.setVisible(true);
						btnSetConfig.setVisible(false);
						setVisible(false);
						parent.stopSettingPanel.setVisible(true);
					}
					
					configController.fillConfiguration(parent,myConfigurationFromDB);
					
					jcount = 1;
					
					
				}
			}
		});

		JLabel lblNewLabel_27 = new JLabel(
				"\u8F93\u5165\u7B97\u6CD5\u540D\u79F0/\u9009\u62E9\u7B97\u6CD5");
		lblNewLabel_27.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_27.setBounds(20, 20, 305, 25);
		add(lblNewLabel_27);
		
	}

	
	
	@SuppressWarnings("unchecked")
	public void refresh(){
	
	   for (int i = 0; i < parent.configurationsOfHistory.size(); i++) {
			String configName = parent.configurationsOfHistory.get(i).getName();
			
			jcomboBoxConfiguration.addItem(configName);
		}
	}
	
	
	public void upload(){
		parent.cfgname = jcomboBoxConfiguration.getSelectedItem().toString();
	}

	
	
	
}
