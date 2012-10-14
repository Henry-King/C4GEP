package ui.customized;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


import twaver.Element;
import twaver.ElementAttribute;
import twaver.Node;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.VisibleFilter;
import twaver.chart.PieChart;
import twaver.network.ui.ComponentAttachment;
import twaver.network.ui.ElementUI;

public class MapAttachment extends ComponentAttachment {

    private TDataBox chartBox = new TDataBox();
    private PieChart pieChart = new PieChart(chartBox);
    private Node male = new Node();
    private Node female = new Node();
    private Node employed = new Node();
    private Node unemploy = new Node();

    public MapAttachment(String name, ElementUI ui) {
        super(name, ui);
        init();
        initChartBox();
        initSheet();
        setChartValue();
    }

    private void initSheet() {
        List list = new ArrayList();
        list.add(createAttribute("LAND_KM", "LAND_KM"));
        list.add(createAttribute("WATER_KM", "WATER_KM"));
        list.add(createAttribute("PERSONS", "PERSONS"));
        list.add(createAttribute("FAMILIES", "FAMILIES"));
        list.add(createAttribute("HOUSHOLD", "HOUSHOLD"));
        list.add(createAttribute("WORKERS", "WORKERS"));
    }

    private ElementAttribute createAttribute(String key, String displayName) {
        ElementAttribute attribute = new ElementAttribute();
        attribute.setClientPropertyKey(key);
        attribute.setDisplayName(displayName);
        return attribute;
    }

    private void initChartBox() {
        male.setName("MALE");
        male.putClientProperty("type", "bar");
        male.putChartColor(new Color(0, 100, 0));
        chartBox.addElement(male);

        female.setName("FEMALE");
        female.putClientProperty("type", "bar");
        female.putChartColor(new Color(250, 100, 0));
        chartBox.addElement(female);

        employed.setName("EMPLOYED");
        employed.putChartColor(new Color(0, 255, 0));

        employed.putClientProperty("type", "pie");
        chartBox.addElement(employed);

        unemploy.setName("UNEMPLOY");
        unemploy.putChartColor(new Color(255, 0, 0));
        unemploy.putClientProperty("type", "pie");
        chartBox.addElement(unemploy);

        pieChart.setOpaque(false);
        pieChart.setStartAngle(-100);
        pieChart.setLegendFont(new Font("Dialog", Font.BOLD, 10));
        pieChart.set3D(true);
        pieChart.setValueTextPercent(true);
        pieChart.setShadowOffset(15);


        pieChart.addVisibleFilter(new VisibleFilter() {

            public boolean isVisible(Element element) {
                if (element.getClientProperty("type").toString().equals("pie")) {
                    return true;
                }
                return false;
            }
        });
    }

    private void init() {
        this.setComponent(pieChart);
        this.setPosition(TWaverConst.POSITION_CENTER);
        this.setBodyVisible(true);
        this.setBodyGradient(false);
        this.setBodyColor(Color.white);
        this.setBorderColor(Color.gray);
        this.setBorderVisible(true);
        this.setStyle(TWaverConst.ATTACHMENT_STYLE_BUBBLE);
        this.setTail(10);
        this.setArc(10);
        this.setWidth(150);
        this.setHeight(150);
        this.setDirection(((MapNode) element).getAttachmentDirection());
    }

    private void setChartValue() {
        double value = Double.parseDouble(element.getClientProperty("MALE").toString());
        male.putChartValue(value);
        value = Double.parseDouble(element.getClientProperty("FEMALE").toString());
        female.putChartValue(value);
        value = Double.parseDouble(element.getClientProperty("EMPLOYED").toString());
        employed.putChartValue(value);
        value = Double.parseDouble(element.getClientProperty("UNEMPLOY").toString());
        unemploy.putChartValue(value);
    }
}
