/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import ui.conf.controller.AccuracyController;
import ui.conf.controller.GeneController;
import ui.conf.controller.OperatorController;
import ui.conf.model.AccuracyModel;
import ui.conf.model.GeneModel;
import ui.conf.model.OperatorModel;
import ui.conf.view.ConfPanel;
import ui.conf.view.TypeHelper.TableType;

import com.jtattoo.plaf.JTattooUtilities;

import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.service.algConfiguration.GepConfigurationService;

/**
 *
 * @author  Michael Hagen
 */
public class TablePanel extends JPanel {
	private ArrayList<String> colNames = new ArrayList<String>();
    private ArrayList<ArrayList> dataList = new ArrayList<ArrayList>();
	
    private List<GepAlgConfiguration> modelList;
    
    private MainWnd mainWnd;
    private ConfPanel confPanel;
    private GepAlgCfgInfoWnd cfgInfoWnd;
    
    private Point prePoint = null;
    private TableType tableType;
    
    public TablePanel(MainWnd mainWnd,TableType tableType) {
        super(new BorderLayout());
        this.mainWnd = mainWnd;
        this.tableType = tableType;
        init();
    }
    
    public TablePanel(ConfPanel confPanel,TableType tableType) {
        super(new BorderLayout());
        this.confPanel = confPanel;
        this.mainWnd = confPanel.mainWnd;
        this.tableType = tableType;
        init();
    }
    
    public  void reflesh(){
    	GepConfigurationService gepConfigurationService = new GepConfigurationService(mainWnd.getHibernateDataContext());
        modelList.clear();
        
        for (GepAlgConfiguration gac : gepConfigurationService.getAllGepAlgConfiguration()) {
        	modelList.add(gac);
		}
        Collections.reverse(modelList);
    }

