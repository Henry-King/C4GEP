package ui.app;

import java.awt.*;
import java.awt.event.*;


import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.multi.*;

import ui.app.*;

public class LinkLabel extends JLabel {
	private String text, url;
	private boolean isSupported;
	
	public LinkLabel(String text) {
		this.text = text;
		
		setText(false);
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setText(isSupported);
				if (isSupported)
					setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				setText(false);
			}

//			public void mouseClicked(MouseEvent e) {
//
//			}
		});
	}
	
	
	

	public LinkLabel(String text, String url) {
		this.text = text;
		this.url = url;
		try {
			this.isSupported = Desktop.isDesktopSupported()
					&& Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
		} catch (Exception e) {
			this.isSupported = false;
		}
		setText(false);
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setText(isSupported);
				if (isSupported)
					setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				setText(false);
			}

			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(
							new java.net.URI(LinkLabel.this.url));
				} catch (Exception ex) {
				}
			}
		});
	}

	private void setText(boolean b) {
		if (!b)
			setText("<html><font color=blue>" + text);
		else
			setText("<html><font color=red>" + text);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("test");
		JPanel jp = new JPanel();
		jp.add(new LinkLabel("Á¬½ÓQQ", "http://www.qq.com"));
		jf.setContentPane(jp);
		jf.pack();
		jf.setVisible(true);
	}
}