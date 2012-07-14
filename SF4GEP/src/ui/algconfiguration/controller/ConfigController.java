package ui.algconfiguration.controller;

import java.io.File;
import java.util.*;

import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.IndividualConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import sun.management.OperatingSystemImpl;
import ui.alginputdataprocess.view.MainFrame;

public class ConfigController {

	/**
	 * 重新设置所有数据
	 * 
	 * @param parent
	 */
	public void resetConfiguration(MainFrame parent) {

		parent.inputFilePanel.txtInputPath.setText("");
		parent.stopSettingPanel.txtAccuracy.setText("");
		parent.functionPanel.txtConstantListSize.setText("");
		parent.genePanel.txtGeneOnePointRecombineRate.setText("");
		parent.genePanel.txtGeneRecombineRate.setText("");
		parent.genePanel.txtGeneTransportRate.setText("");
		parent.genePanel.txtHomeoticGeneNums.setText("");
		parent.genePanel.txtHomeoticHeaderLength.setText("");

		parent.genePanel.txtIsTransportRate.setText("");
		parent.stopSettingPanel.txtMaxGeneration.setText("");
		parent.genePanel.txtMutateRate.setText("");
		parent.genePanel.txtNormalGeneNumber.setText("");
		parent.genePanel.txtNormalHeaderLength.setText("");
		parent.genePanel.txtofIsElement.setText("");
		parent.genePanel.txtofRisElement.setText("");
		parent.populationPanel.txtPopulationSize.setText("");
		parent.functionPanel.txtRandomConstantStart.setText("");
		parent.genePanel.txtRisTransportRate.setText("");
		parent.populationPanel.txtSelectionRange.setText("");
		parent.genePanel.txtTwoPointRecombineRate.setText("");
	}
	
	
	
