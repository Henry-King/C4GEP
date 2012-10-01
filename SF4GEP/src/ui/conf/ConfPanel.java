package ui.conf;

import javax.swing.*;

import ui.app.*;
import ui.conf.view.ContentPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.border.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;

	private MainWnd mainWnd;
	
	
	/**
	 * Create the panel.
	 */
	public ConfPanel(MainWnd mainWnd) {
		setLayout(new BorderLayout(0, 0));
		this.mainWnd = mainWnd;
		
		
		/*�������*/
		ContentPanel contentPanel = new ContentPanel();
		add(contentPanel);
		
		
		/*���ߺ�״̬��*/
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);

		
		
		MainToolBar toolBar = new MainToolBar(mainWnd,contentPanel);
		toolBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		TLPanel.add(toolBar, BorderLayout.NORTH);

		JLabel outputStatusLabel = new JLabel("������ʾ״̬��Ϣ");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10,
				10));
		TLPanel.add(outputStatusLabel, BorderLayout.SOUTH);
//end of tool panel and status panel
		
	
		
		
		
		
	}

}
