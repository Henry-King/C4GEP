package ui.alginputdataprocess.view;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;


import domain.core.algInputDataProcess.*;
import domain.core.algconfiguration.*;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.service.algConfiguration.GepConfigurationService;


import ui.algconfiguration.view.*;
import ui.alginputdataprocess.controller.*;
import ui.alginputdataprocess.model.*;
import ui.algoutput.view.*;


public class MainFrame extends JFrame {
	
    public DataSet inputSet;
    public GepAlgConfiguration gepAlgConfiguration;
	public IgepConfigurationService gepConfigurationService = new GepConfigurationService();
	public File inputFile;
	public String cfgname;
	public List<GepAlgConfiguration> configurationsOfHistory = gepConfigurationService.getAllGepAlgConfiguration();    
    JPanel contentPane;
    public ConfigView configurationPanel = new ConfigView(this);
	public StopSettingView stopSettingPanel=new StopSettingView(this);
	public PopulationView populationPanel=new PopulationView(this);
	public GeneView genePanel=new GeneView(this);
	public FunctionView functionPanel=new FunctionView(this);
	public FooterView footPanel=new FooterView(this);
	public InputFileView inputFilePanel=new InputFileView(this);
	public SaveConfigView scfigNamePanel=new SaveConfigView(this);
	public OutputView outputPanel=new OutputView(this);
	public TreeView  treePanel=new TreeView(this);;
    public JPanel panel_0 = new JPanel();
    JPanel[] panels={configurationPanel,stopSettingPanel,populationPanel,genePanel,functionPanel,inputFilePanel,outputPanel,panel_0,footPanel,treePanel};
    
    public CardLayout card;
    
    public MainController mainController = new MainController();
    public MainModel mainModel = new MainModel();
    
   
    
  
	
    
	
    
    
	public int flag=0;//�Ƿ��ȡ�����ļ�
	public int count=0;//��Ƕ����ڼ������
	int jcount=1;//���jcomboBoxConfiguration��editor�¼�����ItemSelectedchange�¼�

	
	public MainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		configurationsOfHistory.toString();
		
		//this.configurationPanel.configController.fillConfiguration(configurationsOfHistory);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 686);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel_0.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_0.setBackground(Color.WHITE);
		panel_0.setBounds(165, 115, 659, 399);
	    card = new CardLayout(5, 5);
		panel_0.setLayout(card);
		contentPane.add(panel_0);
		
		   
		
	
		  
		  //�㷨�������-------------------------------------------------
		  configurationPanel.setBorder(null);
		  configurationPanel.setVisible(false);
		  panel_0.add(configurationPanel,"p1");
		 

		   
			
		  
		  //ѡ������·�����-------------------------------------------------
		  stopSettingPanel.setBorder(null);
		  stopSettingPanel.setVisible(false);
		  panel_0.add(stopSettingPanel,"p2");
		  
		  //��Ⱥ���------------------------------------------------
		  
		  populationPanel.setBorder(null);
		  populationPanel.setVisible(false);
		  panel_0.add(populationPanel,"p3");
		  //�������---------------------------------------------
		  genePanel.setBorder(null);
		  genePanel.setVisible(false);
		  panel_0.add(genePanel,"p4");
		  //�������----------------------------------
		 
		  functionPanel.setBorder(null);
		  functionPanel.setVisible(false);
		 
		  
			
	      panel_0.add(functionPanel,"p5");
	      
	      //�����������-------------------------------
	      
	      scfigNamePanel.setSize(659, 399);
	      scfigNamePanel.setVisible(false);
	      
	      contentPane.add(scfigNamePanel);
	      //----------------------------------------
	    
	      //�ײ���ť���-----------------------------------
	      
	      footPanel.setBounds(165, 520, 659, 50);
	      contentPane.add(footPanel);
          //�ײ���ť���-----------------------------------
	      
	      
	      inputFilePanel.setVisible(false);
	      contentPane.add(inputFilePanel);
	      //������-----------------------------------------------
	      outputPanel.setBounds(5,115,822,530);
	      outputPanel.setVisible(false);
	      contentPane.add(outputPanel);
	      //�˵����---------------------------------------
	     
	      
	       
			treePanel.setBounds(5, 115, 155, 455);
		    contentPane.add(treePanel);
		    
	}
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	
	
	
	
	
}
