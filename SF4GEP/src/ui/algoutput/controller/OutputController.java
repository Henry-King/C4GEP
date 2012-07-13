package ui.algoutput.controller;

import java.util.ArrayList;
import java.util.List;

import ui.algoutput.model.OutputModel;
import ui.algoutput.view.OutputView;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import domain.core.algInputDataProcess.DataSet;
import domain.core.algOutput.FittedValue;
import domain.core.algOutput.GepAlgRun;
import domain.core.algOutput.Individual;
import domain.core.algconfiguration.GepAlgConfiguration;
import domain.iservice.algOutput.IAlgOutputService;
import domain.service.algOutput.AlgOutputService;
import domain.service.algOutput.AlgRunStep;
import exception.IllegalInputSet;

public class OutputController {
	
	KernelLink ml = null;
	OutputModel outputModel = new OutputModel();
	GepAlgRun gepAlgrun;
	
	//MathCanvas mathCanvasA;
	//MathCanvas mathCanvasB;
	
	IAlgOutputService algOutputService = new AlgOutputService();
	
	
	public void initKernel(OutputView outputPanel){
		try {
			ml = MathLinkFactory
			.createKernelLink("-linkmode launch -linkname 'D:\\program files\\wolfram research\\mathematica\\8.0\\mathkernel.exe'");
			ml.discardAnswer();
		} catch (MathLinkException e1) {
			System.out.println("An error occurred connecting to the kernel.");
			if (ml != null)
				ml.close();
			return;
		}
		outputPanel.mathCanvasA = new MathCanvas(ml);
		outputPanel.mathCanvasB = new MathCanvas(ml);
		ml.evaluateToInputForm("Needs[\"" + KernelLink.PACKAGE_CONTEXT + "\"]", 0);
		ml.evaluateToInputForm("ConnectToFrontEnd[]", 0);
	}
	
	
	public List<FittedValue> getFittingCurveList(){
		gepAlgrun =  algOutputService.run(outputModel.getGepAlgConfiguration(),new AlgRunStep(), outputModel.getInputSet());
		Individual individual = gepAlgrun.getBestIndividual();
		List<FittedValue> resultList = individual.getFittedValues();
		return resultList;
	}
	
	
	public List<Float> getBestEvolutionGraph(){
		List<Float> bestList = algOutputService.getMaxFitnessInEveryGeneration(gepAlgrun);
		return bestList;
	}
	
	public List<Float> getWorseEvolutionGraph(){
		List<Float> worseList = algOutputService.getMinFitnessInEveryGeneration(gepAlgrun);
		return worseList;
	}
	
	
	
	
	public void setParameter(DataSet inputSet,GepAlgConfiguration gepAlgConfiguration){
		outputModel.setInputSet(inputSet);
		outputModel.setGepAlgConfiguration(gepAlgConfiguration);
		
	}
	
	
	public boolean drawFittingCurve(MathCanvas mathCanvas){
		
		boolean result = false;
		DataSet inputSet = outputModel.getInputSet();
		List<FittedValue> resultList = new ArrayList<FittedValue>();
		for(int i=0;i<inputSet.getDataRow().size();i++){
			FittedValue fv = new FittedValue();
			fv.setDataRow(inputSet.getDataRow().get(i));
			fv.setFittedValue(inputSet.getDataRow().get(i).getResultColumn().getValue());
			resultList.add(fv);
		}
		List<FittedValue> caledList = getFittingCurveList();
		
		
		StringBuffer oldStr = new StringBuffer();
		StringBuffer newStr = new StringBuffer();
		
		oldStr.append("o={");
		newStr.append("n={");
		
		for (int i = 0; i < inputSet.getDataRow().size(); i++) {
			oldStr.append(resultList.get(i).getFittedValue()+",");
			newStr.append(caledList.get(i).getFittedValue()+",");
		}
		
		oldStr.delete(oldStr.length()-2, oldStr.length());
		newStr.delete(newStr.length()-2, newStr.length());
		
		oldStr.append("};");
		newStr.append("};");
		String str = "ListLinePlot[{o, n}, AxesLabel -> {������, ���ֵ}, GridLines -> Automatic," +
				"PlotLabel -> Style[Framed[ ��Ѹ�����������ͼ], 16, Blue," +
				"Background -> Lighter[Yellow]]," +
				"Epilog -> Inset[Panel[Grid[{" +
				"{Graphics[{Thick, Yellow, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1, ImageSize -> 20],�û�����}," +
				"{Graphics[{Dashed,Black, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1,ImageSize -> 20], ������}" +
				"}]],Offset[{-2, -180}, Scaled[{1, 1}]]," +
				"{Right, Bottom}], PlotStyle -> {{Yellow,Thick}, {Black,Dashed}}]";
		
		mathCanvas.setMathCommand(oldStr.toString()+newStr.toString()+str);
		
		
		
		return true;
		
	}
	
	public boolean drawEvolutionGraph(MathCanvas mathCanvas){
		boolean result = false;
		
		StringBuffer bestStr = new StringBuffer();
		StringBuffer worseStr = new StringBuffer();
		
		
		
		bestStr.append("yb={");
		worseStr.append("yw={");
		
		List<Float> bestList = getBestEvolutionGraph();
		List<Float> worseList = getWorseEvolutionGraph();
		
		for (int i = 0; i < bestList.size() && i < worseList.size(); i++) {
			bestStr.append(bestList.get(i) + ",");
			worseStr.append(worseList.get(i) + ",");
		}
		
		bestStr.delete(bestStr.length()-2, bestStr.length());
		worseStr.delete(worseStr.length()-2, worseStr.length());
		
		bestStr.append("};");
		worseStr.append("};");
		
		String str = "ListLinePlot[{yb, yw}, AxesLabel -> {����, ��Ӧֵ}, GridLines -> Automatic,PlotLabel -> Style[Framed[ ÿ����Ѹ������������ݻ�����ͼ], 16, Blue,Background -> Lighter[Yellow]], " +
				"Epilog -> Inset[Panel[Grid[{" +
				"{Graphics[{Thick, Green, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1, ImageSize -> 20],��Ѹ���}," +
				"{Graphics[{Thick, Red, Line[{{0, 0}, {1, 0}}]}, AspectRatio -> .1,ImageSize -> 20], ������}" +
				"}]],Offset[{-2, -180}, Scaled[{1, 1}]], {Right, Bottom}], PlotStyle -> {{Green}, {Red}}]";
		
		mathCanvas.setMathCommand(bestStr.toString()+worseStr.toString()+str);
		
		return true;
	}
	
	
	/*
	public static void drawPicture(IgepAlgService gepService,OutputView outputPanel){
		IgepOutputService gepOutput = new GepOutputService();
		IgepAlgService myGepService = gepService;
		AlgInstance algInstance = myGepService.getMyAlgInstance();
		GepAlgorithm gepAlgorithm = myGepService.getMyGepAlgorithm();
		Calculator calculator = myGepService.getCalculator();
		 
		 
		Individual bestIndividual = gepAlgorithm.getPopulationQueue().getLast().getBestIndividual();
		outputPanel.mathCanvasA = gepOutput.drawImageA(calculator, bestIndividual, ml);
		outputPanel.mathCanvasB = gepOutput.drawImageB(algInstance,ml);
		
		
		outputPanel.mathCanvasA.setBounds(0, 0, 410, 310);
		outputPanel.outPutPanel_1.add(outputPanel.mathCanvasA);
		
		outputPanel.mathCanvasB.setBounds(0, 0, 418, 310);
		outputPanel.outputPanel_2.add(outputPanel.mathCanvasB);
		
	}
*/
	
	
	
	
	
}
