/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.*;

import com.sun.istack.internal.FinalArrayList;

import domain.core.algconfiguration.*;

import ui.conf.*;
import ui.conf.controller.AccuracyController;
import ui.conf.model.AccuracyModel;
import ui.conf.model.GeneModel;
import ui.conf.model.OperatorModel;
import ui.conf.view.ContentPanel;
import ui.app.*;
import ui.images.ImageHelper;



/**
 * @author Michael Hagen
 */
public class MainToolBar extends JToolBar {
    private ImageIcon newImage = null;
    private ImageIcon openImage = null;
    private ImageIcon saveImage = null;
    private ImageIcon cutImage = null;
    private ImageIcon copyImage = null;
    private ImageIcon pasteImage = null;
    private ImageIcon undoImage = null;
    private ImageIcon redoImage = null;
    private ImageIcon runImage = null;
    private ImageIcon clearImage = null;
    private ImageIcon debugImage = null;
    private ImageIcon cfgImage = null;
    
    private ToolButton newButton = null;
    private ToolButton openButton = null;
    private ToolButton saveButton = null;
    private ToolButton cutButton = null;
    private ToolButton runButton = null;
    private ToogleToolButton copyButton = null;
    private ToolButton pasteButton = null;
    private ToolButton undoButton = null;
    private ToolButton redoButton = null;
    private ToolButton clearButton = null;
    private ToolButton debugButton = null;
    private ToolButton cfgButton = null;
//    private JButton defaultBorderButton = null;
    
    private MainWnd mainWnd;
    private ContentPanel contentPanel;
    
    
    
    
    

