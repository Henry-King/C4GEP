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
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.BorderLayout;

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
		GridBagConstraints gbc_ISLengthTextField = new GridBagConstraints();
		GridBagConstraints gbc_RISLengthLabel = new GridBagConstraints();
		gbc_RISLengthLabel.anchor = GridBagConstraints.WEST;
		gbc_RISLengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RISLengthLabel.gridx = 1;
		gbc_RISLengthLabel.gridy = 5;
		
		Font font = new Font("Century", Font.PLAIN, 14);
			setLayout(new BorderLayout(0, 0));
			
			JPanel ContainerPanel = new JPanel();
			add(ContainerPanel);
			ContainerPanel.setLayout(new GridLayout(1, 3, 0, 10));
			
			JPanel MutatePanel = new JPanel();
			ContainerPanel.add(MutatePanel);
			MutatePanel.setLayout(null);
			
			
			JLabel mutateLabel = new MyPrompt("\u53D8\u5F02\u6982\u7387   ");
			mutateLabel.setBounds(10, 22, 123, 30);
			MutatePanel.add(mutateLabel);
			mutateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			mutateLabel.setFont(font);
			mutateLabel.setText("Mutate Rate");
			
			mutateTextField = new MyTextField();
			mutateTextField.setFont(font);
			mutateTextField.setHorizontalAlignment(SwingConstants.CENTER);
			mutateTextField.setBounds(143, 24, 113, 30);
			MutatePanel.add(mutateTextField);
			mutateTextField.setText("0");
			
			JLabel lblTheMutateRate = new JLabel("<html> <body>&nbsp;&nbsp;The mutate rate descript a rate that  gep item can be mutated in a way<br/>\r\n&nbsp;&nbsp;You can click here for more details.\r\n</body> </html>");
			lblTheMutateRate.setBounds(10, 98, 320, 67);
			MutatePanel.add(lblTheMutateRate);
			mutateTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					mutateTextField.selectAll();
				}
			});
			
			JPanel TransportPanel = new JPanel();
			ContainerPanel.add(TransportPanel);
			TransportPanel.setLayout(null);
			
			JLabel ISLabel = new MyPrompt("IS\u8F6C\u5EA7\u6982\u7387 ");
			ISLabel.setBounds(11, 24, 142, 30);
			TransportPanel.add(ISLabel);
			ISLabel.setText("IS Transport Rate");
			ISLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			ISLabel.setFont(font);
			
			JLabel ISLengthLabel = new MyPrompt("IS\u8F6C\u5EA7\u957F\u5EA6 ");
			ISLengthLabel.setBounds(11, 64, 142, 30);
			TransportPanel.add(ISLengthLabel);
			ISLengthLabel.setText("IS Transport Length");
			ISLengthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			ISLengthLabel.setFont(font);
			
			ISLengthTextField = new MyTextField();
			ISLengthTextField.setHorizontalAlignment(SwingConstants.CENTER);
			ISLengthTextField.setFont(font);
			ISLengthTextField.setBounds(165, 66, 113, 30);
			TransportPanel.add(ISLengthTextField);
			ISLengthTextField.setText("0");
			
			ISTextField = new MyTextField();
			ISTextField.setHorizontalAlignment(SwingConstants.CENTER);
			ISTextField.setFont(font);
			ISTextField.setBounds(165, 26, 113, 30);
			TransportPanel.add(ISTextField);
			ISTextField.setText("0");
			
			RISTextField = new MyTextField();
			RISTextField.setFont(new Font("Century", Font.PLAIN, 14));
			RISTextField.setHorizontalAlignment(SwingConstants.CENTER);
			RISTextField.setBounds(165, 120, 113, 30);
			TransportPanel.add(RISTextField);
			RISTextField.setText("0");
			
			RISLengthTextField = new MyTextField();
			RISLengthTextField.setHorizontalAlignment(SwingConstants.CENTER);
			RISLengthTextField.setFont(new Font("Century", Font.PLAIN, 14));
			RISLengthTextField.setBounds(165, 160, 113, 30);
			TransportPanel.add(RISLengthTextField);
			RISLengthTextField.setText("0");
			
			JLabel RISLabel = new MyPrompt("RIS\u8F6C\u5EA7\u6982\u7387");
			RISLabel.setBounds(13, 119, 142, 30);
			TransportPanel.add(RISLabel);
			RISLabel.setText("RIS Transport Rate");
			RISLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			RISLabel.setFont(font);
			
			JLabel RISLengthLabel = new MyPrompt("RIS\u8F6C\u5EA7\u957F\u5EA6");
			RISLengthLabel.setBounds(0, 160, 156, 30);
			TransportPanel.add(RISLengthLabel);
			RISLengthLabel.setText("RIS Transport Length");
			RISLengthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			RISLengthLabel.setFont(font);
			
			JLabel geneLabel = new JLabel("Gene Transport Rate");
			geneLabel.setBounds(9, 215, 148, 30);
			TransportPanel.add(geneLabel);
			geneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			geneLabel.setFont(font);
			
				
				geneTextField = new MyTextField();
				geneTextField.setHorizontalAlignment(SwingConstants.CENTER);
				geneTextField.setFont(new Font("Century", Font.PLAIN, 14));
				geneTextField.setBounds(165, 216, 113, 30);
				TransportPanel.add(geneTextField);
				geneTextField.setText("0");
				
				
				
				
				geneTextField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						geneTextField.selectAll();
					}
				});
			RISLengthTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					RISLengthTextField.selectAll();
				}
			});
			RISTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					RISTextField.selectAll();
				}
			});
			ISTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					ISTextField.selectAll();
				}
			});
			ISLengthTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					ISLengthTextField.selectAll();
				}
			});
			
			
			JPanel RecombinationPanel = new JPanel();
			ContainerPanel.add(RecombinationPanel);
			RecombinationPanel.setLayout(null);
			
			JLabel oneCombineLabel = new MyPrompt("\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
			oneCombineLabel.setBounds(5, 26, 137, 30);
			RecombinationPanel.add(oneCombineLabel);
			oneCombineLabel.setText("One Point Combine ");
			oneCombineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			oneCombineLabel.setFont(font);
			
			oneCombineTextField = new MyTextField();
			oneCombineTextField.setHorizontalAlignment(SwingConstants.CENTER);
			oneCombineTextField.setFont(new Font("Century", Font.PLAIN, 14));
			oneCombineTextField.setBounds(147, 26, 113, 30);
			RecombinationPanel.add(oneCombineTextField);
			oneCombineTextField.setText("0");
			
			twoCombineTextField = new MyTextField();
			twoCombineTextField.setHorizontalAlignment(SwingConstants.CENTER);
			twoCombineTextField.setFont(new Font("Century", Font.PLAIN, 14));
			twoCombineTextField.setBounds(147, 66, 113, 30);
			RecombinationPanel.add(twoCombineTextField);
			twoCombineTextField.setText("0");
			
			JLabel twoCombineLabel = new MyPrompt("\u4E24\u70B9\u91CD\u7EC4\u6982\u7387");
			twoCombineLabel.setBounds(14, 66, 127, 30);
			RecombinationPanel.add(twoCombineLabel);
			twoCombineLabel.setText("Two Point Combine");
			twoCombineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			twoCombineLabel.setFont(font);
			
			JLabel geneCombineLabel = new MyPrompt("\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
			geneCombineLabel.setBounds(-2, 105, 143, 30);
			RecombinationPanel.add(geneCombineLabel);
			geneCombineLabel.setText("Gene Combine");
			geneCombineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			geneCombineLabel.setFont(font);
			
			geneCombineTextField = new MyTextField();
			geneCombineTextField.setHorizontalAlignment(SwingConstants.CENTER);
			geneCombineTextField.setFont(new Font("Century", Font.PLAIN, 14));
			geneCombineTextField.setBounds(147, 106, 113, 30);
			RecombinationPanel.add(geneCombineTextField);
			geneCombineTextField.setText("0");
			geneCombineTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					geneCombineTextField.selectAll();
				}
			});
			twoCombineTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					twoCombineTextField.selectAll();
				}
			});
			oneCombineTextField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					oneCombineTextField.selectAll();
				}
			});
		
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
		len = sb.length();
		sb.replace(len-1, len, "");
		ISLengthTextField.setText(sb.toString());
		
		
		
		RISTextField.setText(operatorModel.getRisTransportRate().toString());
		
		elements = operatorModel.getRisElement();
		sb = new StringBuffer();
		len = elements.length;
		for(int i = 0; i < len; i++){
		 sb. append(elements[i] + ",");
		}
		len = sb.length();
		sb.replace(len-1, len, "");
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
