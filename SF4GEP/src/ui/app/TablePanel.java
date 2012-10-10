/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;

import ui.conf.NewGEPDialog.CloseableTabComponent;
import ui.conf.controller.AccuracyController;
import ui.conf.controller.GeneController;
import ui.conf.controller.OperatorController;
import ui.conf.model.AccuracyModel;
import ui.conf.model.GeneModel;
import ui.conf.model.OperatorModel;
import ui.conf.view.ConfPanel;

import com.jtattoo.plaf.JTattooUtilities;

import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;
import domain.service.algConfiguration.GepConfigurationService;
import java.awt.event.MouseAdapter;

/**
 *
 * @author  Michael Hagen
 */
public class TablePanel extends JPanel {
	private ArrayList<String> colNames = new ArrayList<String>();
    private ArrayList<ArrayList> dataList = new ArrayList<ArrayList>();
	
    private List<GepAlgConfiguration> modelList;
    
    private MainWnd mainWnd;
    private GepAlgCfgInfoWnd cfgInfoWnd;
    
    private Point prePoint = null;
    
    public TablePanel(MainWnd mainWnd) {
        super(new BorderLayout());
        
        this.mainWnd = mainWnd;
        GepConfigurationService gepConfigurationService = new GepConfigurationService(mainWnd.getHibernateDataContext());
        modelList = gepConfigurationService.getAllGepAlgConfiguration();

        init();
    }

    private void init() {
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
        		
        		
        		if (JTattooUtilities.getJavaVersion() >= 1.6) {
        			
        			//Open Window
            		JTabbedPane jtp = mainWnd.frame.mainTabbedPane;
            		String title = gepAlgConfiguration.getName().toString();
                    int tabCount = jtp.getTabCount();
                    ConfPanel newConfPanel = new ConfPanel(mainWnd);
                    VTextIcon newVTextIcon=new VTextIcon(newConfPanel, title,VTextIcon.ROTATE_LEFT);
                    jtp.addTab(null,newVTextIcon, newConfPanel, null);
                    //jtp.addTab("Tab", null,welcomeVTextIcon,null);
                   
                    CloseableTabComponent ctc = new CloseableTabComponent(jtp,title);
                    
                    jtp.setSelectedIndex(tabCount);
                    jtp.setTabComponentAt(tabCount, ctc);
                    jtp.setToolTipTextAt(tabCount, "This is project " + (tabCount + 1) + "  "+title);
                    
                    
                    //initial model
                	 AccuracyController accuracyController = newConfPanel.contentPanel.getAccuracyController();
                	 GeneController geneController = newConfPanel.contentPanel.getGeneController();
                	 OperatorController operatorController = newConfPanel.contentPanel.getOperatorController();
                	 
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
						if (prePoint.getX()<e.getPoint().getX()) {	//右移
							
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
					
					//if 右移
						//固定位置，鼠标移出消失
					//else
					if (prePoint!=null) {
						if (prePoint.getX()<e.getPoint().getX()) {	//右移
							
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
            {"My First Project", "2012-9-19", "主从式", "OK"},
            {"My Second Project", "2012-9-19", "串行式", "OK"}
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
