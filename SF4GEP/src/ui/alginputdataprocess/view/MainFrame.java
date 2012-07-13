package ui.alginputdataprocess.view;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import domain.core.algInputDataProcess.*;
import domain.core.algconfiguration.*;

import ui.algconfiguration.view.*;
import ui.alginputdataprocess.controller.*;
import ui.alginputdataprocess.model.*;
import ui.algoutput.view.*;


public class MainFrame extends JFrame {
	
    JPanel contentPane;
    HostView configurationPanel;
	StopSettingView stopSettingPanel=new StopSettingView(this);
	PopulationView populationPanel=new PopulationView(this);
	GeneView genePanel=new GeneView(this);
	FunctionView functionPanel=new FunctionView(this);
	FooterView footPanel=new FooterView(this);
	//UploadInterfaceView uploadInterfacePanel=new UploadInterfaceView(this);
	InputFileView inputFilePanel=new InputFileView(this);
	SaveConfigView scfigNamePanel=new SaveConfigView(this);
	OutputView outputPanel=new OutputView(this);
	
	
	TreeView  treePanel;
	
    JPanel panel_0 = new JPanel();
    
    
    
    CardLayout card;
    
    public MainController mainController = new MainController();
    public MainModel mainModel = new MainModel();
    
    OutputView outputView = new OutputView(this);
    
    
    public DataSet inputSet;
    public GepAlgConfiguration gepAlgConfiguration;
    
   
/*GepConfiguration myParameter=new GepConfiguration();
	IgepAlgService myGepService=new GepAlgService();	
	GepConfiguration myConfigurationFromDB;
	List<GepConfiguration> configurationsOfHistory=myGepService.readArgumentsFromDb();
	
	IgepInputService input =new GepInputService();
*/
	
	
	
	
	int flag=0;//�Ƿ��ȡ�����ļ�
	int count=0;
	int jcount=1;//���jcomboBoxConfiguration��editor�¼�����ItemSelectedchange�¼�
	
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
		
		   
		
	
		  
		  //�㷨�������-------------------------------------------------
		  
		 
		   String configurations[] = new String[configurationsOfHistory.size()];
		   for (int i = 0; i < configurationsOfHistory.size(); i++) {
				String configName = configurationsOfHistory.get(i).getName();
				configurations[i] = configName;
			}
			final JComboBox jcomboBoxConfiguration = new JComboBox(configurations);
			
			Component scrollBtn=jcomboBoxConfiguration.getComponent(0);
			configurationPanel=new HostPanel(jcomboBoxConfiguration);
			configurationPanel.setBorder(null);
			configurationPanel.setBounds(155, 115, 631, 447);
			configurationPanel.setVisible(true);
		    scrollBtn.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent arg0) {
		    	   jcount=1;
		       }
           });
		  configurationPanel.btnSetConfig.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					HostPanelController.btnSetConfigController(stopSettingPanel, populationPanel, genePanel, functionPanel, inputFilePanel);
					card.next(panel_0);
					TreePath visiblePath=new TreePath(((DefaultTreeModel)treePanel.tree_1.getModel()).getPathToRoot(treePanel.node1));
		    		treePanel.tree_1.setSelectionPath(visiblePath);
					jcount=2;
					footPanel.btnRun.setEnabled(false);
					count=1;
					flag=0;
				}
				
			});
		  jcomboBoxConfiguration.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){    
			    public void keyPressed(KeyEvent e) {
			    	HostPanelController.jcomboBoxEditorController(e,configurationPanel.btnSetConfig);
			        footPanel.btnRun.setEnabled(false);
			        HostPanelController.btnSetConfigController(stopSettingPanel, populationPanel, genePanel, functionPanel, inputFilePanel);
			        jcount=2;
			        flag=0;
			    }  
			    
			});
		  
		  jcomboBoxConfiguration.addItemListener(new  ItemListener(){ 
              public void  itemStateChanged(ItemEvent   ie){ 
            	if(jcount!=2){
            	 myConfigurationFromDB = configurationsOfHistory.get(jcomboBoxConfiguration.getSelectedIndex());
            	 System.out.println(myConfigurationFromDB.toString());
            	 flag=-1;
            	 setTitle(myConfigurationFromDB.toString());
                 footPanel.btnRun.setEnabled(true);
            	 HostPanelController.jcomboBoxItemController(ie,configurationPanel, stopSettingPanel, populationPanel, genePanel, functionPanel, footPanel,inputFilePanel ,myConfigurationFromDB, configurationsOfHistory, myGepService);
            	 jcount=1;
            	}
               }
             }
		  );
		
		  panel_0.add(configurationPanel,"p1");
		  
		  //ѡ������·�����-------------------------------------------------
		  stopSettingPanel.setBorder(null);
		  stopSettingPanel.setVisible(false);
		  panel_0.add(stopSettingPanel,"p2");
		  
		  //��Ⱥ���------------------------------------------------
		  
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
		  //�������---------------------------------------------
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
		  //�������----------------------------------
		 
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
					FunctionPanelController.functionComboBoxController(functionPanel.comboBox, functionPanel.JComboBoxOfSelectdFunctions);
				}	
			});
			
	      panel_0.add(functionPanel,"p5");
	      
	      //�����������-------------------------------
	      
	      scfigNamePanel.setSize(659, 399);
	      scfigNamePanel.setVisible(false);
	      scfigNamePanel.btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					SaveConfigPanelController.btnSaveController(myGepService, myParameter, outputPanel, scfigNamePanel, treePanel,panel_0);
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
	      //�ײ���ť���-----------------------------------
	      
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
		    		    ModelForJPanelInputPath inputPath=new ModelForJPanelInputPath(inputFilePanel.txtInputPath.getText(),stopSettingPanel.txtMaxGeneration.getText(),stopSettingPanel.txtAccuracy.getText());
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
						System.out.println("����"+result.toString());
					    FunctionModel function=new FunctionModel(result);
					    
					    /**
					     * ���ִ�м��㰴ť����
					     */
					    if(FooterController.btnRunController(config, inputPath, population, gene, function, myParameter, myGepService, myConfigurationFromDB, flag)==true){
					    	outputPanel.setVisible(true);//��ʾ������
					    	panel_0.setVisible(false);
		    				treePanel.setVisible(false);
		    				footPanel.setVisible(false);
		    				jcomboBoxConfiguration.setVisible(false);
		    		    }
		    		    else{
		    		    	
		    		    	JOptionPane.showMessageDialog(null, "�����ļ��Ѹ��������±���");
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
	      
	      
	      OutputPanelController.init(outputPanel);
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
	      //�˵����---------------------------------------
	     
	      
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
				          	if(node.toString()=="�㷨ϵͳ"){
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
				          		//footPanel��btnBefroe��btnNext
				          		btnBefore.setEnabled(false);
				          		btnNext.setEnabled(true);
				          		count=0;
				          	}
				          	if(node.toString()=="�㷨����"){
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
				          	if(node.toString()=="�㷨��ֹ����"){
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
				          	if(node.toString()=="��Ⱥ��Ϣ"){
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
				          	if(node.toString()=="������Ϣ"){
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
				          	if(node.toString()=="���躯���������"){
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
				          	if(node.toString()=="����ϵͳ"){
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
				          	if(node.toString()=="�ϴ�/���ؽӿ�"){
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
				          	if(node.toString()=="����·��"){
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
				          	if(node.toString()=="���ϵͳ"){
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
				          	if(node.toString()=="������"){
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
