package ui.conf.view;

import javax.swing.*;

import ui.app.*;

import java.awt.*;

public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;

	
	/**
	 * Create the panel.
	 */
	public ConfPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);

		MainToolBar toolBar = new MainToolBar();
		toolBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		TLPanel.add(toolBar, BorderLayout.NORTH);

		JLabel outputStatusLabel = new JLabel("这里显示状态信息");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));
		TLPanel.add(outputStatusLabel, BorderLayout.SOUTH);
		/*************************************************************************************************************/

		
		
		
		
		/*JDesktopPane   desktopPane   =   new   JDesktopPane();
		desktopPane.setVisible(true);
		desktopPane.setLayout(new BorderLayout(0, 0));
		desktopPane.setDesktopManager(new   OutlineManager());
		add(desktopPane,BorderLayout.CENTER);
		
		  JInternalFrame itnFrame = new JInternalFrame();
		  itnFrame.setResizable(true); 
		  
		  
		  
		 itnFrame.setVisible(true); */
		 
		 //BasicInternalFrameUI ui = (BasicInternalFrameUI) itnFrame.getUI();
		  //ui.setNorthPane(null);//去掉frame的标题栏 
		  //add(itnFrame,BorderLayout.CENTER);
		
		
		
		ContentPanel contentPanel = new ContentPanel();
		//contentPanel.setLayout(new GridLayout(2,1));
		add(contentPanel);
		
		

	/*class OutlineManager extends DefaultDesktopManager {
		private Rectangle start, last;
		private boolean first = true;

		// dragging...
		public void beginDraggingFrame(JComponent frame) {
			initializeOutline(frame);
		}

		public void dragFrame(JComponent frame, int x, int y) {
			updateOutline(frame, x, y, start.width, start.height);
		}

		public void endDraggingFrame(JComponent frame) {
			endOutline(frame);
		}

		// resizing...
		public void beginResizingFrame(JComponent frame, int dir) {
			initializeOutline(frame);
		}

		public void resizeFrame(JComponent frame, int x, int y, int w, int h) {
			updateOutline(frame, x, y, w, h);
		}

		public void endResizingFrame(JComponent frame) {
			endOutline(frame);
		}

		// outline...
		private void initializeOutline(final JComponent frame) {
			frame.setVisible(false);
			start = frame.getBounds();
			last = new Rectangle(start);
			first = true;

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					updateOutline(frame, start.x, start.y, start.width,
							start.height);
				}
			});
		}

		public void updateOutline(JComponent frame, int x, int y, int w, int h) {
			Container _container = frame.getParent();
			Graphics g = _container.getGraphics();

			try {
				g.setXORMode(_container.getBackground());
				if (!first) {
					g.drawRect(last.x, last.y, last.width - 1, last.height - 1);
				}
				g.drawRect(x, y, w - 1, h - 1);
				first = false;
			} finally {
				g.dispose();
				last.setBounds(x, y, w, h);
			}
		}

		public void endOutline(JComponent frame) {
			frame.setVisible(true);
			setBoundsForFrame(frame, last.x, last.y, last.width, last.height);
		}*/
	}

}
