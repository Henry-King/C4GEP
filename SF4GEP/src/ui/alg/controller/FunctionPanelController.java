package ui.alg.controller;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class FunctionPanelController {
	static int addTime=0;
	static ArrayList<String> items = new ArrayList<String>();
    public static void functionComboBoxController(JComboBox comboBox,JComboBox JComboBoxOfSelectdFunctions){
    	boolean isaddItem = true;
		if (addTime == 0) {
			items.add(comboBox.getSelectedItem().toString());
			JComboBoxOfSelectdFunctions.addItem(comboBox
					.getSelectedItem().toString());
			JComboBoxOfSelectdFunctions.setSelectedItem(comboBox
					.getSelectedItem().toString());
		} 
		else {
			for (int i = 0; i < items.size(); i++) {
				String item = comboBox.getSelectedItem().toString();
				if (item.equals(items.get(i))) {
					isaddItem = false;
				}
	        }
			if (isaddItem == true) {
				items.add(comboBox.getSelectedItem().toString());
				JComboBoxOfSelectdFunctions.addItem(comboBox
						.getSelectedItem().toString());
				JComboBoxOfSelectdFunctions.setSelectedItem(comboBox
						.getSelectedItem().toString());
			}
		}

		addTime++;

	}
    
}
