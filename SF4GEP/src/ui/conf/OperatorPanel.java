package ui.conf;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;

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
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		Component glue_11 = Box.createGlue();
		add(glue_11);
		
		Box titleHorizontalBox = Box.createHorizontalBox();
		add(titleHorizontalBox);
		
		JLabel titleLabel = new JLabel("\u8F93\u5165\u53D8\u5F02\u53C2\u6570");
		titleHorizontalBox.add(titleLabel);
		
		Component glue = Box.createGlue();
		add(glue);
		
		Box mainOrizontalBox = Box.createHorizontalBox();
		add(mainOrizontalBox);
		
		Component glue_14 = Box.createGlue();
		mainOrizontalBox.add(glue_14);
		
		Box leftVerticalBox = Box.createVerticalBox();
		mainOrizontalBox.add(leftVerticalBox);
		
		Box mutateHorizontalBox = Box.createHorizontalBox();
		leftVerticalBox.add(mutateHorizontalBox);
		
		JLabel mutateLabel = new JLabel("\u53D8\u5F02\u6982\u7387   ");
		mutateHorizontalBox.add(mutateLabel);
		
		mutateTextField = new JTextField();
		mutateHorizontalBox.add(mutateTextField);
		mutateTextField.setColumns(10);
		mutateTextField.setMaximumSize(mutateTextField.getPreferredSize());
		
		Component glue_1 = Box.createGlue();
		leftVerticalBox.add(glue_1);
		
		Box ISHorizontalBox = Box.createHorizontalBox();
		leftVerticalBox.add(ISHorizontalBox);
		
		JLabel ISLabel = new JLabel("IS\u8F6C\u5EA7\u6982\u7387 ");
		ISHorizontalBox.add(ISLabel);
		
		ISTextField = new JTextField();
		ISHorizontalBox.add(ISTextField);
		ISTextField.setColumns(10);
		ISTextField.setMaximumSize(ISTextField.getPreferredSize());
		
		Component glue_2 = Box.createGlue();
		leftVerticalBox.add(glue_2);
		
		Box ISLengthHorizontalBox = Box.createHorizontalBox();
		leftVerticalBox.add(ISLengthHorizontalBox);
		
		JLabel ISLengthLabel = new JLabel("IS\u8F6C\u5EA7\u957F\u5EA6 ");
		ISLengthHorizontalBox.add(ISLengthLabel);
		
		ISLengthTextField = new JTextField();
		ISLengthHorizontalBox.add(ISLengthTextField);
		ISLengthTextField.setColumns(10);
		ISLengthTextField.setMaximumSize(ISLengthTextField.getPreferredSize());
		
		Component glue_4 = Box.createGlue();
		leftVerticalBox.add(glue_4);
		
		Box RISHorizontalBox = Box.createHorizontalBox();
		leftVerticalBox.add(RISHorizontalBox);
		
		JLabel RISLabel = new JLabel("RIS\u8F6C\u5EA7\u6982\u7387");
		RISHorizontalBox.add(RISLabel);
		
		RISTextField = new JTextField();
		RISHorizontalBox.add(RISTextField);
		RISTextField.setColumns(10);
		RISTextField.setMaximumSize(RISTextField.getPreferredSize());
		
		Component glue_3 = Box.createGlue();
		leftVerticalBox.add(glue_3);
		
		Box RISLengthHorizontalBox = Box.createHorizontalBox();
		leftVerticalBox.add(RISLengthHorizontalBox);
		
		JLabel RISLengthLabel = new JLabel("RIS\u8F6C\u5EA7\u957F\u5EA6");
		RISLengthHorizontalBox.add(RISLengthLabel);
		
		RISLengthTextField = new JTextField();
		RISLengthHorizontalBox.add(RISLengthTextField);
		RISLengthTextField.setColumns(10);
		RISLengthTextField.setMaximumSize(RISLengthTextField.getPreferredSize());
		
		Component glue_5 = Box.createGlue();
		leftVerticalBox.add(glue_5);
		
		Box rightVerticalBox = Box.createVerticalBox();
		mainOrizontalBox.add(rightVerticalBox);
		
		Box geneHorizontalBox = Box.createHorizontalBox();
		rightVerticalBox.add(geneHorizontalBox);
		
		JLabel geneLabel = new JLabel("\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
		geneHorizontalBox.add(geneLabel);
		
		geneTextField = new JTextField();
		geneHorizontalBox.add(geneTextField);
		geneTextField.setColumns(10);
		geneTextField.setMaximumSize(geneTextField.getPreferredSize());
		
		Component glue_6 = Box.createGlue();
		rightVerticalBox.add(glue_6);
		
		Box oneCombineHorizontalBox = Box.createHorizontalBox();
		rightVerticalBox.add(oneCombineHorizontalBox);
		
		JLabel oneCombineLabel = new JLabel("\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
		oneCombineHorizontalBox.add(oneCombineLabel);
		
		oneCombineTextField = new JTextField();
		oneCombineHorizontalBox.add(oneCombineTextField);
		oneCombineTextField.setColumns(10);
		oneCombineTextField.setMaximumSize(oneCombineTextField.getPreferredSize());
		
		Component glue_7 = Box.createGlue();
		rightVerticalBox.add(glue_7);
		
		Box twoCombineHorizontalBox = Box.createHorizontalBox();
		rightVerticalBox.add(twoCombineHorizontalBox);
		
		JLabel twoCombineLabel = new JLabel("\u4E24\u70B9\u91CD\u7EC4\u6982\u7387");
		twoCombineHorizontalBox.add(twoCombineLabel);
		
		twoCombineTextField = new JTextField();
		twoCombineHorizontalBox.add(twoCombineTextField);
		twoCombineTextField.setColumns(10);
		twoCombineTextField.setMaximumSize(twoCombineTextField.getPreferredSize());
		
		Component glue_8 = Box.createGlue();
		rightVerticalBox.add(glue_8);
		
		Box geneCombineHorizontalBox = Box.createHorizontalBox();
		rightVerticalBox.add(geneCombineHorizontalBox);
		
		JLabel geneCombineLabel = new JLabel("\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
		geneCombineHorizontalBox.add(geneCombineLabel);
		
		geneCombineTextField = new JTextField();
		geneCombineHorizontalBox.add(geneCombineTextField);
		geneCombineTextField.setColumns(10);
		geneCombineTextField.setMaximumSize(geneCombineTextField.getPreferredSize());
		
		Component glue_9 = Box.createGlue();
		rightVerticalBox.add(glue_9);
		
		Box placeHolderHorizontalBox = Box.createHorizontalBox();
		rightVerticalBox.add(placeHolderHorizontalBox);
		
		JLabel placeHolderLabel = new JLabel(" ");
		placeHolderHorizontalBox.add(placeHolderLabel);
		
		Component glue_10 = Box.createGlue();
		rightVerticalBox.add(glue_10);
		
		Component glue_13 = Box.createGlue();
		mainOrizontalBox.add(glue_13);
		
		Component glue_12 = Box.createGlue();
		add(glue_12);
		
	}

}
