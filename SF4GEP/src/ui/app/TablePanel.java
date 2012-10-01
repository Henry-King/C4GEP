/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;

/**
 *
 * @author  Michael Hagen
 */
public class TablePanel extends JPanel {

    public TablePanel() {
        super(new BorderLayout());
        

        init();
    }

    private void init() {
        setName("Table");
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JTable myTable = new MyTable(new MyTableModel());
        
        
        JScrollPane scrollPane = new JScrollPane(myTable);
        add(scrollPane, BorderLayout.CENTER);

       
    }

//---------------------------------------------------------------------------------------
    public class MyTable extends JTable {

        private int rolloverRowIndex = -1;

        public MyTable(TableModel model) {
            super(model);
            rowHeight = 15;
            
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
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
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
        public boolean isCellEditable(int row, int col) {
            return true;
        }


    }

}
