package ui.conf.controller;

import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GeneController {
	GeneModel geneModel = null;
	GenePanel genePanel = null;
	Hashtable<String, Boolean> isDataFitedHashtable = null;
	
	public GeneController(GeneModel geneModel,GenePanel genePanel) {
		this.geneModel = geneModel;
		this.genePanel = genePanel;
		init();
	}
	
	
	public void init(){
		isDataFitedHashtable = geneModel.getIsDataFitedHashtable();
		genePanel.fillModel(geneModel);
		geneModel.registerObserver(genePanel);
		
		
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
		
		genePanel.homeoticGeneRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//genePanel.useHomeoticGene = true;
				geneModel.setUseHomeoticGene(true);
				if (isDataFitedHashtable.contains("homeoticGeneNumber")||isDataFitedHashtable.contains("homeoticGeneHeaderLength")) {
					
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
							isDataFitedHashtable.put("homeoticGeneNumber", true);
							isDataFitedHashtable.put("homeoticGeneHeaderLength", true);
						} catch (Exception e2) {
							isDataFitedHashtable.put("homeoticGeneNumber", false);
							isDataFitedHashtable.put("homeoticGeneHeaderLength", false);
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
				
				if (isDataFitedHashtable.contains("homeoticGeneNumber")||isDataFitedHashtable.contains("homeoticGeneHeaderLength")) {
					isDataFitedHashtable.remove("homeoticGeneNumber");
					isDataFitedHashtable.remove("homeoticGeneHeaderLength");
				}
				
				
				genePanel.cardLayout.show(genePanel.connectionPanel, "connectionPannel");
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


	public Hashtable<String, Boolean> getIsDataFitedHashtable() {
		return isDataFitedHashtable;
	}


	public void setIsDataFitedHashtable(
			Hashtable<String, Boolean> isDataFitedHashtable) {
		this.isDataFitedHashtable = isDataFitedHashtable;
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
			
			try {
				Integer.parseInt(str);
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.individualNumtextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
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
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneHeaderTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
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
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = genePanel.normalGeneNumTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
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
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			
			
			String str = genePanel.homeoticGeneTextField.getText().toString();
			try {
				Integer.parseInt(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
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
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				genePanel.fillModel(geneModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			/*if (!geneModel.isUseHomeoticGene()) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				return;
			}*/
			
			String str = genePanel.homeoticGeneHeaderTextField.getText().toString();
			try {
				Long.parseLong(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					genePanel.fillModel(geneModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}
		}

	}
	
	
	
	
	
	
}
