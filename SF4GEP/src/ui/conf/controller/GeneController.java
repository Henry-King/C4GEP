package ui.conf.controller;

import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.*;

import data.dao.IHibernateDataContext;
import domain.core.algconfiguration.Function;
import domain.service.algConfiguration.GepConfigurationService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		
//		IndividualNumTextField_onValueChanged
//		NormalGeneHeaderTextField_onValueChanged
//		NormalGeneNumTextField_onValueChanged
//		HomeoticGeneTextField_onValueChanged
//		HomeoticGeneHeaderTextField_onValueChanged
		
		/*genePanel.individualNumtextField.getDocument().addDocumentListener
		(new IndividualNumTextField_onValueChanged ());
		
		genePanel.normalGeneNumTextField.getDocument().addDocumentListener
		(new NormalGeneNumTextField_onValueChanged());
		
		genePanel.normalGeneHeaderTextField.getDocument().addDocumentListener
		(new NormalGeneHeaderTextField_onValueChanged());
		
		genePanel.homeoticGeneTextField.getDocument().addDocumentListener
		(new HomeoticGeneTextField_onValueChanged());
		
		genePanel.homeoticGeneHeaderTextField.getDocument().addDocumentListener
		(new HomeoticGeneHeaderTextField_onValueChanged());*/
		
		
		
		
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
				
				
				
				genePanel.cardLayout.show(genePanel.connectionPanel, "homeoticPannel");
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
				
				
				genePanel.cardLayout.show(genePanel.connectionPanel, "connectionPannel");
			}
		});
		
		IHibernateDataContext hibernateDataContext=genePanel.getMainWnd().getHibernateDataContext();
		gepConfigurationService=new GepConfigurationService(hibernateDataContext);
		List<Function> functions=gepConfigurationService.getAvailableFunctions();
		JComboBox<Function> connectionComboBox=genePanel.connectionComboBox;
		for(Function added:functions)
			connectionComboBox.addItem(added);
		JList<Function> functionList=genePanel.functionList;
		functionList.setListData(functions.toArray(new Function[]{}));
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
	
	
	
	
	
	
	
	
	
	class IndividualNumTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "individualNumber";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = genePanel.individualNumtextField.getText().toString();
			
			/*try {
				Integer.parseInt(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.individualNumtextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Integer.parseInt(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneHeaderTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Integer.parseInt(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneNumTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Integer.parseInt(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			
			
			String str = genePanel.homeoticGeneTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Long.parseLong(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			/*if (!geneModel.isUseHomeoticGene()) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				return;
			}*/
			
			String str = genePanel.homeoticGeneHeaderTextField.getText().toString();
			try {
				Long.parseLong(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

	}
	
	
	
	
	
	
}
