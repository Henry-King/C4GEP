package ui.conf.view;

import javax.swing.*;

import ui.app.*;
import ui.conf.model.NewProjectModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.border.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import domain.core.algInputDataProcess.DataSet;


/**
 * �㷨��Ŀ��
 * ����������Ŀ�����뼯�ļ��������ļ���ʵ��
 * @author Administrator
 */
public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;
	public MainWnd mainWnd;
	public JTabbedPane projectTabPane;
	private DataSet inputData;
	private NewProjectModel newProjectModel;
	private File inputFile;
	
	private boolean hasFittingCurve = false;
	private boolean hasEvolution = false;
	
	
	
	public ContentPanel contentPanel;
	/**
	 * Create the panel.
	 */
	public ConfPanel(MainWnd mainWnd) {
		setLayout(new BorderLayout(0, 0));
		this.mainWnd = mainWnd;
		
		projectTabPane = new JTabbedPane(JTabbedPane.TOP);
		add(projectTabPane);
		
		/*�������*/
		contentPanel = new ContentPanel(mainWnd,this);
		contentPanel.setName("MainPanel");	//ͨ��name���ж�TabPanel�еĸ������
		projectTabPane.addTab("Main Panel",null,contentPanel,null);
		
		JPanel runtimePanel = new JPanel();
		runtimePanel.setName("Runtime");
		projectTabPane.addTab("Runtime",null,runtimePanel,null);
		
		JPanel logPanel = new JPanel();
		logPanel.setName("Log");
		projectTabPane.addTab("Log",null,logPanel,null);
		
		
		/*���ߺ�״̬��*/
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);

		
		
		MainToolBar toolBar = new MainToolBar(mainWnd,this);
		toolBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		TLPanel.add(toolBar, BorderLayout.NORTH);

		JLabel outputStatusLabel = new JLabel("������ʾ״̬��Ϣ");
		outputStatusLabel.setFont(new Font("����", Font.PLAIN, 12));
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
		this.inputData = newProjectModel.getInputDataSet();
	}


	public File getInputFile() {
		return inputFile;
	}


	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}


	public boolean isHasFittingCurve() {
		return hasFittingCurve;
	}


	public void setHasFittingCurve(boolean hasFittingCurve) {
		this.hasFittingCurve = hasFittingCurve;
	}


	public boolean isHasEvolution() {
		return hasEvolution;
	}


	public void setHasEvolution(boolean hasEvolution) {
		this.hasEvolution = hasEvolution;
	}

}
