package ui;

import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import twaver.*;
import twaver.chart.LineChart;
import ui.customized.HistoryChart;

public class Chart {

	
	
	
	public Chart() {
		TDataBox box = new TDataBox();

		LineChart lineChart = new LineChart(box);
		lineChart.setEnableXTranslate(false);
		lineChart.setEnableYTranslate(false);
		lineChart.setLineType(TWaverConst.LINE_TYPE_DEFAULT);
		lineChart.setYAxisVisible(true);
		lineChart.setYScaleTextVisible(true);
		lineChart.setXAxisVisible(true);
		lineChart.setXScaleTextVisible(true);
		lineChart.setLegendLayout(TWaverConst.LEGEND_LAYOUT_VERTICAL);
		
		//每一个点上是否需要显示标记
		lineChart.setInflexionVisible(false);
		Element a = new Node();
		a.setName("A");
		a.putChartColor(Color.GREEN);
		//设置标记的显示样式.
		a.putChartInflexionStyle(TWaverConst.LINE_TYPE_AREA);
		box.addElement(a);

		Element b = new Node();
		b.setName("B");
		b.putChartColor(Color.RED);
		b.putChartInflexionStyle(TWaverConst.LINE_TYPE_AREA);
		box.addElement(b);

		for(int i=0;i<20;i++){
		 lineChart.addXScaleText(""+i);
		 a.addChartValue(Math.random()*50);
		 b.addChartValue(Math.random()*50);
		}

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(lineChart);
		frame.setSize(500, 400);
		TWaverUtil.centerWindow(frame);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) {
		new Chart();
	}
	
	
	
	
	
	
	 
	    
}
