package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import twaver.Element;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.chart.LineChart;
import ui.customized.HistoryChart;

public class test extends JFrame {
	
	public static final Font FONT_9_PLAIN = new Font("Tahoma", Font.PLAIN, 9);
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		add(createStockChart(), BorderLayout.CENTER);
		
		//contentPane.add(panel, BorderLayout.CENTER);
	}
	
	public static JComponent createStockChart() {
        JPanel stockPane = new JPanel();
        stockPane.setOpaque(false);
        List dates = new ArrayList();

        Element open = new twaver.Node();
        Element high = new twaver.Node();
        Element low = new twaver.Node();
        Element history = new twaver.Node();
        Element volume = new twaver.Node();
        Element close = new twaver.Node();

        try {
            InputStream in = TWaverUtil.getInputStream("/ui/google.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.split("\\,");
                String date = ss[0];
                int index = date.lastIndexOf("-");
                date = date.substring(0, index);
                dates.add(0, date);
                
                
                open.getChartValues().add(0, Double.valueOf(ss[1]));
                history.getChartValues().add(0, Double.valueOf(ss[4]));	
                volume.getChartValues().add(0, Double.valueOf(ss[5]));
                
                
                
                high.getChartValues().add(0, Double.valueOf(ss[2]));	//
                low.getChartValues().add(0, Double.valueOf(ss[3]));	//
                close.getChartValues().add(0, Double.valueOf(ss[4]));	//
                //2007-12-20    685.83    691		680.61		689.69		4422200
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        open.setName("Open");
        high.setName("High");
        low.setName("Low");
        history.setName("Close");
        volume.setName("Volume");
        close.setName("Close");

        open.putChartColor(Color.GREEN);
        high.putChartColor(Color.RED);
        low.putChartColor(Color.YELLOW);
        history.putChartColor(Color.BLUE);
        volume.putChartColor(Color.BLUE);
        close.putChartColor(Color.BLUE);

        final LineChart rangeChart = new LineChart();
        rangeChart.setEnableXTranslate(false);
        rangeChart.setEnableXZoom(false);
        rangeChart.setXScaleTextSpanCount(20);
        rangeChart.setXScaleTextVisible(false);
        rangeChart.setLegendFont(FONT_9_PLAIN);
        rangeChart.setXScaleTextOrientation(TWaverConst.LABEL_ORIENTATION_RIGHT);
        rangeChart.setOpaque(false);

        
        HistoryChart historyChart = new HistoryChart(rangeChart, close.getChartValues().size()) {
            @Override
            protected String getFormatedYScaleText(double value) {
                if (value == 0) {
                    return "";
                }
                return "$" + NumberFormat.getNumberInstance().format(value);
            }
        };
        historyChart.setLineType(TWaverConst.LINE_TYPE_DEFAULT);
        
        historyChart.setXScaleTextList(dates);
        historyChart.setXScaleTextSpanCount(30);
        historyChart.setYScaleValueGap(200);
        historyChart.getDataBox().addElement(history);
        historyChart.setOpaque(false);
        historyChart.setXScaleTextFont(FONT_9_PLAIN);
        historyChart.setYScaleTextFont(FONT_9_PLAIN);

        rangeChart.setXScaleTextList(dates);
        TDataBox box = rangeChart.getDataBox();
        box.addElement(low);
        box.addElement(close);
        box.addElement(high);

        GridLayout layout = new GridLayout(1, 2);
        layout.setVgap(10);
        stockPane.setLayout(layout);
        stockPane.add(historyChart);
        stockPane.add(rangeChart);

        return stockPane;
    }

}
