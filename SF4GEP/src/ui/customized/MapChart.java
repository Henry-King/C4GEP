package ui.customized;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import twaver.Element;
import twaver.Generator;
import twaver.MovableFilter;
import twaver.ResizableFilter;
import twaver.TDataBox;
import twaver.TUIManager;
import twaver.TWaverConst;
import twaver.network.InteractionModeFactory;
import twaver.network.TNetwork;
import ui.Util;

public class MapChart extends JPanel {

    static {
        TUIManager.registerAttachment("stateinfo", MapAttachment.class);
    }
    private TDataBox box = new TDataBox();
    private TNetwork network = new TNetwork(box);
    private MapNode lastNode = null;
    private Color outlineColor = new Color(250, 240, 200);

    public MapChart() {
        initGUI();
        initNetwork();
        loadXML();
    }

    public void loadXML() {
        box.clear();
        List list = Util.parserXml("/demo/chart/customized/map.xml", 30, 30);
        box.addElements(list);
    }

    private void initGUI() {
        this.setLayout(new BorderLayout());
        this.add(network, BorderLayout.CENTER);

        //make everything transparent
        this.setOpaque(false);
        network.setOpaque(false);
        network.getCanvas().setOpaque(false);
        network.getCanvasScrollPane().setOpaque(false);
        network.getCanvasScrollPane().getViewport().setOpaque(false);
        network.getLayeredPane().setOpaque(false);

        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                network.getZoomer().zoomToOverview();
            }
        });
        network.getCanvasScrollPane().setBorder(null);
        network.getCanvasScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        network.getCanvasScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void initNetwork() {
        network.setToolbar(null);
        network.setInteractionMode(InteractionModeFactory.getDefaultLazySelectionMode(network));
        network.setEnableMouseWheelToZoom(false);

        network.setElementLabelGenerator(new Generator() {

            public Object generate(Object object) {
                Element element = (Element) object;
                String name = element.getClientProperty("STATE_NAME").toString();
                element.setToolTipText(name);
                String[] splits = name.split(" ");
                if (splits.length > 1) {
                    name = splits[0].substring(0, 1) + splits[1].substring(0, 1);
                } else {
                    name = splits[0].substring(0, 2);
                }
                return name.toUpperCase();
            }
        });
        network.setMovableFilter(new MovableFilter() {

            public boolean isMovable(Element element) {
                return false;
            }
        });
        network.setResizableFilter(new ResizableFilter() {

            public boolean isResizable(Element element) {
                return false;
            }
        });

        box.addElementPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String name = evt.getPropertyName();
                MapNode element = (MapNode) evt.getSource();
                if (name.equals(TWaverConst.PROPERTYNAME_SELECTED)) {
                    boolean isSelected = ((Boolean) evt.getNewValue()).booleanValue();
                    if (isSelected) {
                        element.putClientProperty("old.fillcolor", element.getCustomDrawFillColor());
                        element.putCustomDrawFillColor(Color.orange);
                        element.addAttachment("stateinfo");
                    } else {
                        element.putCustomDrawFillColor((Color) element.getClientProperty("old.fillcolor"));
                        element.putCustomDrawOutlineColor(outlineColor);
                        if (element.containsAttachment("stateinfo")) {
                            element.removeAttachment("stateinfo");
                        }
                    }
                }
            }
        });
        network.getCanvas().addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                MapNode element = (MapNode) network.getElementPhysicalAt(e.getPoint());
                if (element != null && element != lastNode) {
                    Point location = element.getCenterLocation();
                    Point center = network.getLogicalCenterPoint();
                    if (location.x > center.x && location.y < center.y) {
                        element.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_BOTTOM_LEFT);
                    } else if (location.x > center.x && location.y > center.y) {
                        element.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_TOP_LEFT);
                    } else if (location.x < center.x && location.y < center.y) {
                        element.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_BOTTOM_RIGHT);
                    } else {
                        element.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_TOP_RIGHT);
                    }
                    box.getSelectionModel().setSelection(element);
                }
                if (element == null) {
                    box.getSelectionModel().clearSelection();
                }
                lastNode = element;
            }
        });
    }

    public TNetwork getNetwork() {
        return network;
    }
}
