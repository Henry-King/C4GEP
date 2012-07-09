package ui.input.controller;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ui.alg.Model.ModelForJPanelConfig;
import ui.alg.Model.ModelForJPanelFunction;
import ui.alg.Model.ModelForJPanelGEne;
import ui.alg.Model.ModelForJPanelPopulation;
import ui.alg.view.HostPanel;
import ui.alg.view.JPanelForFunction;
import ui.alg.view.JPanelForGene;
import ui.alg.view.JPanelForStopSetting;
import ui.alg.view.JPanelForPopulation;
import ui.input.model.ModelForJPanelInputPath;
import ui.input.view.JPanelForFooter;

import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;
import domain.service.alg.baseclass.GepAlgService;
import domain.service.input.DefaultGepInput;
import domain.service.input.IgepInput;

import jxl.read.biff.BiffException;
import exception.Duplicated;


public class FooterPanelController {
	
    
	 public static int btnNextController(DefaultMutableTreeNode[] node,JTree tree_1,JPanelForFooter footPanel,JPanel panel_0,CardLayout card,int count) {
		footPanel.btnBefore.setEnabled(true);
  		card.next(panel_0);
  		switch(count){
  		  case 0:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[1]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第-个Node"+node[1].toString());
    		break; 
  		  }
  		  case 1:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[2]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第二个Node"+node[2].toString());
    		break;
  		  }
  		  case 2:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第三个Node"+node[3].toString());
    		break;
  		  }
  		  case 3:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[4]));
    		tree_1.setSelectionPath(visiblePath);
    	    System.out.println("第四个Node"+node[4].toString());
    		break;
  		  }
  		 default:{
  			break;
  			
  		  }
  		}
  		if(count==3){
  			footPanel.btnNext.setEnabled(false);
  			footPanel.btnRun.setEnabled(true);
  		}
  		if(count==4){
  			return count;
  		}
  		count++;
  		System.out.println(count);
  		return count;
	}
    public static int btnBeforeController(DefaultMutableTreeNode node[],JTree tree_1,JButton  btnNext,JButton btnBefore,JPanel panel_0,CardLayout card,int count) {
    	count--;
  		if(count==0){
  			btnBefore.setEnabled(false);
  		}
  			btnNext.setEnabled(true);
  		
  		System.out.println(count);
  		switch(count){
  		  case 0:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[0]));
    		tree_1.setSelectionPath(visiblePath);
    	    System.out.println("第0个Node"+node[0].toString());
    		break;
  		  }
  		  case 1:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[1]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第1个Node"+node[1].toString());
    		break;
  		  }
  		  case 2:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[2]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第2个Node"+node[2].toString());
    		break;
  		  }
  		  case 3:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第3个Node"+node[3].toString());
    		break;
  		  }
  		 default:{
  			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
    		tree_1.setSelectionPath(visiblePath);
    		System.out.println("第3个Node"+node[3].toString());
    		break;
  		  }
  		}
  		card.previous(panel_0);
  		return count;
		
	}
	public static boolean btnRunController(ModelForJPanelConfig configuration,ModelForJPanelInputPath inputPath,
	   ModelForJPanelPopulation population,ModelForJPanelGEne gene,ModelForJPanelFunction function,
	   GepConfiguration myParameter,IgepAlgService myGepService,GepConfiguration myConfigurationFromDB,int flag){
		String risElement = gene.getRisElement();
		String strRisElement = "";
		for (int i = 0; i < risElement.length(); i++) {
				if (i != 0) {
					if (risElement.charAt(i) != ','
							&& (risElement.substring(0, i).indexOf(
									risElement.charAt(i)) < 0)) {
						strRisElement = strRisElement
								+ risElement.charAt(i);
					}
				} else {
					if (risElement.charAt(i) != ',') {
						strRisElement = strRisElement
								+ risElement.charAt(i);
					}
				}
			}
		String isElement = gene.getIsElement();
		String strIsElement = "";
		for (int i = 0; i < isElement.length(); i++) {
				if (i != 0) {
					if (isElement.charAt(i) != ','
							&& (isElement.substring(0, i).indexOf(
									isElement.charAt(i)) < 0)) {
						strIsElement = strIsElement + isElement.charAt(i);
					}
				} else {
					if (isElement.charAt(i) != ',') {
						strIsElement = strIsElement + isElement.charAt(i);
					}
				}
			}
		
		myParameter.setInputFile(inputPath.getInputPath());
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
		myParameter.setMutateRate(gene.getMutateRate());

				
	    try {
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

			
			
		    
		try {
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
		}
		
		
		
       myGepService.run();
       
       
       
       if (flag == -1) {
				if (myParameter.equals(myConfigurationFromDB) == false) {
					return false;
					}
				else{
					
					return true;
				}
		} 
		else {
				try {
					myGepService.saveArgumentsToDb(myParameter);
				} catch (Duplicated e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
				
		}
		
		

	}
    
}
