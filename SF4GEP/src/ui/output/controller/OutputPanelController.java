package ui.output.controller;

import ui.output.view.JPanelForOutput;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.outputmodel.AlgInstance;
import domain.iservice.IgepAlgService;
import domain.service.alg.baseclass.Calculator;
import domain.service.output.DefalutGepOutput;
import domain.service.output.IgepOutput;


/**
 * Êä³öPanelµÄ¿ØÖÆÆ÷
 * @author ëøÁèÕÜ
 */
public class OutputPanelController {
	
	
	
	static KernelLink ml = null;
	
	MathCanvas mathCanvasA;
	MathCanvas mathCanvasB;
	
	public OutputPanelController() {
		
	}
	
	public static void init(JPanelForOutput outputPanel){
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
	
	
	
	
	public static void start(MathCanvas a,MathCanvas b){
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
		
		
		/*mathCanvasA = new MathCanvas(ml);
		mathCanvasB = new MathCanvas(ml);
		
		a = mathCanvasA;
		b = mathCanvasA;
		
		*/
		ml.evaluateToInputForm("Needs[\"" + KernelLink.PACKAGE_CONTEXT + "\"]", 0);
		ml.evaluateToInputForm("ConnectToFrontEnd[]", 0);
	}
	
	
	
	
	public static void drawPicture(IgepAlgService gepService,JPanelForOutput outputPanel){
		IgepOutput gepOutput = new DefalutGepOutput();
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

	
	
	
	
	
	
	
}
