package ui.conf.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.app.MainWnd;
import ui.conf.model.Model;
import ui.conf.model.OutputPictureModel;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OutputPicturePanel extends JPanel  implements Observer{

	
	private MathCanvas FittingCurveCanvas;
	private MathCanvas EvolutionGraphCanvas;
	
	private OutputPictureModel outputPictureModel;
	
	private GepAlgRun gepAlgRun;
	private MainWnd mainWnd;
	private KernelLink ml = null;
	
	public OutputPicturePanel(MainWnd mainWnd) {
		this.mainWnd = mainWnd;
		setBorder(new MatteBorder(0, 0, 7, 0,
				(Color) Color.LIGHT_GRAY));
		setLayout(new GridLayout(1, 2,10,0));
		
		JPanel FittingCurvePanel = new JPanel();
		FittingCurvePanel.setBackground(Color.WHITE);
		add(FittingCurvePanel);
		
		JPanel FittingCurveContainer = new JPanel();
		FittingCurveContainer.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Fitting Curve Graph",JLabel.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblRunTime = new JLabel("Runtime:");
		lblRunTime.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblFittingSituation = new JLabel("Fitting Situation:");
		lblFittingSituation.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_FittingCurvePanel = new GroupLayout(FittingCurvePanel);
		gl_FittingCurvePanel.setHorizontalGroup(
			gl_FittingCurvePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(FittingCurveContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_FittingCurvePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFittingSituation, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRunTime))
					.addGap(255))
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
					.addGap(15))
		);
		gl_FittingCurvePanel.setVerticalGroup(
			gl_FittingCurvePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_FittingCurvePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(lblRunTime)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFittingSituation)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(FittingCurveContainer, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
		);
		FittingCurveContainer.setLayout(new BorderLayout(0, 0));
		FittingCurvePanel.setLayout(gl_FittingCurvePanel);
		
		JPanel EvolutionGraphPanel = new JPanel();
		EvolutionGraphPanel.setBackground(Color.WHITE);
		add(EvolutionGraphPanel);
		
		JPanel EvolutionGraphContainer = new JPanel();
		EvolutionGraphContainer.setBackground(Color.WHITE);
		
		JLabel lblEvolutionGraph = new JLabel("Evolution Graph",JLabel.CENTER);
		lblEvolutionGraph.setFont(new Font("Arial", Font.BOLD, 16));
		
		JSeparator separator_1 = new JSeparator();
		
		
		
		ml = mainWnd.getMl();
		
		FittingCurveCanvas = new MathCanvas(ml);
		FittingCurveCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jc = new JFileChooser();
				//jc.setCurrentDirectory(new File("D:/"));
				//jc.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
				jc.setDialogTitle("Save Fitting Curve Gragh"); 
				String saveType[] = {"jpg"};
				jc.setSelectedFile(new File("FittingCurveGragh.jpg")); 
				jc.setFileFilter(new FileNameExtensionFilter("jpg", saveType));

				int rVal = jc.showSaveDialog(OutputPicturePanel.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					File dir = jc.getCurrentDirectory();
					File file = jc.getSelectedFile();
					
					
					
					//txt_InputPath.setText(file.toString());
					
					savePicture(FittingCurveCanvas.getImage(),jc);
					
					
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
				}
				
				
				
				
			}
		});
		FittingCurveCanvas.setBackground(Color.WHITE);
		
		FittingCurveContainer.add(FittingCurveCanvas);
		
		JLabel label = new JLabel("Runtime:");
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblBestValueIn = new JLabel("Best Value in Best Individual:");
		lblBestValueIn.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblWorseValueIn = new JLabel("Worse Value in Worse Individual:");
		lblWorseValueIn.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		//add(FittingCurveCanvas);
		
		
		GroupLayout gl_EvolutionGraphPanel = new GroupLayout(EvolutionGraphPanel);
		gl_EvolutionGraphPanel.setHorizontalGroup(
			gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addComponent(EvolutionGraphContainer, GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEvolutionGraph, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 334, Short.MAX_VALUE)
							.addGap(15))
						.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBestValueIn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWorseValueIn, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
							.addGap(177))))
		);
		gl_EvolutionGraphPanel.setVerticalGroup(
			gl_EvolutionGraphPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EvolutionGraphPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEvolutionGraph, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBestValueIn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWorseValueIn)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(EvolutionGraphContainer, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
		);
		EvolutionGraphContainer.setLayout(new BorderLayout(0, 0));
		EvolutionGraphPanel.setLayout(gl_EvolutionGraphPanel);
		
		EvolutionGraphCanvas = new MathCanvas(ml);
		EvolutionGraphCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser jc = new JFileChooser();
				//jc.setCurrentDirectory(new File("D:/"));
				//jc.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
				jc.setDialogTitle("Save Evolution Graph"); 
				String saveType[] = {"jpg"};
				jc.setSelectedFile(new File("EvolutionGraph.jpg")); 
				jc.setFileFilter(new FileNameExtensionFilter("jpg", saveType));

				int rVal = jc.showSaveDialog(OutputPicturePanel.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					File dir = jc.getCurrentDirectory();
					File file = jc.getSelectedFile();
					
					
					
					//txt_InputPath.setText(file.toString());
					
					savePicture(EvolutionGraphCanvas.getImage(),jc);
					
					
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
				}
				
				
				
				
				
				
			}
		});
		EvolutionGraphCanvas.setBackground(Color.WHITE);
		
		EvolutionGraphContainer.add(EvolutionGraphCanvas);
		//EvolutionGraphPanel.add(EvolutionGraphCanvas);
		//add(EvolutionGraphCanvas);
	}
	
	
	
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

	
	



	public MathCanvas getFittingCurveCanvas() {
		return FittingCurveCanvas;
	}



	public void setFittingCurveCanvas(MathCanvas fittingCurveCanvas) {
		FittingCurveCanvas = fittingCurveCanvas;
	}



	public MathCanvas getEvolutionGraphCanvas() {
		return EvolutionGraphCanvas;
	}



	public void setEvolutionGraphCanvas(MathCanvas evolutionGraphCanvas) {
		EvolutionGraphCanvas = evolutionGraphCanvas;
	}



	public KernelLink getMl() {
		return ml;
	}



	public void setMl(KernelLink ml) {
		this.ml = ml;
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
