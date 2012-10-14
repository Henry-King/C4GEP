package ui.customized;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import twaver.Element;
import twaver.TWaverConst;
import twaver.chart.PieChart;

public class StylePieChart extends PieChart {

    public static final int STYLE_EDGE = 1;
    public static final int STYLE_DISK = 2;
    private int pieStyle = STYLE_EDGE;

    public StylePieChart() {
        this.setLegendOrientation(TWaverConst.LABEL_ORIENTATION_RIGHT);
        this.setHollow(true);
        this.setHollowPercent(0.3);
        this.setShadowColor(new Color(255, 255, 255, 0));
        this.getLegendPane().setVisible(false);
        this.setValueTextPercent(false);
        this.set3D(false);
        this.setSelectedOffset(0);
        this.setSelectedStroke(TWaverConst.STROKE_SOLID_0);
        this.setValueTextVisible(false);
        this.setValueTextColor(Color.white);
    }

    @Override
    protected String getFormatedText(Element element, double value) {
        String text = super.getFormatedText(element, value);
        return text + "%";
    }

    @Override
    protected void drawBody(Graphics2D g2d,
            int width, int height, double start,
            Element element, double value) {
        if (!this.isAppearable(element)) {
            return;
        }
        Rectangle2D drawBound = this.getDrawBound();
        double size = Math.min(drawBound.getWidth(), drawBound.getHeight());
        Shape shape = this.createPie(
                drawBound.getX() + (drawBound.getWidth() - size) / 2,
                drawBound.getY(),
                size,
                size,
                start, value);
        Color color = getColor(element);
        float radius = (float) size / 2;
        if (radius > 0) {
            if (pieStyle == STYLE_EDGE) {
                //fill pie.
                float centerY = (float) drawBound.getCenterY();
                Point2D from = new Point2D.Float(0, centerY - radius);
                Point2D end = new Point2D.Float(0, centerY + radius * 1.5f);
                float[] dist = {0.0f, 0.5f, 1.0f};
                Color[] colors = {color.darker().darker(), color, Color.white};
                LinearGradientPaint paint = new LinearGradientPaint(from, end, dist, colors);
                g2d.setColor(color);
                g2d.draw(shape);
                g2d.setPaint(paint);
                g2d.fill(shape);

                //fill edge ring.
                float edgeThick = (float) size / 15f;
                Shape smallerPie = this.createPie(
                        drawBound.getX() + (drawBound.getWidth() - size) / 2 + edgeThick,
                        drawBound.getY() + edgeThick,
                        size - edgeThick * 2,
                        size - edgeThick * 2,
                        start, value);
                Area edge = new Area(shape);
                edge.subtract(new Area(smallerPie));
                g2d.setColor(color);
                g2d.fill(edge);
                paintText(g2d, getFormatedText(element, value),
                        shape.getBounds().getCenterX(),
                        shape.getBounds().getCenterY());
            }
            if (pieStyle == STYLE_DISK) {
                Point2D center = new Point2D.Double(drawBound.getCenterX(), drawBound.getY() + radius);
                float[] dist = {0.0f, 0.49f, 0.5f, 0.85f, 0.86f, 1.0f};
                Color[] colors = {Color.white, color, color, color.darker(), color, color};
                RadialGradientPaint paint = new RadialGradientPaint(center, radius, dist, colors);
                g2d.setPaint(paint);
                g2d.fill(shape);
            }
        }
    }

    private void paintText(Graphics2D g2d, String text, double pieCenterX, double pieCenterY) {
        g2d.setFont(this.getValueTextFont());
        Rectangle2D textBounds = g2d.getFontMetrics().getStringBounds(text, g2d);
        double x = pieCenterX - textBounds.getWidth() / 2;
        double y = pieCenterY - textBounds.getHeight() / 2;
        y += g2d.getFont().getSize();

        g2d.setColor(this.getValueTextColor());
        g2d.drawString(text, (int) x, (int) y);
    }

    private Shape createPie(double x, double y, double w, double h, double start, double extent) {
        Arc2D.Double shape = new Arc2D.Double(x, y, w, h, start, extent, Arc2D.PIE);
        if (this.isHollow()) {
            Area area = new Area(shape);
            double cx = shape.getCenterX();
            double cy = shape.getCenterY();
            double sw = shape.getWidth() * this.getHollowPercent();
            double sh = shape.getHeight() * this.getHollowPercent();
            Ellipse2D.Double ellipse = new Ellipse2D.Double(cx - sw / 2, cy - sh / 2, sw, sh);
            area.subtract(new Area(ellipse));
            return area;
        } else {
            return shape;
        }
    }

    public int getPieStyle() {
        return pieStyle;
    }

    public void setPieStyle(int pieStyle) {
        this.pieStyle = pieStyle;
        this.repaint();
    }
}
