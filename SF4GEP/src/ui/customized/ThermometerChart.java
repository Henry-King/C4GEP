package ui.customized;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import twaver.Element;
import twaver.TWaverConst;
import twaver.chart.AbstractChart;

public class ThermometerChart extends AbstractChart {

    private Color gradientFillColor = Color.DARK_GRAY;
    private boolean scaleVisible = true;
    private Color scaleLineColor = Color.DARK_GRAY;
    private Color scaleTextColor = Color.GRAY;

    protected void paintThermometer(Graphics2D g2d, Element element, Rectangle rect) {
        double textHeight = 20;
        JLabel label = new JLabel(element.getName());
        label.setFont(getValueTextFont());
        label.setForeground(getColor(element));
        Dimension labelSize = label.getPreferredSize();
        labelSize.width += 100;
        double cx = rect.getCenterX();
        int labelX = (int) (cx - labelSize.getWidth() / 2) + 50;
        int labelY = (int) (rect.getY() + textHeight / 2.0 - labelSize.getHeight() / 2);
        int labelW = labelSize.width > rect.width ? rect.width : labelSize.width;
        paintComponent(g2d, label, labelX, labelY, labelW, labelSize.height);

        double tWidth = this.getWidth() / 10;
        if (tWidth * 2 > rect.width) {
            tWidth = rect.getWidth() / 2;
        }
        double ellipseR = tWidth * 1.5 / 2;
        Rectangle2D gRect = new Rectangle2D.Double(rect.x, rect.y + textHeight + 2, rect.width, rect.height - textHeight - 4);
        double gcx = gRect.getCenterX();
        Rectangle2D pipeRect = new Rectangle2D.Double(
                gcx - tWidth / 2,
                gRect.getY(),
                tWidth,
                gRect.getHeight() - ellipseR);
        Paint paint = new GradientPaint(
                (float) (pipeRect.getX() + pipeRect.getWidth() / 4),
                0, gradientColor,
                (float) (pipeRect.getX() + pipeRect.getWidth()),
                0,
                gradientFillColor,
                true);
        g2d.setPaint(paint);
        g2d.fill(pipeRect);

        Ellipse2D ball = new Ellipse2D.Double(
                gcx - ellipseR,
                rect.getY() + rect.getHeight() - ellipseR * 2,
                ellipseR * 2,
                ellipseR * 2);

        Area outline = new Area(pipeRect);
        outline.add(new Area(ball));

        Ellipse2D ballLighter = new Ellipse2D.Double(
                gcx - ellipseR / 1.5,
                rect.getY() + rect.getHeight() - ellipseR * 1.9,
                ellipseR * 1.2,
                ellipseR / 1.5);

        Rectangle2D valueRect = new Rectangle2D.Double(
                pipeRect.getX(),
                pipeRect.getY(),
                pipeRect.getWidth(),
                pipeRect.getHeight() - ellipseR);

        if (scaleVisible) {
            double gap = valueRect.getHeight() / 10;
            g2d.setStroke(new BasicStroke(1.2f));
            g2d.setColor(scaleLineColor);
            for (int i = 0; i <= 10; i += 3) {
                Line2D line = new Line2D.Double(
                        valueRect.getX() + valueRect.getWidth() + 1 + 3,
                        valueRect.getY() + i * gap,
                        valueRect.getX() + valueRect.getWidth() + 5 + 3,
                        valueRect.getY() + i * gap);
                g2d.draw(line);

                double percent = (10 - i) / 10.0 * 100;
                label = new JLabel(getFormatedYScaleText(percent));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setFont(getValueTextFont());
                label.setForeground(scaleTextColor);
                labelX = (int) (valueRect.getX() + valueRect.getWidth() - 75);
                labelY = (int) (valueRect.getY() + i * gap);
                paintComponent(g2d, label, labelX, labelY - label.getPreferredSize().height / 2, labelSize.width, labelSize.height);
            }
        }
        double value = getValue(element);
        double h = value * valueRect.getHeight();
        Rectangle2D percentRect = new Rectangle2D.Double(
                pipeRect.getX(),
                valueRect.getY() + valueRect.getHeight() - h,
                valueRect.getWidth(),
                h + ellipseR);
        paint = new GradientPaint(
                (float) (pipeRect.getX() + pipeRect.getWidth() / 4),
                0,
                gradientColor,
                (float) (pipeRect.getX() + pipeRect.getWidth()),
                0,
                getColor(element),
                true);
        g2d.setPaint(paint);
        g2d.fill(percentRect);

        paint = new GradientPaint(
                (float) (ball.getX() - 2),
                0,
                gradientColor,
                (float) (ball.getX() + ball.getWidth()),
                0,
                getColor(element),
                true);
        g2d.setPaint(paint);
        g2d.fill(ball);

        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.fill(ballLighter);

        //outline.
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.draw(outline);
    }

    protected String getFormatedYScaleText(double percent) {
        return percent + "";
    }

    public void paintChart(Graphics2D g2d, int width, int height) {
        g2d.setStroke(TWaverConst.BASIC_STROKE);
        backgroundBounds.x = xGap;
        backgroundBounds.y = yGap;
        backgroundBounds.width = width - 2 * xGap;
        backgroundBounds.height = height - 2 * yGap;
        this.paintBackground(g2d);
        int count = this.publishedElements.size();
        if (count == 0) {
            return;
        }
        double pwidth = backgroundBounds.width / count;
        for (int i = 0; i < count; i++) {
            Element element = (Element) this.publishedElements.get(i);
            Rectangle rect = new Rectangle((int) (backgroundBounds.x + i * pwidth), backgroundBounds.y, (int) pwidth, backgroundBounds.height);
            paintThermometer(g2d, element, rect);
        }
    }

    protected void calculate() {
    }

    public Color getGradientFillColor() {
        return gradientFillColor;
    }

    public void setGradientFillColor(Color gradientFillColor) {
        this.gradientFillColor = gradientFillColor;
    }

    public boolean isScaleVisible() {
        return scaleVisible;
    }

    public void setScaleVisible(boolean scaleVisible) {
        this.scaleVisible = scaleVisible;
    }
}
