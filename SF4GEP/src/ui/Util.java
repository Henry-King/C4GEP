package ui;

import ui.customized.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import twaver.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.TaskAdapter;
import twaver.TaskScheduler;
import twaver.chart.BarChart;
import twaver.chart.Bubble;
import twaver.chart.BubbleChart;
import twaver.chart.DialChart;
import twaver.chart.LineChart;
import twaver.chart.Marker;
import twaver.chart.PercentChart;
import twaver.chart.PieChart;
import twaver.chart.RadarChart;

public class Util {

    public static final Font FONT_7_PLAIN = new Font("Tahoma", Font.PLAIN, 7);
    public static final Font FONT_9_PLAIN = new Font("Tahoma", Font.PLAIN, 9);
    public static final Font FONT_11_BOLD = new Font("Tahoma", Font.BOLD, 11);
    public static final Font FONT_11_PLAIN = new Font("Tahoma", Font.PLAIN, 11);
    public static final Color FONT_COLOR = new Color(65, 77, 93);

    public static JComponent createFunnelPane() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        pane.setOpaque(false);
        pane.add(createPipelineChart(true, true, true));
        pane.add(createPipelineChart(false, true, false));

        return pane;
    }

    public static JComponent createPipelineChart(boolean useGap, boolean hollow, boolean alpha) {
        FunnelChart chart = new FunnelChart() {

            @Override
            protected String getFormatedText(Element element, double value, int index) {
                String text = super.getFormatedText(element, value, index);
                return element.getName() + " - " + text;
            }
        };
        chart.setUseGap(useGap);
        chart.setUseHollow(hollow);
        String[] names = {
            "Website",
            "Eval",
            "Contacts",
            "Leads",
            "Quotes",
            "Closes",};
        for (int i = 0; i < names.length; i++) {
            twaver.Node node = new twaver.Node();
            node.putChartValue(1000 + TWaverUtil.getRandomInt((i + 1) * 500));
            node.setName(names[names.length - i - 1]);
            if (alpha) {
                node.putChartColor(TWaverUtil.getRandomAlphaColor());
            } else {
                node.putChartColor(createRandomColor());
            }
            chart.getDataBox().addElement(node);
        }
        chart.getLegendPane().setVisible(false);
        chart.setOpaque(false);
        chart.zoomOut();
        chart.zoomOut();
        chart.setXTranslate(10);
        chart.setValueTextColor(FONT_COLOR);
        chart.setValueTextFont(FONT_9_PLAIN);
        return chart;
    }

    private static Color createRandomColor() {
        int seed = 150;
        return new Color(TWaverUtil.getRandomInt(seed),
                TWaverUtil.getRandomInt(seed),
                TWaverUtil.getRandomInt(seed));
    }

    public static JComponent createPercentChart() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        for (int i = 0; i < 2; i++) {
            TDataBox box = new TDataBox();
            PercentChart chart = new PercentChart(box);

            chart.setGradient(true);
            chart.setPercentLabelFont(FONT_9_PLAIN);
            chart.setPercentLabelColor(FONT_COLOR);
            chart.setSegmentCount(20);
            chart.setMarkerStartPosition(TWaverConst.PERCENT_MARKER_START_WITH_BOTTOM);
            chart.setOutlineColor(new Color(0, 0, 0, 0));
            chart.setOpaque(false);

            addHealthElement(box, "CPU", 70, 50, 90, TWaverConst.PERCENT_STYLE_SEGMENT).setUserObject(Boolean.TRUE);
            addHealthElement(box, "Memory", 70, 60, 80, TWaverConst.PERCENT_STYLE_PLANE).setUserObject(Boolean.FALSE);
            addHealthElement(box, "Storage", 64, 30, 60, TWaverConst.PERCENT_STYLE_SOLID).setUserObject(Boolean.TRUE);

            pane.add(chart);
        }
        pane.setOpaque(false);
        return pane;
    }

    private static Element addHealthElement(TDataBox box, String name,
            double current, int marker1, int marker2, int style) {
        List markers = new ArrayList();
        Marker marker = new Marker();
        marker.setColor(TWaverUtil.getRandomColor());
        marker.setTextColor(FONT_COLOR);
        marker.setValue(0);
        marker.setText("0%");
        markers.add(marker);

        marker = new Marker();
        marker.setColor(TWaverUtil.getRandomColor());
        marker.setTextColor(FONT_COLOR);
        marker.setValue(marker1);
        marker.setText(marker1 + "%");
        markers.add(marker);

        marker = new Marker();
        marker.setColor(TWaverUtil.getRandomColor());
        marker.setTextColor(FONT_COLOR);
        marker.setValue(marker2);
        marker.setText(marker2 + "%");
        markers.add(marker);

        Element element = new twaver.Node();
        element.putChartPercentSpareFill(true);
        element.setName(name);
        element.putChartValue(current);
        element.putChartPercentStyle(style);
        element.putChartMarkers(markers);

        box.addElement(element);
        return element;
    }

    public static JComponent createAdoptionChart() {
        TDataBox box = new TDataBox();
        BarChart chart = new BarChart(box);

        chart.setYAxisText("<html>North American and European IT<br>decision-makers at enterprises and SMBs</html>");
        chart.setYAxisTextFont(FONT_9_PLAIN);
        chart.setValueTextFont(FONT_9_PLAIN);
        chart.setBarType(TWaverConst.BAR_TYPE_PERCENT);
        chart.addXScaleText("S");
        chart.addXScaleText("MS");
        chart.addXScaleText("ML");
        chart.addXScaleText("L");
        chart.addXScaleText("VL");
        chart.addXScaleText("VVL");
        chart.setValueTextCenter(true);
        chart.setXScaleTextVisible(false);
        chart.setValueTextColor(Color.white);
        chart.setYAxisTextColor(FONT_COLOR);
        chart.setLegendFont(FONT_9_PLAIN);
        chart.setOpaque(false);
        addAdoptionElement("Not Considering", 0.68, 0.58, 0.52, 0.44, 0.44, 0.37, new Color(188, 0, 80), box);
        addAdoptionElement("Considering Only", 0.12, 0.16, 0.15, 0.15, 0.16, 0.12, new Color(252, 197, 4), box);
        addAdoptionElement("Buying", 0.2, 0.26, 0.33, 0.41, 0.40, 0.51, new Color(136, 125, 187), box);

        return chart;
    }

    private static void addAdoptionElement(String name, double v1, double v2,
            double v3, double v4, double v5, double v6, Color color, TDataBox box) {
        Element node = new twaver.Node();
        node.putChartColor(color);
        node.setName(name);
        node.addChartValue(v1);
        node.addChartValue(v2);
        node.addChartValue(v3);
        node.addChartValue(v4);
        node.addChartValue(v5);
        node.addChartValue(v6);
        box.addElement(node);
    }

    public static JComponent createClockChart() {
        final twaver.Element hour = new twaver.Node();
        final twaver.Element minute = new twaver.Node();
        final twaver.Element second = new twaver.Node();

        DialChart chart = new DialChart() {

            @Override
            public String getToolTipText(Element element) {
                double value = element.getChartValue();
                if (element == hour) {
                    return element.getName() + ":" + (int) (value);
                }
                return element.getName() + ":" + (int) (value * 5);
            }
        };

        chart.setStartAngle(-90);
        chart.setEndAngle(270);
        chart.setMaxValue(12);
        chart.setScaleMajorCount(12);
        chart.setScaleMinorCount(4);
        chart.setScaleInside(true);
        chart.setScaleLength(12);
        chart.setScaleStyle(TWaverConst.DIAL_SCALE_STYLE_LINE);
        chart.setBallSize(8);
        chart.getLegendPane().setVisible(false);
        chart.setRingFillColor(Color.DARK_GRAY);
        chart.setRingGradientColor(Color.LIGHT_GRAY);
        chart.setRingGradientFactory(TWaverConst.GRADIENT_RADIAL_C);
        chart.setRingStroke(TWaverConst.STROKE_SOLID_5);
        chart.setValueTextVisible(false);
        chart.setOpaque(false);

        Calendar calendar = Calendar.getInstance();
        double secondValue = calendar.get(Calendar.SECOND);
        double minuteValue = calendar.get(Calendar.MINUTE) + secondValue / 60d;
        double hourValue = calendar.get(Calendar.HOUR) + minuteValue / 60;

        hour.setName("hour");
        hour.putChartDialHandLength(0.7);
        hour.putChartDialHandStyle(TWaverConst.DIAL_HAND_STYLE_ARROW);
        hour.putChartValue(hourValue);
        hour.putChartColor(Color.GREEN.darker());
        hour.putChartStroke(TWaverConst.STROKE_SOLID_6);

        minute.setName("minute");
        minute.putChartDialHandLength(0.8);
        minute.putChartDialHandStyle(TWaverConst.DIAL_HAND_STYLE_TRIANGLE);
        minute.putChartValue(minuteValue / 5);
        minute.putChartColor(Color.ORANGE);
        minute.putChartStroke(TWaverConst.STROKE_SOLID_12);

        second.setName("second");
        second.putChartDialHandLength(0.9);
        second.putChartDialHandStyle(TWaverConst.DIAL_HAND_STYLE_LINE);
        second.putChartValue(secondValue / 5);
        second.putChartColor(Color.BLACK);
        chart.getDataBox().addElement(hour);
        chart.getDataBox().addElement(minute);
        chart.getDataBox().addElement(second);

        TaskScheduler.getInstance().register(new TaskAdapter() {

            @Override
            public int getInterval() {
                return 1000;
            }

            public void run(long clock) {
                Calendar calendar = Calendar.getInstance();
                double secondValue = calendar.get(Calendar.SECOND);
                double minuteValue = calendar.get(Calendar.MINUTE) + secondValue / 60d;
                double hourValue = calendar.get(Calendar.HOUR) + minuteValue / 60;
                hour.putChartValue(hourValue);
                minute.putChartValue(minuteValue / 5);
                second.putChartValue(secondValue / 5);
            }
        });
        return chart;
    }

    public static JComponent createDialPane() {
        JPanel pane = new JPanel(new GridLayout(2, 3));
        for (int i = 0; i < 6; i++) {
            pane.add(createDialChart());
        }
        pane.setOpaque(false);
        return pane;
    }

    public static JComponent createDialChart() {
        TDataBox box = new TDataBox();
        DialChart chart = new DialChart(box);
        Element element = new twaver.Node();
        element.putChartDialHandLength(0.9);
        element.putChartDialHandStyle(TWaverConst.DIAL_HAND_STYLE_TRIANGLE);
        element.putChartStroke(TWaverConst.STROKE_ZIGZAG_MIDDLE);
        box.addElement(element);

        chart.setMaxValue(210);
        element.putChartValue(TWaverUtil.getRandomInt((int) chart.getMaxValue()));
        element.putChartColor(Color.red.darker());
        chart.setScaleMajorCount(7);
        chart.getLegendPane().setVisible(false);
        chart.setArcRange(210);

        chart.setDialType(TWaverConst.DIAL_TYPE_ARC);
        chart.setScaleInside(true);
        chart.setScaleTextFont(FONT_7_PLAIN);
        chart.setValueTextFont(FONT_9_PLAIN);
        chart.setValueTextCenter(true);
        chart.setValueTextColor(FONT_COLOR);
        chart.setValueTextVisible(false);
        chart.setScaleMinTextVisible(true);
        chart.setOpaque(false);

        return chart;
    }

    public static JComponent createRadarChart() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        pane.add(createWhetherRadarChart());
        pane.add(createSurveyRadarChart());
        pane.setOpaque(false);

        return pane;
    }

    public static JComponent createWhetherRadarChart() {
        TDataBox box = new TDataBox();
        RadarChart chart = new RadarChart(box);

        chart.setScaleMajorCount(5);
        chart.setRingMinVisible(false);
        chart.setShapeFillGradient(false);
        chart.setRadarFillColor(new Color(50, 170, 160));
        chart.setAxisTextFont(new Font("dialog", Font.PLAIN, 12));
        chart.setScaleMajorCount(4);
        chart.setScaleMajorTextVisible(false);
        chart.getLegendPane().setVisible(false);
        chart.setInflexionVisible(false);

        chart.addAxisText("Rainy");
        chart.addAxisText("Snowy");
        chart.addAxisText("Windy");
        chart.addAxisText("Cloudy");
        chart.addAxisText("Iced");
        chart.addAxisText("Fine");

        chart.setAxisTextVisible(true);
        chart.setAxisTextColor(FONT_COLOR);
        chart.setAxisTextFont(FONT_9_PLAIN);

        List data1 = new ArrayList();
        data1.add("0.1");
        data1.add("0.4");
        data1.add("0.6");
        data1.add("0.4");
        data1.add("0.5");
        data1.add("0.2");
        List data2 = new ArrayList();
        data2.add("0.4");
        data2.add("0.2");
        data2.add("0.5");
        data2.add("0.6");
        data2.add("0.2");
        data2.add("0.5");

        chart.setScaleMaxValue(0.8);
        chart.setOpaque(false);
        addWhetherRadarElement(box, "Jan.", new Color(17, 133, 51), data1);
        addWhetherRadarElement(box, "Feb.", new Color(237, 152, 30), data2);
        chart.setXZoom(1.2);
        chart.setYZoom(1.2);
        chart.setYTranslate(-20);

        return chart;
    }

    public static void addWhetherRadarElement(TDataBox box, String name, Color color, List list) {
        Element element = new twaver.Node();
        element.setName(name);
        element.putChartColor(color);
        element.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
        element.putChartStroke(TWaverConst.STROKE_SOLID_1);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            element.addChartValue(Double.valueOf(list.get(i).toString()).doubleValue());
        }
        box.addElement(element);
    }

    public static JComponent createSurveyRadarChart() {
        String[] axisTexts = new String[]{
            "Best", "Better", "Equal", "Worse", "Unknown", "Other",};
        TDataBox box = new TDataBox();
        RadarChart chart = new RadarChart(box);
        addRadarElement(new Color(17, 133, 51), "Quality", box, axisTexts.length);
        addRadarElement(new Color(37, 77, 192), "Performance", box, axisTexts.length);
        addRadarElement(new Color(222, 33, 73), "Service", box, axisTexts.length);
        chart.setAxisStroke(TWaverConst.STROKE_SOLID_1);
        chart.getLegendPane().setVisible(false);
        chart.setRadarFill(true);
        chart.setShapeFillGradient(false);
        chart.setRadarFillGradient(false);
        chart.setRadarFillColor(new Color(245, 245, 245));
        chart.setScaleMaxValue(1.0);
        chart.setScaleMajorCount(5);
        chart.setAxisStartAngle(-90);
        chart.setAreaFill(true);
        chart.setInflexionVisible(false);
        chart.setScaleMajorTextFont(FONT_9_PLAIN);
        chart.setScaleMajorTextColor(FONT_COLOR);
        chart.setAxisTextFont(FONT_11_BOLD);
        chart.setAxisTextColor(FONT_COLOR);
        chart.getTitleLabel().setForeground(FONT_COLOR);
        chart.setRingMinVisible(false);
        for (int i = 0; i < axisTexts.length; i++) {
            chart.addAxisText(axisTexts[i]);
        }
        chart.setAxisTextTooltipVisible(true);
        chart.setShapeFillGradientColor(new Color(245, 245, 245));
        chart.setRingMajorStroke(TWaverConst.STROKE_SOLID_1);
        chart.setRingStyle(TWaverConst.RADAE_RING_STYLE_ELLIPSE);
        chart.setAxisTextVisible(true);
        chart.setAxisTextColor(FONT_COLOR);
        chart.setAxisTextFont(FONT_9_PLAIN);
        chart.setScaleMajorTextVisible(false);
        chart.setOpaque(false);
        chart.setXZoom(1.3);
        chart.setYZoom(1.3);
        chart.setYTranslate(-20);

        return chart;
    }

    private static twaver.Element addRadarElement(Color color, String name, TDataBox box, int axisLength) {
        Element element = new twaver.Node(name);
        element.setName(name);
        element.putChartColor(color);
        for (int i = 0; i < axisLength; i++) {
            element.addChartValue(TWaverUtil.getRandomInt(100) / 100f);
        }
        box.addElement(element);
        return element;
    }

    public static JComponent createGroupBubbleChart() {
        TDataBox box = new TDataBox();
        BubbleChart chart = new BubbleChart(box) {

            @Override
            protected double getShapeSize(twaver.Element element, Bubble bubble, int index) {
                return super.getShapeSize(element, bubble, index) / 300d * Math.min(getDrawBound().width, getDrawBound().height);
            }
        };

        int xMax = 10;
        int yMax = 100;

        chart.setBackgroundVisible(false);
        chart.setYScaleMaxValue(yMax);
        chart.setYScaleMinValue(0);
        chart.setXScaleMaxValue(xMax);
        chart.setXScaleMinValue(0);
        chart.setXScaleValueGap(1);
        chart.setBubbleAlpha(0.9f);
        chart.setValueTextVisible(false);
        chart.setShapeLineVisible(false);
        chart.getLegendPane().setVisible(false);
        chart.setOpaque(false);

        for (int index = 0; index < 5; index++) {
            twaver.Node element = new twaver.Node();
            element.putChartColor(TWaverUtil.getRandomColor());
            box.addElement(element);

            for (int i = 0; i < 3; i++) {
                int x = 3 + TWaverUtil.getRandomInt(xMax / 2);
                int y = 30 + TWaverUtil.getRandomInt(yMax - 60);
                int value = TWaverUtil.getRandomInt(80);
                Bubble bubble = new Bubble(x, y, value);
                element.addChartBubble(bubble);
            }
            for (int i = 0; i < 10; i++) {
                int x = 1 + TWaverUtil.getRandomInt(xMax - 2);
                int y = 10 + TWaverUtil.getRandomInt(yMax - 20);
                int value = 10 + TWaverUtil.getRandomInt(20);
                Bubble bubble = new Bubble(x, y, value);
                element.addChartBubble(bubble);
            }
        }

        return chart;
    }

    public static JComponent createCalendar() {
        CalendarChart calendar = new CalendarChart(new Date());
        JPanel pane = new JPanel(new GridLayout(1, 2, 10, 0));
        pane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        pane.add(calendar);
        pane.add(createClockChart());
        pane.setBackground(Color.white);
        pane.setOpaque(false);
        return pane;
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
            InputStream in = TWaverUtil.getInputStream("/demo/chart/customized/google.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.split("\\,");
                String date = ss[0];
                int index = date.lastIndexOf("-");
                date = date.substring(0, index);
                dates.add(0, date);
                open.getChartValues().add(0, Double.valueOf(ss[1]));
                high.getChartValues().add(0, Double.valueOf(ss[2]));
                low.getChartValues().add(0, Double.valueOf(ss[3]));
                history.getChartValues().add(0, Double.valueOf(ss[4]));
                volume.getChartValues().add(0, Double.valueOf(ss[5]));
                close.getChartValues().add(0, Double.valueOf(ss[4]));
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
    
    
    

    public static JComponent createStylePieChartPane() {
        JPanel pane = new JPanel(new GridLayout(1, 2));
        pane.setOpaque(false);

        PieChart pie1 = createStylePieChart(StylePieChart.STYLE_DISK);
        pie1.setHollow(false);
        pie1.setOpaque(false);
        pane.add(pie1);

        PieChart pie2 = createStylePieChart(StylePieChart.STYLE_EDGE);
        pie2.setHollow(true);
        pie2.setOpaque(false);
        pane.add(pie2);

        return pane;
    }

    public static StylePieChart createStylePieChart(int style) {
        StylePieChart chart = new StylePieChart();
        chart.setPieStyle(style);
        chart.setOpaque(false);

        addPieChartElement(chart, "Sprint", 23, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Verizon", 26, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "AT&T", 26, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "T-Mobile", 11, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Alltel", 5, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Rest", 9, TWaverUtil.getRandomColor());

        return chart;
    }

    public static JComponent createOldPieChart() {
        PieChart chart = new PieChart() {

            @Override
            protected String getFormatedText(Element element, double value) {
                return super.getFormatedText(element, value) + "%";
            }
        };
        addPieChartElement(chart, "Sprint", 23, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Verizon", 26, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "AT&T", 26, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "T-Mobile", 11, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Alltel", 5, TWaverUtil.getRandomColor());
        addPieChartElement(chart, "Rest", 9, TWaverUtil.getRandomColor());

        chart.setLegendOrientation(TWaverConst.LABEL_ORIENTATION_RIGHT);
        chart.setHollow(true);
        chart.setShadowOffset(30);
        chart.setValueTextPercent(true);
        chart.set3D(true);
        chart.setXGap(30);
        chart.setValueTextPercent(false);
        chart.setSelectedOffset(30);
        chart.setSelectedStroke(TWaverConst.STROKE_SOLID_0);
        chart.setOpaque(false);

        return chart;
    }

    private static void addPieChartElement(PieChart chart, String name, double value, Color color) {
        Element element = new twaver.Node();
        element.setName(name);
        element.putChartValue(value);
        element.putChartColor(color);
        chart.getDataBox().addElement(element);
    }

    public static JComponent createWeekWhetherChart() {
        JPanel pane = new JPanel(new GridLayout(2, 2));
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pane.setOpaque(false);
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 2; i++) {
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            dayOfYear = dayOfYear + i;
            calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
            pane.add(createWhetherChart(calendar.getTime()));
        }

        return pane;
    }

    public static JComponent createWhetherChart(Date date) {
        JPanel whetherPane = new JPanel(new GridLayout(1, 2));
        whetherPane.setOpaque(false);
        int whether = TWaverUtil.getRandomInt(7) + 1;
        final ImageIcon imageIcon = TWaverUtil.getImageIcon("/demo/chart/images/whether_" + whether + ".png");
        JLabel iconLabel = new JLabel() {

            @Override
            public void paintComponent(Graphics g) {
                int width = this.getWidth();
                int height = this.getHeight();
                int size = Math.min(width, height);
                int x = 0;
                if (width > size) {
                    x = (width - size) / 2;
                }
                int y = 0;
                if (height > size) {
                    y = (height - size) / 2;
                }
                g.drawImage(imageIcon.getImage(), x, y, size, size, null);

                g.setFont(FONT_9_PLAIN);

                //center label.
                Rectangle2D textBounds = g.getFontMetrics().getStringBounds(this.getText(), g);
                x = (int) ((this.getWidth() - textBounds.getWidth()) / 2);
                g.setColor(FONT_COLOR);
                g.drawString(this.getText(), x, y + 10);
            }
        };
        iconLabel.setOpaque(false);
        iconLabel.setIcon(imageIcon);
        iconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        iconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        String text = DateFormat.getDateInstance(DateFormat.MEDIUM, TWaverConst.EN_US).format(date);
        iconLabel.setText(text);
        whetherPane.add(iconLabel);
        whetherPane.add(createThermometerChart());

        return whetherPane;
    }

    public static JComponent createThermometerChart() {
        ThermometerChart chart = new ThermometerChart() {

            @Override
            protected String getFormatedYScaleText(double percent) {
                int degree = ((int) percent) - 20;
                return degree + " F";
            }
        };
        chart.setXGap(0);
        chart.setYGap(0);
        chart.setLegendFont(FONT_11_BOLD);
        chart.setValueTextFont(FONT_9_PLAIN);
        chart.setScaleVisible(true);
        chart.setLegendFont(FONT_9_PLAIN);
        chart.getLegendPane().setVisible(false);
        chart.setValueTextVisible(false);

        twaver.Node highTemp = new twaver.Node();
        highTemp.setName("H");
        highTemp.putChartColor(new Color(192, 38, 44));
        highTemp.putChartValue(0.72);
        chart.getDataBox().addElement(highTemp);

        twaver.Node lowTemp = new twaver.Node();
        lowTemp.setName("L");
        lowTemp.putChartColor(new Color(46, 49, 189));
        lowTemp.putChartValue(0.30);
        chart.getDataBox().addElement(lowTemp);
        chart.setOpaque(false);

        return chart;
    }

    public static JComponent createBarChart() {
        TDataBox box = new TDataBox();
        BarChart chart = new BarChart(box) {

            @Override
            protected Color getGradientColor(Element element) {
                return element.getChartColor().brighter();
            }

            @Override
            protected String getFormatedYScaleText(double value) {
                if (value == 0) {
                    return "";
                }
                return "$" + NumberFormat.getNumberInstance().format(value * 25000);
            }
        };

        chart.setYAxisText("Payments");
        chart.setYAxisTextColor(Util.FONT_COLOR);
        chart.setYAxisTextFont(Util.FONT_11_BOLD);
        chart.setYScaleTextVisible(true);
        chart.setYScaleMinTextVisible(true);
        chart.setYScaleValueGap(20);
        chart.setBarType(TWaverConst.BAR_TYPE_GROUP);
        chart.setGradient(true);
        chart.setShadowOffset(0);
        chart.addXScaleText("JAN-2010");
        chart.addXScaleText("FEB-2010");
        chart.addXScaleText("MAR-2010");
        chart.setValueTextVisible(false);
        chart.setLegendFont(Util.FONT_9_PLAIN);
        chart.setOpaque(false);

        Element sun = new twaver.Node("Sun");
        sun.setName("Sun");
        sun.putChartColor(new Color(188, 0, 80));
        sun.addChartValue(20);
        sun.addChartValue(32);
        sun.addChartValue(40);
        box.addElement(sun);

        Element hp = new twaver.Node("HP");
        hp.setName("HP");
        hp.putChartColor(new Color(252, 197, 4));
        hp.addChartValue(28);
        hp.addChartValue(40);
        hp.addChartValue(55);
        box.addElement(hp);

        Element dell = new twaver.Node("Dell");
        dell.setName("Dell");
        dell.putChartColor(new Color(136, 125, 187));
        dell.addChartValue(30);
        dell.addChartValue(68);
        dell.addChartValue(110);
        box.addElement(dell);

        return chart;
    }

    public static JComponent createAreaBubbleChart() {
        TDataBox box = new TDataBox();
        BubbleChart chart = new BubbleChart(box) {

            @Override
            protected String getFormatedXScaleText(double value) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, (int) value);
                Date date = calendar.getTime();
                return DateFormat.getDateInstance(DateFormat.SHORT, TWaverConst.EN_US).format(date);
            }
        };

        int max = 20;
        chart.setXScaleMinValue(0);
        chart.setXScaleValueGap(3);
        chart.setXScaleMaxValue(max);
        chart.setYScaleTextVisible(true);
        chart.setLineType(TWaverConst.LINE_TYPE_AREA);
        chart.setYScaleMinValue(0);
        chart.setYScaleMaxValue(100);
        chart.setYScaleValueGap(10);
        chart.setYAxisTextFont(Util.FONT_9_PLAIN);
        chart.setValueTextVisible(false);
        chart.setAreaSelectable(true);
        chart.setLegendFont(Util.FONT_9_PLAIN);
        chart.setYAxisText("Number of Problems");
        chart.setYAxisTextColor(Util.FONT_COLOR);
        chart.setLegendFont(FONT_9_PLAIN);
        chart.setOpaque(false);

        Element critical = new twaver.Node();
        critical.setName("Critical");
        critical.putChartColor(Color.red);
        box.addElement(critical);

        Element high = new twaver.Node();
        high.setName("High");
        high.putChartColor(Color.orange);
        box.addElement(high);

        Element medium = new twaver.Node();
        medium.setName("Medium");
        medium.putChartColor(Color.cyan);
        box.addElement(medium);

        for (int i = 0; i < max; i++) {
            critical.addChartBubble(new Bubble(i, 60 + TWaverUtil.getRandomInt(40)));
            high.addChartBubble(new Bubble(i, 30 + TWaverUtil.getRandomInt(30)));
            medium.addChartBubble(new Bubble(i, 10 + TWaverUtil.getRandomInt(20)));
        }

        return chart;
    }

    public static List parserXml(String fileName, int xoffset, int yoffset) {

        List list = new ArrayList();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = TWaverUtil.getInputStream(fileName);
            Document document = db.parse(is);
            NodeList features = document.getElementsByTagName("f");
            int count = features.getLength();
            for (int i = 0; i < count; i++) {
                Node feature = features.item(i);
                MapNode stateNode = new MapNode();
                list.add(stateNode);
                List segments = new ArrayList();
                for (Node node = feature.getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        if ("na".equals(node.getNodeName())) {
                            for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
                                if (child.getNodeType() == Node.ELEMENT_NODE) {
                                    String n = child.getAttributes().getNamedItem("n").getNodeValue();
                                    String v = child.getAttributes().getNamedItem("v").getNodeValue();
                                    stateNode.putClientProperty(n, v);
                                }
                            }
                        }
                        if ("s".equals(node.getNodeName())) {
                            for (Node children = node.getFirstChild(); children != null; children = children.getNextSibling()) {
                                if (children.getNodeType() == Node.ELEMENT_NODE) {
                                    Node segment = children.getAttributes().getNamedItem("s");
                                    String x = children.getAttributes().getNamedItem("x").getNodeValue();
                                    String y = children.getAttributes().getNamedItem("y").getNodeValue();
                                    double scale = 0.5d;
                                    double pointX = Double.parseDouble(x) * scale;
                                    double pointY = Double.parseDouble(y) * scale;
                                    stateNode.addPoint(new Point2D.Double(pointX + xoffset, pointY + yoffset));
                                    if (segment != null) {
                                        segments.add(new Integer(PathIterator.SEG_MOVETO));
                                    } else {
                                        segments.add(new Integer(PathIterator.SEG_LINETO));
                                    }
                                }
                            }
                        }
                        stateNode.setSegments(segments);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
