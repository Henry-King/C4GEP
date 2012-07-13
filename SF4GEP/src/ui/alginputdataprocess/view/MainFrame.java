package ui.alginputdataprocess.view;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import domain.core.algInputDataProcess.*;
import domain.core.algconfiguration.*;
import domain.iservice.algConfiguration.IgepConfigurationService;
import domain.service.algConfiguration.GepConfigurationService;

import ui.algconfiguration.controller.FunctionController;
import ui.algconfiguration.controller.ConfigController;
import ui.algconfiguration.model.ConfigModel;
import ui.algconfiguration.model.FunctionModel;
import ui.algconfiguration.model.GeneModel;
import ui.algconfiguration.model.PopulationModel;
import ui.algconfiguration.view.*;
import ui.alginputdataprocess.controller.*;
import ui.alginputdataprocess.model.*;
import ui.algoutput.controller.OutputController;
import ui.algoutput.controller.SaveConfigController;
import ui.algoutput.view.*;


public class MainFrame extends JFrame {
	
    JPanel contentPane;
    ConfigView configurationPanel = new ConfigView(this);
	public StopSettingView stopSettingPanel=new StopSettingView(this);
	PopulationView populationPanel=new PopulationView(this);
	GeneView genePanel=new GeneView(this);
	FunctionView functionPanel=new FunctionView(this);
	public FooterView footPanel=new FooterView(this);
	InputFileView inputFilePanel=new InputFileView(this);
	public SaveConfigView scfigNamePanel=new SaveConfigView(this);
	public OutputView outputPanel=new OutputView(this);
	//UploadInterfaceView uploadInterfacePanel=new UploadInterfaceView(this);
	
	public TreeView  treePanel;
	
    public JPanel panel_0 = new JPanel();
    JPanel[] panels={configurationPanel,stopSettingPanel,populationPanel,genePanel,functionPanel,inputFilePanel,outputPanel,panel_0,footPanel,treePanel};
    
    
    public CardLayout card;
    
    public MainController mainController = new MainController();
    public MainModel mainModel = new MainModel();
    
    OutputView outputView = new OutputView(this);
    
    
    public DataSet inputSet;
    public GepAlgConfiguration gepAlgConfiguration;
    public IgepConfigurationService gepConfigurationService = new GepConfigurationService();
   
/*GepConfiguration myParameter=new GepConfiguration();
	IgepAlgService myGepService=new GepAlgService();	
	GepConfiguration myConfigurationFromDB;
	List<GepConfiguration> configurationsOfHistory=myGepService.readArgumentsFromDb();
	
	IgepInputService input =new GepInputService();
*/
	
    public List<GepAlgConfiguration> configurationsOfHistory = gepConfigurationService.getAllGepAlgConfiguration();
	
    
    
	int flag=0;//是否读取配置文件
	int count=0;
	int jcount=1;//标记jcomboBoxConfiguration的editor事件还是ItemSelectedchange事件

	
	public MainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		

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
		
		   
		
	
		  
		  //算法名称面板-------------------------------------------------
		  
		 
		   String configurations[] = new String[configurationsOfHistory.size()];
		   for (int i = 0; i < configurationsOfHistory.size(); i++) {
				String configName = configurationsOfHistory.get(i).getName();
				configurations[i] = configName;
			}
			
		  
		  //选择输入路径面板-------------------------------------------------
		  stopSettingPanel.setBorder(null);
		  stopSettingPanel.setVisible(false);
		  panel_0.add(stopSettingPanel,"p2");
		  
		  //种群面板------------------------------------------------
		  
		  populationPanel.setBorder(null);
		  populationPanel.setVisible(false);
		 

			
		  panel_0.add(populationPanel,"p3");
		  //基因面板---------------------------------------------
		  genePanel.setBorder(null);
		  genePanel.setVisible(false);
		  panel_0.add(genePanel,"p4");
		  //函数面板----------------------------------
		 
		  functionPanel.setBorder(null);
		  functionPanel.setVisible(false);
		  /*try {
				for (int i = 0; i < myGepService.getAvailableFunctions().size(); i++) {
					functionPanel.comboBox.addItem(myGepService.getAvailableFunctions().get(i).toString());
					
				}
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (InstantiationException e1) {
			   
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}*/
		  
			
	      panel_0.add(functionPanel,"p5");
	      
	      //保存配置面板-------------------------------
	      
	      scfigNamePanel.setSize(659, 399);
	      scfigNamePanel.setVisible(false);
	      
	      contentPane.add(scfigNamePanel);
	      //----------------------------------------
	    
	      //底部按钮面板-----------------------------------
	      
	      footPanel.setBounds(165, 520, 659, 50);
	      contentPane.add(footPanel);
	      //------------------------------------------------
	      
	      
	      OutputController.init(outputPanel);
	      outputPanel.setBounds(5,115,822,530);
	      outputPanel.setVisible(false);
	      outputPanel.btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					outputPanel.setVisible(false);
					panel_0.setVisible(true);
					footPanel.setVisible(true);
					treePanel.setVisible(true);
				}
			});
	      contentPane.add(outputPanel);
	      //菜单面板---------------------------------------
	     
	      
	        treePanel=new TreeView(this);
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
