package ui.app;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LinkLabel extends JLabel {
	private String text,url;
	private boolean isSupported;
	
	
	
	
	public LinkLabel(String text) {
		this.text = text;
		init();
	}
	
	private void init(){
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
		});
	}
	
	
	

	public LinkLabel(String text, String url) {
		this.text = text;
		this.url = url;
		init();
		addMouseListener(new MouseAdapter() {
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
		jp.add(new LinkLabel("����QQ", "http://www.qq.com"));
		jf.setContentPane(jp);
		jf.pack();
		jf.setVisible(true);
	}
}