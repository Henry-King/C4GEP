package ui.input.controller;

import javax.swing.JPanel;

import ui.input.view.JPanelForJTree;
import ui.input.view.JPanelForOutput;
import ui.input.view.JPanelForSaveConfig;
import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;
import exception.Duplicated;

public class SaveConfigPanelController {
	
   public static void btnSaveController(IgepAlgService myGepService,GepConfiguration myParameter,JPanelForOutput outputPanel,JPanelForSaveConfig savePanel,JPanelForJTree treePanel,JPanel panel_0){
	    
		outputPanel.setVisible(true);
		savePanel.setVisible(false);
		treePanel.setVisible(false);
   }
}
