package ui.output;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * DragPanel Class
 * 
 * author:zhangtao mail:zht_dream@hotmail.com 2007-9-26
 */
public class DragPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	JPanel north_JP;

	JScrollPane cen_JP;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setContentPane(new DragPanel());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	public DragPanel() {
		this.setLayout(new BorderLayout());
		north_JP = new JPanel();
		north_JP.add(new JButton("1"));
		north_JP.add(new JButton("2"));
		north_JP.add(new JButton("3"));
		north_JP.add(new JButton("4"));
		this.add("North", north_JP);
		cen_JP = new JScrollPane();
		cen_JP.setViewportView(new JTree());
		this.add(cen_JP);
		north_JP.addMouseListener(this);
		north_JP.addMouseMotionListener(this);

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		System.out.println("exit");
		this.setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		this.setCursor(Cursor.getDefaultCursor());
	}

	boolean dragFlag = false;

	public void mouseDragged(MouseEvent e) {
		dragFlag = true;
		if (flag) {
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			Point po = e.getPoint();
			Dimension di = north_JP.getPreferredSize();
			north_JP.setPreferredSize(new Dimension(di.width,
					(north_JP.getY() + po.y)));
			north_JP.revalidate();
			north_JP.repaint();
		}
	}

	boolean flag = false;

	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();
		this.setCursor(Cursor.getDefaultCursor());
		flag = false;
		dragFlag = false;
		if (((north_JP.getY() + north_JP.getHeight() - point.y) < 5)
				&& ((north_JP.getY() + north_JP.getHeight() - point.y) > -5)) {
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			flag = true;
		} else {
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

}