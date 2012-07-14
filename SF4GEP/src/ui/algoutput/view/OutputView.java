package ui.algoutput.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import ui.alginputdataprocess.view.MainFrame;
import ui.algoutput.controller.OutputController;

import com.wolfram.jlink.MathCanvas;

import domain.core.algconfiguration.GepAlgConfiguration;
import domain.service.algConfiguration.GepConfigurationService;

public class OutputView extends JPanel{
	
	JLabel label = new JLabel("\u7B97\u6CD5\u8BA1\u7B97\u7ED3\u679C\u7684\u8F93\u51FA\u8DEF\u5F84");
	JLabel label_37 = new JLabel("\u8F93\u51FA\u4EE3\u6570");
	JButton btnBrowseOfOutput = new JButton("\u6D4F\u89C8");
	JTextField txtOutputPath = new JTextField();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
	JPanel panelForGeneration = new JPanel();
	JTextField textGeneration = new JTextField();
	JPanel panelForGeneRationFrom = new JPanel();
	JPanel panelPaint = new JPanel();
	public JPanel outPutPanel_1 = new JPanel();
	public JPanel outputPanel_2 = new JPanel();
	JTextField txtGenerationTo = new JTextField();
	JTextField txtGenerationFrom = new JTextField();
	public JButton btnNewButton = new JButton("\u8FD4\u56DE");

	public MathCanvas mathCanvasA = null;
	public MathCanvas mathCanvasB = null;
	
	public OutputController outputController = new OutputController();

	MainFrame parent;

	public OutputView(final MainFrame parent) {
		
		this.parent = parent;
		
		/**
		 * 初始化Kernel
		 */
		outputController.initKernel(this);
		
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		//计算结果的输出路径
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(14, 335, 174, 26);
		add(label);
		txtOutputPath.grabFocus();
		txtOutputPath.setBounds(14, 380, 290, 25);
        add(txtOutputPath);
		txtOutputPath.setColumns(10);
		//btnBrowseOfOutput.addActionListener(new OpenOutputHandler());
		btnBrowseOfOutput.setFont(new Font("宋体", Font.PLAIN, 15));
		btnBrowseOfOutput.setBounds(225, 440, 93, 23);
		add(btnBrowseOfOutput);
		
		
		//输出代数
		
		label_37.setFont(new Font("宋体", Font.PLAIN, 15));
		label_37.setBounds(415, 335, 82, 18);
		add(label_37);
		
		
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(415, 380, 307, 60);
		add(tabbedPane);
		
		//输出后多少代
		
		panelForGeneration.setBorder(null);
		panelForGeneration.setBackground(Color.WHITE);
		tabbedPane.addTab("后x代", null, panelForGeneration, null);
		panelForGeneration.setLayout(null);
		
		
		textGeneration.setBounds(0, 0, 302, 26);
		panelForGeneration.add(textGeneration);
		textGeneration.setColumns(10);
		textGeneration.grabFocus();
		
		//输出从多少代到多少代
		
		panelForGeneRationFrom.setBorder(null);
		panelForGeneRationFrom.setBackground(Color.WHITE);
		tabbedPane.addTab("从X代到y代", null, panelForGeneRationFrom, null);
		panelForGeneRationFrom.setLayout(null);
		
		
		txtGenerationFrom.setBounds(0, 2, 121, 26);
		panelForGeneRationFrom.add(txtGenerationFrom);
		txtGenerationFrom.setColumns(10);
		txtGenerationFrom.grabFocus();
		JLabel lblTo = new JLabel("To");
		lblTo.setBackground(Color.LIGHT_GRAY);
		lblTo.setForeground(Color.BLACK);
		lblTo.setBounds(131, 3, 42, 25);
		lblTo.setBorder(null);
		panelForGeneRationFrom.add(lblTo);
		
		
		txtGenerationTo.setBounds(173, 2, 129, 26);
		panelForGeneRationFrom.add(txtGenerationTo);
		txtGenerationTo.setColumns(10);
		txtGenerationTo.grabFocus();
		JButton btnRunGeneration = new JButton("\u786E\u5B9A");
		btnRunGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRunGeneration.setFont(new Font("宋体", Font.PLAIN, 15));
		btnRunGeneration.setBounds(698, 440, 93, 23);
		
		add(btnRunGeneration);
		
		panelPaint.setBounds(1, 1, 822, 300);
		panelPaint.setLayout(null);
		add(panelPaint);
		panelPaint.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		//画图区1---------------------------------------------------------------------------------------------------
		outPutPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		outPutPanel_1.setBackground(Color.WHITE);
		outPutPanel_1.setBounds(0, 0, 410, 298);
		panelPaint.add(outPutPanel_1);
		mathCanvasA.setBounds(0, 0, 410, 310);
		outPutPanel_1.add(mathCanvasA);
		
		
		//画图区2--------------------------------------------------------------------------------------------------------------
		
		outputPanel_2.setBackground(Color.WHITE);
		outputPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputPanel_2.setBounds(413, 0, 407, 298);
		panelPaint.add(outputPanel_2);
		outputPanel_2.setLayout(null);
		mathCanvasB.setBounds(0, 0, 418, 310);
		outputPanel_2.add(mathCanvasB);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(1, 485, 820, 45);
		add(panel);
		panel.setLayout(null);
		btnNewButton.setBounds(335, 10, 57, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.outputPanel.setVisible(false);
				parent.panel_0.setVisible(true);
				parent.footPanel.setVisible(true);
				parent.treePanel.setVisible(true);
			}
		});
		panel.add(btnNewButton);
	}
	
	
	
	public void refresh(){
		//parent.inputFilePanel.setInputFile();
		
		GepConfigurationService gfs = new GepConfigurationService();
		GepAlgConfiguration g =  gfs.setGepAlgConfiguration(parent.gepAlgConfiguration, parent.inputSet);
		
		
		outputController.setParameter(parent.inputSet, g);
		
		
		
		outputController.drawFittingCurve(mathCanvasA);
		outputController.drawEvolutionGraph(mathCanvasB);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}