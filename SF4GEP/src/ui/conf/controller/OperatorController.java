package ui.conf.controller;

import ui.conf.model.*;
import ui.conf.view.*;

import javax.swing.event.*;

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
		
		
		/*operatorPanel.mutateTextField.getDocument().addDocumentListener
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
		*/
		
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.mutateTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.ISTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			/**
			 * 需要正则判断
			 */
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, true);
			operatorPanel.fillModel(operatorModel);
			/*try {
				Long.parseLong(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.ISLengthTextField.getText().toString();
			try {
				Long.parseLong(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.RISTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			/**
			 * 需要正则判断
			 */
			isDataFitedMap.remove(thisKeyString);
			isDataFitedMap.put(thisKeyString, true);
			operatorPanel.fillModel(operatorModel);
			/*try {
				Long.parseLong(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}*/
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.RISLengthTextField.getText().toString();
			try {
				Long.parseLong(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.geneTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.oneCombineTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.twoCombineTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
			
			try {
				Float.parseFloat(str);
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, true);
				operatorPanel.fillModel(operatorModel);
			} catch (NumberFormatException e) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			String str = operatorPanel.geneCombineTextField.getText().toString();
			try {
				Float.parseFloat(str);
				if (str.equals("")||str.equals(null)) {
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, false);
				}else{
					isDataFitedMap.remove(thisKeyString);
					isDataFitedMap.put(thisKeyString, true);
					operatorPanel.fillModel(operatorModel);
				}
			}catch (NumberFormatException e1) {
				isDataFitedMap.remove(thisKeyString);
				isDataFitedMap.put(thisKeyString, false);
			}
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
