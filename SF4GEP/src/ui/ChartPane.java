package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChartPane extends JPanel {

    private JLabel lbTitle = new JLabel();
    private boolean expanded = false;
    private JComponent chart = null;

    public ChartPane() {
        init();
    }

    private void init() {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(this.lbTitle, BorderLayout.NORTH);
        
        lbTitle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
        lbTitle.setForeground(new Color(90, 90, 90));
        lbTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public void setChart(JComponent chart) {
        if (this.chart != null) {
            this.remove(this.chart);
        }
        this.chart = chart;
        this.add(chart, BorderLayout.CENTER);
    }

    public JComponent getChart() {
        return chart;
    }

    public void setTitle(String title) {
        this.lbTitle.setText(title);
    }

    public boolean isExpanded() {
        return expanded;
    }

}
