package ui.conf.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JScrollPane;

import domain.core.algconfiguration.Function;

import ui.app.MainWnd;
import ui.conf.model.GeneModel;
import ui.conf.model.Model;
import ui.conf.model.MyPrompt;
import ui.conf.model.MyTextField;
import ui.conf.model.MyTitle;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GenePanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180414798333601078L;
	
	
	public JTextField individualNumtextField;
	public JTextField normalGeneNumTextField;
	public JTextField homeoticGeneTextField;
	public JTextField homeoticGeneHeaderTextField;
	public JTextField normalGeneHeaderTextField;
	
	public JRadioButton homeoticGeneRadioButton;
	public JRadioButton connectionFuncRadioButton;
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	public  CardLayout cardLayout=new CardLayout(0, 0);
	public final JPanel connectionPanel;
	private GeneModel geneModel;
	public JComboBox<Function> connectionComboBox;
	private MainWnd mainWnd;
	public JList<Function> functionList;
	
	//public boolean useHomeoticGene = true;


	/**
	 * Create the panel.
	 */
	public GenePanel(MainWnd mainWnd) {
		this.mainWnd=mainWnd;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 164, 126, 0};
		gridBagLayout.rowHeights = new int[]{62, 40, 40, 40, 40, 56, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel titleLabel = new MyTitle("\u8BBE\u7F6E\u4E2A\u4F53\u548C\u57FA\u56E0\u53C2\u6570");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);

		JLabel individualNumLabel = new MyPrompt("\u79CD\u7FA4\u5927\u5C0F");
		GridBagConstraints gbc_individualNumLabel = new GridBagConstraints();
		gbc_individualNumLabel.anchor = GridBagConstraints.WEST;
		gbc_individualNumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_individualNumLabel.gridx = 1;
		gbc_individualNumLabel.gridy = 1;
		add(individualNumLabel, gbc_individualNumLabel);

		individualNumtextField = new MyTextField();
		individualNumtextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				individualNumtextField.selectAll();
			}
		});
		individualNumtextField.setText("0");
		GridBagConstraints gbc_individualNumtextField = new GridBagConstraints();
		gbc_individualNumtextField.anchor = GridBagConstraints.WEST;
		gbc_individualNumtextField.insets = new Insets(0, 0, 5, 0);
		gbc_individualNumtextField.gridx = 2;
		gbc_individualNumtextField.gridy = 1;
		add(individualNumtextField, gbc_individualNumtextField);

		JLabel normalGeneNumLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u6570\u91CF");
		GridBagConstraints gbc_normalGeneNumLabel = new GridBagConstraints();
		gbc_normalGeneNumLabel.anchor = GridBagConstraints.WEST;
		gbc_normalGeneNumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_normalGeneNumLabel.gridx = 1;
		gbc_normalGeneNumLabel.gridy = 2;
		add(normalGeneNumLabel, gbc_normalGeneNumLabel);

		normalGeneNumTextField = new MyTextField();
		normalGeneNumTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				normalGeneNumTextField.selectAll();
			}
		});
		normalGeneNumTextField.setText("0");
		GridBagConstraints gbc_normalGeneNumTextField = new GridBagConstraints();
		gbc_normalGeneNumTextField.anchor = GridBagConstraints.WEST;
		gbc_normalGeneNumTextField.insets = new Insets(0, 0, 5, 0);
		gbc_normalGeneNumTextField.gridx = 2;
		gbc_normalGeneNumTextField.gridy = 2;
		add(normalGeneNumTextField, gbc_normalGeneNumTextField);

		JLabel normalGeneHeaderLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u5934\u957F");
		GridBagConstraints gbc_normalGeneHeaderLabel = new GridBagConstraints();
		gbc_normalGeneHeaderLabel.anchor = GridBagConstraints.WEST;
		gbc_normalGeneHeaderLabel.insets = new Insets(0, 0, 5, 5);
		gbc_normalGeneHeaderLabel.gridx = 1;
		gbc_normalGeneHeaderLabel.gridy = 3;
		add(normalGeneHeaderLabel, gbc_normalGeneHeaderLabel);

		normalGeneHeaderTextField = new MyTextField();
		normalGeneHeaderTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				normalGeneHeaderTextField.selectAll();
			}
		});
		normalGeneHeaderTextField.setText("0");
		GridBagConstraints gbc_normalGeneHeaderTextField = new GridBagConstraints();
		gbc_normalGeneHeaderTextField.anchor = GridBagConstraints.WEST;
		gbc_normalGeneHeaderTextField.insets = new Insets(0, 0, 5, 0);
		gbc_normalGeneHeaderTextField.gridx = 2;
		gbc_normalGeneHeaderTextField.gridy = 3;
		add(normalGeneHeaderTextField, gbc_normalGeneHeaderTextField);

		JLabel connectionTypeLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u8FDE\u63A5\u65B9\u5F0F");
		GridBagConstraints gbc_connectionTypeLabel = new GridBagConstraints();
		gbc_connectionTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_connectionTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_connectionTypeLabel.gridx = 1;
		gbc_connectionTypeLabel.gridy = 4;
		add(connectionTypeLabel, gbc_connectionTypeLabel);

		JPanel connectionTypePanel = new JPanel();
		GridBagConstraints gbc_connectionTypePanel = new GridBagConstraints();
		gbc_connectionTypePanel.anchor = GridBagConstraints.WEST;
		gbc_connectionTypePanel.insets = new Insets(0, 0, 5, 0);
		gbc_connectionTypePanel.gridx = 2;
		gbc_connectionTypePanel.gridy = 4;
		add(connectionTypePanel, gbc_connectionTypePanel);

		
		homeoticGeneRadioButton = new JRadioButton("\u4F7F\u7528\u540C\u6E90\u57FA\u56E0");
		connectionTypePanel.add(homeoticGeneRadioButton);
		buttonGroup.add(homeoticGeneRadioButton);

		connectionFuncRadioButton = new JRadioButton(
				"\u4F7F\u7528\u8FDE\u63A5\u51FD\u6570");
		connectionTypePanel.add(connectionFuncRadioButton);
		buttonGroup.add(connectionFuncRadioButton);
		
		ButtonGroup group = new ButtonGroup ();
		group.add(homeoticGeneRadioButton);
		group.add(connectionFuncRadioButton);
