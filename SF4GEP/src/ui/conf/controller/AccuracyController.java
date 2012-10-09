package ui.conf.controller;


import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.event.*;
import java.util.Hashtable;

public class AccuracyController {
	
	AccuracyModel accuracyModel = null;
	AccuracyPanel accuracyPanel = null;
	Hashtable<String, Boolean> isDataFitedHashtable = null;
	
	
	public AccuracyController(AccuracyModel accuracyModel,AccuracyPanel accuracyPanel) {
		this.accuracyModel = accuracyModel;
		this.accuracyPanel = accuracyPanel;
		init();
	}
	
	public AccuracyController(AccuracyPanel accuracyPanel) {
		accuracyModel = new AccuracyModel();
		this.accuracyPanel = accuracyPanel;
		init();
	}
	
	public void init(){
		isDataFitedHashtable = accuracyModel.getIsDataFitedHashtable();
		accuracyPanel.fillModel(accuracyModel);
		accuracyModel.registerObserver(accuracyPanel);
		accuracyPanel.generationNumTextField.getDocument().addDocumentListener
		(new GenerationNumTextField_onValueChanged ());
		
		
		accuracyPanel.accuracytextField.getDocument().addDocumentListener
		(new AccuracyTextField_onValueChanged());
		
		accuracyPanel.selectionRangeTextField.getDocument().addDocumentListener
		(new SelectionRangeTextField_onValueChanged());
		
		
		
		
	}

	public AccuracyModel getAccuracyModel() {
		return accuracyModel;
	}

	public void setAccuracyModel(AccuracyModel accuracyModel) {
		this.accuracyModel = accuracyModel;
		
	}

	public AccuracyPanel getAccuracyPanel() {
		return accuracyPanel;
	}

	public void setAccuracyPanel(AccuracyPanel accuracyPanel) {
		this.accuracyPanel = accuracyPanel;
	}
	
	
	
	/**
	 * 最大代数文本框修改事件
	 * @author Administrator
	 */
	class GenerationNumTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "maxGeneration";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = accuracyPanel.generationNumTextField.getText().toString();
			if (str.equals(null)) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
				return;
			}
			
			/*
			try {
				
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					Long.parseLong(str);
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					accuracyPanel.fillModel(accuracyModel);
				}
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = accuracyPanel.generationNumTextField.getText().toString();
			/*try {
				
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					Long.parseLong(str);
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					accuracyPanel.fillModel(accuracyModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

	}
	
	
	
	/**
	 * 选择范围文本框修改事件
	 * @author Administrator
	 *
	 */
	class SelectionRangeTextField_onValueChanged implements DocumentListener
	{
		String thisKeyString = "selectionRange";
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = accuracyPanel.selectionRangeTextField.getText().toString();
			
			/*try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					accuracyPanel.fillModel(accuracyModel);
				}
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = accuracyPanel.selectionRangeTextField.getText().toString();
			/*try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					accuracyPanel.fillModel(accuracyModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

	}

	
	
	
	/**
	 * 精度文本框修改事件
	 * @author Administrator
	 *
	 */
	class AccuracyTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "accuracy";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = accuracyPanel.accuracytextField.getText().toString();
			
			/*try {
				Float.parseFloat(str);
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, true);
				accuracyPanel.fillModel(accuracyModel);
			} catch (NumberFormatException e) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = accuracyPanel.accuracytextField.getText().toString();
			/*try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, false);
				}else{
					isDataFitedHashtable.remove(thisKeyString);
					isDataFitedHashtable.put(thisKeyString, true);
					accuracyPanel.fillModel(accuracyModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedHashtable.remove(thisKeyString);
				isDataFitedHashtable.put(thisKeyString, false);
			}*/
		}

	}
	
	
	
	
	
}

