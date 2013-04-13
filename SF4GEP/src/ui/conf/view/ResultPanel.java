package ui.conf.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import ui.conf.model.MyPrompt;
import ui.conf.model.MyTitle;

public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5437823933650184847L;

	/**
	 * Create the panel.
	 */
	public ResultPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 130, 123, 0};
		gridBagLayout.rowHeights = new int[]{62, 40, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		MyTitle myTitle = new MyTitle();
		myTitle.setText("\u8FD0\u7B97\u7ED3\u679C");
		GridBagConstraints gbc_myTitle = new GridBagConstraints();
		gbc_myTitle.gridwidth = 2;
		gbc_myTitle.insets = new Insets(0, 0, 5, 0);
		gbc_myTitle.gridx = 1;
		gbc_myTitle.gridy = 0;
		add(myTitle, gbc_myTitle);
		
		MyPrompt generationLabel = new MyPrompt();
		generationLabel.setText("\u603B\u4EE3\u6570");
		GridBagConstraints gbc_generationLabel = new GridBagConstraints();
		gbc_generationLabel.anchor = GridBagConstraints.WEST;
		gbc_generationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generationLabel.gridx = 1;
		gbc_generationLabel.gridy = 1;
		add(generationLabel, gbc_generationLabel);
		
		MyPrompt generation = new MyPrompt();
		GridBagConstraints gbc_generation = new GridBagConstraints();
		gbc_generation.insets = new Insets(0, 0, 5, 0);
		gbc_generation.gridx = 2;
		gbc_generation.gridy = 1;
		add(generation, gbc_generation);
		
		MyPrompt timeLabel = new MyPrompt();
		timeLabel.setText("\u6240\u7528\u65F6\u95F4");
		GridBagConstraints gbc_timeLabel = new GridBagConstraints();
		gbc_timeLabel.anchor = GridBagConstraints.WEST;
		gbc_timeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_timeLabel.gridx = 1;
		gbc_timeLabel.gridy = 2;
		add(timeLabel, gbc_timeLabel);
		
		MyPrompt time = new MyPrompt();
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.insets = new Insets(0, 0, 5, 0);
		gbc_time.gridx = 2;
		gbc_time.gridy = 2;
		add(time, gbc_time);
		
		MyPrompt exprLabel = new MyPrompt();
		exprLabel.setText("\u8868\u73B0\u578B");
		GridBagConstraints gbc_exprLabel = new GridBagConstraints();
		gbc_exprLabel.anchor = GridBagConstraints.WEST;
		gbc_exprLabel.insets = new Insets(0, 0, 5, 5);
		gbc_exprLabel.gridx = 1;
		gbc_exprLabel.gridy = 3;
		add(exprLabel, gbc_exprLabel);
		
		MyPrompt expression = new MyPrompt();
		GridBagConstraints gbc_expression = new GridBagConstraints();
		gbc_expression.insets = new Insets(0, 0, 5, 0);
		gbc_expression.gridx = 2;
		gbc_expression.gridy = 3;
		add(expression, gbc_expression);
		
		MyPrompt geneLabel = new MyPrompt();
		geneLabel.setText("\u57FA\u56E0\u578B");
		GridBagConstraints gbc_geneLabel = new GridBagConstraints();
		gbc_geneLabel.anchor = GridBagConstraints.WEST;
		gbc_geneLabel.insets = new Insets(0, 0, 0, 5);
		gbc_geneLabel.gridx = 1;
		gbc_geneLabel.gridy = 4;
		add(geneLabel, gbc_geneLabel);
		
		MyPrompt gene = new MyPrompt();
		GridBagConstraints gbc_gene = new GridBagConstraints();
		gbc_gene.gridx = 2;
		gbc_gene.gridy = 4;
		add(gene, gbc_gene);

	}

}
