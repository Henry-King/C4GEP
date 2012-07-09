package ui.alg.controller;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;

import jxl.read.biff.BiffException;
import ui.alg.view.HostPanel;
import ui.alg.view.JPanelForFunction;
import ui.alg.view.JPanelForGene;
import ui.alg.view.JPanelForStopSetting;
import ui.alg.view.JPanelForPopulation;
import ui.input.view.*;



public class HostPanelController {
   
 
   
   
  
   
   public static void btnNext0Controller(DefaultMutableTreeNode node1,JTree tree_1, HostPanel configurationPanel,JPanelForStopSetting inputPathPanel) {
		
		TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
		tree_1.setSelectionPath(visiblePath);
		configurationPanel.setVisible(false);
	    inputPathPanel.setVisible(true);
	   
	}
	public static void btnSetConfigController(DefaultMutableTreeNode node1,JTree tree_1, HostPanel configurationPanel,JPanelForStopSetting inputPathPanel) {
		TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
		tree_1.setSelectionPath(visiblePath);
		configurationPanel.setVisible(false);
		inputPathPanel.setVisible(true);
		
	}
	public static String jcomboBoxItemController(ItemEvent ie,HostPanel configurationPanel,JPanelForStopSetting stopSettingPanel,
			JPanelForPopulation populationPanel,JPanelForGene genePanel,JPanelForFunction functionPanel,JPanelForFooter footerPanel,JPanelForInputFile  inputFilePanel,GepConfiguration myConfigurationFromDB,List<GepConfiguration> configurationsOfHistory,
			IgepAlgService myGepService) {
		
		if (ie.getStateChange()==ItemEvent.SELECTED){
	  		
	  		   footerPanel.setVisible(true);
	       	   configurationPanel.btnSetConfig.setVisible(false);
	       	   configurationPanel.setVisible(false);
	       	   stopSettingPanel.setVisible(true);
	    }
		return handler(configurationPanel, stopSettingPanel, populationPanel, genePanel, functionPanel,inputFilePanel, myConfigurationFromDB, configurationsOfHistory, myGepService);
   }

	public static void jcomboBoxEditorController(KeyEvent e,JButton btnSetConfig) {
		 if(e.getKeyCode() == KeyEvent.VK_ENTER){  
	        	btnSetConfig.setVisible(true);
	       }
		
	}
	
	public static String handler(HostPanel configurationPanel,JPanelForStopSetting stopSettingPanel,
		    JPanelForPopulation populationPanel,JPanelForGene genePanel,JPanelForFunction functionPanel,
		    JPanelForInputFile  inputFilePanel,
		    GepConfiguration myConfigurationFromDB,List<GepConfiguration> configurationsOfHistory,
		    IgepAlgService myGepService){
		    	
		    	inputFilePanel.txtInputPath.setText(myConfigurationFromDB.getInputFile());
				stopSettingPanel.txtAccuracy.setText(myConfigurationFromDB.getAccuray());
				functionPanel.txtConstantListSize.setText(myConfigurationFromDB.getConstantListSize());
				genePanel.txtGeneOnePointRecombineRate.setText(myConfigurationFromDB.getOnePointRecombineRate());
				genePanel.txtGeneRecombineRate.setText(myConfigurationFromDB.getGeneRecombineRate());
				genePanel.txtGeneTransportRate.setText(myConfigurationFromDB.getGeneTransportRate());
				genePanel.txtHomeoticGeneNums.setText(myConfigurationFromDB.getHomeoticGeneNumber());
				genePanel.txtHomeoticHeaderLength.setText(myConfigurationFromDB.getHomeoticHeaderLength());
				
				genePanel.txtIsTransportRate.setText(myConfigurationFromDB.getIsTransportRate());
				stopSettingPanel.txtMaxGeneration.setText(myConfigurationFromDB.getMaxGeneration());
				genePanel.txtMutateRate.setText(myConfigurationFromDB.getMutateRate());
				genePanel.txtNormalGeneNumber.setText(myConfigurationFromDB.getNormalGeneNumber());
				genePanel.txtNormalHeaderLength.setText(myConfigurationFromDB.getNormalHeaderLength());
				genePanel.txtofIsElement.setText(myConfigurationFromDB.getIsElement());
				genePanel.txtofRisElement.setText(myConfigurationFromDB.getRisElement());
				populationPanel.txtPopulationSize.setText(myConfigurationFromDB.getPopulationSize());
				functionPanel.txtRandomConstantStart.setText(myConfigurationFromDB.getRandomConstantStart());
				genePanel.txtRisTransportRate.setText(myConfigurationFromDB.getRisTransportRate());
				populationPanel.txtSelectionRange.setText(myConfigurationFromDB.getSelectionRange());
				genePanel.txtTwoPointRecombineRate.setText(myConfigurationFromDB.getTwoPointRecombineRate());
				

				String[] functions = myConfigurationFromDB.getFunctionList()
						.split(",");
				for (int i = 0; i < functions.length; i++) {
					System.out.println(functions[i].toString());
					if (functions[i].toString().equals("domain.service.alg.userdefined.function.Additioin")) {
					   functionPanel.JComboBoxOfSelectdFunctions.addItem("+");
					} else if (functions[i].toString().equals("domain.service.alg.userdefined.function.Minus")) {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("-");
					} else if (functions[i].toString().equals("domain.service.alg.userdefined.function.Multiply")) {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("*");
					} else {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("/");
					}
				}
				populationPanel.JcomboBoxOfPopulationCreator.setSelectedItem(myConfigurationFromDB.getCreator());
				genePanel.JComboBoxOfAvailableModifyings.setSelectedItem(myConfigurationFromDB.getModify());
				populationPanel.JComboBoxAvailableCalculator.setSelectedItem(myConfigurationFromDB.getCalculator());
				configurationPanel.setVisible(false);
				stopSettingPanel.setVisible(true);
				stopSettingPanel.txtMaxGeneration.grabFocus();
				return null;
		    }
	public static void btnSetConfigController(JPanelForStopSetting stopSettingPanel,
		    JPanelForPopulation populationPanel,JPanelForGene genePanel,JPanelForFunction functionPanel,
		    JPanelForInputFile  inputFilePanel){
		    initialiseController(stopSettingPanel,populationPanel,genePanel,functionPanel,inputFilePanel);
	}
	private static void initialiseController(JPanelForStopSetting stopSettingPanel,JPanelForPopulation populationPanel, JPanelForGene genePanel,JPanelForFunction functionPanel, JPanelForInputFile inputFilePanel) {
		inputFilePanel.txtInputPath.setText("");
		stopSettingPanel.txtAccuracy.setText("");
		functionPanel.txtConstantListSize.setText("");
		genePanel.txtGeneOnePointRecombineRate.setText("");
		genePanel.txtGeneRecombineRate.setText("");
		genePanel.txtGeneTransportRate.setText("");
		genePanel.txtHomeoticGeneNums.setText("");
		genePanel.txtHomeoticHeaderLength.setText("");
		
		genePanel.txtIsTransportRate.setText("");
		stopSettingPanel.txtMaxGeneration.setText("");
		genePanel.txtMutateRate.setText("");
		genePanel.txtNormalGeneNumber.setText("");
		genePanel.txtNormalHeaderLength.setText("");
		genePanel.txtofIsElement.setText("");
		genePanel.txtofRisElement.setText("");
		populationPanel.txtPopulationSize.setText("");
		functionPanel.txtRandomConstantStart.setText("");
		genePanel.txtRisTransportRate.setText("");
		populationPanel.txtSelectionRange.setText("");
		genePanel.txtTwoPointRecombineRate.setText("");
		
	}

}