	/**
	 * 填充配置信息
	 * 
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	public void fillConfiguration(MainFrame parent) {
	

		GepAlgConfiguration myConfigurationFromDB = parent.gepAlgConfiguration;

		parent.inputFilePanel.txtInputPath.setText(parent.inputFile.getPath());
		parent.stopSettingPanel.txtAccuracy.setText(myConfigurationFromDB
				.getAccuracy().toString());
		parent.genePanel.txtGeneOnePointRecombineRate
				.setText(myConfigurationFromDB.getOperatorConfiguration()
						.getOnePointRecombineRate().toString());
		parent.genePanel.txtGeneRecombineRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getGeneRecombineRate().toString());
		parent.genePanel.txtGeneTransportRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getGeneTransportRate().toString());
		parent.genePanel.txtHomeoticGeneNums.setText(myConfigurationFromDB
				.getIndividualConfiguration().getHomeoticGeneTotalLength()
				.toString());
		parent.genePanel.txtHomeoticHeaderLength.setText(myConfigurationFromDB
				.getIndividualConfiguration().getGeneConfiguration()
				.getHomeoticGeneHeaderLength().toString());
		parent.genePanel.txtIsTransportRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getIsTransportRate().toString());
		parent.stopSettingPanel.txtMaxGeneration.setText(myConfigurationFromDB
				.getMaxGeneration().toString());
		parent.genePanel.txtMutateRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getMutateRate().toString());
		parent.genePanel.txtNormalGeneNumber.setText(myConfigurationFromDB
				.getIndividualConfiguration().getGeneTotalLength().toString());
		parent.genePanel.txtNormalHeaderLength.setText(myConfigurationFromDB
				.getIndividualConfiguration().getGeneConfiguration()
				.getNormalGeneHeaderLength().toString());
		parent.genePanel.txtofIsElement.setText(myConfigurationFromDB
				.getOperatorConfiguration().getIsElement().toString());
		parent.genePanel.txtofRisElement.setText(myConfigurationFromDB
				.getOperatorConfiguration().getRisElement().toString());
		parent.populationPanel.txtPopulationSize.setText(myConfigurationFromDB
				.getIndividualConfiguration().getIndividualNumber().toString());
		parent.genePanel.txtRisTransportRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getRisTransportRate().toString());
		parent.populationPanel.txtSelectionRange.setText(myConfigurationFromDB
				.getSelectionRange().toString());
		parent.genePanel.txtTwoPointRecombineRate.setText(myConfigurationFromDB
				.getOperatorConfiguration().getTwoPointRecombineRate()
				.toString());

		
		List<Function> functions = myConfigurationFromDB.getIndividualConfiguration().getGeneConfiguration().getFunctionUsed();
		
		
		
		for (int i = 0; i < functions.size(); i++) {
			Function function = functions.get(i);
			
			System.out.println(function.toString());
			parent.functionPanel.JComboBoxOfSelectdFunctions.addItem(function);
			
		}
		
		
		parent.configurationPanel.setVisible(false);
		parent.stopSettingPanel.setVisible(true);
		parent.stopSettingPanel.txtMaxGeneration.grabFocus();

	}
	
	
	
	public void setNewConfiguration(MainFrame parent){
		GepAlgConfiguration newConfiguration = new GepAlgConfiguration();
		IndividualConfiguration individualConfiguration = new IndividualConfiguration();
		GeneConfiguration geneConfiguration = new GeneConfiguration();
		OperatorConfiguration operatorConfiguration = new OperatorConfiguration();
		
		individualConfiguration.setGeneConfiguration(geneConfiguration);
		
		newConfiguration.setIndividualConfiguration(individualConfiguration);
		newConfiguration.setOperatorConfiguration(operatorConfiguration);
		
		
		
		parent.inputFile = new File(parent.inputFilePanel.txtInputPath.getText().toString());
		
		
		
		
		newConfiguration.setName(parent.configurationPanel.jcomboBoxConfiguration.getSelectedItem().toString());
		newConfiguration.setAccuracy(Float.parseFloat(parent.stopSettingPanel.txtAccuracy.getText().toString()));
		newConfiguration.setMaxGeneration(Long.parseLong(parent.stopSettingPanel.txtMaxGeneration.getText().toString()));
		newConfiguration.setSelectionRange(Float.parseFloat(parent.populationPanel.txtSelectionRange.getText().toString()));
		
		//individualConfiguration.setNormalGeneTotalLength(Integer.parseInt(parent.genePanel.txtNormalGeneNumber.getText().toString()));
		individualConfiguration.setIndividualNumber(Integer.parseInt(parent.populationPanel.txtPopulationSize.getText().toString()));
		
		geneConfiguration.setNormalGeneLength(Integer.parseInt(parent.genePanel.txtNormalGeneNumber.getText().toString()));
		geneConfiguration.setNormalGeneHeaderLength(Integer.parseInt(parent.genePanel.txtNormalHeaderLength.getText().toString()));
		geneConfiguration.setHomeoticGeneLength(Integer.parseInt(parent.genePanel.txtHomeoticGeneNums.getText().toString()));
		geneConfiguration.setHomeoticGeneHeaderLength(Integer.parseInt(parent.genePanel.txtHomeoticHeaderLength.getText().toString()));
		
		operatorConfiguration.setGeneRecombineRate(Float.parseFloat(parent.genePanel.txtGeneRecombineRate.getText().toString()));
		operatorConfiguration.setGeneTransportRate(Float.parseFloat(parent.genePanel.txtGeneTransportRate.getText().toString()));
		operatorConfiguration.setIsTransportRate(Float.parseFloat(parent.genePanel.txtIsTransportRate.getText().toString()));
		operatorConfiguration.setOnePointRecombineRate(Float.parseFloat(parent.genePanel.txtGeneOnePointRecombineRate.getText().toString()));
		operatorConfiguration.setRisTransportRate(Float.parseFloat(parent.genePanel.txtRisTransportRate.getText().toString()));
		operatorConfiguration.setTwoPointRecombineRate(Float.parseFloat(parent.genePanel.txtTwoPointRecombineRate.getText().toString()));
		operatorConfiguration.setMutateRate(Float.parseFloat(parent.genePanel.txtMutateRate.getText().toString()));
		
		operatorConfiguration.setIsElementString(parent.genePanel.txtofIsElement.getText().toString());
		operatorConfiguration.setRisElementString(parent.genePanel.txtofRisElement.getText().toString());
		operatorConfiguration.setIsTransportRate(Float.parseFloat(parent.genePanel.txtIsTransportRate.getText().toString()));
		operatorConfiguration.setIsTransportRate(Float.parseFloat(parent.genePanel.txtIsTransportRate.getText().toString()));
		List<Function> functionList=new LinkedList<Function>();
		for(int i=0;i<parent.functionPanel.JComboBoxOfSelectdFunctions.getItemCount();i++){
			functionList.add(parent.functionPanel.JComboBoxOfSelectdFunctions.getItemAt(i));		 
		}
			 
		geneConfiguration.setFunctionUsed(functionList);
		
			
			
		
		
		
		
	}
	
	
	
	
	

}
