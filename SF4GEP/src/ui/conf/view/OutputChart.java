package ui.conf.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import com.sun.media.sound.Toolkit;

import twaver.AlarmState;
import twaver.BatchEvent;
import twaver.BatchListener;
import twaver.DataBoxEvent;
import twaver.DataBoxListener;
import twaver.Element;
import twaver.Node;
import twaver.PopupMenuGenerator;
import twaver.TDataBox;
import twaver.TView;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.base.A.E.a;
import twaver.chart.LineChart;
import ui.images.ImageHelper;

import java.awt.event.MouseMotionAdapter;

public class OutputChart extends LineChart {
	public OutputChart(TDataBox tDataBox) {
		super(tDataBox);
    	initial();
    }
	
	
	public OutputChart(LineChart rangeChart, int valuesCount) {
		this.rangeChart = rangeChart;
    	this.valuesCount = valuesCount;
    	initial();
    }

    private int valuesCount = 0;
    private LineChart rangeChart = null;
    private Point startPoint = null;
    private Point endPoint = null;
    private Point lastPoint = null;

    @Override
    public void paintChart(Graphics2D g2d, int width, int height) {
        super.paintChart(g2d, width, height);
        if (startPoint != null && endPoint != null) {
            Rectangle bounds = this.getBackgroundBounds();
            g2d.setColor(new Color(0, 255, 0, 128));
            int x = Math.min(startPoint.x, endPoint.x);
            int y = bounds.y;
            int w = Math.abs(endPoint.x - startPoint.x);
            int h = bounds.height;
            g2d.fillRect(x, y, w, h);
        }
    }

