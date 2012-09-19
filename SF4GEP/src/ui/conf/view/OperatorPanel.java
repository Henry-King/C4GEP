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

public class OperatorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2305610876923730049L;
	private JTextField mutateTextField;
	private JTextField ISTextField;
	private JTextField ISLengthTextField;
	private JTextField RISTextField;
	private JTextField RISLengthTextField;
	private JTextField oneCombineTextField;
	private JTextField twoCombineTextField;
	private JTextField geneCombineTextField;
	private JTextField geneTextField;

	/**
	 * Create the panel.
	 */
	public OperatorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 168, 94, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new MyTitle("\u8F93\u5165\u53D8\u5F02\u53C2\u6570");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.anchor = GridBagConstraints.NORTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JLabel mutateLabel = new MyPrompt("\u53D8\u5F02\u6982\u7387   ");
		GridBagConstraints gbc_mutateLabel = new GridBagConstraints();
		gbc_mutateLabel.anchor = GridBagConstraints.WEST;
		gbc_mutateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mutateLabel.gridx = 1;
		gbc_mutateLabel.gridy = 1;
		add(mutateLabel, gbc_mutateLabel);
		
		mutateTextField = new MyTextField();
		GridBagConstraints gbc_mutateTextField = new GridBagConstraints();
		gbc_mutateTextField.anchor = GridBagConstraints.WEST;
		gbc_mutateTextField.insets = new Insets(0, 0, 5, 0);
		gbc_mutateTextField.gridx = 2;
		gbc_mutateTextField.gridy = 1;
		add(mutateTextField, gbc_mutateTextField);
		mutateTextField.setColumns(10);
		mutateTextField.setMaximumSize(mutateTextField.getPreferredSize());
		
		JLabel ISLabel = new MyPrompt("IS\u8F6C\u5EA7\u6982\u7387 ");
		GridBagConstraints gbc_ISLabel = new GridBagConstraints();
		gbc_ISLabel.anchor = GridBagConstraints.WEST;
		gbc_ISLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ISLabel.gridx = 1;
		gbc_ISLabel.gridy = 2;
		add(ISLabel, gbc_ISLabel);
		
		ISTextField = new MyTextField();
		GridBagConstraints gbc_ISTextField = new GridBagConstraints();
		gbc_ISTextField.anchor = GridBagConstraints.WEST;
		gbc_ISTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ISTextField.gridx = 2;
		gbc_ISTextField.gridy = 2;
		add(ISTextField, gbc_ISTextField);
		ISTextField.setColumns(10);
		ISTextField.setMaximumSize(ISTextField.getPreferredSize());
		
		JLabel ISLengthLabel = new MyPrompt("IS\u8F6C\u5EA7\u957F\u5EA6 ");
		GridBagConstraints gbc_ISLengthLabel = new GridBagConstraints();
		gbc_ISLengthLabel.anchor = GridBagConstraints.WEST;
		gbc_ISLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ISLengthLabel.gridx = 1;
		gbc_ISLengthLabel.gridy = 3;
		add(ISLengthLabel, gbc_ISLengthLabel);
		
		ISLengthTextField = new MyTextField();
		GridBagConstraints gbc_ISLengthTextField = new GridBagConstraints();
		gbc_ISLengthTextField.anchor = GridBagConstraints.WEST;
		gbc_ISLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ISLengthTextField.gridx = 2;
		gbc_ISLengthTextField.gridy = 3;
		add(ISLengthTextField, gbc_ISLengthTextField);
		ISLengthTextField.setColumns(10);
		ISLengthTextField.setMaximumSize(ISLengthTextField.getPreferredSize());
		
		JLabel RISLabel = new MyPrompt("RIS\u8F6C\u5EA7\u6982\u7387");
		GridBagConstraints gbc_RISLabel = new GridBagConstraints();
		gbc_RISLabel.anchor = GridBagConstraints.WEST;
		gbc_RISLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RISLabel.gridx = 1;
		gbc_RISLabel.gridy = 4;
		add(RISLabel, gbc_RISLabel);
		
		RISTextField = new MyTextField();
		GridBagConstraints gbc_RISTextField = new GridBagConstraints();
		gbc_RISTextField.anchor = GridBagConstraints.WEST;
		gbc_RISTextField.insets = new Insets(0, 0, 5, 0);
		gbc_RISTextField.gridx = 2;
		gbc_RISTextField.gridy = 4;
		add(RISTextField, gbc_RISTextField);
		RISTextField.setColumns(10);
		RISTextField.setMaximumSize(RISTextField.getPreferredSize());
		
		JLabel RISLengthLabel = new MyPrompt("RIS\u8F6C\u5EA7\u957F\u5EA6");
		GridBagConstraints gbc_RISLengthLabel = new GridBagConstraints();
		gbc_RISLengthLabel.anchor = GridBagConstraints.WEST;
		gbc_RISLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RISLengthLabel.gridx = 1;
		gbc_RISLengthLabel.gridy = 5;
		add(RISLengthLabel, gbc_RISLengthLabel);
		
		RISLengthTextField = new MyTextField();
		GridBagConstraints gbc_RISLengthTextField = new GridBagConstraints();
		gbc_RISLengthTextField.anchor = GridBagConstraints.WEST;
		gbc_RISLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_RISLengthTextField.gridx = 2;
		gbc_RISLengthTextField.gridy = 5;
		add(RISLengthTextField, gbc_RISLengthTextField);
		RISLengthTextField.setColumns(10);
		RISLengthTextField.setMaximumSize(RISLengthTextField.getPreferredSize());
		
		JLabel geneLabel = new MyPrompt("\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
		GridBagConstraints gbc_geneLabel = new GridBagConstraints();
		gbc_geneLabel.anchor = GridBagConstraints.WEST;
		gbc_geneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_geneLabel.gridx = 1;
		gbc_geneLabel.gridy = 6;
		add(geneLabel, gbc_geneLabel);
		
		geneTextField = new MyTextField();
		GridBagConstraints gbc_geneTextField = new GridBagConstraints();
		gbc_geneTextField.anchor = GridBagConstraints.WEST;
		gbc_geneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_geneTextField.gridx = 2;
		gbc_geneTextField.gridy = 6;
		add(geneTextField, gbc_geneTextField);
		geneTextField.setColumns(10);
		geneTextField.setMaximumSize(geneTextField.getPreferredSize());
		
		JLabel oneCombineLabel = new MyPrompt("\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_oneCombineLabel = new GridBagConstraints();
		gbc_oneCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_oneCombineLabel.insets = new Insets(0, 0, 5, 5);
		gbc_oneCombineLabel.gridx = 1;
		gbc_oneCombineLabel.gridy = 7;
		add(oneCombineLabel, gbc_oneCombineLabel);
		
		oneCombineTextField = new MyTextField();
		GridBagConstraints gbc_oneCombineTextField = new GridBagConstraints();
		gbc_oneCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_oneCombineTextField.insets = new Insets(0, 0, 5, 0);
		gbc_oneCombineTextField.gridx = 2;
		gbc_oneCombineTextField.gridy = 7;
		add(oneCombineTextField, gbc_oneCombineTextField);
		oneCombineTextField.setColumns(10);
		oneCombineTextField.setMaximumSize(oneCombineTextField.getPreferredSize());
		
		JLabel twoCombineLabel = new MyPrompt("\u4E24\u70B9\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_twoCombineLabel = new GridBagConstraints();
		gbc_twoCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_twoCombineLabel.insets = new Insets(0, 0, 5, 5);
		gbc_twoCombineLabel.gridx = 1;
		gbc_twoCombineLabel.gridy = 8;
		add(twoCombineLabel, gbc_twoCombineLabel);
		
		twoCombineTextField = new MyTextField();
		GridBagConstraints gbc_twoCombineTextField = new GridBagConstraints();
		gbc_twoCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_twoCombineTextField.insets = new Insets(0, 0, 5, 0);
		gbc_twoCombineTextField.gridx = 2;
		gbc_twoCombineTextField.gridy = 8;
		add(twoCombineTextField, gbc_twoCombineTextField);
		twoCombineTextField.setColumns(10);
		twoCombineTextField.setMaximumSize(twoCombineTextField.getPreferredSize());
		
		JLabel geneCombineLabel = new MyPrompt("\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_geneCombineLabel = new GridBagConstraints();
		gbc_geneCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_geneCombineLabel.insets = new Insets(0, 0, 0, 5);
		gbc_geneCombineLabel.gridx = 1;
		gbc_geneCombineLabel.gridy = 9;
		add(geneCombineLabel, gbc_geneCombineLabel);
		
		geneCombineTextField = new MyTextField();
		GridBagConstraints gbc_geneCombineTextField = new GridBagConstraints();
		gbc_geneCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_geneCombineTextField.gridx = 2;
		gbc_geneCombineTextField.gridy = 9;
		add(geneCombineTextField, gbc_geneCombineTextField);
		geneCombineTextField.setColumns(10);
		geneCombineTextField.setMaximumSize(geneCombineTextField.getPreferredSize());
		
	}

}
