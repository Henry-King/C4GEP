package ui.conf.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.chart.LineChart;
import ui.ChartPane;
import ui.app.CloseableTabComponent;
import ui.app.LinkLabel;
import ui.app.MainWnd;
import ui.conf.model.Model;
import ui.conf.model.OutputPictureModel;

import com.jtattoo.plaf.JTattooUtilities;
import com.sun.org.apache.xml.internal.security.Init;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OutputPicturePanel extends JPanel  implements Observer{
	
	private OutputPictureModel outputPictureModel;
	
	private GepAlgRun gepAlgRun;
	private MainWnd mainWnd;
	private KernelLink ml = null;
	private JPanel InfoPanel = null;
	private JPanel PicturePanel = null;
	private ConfPanel confPanel;
	
	
	private OutputChart FittingCurveChart;
	private OutputChart EvolutionGraphChart;
	
	
	private TDataBox FittingCurveBox;
	private TDataBox EvolutionBox;
	
	
	private JScrollBar FittingCurveScrollBar;
	private JScrollBar EvolutionScrollBar;
	
	private Element UserInputDataElement;
	private Element FittedResultElement;
	private Element EvolutionBestElement;
	private Element EvolutionWorseElement;
	
	public JTextArea tooltip_PicturePanel;
	public GroupLayout gl_InfoPanel;
	private void init(){
		FittingCurveBox = new TDataBox();
		EvolutionBox = new TDataBox();
	}
	
	public OutputPicturePanel(final MainWnd mainWnd,final ConfPanel confPanel) {
		init();
		
		this.mainWnd = mainWnd;
		this.confPanel = confPanel;
		
		InfoPanel = new JPanel();
		PicturePanel = new JPanel();
		
		
		
		
		setLayout(new BorderLayout(0,0));
		setBorder(new MatteBorder(0, 0, 7, 0, (Color) new Color(250, 250, 210)));
		
		tooltip_PicturePanel = new JTextArea("It's no picture info now.");
		tooltip_PicturePanel.setToolTipText("The picture status will show here");
		tooltip_PicturePanel.setFont(new Font("Century", Font.PLAIN, 14));
		tooltip_PicturePanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(250, 240, 230)));
		tooltip_PicturePanel.setBackground(new Color(255, 250, 205));
		tooltip_PicturePanel.setEditable(false);
		tooltip_PicturePanel.setLineWrap(true);
		tooltip_PicturePanel.setWrapStyleWord(true);

		gl_InfoPanel = new GroupLayout(InfoPanel);
		gl_InfoPanel.setHorizontalGroup(
			gl_InfoPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tooltip_PicturePanel, GroupLayout.DEFAULT_SIZE,100, Short.MAX_VALUE)
		);
		gl_InfoPanel.setVerticalGroup(
			gl_InfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_InfoPanel.createSequentialGroup()
					.addComponent(tooltip_PicturePanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		InfoPanel.setLayout(gl_InfoPanel);
		add(PicturePanel,BorderLayout.CENTER);
		
		
		//PicturePanel.setBorder(new MatteBorder(0, 0, 7, 0, (Color) new Color(250, 250, 210)));
		PicturePanel.setLayout(new GridLayout(1, 2,10,0));
		
		JPanel FittingCurvePanel = new JPanel();
		FittingCurvePanel.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(240, 230, 140)));
		FittingCurvePanel.setBackground(Color.WHITE);
		PicturePanel.add(FittingCurvePanel);
		
		JLabel lblNewLabel = new JLabel("Fitting Curve Graph",JLabel.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblRunTime = new JLabel("Runtime:");
		lblRunTime.setFont(new Font("Century", Font.PLAIN, 12));
		
		JLabel lblFittingSituation = new JLabel("Fitting Situation:");
		lblFittingSituation.setFont(new Font("Century", Font.PLAIN, 12));
		
		JSeparator separator = new JSeparator();
		
		ChartPane FittingCurveContainer = new ChartPane();
		FittingCurveContainer.setBackground(Color.WHITE);
		
		FittingCurveScrollBar = new JScrollBar();
		FittingCurveScrollBar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
			         //System.out.println(e.getValue());
			         FittingCurveChart.setXScaleLineVisible(true);
			         FittingCurveChart.setStartIndex(e.getValue());
			         FittingCurveChart.setEndIndex(e.getValue()+10);
			         //FittingCurveChart.setValueSpanCount(e.getValue());
			         //FittingCurveChart.setXScaleTextSpanCount(e.getValue());
			         
			}
		});
		
		
		
		FittingCurveScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		FittingCurveScrollBar.setEnabled(false);
		
		LinkLabel lblShowDetail = new LinkLabel("Show Detail");
		lblShowDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JTattooUtilities.getJavaVersion() >= 1.6) {
					
					
					
		    		JTabbedPane jtp = confPanel.projectTabPane;
		    		int tabCount = jtp.getTabCount();
		    		
		    		if (!confPanel.isHasFittingCurve()) {
		    			int position = tabCount - 2;
		    			String title = "FittingCurveDetail";
		    			JPanel panel = new JPanel();
		    			panel.setName("FittingCurveDetail");
		    			CloseableTabComponent ctc = new CloseableTabComponent(jtp,title);
		    			jtp.add(panel,position);
		    			jtp.setSelectedIndex(position);
			            jtp.setTabComponentAt(position, ctc);
			            jtp.setToolTipTextAt(position, "This is project " + (position + 1) + "  "+title);
			            confPanel.setHasFittingCurve(true);
		    		}else{
		    			for (int i = 0; i < tabCount; i++) {
		    				Component component = jtp.getComponentAt(i);
		    				String name = component.getName();
		    				System.out.println(name);
		    				if (name.equals("FittingCurveDetail")) {
		    					jtp.setSelectedIndex(i);
		    					//预留
							}else if (name.endsWith("EvolutionDetail")) {
								jtp.setSelectedIndex(i);
								//预留
							}
						}
		    		}
		    		
		    		
		    		
		    		
		            
		            
		            //jtp.addTab("Tab", null,welcomeVTextIcon,null);
		            
		            
		            
		            
		            
				}
				
				
				
				
			}
		});
		
		
		lblShowDetail.setFont(new Font("Century", Font.PLAIN, 12));
		
		
		GroupLayout gl_FittingCurvePanel = new GroupLayout(FittingCurvePanel);
		gl_FittingCurvePanel.setHorizontalGroup(
			gl_FittingCurvePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
					.addGap(15))
				.addComponent(FittingCurveScrollBar, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
				.addComponent(FittingCurveContainer, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_FittingCurvePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblShowDetail, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFittingSituation, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRunTime))
					.addGap(255))
		);
		gl_FittingCurvePanel.setVerticalGroup(
			gl_FittingCurvePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(lblRunTime, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblFittingSituation, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblShowDetail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(FittingCurveContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(FittingCurveScrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		FittingCurvePanel.setLayout(gl_FittingCurvePanel);
		
		JPanel EvolutionGraphPanel = new JPanel();
		EvolutionGraphPanel.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(240, 230, 140)));
		EvolutionGraphPanel.setBackground(Color.WHITE);
		PicturePanel.add(EvolutionGraphPanel);
		
		JLabel lblEvolutionGraph = new JLabel("Evolution Graph",JLabel.CENTER);
		lblEvolutionGraph.setFont(new Font("Arial", Font.BOLD, 16));
		
		JSeparator separator_1 = new JSeparator();
		
		
		
		
		JLabel label = new JLabel("Runtime:");
		label.setFont(new Font("Century", Font.PLAIN, 12));
		
		JLabel lblBestValueIn = new JLabel("Best Value in Best Individual:");
		lblBestValueIn.setFont(new Font("Century", Font.PLAIN, 12));
		
		JLabel lblWorseValueIn = new JLabel("Worse Value in Worse Individual:");
		lblWorseValueIn.setFont(new Font("Century", Font.PLAIN, 12));
		
		JPanel EvolutionContainer = new JPanel();
		EvolutionContainer.setBackground(Color.WHITE);
		
		EvolutionScrollBar = new JScrollBar();
		EvolutionScrollBar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
			         //System.out.println(e.getValue());
			         EvolutionGraphChart.setXScaleLineVisible(true);
			         EvolutionGraphChart.setStartIndex(e.getValue());
			         EvolutionGraphChart.setEndIndex(e.getValue()+10);
			         //FittingCurveChart.setValueSpanCount(e.getValue());
			         //FittingCurveChart.setXScaleTextSpanCount(e.getValue());
			         
			}
		});
		EvolutionScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		EvolutionScrollBar.setEnabled(false);
		
		
		//add(FittingCurveCanvas);
		
		
		GroupLayout gl_EvolutionGraphPanel = new GroupLayout(EvolutionGraphPanel);
		gl_EvolutionGraphPanel.setHorizontalGroup(
			gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEvolutionGraph, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
							.addGap(15))
						.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBestValueIn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWorseValueIn, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
							.addGap(177))))
				.addComponent(EvolutionContainer, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addComponent(EvolutionScrollBar, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_EvolutionGraphPanel.setVerticalGroup(
			gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEvolutionGraph, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblBestValueIn, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblWorseValueIn, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(EvolutionContainer, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(EvolutionScrollBar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
		);
		EvolutionGraphPanel.setLayout(gl_EvolutionGraphPanel);
		FittingCurveContainer.setLayout(new BorderLayout(0, 0));
		FittingCurveChart = new OutputChart(FittingCurveBox);
		FittingCurveChart.setToolTipText("FittingCurveToolTip");
		FittingCurveChart.setXAxisText("num");
		FittingCurveChart.setYAxisText("value");
		FittingCurveContainer.add(FittingCurveChart);
		EvolutionContainer.setLayout(new BorderLayout(0, 0));
		
		EvolutionGraphChart = new OutputChart(EvolutionBox);
		EvolutionGraphChart.setToolTipText("EvolutionToolTip");
		EvolutionGraphChart.setXAxisText("n");
		EvolutionGraphChart.setYAxisText("value");
		EvolutionContainer.add(EvolutionGraphChart);
		
		add(InfoPanel,BorderLayout.NORTH);
	}
	
	
	/**
	 * 监听Model的变化响应
	 */
	@Override
	public void dataUpdate(Model model) {
		this.outputPictureModel = (OutputPictureModel)model;
		gepAlgRun = outputPictureModel.getGepAlgRun();
		drawFittingCurve();
		drawEvolutionGraph();
		
	}
	
	
	
	public List<FittedValue> getFittingCurveList(){
//		gepAlgrun =  algOutputService.run(outputModel.getGepAlgConfiguration(),new AlgRunStep(), outputModel.getInputSet());
		Individual individual = gepAlgRun.getBestIndividual();
		List<FittedValue> resultList = individual.getFittedValues();
		return resultList;
	}
	
	
	public boolean drawFittingCurve(){
		
		boolean result = false;
		
		DataSet inputSet = gepAlgRun.getDataSet();
		
		List<FittedValue> resultList = new ArrayList<FittedValue>();
		for(int i=0;i<inputSet.getDataRows().size();i++){
			FittedValue fv = new FittedValue();
			fv.setDataRow(inputSet.getDataRows().get(i));
			fv.setFittedValue(inputSet.getDataRows().get(i).getResultColumn().getValue());
			resultList.add(fv);
		}
		List<FittedValue> caledList = getFittingCurveList();
		
		FittingCurveBox.clear();
		
		UserInputDataElement = new Node();
		UserInputDataElement.setDisplayName("User Input Data");
		UserInputDataElement.setName("UserInputData");
		UserInputDataElement.putChartColor(Color.GREEN);
		//设置标记的显示样式.
		UserInputDataElement.putChartInflexionStyle(TWaverConst.ANTENNA_TYPE_ELLIPSE);
		FittingCurveBox.addElement(UserInputDataElement);
		
		
		

		FittedResultElement = new Node();
		FittedResultElement.setDisplayName("Fitted Result");
		FittedResultElement.setName("FittedResult");
		FittedResultElement.putChartColor(Color.YELLOW);
		FittedResultElement.putChartInflexionStyle(TWaverConst.ANTENNA_TYPE_ELLIPSE);
		FittingCurveBox.addElement(FittedResultElement);
		
		
		
		int len = inputSet.getDataRows().size();
		for (int i = 0; i < len; i++) {
			FittingCurveChart.addXScaleText(""+i);
			UserInputDataElement.addChartValue(resultList.get(i).getFittedValue());
			
			FittedResultElement.addChartValue(caledList.get(i).getFittedValue());
			 //System.out.println(resultList.get(i).getFittedValue() + "|" + caledList.get(i).getFittedValue());
			//FittingCurveChart.getChartPane().repaint();
		}
		
		FittingCurveChart.setXScaleTextVisible(true);
		
		FittingCurveScrollBar.setEnabled(true);
		FittingCurveScrollBar.setMinimum(0);
		FittingCurveScrollBar.setMaximum(len);
		
		
		
		return true;
		
	}
	
	
	
	
	
	
	
	
	public boolean drawEvolutionGraph(){
		boolean result = false;
		
		List<Float> bestList = gepAlgRun.getMaxFitnesses();
		List<Float> worseList = gepAlgRun.getMinFitnesses();
		
		
		EvolutionBox.clear();
		
		EvolutionBestElement = new Node();
		EvolutionBestElement.setDisplayName("Best");
		EvolutionBestElement.setName("Best");
		EvolutionBestElement.putChartColor(Color.GREEN);
		EvolutionBestElement.putChartInflexionStyle(TWaverConst.ANTENNA_TYPE_ELLIPSE);
		EvolutionBox.addElement(EvolutionBestElement);
		
		EvolutionWorseElement = new Node();
		EvolutionWorseElement.setDisplayName("Worse");
		EvolutionWorseElement.setName("Worse");
		EvolutionWorseElement.putChartColor(Color.RED);
		EvolutionWorseElement.putChartInflexionStyle(TWaverConst.ANTENNA_TYPE_ELLIPSE);
		EvolutionBox.addElement(EvolutionWorseElement);
		
		
		int len = bestList.size();
		//int worseLen = worseList.size();
		for (int i = 0; i < len; i++) {
			EvolutionGraphChart.addXScaleText(""+i);
			EvolutionBestElement.addChartValue(bestList.get(i));
			EvolutionWorseElement.addChartValue(worseList.get(i));
		}
		EvolutionGraphChart.setXScaleTextVisible(true);
		EvolutionScrollBar.setEnabled(true);
		EvolutionScrollBar.setMinimum(0);
		EvolutionScrollBar.setMaximum(len-10);
		
			
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public boolean drawFittingCurve(){
		
		boolean result = false;
		
		DataSet inputSet = gepAlgRun.getDataSet();
		
		List<FittedValue> resultList = new ArrayList<FittedValue>();
		for(int i=0;i<inputSet.getDataRows().size();i++){
			FittedValue fv = new FittedValue();
			fv.setDataRow(inputSet.getDataRows().get(i));
			fv.setFittedValue(inputSet.getDataRows().get(i).getResultColumn().getValue());
			resultList.add(fv);
		}
		List<FittedValue> caledList = getFittingCurveList();
		
		
		StringBuffer oldStr = new StringBuffer();
		StringBuffer newStr = new StringBuffer();
		
		oldStr.append("o={");
		newStr.append("n={");
		
		for (int i = 0; i < inputSet.getDataRows().size(); i++) {
			oldStr.append(resultList.get(i).getFittedValue()+",");
			newStr.append(caledList.get(i).getFittedValue()+",");
		}
		
		oldStr.delete(oldStr.length()-2, oldStr.length());
		newStr.delete(newStr.length()-2, newStr.length());
		
		oldStr.append("};");
		newStr.append("};");
		String str = "ListLinePlot[{o, n}, AxesLabel -> {样本数, 拟合值}, GridLines -> Automatic," +
				"PlotLabel -> Style[Framed[ 最佳个体的拟合曲线图], 16, Blue," +
				"Background -> Lighter[Yellow]]," +
				"Epilog -> Inset[Panel[Grid[{" +
				"{Graphics[{Thick, Yellow, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1, ImageSize -> 20],用户输入}," +
				"{Graphics[{Dashed,Black, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1,ImageSize -> 20], 计算结果}" +
				"}]],Offset[{-2, -180}, Scaled[{1, 1}]]," +
				"{Right, Bottom}], PlotStyle -> {{Yellow,Thick}, {Black,Dashed}}]";
		
		FittingCurveCanvas.setMathCommand(oldStr.toString()+newStr.toString()+str);
		
		
		
		return true;
		
	}
	
	
	
	
	
	public boolean drawEvolutionGraph(){
		boolean result = false;
		
		StringBuffer bestStr = new StringBuffer();
		StringBuffer worseStr = new StringBuffer();
		
		
		
		bestStr.append("yb={");
		worseStr.append("yw={");
		
		List<Float> bestList = gepAlgRun.getMaxFitnesses();
		List<Float> worseList = gepAlgRun.getMinFitnesses();
		
		for (int i = 0; i < bestList.size() && i < worseList.size(); i++) {
			bestStr.append(bestList.get(i) + ",");
			worseStr.append(worseList.get(i) + ",");
		}
		
		bestStr.delete(bestStr.length()-2, bestStr.length());
		worseStr.delete(worseStr.length()-2, worseStr.length());
		
		bestStr.append("};");
		worseStr.append("};");
		
		String str = "ListLinePlot[{yb, yw}, AxesLabel -> {代数, 适应值}, GridLines -> Automatic,PlotLabel -> Style[Framed[ 每代最佳个体和最差个体的演化曲线图], 16, Blue,Background -> Lighter[Yellow]], " +
				"Epilog -> Inset[Panel[Grid[{" +
				"{Graphics[{Thick, Green, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1, ImageSize -> 20],最佳个体}," +
				"{Graphics[{Thick, Red, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1,ImageSize -> 20], 最差个体}" +
				"}]],Offset[{-2, -180}, Scaled[{1, 1}]], {Right, Bottom}], PlotStyle -> {{Green}, {Red}}]";
		
		EvolutionGraphCanvas.setMathCommand(bestStr.toString()+worseStr.toString()+str);
		
		return true;
	}
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 保存图片
	 * @param image
	 * @param jc
	 */
	public void savePicture(Image image,JFileChooser jc){ 
		if (image==null) {
			return;
		}
		
        int w = image.getWidth(this); 
        int h = image.getHeight(this);

//首先创建一个BufferedImage变量，因为ImageIO写图片用到了BufferedImage变量。 
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

//再创建一个Graphics变量，用来画出来要保持的图片，及上面传递过来的Image变量 
        Graphics g = bi.getGraphics(); 
        try { 
            g.drawImage(image, 0, 0, null);

//将BufferedImage变量写入文件中。 
            
            ImageIO.write(bi,"jpg",new File(jc.getSelectedFile().getAbsolutePath())); 
        } catch (IOException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    }

	
	





	
	class SaveHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser jc = new JFileChooser();
			int rVal = jc.showSaveDialog(OutputPicturePanel.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File dir = jc.getCurrentDirectory();
				File file = jc.getSelectedFile();
				
				
				
				//txt_InputPath.setText(file.toString());
				
				
				
				
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
			}
		}
	}
}
