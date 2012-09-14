package ui.output;

import java.awt.*;
import javax.swing.*;
import ui.app.*;

public class OutputPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public OutputPanel() {
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel TLPanel = new JPanel();
		TLPanel.setLayout(new BorderLayout(0, 0));
		add(TLPanel, BorderLayout.NORTH);
		
		
		
		
		
		MainToolBar toolBar = new MainToolBar();
		toolBar.setBorder(BorderFactory.createEmptyBorder(10,20,10,0));
		TLPanel.add(toolBar,BorderLayout.NORTH);
		
		JLabel outputStatusLabel = new JLabel("这里显示状态信息");
		outputStatusLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
		TLPanel.add(outputStatusLabel,BorderLayout.SOUTH);
		
		
		
		JPanel OutputPicturePanel = new JPanel();
		add(OutputPicturePanel, BorderLayout.CENTER);
		OutputPicturePanel.setLayout(new GridLayout(1,2));
		
		
		
		
		JPanel FittingCurvePanel = new JPanel();
		OutputPicturePanel.add(FittingCurvePanel);
		
		
		JPanel EvolutionGraphPanel = new JPanel();
		OutputPicturePanel.add(EvolutionGraphPanel);
	}
	
	
	

}
