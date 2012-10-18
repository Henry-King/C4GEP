package ui.conf.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.conf.model.AccuracyModel;
import ui.conf.model.Model;
import ui.conf.model.MyPrompt;
import ui.conf.model.MyTextField;
import ui.conf.model.MyTitle;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class AccuracyPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8245739950136079772L;
	public JTextField generationNumTextField;
	public JTextField selectionRangeTextField;
	public JTextField accuracytextField;

	public JLabel tooltip_maxGeneration;
	
	AccuracyModel accuracyModel;
	
	private JPanel mainPanel = null; 
	
	/**
	 * Create the panel.
	 */
	public AccuracyPanel() {
		
		
		
		
		
		
		
		
		
		JLabel maxGenerationLabel = new MyPrompt("\u6700\u5927\u6F14\u5316\u4EE3\u6570");
		maxGenerationLabel.setFont(new Font("Century", Font.PLAIN, 14));
		maxGenerationLabel.setText("Max Generation:");
		maxGenerationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		accuracytextField = new MyTextField();
		accuracytextField.setFont(new Font("Century", Font.PLAIN, 14));
		accuracytextField.setHorizontalAlignment(SwingConstants.CENTER);
		accuracytextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				accuracytextField.selectAll();
			}
		});
		accuracytextField.setText("0");
		
		
		JLabel accuracyLabel = new MyPrompt("\u6C42\u89E3\u7CBE\u5EA6");
		accuracyLabel.setFont(new Font("Century", Font.PLAIN, 14));
		accuracyLabel.setText("Accuracy:");
		accuracyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		generationNumTextField = new MyTextField();
		
		
		generationNumTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				tooltip_maxGeneration.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tooltip_maxGeneration.setVisible(false);
			}
		});
		generationNumTextField.setToolTipText("");
		generationNumTextField.setFont(new Font("Century", Font.PLAIN, 14));
		generationNumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		generationNumTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				generationNumTextField.selectAll();
			}
		});
		generationNumTextField.setText("0");
		
		
		JLabel selectionRangeLabel = new MyPrompt("\u9009\u62E9\u8303\u56F4");
		selectionRangeLabel.setFont(new Font("Century", Font.PLAIN, 14));
		selectionRangeLabel.setText("Selection Range:");
		selectionRangeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		selectionRangeTextField = new MyTextField();
		selectionRangeTextField.setFont(new Font("Century", Font.PLAIN, 14));
		selectionRangeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		selectionRangeTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				selectionRangeTextField.selectAll();
			}
		});
		selectionRangeTextField.setText("0");
		
		tooltip_maxGeneration = new JLabel("  Setting the max generation that needs a long type num greater than zero ");
		tooltip_maxGeneration.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(109, 109, 109)));
		tooltip_maxGeneration.setBackground(new Color(255, 255, 102));
		tooltip_maxGeneration.setOpaque(true);
		tooltip_maxGeneration.setFont(new Font("Century", Font.PLAIN, 14));
		tooltip_maxGeneration.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(maxGenerationLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(generationNumTextField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(tooltip_maxGeneration, GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(accuracyLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(accuracytextField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(selectionRangeLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(selectionRangeTextField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(maxGenerationLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(generationNumTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(tooltip_maxGeneration, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(accuracyLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(accuracytextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(selectionRangeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(selectionRangeTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
		);
		setLayout(groupLayout);
		
		
	}

	@Override
	public void dataUpdate(Model model) {
		this.accuracyModel = (AccuracyModel)model;
		generationNumTextField.setText(accuracyModel.getMaxGeneration().toString());
		selectionRangeTextField.setText(accuracyModel.getSelectionRange().toString());
		accuracytextField.setText(accuracyModel.getAccuracy().toString());
	}
	
	public void fillModel(AccuracyModel model){
		model.setMaxGeneration(Long.parseLong(generationNumTextField.getText().toString()));
		model.setSelectionRange(Float.parseFloat(selectionRangeTextField.getText().toString()));
		model.setAccuracy(Float.parseFloat(accuracytextField.getText().toString()));
	}
}
