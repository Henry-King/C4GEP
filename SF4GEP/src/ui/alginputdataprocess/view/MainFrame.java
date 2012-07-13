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
	StopSettingView stopSettingPanel=new StopSettingView(this);
	PopulationView populationPanel=new PopulationView(this);
	GeneView genePanel=new GeneView(this);
	FunctionView functionPanel=new FunctionView(this);
	FooterView footPanel=new FooterView(this);
	InputFileView inputFilePanel=new InputFileView(this);
	SaveConfigView scfigNamePanel=new SaveConfigView(this);
	OutputView outputPanel=new OutputView(this);
	//UploadInterfaceView uploadInterfacePanel=new UploadInterfaceView(this);
	
	TreeView  treePanel;
	
    JPanel panel_0 = new JPanel();
    
    
    
    CardLayout card;
    
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
		  try {
				for (int i = 0; i < myGepService.getAvailableSelector().size(); i++) {
					populationPanel.JComboBoxofSelectionStrategy.addItem(myGepService.getAvailableSelector().get(i).toString());
				}
				for (int i = 0; i < myGepService.getAvailableCalculator().size(); i++) {
	                 populationPanel.JComboBoxAvailableCalculator.addItem(myGepService.getAvailableCalculator().get(i).toString());
				}
				for (int i = 0; i < myGepService.getAvailableCreator().size(); i++) {
					populationPanel.JcomboBoxOfPopulationCreator.addItem(myGepService.getAvailableCreator().get(i).toString());
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		  panel_0.add(populationPanel,"p3");
		  //基因面板---------------------------------------------
		  genePanel.setBorder(null);
		  genePanel.setVisible(false);
		  try {
				for (int i = 0; i < myGepService.getAvailableModifyings().size(); i++) {
					genePanel.JComboBoxOfAvailableModifyings.addItem(myGepService.getAvailableModifyings().get(i).toString());
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		  panel_0.add(genePanel,"p4");
		  //函数面板----------------------------------
		 
		  functionPanel.setBorder(null);
		  functionPanel.setVisible(false);
		  try {
				for (int i = 0; i < myGepService.getAvailableFunctions().size(); i++) {
					functionPanel.comboBox.addItem(myGepService.getAvailableFunctions().get(i).toString());
					
				}
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (InstantiationException e1) {
			   
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		  functionPanel.comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FunctionController.functionComboBoxController(functionPanel.comboBox, functionPanel.JComboBoxOfSelectdFunctions);
				}	
			});
			
	      panel_0.add(functionPanel,"p5");
	      
	      //保存配置面板-------------------------------
	      
	      scfigNamePanel.setSize(659, 399);
	      scfigNamePanel.setVisible(false);
	      scfigNamePanel.btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					SaveConfigController.btnSaveController(myGepService, myParameter, outputPanel, scfigNamePanel, treePanel,panel_0);
				}
			});
	      contentPane.add(scfigNamePanel);
	      //----------------------------------------
	      uploadInterfacePanel.setBounds(165, 115, 659, 450);
	      uploadInterfacePanel.setVisible(false);
	      contentPane.add(uploadInterfacePanel);
	      inputFilePanel.setBounds(165, 115, 659, 455);
	      inputFilePanel.setVisible(false);
	      contentPane.add(inputFilePanel);
	      //底部按钮面板-----------------------------------
	      
	      footPanel.setBounds(165, 520, 659, 50);
	      footPanel.btnBefore.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		count=FooterController.btnBeforeController(treePanel.Node,treePanel.tree_1,footPanel.btnNext,footPanel.btnBefore,panel_0,card,count);
		    	}
		      });
	      footPanel.btnRun.setEnabled(false);
	      
	      footPanel.btnRun.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		   
		    		    ConfigModel config=new ConfigModel(jcomboBoxConfiguration.getSelectedItem().toString());
		    		    InputPathModel inputPath=new InputPathModel(inputFilePanel.txtInputPath.getText(),stopSettingPanel.txtMaxGeneration.getText(),stopSettingPanel.txtAccuracy.getText());
		    		    int creatorSelectedIndex = populationPanel.JcomboBoxOfPopulationCreator.getSelectedIndex();
		    		    System.out.print("ww"+creatorSelectedIndex);
						int calculatorSelectedIndex = populationPanel.JComboBoxAvailableCalculator.getSelectedIndex();
						int strategySelectedIndex =populationPanel.JComboBoxofSelectionStrategy.getSelectedIndex();;
					    PopulationModel population=new PopulationModel(populationPanel.txtPopulationSize.getText(), populationPanel.txtSelectionRange.getText(), creatorSelectedIndex, strategySelectedIndex,calculatorSelectedIndex);
					   //--------
					    int modifyingSelectedIndex = genePanel.JComboBoxOfAvailableModifyings.getSelectedIndex();
					    GeneModel gene=new GeneModel(genePanel.txtNormalGeneNumber.getText(), genePanel.txtNormalHeaderLength.getText(), genePanel.txtHomeoticGeneNums.getText(),genePanel.txtHomeoticHeaderLength.getText(), genePanel.txtGeneRecombineRate.getText(), genePanel.txtRisTransportRate.getText(), genePanel.txtofRisElement.getText().trim(),genePanel.txtIsTransportRate.getText() ,genePanel.txtofRisElement.getText().trim(),genePanel.txtGeneTransportRate.getText() ,genePanel.txtMutateRate.getText(),genePanel.txtTwoPointRecombineRate.getText(),genePanel.txtGeneOnePointRecombineRate.getText(),modifyingSelectedIndex);
		    		    //----
					    StringBuffer result = new StringBuffer();
						String str = new String();
						
						for (int i = 0; i < functionPanel.JComboBoxOfSelectdFunctions.getItemCount(); i++) {
							if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i).toString()
									.equals("+")) {
								str = "domain.service.alg.userdefined.Additioin";

							} else if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i)
									.toString().equals("-")) {
								str = "domain.service.alg.userdefined.Minus";

							} else if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i)
									.toString().equals("*")) {
								str = "domain.service.alg.userdefined.Multiply";

							} else {
								str = "domain.service.alg.userdefined.Divide";

							}

							result.append(str + ",");
						}
						System.out.println("函数"+result.toString());
					    FunctionModel function=new FunctionModel(result);
					    
					    /**
					     * 点击执行计算按钮触发
					     */
					    if(FooterController.btnRunController(config, inputPath, population, gene, function, myParameter, myGepService, myConfigurationFromDB, flag)==true){
					    	outputPanel.setVisible(true);//显示输出面板
					    	panel_0.setVisible(false);
		    				treePanel.setVisible(false);
		    				footPanel.setVisible(false);
		    				jcomboBoxConfiguration.setVisible(false);
		    		    }
		    		    else{
		    		    	
		    		    	JOptionPane.showMessageDialog(null, "配置文件已更改请重新保存");
     	    		    	panel_0.setVisible(false);
							jcomboBoxConfiguration.setVisible(false);
							footPanel.setVisible(false);
							scfigNamePanel.setVisible(true);
		    		    }
					    
					    OutputPanelController.drawPicture(myGepService, outputPanel);
					    
					    jcomboBoxConfiguration.setVisible(true);
					    
					    
					    
		    	}
		      });
	      
	      
	      footPanel.btnNext.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		count=FooterController.btnNextController(treePanel.Node,treePanel.tree_1,footPanel,panel_0,card,count);
		    	}
		      });
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
	     
	      
	        treePanel=new TreeView();
			treePanel.setBounds(5, 115, 155, 455);
			final JPanel[] panels={configurationPanel,stopSettingPanel,populationPanel,genePanel,functionPanel,uploadInterfacePanel,inputFilePanel,outputPanel,panel_0,footPanel,treePanel};
			treePanel.tree_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					 int selRow = tree.getRowForLocation(e.getX(), e.getY());
				      TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				      if (selRow != -1)
				      {
				          if (e.getClickCount() == 1)
				          {
				          	TreeNode node = (TreeNode) selPath.getLastPathComponent();
				          	if(node.toString()=="算法系统"){
				          		panels[0].setVisible(true);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		//footPanel的btnBefroe和btnNext
				          		btnBefore.setEnabled(false);
				          		btnNext.setEnabled(true);
				          		count=0;
				          	}
				          	if(node.toString()=="算法名称"){
				          		panels[0].setVisible(true);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		btnBefore.setEnabled(true);
				          		btnNext.setEnabled(true);
				          		count=0;
				          	}
				          	if(node.toString()=="算法终止条件"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(true);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		btnBefore.setEnabled(true);
				          		btnNext.setEnabled(true);
				          		count=1;
				          	}
				          	if(node.toString()=="种群信息"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(true);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		btnBefore.setEnabled(true);
				          		btnNext.setEnabled(true);
				          		count=2;
				          	}
				          	if(node.toString()=="基因信息"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(true);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		btnBefore.setEnabled(true);
				          		btnNext.setEnabled(true);
				          		count=3;
				          	}
				          	if(node.toString()=="所需函数和随机数"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(true);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(true);
				          		panels[9].setVisible(true);
				          		btnBefore.setEnabled(true);
				          		btnNext.setEnabled(false);
				          		count=4;
				          	}
				          	if(node.toString()=="输入系统"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(true);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(false);
				          		panels[9].setVisible(false);
				          	} 
				          	if(node.toString()=="上传/下载接口"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(true);
				          		panels[6].setVisible(false);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(false);
				          		panels[9].setVisible(false);
				          	} 
				          	if(node.toString()=="输入路径"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(true);
				          		panels[7].setVisible(false);
				          		panels[8].setVisible(false);
				          		panels[9].setVisible(false);
				          	}
				          	if(node.toString()=="输出系统"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[8].setVisible(false);
				          	    panels[9].setVisible(false);
				          		panels[7].setVisible(true);
				          		panels[10].setVisible(false);
				          	}  
				          	if(node.toString()=="输出结果"){
				          		panels[0].setVisible(false);
				          		panels[1].setVisible(false);
				          		panels[2].setVisible(false);
				          		panels[3].setVisible(false);
				          		panels[4].setVisible(false);
				          		panels[5].setVisible(false);
				          		panels[6].setVisible(false);
				          		panels[8].setVisible(false);
				          	    panels[9].setVisible(false);
				          		panels[7].setVisible(true);
				          		panels[10].setVisible(false);
				          	}  
				          } 
				      }
					
				}
			});
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
