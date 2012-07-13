package ui.alginputdataprocess.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class JPanelForFooter extends JPanel {
	
	public JButton btnNext = new JButton("下一步 >");
    public JButton btnBefore = new JButton("< 上一步");
    public JButton btnRun = new JButton("\u6267\u884C\u7B97\u6CD5");
    
	public JPanelForFooter() {
		
	      setBorder(new LineBorder(new Color(0, 0, 0)));
	      setBackground(Color.WHITE);
	      setBounds(160, 523, 669, 39);
	      setLayout(null);
	      	      
	      btnNext.setBounds(523, 10, 93, 23);
	      add(btnNext);
	      
	      
	      btnBefore.setEnabled(false);
	      
	      btnBefore.setBounds(105, 10, 93, 23);
	      add(btnBefore);
	      
	      
	      btnRun.setBounds(312, 10, 93, 23);
	      add(btnRun);
	}

}
