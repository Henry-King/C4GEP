package ui.conf;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class ConfPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5609826898115031426L;

	/**
	 * Create the panel.
	 */
	public ConfPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		AccuracyPanel accurayPanel = new AccuracyPanel();
		tabbedPane.addTab("\u6C42\u89E3\u7CBE\u5EA6", null, accurayPanel, 
				"\u5728\u8FD9\u91CC\u8BBE\u7F6E\u6C42\u89E3\u7CBE\u5EA6\u548C\u505C\u673A\u6761\u4EF6");
		
		GenePanel genePanel = new GenePanel();
		tabbedPane.addTab("\u4E2A\u4F53/\u57FA\u56E0\u53C2\u6570", null, genePanel, null);
		
		OperatorPanel operatorPanel = new OperatorPanel();
		tabbedPane.addTab("\u6F14\u5316\u53C2\u6570", null, operatorPanel, null);
		
		InputPanel inputPanel = new InputPanel();
		tabbedPane.addTab("\u7B97\u6CD5\u8F93\u5165", null, inputPanel, 
				"\u5728\u8FD9\u91CC\u8BBE\u7F6E\u7B97\u6CD5\u7684\u8F93\u5165\u4FE1\u606F");

	}

}
