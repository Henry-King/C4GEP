package ui.input.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ui.alg.Model.ModelForJPanelConfig;
import ui.alg.Model.ModelForJPanelFunction;
import ui.alg.Model.ModelForJPanelGEne;
import ui.alg.controller.FunctionPanelController;
import ui.alg.controller.HostPanelController;
import ui.alg.view.HostPanel;
import ui.alg.view.JPanelForFunction;
import ui.alg.view.JPanelForGene;
import ui.alg.view.JPanelForInputPathSetting;
import ui.alg.view.JPanelForPopulation;
import ui.alg.view.ModelForJPanelInputPath;
import ui.alg.view.ModelForJPanelPopulation;
import ui.input.controller.*;
import ui.input.model.ModelForUploadInterface;
import ui.output.view.JPanelForOutput;
import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;
import domain.service.alg.configuration.GepAlgService;
import domain.service.input.DefaultGepInput;
import domain.service.input.IgepInput;
import exception.Duplicated;




public class New2 extends JFrame {

    JPanel contentPane;
    HostPanel configurationPanel;
	JPanelForInputPathSetting inputPathPanel=new JPanelForInputPathSetting();
	JPanelForPopulation populationPanel=new JPanelForPopulation();
	JPanelForGene genePanel=new JPanelForGene();
	JPanelForFunction functionPanel=new JPanelForFunction();
	JPanelForFooter footPanel=new JPanelForFooter();
	JPanelForUploadInterface uploadInterfacePanel=new JPanelForUploadInterface();
	JPanelForJTree  treePanel;
	JPanelForSaveConfig scfigNamePanel;
	JPanelForOutput outputPanel;
	CardLayout card;
	
   
	GepConfiguration myParameter=new GepConfiguration();
	IgepAlgService myGepService=new GepAlgService();	
	GepConfiguration myConfigurationFromDB;
	List<GepConfiguration> configurationsOfHistory=myGepService.readArgumentsFromDb();
	IgepInput input =new DefaultGepInput();
	int flag=0;//是否读取配置文件
	int count=0;//标记第几个panel
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New2 frame = new New2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public New2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		//javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 686);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JPanel panel_0 = new JPanel();
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
			final JComboBox jcomboBoxConfiguration = new JComboBox(configurations);
			
