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


public class GeneView extends JPanel {

	
	
	
	public JTextField txtNormalGeneNumber=new JTextField(),
	                   txtNormalHeaderLength=new JTextField(),
                       txtHomeoticGeneNums=new JTextField(),
	                   txtHomeoticHeaderLength=new JTextField(),
                       txtGeneRecombineRate=new JTextField(),
	                   txtRisTransportRate=new JTextField(),
	                   txtofRisElement=new JTextField(),
	                   txtIsTransportRate=new JTextField(),
	                   txtofIsElement=new JTextField(),
	                   txtGeneTransportRate=new JTextField(),
	                   txtMutateRate=new JTextField(),
	                   txtTwoPointRecombineRate=new JTextField(),
	                   txtGeneOnePointRecombineRate=new JTextField();
	
	public JComboBox JComboBoxOfAvailableModifyings = new JComboBox();
	MainFrame parent;
	public GeneView(MainFrame parent) {
		this.parent = parent;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(155, 115, 631, 447);
		setLayout(null);
		setVisible(false);
		JLabel lblNewLabel_6 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u57FA\u56E0\u4FE1\u606F");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(20, 20, 167, 25);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u57FA\u56E0\u4E2A\u6570");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(20, 60, 100, 25);
		add(lblNewLabel_7);
		
		
		txtNormalGeneNumber.setBounds(160, 60, 120, 25);
		add(txtNormalGeneNumber);
		txtNormalGeneNumber.setColumns(10);
		txtNormalGeneNumber.grabFocus();
		
