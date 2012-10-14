package ui.customized;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import twaver.Element;
import twaver.TWaverConst;
import twaver.chart.AbstractChart;
import twaver.swing.RotatableLabel;

public class FunnelChart extends AbstractChart {

    private double angle = Math.toRadians(30);
    private boolean useHollow = false;
    private boolean useGap = true;

    @Override
    public void paintChart(Graphics2D g2d, int width, int height) {
        g2d.setStroke(TWaverConst.BASIC_STROKE);

        this.paintBackground(g2d);

        paintFunnel(g2d, width, height);
    }

    protected void paintFunnel(Graphics2D g2d, int width, int height) {
        int count = publishedElements.size();
        Rectangle2D drawBound = new Rectangle2D.Double(xGap, yGap, width - 2 * xGap, height - 2 * yGap);
        double topGap = drawBound.getWidth() / 2 * Math.tan(angle) / 2 / 4;
        drawBound.setRect(drawBound.getX(), drawBound.getY() + topGap,
                drawBound.getWidth(), drawBound.getHeight() - topGap);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //value total.
        double totalValue = 0;
        for (int i = 0; i < count; i++) {
            Element element = (Element) publishedElements.get(i);
            double value = element.getChartValue();
            totalValue += value;
        }

        if (totalValue == 0) {
            return;
        }

        double gap = 0;
        if (isUseGap()) {
            gap = drawBound.getHeight() / (count * count);
        }

        //each % of each element.
        double heightCursor = drawBound.getHeight();
        for (int i = 0; i < count; i++) {
            Element element = (Element) publishedElements.get(i);
            Color color = element.getChartColor();
            double value = element.getChartValue();
            double percent = value / totalValue;
            double elementHeight = drawBound.getHeight() * percent;

            double bottomLocation = heightCursor;
            double topLocation = bottomLocation - elementHeight + gap;
            double radiusTop = (drawBound.getHeight() - topLocation) * Math.tan(angle);
            double radiusBottom = (drawBound.getHeight() - bottomLocation) * Math.tan(angle);
            double xCenter = drawBound.getX() + drawBound.getWidth() / 2;

            //side shape.
            GeneralPath path = new GeneralPath();
            path.moveTo(-radiusTop + xCenter, topLocation + drawBound.getY());
            path.lineTo(-radiusBottom + xCenter, bottomLocation + drawBound.getY());
            path.lineTo(radiusBottom + xCenter, bottomLocation + drawBound.getY());
            path.lineTo(radiusTop + xCenter, topLocation + drawBound.getY());
            path.closePath();

            //top and bottom ellipse
            double topEllipseHeight = radiusTop * Math.tan(angle) / 2;
            Ellipse2D.Double topEllipse = new Ellipse2D.Double(-radiusTop + xCenter,
                    topLocation + drawBound.getY() - topEllipseHeight / 2,
                    radiusTop * 2, topEllipseHeight);
            double bottomEllipseHeight = radiusBottom * Math.tan(angle) / 2;
            Ellipse2D.Double bottomEllipse = new Ellipse2D.Double(-radiusBottom + xCenter,
                    bottomLocation + drawBound.getY() - bottomEllipseHeight / 2,
                    radiusBottom * 2, bottomEllipseHeight);
            Area ellipseIntersection = new Area(topEllipse);
            ellipseIntersection.intersect(new Area(bottomEllipse));

            //create side area.
            Area side = new Area(path);
            side.subtract(new Area(topEllipse));
            side.add(new Area(bottomEllipse));
            side.subtract(ellipseIntersection);

            //paint tail, for bottom pipe.
            if (i == 0) {
                double tailWidth = 10;
                tailWidth = Math.min(tailWidth, radiusTop * 2);
                Rectangle2D.Double tail = new Rectangle2D.Double(
                        xCenter - tailWidth / 2,
                        topLocation + drawBound.getY() + topEllipseHeight,
                        tailWidth,
                        elementHeight);
                side.add(new Area(tail));
            }

            //paint top ellipse.
            if (this.isUseHollow()) {
                Point2D from = new Point2D.Float((float) (xCenter - radiusTop), 0);
                Point2D end = new Point2D.Float((float) (xCenter + radiusTop), 0);
                float[] dist = {0.0f, 0.75f, 1.0f};
                Color[] colors = {color.darker().darker(), color.brighter().brighter(), color.darker()};
                LinearGradientPaint paint = new LinearGradientPaint(from, end, dist, colors);
                g2d.setPaint(paint);
            } else {
                g2d.setColor(color.brighter().brighter());
            }
            g2d.fill(topEllipse);
            if (i == count - 1 || this.isUseGap()) {
                g2d.draw(topEllipse);
            }

            //fill side area.
            Point2D from = new Point2D.Float((float) (xCenter - radiusTop), 0);
            Point2D end = new Point2D.Float((float) (xCenter + radiusTop), 0);
            float[] dist = {0.0f, 0.5f, 1.0f};
            Color[] colors = {color.darker(), color.brighter().brighter(), color.darker()};
            if (this.isUseHollow()) {
                dist = new float[]{0.0f, 0.4f, 1.0f};
                colors = new Color[]{color, color.brighter().brighter(), color.darker()};
            }
            LinearGradientPaint paint = new LinearGradientPaint(from, end, dist, colors);
            g2d.setPaint(paint);
            g2d.fill(side);

            //draw string.
            String text = this.getFormatedText(element, value);
            if (text != null) {
                int labelX = (int) (xCenter + (radiusBottom + radiusTop) / 2);
                int labelY = (int) (side.getBounds().getCenterY());
                labelX += (count - i);
                if (i == 0) {
                    labelX += g2d.getFont().getSize() / 2;
                }
                g2d.setColor(this.getValueTextColor());
                g2d.setFont(this.getValueTextFont());
                this.paintLabel(g2d,
                        TWaverConst.ORIENTATION_WEST,
                        text,
                        this.getValueTextColor(),
                        this.getValueTextFont(),
                        labelX,
                        labelY,
                        TWaverConst.POSITION_RIGHT);
            }

            heightCursor -= elementHeight;
        }
    }

