package ui.conf;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

public class InputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8014381780398643384L;
	private JTextField nameTextField;
	private JTextField inputTextField;

	/**
	 * Create the panel.
	 */
	public InputPanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component glue_9 = Box.createGlue();
		add(glue_9);
		
		Box titleHorizontalBox = Box.createHorizontalBox();
		add(titleHorizontalBox);
		
		JLabel titleLabel = new JLabel("\u8BBE\u7F6E\u8F93\u5165\u6570\u636E");
		titleHorizontalBox.add(titleLabel);
		
		Component glue_1 = Box.createGlue();
		add(glue_1);
		
		Box inputPathHorizontalBox = Box.createHorizontalBox();
		add(inputPathHorizontalBox);
		
		Component glue_6 = Box.createGlue();
		inputPathHorizontalBox.add(glue_6);
		
		Component glue_2 = Box.createGlue();
		inputPathHorizontalBox.add(glue_2);
						
		JLabel setPathLabel = new JLabel("\u8F93\u5165\u6570\u636E\u8DEF\u5F84:");
		inputPathHorizontalBox.add(setPathLabel);
		
		Component glue_3 = Box.createGlue();
		inputPathHorizontalBox.add(glue_3);
		
		inputTextField = new JTextField();
		inputPathHorizontalBox.add(inputTextField);
		inputTextField.setColumns(10);
		inputTextField.setMaximumSize(new Dimension(126, 21));
		
		Component glue_4 = Box.createGlue();
		inputPathHorizontalBox.add(glue_4);
		
		Component glue_7 = Box.createGlue();
		inputPathHorizontalBox.add(glue_7);
		
		Component glue = Box.createGlue();
		add(glue);
		
		Box nameHorizontalBox = Box.createHorizontalBox();
		add(nameHorizontalBox);
		
		Component glue_11 = Box.createGlue();
		nameHorizontalBox.add(glue_11);
		
		Component glue_15 = Box.createGlue();
		nameHorizontalBox.add(glue_15);
		
		JLabel nameLabel = new JLabel("\u8BF7\u4E3A\u914D\u7F6E\u53C2\u6570\u547D\u540D");
		nameHorizontalBox.add(nameLabel);
		
		Component glue_12 = Box.createGlue();
		nameHorizontalBox.add(glue_12);
		
		nameTextField = new JTextField();
		nameHorizontalBox.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setMaximumSize(new Dimension(126, 21));
		
		Component glue_13 = Box.createGlue();
		nameHorizontalBox.add(glue_13);
		
		Component glue_14 = Box.createGlue();
		nameHorizontalBox.add(glue_14);
		
		Component glue_10 = Box.createGlue();
		add(glue_10);
		
		Box admitHorizontalBox = Box.createHorizontalBox();
		add(admitHorizontalBox);
		
		JButton admitButton = new JButton("\u786E\u5B9A");
		admitHorizontalBox.add(admitButton);
		
		Component glue_8 = Box.createGlue();
		add(glue_8);
		
		Component glue_5 = Box.createGlue();
		add(glue_5);
	}

}
