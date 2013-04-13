package ui.conf.view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import twaver.Element;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.chart.LineChart;


public class OutputPictureDetail extends JPanel{
	public static final Font FONT_9_PLAIN = new Font("Tahoma", Font.PLAIN, 9);
	
	
	
	public LineChart rangeChart;
	public OutputChart historyChart;
	public String name;
	private OutputPictureDetail ouputPictureDetail;
	
	
	public OutputPictureDetail(OutputChart outputChart) {
		//this.setName("OutputPictureDetail");
		ouputPictureDetail = this;
		
		
		/*Element element1 = new twaver.Node();
        Element element2 = new twaver.Node();
        Element detail1 = new twaver.Node();
        Element detail2 = new twaver.Node();
		
		
		
        element1.setName("Open");
        element2.setName("High");
        detail1.setName("Low");
        detail2.setName("Close");*/
		
		
		
		
		
		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel info_Panel = new JPanel();
		mainPanel.add(info_Panel);
		info_Panel.setLayout(new BorderLayout(0, 0));
		
		JLabel info_Label = new JLabel("New label");
		info_Panel.add(info_Label, BorderLayout.NORTH);
		
		JPanel info_ToolPanel = new JPanel();
		info_Panel.add(info_ToolPanel, BorderLayout.NORTH);
		
		JPanel PicturePanel = new JPanel();
		info_Panel.add(PicturePanel, BorderLayout.CENTER);
		PicturePanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		rangeChart = new LineChart();
        rangeChart.setEnableXTranslate(false);
        rangeChart.setEnableXZoom(false);
        rangeChart.setXScaleTextSpanCount(20);
        rangeChart.setXScaleTextVisible(true);
        rangeChart.setLegendFont(FONT_9_PLAIN);
        rangeChart.setXScaleTextOrientation(TWaverConst.LABEL_ORIENTATION_RIGHT);
        rangeChart.setYScaleTextVisible(true);
        rangeChart.setXScaleTextVisible(true);
        rangeChart.setGradient(false);
        rangeChart.setOpaque(false);
        rangeChart.setLineType(TWaverConst.LINE_TYPE_AREA);
        
        TDataBox tDataBox = outputChart.getDataBox();
        List<Element> elements = tDataBox.getAllElements();
        Element element1 = elements.get(0).copy();
        Element element2 = elements.get(1).copy();
        
        historyChart = new OutputChart(rangeChart, element1.getChartValues().size()) {
            @Override
            protected String getFormatedYScaleText(double value) {
                if (value == 0) {
                    return "";
                }
                return "$" + NumberFormat.getNumberInstance().format(value);
            }
        };
        
        historyChart.setLineType(TWaverConst.LINE_TYPE_AREA);
        
        historyChart.setStartIndex(0);
        historyChart.setEndIndex((int) element2.getChartValues().size());
        
        //historyChart.initialRangePanel(rangeChart, (int) historyChart.getEndX());
        
        historyChart.setXScaleTextSpanCount(30);
        historyChart.setYScaleValueGap(200);
        historyChart.getDataBox().addElement(element1);
        historyChart.getDataBox().addElement(element2);
        historyChart.setOpaque(false);
        historyChart.setXScaleTextFont(FONT_9_PLAIN);
        historyChart.setYScaleTextFont(FONT_9_PLAIN);
        historyChart.setYScaleTextVisible(true);
        historyChart.setXScaleTextVisible(true);//
        historyChart.setGradient(false);
        
        
        rangeChart.setXScaleTextList(historyChart.getXScaleTextList());
        TDataBox box = rangeChart.getDataBox();
        
        
        for (int i = 0; i < elements.size(); i++) {
        	box.addElement(elements.get(i));
		}
        /*for (int i = 0; i < tDataBox.size(); i++) {
        	box.addElement(tDataBox.getElementByID(i));
		}*/

        PicturePanel.add(historyChart);
        PicturePanel.add(rangeChart);
        
        
        
        
        
        
        
        
        
        
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1664524211949263771L;
}
