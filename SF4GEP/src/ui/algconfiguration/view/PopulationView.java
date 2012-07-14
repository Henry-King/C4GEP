package ui.algconfiguration.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ui.alginputdataprocess.view.MainFrame;


public class PopulationView extends JPanel {


	
    public JTextField 
	txtPopulationSize = new JTextField();
    public JTextField txtSelectionRange = new JTextField();
	
	MainFrame parent;
	public PopulationView(MainFrame parent) {
		this.parent = parent;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(155, 115, 631, 447);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("\u586B\u5199\u6240\u9700\u7684\u79CD\u7FA4\u4FE1\u606F");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 20, 189, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u79CD\u7FA4\u521D\u59CB\u5316\u5927\u5C0F");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 60, 120, 25);
		add(lblNewLabel_1);
		
		
		txtPopulationSize.setBounds(160, 60, 120, 25);
		add(txtPopulationSize);
		txtPopulationSize.setColumns(10);
		txtPopulationSize.grabFocus();
		
		JLabel  lblNewLabel_5= new JLabel("\u9009\u62E9\u4E2A\u4F53\u7684\u8303\u56F4");
		lblNewLabel_5.setFont(new Font("ËÎÌו", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(336, 60, 120, 25);
		add(lblNewLabel_5);
		
		
		txtSelectionRange.setBounds(480, 60, 120, 25);
		add(txtSelectionRange);
		txtSelectionRange.setColumns(10);
		/*btnNext2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node3));
				tree_1.setSelectionPath(visiblePath);
				
				

				HostPane.setVisible(false);
        		panel_1.setVisible(false);
        		panel_2.setVisible(true);
        		panel_3.setVisible(false);
        		panel_4.setVisible(false);
				
			}
			
		});*/
	}

}
