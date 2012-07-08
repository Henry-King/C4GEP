package ui.output.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.wolfram.jlink.MathCanvas;

public class JPanelForOutput extends JPanel {
    
	
	JLabel label = new JLabel("\u7B97\u6CD5\u8BA1\u7B97\u7ED3\u679C\u7684\u8F93\u51FA\u8DEF\u5F84");
	JLabel label_37 = new JLabel("\u8F93\u51FA\u4EE3\u6570");
	JButton btnBrowseOfOutput = new JButton("\u6D4F\u89C8");
	JTextField txtOutputPath = new JTextField();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
	JPanel panelForGeneration = new JPanel();
	JTextField textGeneration = new JTextField();
	JPanel panelForGeneRationFrom = new JPanel();
	JPanel panelPaint = new JPanel();
	public JPanel outPutPanel_1 = new JPanel();
	public JPanel outputPanel_2 = new JPanel();
	JTextField txtGenerationTo = new JTextField();
	JTextField txtGenerationFrom = new JTextField();
	
	public MathCanvas mathCanvasA = null;
	public MathCanvas mathCanvasB = null;
	public JPanelForOutput() {
		
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		
		
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		//计算结果的输出路径
		
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(14, 341, 174, 26);
		add(label);
		
		
		txtOutputPath.grabFocus();
		txtOutputPath.setBounds(14, 387, 290, 25);
        add(txtOutputPath);
		txtOutputPath.setColumns(10);
		
		
		//btnBrowseOfOutput.addActionListener(new OpenOutputHandler());
		btnBrowseOfOutput.setFont(new Font("宋体", Font.PLAIN, 15));
		btnBrowseOfOutput.setBounds(211, 438, 93, 23);
		add(btnBrowseOfOutput);
		
		
		//输出代数
		
		label_37.setFont(new Font("宋体", Font.PLAIN, 15));
		label_37.setBounds(439, 341, 82, 18);
		add(label_37);
		
		
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(439, 387, 307, 60);
		add(tabbedPane);
		
		//输出后多少代
		
		panelForGeneration.setBorder(null);
		panelForGeneration.setBackground(Color.WHITE);
		tabbedPane.addTab("后xx代", null, panelForGeneration, null);
		panelForGeneration.setLayout(null);
		
		
		textGeneration.setBounds(0, 0, 302, 26);
		panelForGeneration.add(textGeneration);
		textGeneration.setColumns(10);
		textGeneration.grabFocus();
		
		//输出从多少代到多少代
		
		panelForGeneRationFrom.setBorder(null);
		panelForGeneRationFrom.setBackground(Color.WHITE);
		tabbedPane.addTab("从XX代到XX代", null, panelForGeneRationFrom, null);
		panelForGeneRationFrom.setLayout(null);
		
		
		txtGenerationFrom.setBounds(0, 2, 121, 26);
		panelForGeneRationFrom.add(txtGenerationFrom);
		txtGenerationFrom.setColumns(10);
		txtGenerationFrom.grabFocus();
		JLabel lblTo = new JLabel("----->");
		lblTo.setBackground(Color.LIGHT_GRAY);
		lblTo.setForeground(Color.BLACK);
		lblTo.setBounds(131, 3, 42, 25);
		lblTo.setBorder(null);
		panelForGeneRationFrom.add(lblTo);
		
		
		txtGenerationTo.setBounds(173, 2, 129, 26);
		panelForGeneRationFrom.add(txtGenerationTo);
		txtGenerationTo.setColumns(10);
		txtGenerationTo.grabFocus();
		JButton btnRunGeneration = new JButton("\u786E\u5B9A");
		btnRunGeneration.setFont(new Font("宋体", Font.PLAIN, 15));
		btnRunGeneration.setBounds(653, 438, 93, 23);
		/*btnRunGeneration.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if((textGeneration.getText()!=null)||(textGeneration.getText()!="")){
					long generation=Long.parseLong(textGeneration.getText());
					output.setGeneration(generation);
				}
				else{
				   long generationFrom=Long.parseLong(txtGenerationFrom.getText());
				   long generationTo=Long.parseLong(txtGenerationTo.getText());
				   output.setGeneration(generationFrom,generationTo);
				}
				try {
					output.writeExcel(myGepService.getMyAlgInstance(), new File(txtOutputPath+"\\"+"Outputdemo.xls"));
				} catch (WriteException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myGepService.getMyAlgInstance();				
			}
			
		});*/
		add(btnRunGeneration);
	
		
		
		
		
		
		panelPaint.setBounds(1, 1, 822, 310);
		panelPaint.setLayout(null);
		add(panelPaint);
		panelPaint.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		//画图区1---------------------------------------------------------------------------------------------------
		outPutPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		outPutPanel_1.setBackground(Color.WHITE);
		outPutPanel_1.setBounds(0, 0, 410, 310);
		panelPaint.add(outPutPanel_1);
		
		
		//画图区2--------------------------------------------------------------------------------------------------------------
		
		outputPanel_2.setBackground(Color.WHITE);
		outputPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputPanel_2.setBounds(402, 0, 418, 310);
		panelPaint.add(outputPanel_2);
		outputPanel_2.setLayout(null);
	}

}
