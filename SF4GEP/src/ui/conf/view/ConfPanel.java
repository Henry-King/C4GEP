package ui.conf.view;

import javax.swing.*;

import ui.app.*;

import java.awt.*;

public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;

	private MainWnd mainWnd;
	private MainToolBar toolBar;
	private ContentPanel contentPanel;
	
	/**
	 * Create the panel.
	 */
	public ConfPanel(MainWnd mainWnd) {
		setLayout(new BorderLayout(0, 0));
		this.mainWnd = mainWnd;
		
		
		/*内容面板*/
		contentPanel = new ContentPanel();
		add(contentPanel);
		
		
		/*工具和状态栏*/
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);

		toolBar = new MainToolBar(mainWnd,contentPanel);
		toolBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		TLPanel.add(toolBar, BorderLayout.NORTH);

		JLabel outputStatusLabel = new JLabel("这里显示状态信息");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));
		TLPanel.add(outputStatusLabel, BorderLayout.SOUTH);

		
	
		
		
		
		
	}

}
