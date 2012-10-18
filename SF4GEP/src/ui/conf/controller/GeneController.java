package ui.conf.controller;

import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.*;

import data.dao.IHibernateDataContext;
import domain.core.algconfiguration.Function;
import domain.service.algConfiguration.GepConfigurationService;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class GeneController {
	GeneModel geneModel = null;
	GenePanel genePanel = null;
	Map<String, Boolean> isDataFitedMap = null;
	private GepConfigurationService gepConfigurationService=null;
	public GeneController(GeneModel geneModel,GenePanel genePanel) {
		this.geneModel = geneModel;
		this.genePanel = genePanel;
		init();
	}
	
	
	public void init(){
		isDataFitedMap = geneModel.getIsDataFitedMap();
		genePanel.fillModel(geneModel);
		geneModel.registerObserver(genePanel);
		
		
		IHibernateDataContext hibernateDataContext=genePanel.getMainWnd().getHibernateDataContext();
		gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		List<Function> functions=gepConfigurationService.getAvailableFunctions();
		JComboBox<Function> connectionComboBox=genePanel.connectionComboBox;
		for(Function added:functions)
			connectionComboBox.addItem(added);
		JList<Function> functionList=genePanel.functionList;
		functionList.setListData(functions.toArray(new Function[]{}));
		
//		IndividualNumTextField_onValueChanged
//		NormalGeneHeaderTextField_onValueChanged
//		NormalGeneNumTextField_onValueChanged
//		HomeoticGeneTextField_onValueChanged
//		HomeoticGeneHeaderTextField_onValueChanged
		
		genePanel.individualNumtextField.getDocument().addDocumentListener
		(new IndividualNumTextField_onValueChanged ());
		
		genePanel.normalGeneNumTextField.getDocument().addDocumentListener
		(new NormalGeneNumTextField_onValueChanged());
		
		genePanel.normalGeneHeaderTextField.getDocument().addDocumentListener
		(new NormalGeneHeaderTextField_onValueChanged());
		
		genePanel.homeoticGeneTextField.getDocument().addDocumentListener
		(new HomeoticGeneTextField_onValueChanged());
		
		genePanel.homeoticGeneHeaderTextField.getDocument().addDocumentListener
		(new HomeoticGeneHeaderTextField_onValueChanged());
		
		
		
		genePanel.homeoticGeneRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (genePanel.homeoticGeneRadioButton.isSelected()) {
					
				}
			}
		});
		
		genePanel.connectionFuncRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String connectionString = null;
				if (genePanel.connectionFuncRadioButton.isSelected()) {
					connectionString = genePanel.connectionComboBox.getSelectedItem().toString();
				}
				List<Function> functions=gepConfigurationService.getAvailableFunctions();
				for(Function added:functions){
					if (added.getName().equals(connectionString)) {
						geneModel.setConnectionFunction(added);
						//System.out.println(added.getName());
					}
				}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		genePanel.connectionComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<Function> jcb = (JComboBox<Function>) e.getSource();
			    String connectionString = jcb.getSelectedItem().toString();
				//geneModel.setConnectionFunction(geneConfiguration.getConnectionFunction());
				List<Function> functions=gepConfigurationService.getAvailableFunctions();
				for(Function added:functions){
					if (added.getName().equals(connectionString)) {
						geneModel.setConnectionFunction(added);
						//System.out.println(added.getName());
					}
				}
				
				
			}
		});
		
		
		genePanel.homeoticGeneRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//genePanel.useHomeoticGene = true;
				geneModel.setUseHomeoticGene(true);
				if (isDataFitedMap.containsKey("homeoticGeneNumber")||isDataFitedMap.containsKey("homeoticGeneHeaderLength")) {
					
				}else{
					String homeoticGeneString = genePanel.homeoticGeneTextField.getText();
					String homeoticGeneHeaderString = genePanel.homeoticGeneHeaderTextField.getText();
					if (homeoticGeneString.equals("")||homeoticGeneString.equals(null)||
							homeoticGeneHeaderString.equals("")||homeoticGeneHeaderString.equals(null)) {
						//Œ¥ ‰»Î
					}else{
						try {
							Integer.parseInt(homeoticGeneString);
							Integer.parseInt(homeoticGeneHeaderString);
							isDataFitedMap.put("homeoticGeneNumber", true);
							isDataFitedMap.put("homeoticGeneHeaderLength", true);
						} catch (Exception e2) {
							isDataFitedMap.put("homeoticGeneNumber", false);
							isDataFitedMap.put("homeoticGeneHeaderLength", false);
						}
					}
				}
				
				
				
				//genePanel.cardLayout.show(genePanel.connectionPanel, "homeoticPannel");
			}
		});
		
		genePanel.connectionFuncRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//genePanel.useHomeoticGene = false;
				geneModel.setUseHomeoticGene(false);
				if (isDataFitedMap.containsKey("homeoticGeneNumber")||isDataFitedMap.containsKey("homeoticGeneHeaderLength")) {
					isDataFitedMap.remove("homeoticGeneNumber");
					isDataFitedMap.remove("homeoticGeneHeaderLength");
				}
				
				
				//genePanel.cardLayout.show(genePanel.connectionPanel, "connectionPannel");
			}
		});
		
		
	}


	public GeneModel getGeneModel() {
		return geneModel;
	}


	public void setGeneModel(GeneModel geneModel) {
		this.geneModel = geneModel;
	}


	public GenePanel getGenePanel() {
		return genePanel;
	}


	public void setGenePanel(GenePanel genePanel) {
		this.genePanel = genePanel;
	}


	public Map<String, Boolean> getisDataFitedMap() {
		return isDataFitedMap;
	}


	public void setisDataFitedMap(
			Hashtable<String, Boolean> isDataFitedMap) {
		this.isDataFitedMap = isDataFitedMap;
	}
	
	
	
	
	private void checkValue(String thisKeyString,String str){
		if (str.equals("")||str.equals(null)) {
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, false);
			return;
		}
		try {
			switch (thisKeyString) {
			case "individualNumber":
				geneModel.setIndividualNumber(Integer.parseInt(str));
				genePanel.individualNumtextField.setBackground(Color.WHITE);
				break;
			case "normalGeneNumber":
				geneModel.setNormalGeneNumber(Integer.parseInt(str));
				genePanel.normalGeneNumTextField.setBackground(Color.WHITE);
				break;
			case "normalGeneHeaderLength":
				geneModel.setNormalGeneHeaderLength(Integer.parseInt(str));
				genePanel.normalGeneHeaderTextField.setBackground(Color.WHITE);
				break;
			case "homeoticGeneNumber":
				geneModel.setHomeoticGeneNumber(Integer.parseInt(str));
				genePanel.homeoticGeneTextField.setBackground(Color.WHITE);
				break;
			case "homeoticGeneHeaderLength":
				geneModel.setHomeoticGeneHeaderLength(Integer.parseInt(str));
				genePanel.homeoticGeneHeaderTextField.setBackground(Color.WHITE);
				break;
			default:
				break;
			}
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
		} catch (NumberFormatException e) {
			Color color = new Color(255, 69, 0);
			switch (thisKeyString) {
			case "individualNumber":
				genePanel.individualNumtextField.setBackground(color);
				break;
			case "normalGeneNumber":
				genePanel.normalGeneNumTextField.setBackground(color);
				break;
			case "normalGeneHeaderLength":
				genePanel.normalGeneHeaderTextField.setBackground(color);
				break;
			case "homeoticGeneNumber":
				genePanel.homeoticGeneTextField.setBackground(color);
				break;
			case "homeoticGeneHeaderLength":
				genePanel.homeoticGeneHeaderTextField.setBackground(color);
				break;
			default:
				break;
			}
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, false);
		}
	}
	
	
	
	
	class IndividualNumTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "individualNumber";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = genePanel.individualNumtextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.individualNumtextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	class NormalGeneHeaderTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "normalGeneHeaderLength";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = genePanel.normalGeneHeaderTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneHeaderTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	class NormalGeneNumTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "normalGeneNumber";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = genePanel.normalGeneNumTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneNumTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class HomeoticGeneTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "homeoticGeneNumber";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			
			
			
			String str = genePanel.homeoticGeneTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			
			
			String str = genePanel.homeoticGeneTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	class HomeoticGeneHeaderTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "homeoticGeneHeaderLength";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			
			
			String str = genePanel.homeoticGeneHeaderTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			/*if (!geneModel.isUseHomeoticGene()) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				return;
			}*/
			
			String str = genePanel.homeoticGeneHeaderTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
}
