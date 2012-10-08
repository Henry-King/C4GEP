package ui.conf.view;

import javax.swing.*;

import ui.app.*;
import ui.conf.model.NewProjectModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.border.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import domain.core.algInputDataProcess.DataSet;


/**
 * 算法项目类
 * 包含本次项目的输入集文件和配置文件的实例
 * @author Administrator
 */
public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;
	private MainWnd mainWnd;
	private DataSet inputData;
	private NewProjectModel newProjectModel;
	
	
	public ContentPanel contentPanel;
	/**
	 * Create the panel.
	 */
	public ConfPanel(MainWnd mainWnd) {
		setLayout(new BorderLayout(0, 0));
		this.mainWnd = mainWnd;
		
		
		/*内容面板*/
		contentPanel = new ContentPanel(mainWnd);
		add(contentPanel);
		
		
		/*工具和状态栏*/
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);

		
		
		MainToolBar toolBar = new MainToolBar(mainWnd,this);
		toolBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		TLPanel.add(toolBar, BorderLayout.NORTH);

		JLabel outputStatusLabel = new JLabel("这里显示状态信息");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));
		TLPanel.add(outputStatusLabel, BorderLayout.SOUTH);
//end of tool panel and status panel
		
		
	}


	public DataSet getInputData() {
		return inputData;
	}


	public void setInputData(DataSet inputData) {
		this.inputData = inputData;
	}


	public NewProjectModel getNewProjectModel() {
		return newProjectModel;
	}


	public void setNewProjectModel(NewProjectModel newProjectModel) {
		this.newProjectModel = newProjectModel;
	}

}
