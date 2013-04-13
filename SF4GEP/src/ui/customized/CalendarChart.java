package ui.customized;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

import twaver.TWaverConst;
import twaver.TWaverUtil;

public class CalendarChart extends JLabel {

    private SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE", TWaverConst.EN_US);
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", TWaverConst.EN_US);
    private Image image = TWaverUtil.getImage("/demo/chart/images/calendar.png");
    private Color fontColor = Color.white;
    private Date date = new Date();

    public CalendarChart() {
        this(new Date());
    }

    public CalendarChart(Date date) {
        this.date = date;
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

        //draw week.
        String week = weekFormat.format(date);
        int weekFontSize = this.getHeight() / 10;
        Font weekFont = new Font("Tahoma", Font.BOLD, weekFontSize);
        g.setFont(weekFont);
        int textBoundWidth = (int) g2d.getFontMetrics().getStringBounds(week, g).getWidth();
        int x = (this.getWidth() - textBoundWidth) / 2;
        int y = (int) (weekFontSize * 2.5);
        g.setColor(fontColor);
        g2d.drawString(week, x, y);

        //draw date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
        int dayFontSize = this.getHeight() / 2;
        Font dayFont = new Font("Calibri", Font.BOLD, dayFontSize);
        g.setFont(dayFont);
        textBoundWidth = (int) g2d.getFontMetrics().getStringBounds(day, g).getWidth();
        x = (this.getWidth() - textBoundWidth) / 2;
        y = (int) (dayFontSize * 1.3);
        g.setColor(fontColor);
        g2d.drawString(day, x, y);

        //draw year, month.
        String year = "" + calendar.get(Calendar.YEAR);
        String month = monthFormat.format(date);
        String yearMonth = year + "  " + month;
        int yearFontSize = this.getHeight() / 6;
        Font yearFont = new Font("Calibri", Font.BOLD, yearFontSize);
        g.setFont(yearFont);
        textBoundWidth = (int) g2d.getFontMetrics().getStringBounds(yearMonth, g).getWidth();
        x = (this.getWidth() - textBoundWidth) / 2;
        y = (int) (yearFontSize * 5) + yearFontSize / 2;
        g.setColor(fontColor);
        g2d.drawString(yearMonth, x, y);
    }
}