    protected void paintLabel(Graphics2D g, int orientation, String text, Color color,
            Font font, int x, int y, int position) {
        if (text == null || color == null || font == null) {
            return;
        }
        JLabel label = RotatableLabel.getLabelRenderer(orientation);
        label.setForeground(color);
        label.setFont(font);
        label.setText(text);
        Dimension size = label.getPreferredSize();
        switch (position) {
            case TWaverConst.POSITION_BOTTOM:
                this.paintComponent(g, label, x - size.width / 2, y, size.width, size.height);
                break;
            case TWaverConst.POSITION_BOTTOMLEFT:
                this.paintComponent(g, label, x - size.width, y, size.width, size.height);
                break;
            case TWaverConst.POSITION_BOTTOMRIGHT:
                this.paintComponent(g, label, x, y, size.width, size.height);
                break;
            case TWaverConst.POSITION_CENTER:
                this.paintComponent(g, label, x - size.width / 2, y - size.height / 2, size.width, size.height);
                break;
            case TWaverConst.POSITION_TOP:
                this.paintComponent(g, label, x - size.width / 2, y - size.height, size.width, size.height);
                break;
            case TWaverConst.POSITION_LEFT:
                this.paintComponent(g, label, x - size.width, y - size.height / 2, size.width, size.height);
                break;
            case TWaverConst.POSITION_RIGHT:
                this.paintComponent(g, label, x, y - size.height / 2, size.width, size.height);
                break;
            case TWaverConst.POSITION_TOPLEFT:
                this.paintComponent(g, label, x - size.width, y - size.height, size.width, size.height);
                break;
            case TWaverConst.POSITION_TOPRIGHT:
                this.paintComponent(g, label, x, y - size.height, size.width, size.height);
                break;
            default:
                this.paintComponent(g, label, x - size.width / 2, y - size.height / 2, size.width, size.height);
                break;
        }
    }

    @Override
    protected void calculate() {
    }

    public boolean isUseGap() {
        return useGap;
    }

    public void setUseGap(boolean useGap) {
        this.useGap = useGap;
        this.repaint();
    }

    public boolean isUseHollow() {
        return useHollow;
    }

    public void setUseHollow(boolean useHollow) {
        this.useHollow = useHollow;
    }
}
