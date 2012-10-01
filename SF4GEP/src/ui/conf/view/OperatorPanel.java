package ui.conf.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.conf.model.AccuracyModel;
import ui.conf.model.Model;
import ui.conf.model.MyPrompt;
import ui.conf.model.MyTextField;
import ui.conf.model.MyTitle;
import ui.conf.model.OperatorModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OperatorPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2305610876923730049L;
	public JTextField mutateTextField;
	public JTextField ISTextField;
	public JTextField ISLengthTextField;
	public JTextField RISTextField;
	public JTextField RISLengthTextField;
	public JTextField oneCombineTextField;
	public JTextField twoCombineTextField;
	public JTextField geneCombineTextField;
	public JTextField geneTextField;

	OperatorModel operatorModel;
	
	
	/**
	 * Create the panel.
	 */
	public OperatorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 168, 94, 0};
		gridBagLayout.rowHeights = new int[]{62, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new MyTitle("\u8F93\u5165\u53D8\u5F02\u53C2\u6570");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
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
		mutateTextField.setText("0");
		mutateTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				mutateTextField.selectAll();
			}
		});
		GridBagConstraints gbc_mutateTextField = new GridBagConstraints();
		gbc_mutateTextField.anchor = GridBagConstraints.WEST;
		gbc_mutateTextField.insets = new Insets(0, 0, 5, 0);
		gbc_mutateTextField.gridx = 2;
		gbc_mutateTextField.gridy = 1;
		add(mutateTextField, gbc_mutateTextField);
		
		JLabel ISLabel = new MyPrompt("IS\u8F6C\u5EA7\u6982\u7387 ");
		GridBagConstraints gbc_ISLabel = new GridBagConstraints();
		gbc_ISLabel.anchor = GridBagConstraints.WEST;
		gbc_ISLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ISLabel.gridx = 1;
		gbc_ISLabel.gridy = 2;
		add(ISLabel, gbc_ISLabel);
		
		ISTextField = new MyTextField();
		ISTextField.setText("0");
		ISTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ISTextField.selectAll();
			}
		});
		GridBagConstraints gbc_ISTextField = new GridBagConstraints();
		gbc_ISTextField.anchor = GridBagConstraints.WEST;
		gbc_ISTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ISTextField.gridx = 2;
		gbc_ISTextField.gridy = 2;
		add(ISTextField, gbc_ISTextField);
		
		JLabel ISLengthLabel = new MyPrompt("IS\u8F6C\u5EA7\u957F\u5EA6 ");
		GridBagConstraints gbc_ISLengthLabel = new GridBagConstraints();
		gbc_ISLengthLabel.anchor = GridBagConstraints.WEST;
		gbc_ISLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ISLengthLabel.gridx = 1;
		gbc_ISLengthLabel.gridy = 3;
		add(ISLengthLabel, gbc_ISLengthLabel);
		
		ISLengthTextField = new MyTextField();
		ISLengthTextField.setText("0");
		ISLengthTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ISLengthTextField.selectAll();
			}
		});
		GridBagConstraints gbc_ISLengthTextField = new GridBagConstraints();
		gbc_ISLengthTextField.anchor = GridBagConstraints.WEST;
		gbc_ISLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ISLengthTextField.gridx = 2;
		gbc_ISLengthTextField.gridy = 3;
		add(ISLengthTextField, gbc_ISLengthTextField);
		
		JLabel RISLabel = new MyPrompt("RIS\u8F6C\u5EA7\u6982\u7387");
		GridBagConstraints gbc_RISLabel = new GridBagConstraints();
		gbc_RISLabel.anchor = GridBagConstraints.WEST;
		gbc_RISLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RISLabel.gridx = 1;
		gbc_RISLabel.gridy = 4;
		add(RISLabel, gbc_RISLabel);
		
		RISTextField = new MyTextField();
		RISTextField.setText("0");
		RISTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				RISTextField.selectAll();
			}
		});
		GridBagConstraints gbc_RISTextField = new GridBagConstraints();
		gbc_RISTextField.anchor = GridBagConstraints.WEST;
		gbc_RISTextField.insets = new Insets(0, 0, 5, 0);
		gbc_RISTextField.gridx = 2;
		gbc_RISTextField.gridy = 4;
		add(RISTextField, gbc_RISTextField);
		
		JLabel RISLengthLabel = new MyPrompt("RIS\u8F6C\u5EA7\u957F\u5EA6");
		GridBagConstraints gbc_RISLengthLabel = new GridBagConstraints();
		gbc_RISLengthLabel.anchor = GridBagConstraints.WEST;
		gbc_RISLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RISLengthLabel.gridx = 1;
		gbc_RISLengthLabel.gridy = 5;
		add(RISLengthLabel, gbc_RISLengthLabel);
		
		RISLengthTextField = new MyTextField();
		RISLengthTextField.setText("0");
		RISLengthTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				RISLengthTextField.selectAll();
			}
		});
		GridBagConstraints gbc_RISLengthTextField = new GridBagConstraints();
		gbc_RISLengthTextField.anchor = GridBagConstraints.WEST;
		gbc_RISLengthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_RISLengthTextField.gridx = 2;
		gbc_RISLengthTextField.gridy = 5;
		add(RISLengthTextField, gbc_RISLengthTextField);
		
		JLabel geneLabel = new MyPrompt("\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
		GridBagConstraints gbc_geneLabel = new GridBagConstraints();
		gbc_geneLabel.anchor = GridBagConstraints.WEST;
		gbc_geneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_geneLabel.gridx = 1;
		gbc_geneLabel.gridy = 6;
		add(geneLabel, gbc_geneLabel);
		
		geneTextField = new MyTextField();
		geneTextField.setText("0");
		geneTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				geneTextField.selectAll();
			}
		});
		GridBagConstraints gbc_geneTextField = new GridBagConstraints();
		gbc_geneTextField.anchor = GridBagConstraints.WEST;
		gbc_geneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_geneTextField.gridx = 2;
		gbc_geneTextField.gridy = 6;
		add(geneTextField, gbc_geneTextField);
		
		JLabel oneCombineLabel = new MyPrompt("\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_oneCombineLabel = new GridBagConstraints();
		gbc_oneCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_oneCombineLabel.insets = new Insets(0, 0, 5, 5);
		gbc_oneCombineLabel.gridx = 1;
		gbc_oneCombineLabel.gridy = 7;
		add(oneCombineLabel, gbc_oneCombineLabel);
		
		oneCombineTextField = new MyTextField();
		oneCombineTextField.setText("0");
		oneCombineTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				oneCombineTextField.selectAll();
			}
		});
		GridBagConstraints gbc_oneCombineTextField = new GridBagConstraints();
		gbc_oneCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_oneCombineTextField.insets = new Insets(0, 0, 5, 0);
		gbc_oneCombineTextField.gridx = 2;
		gbc_oneCombineTextField.gridy = 7;
		add(oneCombineTextField, gbc_oneCombineTextField);
		
		JLabel twoCombineLabel = new MyPrompt("\u4E24\u70B9\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_twoCombineLabel = new GridBagConstraints();
		gbc_twoCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_twoCombineLabel.insets = new Insets(0, 0, 5, 5);
		gbc_twoCombineLabel.gridx = 1;
		gbc_twoCombineLabel.gridy = 8;
		add(twoCombineLabel, gbc_twoCombineLabel);
		
		twoCombineTextField = new MyTextField();
		twoCombineTextField.setText("0");
		twoCombineTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				twoCombineTextField.selectAll();
			}
		});
		GridBagConstraints gbc_twoCombineTextField = new GridBagConstraints();
		gbc_twoCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_twoCombineTextField.insets = new Insets(0, 0, 5, 0);
		gbc_twoCombineTextField.gridx = 2;
		gbc_twoCombineTextField.gridy = 8;
		add(twoCombineTextField, gbc_twoCombineTextField);
		
		JLabel geneCombineLabel = new MyPrompt("\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
		GridBagConstraints gbc_geneCombineLabel = new GridBagConstraints();
		gbc_geneCombineLabel.anchor = GridBagConstraints.WEST;
		gbc_geneCombineLabel.insets = new Insets(0, 0, 0, 5);
		gbc_geneCombineLabel.gridx = 1;
		gbc_geneCombineLabel.gridy = 9;
		add(geneCombineLabel, gbc_geneCombineLabel);
		
		geneCombineTextField = new MyTextField();
		geneCombineTextField.setText("0");
		geneCombineTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				geneCombineTextField.selectAll();
			}
		});
		GridBagConstraints gbc_geneCombineTextField = new GridBagConstraints();
		gbc_geneCombineTextField.anchor = GridBagConstraints.WEST;
		gbc_geneCombineTextField.gridx = 2;
		gbc_geneCombineTextField.gridy = 9;
		add(geneCombineTextField, gbc_geneCombineTextField);
		
	}

	@Override
	public void dataUpdate(Model model) {
		this.operatorModel = (OperatorModel)model;
		mutateTextField.setText(operatorModel.getMutateRate().toString());
		ISTextField.setText(operatorModel.getIsTransportRate().toString());
		
		
		
		Integer[] elements = operatorModel.getIsElement();
		StringBuffer sb = new StringBuffer();
		int len = elements.length;
		for(int i = 0; i < len; i++){
		 sb. append(elements[i] + ",");
		}
		sb.replace(len-2, len, "");
		ISLengthTextField.setText(sb.toString());
		
		
		
		RISTextField.setText(operatorModel.getRisTransportRate().toString());
		
		elements = operatorModel.getRisElement();
		sb = new StringBuffer();
		len = elements.length;
		for(int i = 0; i < len; i++){
		 sb. append(elements[i] + ",");
		}
		sb.replace(len-2, len, "");
		RISLengthTextField.setText(sb.toString());
		geneTextField.setText(operatorModel.getGeneTransportRate().toString());
		
		oneCombineTextField.setText(operatorModel.getOnePointRecombineRate().toString());
		twoCombineTextField.setText(operatorModel.getTwoPointRecombineRate().toString());
		geneCombineTextField.setText(operatorModel.getGeneRecombineRate().toString());
		
		
		
		
	}
	
	
	
	public void fillModel(OperatorModel model){
		
		model.setMutateRate(Float.parseFloat(mutateTextField.getText().toString()));
		model.setIsTransportRate(Float.parseFloat(ISTextField.getText().toString()));
		
		
		String str = ISLengthTextField.getText().toString();
		String IsElements[] = str.split(",");
		Integer ISIntegerElements[] = new Integer[IsElements.length];
		for (int i = 0; i < ISIntegerElements.length; i++) {
			ISIntegerElements[i] = Integer.parseInt(IsElements[i]);
		}
		model.setIsElement(ISIntegerElements);
		
		
		model.setRisTransportRate(Float.parseFloat(RISTextField.getText().toString()));
		
		
		str = RISLengthTextField.getText().toString();
		String RISElements[] = str.split(",");
		Integer RISIntegerElements[] = new Integer[RISElements.length];
		for (int i = 0; i < RISIntegerElements.length; i++) {
			RISIntegerElements[i] = Integer.parseInt(RISElements[i]);
		}
		model.setRisElement(RISIntegerElements);
		
		
		model.setGeneTransportRate(Float.parseFloat(geneTextField.getText().toString()));
		model.setOnePointRecombineRate(Float.parseFloat(oneCombineTextField.getText().toString()));
		model.setTwoPointRecombineRate(Float.parseFloat(twoCombineTextField.getText().toString()));
		model.setGeneRecombineRate(Float.parseFloat(geneCombineTextField.getText().toString()));
		
		
	}

}
