package ui.conf.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.conf.model.MyPrompt;
import ui.conf.model.MyTextField;
import ui.conf.model.MyTitle;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 130, 126, 0};
		gridBagLayout.rowHeights = new int[]{62, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new MyTitle("\u8BBE\u7F6E\u7B97\u6CD5\u7684\u6C42\u89E3\u7CBE\u5EA6");
		titleLabel.setText("\u8BBE\u7F6E\u7B97\u6CD5\u7CBE\u5EA6");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		
		JLabel maxGenerationLabel = new MyPrompt("\u6700\u5927\u6F14\u5316\u4EE3\u6570");
		GridBagConstraints gbc_maxGenerationLabel = new GridBagConstraints();
		gbc_maxGenerationLabel.anchor = GridBagConstraints.WEST;
		gbc_maxGenerationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_maxGenerationLabel.gridx = 1;
		gbc_maxGenerationLabel.gridy = 1;
		add(maxGenerationLabel, gbc_maxGenerationLabel);
		
		accuracytextField = new MyTextField();
		GridBagConstraints gbc_accuracytextField = new GridBagConstraints();
		gbc_accuracytextField.anchor = GridBagConstraints.WEST;
		gbc_accuracytextField.insets = new Insets(0, 0, 5, 0);
		gbc_accuracytextField.gridx = 2;
		gbc_accuracytextField.gridy = 1;
		add(accuracytextField, gbc_accuracytextField);
		
		
		JLabel accuracyLabel = new MyPrompt("\u6C42\u89E3\u7CBE\u5EA6");
		GridBagConstraints gbc_accuracyLabel = new GridBagConstraints();
		gbc_accuracyLabel.anchor = GridBagConstraints.WEST;
		gbc_accuracyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_accuracyLabel.gridx = 1;
		gbc_accuracyLabel.gridy = 2;
		add(accuracyLabel, gbc_accuracyLabel);
		
		generationNumTextField = new MyTextField();
		GridBagConstraints gbc_generationNumTextField = new GridBagConstraints();
		gbc_generationNumTextField.anchor = GridBagConstraints.WEST;
		gbc_generationNumTextField.insets = new Insets(0, 0, 5, 0);
		gbc_generationNumTextField.gridx = 2;
		gbc_generationNumTextField.gridy = 2;
		add(generationNumTextField, gbc_generationNumTextField);
		
		
		JLabel selectionRangeLabel = new MyPrompt("\u9009\u62E9\u8303\u56F4");
		GridBagConstraints gbc_selectionRangeLabel = new GridBagConstraints();
		gbc_selectionRangeLabel.anchor = GridBagConstraints.WEST;
		gbc_selectionRangeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_selectionRangeLabel.gridx = 1;
		gbc_selectionRangeLabel.gridy = 3;
		add(selectionRangeLabel, gbc_selectionRangeLabel);
		
		selectionRangeTextField = new MyTextField();
		GridBagConstraints gbc_selectionRangeTextField = new GridBagConstraints();
		gbc_selectionRangeTextField.anchor = GridBagConstraints.WEST;
		gbc_selectionRangeTextField.gridx = 2;
		gbc_selectionRangeTextField.gridy = 3;
		add(selectionRangeTextField, gbc_selectionRangeTextField);
		
		
	}

}
