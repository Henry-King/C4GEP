package ui.algconfiguration.controller;

import java.awt.List;

import ui.algconfiguration.view.GepConfiguration;
import ui.algconfiguration.view.HostPanel;
import ui.algconfiguration.view.IgepAlgService;
import ui.algconfiguration.view.JPanelForFunction;
import ui.algconfiguration.view.GeneView;
import ui.algconfiguration.view.JPanelForInputFile;
import ui.algconfiguration.view.PopulationView;
import ui.algconfiguration.view.StopSettingView;
import ui.alginputdataprocess.view.MainFrame;




public class ConfigController {

	
	
	/**
	 *  重新设置所有数据
	 * @param parent
	 */
	public void resetConfiguration(MainFrame parent){
		
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
	
	
	
	
	
	/**
	 * 填充配置信息
	 * @param parent
	 */
	public void fillConfiguration(MainFrame parent){
		/*
	(HostPanel configurationPanel,
			JPanelForStopSetting stopSettingPanel,
			JPanelForPopulation populationPanel, JPanelForGene genePanel,
			JPanelForFunction functionPanel, JPanelForInputFile inputFilePanel,
			GepConfiguration myConfigurationFromDB,
			List<GepConfiguration> configurationsOfHistory,
			IgepAlgService myGepService)  
		 */
		inputFilePanel.txtInputPath.setText(myConfigurationFromDB
				.getInputFile());
		stopSettingPanel.txtAccuracy
				.setText(myConfigurationFromDB.getAccuray());
		functionPanel.txtConstantListSize.setText(myConfigurationFromDB
				.getConstantListSize());
		genePanel.txtGeneOnePointRecombineRate.setText(myConfigurationFromDB
				.getOnePointRecombineRate());
		genePanel.txtGeneRecombineRate.setText(myConfigurationFromDB
				.getGeneRecombineRate());
		genePanel.txtGeneTransportRate.setText(myConfigurationFromDB
				.getGeneTransportRate());
		genePanel.txtHomeoticGeneNums.setText(myConfigurationFromDB
				.getHomeoticGeneNumber());
		genePanel.txtHomeoticHeaderLength.setText(myConfigurationFromDB
				.getHomeoticHeaderLength());

		genePanel.txtIsTransportRate.setText(myConfigurationFromDB
				.getIsTransportRate());
		stopSettingPanel.txtMaxGeneration.setText(myConfigurationFromDB
				.getMaxGeneration());
		genePanel.txtMutateRate.setText(myConfigurationFromDB.getMutateRate());
		genePanel.txtNormalGeneNumber.setText(myConfigurationFromDB
				.getNormalGeneNumber());
		genePanel.txtNormalHeaderLength.setText(myConfigurationFromDB
				.getNormalHeaderLength());
		genePanel.txtofIsElement.setText(myConfigurationFromDB.getIsElement());
		genePanel.txtofRisElement
				.setText(myConfigurationFromDB.getRisElement());
		populationPanel.txtPopulationSize.setText(myConfigurationFromDB
				.getPopulationSize());
		functionPanel.txtRandomConstantStart.setText(myConfigurationFromDB
				.getRandomConstantStart());
		genePanel.txtRisTransportRate.setText(myConfigurationFromDB
				.getRisTransportRate());
		populationPanel.txtSelectionRange.setText(myConfigurationFromDB
				.getSelectionRange());
		genePanel.txtTwoPointRecombineRate.setText(myConfigurationFromDB
				.getTwoPointRecombineRate());

		String[] functions = myConfigurationFromDB.getFunctionList().split(",");
		for (int i = 0; i < functions.length; i++) {
			System.out.println(functions[i].toString());
			if (functions[i].toString().equals(
					"domain.service.alg.userdefined.Additioin")) {
				functionPanel.JComboBoxOfSelectdFunctions.addItem("+");
			} else if (functions[i].toString().equals(
					"domain.service.alg.userdefined.Minus")) {
				functionPanel.JComboBoxOfSelectdFunctions.addItem("-");
			} else if (functions[i].toString().equals(
					"domain.service.alg.userdefined.Multiply")) {
				functionPanel.JComboBoxOfSelectdFunctions.addItem("*");
			} else {
				functionPanel.JComboBoxOfSelectdFunctions.addItem("/");
			}
		}
		populationPanel.JcomboBoxOfPopulationCreator
				.setSelectedItem(myConfigurationFromDB.getCreator());
		genePanel.JComboBoxOfAvailableModifyings
				.setSelectedItem(myConfigurationFromDB.getModify());
		populationPanel.JComboBoxAvailableCalculator
				.setSelectedItem(myConfigurationFromDB.getCalculator());
		configurationPanel.setVisible(false);
		stopSettingPanel.setVisible(true);
		stopSettingPanel.txtMaxGeneration.grabFocus();
		
		
	}
	
	
	
}
