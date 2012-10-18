/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Timer;


import data.dao.HibernateDataContext;
import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.GepAlgRun;
import domain.core.algconfiguration.*;
import domain.iservice.algOutput.IAlgOutputService;
import domain.service.algConfiguration.GepConfigurationService;
import domain.service.algOutput.AlgCpuRunStep;
import domain.service.algOutput.AlgOutputService;

import ui.conf.*;
import ui.conf.controller.InputDataController;
import ui.conf.controller.OutputPictureController;
import ui.conf.model.*;
import ui.conf.view.*;
import ui.alginputdataprocess.controller.InputFileController;
import ui.app.*;
import ui.images.*;



/**
 * @author Michael Hagen
 */
public class MainToolBar extends JToolBar {
	private InputDataWnd inputDataWnd;
	private ProfileWnd profileWnd;
	private Point prePoint = null;
	private Timer timer = null;
	
	//private List<JWindow> winList = new ArrayList<JWindow>();
	
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
    private ImageIcon inputDataImage = null;
    private ImageIcon profileImage = null;
    
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
    private ToolButton inputDataButton = null;
    private ToolButton profileButton = null;
//    private JButton defaultBorderButton = null;
    
    private MainWnd mainWnd;
    private ConfPanel confPanel;
    private HibernateDataContext hibernateDataContext;
    private DataSet inputDataSet;
    
    private GepAlgRun gepAlgRun;
    