    private boolean isValidEvent(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Rectangle bounds = this.getBackgroundBounds();
            bounds.grow(1, 1);
            if (bounds.contains(e.getPoint())) {
                return true;
            }
        }
        return false;
    }

    private boolean isInsideEvent(MouseEvent e) {
        if (startPoint != null && endPoint != null) {
            if (e.getX() >= startPoint.x && e.getX() <= endPoint.getX()) {
                return true;
            }
            if (e.getX() >= endPoint.x && e.getX() <= startPoint.getX()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	if (rangeChart==null) {
			return;
		}
    	
        lastPoint = null;
        if (isValidEvent(e)) {
            if (isInsideEvent(e)) {
                this.lastPoint = e.getPoint();
            } else {
                this.startPoint = e.getPoint();
                this.endPoint = e.getPoint();
                this.lastPoint = null;
            }
            
            this.changeRange();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	if (rangeChart==null) {
			return;
		}
        if (isValidEvent(e) && this.startPoint != null) {
            if (lastPoint != null) {
                int offset = this.lastPoint.x - e.getX();
                this.startPoint.x -= offset;
                this.endPoint.x -= offset;
                this.lastPoint = e.getPoint();
            } else {
                this.endPoint = e.getPoint();
            }
            this.changeRange();
        }
    }

    private void changeRange() {
        this.getChartPane().repaint();

        if (this.startPoint.x == this.endPoint.x) {
            this.rangeChart.setStartIndex(0);
            this.rangeChart.setEndIndex(Integer.MAX_VALUE);
            this.rangeChart.setXScaleTextSpanCount(30);
        } else {
            double x1 = this.getStartX();
            double x2 = this.getEndX();
            double w = (x2 - x1) / (this.valuesCount - 1);
            int s = (int) ((startPoint.x - x1) / w);
            int e = (int) ((endPoint.x - x1) / w);
            if (s > e) {
                int tmp = e;
                e = s;
                s = tmp;
            }
            if (s < 0) {
                s = 0;
            }
            if (e > this.valuesCount) {
                e = this.valuesCount;
            }

            this.rangeChart.setStartIndex(s);
            this.rangeChart.setEndIndex(e);

            int span = Math.max(1, 15 * (e - s) / rangeChart.getBackgroundBounds().width - 1);
            this.rangeChart.setXScaleTextSpanCount(span);
        }
    }

    private void setRangeChart(LineChart rangeChart){
    	this.rangeChart = rangeChart;
    }
    private void setValuesCount(int valuesCount){
    	this.valuesCount = valuesCount;
    }
    
    public void initialRangePanel(LineChart rangeChart,int valuesCount){
    	setRangeChart(rangeChart);
    	setValuesCount(valuesCount);
    	initial();
    }
    
    private void initial(){
    	
        this.setEnableXZoom(false);
        this.setEnableYZoom(false);
        this.setEnableXTranslate(false);
        this.setEnableYTranslate(false);
        
        this.setYAxisVisible(true);
        this.setXAxisVisible(true);
        this.setXScaleTextVisible(false);
        this.setInflexionVisible(true);	//显示弯曲线条
		
		
		
        this.setLineType(TWaverConst.LINE_TYPE_AREA);
        //this.setValueSpanCount(10);		//需要显示的点的个数
        //this.setXScaleTextSpanCount(10);
        this.setXScaleTextOrientation(TWaverConst.LABEL_ORIENTATION_HORIZONTAL);
        //this.setLowerLimit(0);
        this.setYScaleTextVisible(true);
        this.setXScaleTextVisible(true);//
        this.setGradient(false);
        //this.setYScaleValueGap(100);
        this.setLegendLayout(TWaverConst.LEGEND_LAYOUT_HORIZONTAL);	//设置线条标签位置
        this.getLegendPane().setVisible(true);
       
        this.setXAxisVisible(true);
        this.setYAxisVisible(true);
        
        this.setEnableToolTipText(true);
        
        
        
        
        
        
        
        this.getChartPane().setFocusTraversalKeysEnabled(true);
        this.setFocusTraversalPolicyProvider(true);
        this.getChartPane().setFocusCycleRoot(true);

        
        TWaverUtil.setEnableAutoScroll(true);
        this.setPopupMenuGenerator(new PopupMenuGenerator() {

        public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {
	            JPopupMenu popup = new JPopupMenu();
	            
	            
	            JMenuItem item = new JMenuItem("Show Full View");
	            item.addActionListener(new ActionListener() {

	            	
	            	
	                public void actionPerformed(ActionEvent e) {
	                	OutputChart.this.setValueSpanCount(1);
	                	OutputChart.this.setXScaleTextSpanCount(1);
	                	OutputChart.this.setStartIndex(0);
	                	OutputChart.this.setEndIndex((int) OutputChart.this.getEndX());//
	                	OutputChart.this.setXScaleTextVisible(false);
	                }
	            });
	            
	            

	            popup.add(item);
	            return popup;
        	}
        });
        
        
        
        /*this.addElementClickedActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OutputChart.this.gete
				
			}
		});*/
        
        
        
        this.addElementClickedActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click");
				//Element elememt =(Element) e.getSource();
				
				
				
				Element elememt = (Element) OutputChart.this.getDataBox().getAllElements().get(0);
					
				/*Node cur = (Node) OutputChart.this.getDataBox().getElementByName(elememt.getName());
				
				//System.out.println(cur.getChartValue() +"|" + cur.getChartValueTextPosition());
				*/
				if (elememt.isEmpty()) {
					System.out.println("empty");
					return;
				}
				
				System.out.println(elememt.getX()+ "|" + elememt.getY());
				
				System.out.println(elememt.getChartValue()+"|"+elememt.getChartValueTextPosition());
				System.out.println("-------");
				
			}
		});
        
        getChartPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Element element = OutputChart.this.getElementAt(e.getPoint());
				
				if (element!=null) {
					System.out.println(OutputChart.this.getToolTipText(element));
					System.out.println(e.getPoint().getX()+"|"+e.getPoint().getY());
				}
				
				
				
				
				
				//Point inPoint = OutputChart.this.getGraphics()
				/*if (e.getSource()==Element.class) {
					Element thisElement = (Element) e.getSource();
					Element userInputDataElement = OutputChart.this.getDataBox().getElementByName("UserInputData");
					Element fittedResultElement = OutputChart.this.getDataBox().getElementByName("FittedResult");
					System.out.println("It is");
					if (thisElement.equals(userInputDataElement)||thisElement.equals(fittedResultElement)) {
						System.out.println(userInputDataElement.getX()+"|"+fittedResultElement.getX());
						
						
						
					}
				}*/
				
				
				
				/*if (element!=null&&!element.isEmpty()) {
					
					//System.out.println(element.getAlarmState() +"|"+element.getAttachmentPosition() +"|" +element.getLocation().getX() + "|" + element.getRenderAlpha());
					
					//TDataBox td =  OutputChart.this.getDataBox();
					//Node cur = (Node) OutputChart.this.getDataBox().getElementByName("UserInputData");
					
					
					System.out.println("  "+OutputChart.this.getStroke() + "|" + element.getChartValue() +  "|" +
							element.getChartValueTextPosition() +"|"+ element.getChartPercentMarkerPostion() + 
							"|" + OutputChart.this.getStartIndex()+"|" +OutputChart.this.endIndex);
					
				}
				*/
			}
		});
        
        
        
    }
    
    

    private JMenuItem createMenuItem(final int valueSpanCount, final int textSpanCount) {
        JMenuItem item = new JMenuItem("Value Span:" + valueSpanCount + "|Text Span:" + textSpanCount);
        item.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	OutputChart.this.setValueSpanCount(valueSpanCount);
            	OutputChart.this.setXScaleTextSpanCount(textSpanCount);
            	
            	
            	
            }
        });
        return item;
    }
}
