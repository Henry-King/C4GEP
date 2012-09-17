package ui.conf;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

public class AccuracyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8245739950136079772L;
	private JTextField generationNumTextField;
	private JTextField selectionRangeTextField;
	private JTextField accuracytextField;

	/**
	 * Create the panel.
	 */
	public AccuracyPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component glue = Box.createGlue();
		add(glue);
		
		Box titleHorizontalBox = Box.createHorizontalBox();
		add(titleHorizontalBox);
		
		JLabel titleLabel = new JLabel("\u8BBE\u7F6E\u7B97\u6CD5\u7684\u6C42\u89E3\u7CBE\u5EA6");
		titleHorizontalBox.add(titleLabel);
		
		Component glue_5 = Box.createGlue();
		add(glue_5);
		
		Box generationHorizontalBox = Box.createHorizontalBox();
		add(generationHorizontalBox);
		
		JLabel maxGenerationLabel = new JLabel("\u6700\u5927\u6F14\u5316\u4EE3\u6570");
		generationHorizontalBox.add(maxGenerationLabel);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		generationHorizontalBox.add(rigidArea_2);
		
		generationNumTextField = new JTextField();
		generationHorizontalBox.add(generationNumTextField);
		generationNumTextField.setMaximumSize(new Dimension(126, 21));
		generationNumTextField.setColumns(20);
		
		Component glue_1 = Box.createGlue();
		add(glue_1);
		
		Box selectionRangeHorizontalBox = Box.createHorizontalBox();
		add(selectionRangeHorizontalBox);
		
		JLabel selectionRangeLabel = new JLabel("\u9009\u62E9\u8303\u56F4");
		selectionRangeHorizontalBox.add(selectionRangeLabel);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(40, 20));
		selectionRangeHorizontalBox.add(rigidArea_1);
		
		selectionRangeTextField = new JTextField();
		selectionRangeHorizontalBox.add(selectionRangeTextField);
		selectionRangeTextField.setMaximumSize(new Dimension(126, 21));
		selectionRangeTextField.setColumns(20);
		
		Component glue_2 = Box.createGlue();
		add(glue_2);
		
		Box accuracyHorizontalBox = Box.createHorizontalBox();
		add(accuracyHorizontalBox);
		
		JLabel accuracyLabel = new JLabel("\u6C42\u89E3\u7CBE\u5EA6");
		accuracyHorizontalBox.add(accuracyLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(40, 20));
		accuracyHorizontalBox.add(rigidArea);
		
		accuracytextField = new JTextField();
		accuracyHorizontalBox.add(accuracytextField);
		accuracytextField.setMaximumSize(new Dimension(126, 21));
		accuracytextField.setColumns(20);
		
		Component glue_3 = Box.createGlue();
		add(glue_3);
		
		Box admitHorizontalBox = Box.createHorizontalBox();
		add(admitHorizontalBox);
		
		Component glue_4 = Box.createGlue();
		add(glue_4);

	}

}
