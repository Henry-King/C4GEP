/*
 * Copyright 2006 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package ui.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.images.ImageHelper;

/**
 * @author Michael Hagen
 */
public class SplashPanel extends JPanel {
    private ImageIcon splashImage = ImageHelper.loadImage("splash.jpg");
    private Dimension size = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());
    private String loadString = "loading...";
    
    
    public SplashPanel() {
        super();
        setForeground(new Color(233, 115, 103));
        setFont(new Font("Century", Font.PLAIN, 28));
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public void paint(Graphics g) {
        //Graphics2D g2D = (Graphics2D)g;
        splashImage.paintIcon(this, g, 0, 0);
        
        int x = 10;
        int y = splashImage.getIconHeight()-10;
        g.setFont(new Font("Century", Font.PLAIN, 14));
        g.setColor(Color.WHITE);
        g.drawString(loadString, x, y);

        
        /*Object rh = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(getFont());
        g.setColor(Color.black);
        g.drawString(com.jtattoo.plaf.About.JTATTOO_VERSION, x + 3, y + 2);
        g.setColor(getForeground());
        g.drawString(com.jtattoo.plaf.About.JTATTOO_VERSION, x, y);
        g.drawString(com.jtattoo.plaf.About.JTATTOO_VERSION, x + 1, y);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, rh);*/
        
    }

	public String getLoadString() {
		return loadString;
	}

	public void setLoadString(String loadString) {
		this.loadString = loadString;
		updata();
	}
	
	  public void updata() {
	            Graphics g = this.getGraphics();
	            paint(g);
	  }
	

}
