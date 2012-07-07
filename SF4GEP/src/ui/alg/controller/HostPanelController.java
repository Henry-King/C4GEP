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
import ui.alg.view.JPanelForInputPathSetting;
import ui.alg.view.JPanelForPopulation;
import ui.input.view.*;



public class HostPanelController {
   
   static int count=0;
   
   
   public static void scrollBtnController(){
	    count=1;
   }
   
   public static void btnNext0Controller(DefaultMutableTreeNode node1,JTree tree_1, HostPanel configurationPanel,JPanelForInputPathSetting inputPathPanel) {
		
		TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
		tree_1.setSelectionPath(visiblePath);
		configurationPanel.setVisible(false);
	    inputPathPanel.setVisible(true);
	   
	}
	public static void btnSetConfigController(DefaultMutableTreeNode node1,JTree tree_1, HostPanel configurationPanel,JPanelForInputPathSetting inputPathPanel) {
		TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
		tree_1.setSelectionPath(visiblePath);
		configurationPanel.setVisible(false);
		inputPathPanel.setVisible(true);
		
	}
	public static String jcomboBoxItemController(ItemEvent ie,HostPanel configurationPanel,JPanelForInputPathSetting inputPathPanel,
			JPanelForPopulation populationPanel,JPanelForGene genePanel,JPanelForFunction functionPanel,JPanelForFooter footerPanel,GepConfiguration myConfigurationFromDB,List<GepConfiguration> configurationsOfHistory,
			IgepAlgService myGepService) {
		
		if (ie.getStateChange()==ItemEvent.SELECTED){
	  		 if(count!=2){
	  			footerPanel.setVisible(true);
	       	   configurationPanel.btnChangeConfig.setVisible(true);
	       	   configurationPanel.btnSetConfig.setVisible(false);
	       	   configurationPanel.setVisible(false);
	       	   inputPathPanel.setVisible(true);
	       	  
	  		 }
	    }
		return handler(configurationPanel, inputPathPanel, populationPanel, genePanel, functionPanel, myConfigurationFromDB, configurationsOfHistory, myGepService);
   }

	public static void jcomboBoxEditorController(KeyEvent e,JButton btnSetConfig, JButton btnNewButton, JButton btnChangeConfig) {
		 if(e.getKeyCode() == KeyEvent.VK_ENTER){  
	        	btnSetConfig.setVisible(true);
	        	btnNewButton.setVisible(false);
	        	btnChangeConfig.setVisible(false);
	        	count=2;
	        	
	        }  
		
	}
	
	public static String handler(HostPanel configurationPanel,JPanelForInputPathSetting inputPathPanel,
		    JPanelForPopulation populationPanel,JPanelForGene genePanel,JPanelForFunction functionPanel,GepConfiguration myConfigurationFromDB,List<GepConfiguration> configurationsOfHistory,
		    IgepAlgService myGepService){
		    	System.out.print("有问题qqq");
		    	inputPathPanel.txtInputPath.setText(myConfigurationFromDB.getInputFile());
				inputPathPanel.txtAccuracy.setText(myConfigurationFromDB.getAccuray());
				functionPanel.txtConstantListSize.setText(myConfigurationFromDB.getConstantListSize());
				genePanel.txtGeneOnePointRecombineRate.setText(myConfigurationFromDB.getOnePointRecombineRate());
				genePanel.txtGeneRecombineRate.setText(myConfigurationFromDB.getGeneRecombineRate());
				genePanel.txtGeneTransportRate.setText(myConfigurationFromDB.getGeneTransportRate());
				genePanel.txtHomeoticGeneNums.setText(myConfigurationFromDB.getHomeoticGeneNumber());
				genePanel.txtHomeoticHeaderLength.setText(myConfigurationFromDB.getHomeoticHeaderLength());
				inputPathPanel.txtInputPath.setText(myConfigurationFromDB.getInputFile());// 文件输入路径
				genePanel.txtIsTransportRate.setText(myConfigurationFromDB.getIsTransportRate());
				inputPathPanel.txtMaxGeneration.setText(myConfigurationFromDB.getMaxGeneration());
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
					if (functions[i]
							.toString()
							.equals("domain.service.alg.userdefined.function.Additioin")) {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("+");
					} else if (functions[i].toString().equals(
							"domain.service.alg.userdefined.function.Minus")) {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("-");
					} else if (functions[i].toString().equals(
							"domain.service.alg.userdefined.function.Multiply")) {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("*");
					} else {
						functionPanel.JComboBoxOfSelectdFunctions.addItem("/");
					}
				}
				populationPanel.JcomboBoxOfPopulationCreator.setSelectedItem(myConfigurationFromDB.getCreator());
				genePanel.JComboBoxOfAvailableModifyings.setSelectedItem(myConfigurationFromDB.getModify());
				populationPanel.JComboBoxAvailableCalculator.setSelectedItem(myConfigurationFromDB.getCalculator());
				configurationPanel.setVisible(false);
				inputPathPanel.setVisible(true);
				inputPathPanel.txtInputPath.grabFocus();
				return null;
		    }

}