    public MainToolBar(final MainWnd mainWnd,final ConfPanel confPanel) {
        super();
        this.mainWnd = mainWnd;
        this.confPanel = confPanel;
        hibernateDataContext = mainWnd.getHibernateDataContext();
        inputDataSet = confPanel.getInputData();
        
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
        
        
        inputDataImage = ImageHelper.loadImage("inputdata.png");
        inputDataButton = new ToolButton(inputDataImage);
        inputDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        timer = new Timer(500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputDataWnd!=null) {
					if (!inputDataWnd.isInWnd()) {
						if (!inputDataWnd.inBtn&&!inputDataWnd.inTextField) {
							inputDataWnd.setVisible(false);
							inputDataWnd.dispose();
						}
						
					}
					
					
				}
				if (profileWnd!=null) {
					if (!profileWnd.isInWnd()) {
						if (!profileWnd.isInElement()) {
							profileWnd.setVisible(false);
							profileWnd.dispose();
						}
					}
				}
				
			}
		});
        
        
        
        
        inputDataButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		
        		if (profileWnd!=null) {
        			profileWnd.setVisible(false);
        			profileWnd.dispose();
				}
    			//Point p = inputDataButton.getLocation();
        		Point p= inputDataButton.getLocationOnScreen();
    			
    			inputDataWnd = new InputDataWnd(confPanel);
    			p.y=p.y+inputDataButton.getIcon().getIconHeight()+7;
    			inputDataWnd.setLocation(p);
    			//Point p = inputDataButton.getLocation();
        		//Point p= inputDataButton.getLocationOnScreen();
    			
                inputDataWnd.setVisible(true);
                
                if (timer.isRunning()) {
					timer.stop();
				}
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		/*if (inputDataWnd!=null) {
					inputDataWnd.setVisible(false);
					inputDataWnd.dispose();
					inputDataWnd = null;
				}*/
        		if (!timer.isRunning()) {
					timer.start();
				}
        	}
        });
        inputDataButton.addMouseMotionListener(new MouseMotionAdapter() {
            /*public void mouseMoved(final MouseEvent e) {
            	if (inputDataWnd==null) {
            		
                    inputDataWnd = new InputDataWnd(inputDataButton.getLocation());
                    inputDataWnd.setVisible(true);
				}else{
					
					//if ����
						//�̶�λ�ã�����Ƴ���ʧ
					//else
					if (prePoint!=null) {
						if (prePoint.getX()<e.getPoint().getX()) {	//����
							
						}else{
							inputDataWnd.setLocation(e.getPoint());
						}
						
					}
					
				}
                
                
            	prePoint = e.getPoint();
            }*/
        });
        inputDataButton.setToolTipText("ѡ���������ݼ�");
        
        
        profileImage = ImageHelper.loadImage("profile.png");
        profileButton = new ToolButton(profileImage);
        profileButton.setToolTipText("Profile");
        profileButton.addMouseListener(new MouseAdapter() {
        	
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
}
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		
        		if (inputDataWnd!=null) {
        			inputDataWnd.setVisible(false);
        			inputDataWnd.dispose();
				}
    			Point p = profileButton.getLocationOnScreen();
    			//System.out.println(p.x+"|"+p.y);
    			//System.out.println("cur " + e.getPoint().x + "|"+e.getPoint().y);
    			profileWnd = new ProfileWnd(confPanel);
    			p.y=p.y+profileButton.getIcon().getIconHeight()+7;
    			profileWnd.setLocation(p);
    			profileWnd.setVisible(true);
                if (timer.isRunning()) {
					timer.stop();
				}
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		/*if (inputDataWnd!=null) {
					inputDataWnd.setVisible(false);
					inputDataWnd.dispose();
					inputDataWnd = null;
				}*/
        		if (!timer.isRunning()) {
					timer.start();
				}
        	}
        });
        
        
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
            	
            	
            	GepAlgConfiguration gepAlgConfiguration=null;
				IndividualConfiguration individualConfiguration = null;
				GeneConfiguration geneConfiguration = null;
				OperatorConfiguration operatorConfiguration = null;
				inputDataSet = confPanel.getInputData();
				if (inputDataSet==null) {
					JOptionPane.showMessageDialog(mainWnd.frame,"Please input a input data first.");
					return;
				}
            	
            	
            	
            	/*�������*/
            	AccuracyModel accuracyModel = confPanel.contentPanel.accuracyController.getAccuracyModel();
            	Map<String, Boolean> isAccuracyFited = accuracyModel.getIsDataFitedMap();
            	Collection<Boolean> isAccuracyFitedValues = isAccuracyFited.values();
            	
            	
            	//if (isAccuracyFitedValues.contains(false)) {
            		//JOptionPane.showMessageDialog(mainWnd.frame,"��������ȷ");
				//}else{
					//JOptionPane.showMessageDialog(mainWnd.frame,accuracyModel.getMaxGeneration()+"|"+accuracyModel.getAccuracy()+"|"+accuracyModel.getSelectionRange());
				
					gepAlgConfiguration = new GepAlgConfiguration();
					
					gepAlgConfiguration.setName(confPanel.getGepAlgConfiguration().getName());
	            	gepAlgConfiguration.setMaxGeneration(accuracyModel.getMaxGeneration());
	            	gepAlgConfiguration.setSelectionRange(accuracyModel.getSelectionRange());
	            	gepAlgConfiguration.setAccuracy(accuracyModel.getAccuracy());
				
				//}
            	
            	
            	
            	/*�������*/
            	GeneModel geneModel = confPanel.contentPanel.geneController.getGeneModel();
            	Map<String, Boolean> isGeneFited = geneModel.getIsDataFitedMap();
            	Collection<Boolean> isGeneFitedValues = isGeneFited.values();
            	
            	
            	//if (isGeneFitedValues.contains(false)) {
            		//JOptionPane.showMessageDialog(mainWnd.frame,"��������ȷ");
				//}else{
					//JOptionPane.showMessageDialog(mainWnd.frame,accuracyModel.getMaxGeneration()+"|"+accuracyModel.getAccuracy()+"|"+accuracyModel.getSelectionRange());
					
					individualConfiguration = new IndividualConfiguration();
					geneConfiguration = new GeneConfiguration();
					individualConfiguration.setGeneConfiguration(geneConfiguration);
					
					individualConfiguration.setIndividualNumber(geneModel.getIndividualNumber());
					geneConfiguration.setNormalGeneNumber(geneModel.getNormalGeneNumber());
					geneConfiguration.setNormalGeneHeaderLength(geneModel.getNormalGeneHeaderLength());
					geneConfiguration.setFunctionUsed(geneModel.getFunctionList());
					
					
					
					
					if (geneModel.isUseHomeoticGene()) {	//ʹ��ͬԴ��������
						geneConfiguration.setHomeoticGeneNumber(geneModel.getHomeoticGeneNumber());
						geneConfiguration.setHomeoticGeneHeaderLength(geneModel.getHomeoticGeneHeaderLength());
						geneConfiguration.setUseHomeoticGene(true);
						/*JOptionPane.showMessageDialog(mainWnd.frame,geneModel.getIndividualNumber()+"|"
								+geneModel.getNormalGeneNumber()+"|"
								+geneModel.getNormalGeneHeaderLength()+"|"
								+geneModel.getHomeoticGeneNumber()+"|"
								+geneModel.getHomeoticGeneHeaderLength());
						*/
					}else{	//ʹ�ú�������
						
						geneConfiguration.setUseHomeoticGene(false);
						//JOptionPane.showMessageDialog(mainWnd.frame,"ʹ�ú�������");
						//geneConfiguration.setConnectionFunctionString(geneModel.getConnectionFunction().getName());
						geneConfiguration.setConnectionFunction(geneModel.getConnectionFunction());
						System.out.println(geneModel.getConnectionFunction()==null);
					}
					
				
				//}
            	
            	
            	
            	/*�������*/
            	OperatorModel operatorModel = confPanel.contentPanel.operatorController.getOperatorModel();
            	Map<String, Boolean> isOperatorFited = operatorModel.getIsDataFitedMap();
            	Collection<Boolean> isOperatorFitedValues = isOperatorFited.values();
            	
            	
            	//if (isOperatorFitedValues.contains(false)) {
            		//JOptionPane.showMessageDialog(mainWnd.frame,"Operator��������ȷ");
				//}else{
				
					operatorConfiguration = new OperatorConfiguration();
					operatorConfiguration.setMutateRate(operatorModel.getMutateRate());
					operatorConfiguration.setIsTransportRate(operatorModel.getIsTransportRate());
					operatorConfiguration.setIsElement(operatorModel.getIsElement());
					operatorConfiguration.setRisTransportRate(operatorModel.getRisTransportRate());
					operatorConfiguration.setRisElement(operatorModel.getRisElement());
					operatorConfiguration.setGeneTransportRate(operatorModel.getGeneTransportRate());
					operatorConfiguration.setOnePointRecombineRate(operatorModel.getOnePointRecombineRate());
					operatorConfiguration.setTwoPointRecombineRate(operatorModel.getTwoPointRecombineRate());
					operatorConfiguration.setGeneRecombineRate(operatorModel.getGeneRecombineRate());
			
					/*JOptionPane.showMessageDialog(
							mainWnd.frame,operatorModel.getMutateRate()+"|"
							+operatorModel.getIsTransportRate()+"|"
							+operatorModel.getIsElement()+"|"
							+operatorModel.getRisTransportRate()+"|"
							+operatorModel.getRisElement()+"|"
							+operatorModel.getGeneTransportRate()+"|"
							+operatorModel.getOnePointRecombineRate()+"|"
							+operatorModel.getTwoPointRecombineRate()+"|"
							+operatorModel.getGeneRecombineRate());
					Integer[] integer1 = operatorModel.getIsElement();
					for (int i = 0; i < integer1.length; i++) {
						System.out.print(integer1[i]+",");
					}
					System.out.println();
					Integer[] integer2 = operatorModel.getRisElement();
					for (int i = 0; i < integer2.length; i++) {
						System.out.print(integer2[i]+",");
					}*/
					
				
				//}
            	
            	
            	
            	gepAlgConfiguration.setOperatorConfiguration(operatorConfiguration);
            	gepAlgConfiguration.setIndividualConfiguration(individualConfiguration);
            	
            	GepConfigurationService configurationService=new GepConfigurationService(hibernateDataContext);
            	
            	gepAlgConfiguration=configurationService.processConf(gepAlgConfiguration, inputDataSet);
            	
            	AlgOutputService algOutputService=new AlgOutputService(hibernateDataContext);
            	
            	/**
            	 * �����Ƿ�д�����ݿ�
            	 */
            	algOutputService.setWriteToDB(false);
            	
            	
            	
            	Future<GepAlgRun> result=algOutputService.run(gepAlgConfiguration, new AlgCpuRunStep(), inputDataSet);
            	
            	try {
            		gepAlgRun = result.get();
            		
            		OutputPictureController ouputPictureController = confPanel.contentPanel.getOutputPictureController();
            		ouputPictureController.setGepAlgRun(gepAlgRun);	//���￪ʼ��ͼ���޸�ģ����Ӧ��UI����
            		
            		OutputPicturePanel outputPicturePanel = confPanel.contentPanel.outputPicturePanel;
            		Dimension di = outputPicturePanel.getPreferredSize();
            		outputPicturePanel.setPreferredSize(new Dimension(di.width,confPanel.contentPanel.getHeight()-25));
            		/*outputPicturePanel.tooltip_PicturePanel.setText("  Here shows the Fitting Curve Gragh and Evolution Gragh,You can "
            				+"do more by click the link label.");	//��ʵ�Ǹ�Label*/
            		/*outputPicturePanel.tooltip_PicturePanel.setText("<html><body>&nbsp;&nbsp;Run Time: "+gepAlgRun.getPeriod()+
            				"&nbsp;&nbsp;&nbsp;&nbsp;Total Generation: "+gepAlgRun.getCurrentPopulation().getGenerationNum()+ "<br/>"+
            				"&nbsp;&nbsp;Result&Expression:"
            				+
            				gepAlgRun.getCurrentPopulation().toExprString()
            				
            				+"</body></html>");*/
            		outputPicturePanel.tooltip_PicturePanel.setText("Run Time: " +gepAlgRun.getPeriod() +
            				"\nTotal Generation: "+gepAlgRun.getCurrentPopulation().getGenerationNum()+
            				"\nResult&Expression:"+ gepAlgRun.getCurrentPopulation().toExprString());
            				
            		//outputPicturePanel.tooltip_PicturePanel.setPreferredSize(new Dimension(60,80));
            		//outputPicturePanel.tooltip_PicturePanel.setMaximumSize(new Dimension(60,80));
            		
            		
            		outputPicturePanel.gl_InfoPanel.setVerticalGroup(
            				outputPicturePanel.gl_InfoPanel.createParallelGroup(Alignment.LEADING)
            				.addGroup(outputPicturePanel.gl_InfoPanel.createSequentialGroup()
            					.addComponent(outputPicturePanel.tooltip_PicturePanel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
            					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            		);
            		//outputPicturePanel.tooltip_PicturePanel.setSize(outputPicturePanel.tooltip_PicturePanel.getWidth(), outputPicturePanel.tooltip_PicturePanel.getHeight()+40);
				} catch (InterruptedException | ExecutionException e1) {
					e1.printStackTrace();
				}
            	
            	
            	/**
            	 * ����Welcome����
            	 */
            	//mainWnd.frame.mainTabbedPane.welcomePanel.hp.projectTable.reflesh();
            	
            	
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
        add(inputDataButton);
        add(profileButton);
        /*addSeparator();
        add(cutButton);
        add(copyButton);
        add(pasteButton);
        add(clearButton);
        */
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

	public GepAlgRun getGepAlgRun() {
		return gepAlgRun;
	}

	public void setGepAlgRun(GepAlgRun gepAlgRun) {
		this.gepAlgRun = gepAlgRun;
	}
}
