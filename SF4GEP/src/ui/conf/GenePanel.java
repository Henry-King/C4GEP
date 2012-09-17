package ui.conf;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180414798333601078L;
	private JTextField individualNumtextField;
	private JTextField normalGeneNumTextField;
	private JTextField homeoticGeneTextField;
	private JTextField homeoticGeneHeaderTextField;
	private JTextField normalGeneHeaderTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public GenePanel() {
		final CardLayout cardLayout=new CardLayout(0, 0);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component glue_8 = Box.createGlue();
		add(glue_8);
		
		Box titleHorizontalBox = Box.createHorizontalBox();
		add(titleHorizontalBox);

		JLabel titleLabel = new JLabel("\u8BBE\u7F6E\u4E2A\u4F53\u548C\u57FA\u56E0\u53C2\u6570");
		titleHorizontalBox.add(titleLabel);
		
		Component glue_6 = Box.createGlue();
		add(glue_6);

		Box individualNumHorizontalBox = Box.createHorizontalBox();
		add(individualNumHorizontalBox);

		JLabel individualNumLabel = new JLabel("\u79CD\u7FA4\u5927\u5C0F");
		individualNumHorizontalBox.add(individualNumLabel);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(115, 20));
		individualNumHorizontalBox.add(rigidArea_4);

		individualNumtextField = new JTextField();
		individualNumHorizontalBox.add(individualNumtextField);
		individualNumtextField.setColumns(10);
		individualNumtextField.setMaximumSize(new Dimension(126, 21));
		
		Component glue_5 = Box.createGlue();
		add(glue_5);

		Box normalGeneNumHorizontalBox = Box.createHorizontalBox();
		add(normalGeneNumHorizontalBox);

		JLabel normalGeneNumLabel = new JLabel("\u666E\u901A\u57FA\u56E0\u6570\u91CF");
		normalGeneNumHorizontalBox.add(normalGeneNumLabel);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(90, 20));
		normalGeneNumHorizontalBox.add(rigidArea_3);

		normalGeneNumTextField = new JTextField();
		normalGeneNumHorizontalBox.add(normalGeneNumTextField);
		normalGeneNumTextField.setColumns(10);
		normalGeneNumTextField.setMaximumSize(new Dimension(126, 21));
		
		Component glue_4 = Box.createGlue();
		add(glue_4);
		
		Box normalGeneHeaderHorizontalBox = Box.createHorizontalBox();
		add(normalGeneHeaderHorizontalBox);
		
		JLabel normalGeneHeaderLabel = new JLabel("\u666E\u901A\u57FA\u56E0\u5934\u957F");
		normalGeneHeaderHorizontalBox.add(normalGeneHeaderLabel);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(90, 20));
		normalGeneHeaderHorizontalBox.add(rigidArea_2);
		
		normalGeneHeaderTextField = new JTextField();
		normalGeneHeaderHorizontalBox.add(normalGeneHeaderTextField);
		normalGeneHeaderTextField.setColumns(10);
		normalGeneHeaderTextField.setMaximumSize(new Dimension(126, 21));
		
		Component glue_3 = Box.createGlue();
		add(glue_3);

		Box connectionVerticalBox = Box.createVerticalBox();
		add(connectionVerticalBox);

		Box connectionTypeHorizontalBox = Box.createHorizontalBox();
		connectionVerticalBox.add(connectionTypeHorizontalBox);

		JLabel connectionTypeLabel = new JLabel("\u666E\u901A\u57FA\u56E0\u8FDE\u63A5\u65B9\u5F0F");
		connectionTypeHorizontalBox.add(connectionTypeLabel);

		JRadioButton homeoticGeneRadioButton = new JRadioButton("\u4F7F\u7528\u540C\u6E90\u57FA\u56E0");
		buttonGroup.add(homeoticGeneRadioButton);
		connectionTypeHorizontalBox.add(homeoticGeneRadioButton);

		JRadioButton connectionFuncRadioButton = new JRadioButton(
				"\u4F7F\u7528\u8FDE\u63A5\u51FD\u6570");
		buttonGroup.add(connectionFuncRadioButton);
		connectionTypeHorizontalBox.add(connectionFuncRadioButton);
		
		Component glue_7 = Box.createGlue();
		connectionVerticalBox.add(glue_7);

		final JPanel connectionPanel = new JPanel();
		connectionVerticalBox.add(connectionPanel);
		connectionPanel.setLayout(cardLayout);
		connectionPanel.setMaximumSize(new Dimension(600, 70));

		Box homeoticGeneVerticalBox = Box.createVerticalBox();
		connectionPanel.add(homeoticGeneVerticalBox, "name_58232059207826");
		homeoticGeneRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(connectionPanel, "name_58232059207826");
			}
		});

		Box homeoticGeneNumHorizontalBox = Box.createHorizontalBox();
		homeoticGeneVerticalBox.add(homeoticGeneNumHorizontalBox);

		JLabel homeoticGeneNumLabel = new JLabel("\u540C\u6E90\u57FA\u56E0\u6570\u91CF");
		homeoticGeneNumHorizontalBox.add(homeoticGeneNumLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(90, 20));
		homeoticGeneNumHorizontalBox.add(rigidArea);

		homeoticGeneTextField = new JTextField();
		homeoticGeneNumHorizontalBox.add(homeoticGeneTextField);
		homeoticGeneTextField.setColumns(10);
		homeoticGeneTextField.setMaximumSize(new Dimension(126, 21));

		Box homeoticGeneHeaderhorizontalBox = Box.createHorizontalBox();
		homeoticGeneVerticalBox.add(homeoticGeneHeaderhorizontalBox);

		JLabel homeoticGeneHeaderLabel = new JLabel("\u540C\u6E90\u57FA\u56E0\u5934\u957F");
		homeoticGeneHeaderhorizontalBox.add(homeoticGeneHeaderLabel);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(90, 20));
		homeoticGeneHeaderhorizontalBox.add(rigidArea_1);

		homeoticGeneHeaderTextField = new JTextField();
		homeoticGeneHeaderhorizontalBox.add(homeoticGeneHeaderTextField);
		homeoticGeneHeaderTextField.setColumns(10);
		homeoticGeneHeaderTextField.setMaximumSize(new Dimension(126, 21));

		Box connectionFuncHorizontalBox = Box.createHorizontalBox();
		connectionPanel.add(connectionFuncHorizontalBox, "name_58336532487025");
		connectionFuncRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(connectionPanel, "name_58336532487025");
			}
		});
		
		Component glue_9 = Box.createGlue();
		connectionFuncHorizontalBox.add(glue_9);

		JLabel connectionFuncLabel = new JLabel("\u8FDE\u63A5\u51FD\u6570");
		connectionFuncHorizontalBox.add(connectionFuncLabel);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(115, 20));
		connectionFuncHorizontalBox.add(rigidArea_6);

		JComboBox connectionComboBox = new JComboBox();
		connectionComboBox.setMaximumSize(new Dimension(18726, 21));
		connectionFuncHorizontalBox.add(connectionComboBox);
		
		Component glue_10 = Box.createGlue();
		connectionFuncHorizontalBox.add(glue_10);
		
		Component glue_2 = Box.createGlue();
		add(glue_2);



		Box usedFuncHorizontalBox = Box.createHorizontalBox();
		add(usedFuncHorizontalBox);

		JLabel usedFuncLabel = new JLabel("\u6240\u7528\u7684\u51FD\u6570");
		usedFuncHorizontalBox.add(usedFuncLabel);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(100, 20));
		usedFuncHorizontalBox.add(rigidArea_5);
		
		JScrollPane functionScrollPane = new JScrollPane();
		usedFuncHorizontalBox.add(functionScrollPane);
		
		JList functionList = new JList();
		functionList.setVisibleRowCount(4);
		functionScrollPane.setViewportView(functionList);
		functionScrollPane.setMaximumSize(new Dimension(126, 60));
		
		Component glue_1 = Box.createGlue();
		add(glue_1);

		Box admitHorizontalBox = Box.createHorizontalBox();
		add(admitHorizontalBox);
		
		Component glue = Box.createGlue();
		add(glue);

	}

}
