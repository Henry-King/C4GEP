package ui;

import java.awt.Color;

import javax.swing.JFrame;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.chart.LineChart;

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
		
		//ÿһ�������Ƿ���Ҫ��ʾ���
		lineChart.setInflexionVisible(false);
		Element a = new Node();
		a.setName("A");
		a.putChartColor(Color.GREEN);
		//���ñ�ǵ���ʾ��ʽ.
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