		txtNormalGeneNumber.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtNormalHeaderLength.grabFocus();
		        }  
		    }  
		}); 
		JLabel lblNewLabel_8 = new JLabel("\u57FA\u56E0\u5934\u90E8\u5927\u5C0F");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(340, 60, 100, 25);
		add(lblNewLabel_8);
		
		
		txtNormalHeaderLength.setBounds(485, 62, 120, 25);
		add(txtNormalHeaderLength);
		txtNormalHeaderLength.setColumns(10);
		txtNormalHeaderLength.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtHomeoticGeneNums.grabFocus();
		        }  
		    }  
		});
		JLabel lblNewLabel_9 = new JLabel("\u540C\u6E90\u67D3\u8272\u4F53\u4E2A\u6570");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(20, 100, 120, 25);
		add(lblNewLabel_9);
		
		
		txtHomeoticGeneNums.setBounds(160, 100, 120, 25);
		add(txtHomeoticGeneNums);
		txtHomeoticGeneNums.setColumns(10);
		txtHomeoticGeneNums.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtHomeoticHeaderLength.grabFocus();
		        }  
		    }  
		}); 
		JLabel lblNewLabel_10 = new JLabel("\u540C\u6E90\u67D3\u8272\u4F53\u5934\u90E8\u5927\u5C0F");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(340, 100, 142, 25);
		add(lblNewLabel_10);
		
		
		txtHomeoticHeaderLength.setBounds(485, 100, 120, 25);
		add(txtHomeoticHeaderLength);
		txtHomeoticHeaderLength.setColumns(10);
		txtHomeoticHeaderLength.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtGeneRecombineRate.grabFocus();
		        }  
		    }  
		});
		JLabel lblNewLabel_11 = new JLabel("\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(20, 140, 100, 25);
		add(lblNewLabel_11);
		
		
		txtGeneRecombineRate.setBounds(160, 140, 120, 25);
		add(txtGeneRecombineRate);
		txtGeneRecombineRate.setColumns(10);
		txtGeneRecombineRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtGeneOnePointRecombineRate.grabFocus();
		        }  
		    }  
		});
		JLabel lblNewLabel_12 = new JLabel("\u57FA\u56E0\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(340, 140, 142, 25);
	    add(lblNewLabel_12);
		
		txtGeneOnePointRecombineRate.setBounds(485, 140, 120, 25);
		add(txtGeneOnePointRecombineRate);
		txtGeneOnePointRecombineRate.setColumns(10);
		txtGeneOnePointRecombineRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtTwoPointRecombineRate.grabFocus();
		        }  
		    }  
		});  
		JLabel lblNewLabel_13 = new JLabel("\u57FA\u56E0\u591A\u70B9\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(20, 180, 131, 25);
		add(lblNewLabel_13);
		
		
		txtTwoPointRecombineRate.setBounds(160, 180, 120, 25);
		add(txtTwoPointRecombineRate);
		txtTwoPointRecombineRate.setColumns(10);
		txtTwoPointRecombineRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtMutateRate.grabFocus();
		        }  
		    }  
		}); 
		
		JLabel lblNewLabel_30 = new JLabel("\u57FA\u56E0\u7A81\u53D8\u6982\u7387");
		lblNewLabel_30.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_30.setBounds(340, 180, 100, 25);
		add(lblNewLabel_30);
		
		
		txtMutateRate.setBounds(485, 180, 120, 25);
		add(txtMutateRate);
		txtMutateRate.setColumns(10);
		txtMutateRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtGeneTransportRate.grabFocus();
		        }  
		    }  
		}); 
		
		JLabel lblNewLabel_31 = new JLabel("\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
		lblNewLabel_31.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_31.setBounds(20, 220, 100, 25);
		add(lblNewLabel_31);
		
	    txtGeneTransportRate.setBounds(160, 220, 120, 25);
		add(txtGeneTransportRate);
		txtGeneTransportRate.setColumns(10);
		txtGeneTransportRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtofIsElement.grabFocus();
		        }  
		    }  
		}); 
		
		JComboBoxOfAvailableModifyings.setBounds(485, 220, 120, 25);
		add(JComboBoxOfAvailableModifyings);
		
		JLabel lblNewLabel_32 = new JLabel("\u57FA\u56E0\u9009\u62E9\u53D8\u5F02\u7B56\u7565");
		lblNewLabel_32.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_32.setBounds(340, 220, 120, 25);
		add(lblNewLabel_32);
		
		JLabel lblNewLabel_14 = new JLabel("IS\u957F\u5EA6");
		lblNewLabel_14.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_14.setBounds(20, 260, 100, 25);
		add(lblNewLabel_14);
		
		
		txtofIsElement.setBounds(160, 260, 120, 25);
		add(txtofIsElement);
		txtofIsElement.setColumns(10);
		txtofIsElement.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtIsTransportRate.grabFocus();
		        }  
		    }  
		});  
		JLabel lblNewLabel_15 = new JLabel("IS\u8F6C\u5EA7\u7387");
		lblNewLabel_15.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_15.setBounds(340, 260, 100, 25);
		add(lblNewLabel_15);
		
		
		txtIsTransportRate.setBounds(485, 260, 120, 25);
		add(txtIsTransportRate);
		txtIsTransportRate.setColumns(10);
		txtIsTransportRate.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtofRisElement.grabFocus();
		        }  
		    }  
		});  
		
		txtofRisElement.setBounds(160, 300, 120, 25);
		add(txtofRisElement);
		txtofRisElement.setColumns(10);
		txtofRisElement.addKeyListener(new KeyAdapter(){
		    public void keyPressed(KeyEvent e) {  
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
		        	txtRisTransportRate.grabFocus();
		        }  
		    }  
		});
		JLabel lblNewLabel_16 = new JLabel("RIS\u957F\u5EA6");
		lblNewLabel_16.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_16.setBounds(20, 300, 100, 25);
		add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("RIS\u8F6C\u5EA7\u7387");
		lblNewLabel_17.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_17.setBounds(340, 300, 100, 25);
		add(lblNewLabel_17);
		
		
		txtRisTransportRate.setBounds(485, 300, 120, 25);
		add(txtRisTransportRate);
		txtRisTransportRate.setColumns(10);
        /*btnForward3.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e) {
				TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node2));
				tree_1.setSelectionPath(visiblePath);
				HostPane.setVisible(false);
        		panel_1.setVisible(false);
        		panel_2.setVisible(true);
        		panel_3.setVisible(false);
        		panel_4.setVisible(false);
				
			}
			});*/
		//panel_0.add(panel_3);
	}

}
