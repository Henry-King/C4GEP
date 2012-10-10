package ui.app;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;

import domain.core.algconfiguration.Function;
import domain.core.algconfiguration.GeneConfiguration;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.core.algconfiguration.OperatorConfiguration;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.List;

public class GepAlgCfgInfoWnd extends JWindow{
	
	
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel mainPanel = null;
	private ProgressBarPanel loadProgressBarPanel;
	private GepAlgCfgInfoWnd thisWnd;
	
	public GepAlgCfgInfoWnd(GepAlgConfiguration gepAlgConfiguration,Point p) {
		thisWnd = this;
		this.setSize(360,340);
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				thisWnd.dispose();
			}
		});
		
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowText));
		mainPanel.setBackground(Color.WHITE);
		
		mainPanel.setLayout(null);
		
		JLabel lbl_Title = new JLabel("Profile Setting");
		lbl_Title.setForeground(SystemColor.menuText);
		lbl_Title.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_Title.setFont(new Font("宋体", Font.BOLD, 16));
		lbl_Title.setBounds(10, 0, 242, 26);
		mainPanel.add(lbl_Title);
		
		
		
		getContentPane().add(mainPanel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 25, 341, 7);
		mainPanel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Profile Name:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 36, 85, 15);
		mainPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Accuracy Paras"));
		panel.setBounds(10, 54, 340, 58);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblMaxGeneration = new JLabel("Max Generation:");
		lblMaxGeneration.setFont(new Font("Arial", Font.PLAIN, 10));
		lblMaxGeneration.setBounds(10, 18, 85, 15);
		panel.add(lblMaxGeneration);
		
		JLabel lblSelectionRange = new JLabel("Selection Range:");
		lblSelectionRange.setFont(new Font("Arial", Font.PLAIN, 10));
		lblSelectionRange.setBounds(10, 33, 85, 15);
		panel.add(lblSelectionRange);
		
		JLabel lblAccuracy = new JLabel("Accuracy:");
		lblAccuracy.setFont(new Font("Arial", Font.PLAIN, 10));
		lblAccuracy.setBounds(169, 18, 48, 15);
		panel.add(lblAccuracy);
		
		JLabel info_maxGeneratiron = new JLabel("xxx");
		info_maxGeneratiron.setFont(new Font("Arial", Font.PLAIN, 10));
		info_maxGeneratiron.setBounds(95, 17, 64, 15);
		panel.add(info_maxGeneratiron);
		
		JLabel info_accuracy = new JLabel("xxx");
		info_accuracy.setFont(new Font("Arial", Font.PLAIN, 10));
		info_accuracy.setBounds(217, 17, 85, 15);
		panel.add(info_accuracy);
		
		JLabel info_selectionRange = new JLabel("xxx");
		info_selectionRange.setFont(new Font("Arial", Font.PLAIN, 10));
		info_selectionRange.setBounds(95, 32, 76, 15);
		panel.add(info_selectionRange);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(BorderFactory.createTitledBorder("Gene Paras"));
		panel_1.setBounds(10, 117, 340, 99);
		mainPanel.add(panel_1);
		
		JLabel lblIndividualNumber = new JLabel("Individual Number:");
		lblIndividualNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		lblIndividualNumber.setBounds(12, 18, 95, 15);
		panel_1.add(lblIndividualNumber);
		
		JLabel lblNormalGeneHeader = new JLabel("Normal Gene Header Length:");
		lblNormalGeneHeader.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNormalGeneHeader.setBounds(169, 32, 139, 15);
		panel_1.add(lblNormalGeneHeader);
		
		JLabel lblNormalGeneNumber = new JLabel("Normal Gene Number:");
		lblNormalGeneNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNormalGeneNumber.setBounds(11, 32, 111, 15);
		panel_1.add(lblNormalGeneNumber);
		
		JLabel lblUseHomeoticGene = new JLabel("Is Used Homeotic Gene:");
		lblUseHomeoticGene.setFont(new Font("Arial", Font.PLAIN, 10));
		lblUseHomeoticGene.setBounds(11, 45, 129, 15);
		panel_1.add(lblUseHomeoticGene);
		
		JPanel UseHomeoticPanel = new JPanel();
		UseHomeoticPanel.setBackground(Color.WHITE);
		UseHomeoticPanel.setBounds(6, 57, 316, 37);
		
		
		
		UseHomeoticPanel.setLayout(null);
		
		JLabel lblHomeoticGeneNumber = new JLabel("Homeotic Gene Number:");
		lblHomeoticGeneNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		lblHomeoticGeneNumber.setBounds(5, 2, 123, 13);
		UseHomeoticPanel.add(lblHomeoticGeneNumber);
		
		JLabel lblHomeoticGeneHeader = new JLabel("Homeotic Gene Header Number:");
		lblHomeoticGeneHeader.setFont(new Font("Arial", Font.PLAIN, 10));
		lblHomeoticGeneHeader.setBounds(5, 18, 160, 13);
		UseHomeoticPanel.add(lblHomeoticGeneHeader);
		
		
		
		
		
		JLabel info_HomeoticGeneNumber = new JLabel("xxx");
		info_HomeoticGeneNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		info_HomeoticGeneNumber.setBounds(123, 0, 89, 15);
		UseHomeoticPanel.add(info_HomeoticGeneNumber);
		
		JLabel info_HomeoticGeneHeaderNumber = new JLabel("xxx");
		info_HomeoticGeneHeaderNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		info_HomeoticGeneHeaderNumber.setBounds(164, 16, 89, 15);
		UseHomeoticPanel.add(info_HomeoticGeneHeaderNumber);
		
		
		JPanel NoUseHomeoticPanel = new JPanel();
		NoUseHomeoticPanel.setBounds(6, 57, 316, 37);
		
		
		
		panel_1.add(NoUseHomeoticPanel);
		panel_1.add(UseHomeoticPanel);
		
		NoUseHomeoticPanel.setBackground(Color.WHITE);
		NoUseHomeoticPanel.setLayout(null);
		
		JLabel label_2 = new JLabel("Connection Function:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 10));
		label_2.setBounds(5, 2, 103, 15);
		NoUseHomeoticPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Function List:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 10));
		label_3.setBounds(5, 18, 70, 15);
		NoUseHomeoticPanel.add(label_3);
		
		JLabel info_connectionFunction = new JLabel("xxx");
		info_connectionFunction.setFont(new Font("Arial", Font.PLAIN, 10));
		info_connectionFunction.setBounds(108, 1, 35, 15);
		NoUseHomeoticPanel.add(info_connectionFunction);
		
		JLabel info_functionList = new JLabel("xxx");
		info_functionList.setFont(new Font("Arial", Font.PLAIN, 10));
		info_functionList.setBounds(72, 17, 234, 15);
		NoUseHomeoticPanel.add(info_functionList);
		
		JLabel info_individualNumber = new JLabel("xxx");
		info_individualNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		info_individualNumber.setBounds(104, 17, 73, 15);
		panel_1.add(info_individualNumber);
		
		JLabel info_normalGeneNumber = new JLabel("xxx");
		info_normalGeneNumber.setFont(new Font("Arial", Font.PLAIN, 10));
		info_normalGeneNumber.setBounds(117, 31, 42, 15);
		panel_1.add(info_normalGeneNumber);
		
		JLabel info_normalGeneHeaderLength = new JLabel("xxx");
		info_normalGeneHeaderLength.setFont(new Font("Arial", Font.PLAIN, 10));
		info_normalGeneHeaderLength.setBounds(309, 31, 27, 15);
		panel_1.add(info_normalGeneHeaderLength);
		
		JLabel info_isUsedHomeoticGene = new JLabel("xxx");
		info_isUsedHomeoticGene.setForeground(new Color(255, 0, 0));
		info_isUsedHomeoticGene.setFont(new Font("Arial", Font.PLAIN, 10));
		info_isUsedHomeoticGene.setBounds(127, 44, 88, 15);
		panel_1.add(info_isUsedHomeoticGene);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(BorderFactory.createTitledBorder("Operator Paras"));
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 215, 338, 118);
		mainPanel.add(panel_2);
		
		JLabel lblMutateRate = new JLabel("Mutate Rate:");
		lblMutateRate.setFont(new Font("Arial", Font.PLAIN, 10));
		lblMutateRate.setBounds(8, 18, 63, 15);
		panel_2.add(lblMutateRate);
		
		JLabel lblIsTransportRate = new JLabel("Is Transport Rate:");
		lblIsTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		lblIsTransportRate.setBounds(8, 31, 85, 15);
		panel_2.add(lblIsTransportRate);
		
		JLabel lblIsElements = new JLabel("Is Elements:");
		
		lblIsElements.setFont(new Font("Arial", Font.PLAIN, 10));
		lblIsElements.setBounds(167, 31, 57, 15);
		panel_2.add(lblIsElements);
		
		JLabel lblRisTransportRate = new JLabel("Ris Transport Rate:");
		lblRisTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		lblRisTransportRate.setBounds(8, 44, 94, 15);
		panel_2.add(lblRisTransportRate);
		
		JLabel lblRisElement = new JLabel("Ris Element:");
		lblRisElement.setFont(new Font("Arial", Font.PLAIN, 10));
		lblRisElement.setBounds(167, 44, 64, 15);
		panel_2.add(lblRisElement);
		
		JLabel lblGeneTransportRate = new JLabel("Gene Transport Rate:");
		lblGeneTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		lblGeneTransportRate.setBounds(8, 57, 113, 15);
		panel_2.add(lblGeneTransportRate);
		
		JLabel lblOnePointRecombine = new JLabel("One Point Recombine Rate:");
		lblOnePointRecombine.setFont(new Font("Arial", Font.PLAIN, 10));
		lblOnePointRecombine.setBounds(8, 70, 140, 15);
		panel_2.add(lblOnePointRecombine);
		
		JLabel lblTwoPointRecombine = new JLabel("Two Point Recombine Rate:");
		lblTwoPointRecombine.setFont(new Font("Arial", Font.PLAIN, 10));
		lblTwoPointRecombine.setBounds(8, 83, 140, 15);
		panel_2.add(lblTwoPointRecombine);
		
		JLabel lblGeneRecombineRate = new JLabel("Gene Recombine Rate:");
		lblGeneRecombineRate.setFont(new Font("Arial", Font.PLAIN, 10));
		lblGeneRecombineRate.setBounds(8, 97, 118, 15);
		panel_2.add(lblGeneRecombineRate);
		
		JLabel info_mutateRate = new JLabel("xxx");
		info_mutateRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_mutateRate.setBounds(71, 17, 63, 15);
		panel_2.add(info_mutateRate);
		
		JLabel info_IsTransportRate = new JLabel("xxx");
		info_IsTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_IsTransportRate.setBounds(91, 30, 57, 15);
		panel_2.add(info_IsTransportRate);
		
		JLabel info_IsElements = new JLabel("xxx");
		info_IsElements.setFont(new Font("Arial", Font.PLAIN, 10));
		info_IsElements.setBounds(225, 30, 85, 15);
		panel_2.add(info_IsElements);
		
		JLabel info_RisTransportRate = new JLabel("xxx");
		info_RisTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_RisTransportRate.setBounds(100, 43, 57, 15);
		panel_2.add(info_RisTransportRate);
		
		JLabel info_RisElements = new JLabel("xxx");
		info_RisElements.setFont(new Font("Arial", Font.PLAIN, 10));
		info_RisElements.setBounds(227, 44, 57, 15);
		panel_2.add(info_RisElements);
		
		JLabel info_geneTransportRate = new JLabel("xxx");
		info_geneTransportRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_geneTransportRate.setBounds(110, 56, 57, 15);
		panel_2.add(info_geneTransportRate);
		
		JLabel info_onePointRecombineRate = new JLabel("xxx");
		info_onePointRecombineRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_onePointRecombineRate.setBounds(143, 69, 57, 15);
		panel_2.add(info_onePointRecombineRate);
		
		JLabel info_twoPointRecombineRate = new JLabel("xxx");
		info_twoPointRecombineRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_twoPointRecombineRate.setBounds(143, 82, 57, 15);
		panel_2.add(info_twoPointRecombineRate);
		
		JLabel info_geneRecombineRate = new JLabel("xxx");
		info_geneRecombineRate.setFont(new Font("Arial", Font.PLAIN, 10));
		info_geneRecombineRate.setBounds(121, 96, 57, 15);
		panel_2.add(info_geneRecombineRate);
		
		JLabel info_profileName = new JLabel("xxx");
		info_profileName.setFont(new Font("Arial", Font.PLAIN, 13));
		info_profileName.setBounds(115, 36, 231, 15);
		mainPanel.add(info_profileName);
		
		//this.pack();
		setLocation(p);
		
		//Initial Data
		NumberFormat nt = NumberFormat.getPercentInstance();
		//设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		
		GeneConfiguration geneConfiguration = gepAlgConfiguration.getIndividualConfiguration().getGeneConfiguration();
		OperatorConfiguration operatorConfiguration = gepAlgConfiguration.getOperatorConfiguration();
		
		
		info_maxGeneratiron.setText(gepAlgConfiguration.getMaxGeneration().toString());
		info_selectionRange.setText(gepAlgConfiguration.getSelectionRange().toString());
		info_accuracy.setText(gepAlgConfiguration.getAccuracy().toString());
		
		info_individualNumber.setText(gepAlgConfiguration.getIndividualConfiguration().getIndividualNumber().toString());
		info_normalGeneNumber.setText(geneConfiguration.getNormalGeneNumber().toString());
		info_normalGeneHeaderLength.setText(geneConfiguration.getNormalGeneHeaderLength().toString());
		
		boolean isUseHomeoticGene = geneConfiguration.getUseHomeoticGene();
		if (isUseHomeoticGene) {
			info_isUsedHomeoticGene.setText("true");
			info_HomeoticGeneNumber.setText(geneConfiguration.getHomeoticGeneNumber().toString());
			info_HomeoticGeneHeaderNumber.setText(geneConfiguration.getHomeoticGeneHeaderLength().toString());
			
		}else{
			info_isUsedHomeoticGene.setText("false");
			info_connectionFunction.setText(geneConfiguration.getConnectionFunctionString());
			
			List<Function> functionList = geneConfiguration.getFunctionUsed();
			
			StringBuffer functionListBuffer = new StringBuffer();
			int len = functionList.size();
			for (int i = 0; i < len; i++) {
				functionListBuffer. append(functionList.get(i) + ",");
			}
			len = functionListBuffer.length();
			functionListBuffer.replace(len-1, len, "");
			info_functionList.setText(functionListBuffer.toString());
		}
		
		
		
		
		info_mutateRate.setText(nt.format(operatorConfiguration.getMutateRate()).toString());
		info_IsTransportRate.setText(nt.format(operatorConfiguration.getIsTransportRate()).toString());
		
		
		
		Integer[] elements = operatorConfiguration.getIsElement();
		StringBuffer sb = new StringBuffer();
		int len = elements.length;
		for(int i = 0; i < len; i++){
		 sb. append(elements[i] + ",");
		}
		len = sb.length();
		sb.replace(len-1, len, "");
		info_IsElements.setText(sb.toString());
		
		info_RisTransportRate.setText(nt.format(operatorConfiguration.getRisTransportRate()).toString());
		
		elements = operatorConfiguration.getRisElement();
		sb = new StringBuffer();
		len = elements.length;
		for(int i = 0; i < len; i++){
		 sb. append(elements[i] + ",");
		}
		len = sb.length();
		sb.replace(len-1, len, "");
		info_RisElements.setText(sb.toString());
		
		
		info_geneTransportRate.setText(nt.format(operatorConfiguration.getGeneTransportRate()).toString());
		info_onePointRecombineRate.setText(nt.format(operatorConfiguration.getOnePointRecombineRate()).toString());
		info_twoPointRecombineRate.setText(nt.format(operatorConfiguration.getTwoPointRecombineRate()).toString());
		info_geneRecombineRate.setText(nt.format(operatorConfiguration.getGeneRecombineRate()).toString());
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void setLocation(Point p){
		this.setLocation((int)p.getX()+90, (int)p.getY()+190);
	}

	public JProgressBar getLoadBar() {
		return loadProgressBarPanel.getLoadBar();
	}
}