//--		group.setSelected((ButtonModel) homeoticGeneRadioButton, true);
		connectionFuncRadioButton.setSelected(true);
		

		connectionPanel = new JPanel();
		GridBagConstraints gbc_connectionPanel = new GridBagConstraints();
		gbc_connectionPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_connectionPanel.gridwidth = 2;
		gbc_connectionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_connectionPanel.gridx = 1;
		gbc_connectionPanel.gridy = 5;
		add(connectionPanel, gbc_connectionPanel);
		connectionPanel.setLayout(cardLayout);

		JPanel connectionFuncPanel = new JPanel();
		connectionPanel.add(connectionFuncPanel, "connectionPannel");
		GridBagLayout gbl_connectionFuncPanel = new GridBagLayout();
		gbl_connectionFuncPanel.columnWidths = new int[]{164, 236, 0};
		gbl_connectionFuncPanel.rowHeights = new int[]{40, 0};
		gbl_connectionFuncPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_connectionFuncPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		connectionFuncPanel.setLayout(gbl_connectionFuncPanel);

		JLabel connectionFuncLabel = new MyPrompt("\u9009\u62E9\u8FDE\u63A5\u51FD\u6570");
		GridBagConstraints gbc_connectionFuncLabel = new GridBagConstraints();
		gbc_connectionFuncLabel.anchor = GridBagConstraints.WEST;
		gbc_connectionFuncLabel.insets = new Insets(0, 0, 0, 5);
		gbc_connectionFuncLabel.gridx = 0;
		gbc_connectionFuncLabel.gridy = 0;
		connectionFuncPanel.add(connectionFuncLabel, gbc_connectionFuncLabel);

		connectionComboBox = new JComboBox<Function>();
		GridBagConstraints gbc_connectionComboBox = new GridBagConstraints();
		gbc_connectionComboBox.anchor = GridBagConstraints.WEST;
		gbc_connectionComboBox.gridx = 1;
		gbc_connectionComboBox.gridy = 0;
		connectionFuncPanel.add(connectionComboBox, gbc_connectionComboBox);
		connectionComboBox.setMaximumSize(new Dimension(110, 21));

		JPanel homecticPanel = new JPanel();
		connectionPanel.add(homecticPanel, "homeoticPannel");
		GridBagLayout gbl_homecticPanel = new GridBagLayout();
		gbl_homecticPanel.columnWidths = new int[]{164, 78, 0};
		gbl_homecticPanel.rowHeights = new int[]{40, 40, 0};
		gbl_homecticPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_homecticPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		homecticPanel.setLayout(gbl_homecticPanel);

		JLabel homeoticGeneNumLabel = new MyPrompt("\u540C\u6E90\u57FA\u56E0\u6570\u91CF");
		GridBagConstraints gbc_homeoticGeneNumLabel = new GridBagConstraints();
		gbc_homeoticGeneNumLabel.anchor = GridBagConstraints.WEST;
		gbc_homeoticGeneNumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_homeoticGeneNumLabel.gridx = 0;
		gbc_homeoticGeneNumLabel.gridy = 0;
		homecticPanel.add(homeoticGeneNumLabel, gbc_homeoticGeneNumLabel);

		homeoticGeneTextField = new MyTextField();
		homeoticGeneTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				homeoticGeneTextField.selectAll();
			}
		});
		homeoticGeneTextField.setText("0");
		GridBagConstraints gbc_homeoticGeneTextField = new GridBagConstraints();
		gbc_homeoticGeneTextField.anchor = GridBagConstraints.WEST;
		gbc_homeoticGeneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_homeoticGeneTextField.gridx = 1;
		gbc_homeoticGeneTextField.gridy = 0;
		homecticPanel.add(homeoticGeneTextField, gbc_homeoticGeneTextField);

		JLabel homeoticGeneHeaderLabel = new MyPrompt("\u540C\u6E90\u57FA\u56E0\u5934\u957F");
		GridBagConstraints gbc_homeoticGeneHeaderLabel = new GridBagConstraints();
		gbc_homeoticGeneHeaderLabel.anchor = GridBagConstraints.WEST;
		gbc_homeoticGeneHeaderLabel.insets = new Insets(0, 0, 0, 5);
		gbc_homeoticGeneHeaderLabel.gridx = 0;
		gbc_homeoticGeneHeaderLabel.gridy = 1;
		homecticPanel.add(homeoticGeneHeaderLabel, gbc_homeoticGeneHeaderLabel);

		homeoticGeneHeaderTextField = new MyTextField();
		homeoticGeneHeaderTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				homeoticGeneHeaderTextField.selectAll();
			}
		});
		homeoticGeneHeaderTextField.setText("0");
		GridBagConstraints gbc_homeoticGeneHeaderTextField = new GridBagConstraints();
		gbc_homeoticGeneHeaderTextField.anchor = GridBagConstraints.WEST;
		gbc_homeoticGeneHeaderTextField.gridx = 1;
		gbc_homeoticGeneHeaderTextField.gridy = 1;
		homecticPanel.add(homeoticGeneHeaderTextField, gbc_homeoticGeneHeaderTextField);

		JLabel usedFuncLabel = new MyPrompt("\u6240\u7528\u7684\u51FD\u6570");
		usedFuncLabel.setText("\u6240\u7528\u51FD\u6570");
		GridBagConstraints gbc_usedFuncLabel = new GridBagConstraints();
		gbc_usedFuncLabel.anchor = GridBagConstraints.WEST;
		gbc_usedFuncLabel.insets = new Insets(0, 0, 0, 5);
		gbc_usedFuncLabel.gridx = 1;
		gbc_usedFuncLabel.gridy = 6;
		add(usedFuncLabel, gbc_usedFuncLabel);

		JScrollPane functionScrollPane = new JScrollPane();
		GridBagConstraints gbc_functionScrollPane = new GridBagConstraints();
		gbc_functionScrollPane.anchor = GridBagConstraints.WEST;
		gbc_functionScrollPane.gridx = 2;
		gbc_functionScrollPane.gridy = 6;
		add(functionScrollPane, gbc_functionScrollPane);

		functionList = new JList<Function>();
		functionList.setVisibleRowCount(4);
		functionScrollPane.setViewportView(functionList);
		functionScrollPane.setMaximumSize(new Dimension(126, 60));

		
	}
	

	@Override
	public void dataUpdate(Model model) {
		this.geneModel = (GeneModel)model;
		individualNumtextField.setText(geneModel.getIndividualNumber().toString());
		normalGeneNumTextField.setText(geneModel.getNormalGeneNumber().toString());
		normalGeneHeaderTextField.setText(geneModel.getNormalGeneHeaderLength().toString());
		if (geneModel.isUseHomeoticGene()) {
			
			
			
			homeoticGeneRadioButton.setSelected(true);
			//connectionFuncRadioButton.setSelected(false);
			homeoticGeneHeaderTextField.setText(geneModel.getHomeoticGeneHeaderLength().toString());
			homeoticGeneTextField.setText(geneModel.getHomeoticGeneNumber().toString());
			
		}
		else {
			//homeoticGeneRadioButton.setSelected(false);
			connectionFuncRadioButton.setSelected(true);
			/**
			 * ´ý²¹³ä
			 */
			//geneModel.setConnectionFunction(connectionComboBox.getItemAt(connectionComboBox.getSelectedIndex()));
		}
		//homeoticGeneTextField.setText(geneModel.getHomeoticGeneNumber().toString());
		//homeoticGeneHeaderTextField.setText(geneModel.getHomeoticGeneHeaderLength().toString());
		
	}
	
	public void fillModel(GeneModel model){
//model.setUseHomeoticGene(useHomeoticGene);
		model.setIndividualNumber(Integer.parseInt(individualNumtextField.getText().toString()));
		model.setNormalGeneNumber(Integer.parseInt(normalGeneNumTextField.getText().toString()));
		model.setNormalGeneHeaderLength(Integer.parseInt(normalGeneHeaderTextField.getText().toString()));
		model.setFunctionList(functionList.getSelectedValuesList());
		if (model.isUseHomeoticGene()) {
			model.setHomeoticGeneNumber(Integer.parseInt(homeoticGeneTextField.getText().toString()));
			model.setHomeoticGeneHeaderLength(Integer.parseInt(homeoticGeneHeaderTextField.getText().toString()));
		}
		else {
			model.setConnectionFunction(connectionComboBox.getItemAt(connectionComboBox.getSelectedIndex()));
		}

	}

	public MainWnd getMainWnd() {
		return mainWnd;
	}

}
