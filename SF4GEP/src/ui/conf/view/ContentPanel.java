package ui.conf.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	
	private JTabbedPane tabbedPane;

	private AccuracyPanel accuracyPanel;
	private GenePanel genePanel;
	private OperatorPanel operatorPanel;
	private JPanel resultPanel;
	private MainWnd mainWnd;
	private ConfPanel confPanel;
	
	public OutputPicturePanel outputPicturePanel;
	public AccuracyController accuracyController;
	public GeneController geneController;
	public OperatorController operatorController;
	public OutputPictureController outputPictureController;
	
	
	private int SettingsTabIndex = 0;
	private double dragBarYPosition = 0;
	/**
	 * Create the panel.
	 */
	public ContentPanel(MainWnd mainWnd,ConfPanel confPanel) {
		this.mainWnd = mainWnd;
		this.confPanel = confPanel;
		this.setLayout(new BorderLayout());
		/* »­Í¼Ãæ°å */
		outputPicturePanel = new OutputPicturePanel(mainWnd,confPanel);
		Dimension di = outputPicturePanel.getPreferredSize();
		outputPicturePanel.setPreferredSize(new Dimension());
		
		OutputPictureModel outputPictureModel = new OutputPictureModel();
		outputPicturePanel.setPreferredSize(new Dimension(di.width,40));
		outputPictureController = new OutputPictureController(outputPictureModel, outputPicturePanel);
		add("North", outputPicturePanel);

		
		
		/*
		JPanel FittingCurvePanel = new JPanel();
		OutputPicturePanel.add(FittingCurvePanel);

		JPanel EvolutionGraphPanel = new JPanel();
		OutputPicturePanel.add(EvolutionGraphPanel);
		*/
		
		
		
		
		
		
		/* TabbedPanel */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	
            	if (SettingsTabIndex!=tabbedPane.getSelectedIndex()){
            		
            		Dimension di = outputPicturePanel.getPreferredSize();
            		//System.out.println(dragBarYPosition+"|"+ContentPanel.this.getHeight()/2);
            		if (dragBarYPosition<ContentPanel.this.getHeight()/2) {
            			outputPicturePanel.setPreferredSize(new Dimension(di.width,162));
					}else{
	            		outputPicturePanel.setPreferredSize(new Dimension(di.width,ContentPanel.this.getHeight()-200));
					}
            		
            		
                }
            	SettingsTabIndex=tabbedPane.getSelectedIndex();
            	
            	
            	
            	
            }
        });

		
		
		
		
		
		
		
		
		accuracyPanel = new AccuracyPanel();
		AccuracyModel accuracyModel = new AccuracyModel();
		accuracyController = new AccuracyController(accuracyModel,accuracyPanel);
		
		//JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, accuracyPanel, accuracyPanel);
		
        //splitPane.setOneTouchExpandable(true);
        //splitPane.setDividerLocation(320);
		
		JScrollPane accuracyScrollPane = new JScrollPane(accuracyPanel);
		accuracyScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		tabbedPane
				.addTab("Accuracy Setting",
						null,
						accuracyScrollPane,
						null);

		
		
		
		
		genePanel = new GenePanel(mainWnd);
		GeneModel geneModel = new GeneModel();
		geneController = new GeneController(geneModel,genePanel);
		
		
		JScrollPane geneScrollPane = new JScrollPane(genePanel);
		//geneScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		tabbedPane.addTab("Individual&Gene Para", null,
				geneScrollPane, null);
		
		
		
		

		operatorPanel = new OperatorPanel();
		OperatorModel operatorModel = new OperatorModel();
		operatorController = new OperatorController(operatorModel,operatorPanel);
		
		
		JScrollPane operatorScrollPane = new JScrollPane(operatorPanel);
		operatorScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		tabbedPane
				.addTab("Evolution Para", null, operatorScrollPane, null);


		add(tabbedPane);
		
		/*resultPanel = new ResultPanel();
		tabbedPane.addTab("\u8fd0\u7b97\u7ed3\u679c", null, resultPanel, null);*/

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
			//System.out.println(dragBarYPosition+"|"+e.getPoint().getY());
			dragBarYPosition = e.getPoint().getY();
			if (po.y < 43 || this.getHeight()-25 < po.y) {

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
