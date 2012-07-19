package ui.algoutput.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import ui.alginputdataprocess.view.*;
import ui.algoutput.controller.*;
import com.wolfram.jlink.*;
import domain.core.algconfiguration.*;
import domain.service.algConfiguration.*;


public class OutputView extends JPanel{
	
	public MathCanvas mathCanvasA = null;
	public MathCanvas mathCanvasB = null;
	//public MathCanvas outputCanvas = null;
	
	public OutputController outputController;

	MainFrame parent;
	public OutputView(final MainFrame parent) {
		
		outputController = new OutputController(parent);
		this.parent = parent;
		
		/**
		 * 初始化Kernel
		 */
		outputController.initKernel(this);
		
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize( 850, 686);
		
		///outputCanvas.setSize(850, 600);
		///add(outputCanvas);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setBounds(367, 489, 93, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.outputPanel.setVisible(false);
				parent.panel_0.setVisible(true);
				parent.footPanel.setVisible(true);
				parent.treePanel.setVisible(true);
			}
		});
		add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 795, 467);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(tabbedPane);
		
		tabbedPane.addTab("最佳个体的拟合曲线图", null,mathCanvasA,null);
		tabbedPane.addTab("每代最佳个体和最差个体的演化曲线图", null,mathCanvasB,null);
		
	}
	
	public void refresh(){
		GepConfigurationService gfs = new GepConfigurationService(parent.hibernateDataContext);
		GepAlgConfiguration g =  gfs.setGepAlgConfiguration(parent.gepAlgConfiguration, parent.inputSet);
		outputController.setParameter(parent.inputSet, g);
		outputController.drawFittingCurve(mathCanvasA);
		outputController.drawEvolutionGraph(mathCanvasB);
		//outputController.addCanvas(outputCanvas);
	}
}
