package ui.conf.controller;

import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.event.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class OperatorController {
	
	OperatorModel operatorModel = null;
	OperatorPanel operatorPanel = null;
	Map<String, Boolean> isDataFitedMap = null;
	
	
	public OperatorController(OperatorModel operatorModel,OperatorPanel operatorPanel) {
		this.operatorModel = operatorModel;
		this.operatorPanel = operatorPanel;
		init();
	}
	
	
	public void init(){
		
		isDataFitedMap = operatorModel.getIsDataFitedMap();
		operatorPanel.fillModel(operatorModel);
		operatorModel.registerObserver(operatorPanel);
		
		
		operatorPanel.mutateTextField.getDocument().addDocumentListener
		(new MutateTextField_onValueChanged ());

		
		operatorPanel.ISTextField.getDocument().addDocumentListener
		(new ISTextField_onValueChanged());
		
		operatorPanel.ISLengthTextField.getDocument().addDocumentListener
		(new ISLengthTextField_onValueChanged());
		
		operatorPanel.RISTextField.getDocument().addDocumentListener
		(new RISTextField_onValueChanged());
		
		operatorPanel.RISLengthTextField.getDocument().addDocumentListener
		(new RISLengthTextField_onValueChanged());
		
		operatorPanel.geneTextField.getDocument().addDocumentListener
		(new GeneTextField_onValueChanged());
		
		operatorPanel.oneCombineTextField.getDocument().addDocumentListener
		(new OneCombineTextField_onValueChanged());
		
		operatorPanel.twoCombineTextField.getDocument().addDocumentListener
		(new TwoCombineTextField_onValueChanged());
		
		operatorPanel.geneCombineTextField.getDocument().addDocumentListener
		(new GeneCombineTextField_onValueChanged());
		
		
	}
	
	private void checkValue(String thisKeyString,String str){
		if (str.equals("")||str.equals(null)) {
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, false);
			return;
		}
		try {
			switch (thisKeyString) {
			case "mutateRate":
				operatorModel.setMutateRate(Float.parseFloat(str));
				operatorPanel.mutateTextField.setBackground(Color.WHITE);
				break;
			case "isTransportRate":
				operatorModel.setIsTransportRate(Float.parseFloat(str));
				operatorPanel.ISTextField.setBackground(Color.WHITE);
				break;
			case "isElement":
				String[] isString = str.split(",");
				Integer[] isIntStr = new Integer[isString.length];
				for (int i = 0; i < isString.length; i++) {
					isIntStr[i] = Integer.valueOf(isString[i]);
				}
				operatorModel.setIsElement(isIntStr);
				operatorPanel.ISLengthTextField.setBackground(Color.WHITE);
				break;
			case "risTransportRate":
				operatorModel.setRisTransportRate(Float.parseFloat(str));
				operatorPanel.RISTextField.setBackground(Color.WHITE);
				break;
			case "risElement":
				String[] risString = str.split(",");
				Integer[] risIntStr = new Integer[risString.length];
				for (int i = 0; i < risString.length; i++) {
					risIntStr[i] = Integer.valueOf(risString[i]);
				}
				operatorModel.setRisElement(risIntStr);
				operatorPanel.RISLengthTextField.setBackground(Color.WHITE);
				break;
			case "geneTransportRate":
				operatorModel.setGeneTransportRate(Float.parseFloat(str));
				operatorPanel.geneTextField.setBackground(Color.WHITE);
				break;
			case "onePointRecombineRate":
				operatorModel.setOnePointRecombineRate(Float.parseFloat(str));
				operatorPanel.oneCombineTextField.setBackground(Color.WHITE);
				break;
			case "twoPointRecombineRate":
				operatorModel.setTwoPointRecombineRate(Float.parseFloat(str));
				operatorPanel.twoCombineTextField.setBackground(Color.WHITE);
				break;
			case "geneRecombineRate":
				operatorModel.setGeneRecombineRate(Float.parseFloat(str));
				operatorPanel.geneCombineTextField.setBackground(Color.WHITE);
				break;
			default:
				break;
			}
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
		} catch (NumberFormatException e) {
			Color color = new Color(255, 69, 0);
			switch (thisKeyString) {
			case "mutateRate":
				operatorPanel.mutateTextField.setBackground(color);
				break;
			case "isTransportRate":
				operatorPanel.ISTextField.setBackground(color);
				break;
			case "isElement":
				operatorPanel.ISLengthTextField.setBackground(color);
				break;
			case "risTransportRate":
				operatorPanel.RISTextField.setBackground(color);
				break;
			case "risElement":
				operatorPanel.RISLengthTextField.setBackground(color);
				break;
			case "geneTransportRate":
				operatorPanel.geneTextField.setBackground(color);
				break;
			case "onePointRecombineRate":
				operatorPanel.oneCombineTextField.setBackground(color);
				break;
			case "twoPointRecombineRate":
				operatorPanel.twoCombineTextField.setBackground(color);
				break;
			case "geneRecombineRate":
				operatorPanel.geneCombineTextField.setBackground(color);
				break;
			default:
				break;
			}
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, false);
		}
	}
	
	
	class MutateTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "mutateRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.mutateTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.mutateTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	
	
	
	

	
	class ISTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "isTransportRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.ISTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.ISTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class ISLengthTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "isElement";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.ISLengthTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.ISLengthTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class RISTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "risTransportRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.RISTextField.getText().toString();
			
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.RISTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class RISLengthTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "risElement";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.RISLengthTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.RISLengthTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class GeneTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "geneTransportRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.geneTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.geneTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class OneCombineTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "onePointRecombineRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.oneCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.oneCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	

	
	class TwoCombineTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "twoPointRecombineRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.twoCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.twoCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}
	
	
	
	
	
	
	class GeneCombineTextField_onValueChanged implements DocumentListener
	{
		
		String thisKeyString = "geneRecombineRate";
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String str = operatorPanel.geneCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.geneCombineTextField.getText().toString();
			checkValue(thisKeyString,str);
		}

	}






	public OperatorModel getOperatorModel() {
		return operatorModel;
	}


	public void setOperatorModel(OperatorModel operatorModel) {
		this.operatorModel = operatorModel;
	}


	public OperatorPanel getOperatorPanel() {
		return operatorPanel;
	}


	public void setOperatorPanel(OperatorPanel operatorPanel) {
		this.operatorPanel = operatorPanel;
	}


	public Map<String, Boolean> getIsDataFitedMap() {
		return isDataFitedMap;
	}


	public void setisDataFitedMap(
			Map<String, Boolean> isDataFitedMap) {
		this.isDataFitedMap = isDataFitedMap;
	}
	
	
	
	
	
	
	
}