    private void init() {
    	GepConfigurationService gepConfigurationService = new GepConfigurationService(mainWnd.getHibernateDataContext());
        modelList = gepConfigurationService.getAllGepAlgConfiguration();
        Collections.reverse(modelList);
        setName("Table");
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        initModel();
        final JTable myTable = new MyTable(new MyTableModel());
        
        for (int i = 0; i < myTable.getColumnCount(); i++) {
        	myTable.getColumn(myTable.getColumnName(i)).setCellRenderer(new MyTableCellRenderer());
        }
        
        myTable.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//JOptionPane.showMessageDialog(mainWnd.frame,"test click");
        		GepAlgConfiguration gepAlgConfiguration = (GepAlgConfiguration)modelList.get(myTable.rowAtPoint(e.getPoint()));
        		//System.out.println("here "+myTable.rowAtPoint(e.getPoint()));
        		
        		
        		if (JTattooUtilities.getJavaVersion() >= 1.6) {
        			
        			AccuracyController accuracyController = null;
               	 	GeneController geneController = null;
               	 	OperatorController operatorController = null;
        			
        			
        			if (tableType.equals(TableType.CreateNewProfileTable)) {
        				//Open Window
	            		JTabbedPane jtp = mainWnd.frame.mainTabbedPane;
	            		String title = gepAlgConfiguration.getName().toString();
	                    int tabCount = jtp.getTabCount();
	                    ConfPanel newConfPanel = new ConfPanel(mainWnd);
	                    VTextIcon newVTextIcon=new VTextIcon(newConfPanel, title,VTextIcon.ROTATE_LEFT);
	                    jtp.addTab(null,newVTextIcon, newConfPanel, null);
	                    //jtp.addTab("Tab", null,welcomeVTextIcon,null);
	                   
	                    //CloseableTabComponent ctc = new CloseableTabComponent(jtp,title);
	                    
	                    jtp.setSelectedIndex(tabCount);
	                    //jtp.setTabComponentAt(tabCount, ctc);
	                    jtp.setToolTipTextAt(tabCount, "This is project " + (tabCount + 1) + "  "+title);
	                    
	                     accuracyController = newConfPanel.contentPanel.getAccuracyController();
	                     geneController = newConfPanel.contentPanel.getGeneController();
	                     operatorController = newConfPanel.contentPanel.getOperatorController();
	                     newConfPanel.setGepAlgConfiguration(gepAlgConfiguration);
	                     newConfPanel.outputStatusLabel.setText("It's your history profile:" + title +"  You can load a input data later or modify this profile.");
					}else if(tableType.equals(TableType.LoadProfileTable)){
						 accuracyController = confPanel.contentPanel.getAccuracyController();
	                     geneController = confPanel.contentPanel.getGeneController();
	                     operatorController = confPanel.contentPanel.getOperatorController();
	                     confPanel.setGepAlgConfiguration(gepAlgConfiguration);
	                     //confPanel.outputStatusLabel.setText(".");
					}else{
						
					}
                    
                    //initial model
                	 AccuracyModel accuracyModel = accuracyController.getAccuracyModel();
                	 accuracyModel.setMaxGeneration(gepAlgConfiguration.getMaxGeneration());
                	 accuracyModel.setSelectionRange(gepAlgConfiguration.getSelectionRange());
                	 accuracyModel.setAccuracy(gepAlgConfiguration.getAccuracy());
                	 accuracyModel.changeModel();
                	 GeneConfiguration geneConfiguration = gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration();
                	 
                	 
                	 GeneModel geneModel = geneController.getGeneModel();
                	 geneModel.setIndividualNumber(gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber());
                	 geneModel.setNormalGeneNumber(geneConfiguration.getNormalGeneNumber());
                	 geneModel.setNormalGeneHeaderLength(geneConfiguration.getNormalGeneHeaderLength());
                	 geneModel.setFunctionList(geneConfiguration.getFunctionUsed());
             		if (geneConfiguration.getUseHomeoticGene()) {
             			geneModel.setUseHomeoticGene(true);
             			geneModel.setHomeoticGeneNumber(geneConfiguration.getHomeoticGeneNumber());
             			geneModel.setHomeoticGeneHeaderLength(geneConfiguration.getHomeoticGeneHeaderLength());
             		}
             		else {
             			geneModel.setUseHomeoticGene(false);
             			geneModel.setConnectionFunction(geneConfiguration.getConnectionFunction());
             			//System.out.println(geneConfiguration.getConnectionFunctionString());
             		}
             		geneModel.changeModel();
             		
             		OperatorConfiguration operatorConfiguration = gepAlgConfiguration.getOperatorConfiguration();
             		OperatorModel operatorModel = operatorController.getOperatorModel();
             		operatorModel.setMutateRate(operatorConfiguration.getMutateRate());
             		operatorModel.setIsTransportRate(operatorConfiguration.getIsTransportRate());
            		operatorModel.setIsElement(operatorConfiguration.getIsElement());
            		operatorModel.setRisTransportRate(operatorConfiguration.getRisTransportRate());
            		operatorModel.setRisElement(operatorConfiguration.getRisElement());
            		operatorModel.setGeneTransportRate(operatorConfiguration.getGeneTransportRate());
            		operatorModel.setOnePointRecombineRate(operatorConfiguration.getOnePointRecombineRate());
            		operatorModel.setTwoPointRecombineRate(operatorConfiguration.getTwoPointRecombineRate());
            		operatorModel.setGeneRecombineRate(operatorConfiguration.getGeneRecombineRate());
            		operatorModel.changeModel();
        		}
        		
        		
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if (cfgInfoWnd!=null) {
        			cfgInfoWnd.setVisible(true);
        			cfgInfoWnd.setLocation(e.getPoint());
				}
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		if (cfgInfoWnd!=null) {
        			
        			if (prePoint!=null) {
						if (prePoint.getX()<e.getPoint().getX()) {	//����
							
						}else{
							cfgInfoWnd.setVisible(false);
		        			cfgInfoWnd.dispose();
						}
						
					}
        			
				}
        	}
        });
        myTable.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
            	
            	
            	
            	if (cfgInfoWnd==null) {
            		System.out.println(myTable.rowAtPoint(e.getPoint()));
                    GepAlgConfiguration gepAlgConfiguration = (GepAlgConfiguration)modelList.get(myTable.rowAtPoint(e.getPoint()));
                    
                    cfgInfoWnd = new GepAlgCfgInfoWnd(gepAlgConfiguration, e.getPoint());
                    cfgInfoWnd.setVisible(true);
				}else{
					
					//if ����
						//�̶�λ�ã�����Ƴ���ʧ
					//else
					if (prePoint!=null) {
						if (prePoint.getX()<e.getPoint().getX()) {	//����
							
						}else{
							cfgInfoWnd.setLocation(e.getPoint());
						}
						
					}
					
				}
                
                
            	prePoint = e.getPoint();
            }
        });
        
        
