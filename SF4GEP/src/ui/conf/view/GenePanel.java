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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private GeneModel geneModel;
	public JComboBox<Function> connectionComboBox;
	private MainWnd mainWnd;
	public JList<Function> functionList;
	private JPanel homecticPanel;
	private JPanel connectionFuncPanel;
	
	//public boolean useHomeoticGene = true;


	/**
	 * Create the panel.
	 */
	public GenePanel(MainWnd mainWnd) {
		this.mainWnd=mainWnd;

		JLabel individualNumLabel = new MyPrompt("\u79CD\u7FA4\u5927\u5C0F");
		individualNumLabel.setFont(new Font("Century", Font.PLAIN, 14));
		individualNumLabel.setText("Individual Number");

		
		
		
		
		
		
		individualNumtextField = new MyTextField();
		individualNumtextField.setHorizontalAlignment(SwingConstants.CENTER);
		individualNumtextField.setFont(new Font("Century", Font.PLAIN, 14));
		individualNumtextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				individualNumtextField.selectAll();
			}
		});
		individualNumtextField.setText("0");

		JLabel normalGeneNumLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u6570\u91CF");
		normalGeneNumLabel.setFont(new Font("Century", Font.PLAIN, 14));
		normalGeneNumLabel.setText("Normal Gene Number");

		normalGeneNumTextField = new MyTextField();
		normalGeneNumTextField.setFont(new Font("Century", Font.PLAIN, 14));
		normalGeneNumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		normalGeneNumTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				normalGeneNumTextField.selectAll();
			}
		});
		normalGeneNumTextField.setText("0");

		JLabel normalGeneHeaderLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u5934\u957F");
		normalGeneHeaderLabel.setFont(new Font("Century", Font.PLAIN, 14));
		normalGeneHeaderLabel.setText("Normal Gene Header Length");

		normalGeneHeaderTextField = new MyTextField();
		normalGeneHeaderTextField.setHorizontalAlignment(SwingConstants.CENTER);
		normalGeneHeaderTextField.setFont(new Font("Century", Font.PLAIN, 14));
		normalGeneHeaderTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				normalGeneHeaderTextField.selectAll();
			}
		});
		normalGeneHeaderTextField.setText("0");

		JLabel connectionTypeLabel = new MyPrompt("\u666E\u901A\u57FA\u56E0\u8FDE\u63A5\u65B9\u5F0F");
		connectionTypeLabel.setFont(new Font("Century", Font.PLAIN, 14));
		connectionTypeLabel.setText("Connection Type");

		JLabel usedFuncLabel = new MyPrompt("\u6240\u7528\u7684\u51FD\u6570");
		usedFuncLabel.setFont(new Font("Century", Font.PLAIN, 14));
		usedFuncLabel.setText("Used Function");
		
				functionList = new JList<Function>();
				functionList.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				functionList.setVisibleRowCount(4);
		
				
				homeoticGeneRadioButton = new JRadioButton("Homeotic Gene");
				homeoticGeneRadioButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						homeoticGeneRadioButton.setSelected(true);
						connectionFuncRadioButton.setSelected(false);
						 connectionFuncPanel.setVisible(false);
						 homecticPanel.setVisible(true);
						
					}
				});
				homeoticGeneRadioButton.setFont(new Font("Century", Font.PLAIN, 14));
				homeoticGeneRadioButton.setSelected(true);
				buttonGroup.add(homeoticGeneRadioButton);
				
						connectionFuncRadioButton = new JRadioButton(
								"Connection Function");
						connectionFuncRadioButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								homeoticGeneRadioButton.setSelected(false);
								connectionFuncRadioButton.setSelected(true);
								connectionFuncPanel.setVisible(true);
								homecticPanel.setVisible(false);
								
							}
						});
						connectionFuncRadioButton.setFont(new Font("Century", Font.PLAIN, 14));
						buttonGroup.add(connectionFuncRadioButton);
																				
																				JPanel switchPanel = new JPanel();
																				switchPanel.setLayout(null);
																				
																						homecticPanel = new JPanel();
																						homecticPanel.setBounds(0, 0, 562, 103);
																						
																						homecticPanel.setLayout(null);
																						JLabel homeoticGeneNumLabel = new MyPrompt("\u540C\u6E90\u57FA\u56E0\u6570\u91CF");
																						homeoticGeneNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
																						homeoticGeneNumLabel.setFont(new Font("Century", Font.PLAIN, 14));
																						homeoticGeneNumLabel.setBounds(22, 7, 194, 30);
																						homeoticGeneNumLabel.setText("Homeotic Gene Number");
																						homecticPanel.add(homeoticGeneNumLabel);
																						
																								homeoticGeneTextField = new MyTextField();
																								homeoticGeneTextField.setHorizontalAlignment(SwingConstants.CENTER);
																								homeoticGeneTextField.setFont(new Font("Century", Font.PLAIN, 14));
																								homeoticGeneTextField.setBounds(222, 5, 129, 30);
																								homeoticGeneTextField.addFocusListener(new FocusAdapter() {
																									@Override
																									public void focusGained(FocusEvent arg0) {
																										homeoticGeneTextField.selectAll();
																									}
																								});
																								homeoticGeneTextField.setText("0");
																								homecticPanel.add(homeoticGeneTextField);
																								
																										JLabel homeoticGeneHeaderLabel = new MyPrompt("\u540C\u6E90\u57FA\u56E0\u5934\u957F");
																										homeoticGeneHeaderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
																										homeoticGeneHeaderLabel.setFont(new Font("Century", Font.PLAIN, 14));
																										homeoticGeneHeaderLabel.setText("Homeotic Gene Header Length");
																										homeoticGeneHeaderLabel.setBounds(8, 46, 206, 30);
																										homecticPanel.add(homeoticGeneHeaderLabel);
																										
																												homeoticGeneHeaderTextField = new MyTextField();
																												homeoticGeneHeaderTextField.setHorizontalAlignment(SwingConstants.CENTER);
																												homeoticGeneHeaderTextField.setFont(new Font("Century", Font.PLAIN, 14));
																												homeoticGeneHeaderTextField.setBounds(222, 48, 129, 30);
																												homeoticGeneHeaderTextField.addFocusListener(new FocusAdapter() {
																													@Override
																													public void focusGained(FocusEvent arg0) {
																														homeoticGeneHeaderTextField.selectAll();
																													}
																												});
																												homeoticGeneHeaderTextField.setText("0");
																												homecticPanel.add(homeoticGeneHeaderTextField);
																												
																														connectionComboBox = new JComboBox<Function>();
																														connectionComboBox.setLocation(224, 5);
																														connectionComboBox.setSize(129, 30);
																														connectionComboBox.setMaximumSize(new Dimension(110, 21));
																														
																																connectionFuncPanel = new JPanel();
																																connectionFuncPanel.setBounds(0, 0, 536, 46);
																																switchPanel.add(connectionFuncPanel);
																																switchPanel.add(homecticPanel);
																																connectionFuncPanel.setVisible(false);
																																connectionFuncPanel.add(connectionComboBox);
																																connectionFuncPanel.setVisible(false);
																																connectionFuncPanel.setLayout(null);
																																
																																		JLabel connectionFuncLabel = new MyPrompt("\u9009\u62E9\u8FDE\u63A5\u51FD\u6570");
																																		connectionFuncLabel.setFont(new Font("Century", Font.PLAIN, 14));
																																		connectionFuncLabel.setText("Connection Function");
																																		connectionFuncLabel.setBounds(82, 4, 143, 30);
																																		connectionFuncPanel.add(connectionFuncLabel);
																				GroupLayout groupLayout = new GroupLayout(this);
																				groupLayout.setHorizontalGroup(
																					groupLayout.createParallelGroup(Alignment.TRAILING)
																						.addGroup(groupLayout.createSequentialGroup()
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(98)
																									.addComponent(individualNumLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
																									.addGap(2)
																									.addComponent(individualNumtextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(80)
																									.addComponent(normalGeneNumLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
																									.addComponent(normalGeneNumTextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(36)
																									.addComponent(normalGeneHeaderLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
																									.addGap(2)
																									.addComponent(normalGeneHeaderTextField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(133)
																									.addComponent(usedFuncLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																									.addComponent(functionList, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(118)
																									.addComponent(connectionTypeLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
																									.addGap(9)
																									.addComponent(homeoticGeneRadioButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
																									.addGap(18)
																									.addComponent(connectionFuncRadioButton, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addContainerGap()
																									.addComponent(switchPanel, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)))
																							.addContainerGap(60, Short.MAX_VALUE))
																				);
																				groupLayout.setVerticalGroup(
																					groupLayout.createParallelGroup(Alignment.LEADING)
																						.addGroup(groupLayout.createSequentialGroup()
																							.addGap(11)
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(1)
																									.addComponent(individualNumLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																								.addComponent(individualNumtextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																							.addGap(15)
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(1)
																									.addComponent(normalGeneNumLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
																								.addComponent(normalGeneNumTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																							.addGap(14)
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addComponent(normalGeneHeaderLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																								.addComponent(normalGeneHeaderTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(3)
																									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																										.addComponent(homeoticGeneRadioButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																										.addComponent(connectionFuncRadioButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(2)
																									.addComponent(connectionTypeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
																							.addPreferredGap(ComponentPlacement.RELATED)
																							.addComponent(switchPanel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
																							.addGap(13)
																							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addComponent(usedFuncLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																								.addGroup(groupLayout.createSequentialGroup()
																									.addGap(4)
																									.addComponent(functionList, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
																							.addGap(44))
																				);
																				setLayout(groupLayout);
						//--		group.setSelected((ButtonModel) homeoticGeneRadioButton, true);
								//connectionFuncRadioButton.setSelected(true);

		
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
			Function function;
			for(int i=0;i<connectionComboBox.getItemCount();i++){
				function=connectionComboBox.getItemAt(i);
				if(function.equals(geneModel.getConnectionFunction()))
					connectionComboBox.setSelectedIndex(i);
			}
		}
		Function function;
		int result[]=new int[geneModel.getFunctionList().size()];
		for(int i=0;i<geneModel.getFunctionList().size();i++){
			function=geneModel.getFunctionList().get(i);
			for(int j=0;j<functionList.getModel().getSize();j++)
				if(functionList.getModel().getElementAt(j).equals(function)){
					result[i]=j;
					break;
				}
		}
		functionList.setSelectedIndices(result);
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