    public MainToolBar(final MainWnd mainWnd,final ContentPanel contentPanel) {
        super();
        this.mainWnd = mainWnd;
        this.contentPanel = contentPanel;
        //this.contentPanel = contentPanel;
        setFloatable(false);
        setMargin(new Insets(2, 0, 2, 0));
        newImage = ImageHelper.loadImage("new.png");
        newButton = new ToolButton(newImage);
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NewGEPDialog newGEPDialog = new NewGEPDialog("NewGEPDialog",mainWnd);
            }
        });
        newButton.setToolTipText("�½�");
        
        openImage = ImageHelper.loadImage("open.png");
        openButton = new ToolButton(openImage);
        openButton.setToolTipText("��");
        
        saveImage = ImageHelper.loadImage("save.png");
        saveButton = new ToolButton(saveImage);
        saveButton.setToolTipText("����");
        
        
        
        
        cutImage = ImageHelper.loadImage("cut.png");
        cutButton = new ToolButton(cutImage);
        cutButton.setToolTipText("����");
        
        copyImage = ImageHelper.loadImage("copy.png");
        copyButton = new ToogleToolButton(copyImage);
        copyButton.setToolTipText("����");
        
        pasteImage = ImageHelper.loadImage("paste.png");
        pasteButton = new ToolButton(pasteImage);
        pasteButton.setToolTipText("ճ��");
        pasteButton.setEnabled(false);
        
        clearImage = ImageHelper.loadImage("clear.png");
        clearButton = new ToolButton(clearImage);
        clearButton.setToolTipText("���");
        
        undoImage = ImageHelper.loadImage("undo.png");
        undoButton = new ToolButton(undoImage);
        undoButton.setToolTipText("����");
        
        redoImage = ImageHelper.loadImage("redo.png");
        redoButton = new ToolButton(redoImage);
        redoButton.setToolTipText("����");
        
        
        
        
        
        runImage = ImageHelper.loadImage("run.png");
        runButton = new ToolButton(runImage);
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	
            	
            	
            	
            	/*�������*/
            	AccuracyModel accuracyModel = contentPanel.accuracyController.getAccuracyModel();
            	Hashtable<String, Boolean> isAccuracyFited = accuracyModel.getIsDataFitedHashtable();
            	Collection<Boolean> isAccuracyFitedValues = isAccuracyFited.values();
            	
            	
            	if (isAccuracyFitedValues.contains(false)) {
            		//JOptionPane.showMessageDialog(mainWnd.frame,"��������ȷ");
				}else{
					//JOptionPane.showMessageDialog(mainWnd.frame,accuracyModel.getMaxGeneration()+"|"+accuracyModel.getAccuracy()+"|"+accuracyModel.getSelectionRange());
				
					GepAlgConfiguration gepAlgConfiguration = new GepAlgConfiguration();
	            	gepAlgConfiguration.setMaxGeneration(accuracyModel.getMaxGeneration());
	            	gepAlgConfiguration.setSelectionRange(accuracyModel.getSelectionRange());
	            	gepAlgConfiguration.setAccuracy(accuracyModel.getAccuracy());
				
				}
            	
            	
            	
            	/*�������*/
            	GeneModel geneModel = contentPanel.geneController.getGeneModel();
            	Hashtable<String, Boolean> isGeneFited = geneModel.getIsDataFitedHashtable();
            	Collection<Boolean> isGeneFitedValues = isGeneFited.values();
            	
            	
            	if (isGeneFitedValues.contains(false)) {
            		//JOptionPane.showMessageDialog(mainWnd.frame,"��������ȷ");
				}else{
					//JOptionPane.showMessageDialog(mainWnd.frame,accuracyModel.getMaxGeneration()+"|"+accuracyModel.getAccuracy()+"|"+accuracyModel.getSelectionRange());
					
					IndividualConfiguration individualConfiguration = new IndividualConfiguration();
					GeneConfiguration geneConfiguration = new GeneConfiguration();
					individualConfiguration.setGeneConfiguration(geneConfiguration);
					
					individualConfiguration.setIndividualNumber(geneModel.getIndividualNumber());
					geneConfiguration.setNormalGeneNumber(geneModel.getNormalGeneNumber());
					geneConfiguration.setNormalGeneHeaderLength(geneModel.getNormalGeneHeaderLength());
					
					if (geneModel.isUseHomeoticGene()) {	//ʹ��ͬԴ��������
						geneConfiguration.setHomeoticGeneNumber(geneModel.getHomeoticGeneNumber());
						geneConfiguration.setHomeoticGeneHeaderLength(geneModel.getHomeoticGeneHeaderLength());
						
						/*JOptionPane.showMessageDialog(mainWnd.frame,geneModel.getIndividualNumber()+"|"
								+geneModel.getNormalGeneNumber()+"|"
								+geneModel.getNormalGeneHeaderLength()+"|"
								+geneModel.getHomeoticGeneNumber()+"|"
								+geneModel.getHomeoticGeneHeaderLength());
						*/
					}else{	//ʹ�ú�������
						JOptionPane.showMessageDialog(mainWnd.frame,"ʹ�ú�������");
						//geneConfiguration.setConnectionFunction(geneModel.getConnectionFunction());
						
					}
					
				
				}
            	
            	
            	
            	/*�������*/
            	OperatorModel operatorModel = contentPanel.operatorController.getOperatorModel();
            	Hashtable<String, Boolean> isOperatorFited = operatorModel.getIsDataFitedHashtable();
            	Collection<Boolean> isOperatorFitedValues = isOperatorFited.values();
            	
            	
            	if (isOperatorFitedValues.contains(false)) {
            		JOptionPane.showMessageDialog(mainWnd.frame,"��������ȷ");
				}else{
				
					OperatorConfiguration operatorConfiguration = new OperatorConfiguration();
					operatorConfiguration.setMutateRate(operatorModel.getMutateRate());
					operatorConfiguration.setIsTransportRate(operatorModel.getIsTransportRate());
					operatorConfiguration.setIsElement(operatorModel.getIsElement());
					operatorConfiguration.setRisTransportRate(operatorModel.getRisTransportRate());
					operatorConfiguration.setRisElement(operatorModel.getRisElement());
					operatorConfiguration.setGeneTransportRate(operatorModel.getGeneTransportRate());
					operatorConfiguration.setOnePointRecombineRate(operatorModel.getOnePointRecombineRate());
					operatorConfiguration.setTwoPointRecombineRate(operatorModel.getTwoPointRecombineRate());
					operatorConfiguration.setGeneRecombineRate(operatorModel.getGeneRecombineRate());
			
					JOptionPane.showMessageDialog(mainWnd.frame,operatorModel.getMutateRate()+"|"
							+operatorModel.getIsTransportRate()+"|"
							+operatorModel.getIsElement()+"|"
							+operatorModel.getRisTransportRate()+"|"
							+operatorModel.getRisElement()+"|"
							+operatorModel.getGeneTransportRate()+"|"
							+operatorModel.getOnePointRecombineRate()+"|"
							+operatorModel.getTwoPointRecombineRate()+"|"
							+operatorModel.getGeneRecombineRate());
				
				}
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            	
            }
        });
        runButton.setToolTipText("ִ���㷨");
        
        
        
        
        
        debugImage = ImageHelper.loadImage("debug.png");
        debugButton = new ToolButton(debugImage);
        debugButton.setToolTipText("����");
        
        
        cfgImage = ImageHelper.loadImage("config.png");
        cfgButton = new ToolButton(cfgImage);
        cfgButton.setToolTipText("����");

//        defaultBorderButton = new JButton("DefaultBorder");
//        defaultBorderButton.setFocusable(false);
//        defaultBorderButton.putClientProperty("paintToolBarBorder", Boolean.FALSE);

        add(newButton);
        add(openButton);
        add(saveButton);
        
        addSeparator();
        add(cutButton);
        add(copyButton);
        add(pasteButton);
        add(clearButton);
        addSeparator();
        add(undoButton);
        add(redoButton);
        addSeparator();
        add(runButton);
        //add(debugButton);
        add(cfgButton);

//        addSeparator();
//        add(defaultBorderButton);

    }

    private class ToolButton extends JButton {

        public ToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }

    private class ToogleToolButton extends JToggleButton {

        public ToogleToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }
}
