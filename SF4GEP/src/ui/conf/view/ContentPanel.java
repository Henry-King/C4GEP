package ui.conf.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.wolfram.jlink.MathCanvas;

import ui.app.MainWnd;
import ui.conf.controller.AccuracyController;
import ui.conf.controller.GeneController;
import ui.conf.controller.OperatorController;
import ui.conf.controller.OutputPictureController;
import ui.conf.model.AccuracyModel;
import ui.conf.model.GeneModel;
import ui.conf.model.OperatorModel;
import ui.conf.model.OutputPictureModel;

public class ContentPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 143362423596329375L;
	OutputPicturePanel outputPicturePanel;
	JTabbedPane tabbedPane;

	private AccuracyPanel accuracyPanel;
	private GenePanel genePanel;
	private OperatorPanel operatorPanel;
	private JPanel resultPanel;
	private MainWnd mainWnd;
	
	public AccuracyController accuracyController;
	public GeneController geneController;
	public OperatorController operatorController;
	public OutputPictureController outputPictureController;
	
	
	
	/**
	 * Create the panel.
	 */
	public ContentPanel(MainWnd mainWnd) {
		this.mainWnd = mainWnd;
		this.setLayout(new BorderLayout());
		/* »­Í¼Ãæ°å */
		outputPicturePanel = new OutputPicturePanel(mainWnd);
		OutputPictureModel outputPictureModel = new OutputPictureModel();
		outputPictureController = new OutputPictureController(outputPictureModel,outputPicturePanel);
		add("North", outputPicturePanel);

		
		
		/*
		JPanel FittingCurvePanel = new JPanel();
		OutputPicturePanel.add(FittingCurvePanel);

		JPanel EvolutionGraphPanel = new JPanel();
		OutputPicturePanel.add(EvolutionGraphPanel);
		*/
		
		
		
		
		
		
		/* TabbedPanel */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		accuracyPanel = new AccuracyPanel();
		AccuracyModel accuracyModel = new AccuracyModel();
		accuracyController = new AccuracyController(accuracyModel,accuracyPanel);
		
		
		
		tabbedPane
				.addTab("\u6C42\u89E3\u7CBE\u5EA6",
						null,
						accuracyPanel,
						"\u5728\u8FD9\u91CC\u8BBE\u7F6E\u6C42\u89E3\u7CBE\u5EA6\u548C\u505C\u673A\u6761\u4EF6");

		
		
		
		
		genePanel = new GenePanel(mainWnd);
		GeneModel geneModel = new GeneModel();
		geneController = new GeneController(geneModel,genePanel);
		
		
		tabbedPane.addTab("\u4E2A\u4F53/\u57FA\u56E0\u53C2\u6570", null,
				genePanel, null);

		operatorPanel = new OperatorPanel();
		OperatorModel operatorModel = new OperatorModel();
		operatorController = new OperatorController(operatorModel,operatorPanel);
		
		
		tabbedPane
				.addTab("\u6F14\u5316\u53C2\u6570", null, operatorPanel, null);


		add(tabbedPane);
		
		resultPanel = new ResultPanel();
		tabbedPane.addTab("\u8fd0\u7b97\u7ed3\u679c", null, resultPanel, null);

		outputPicturePanel.addMouseListener(this);
		outputPicturePanel.addMouseMotionListener(this);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// System.out.println("exit");
		this.setCursor(Cursor.getDefaultCursor());
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		this.setCursor(Cursor.getDefaultCursor());
	}

	boolean dragFlag = false;

	public void mouseDragged(MouseEvent e) {
		dragFlag = true;
		if (flag) {

			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			Point po = e.getPoint();
			Dimension di = outputPicturePanel.getPreferredSize();

			if (po.y < 5 || this.getHeight() < po.y) {

			} else {
				outputPicturePanel.setPreferredSize(new Dimension(di.width,
						(outputPicturePanel.getY() + po.y)));
				// System.out.println(po.y);
				// System.out.println(mainFrame.getY());
				// System.out.println(this.getHeight() + "|" + po.y);
			}

			outputPicturePanel.revalidate();
			outputPicturePanel.repaint();
		}

	}

	boolean flag = false;

	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();
		this.setCursor(Cursor.getDefaultCursor());
		flag = false;
		dragFlag = false;
		if (((outputPicturePanel.getY() + outputPicturePanel.getHeight() - point.y) < 15)
				&& ((outputPicturePanel.getY() + outputPicturePanel.getHeight() - point.y) > -15)) {
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			flag = true;
		} else {
			this.setCursor(Cursor.getDefaultCursor());
		}
	}

	public AccuracyController getAccuracyController() {
		return accuracyController;
	}

	public void setAccuracyController(AccuracyController accuracyController) {
		this.accuracyController = accuracyController;
	}

	public GeneController getGeneController() {
		return geneController;
	}

	public void setGeneController(GeneController geneController) {
		this.geneController = geneController;
	}

	public OperatorController getOperatorController() {
		return operatorController;
	}

	public void setOperatorController(OperatorController operatorController) {
		this.operatorController = operatorController;
	}

	public OutputPictureController getOutputPictureController() {
		return outputPictureController;
	}

	public void setOutputPictureController(
			OutputPictureController outputPictureController) {
		this.outputPictureController = outputPictureController;
	}

}