//        TableColumn tableCol = myTable.getColumnModel().getColumn(5);
//        tableCol.setCellRenderer(new CheckBoxRenderer());
//        tableCol.setPreferredWidth(30);
        
        JScrollPane scrollPane = new JScrollPane(myTable);
        
        
        add(scrollPane, BorderLayout.CENTER);

       
    }

    
private void initModel() {
    	
        colNames.add("Id");
        colNames.add("Name");
        colNames.add("MaxGeneration");
        colNames.add("SelectionRange");
        colNames.add("Accuracy");

        
        
        
    }
//---------------------------------------------------------------------------------------
    public class MyTable extends JTable {

        private int rolloverRowIndex = -1;

        public MyTable(TableModel model) {
            super(model);
            
            
            
            rowHeight = 18;
            
            RolloverListener listener = new RolloverListener();
            addMouseMotionListener(listener);
            addMouseListener(listener);
        }
        
        

        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);
            Color foreground = getForeground();
            Color background = getBackground();
            if (isRowSelected(row)) {
                foreground = getSelectionForeground();
                background = getSelectionBackground();
            }
            else if (row == rolloverRowIndex) {
                foreground = getSelectionForeground();
                background = com.jtattoo.plaf.ColorHelper.brighter(getSelectionBackground(), 40);
            }
            else if (row % 2 == 0) {
                background = com.jtattoo.plaf.ColorHelper.brighter(getParent().getBackground(), 20);
            }
            c.setForeground(foreground);
            c.setBackground(background);
            return c;
        }

        private class RolloverListener extends MouseInputAdapter {

            public void mouseExited(MouseEvent e) {
                rolloverRowIndex = -1;
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != rolloverRowIndex) {
                    rolloverRowIndex = row;
                    repaint();
                }
            }
        }
    }

//---------------------------------------------------------------------------------------
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Project Name", "Last Open Date", "FT", "Status"};
        private Object[][] data = {
            {"My First Project", "2012-9-19", "����ʽ", "OK"},
            {"My Second Project", "2012-9-19", "����ʽ", "OK"}
        };

        public int getColumnCount() {
            return colNames.size();
        }

        public String getColumnName(int index) {
            return (String)colNames.get(index);
        }

        public int getRowCount() {
            return modelList.size();
        }

        public Object getValueAt(int rowIndex, int colIndex) {
            if (rowIndex < modelList.size()) {
                @SuppressWarnings("unchecked")
                GepAlgConfiguration gepAlgConfiguration = (GepAlgConfiguration)modelList.get(rowIndex);
                
                ArrayList rowData = new ArrayList();
                rowData.add(gepAlgConfiguration.getId());
                rowData.add(gepAlgConfiguration.getName());
                rowData.add(gepAlgConfiguration.getMaxGeneration());
                rowData.add(gepAlgConfiguration.getSelectionRange());
                rowData.add(gepAlgConfiguration.getAccuracy());
                
                if (colIndex < rowData.size()) {
                    return rowData.get(colIndex);
                }
            }
            return "ERROR";
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell. If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's editable.
         */
        /*public boolean isCellEditable(int row, int col) {
            return true;
        }*/


    }

    class CheckBoxRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable jTable, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {
            JCheckBox checkBox = new JCheckBox("");
            checkBox.setOpaque(true);
            checkBox.setForeground(jTable.getForeground());
            checkBox.setBackground(jTable.getBackground());
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            if (isSelected) {
                checkBox.setForeground(jTable.getSelectionForeground());
                Color bc = new Color(jTable.getSelectionBackground().getRGB());
                checkBox.setBackground(bc);
            }
            if (obj instanceof Boolean) {
                checkBox.setSelected(((Boolean)obj).booleanValue());
            }
            return checkBox;
        }
    }
    
    class MyTableCellRenderer extends DefaultTableCellRenderer {  
        public MyTableCellRenderer(){  
            setHorizontalAlignment(JLabel.CENTER);  
        }  
    }  
}
