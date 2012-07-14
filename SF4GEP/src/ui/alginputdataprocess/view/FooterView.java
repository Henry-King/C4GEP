package ui.alginputdataprocess.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import domain.core.algconfiguration.GepAlgConfiguration;

import jxl.read.biff.BiffException;
import exception.Duplicated;

import ui.algconfiguration.model.ConfigModel;
import ui.algconfiguration.model.FunctionModel;
import ui.algconfiguration.model.GeneModel;
import ui.algconfiguration.model.PopulationModel;

public class FooterView extends JPanel {
	
	
	public JButton btnNext = new JButton("下一步 >");
    public JButton btnBefore = new JButton("< 上一步");
    public JButton btnRun = new JButton("\u6267\u884C\u7B97\u6CD5");
    
    MainFrame parent;

    GepAlgConfiguration gepAlgConfiguration;
    
	public FooterView(final MainFrame parent) {
		  this.parent = parent;
		  gepAlgConfiguration = parent.gepAlgConfiguration;
	      setBorder(new LineBorder(new Color(0, 0, 0)));
	      setBackground(Color.WHITE);
	      setBounds(160, 523, 669, 39);
	      setLayout(null);
	      btnNext.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		//btnNextController(DefaultMutableTreeNode[] node,JTree tree_1,JPanelForFooter footPanel,JPanel panel_0,CardLayout card,int count)
	      		//count用于标记cardlayout跳到第几个面板
	      		btnBefore.setEnabled(true);
	      		parent.card.next(parent.panel_0);
	      		switch(parent.count){
	      		  case 0:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node1));
	        		parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		break; 
	      		  }
	      		  case 1:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node2));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		break;
	      		  }
	      		  case 2:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node3));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		
	        		break;
	      		  }
	      		  case 3:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node4));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        	   
	        		break;
	      		  }
	      		 default:{
	      			break;
	      			
	      		  }
	      		}
	      		if(parent.count==3){
	      			parent.footPanel.btnNext.setEnabled(false);
	      			parent.footPanel.btnRun.setEnabled(true);
	      		}
	      		if(parent.count==4){
	      			return;
	      		}
	      		parent.count++;
	      		
	      		
	      	}
	      });
	      	      
	      btnNext.setBounds(523, 10, 93, 23);
	      add(btnNext);
	      btnBefore.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		//btnBeforeController(DefaultMutableTreeNode node[],JTree tree_1,JButton  btnNext,JButton btnBefore,JPanel panel_0,CardLayout card,int count)
	      		parent.count--;
	      		if(parent.count==0){
	      			btnBefore.setEnabled(false);
	      		}
	      			btnNext.setEnabled(true);
	      		
	      	
	      		switch(parent.count){
	      		  case 0:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node0));
	        		parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		break;
	      		  }
	      		  case 1:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node1));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		break;
	      		  }
	      		  case 2:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node2));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		
	        		break;
	      		  }
	      		  case 3:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node3));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		
	        		break;
	      		  }
	      		 default:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)parent.treePanel.tree_1.getModel()).getPathToRoot(parent.treePanel.node4));
	      			parent.treePanel.tree_1.setSelectionPath(visiblePath);
	        		
	        		break;
	      		  }
	      		}
	      		parent.card.previous(parent.panel_0);
	      		
	      	}
	      });
	      
	      
	      btnBefore.setEnabled(false);
	      
	      btnBefore.setBounds(105, 10, 93, 23);
	      add(btnBefore);
	      btnRun.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		
	      		
	      		//运行程序
	      		/*btnRunController(ModelForJPanelConfig configuration,ModelForJPanelInputPath inputPath,
	      			   ModelForJPanelPopulation population,ModelForJPanelGEne gene,ModelForJPanelFunction function,
	      			   GepConfiguration myParameter,IgepAlgService myGepService,GepConfiguration myConfigurationFromDB,int flag)*/
	      		
	      		/*myParameter.setInputFile(inputPath.getInputPath());
	    		myParameter.setName(configuration.getConfig());
	    		myParameter.setAccuray(inputPath.getAccuracy());
	    		myParameter.setNormalGeneNumber(gene.getNormalGeneNumber());
	    		myParameter.setGeneRecombineRate(gene.getGeneRecombineRate());
	    		myParameter.setGeneTransportRate(gene.getGeneTransportRate());
	    		myParameter.setNormalHeaderLength(gene.getNormalHeaderLength());
	    		myParameter.setIsTransportRate(gene.getIsTransportRate());
	    		myParameter.setMaxGeneration(inputPath.getMaxgeneration());
	    		myParameter.setOnePointRecombineRate(gene.getGeneOnePointRecombineRate());
	    		myParameter.setPopulationSize(population.getPopulationSize());
	    		myParameter.setRisTransportRate(gene.getRisTransportRate());
	    		myParameter.setSelectionRange(population.getSelectionRange());
	    		myParameter.setTwoPointRecombineRate(gene.getTwoPointRecombineRate());
	    		myParameter.setIsElement(gene.getIsElement());
	            myParameter.setRisElement(gene.getRisElement());
	    		myParameter.setHomeoticGeneNumber(gene.getHomeoticGeneNums());
	    	    myParameter.setHomeoticHeaderLength(gene.getHomeoticHeaderLength());
	    		myParameter.setFunctionList(function.getFunctions().toString());
	    		myParameter.setMutateRate(gene.getMutateRate());*/

	      		
	      		
	      		
	    				
	    	   /* try {
	    	    	System.out.println(myGepService.getAvailableSelector().get(population.getSelectionStrategyIndex()).getClass().getName());
	    			myParameter.setSelector(myGepService.getAvailableSelector().get(population.getSelectionStrategyIndex()).getClass().getName());
	    			myParameter.setCalculator(myGepService.getAvailableCalculator().get(population.getAvailableCalculatorIndex()).getClass().getName());
	    			myParameter.setCreator(myGepService.getAvailableCreator().get(population.getPopulationCreatorIndex()).getClass().getName());
	    			myParameter.setModify(myGepService.getAvailableModifyings().get(gene.getAvailableModifyingsIndex()).getClass().getName());
	    			
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
*/
	    			
	    			
	    		    
	    		/*try {
	    			
	    			myGepService.setParameters(myParameter);
	    			
	    		} catch (BiffException e3) {
	    			// TODO Auto-generated catch block
	    			e3.printStackTrace();
	    		} catch (InstantiationException e3) {
	    			// TODO Auto-generated catch block
	    			e3.printStackTrace();
	    		} catch (IllegalAccessException e3) {
	    			// TODO Auto-generated catch block
	    			e3.printStackTrace();
	    		} catch (ClassNotFoundException e3) {
	    			// TODO Auto-generated catch block
	    			e3.printStackTrace();
	    		} catch (IOException e3) {
	    			// TODO Auto-generated catch block
	    			e3.printStackTrace();
	    		}*/
	    		
	    		
	    		
	           //myGepService.run();
	           parent.outputPanel.refresh();	//开始画图，相当于run
	      		
	      		
	           /**
	            * 自己改
	            * = =
	            */
	           
	           if (parent.flag == -1) {
	        	   		
	    				/*if (myParameter.equals(myConfigurationFromDB) == false) {
	    					return true;
	    					}
	    				else{
	    					return false;
	    				}
	    				*/
	        	   		
	    		} 
	    		else {
	    			
	    			/*
	    				try {
	    					myGepService.saveArgumentsToDb(myParameter);
	    				} catch (Duplicated e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				return true;
	    				*/
	    		}
	           
	           
	           
	      	}
	      });
	      
	      
	      btnRun.setBounds(312, 10, 93, 23);
	      add(btnRun);
	}

}