			Component scrollBtn=jcomboBoxConfiguration.getComponent(0);
			configurationPanel=new HostPanel(jcomboBoxConfiguration);
			configurationPanel.setBorder(null);
			configurationPanel.setBounds(155, 115, 631, 447);
			configurationPanel.setVisible(true);
		    scrollBtn.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent arg0) {
		    	   HostPanelController.scrollBtnController();
		       }
           });
		  configurationPanel.btnSetConfig.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					card.next(panel_0);
					TreePath visiblePath=new TreePath(((DefaultTreeModel)treePanel.tree_1.getModel()).getPathToRoot(treePanel.node1));
		    		treePanel.tree_1.setSelectionPath(visiblePath);
					count=2;
					footPanel.btnRun.setEnabled(false);
				}
				
			});
		  jcomboBoxConfiguration.addItemListener(new  ItemListener(){ 
              public void  itemStateChanged(ItemEvent   ie)   { 
            	 myConfigurationFromDB = configurationsOfHistory.get(jcomboBoxConfiguration.getSelectedIndex());
            	 System.out.println(myConfigurationFromDB.toString());
            	 flag=-1;
            	 setTitle(myConfigurationFromDB.toString());
                 footPanel.btnRun.setEnabled(true);
            	 
                HostPanelController.jcomboBoxItemController(ie,configurationPanel, inputPathPanel, populationPanel, genePanel, functionPanel, footPanel, myConfigurationFromDB, configurationsOfHistory, myGepService);
                
               }
             }
		  );
		 jcomboBoxConfiguration.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){    
			    public void keyPressed(KeyEvent e) {
			    	HostPanelController.jcomboBoxEditorController(e,configurationPanel.btnSetConfig,configurationPanel.btnRunButton,configurationPanel.btnChangeConfig);
			        footPanel.btnRun.setEnabled(true);
			    }  
			    
			});
		  configurationPanel.btnChangeConfig.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		flag=-1;
	    		myConfigurationFromDB = configurationsOfHistory.get(jcomboBoxConfiguration.getSelectedIndex());
	    		setTitle(myConfigurationFromDB.toString());
	    		HostPanelController.handler(configurationPanel, inputPathPanel, populationPanel, genePanel, functionPanel, myConfigurationFromDB, configurationsOfHistory, myGepService);
	    		footPanel.btnRun.setEnabled(true);
	    	}
	      });
		  panel_0.add(configurationPanel,"p1");
		  
		  //选择输入路径面板-------------------------------------------------
		  inputPathPanel.setBorder(null);
		  inputPathPanel.setVisible(false);
		  panel_0.add(inputPathPanel,"p2");
		  
		  //种群面板------------------------------------------------
		  
		  populationPanel.setBorder(null);
		  populationPanel.setVisible(false);
		  try {
				for (int i = 0; i < myGepService.getAvailableSelector().size(); i++) {
					populationPanel.JComboBoxofSelectionStrategy.addItem(myGepService
							.getAvailableSelector().get(i).toString());
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
					genePanel.JComboBoxOfAvailableModifyings.addItem(myGepService
							.getAvailableModifyings().get(i).toString());
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
					FunctionPanelController.functionComboBoxController(functionPanel.comboBox, functionPanel.JComboBoxOfSelectdFunctions);
				}	
			});
			
	      panel_0.add(functionPanel,"p5");
	      
	      //保存配置面板-------------------------------
	      scfigNamePanel=new JPanelForSaveConfig();
	      scfigNamePanel.setSize(659, 399);
	      scfigNamePanel.setVisible(false);
	      scfigNamePanel.btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					SaveConfigPanelController.btnSaveController(myGepService, myParameter, outputPanel, scfigNamePanel, treePanel,panel_0);
				}
			});
	      contentPane.add(scfigNamePanel);
	      //----------------------------------------
	     
	      uploadInterfacePanel.setVisible(false);
	      
	      
	      panel_0.add(uploadInterfacePanel,"p6");
	      //底部按钮面板-----------------------------------
	      
	      footPanel.setBounds(165, 520, 659, 50);
	      footPanel.btnBefore.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		count=FooterPanelController.btnBeforeController(treePanel.Node,treePanel.tree_1,footPanel.btnNext,footPanel.btnBefore,panel_0,card,count);
		    	}
		      });
	      footPanel.btnRun.setEnabled(false);
	      footPanel.btnRun.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		   
		    		    ModelForJPanelConfig config=new ModelForJPanelConfig(jcomboBoxConfiguration.getSelectedItem().toString());
		    		    ModelForJPanelInputPath inputPath=new ModelForJPanelInputPath(inputPathPanel.txtInputPath.getText(),inputPathPanel.txtMaxGeneration.getText(),inputPathPanel.txtAccuracy.getText());
		    		    int creatorSelectedIndex = populationPanel.JcomboBoxOfPopulationCreator.getSelectedIndex();
		    		    System.out.print("ww"+creatorSelectedIndex);
						int calculatorSelectedIndex = populationPanel.JComboBoxAvailableCalculator.getSelectedIndex();
						int strategySelectedIndex =populationPanel.JComboBoxofSelectionStrategy.getSelectedIndex();;
					    ModelForJPanelPopulation population=new ModelForJPanelPopulation(populationPanel.txtPopulationSize.getText(), populationPanel.txtSelectionRange.getText(), creatorSelectedIndex, strategySelectedIndex,calculatorSelectedIndex);
					   //--------
					    int modifyingSelectedIndex = genePanel.JComboBoxOfAvailableModifyings.getSelectedIndex();
					    ModelForJPanelGEne gene=new ModelForJPanelGEne(genePanel.txtNormalGeneNumber.getText(), genePanel.txtNormalHeaderLength.getText(), genePanel.txtHomeoticGeneNums.getText(),genePanel.txtHomeoticHeaderLength.getText(), genePanel.txtGeneRecombineRate.getText(), genePanel.txtRisTransportRate.getText(), genePanel.txtofRisElement.getText().trim(),genePanel.txtIsTransportRate.getText() ,genePanel.txtofRisElement.getText().trim(),genePanel.txtGeneTransportRate.getText() ,genePanel.txtMutateRate.getText(),genePanel.txtTwoPointRecombineRate.getText(),genePanel.txtGeneOnePointRecombineRate.getText(),modifyingSelectedIndex);
		    		    //----
					    StringBuffer result = new StringBuffer();
						String str = new String();
						for (int i = 0; i < functionPanel.JComboBoxOfSelectdFunctions.getItemCount(); i++) {
							if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i).toString()
									.equals("+")) {
								str = "domain.service.alg.userdefined.function.Additioin";

							} else if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i)
									.toString().equals("-")) {
								str = "domain.service.alg.userdefined.function.Minus";

							} else if (functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i)
									.toString().equals("*")) {
								str = "domain.service.alg.userdefined.function.Multiply";

							} else {
								str = "domain.service.alg.userdefined.function.Divide";

							}

							result.append(str + ",");
						}
					    ModelForJPanelFunction function=new ModelForJPanelFunction(result);
					    
					    
					    /**
					     * 点击执行计算按钮触发
					     */
					    if(FooterPanelController.btnRunController(config, inputPath, population, gene, function, myParameter, myGepService, myConfigurationFromDB, flag)==true){
					    	OutputPanelController.drawPicture(myGepService, outputPanel);
					    	
					    	outputPanel.setVisible(true);//显示输出面板
					    	panel_0.setVisible(false);
		    				treePanel.setVisible(false);
		    				footPanel.setVisible(false);
		    				jcomboBoxConfiguration.setVisible(false);
		    		    }
		    		    else{
		    		    	
		    		    	JOptionPane.showMessageDialog(null, "配置文件已更改请重新保存");
		    		    	OutputPanelController.drawPicture(myGepService, outputPanel);
     	    		    	panel_0.setVisible(false);
							jcomboBoxConfiguration.setVisible(false);
							footPanel.setVisible(false);
							scfigNamePanel.setVisible(true);
							
		    		    }
		    	}
		      });
	      footPanel.btnNext.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		count=FooterPanelController.btnNextController(treePanel.Node,treePanel.tree_1,footPanel,panel_0,card,count);
		    	}
		      });

	      contentPane.add(footPanel);
	      //------------------------------------------------
	      
	      outputPanel=new JPanelForOutput();
	      OutputPanelController.init(outputPanel);
	      outputPanel.setBounds(5,115,822,530);
	      outputPanel.setVisible(false);
	      contentPane.add(outputPanel);
	      //菜单面板---------------------------------------
	      final JPanel[] panels={configurationPanel,inputPathPanel,populationPanel,genePanel,functionPanel,uploadInterfacePanel};
	      treePanel=new JPanelForJTree();
			treePanel.setBounds(5, 115, 155, 455);
			treePanel.tree_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					TreePanelController.treeMouseListener(e, panels);
				}
			});
		    contentPane.add(treePanel);
	      
	}
}
