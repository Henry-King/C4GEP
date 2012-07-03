package ui.input;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;







public class New extends JFrame {
	
	private JPanel panel_0; 
	private JPanel contentPane;
	private JPanel HostPane;
	private JComboBox jcomboBox;
    private JButton btnNewButton;
    private JButton btnSetConfig;
    private JButton btnChangeConfig;
	private JPanel panel_1;
	private JTextField txtInputPath;
	private JButton btnRun1;
	private JPanel panel_2;
	private JTextField txtPopulationSize;
	private JTextField txtMaxGeneration;
	private JTextField txtSelectionRange;
	private JButton btnRun2;
	private JPanel panel_3 ;
	private JTextField txtNormalGeneNumber;
	private JTextField txtNormalHeaderLength;
	private JTextField txtHomeoticGeneNums;
	private JTextField txtHomeoticHeaderLength;
	private JTextField txtGeneRecombineRate;
	private JTextField txtGeneOnePointRecombineRate;
	private JTextField txtTwoPointRecombineRate;
	private JTextField txtofIsElement;
	private JTextField txtIsTransportRate;
	private JTextField txtofRisElement;
	private JTextField txtRisTransportRate;
	private JTextField txtMutateRate;
	private JTextField txtGeneTransportRange;
	private JButton btnRun3;
	private JPanel panel_4;
	private JTextField txtConstantListSize;
	private JTextField txtRandomConstantStart;
	private JTextField txtRandomConstantEnd;
	private JTextField txtAccuracy;
	private JButton btnRun4;
	int count=2;//判断是否是第一次点击下拉框
	int flag=1;//
	int flagForRunBtn=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New frame = new New();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public New() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 686);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
		panel_0 = new JPanel();
		panel_0.setBackground(Color.WHITE);
		panel_0.setBounds(0, 0, 791, 648);
		panel_0.setLayout(null);
		contentPane.add(panel_0);
		
		
		
		
		//树
		  DefaultMutableTreeNode root = new DefaultMutableTreeNode("菜单");
		  final DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("算法名称");
		  final DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("数据输入路径");
		  final DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("种群信息");
		  final DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("基因信息");
		  final DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("所需函数和随机数");
		  DefaultTreeModel treeModel = new DefaultTreeModel(root);
		  treeModel.insertNodeInto(node0, root, root.getChildCount());
		  treeModel.insertNodeInto(node1, root, root.getChildCount());
		  treeModel.insertNodeInto(node2, root, root.getChildCount());
		  treeModel.insertNodeInto(node3, root, root.getChildCount());
		  treeModel.insertNodeInto(node4, root, root.getChildCount());
		  JRadioButton ra=new JRadioButton("+");
		
		

		
		
		
		// this line needs to be implemented in order to make JWS work properly
		  UIManager.getLookAndFeelDefaults().put("ClassLoader",
				getClass().getClassLoader());
		  final JTree tree_1 = new JTree(treeModel);
		  tree_1.setRowHeight(20);
		  DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree_1.getCellRenderer();
		  cellRenderer.setLeafIcon(new ImageIcon("//leaf.jpg"));
		  cellRenderer.setOpenIcon(new ImageIcon("//leaf.jpg"));
		  cellRenderer.setClosedIcon(new ImageIcon("//leaf.jpg"));

		  cellRenderer.setFont(new Font("宋体", Font.PLAIN, 15));// 设置字体.
		  cellRenderer.setBackgroundNonSelectionColor(Color.white);
		  cellRenderer.setBorderSelectionColor(Color.red);
		  cellRenderer.setTextNonSelectionColor(Color.black);
		  cellRenderer.setTextSelectionColor(Color.WHITE);
		  cellRenderer.setBorderSelectionColor(Color.blue);
		
		
		/*
		 * 设置选时或不选时，文字的变化颜色
		 */
		  cellRenderer.setTextNonSelectionColor(Color.black);
		  cellRenderer.setTextSelectionColor(Color.WHITE);
		  cellRenderer.setBorderSelectionColor(Color.blue);
		  tree_1.addMouseListener(new MouseAdapter(){
							public void mousePressed(MouseEvent e) {
						        JTree tree = (JTree) e.getSource();
						         int selRow = tree.getRowForLocation(e.getX(), e.getY());
						        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
						        if (selRow != -1)
						        {
						            if (e.getClickCount() == 1)
						            {
						            	TreeNode node = (TreeNode) selPath.getLastPathComponent();
						            	if(node.toString()=="算法名称"){
						            		HostPane.setVisible(true);
						            		panel_1.setVisible(false);
						            		panel_2.setVisible(false);
						            		panel_3.setVisible(false);
						            		panel_4.setVisible(false);
						            	}
						            	if(node.toString()=="数据输入路径"){
						            		HostPane.setVisible(false);
						            		panel_1.setVisible(true);
						            		panel_2.setVisible(false);
						            		panel_3.setVisible(false);
						            		panel_4.setVisible(false);
						            	}
						            	if(node.toString()=="种群信息"){
						            		HostPane.setVisible(false);
						            		panel_1.setVisible(false);
						            		panel_2.setVisible(true);
						            		panel_3.setVisible(false);
						            		panel_4.setVisible(false);
						            	}
						            	if(node.toString()=="基因信息"){
						            		txtNormalGeneNumber.grabFocus();
						            		HostPane.setVisible(false);
						            		panel_1.setVisible(false);
						            		panel_2.setVisible(false);
						            		panel_3.setVisible(true);
						            		panel_4.setVisible(false);
						            	}
						            	if(node.toString()=="所需函数和随机数"){
						            		HostPane.setVisible(false);
						            		panel_1.setVisible(false);
						            		panel_2.setVisible(false);
						            		panel_3.setVisible(false);
						            		panel_4.setVisible(true);
						            	}
						                //单击
						            } else if (e.getClickCount() == 2)
						            {
						            	
						            }
						        }
						}
						});
						tree_1.setRowHeight(20);
						JScrollPane scrollPane = new JScrollPane(tree_1);
						scrollPane.setBounds(0, 115, 150, 447);
						panel_0.add(scrollPane);
						
						
						//主页------------------------------------------------------------
						
						String[] fontsize={"12"};
						HostPane = new JPanel();
						HostPane.setBounds(155, 115, 631, 447);
						HostPane.setBackground(Color.WHITE);
						HostPane.setLayout(null);
						HostPane.setVisible(true);
						JLabel lblNewLabel_28 = new JLabel("\u7B97\u6CD5\u540D\u79F0");
						lblNewLabel_28.setFont(new Font("宋体", Font.PLAIN, 18));
						lblNewLabel_28.setBounds(20, 58, 83, 33);
						HostPane.add(lblNewLabel_28);
						//String defaultMessage="请选择或直接输入文字大小!输入完请按回车结束";
						HostPane.setBorder(new LineBorder(new Color(0, 0, 0)));
						jcomboBox=new JComboBox(fontsize);
						jcomboBox.setSelectedIndex(-1);
						jcomboBox.setFont(new Font("宋体", Font.PLAIN, 14));
						jcomboBox.setEditable(true);
						jcomboBox.setBackground(Color.WHITE);
						jcomboBox.setBounds(140, 60, 362, 33);
						final ComboBoxEditor editor=jcomboBox.getEditor();
					    jcomboBox.configureEditor(editor,null);
						HostPane.add(jcomboBox);
						panel_0.add(HostPane);
					    jcomboBox.setVisible(true);
					   
						
						
					  Component scrollBtn=jcomboBox.getComponent(0);
				       scrollBtn.addMouseListener(new MouseAdapter() {
				        public void mouseClicked(MouseEvent arg0) {
				        	count=1;
							
				       }

				      });
				       
				       
				        
						jcomboBox.addItemListener(new   ItemListener(){ 
				                public   void   itemStateChanged(ItemEvent   ie)   { 
				                	 if (ie.getStateChange()==ItemEvent.SELECTED){
				                		 if(count!=2){
				                		   btnNewButton.setVisible(true);
				                     	   btnChangeConfig.setVisible(true);
				                     	   btnSetConfig.setVisible(false);
				                     	   showRunButton();
				                     	   panel_1.setVisible(true);
				                     	   HostPane.setVisible(false);
				                		 }
				                		
				                	 }
				                	
				                	}
				                }
						);
				          
						//jcomboBox.grabFocus();
						
						jcomboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){  
						    @Override  
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){  
						        	btnSetConfig.setVisible(true);
						        	btnNewButton.setVisible(false);
						        	btnChangeConfig.setVisible(false);
						        	count=2;
						        	fadeRunButton();
						        }  
						    }  
						}); 
						

						
						
						
					    btnSetConfig = new JButton("\u914D\u7F6E\u53C2\u6570");
						btnSetConfig.setBounds(515, 67, 93, 23);
						btnSetConfig.setVisible(true);
						btnSetConfig.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								HostPane.setVisible(false);
								panel_0.setVisible(true);
								panel_1.setVisible(true);
								
							}
							
						});
						HostPane.add(btnSetConfig);
						
					    btnNewButton = new JButton("\u6267\u884C\u7B97\u6CD5");
						btnNewButton.setBounds(193, 120, 93, 23);
						btnNewButton.setVisible(false);
						HostPane.add(btnNewButton);
						
					    btnChangeConfig = new JButton("\u66F4\u6539\u914D\u7F6E");
					    btnChangeConfig.addActionListener(new ActionListener() {
					    	public void actionPerformed(ActionEvent e) {
					    		HostPane.setVisible(false);
					    		panel_1.setVisible(true);
					    	}
					    });
						btnChangeConfig.setBounds(398, 120, 93, 23);
						btnChangeConfig.setVisible(false);
						HostPane.add(btnChangeConfig);
						//contentPane.add(HostPane);
						
						JLabel lblNewLabel_27 = new JLabel("\u8F93\u5165\u7B97\u6CD5\u540D\u79F0/\u9009\u62E9\u7B97\u6CD5");
						lblNewLabel_27.setFont(new Font("宋体", Font.PLAIN, 18));
						lblNewLabel_27.setBounds(20, 20, 305, 25);
						HostPane.add(lblNewLabel_27);
						JButton btnNext0 = new JButton("\u4E0B\u4E00\u6B65");
						btnNext0.setBounds(528, 414, 93, 23);
						HostPane.add(btnNext0);
						
						btnNext0.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
								tree_1.setSelectionPath(visiblePath);
								HostPane.setVisible(false);
				        		panel_1.setVisible(true);
				        		panel_2.setVisible(false);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(false);
								
							}
							
						}); 
						
						 //文件输入路径----------------------------------------------------
					    panel_1 = new JPanel();
					    panel_1.setBounds(155, 115, 631, 447);
					    panel_0.add(panel_1);
					    panel_1.setVisible(false);
					    panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
					    panel_1.setBackground(Color.WHITE);
					    panel_1.setLayout(null);
					    
					    JLabel lblNewLabel_25 = new JLabel("\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
					    lblNewLabel_25.setBackground(SystemColor.inactiveCaptionBorder);
					    lblNewLabel_25.setFont(new Font("宋体", Font.PLAIN, 17));
					    lblNewLabel_25.setBounds(20, 60, 120, 33);
					    panel_1.add(lblNewLabel_25);
					    
					    txtInputPath = new JTextField();
					    txtInputPath.setBounds(160, 60, 362, 33);
					    panel_1.add(txtInputPath);
					    txtInputPath.setColumns(10);
					    
					    JButton btnInputBrowse = new JButton("\u6D4F\u89C8");
					    btnInputBrowse.addActionListener(new OpenHandler());
					    btnInputBrowse.setBounds(532, 60, 93, 23);
					    panel_1.add(btnInputBrowse);
					    
					    JButton btnNext1 = new JButton("\u4E0B\u4E00\u6B65");
					    btnNext1.setBounds(528, 414, 93, 23);
					    btnNext1.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node2));
								tree_1.setSelectionPath(visiblePath);
								HostPane.setVisible(false);
				        		panel_1.setVisible(false);
				        		panel_2.setVisible(true);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(false);
								
							}
							
						});
					    panel_1.add(btnNext1);
					    
					    JLabel lblNewLabel_26 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
					    lblNewLabel_26.setFont(new Font("宋体", Font.PLAIN, 18));
					    lblNewLabel_26.setBounds(20, 20, 208, 25);
					    panel_1.add(lblNewLabel_26);
					    
					    JPanel panel = new JPanel();
					    panel.setBounds(0, 561, 813, 87);
					    panel.setLayout(null);
					    
					    JLabel lblNewLabel_38 = new JLabel("-----------1.0\u7248\u672C\uFF08c\uFF09Miscrosoft Cooperation------------");
					    lblNewLabel_38.setBounds(162, 35, 463, 28);
					    lblNewLabel_38.setFont(new Font("宋体", Font.PLAIN, 13));
					    lblNewLabel_38.setForeground(Color.RED);
					    panel.add(lblNewLabel_38);
					    JLabel lblNewLabel_40 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u7B97\u6CD5\u7EC8\u6B62\u6761\u4EF6");
					    lblNewLabel_40.setFont(new Font("宋体", Font.PLAIN, 18));
					    lblNewLabel_40.setBounds(20, 130, 208, 25);
					    panel_1.add(lblNewLabel_40);
					    
					    JLabel lblNewLabel_41 = new JLabel("\u6F14\u5316\u4EE3\u6570");
					    lblNewLabel_41.setFont(new Font("宋体", Font.PLAIN, 17));
					    lblNewLabel_41.setBounds(20, 170, 120, 25);
					    panel_1.add(lblNewLabel_41);
					    
					    txtMaxGeneration = new JTextField();
					    txtMaxGeneration.setBounds(160, 170, 160, 25);
					    panel_1.add(txtMaxGeneration);
					    txtMaxGeneration.setColumns(10);
					    
					    JLabel lblNewLabel_42 = new JLabel("\u7B97\u6CD5\u7ED3\u679C\u7684\u7CBE\u5EA6");
					    lblNewLabel_42.setFont(new Font("宋体", Font.PLAIN, 17));
					    lblNewLabel_42.setBounds(20, 220, 120, 25);
					    panel_1.add(lblNewLabel_42);
					    
					    txtAccuracy = new JTextField();
					    txtAccuracy.setBounds(160, 220, 160, 25);
					    panel_1.add(txtAccuracy);
					    txtAccuracy.setColumns(10);
					    
					    JButton btnForward1 = new JButton("\u4E0A\u4E00\u6B65");
					    btnForward1.addActionListener(new ActionListener() {
					    	public void actionPerformed(ActionEvent e) {
					    		    TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node0));
								    tree_1.setSelectionPath(visiblePath);
					    		    HostPane.setVisible(true);
						            panel_1.setVisible(false);
						            panel_2.setVisible(false);
						            panel_3.setVisible(false);
						            panel_4.setVisible(false);
										
									
									
					    	}
					    });
					    btnForward1.setBounds(311, 414, 93, 23);
					    panel_1.add(btnForward1);
					    
					    btnRun1 = new JButton("\u8FD0\u884C");
					    btnRun1.setVisible(false);//设置flag
					    btnRun1.addActionListener(new ActionListener() {
					    	public void actionPerformed(ActionEvent e) {
					    		//添加运行事件
					    	}
					    });
					    btnRun1.setBounds(420, 414, 93, 23);
					    panel_1.add(btnRun1);  
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						//种群的panel-------------------------------------------------------------------
						panel_2 = new JPanel();
						panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_2.setBounds(155, 115, 631, 447);
						panel_0.add(panel_2);
						panel_2.setForeground(Color.DARK_GRAY);
						panel_2.setBackground(Color.WHITE);
						panel_2.setLayout(null);
						panel_2.setVisible(false);
						
						JLabel lblNewLabel = new JLabel("\u586B\u5199\u6240\u9700\u7684\u79CD\u7FA4\u4FE1\u606F");
						lblNewLabel.setForeground(Color.BLACK);
						lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
						lblNewLabel.setBounds(20, 20, 189, 25);
						panel_2.add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel("\u79CD\u7FA4\u521D\u59CB\u5316\u5927\u5C0F");
						lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_1.setBounds(20, 60, 120, 25);
						panel_2.add(lblNewLabel_1);
						
						txtPopulationSize = new JTextField();
						txtPopulationSize.setBounds(160, 60, 120, 25);
						panel_2.add(txtPopulationSize);
						txtPopulationSize.setColumns(10);
						txtPopulationSize.grabFocus();
						txtPopulationSize.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	//txtMaxGeneration.grabFocus();
						        }  
						    }  
						});
						
						JLabel lblNewLabel_3 = new JLabel("\u79CD\u7FA4\u521D\u59CB\u5316\u65B9\u5F0F");
						lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_3.setBounds(340, 60, 134, 25);
						panel_2.add(lblNewLabel_3);
						
						JComboBox JcomboBoxOfPopulationCreator = new JComboBox();
						JcomboBoxOfPopulationCreator.setBounds(485, 60, 120, 21);
						panel_2.add(JcomboBoxOfPopulationCreator);
						
						JLabel  lblNewLabel_5= new JLabel("\u9009\u62E9\u4E2A\u4F53\u7684\u8303\u56F4");
						lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_5.setBounds(340, 100, 120, 25);
						panel_2.add(lblNewLabel_5);
						
						txtSelectionRange = new JTextField();
						txtSelectionRange.setBounds(485, 100, 120, 25);
						panel_2.add(txtSelectionRange);
						txtSelectionRange.setColumns(10);
						
						JLabel lblNewLabel_4 = new JLabel("\u9009\u62E9\u4E2A\u4F53\u7684\u7B56\u7565");
						lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_4.setBounds(20, 100, 120, 25);
						panel_2.add(lblNewLabel_4);
						
						JComboBox JComboBoxofSelectionStrategy = new JComboBox();
						JComboBoxofSelectionStrategy.setBounds(160, 100, 120, 21);
						panel_2.add(JComboBoxofSelectionStrategy);
						
						JLabel lblNewLabel_33 = new JLabel("\u9002\u5E94\u503C\u8BA1\u7B97\u65B9\u5F0F");
						lblNewLabel_33.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_33.setBounds(20, 140, 120, 25);
						panel_2.add(lblNewLabel_33);
						
						JComboBox JComboBoxAvailableCalculator = new JComboBox();
						JComboBoxAvailableCalculator.setBounds(160, 140, 120, 21);
						panel_2.add(JComboBoxAvailableCalculator);
						
						
						
						
						
						JButton btnNext2 = new JButton("\u4E0B\u4E00\u6B65");
						btnNext2.setBounds(528, 414, 93, 23);
						btnNext2.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								HostPane.setVisible(false);
				        		panel_1.setVisible(false);
				        		panel_2.setVisible(false);
				        		panel_3.setVisible(true);
				        		panel_4.setVisible(false);
								
							}
						
						});
						panel_2.add(btnNext2);
						
						JButton btnForward2 = new JButton("\u4E0A\u4E00\u6B65");
						btnForward2.setBounds(325, 414, 93, 23);
						btnForward2.addActionListener(new ActionListener(){

							
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node1));
								tree_1.setSelectionPath(visiblePath);
								HostPane.setVisible(false);
				        		panel_1.setVisible(true);
				        		panel_2.setVisible(false);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(false);
								
							}
							});
						panel_2.add(btnForward2);
						
					    btnRun2 = new JButton("\u8FD0\u884C");
						btnRun2.setBounds(425, 414, 93, 23);
						btnRun2.setVisible(false);
						panel_2.add(btnRun2);
						btnNext2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node3));
								tree_1.setSelectionPath(visiblePath);
								
								

								HostPane.setVisible(false);
				        		panel_1.setVisible(false);
				        		panel_2.setVisible(true);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(false);
								
							}
							
						});
				
						
						
						//基因的----------------------------------------------------------
						panel_3 = new JPanel();
						panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_3.setBackground(Color.WHITE);
						panel_3.setBounds(155, 115, 631, 447);
						panel_3.setLayout(null);
						panel_3.setVisible(false);
						JLabel lblNewLabel_6 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u57FA\u56E0\u4FE1\u606F");
						lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 18));
						lblNewLabel_6.setBounds(20, 20, 167, 25);
						panel_3.add(lblNewLabel_6);
						
						JLabel lblNewLabel_7 = new JLabel("\u57FA\u56E0\u4E2A\u6570");
						lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_7.setBounds(20, 60, 100, 25);
						panel_3.add(lblNewLabel_7);
						
						txtNormalGeneNumber = new JTextField();
						txtNormalGeneNumber.setBounds(160, 60, 120, 25);
						panel_3.add(txtNormalGeneNumber);
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
						panel_3.add(lblNewLabel_8);
						
						txtNormalHeaderLength = new JTextField();
						txtNormalHeaderLength.setBounds(485, 62, 120, 25);
						panel_3.add(txtNormalHeaderLength);
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
						panel_3.add(lblNewLabel_9);
						
						txtHomeoticGeneNums = new JTextField();
						txtHomeoticGeneNums.setBounds(160, 100, 120, 25);
						panel_3.add(txtHomeoticGeneNums);
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
						panel_3.add(lblNewLabel_10);
						
						txtHomeoticHeaderLength = new JTextField();
						txtHomeoticHeaderLength.setBounds(485, 100, 120, 25);
						panel_3.add(txtHomeoticHeaderLength);
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
						panel_3.add(lblNewLabel_11);
						
						txtGeneRecombineRate = new JTextField();
						txtGeneRecombineRate.setBounds(160, 140, 120, 25);
						panel_3.add(txtGeneRecombineRate);
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
						panel_3.add(lblNewLabel_12);
						
						txtGeneOnePointRecombineRate = new JTextField();
						txtGeneOnePointRecombineRate.setBounds(485, 140, 120, 25);
						panel_3.add(txtGeneOnePointRecombineRate);
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
						panel_3.add(lblNewLabel_13);
						
						txtTwoPointRecombineRate = new JTextField();
						txtTwoPointRecombineRate.setBounds(160, 180, 120, 25);
						panel_3.add(txtTwoPointRecombineRate);
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
						panel_3.add(lblNewLabel_30);
						
						txtMutateRate = new JTextField();
						txtMutateRate.setBounds(485, 180, 120, 25);
						panel_3.add(txtMutateRate);
						txtMutateRate.setColumns(10);
						txtMutateRate.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	txtGeneTransportRange.grabFocus();
						        }  
						    }  
						}); 
						
						JLabel lblNewLabel_31 = new JLabel("\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
						lblNewLabel_31.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_31.setBounds(20, 220, 100, 25);
						panel_3.add(lblNewLabel_31);
						
						txtGeneTransportRange = new JTextField();
						txtGeneTransportRange.setBounds(160, 220, 120, 25);
						panel_3.add(txtGeneTransportRange);
						txtGeneTransportRange.setColumns(10);
						txtGeneTransportRange.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	txtofIsElement.grabFocus();
						        }  
						    }  
						}); 
						JComboBox JComboBoxOfAvailableModifyings = new JComboBox();
						JComboBoxOfAvailableModifyings.setBounds(485, 220, 120, 25);
						panel_3.add(JComboBoxOfAvailableModifyings);
						
						JLabel lblNewLabel_32 = new JLabel("\u57FA\u56E0\u9009\u62E9\u53D8\u5F02\u7B56\u7565");
						lblNewLabel_32.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_32.setBounds(340, 220, 120, 25);
						panel_3.add(lblNewLabel_32);
						
						JLabel lblNewLabel_14 = new JLabel("IS\u957F\u5EA6");
						lblNewLabel_14.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_14.setBounds(20, 260, 100, 25);
						panel_3.add(lblNewLabel_14);
						
						txtofIsElement = new JTextField();
						txtofIsElement.setBounds(160, 260, 120, 25);
						panel_3.add(txtofIsElement);
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
						panel_3.add(lblNewLabel_15);
						
						txtIsTransportRate = new JTextField();
						txtIsTransportRate.setBounds(485, 260, 120, 25);
						panel_3.add(txtIsTransportRate);
						txtIsTransportRate.setColumns(10);
						txtIsTransportRate.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	txtofRisElement.grabFocus();
						        }  
						    }  
						});  
						txtofRisElement = new JTextField();
						txtofRisElement.setBounds(160, 300, 120, 25);
						panel_3.add(txtofRisElement);
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
						panel_3.add(lblNewLabel_16);
						
						JLabel lblNewLabel_17 = new JLabel("RIS\u8F6C\u5EA7\u7387");
						lblNewLabel_17.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_17.setBounds(340, 300, 100, 25);
						panel_3.add(lblNewLabel_17);
						
						txtRisTransportRate = new JTextField();
						txtRisTransportRate.setBounds(485, 300, 120, 25);
						panel_3.add(txtRisTransportRate);
						txtRisTransportRate.setColumns(10);
						
						JButton btnNext3 = new JButton("\u4E0B\u4E00\u6B65");
						btnNext3.setBounds(528, 414, 93, 23);
						btnNext3.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node4));
								tree_1.setSelectionPath(visiblePath);
								HostPane.setVisible(false);
				        		panel_1.setVisible(false);
				        		panel_2.setVisible(false);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(true);
								
							}
							
						});
						panel_3.add(btnNext3);
						btnRun3 = new JButton("\u8FD0\u884C");
					    btnRun3.setBounds(397, 414, 93, 23);
					    panel_3.add(btnRun3);
					    btnRun3.setVisible(false);
					    
					    JButton btnForward3 = new JButton("\u4E0A\u4E00\u6B65");
						btnForward3.setBounds(283, 414, 93, 23);
						panel_3.add(btnForward3);
                        btnForward3.addActionListener(new ActionListener(){

							
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node2));
								tree_1.setSelectionPath(visiblePath);
								HostPane.setVisible(false);
				        		panel_1.setVisible(false);
				        		panel_2.setVisible(true);
				        		panel_3.setVisible(false);
				        		panel_4.setVisible(false);
								
							}
							});
						panel_0.add(panel_3);
						
						
						//函数与随机数的----------------------------------------------------------------------
					    panel_4 = new JPanel();
						panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_4.setBackground(Color.WHITE);
						panel_4.setBounds(155, 115, 631, 447);
						panel_4.setVisible(false);
						panel_4.setLayout(null);
						
						JLabel lblNewLabel_24 = new JLabel("\u586B\u5199\u6240\u9700\u7684\u51FD\u6570\u96C6\u548C\u968F\u673A\u6570");
						lblNewLabel_24.setFont(new Font("宋体", Font.PLAIN, 18));
						lblNewLabel_24.setBounds(20, 20, 216, 25);
						panel_4.add(lblNewLabel_24);
						
						JLabel lblNewLabel_18 = new JLabel("\u53EF\u4F9B\u9009\u62E9\u7684\u51FD\u6570");
						lblNewLabel_18.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_18.setBounds(20, 60, 120, 25);
						panel_4.add(lblNewLabel_18);
						
						JComboBox comboBox = new JComboBox();
						comboBox.setBounds(160, 62, 141, 25);
						panel_4.add(comboBox);
						
						JLabel lblNewLabel_19 = new JLabel("\u5DF2\u9009\u62E9\u7684\u51FD\u6570");
						lblNewLabel_19.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_19.setBounds(20, 100, 100, 25);
						panel_4.add(lblNewLabel_19);
						
						
						
						JButton btnDelFunction = new JButton("\u5220\u9664\u6240\u9009\u51FD\u6570");
						btnDelFunction.setBounds(323, 102, 104, 20);
						panel_4.add(btnDelFunction);
						
						JButton btnDelAllFunction = new JButton("\u5220\u9664\u5168\u90E8\u51FD\u6570");
						btnDelAllFunction.setBounds(473, 102, 110, 20);
						panel_4.add(btnDelAllFunction);
						
						JComboBox JComboBoxOfSelectdFunctions = new JComboBox();
						JComboBoxOfSelectdFunctions.setBounds(160, 102, 141, 25);
						panel_4.add(JComboBoxOfSelectdFunctions);
						
						JLabel lblNewLabel_20 = new JLabel("\u968F\u673A\u53D8\u91CF\u6570\u7EC4\u5927\u5C0F");
						lblNewLabel_20.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_20.setBounds(20, 140, 120, 25);
						panel_4.add(lblNewLabel_20);
						
						txtConstantListSize = new JTextField();
						txtConstantListSize.setBounds(160, 140, 141, 25);
						panel_4.add(txtConstantListSize);
						txtConstantListSize.setColumns(10);
						txtConstantListSize.grabFocus();
						txtConstantListSize.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	txtRandomConstantStart.grabFocus();
						        }  
						    }  
						});  
						JLabel lblNewLabel_21 = new JLabel("\u968F\u673A\u5E38\u91CF\u53D6\u503C\u8303\u56F4");
						lblNewLabel_21.setFont(new Font("宋体", Font.PLAIN, 15));
						lblNewLabel_21.setBounds(20, 180, 120, 25);
						panel_4.add(lblNewLabel_21);
						
						JLabel lblNewLabel_22 = new JLabel("\u4E0A\u9650");
						lblNewLabel_22.setFont(new Font("宋体", Font.PLAIN, 14));
						lblNewLabel_22.setBounds(105, 220, 35, 25);
						panel_4.add(lblNewLabel_22);
						
						txtRandomConstantStart = new JTextField();
						txtRandomConstantStart.setBounds(160, 220, 140, 25);
						panel_4.add(txtRandomConstantStart);
						txtRandomConstantStart.setColumns(10);
						txtRandomConstantStart.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						        if(e.getKeyCode() == KeyEvent.VK_ENTER){   
						        	txtRandomConstantEnd.grabFocus();
						        }  
						    }  
						});  
						JLabel lblNewLabel_23 = new JLabel("\u4E0B\u9650");
						lblNewLabel_23.setFont(new Font("宋体", Font.PLAIN, 14));
						lblNewLabel_23.setBounds(105, 260, 35, 25);
						panel_4.add(lblNewLabel_23);
						
						txtRandomConstantEnd = new JTextField();
						txtRandomConstantEnd.setBounds(160, 260, 140, 25);
						panel_4.add(txtRandomConstantEnd);
						txtRandomConstantEnd.setColumns(10);
						txtRandomConstantEnd.addKeyListener(new KeyAdapter(){
						    public void keyPressed(KeyEvent e) {  
						    	
						    }  
						}); 
						
						
						
						JButton btnRun = new JButton("\u8FD0\u884C\u7B97\u6CD5");
						btnRun.setBounds(528, 414, 93, 23);
						panel_4.add(btnRun);
						btnRun.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								
								
								
								
							}
						});
						panel_0.add(panel_4);
						JButton btnForward4 = new JButton("\u4E0A\u4E00\u6B65");
						btnForward4.setVisible(true);
						btnForward4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node3));
								tree_1.setSelectionPath(visiblePath);
										HostPane.setVisible(false);
						        		panel_1.setVisible(false);
						        		panel_2.setVisible(false);
						        		panel_3.setVisible(true);
						        		panel_4.setVisible(false);
										
									
							}
						});
						btnForward4.setBounds(416, 414, 93, 23);
						panel_4.add(btnForward4);
						
					    
						
						
						
						
						
						
						
						
						
						JLabel lblNewLabel_0 = new JLabel(" GEP \u7B97\u6CD5\u7CFB\u7EDF");
						lblNewLabel_0.setForeground(Color.RED);
						lblNewLabel_0.setFont(new Font("华文新魏", Font.PLAIN, 35));
						lblNewLabel_0.setBounds(267, 10, 286, 63);
						panel_0.add(lblNewLabel_0);
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
	  
	}

	
	class OpenHandler implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(New.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
				File dir=jc.getCurrentDirectory();
				File file=jc.getSelectedFile();
				txtInputPath.setText(dir.toString()+file.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 txtInputPath.setText("You pressed cancel");
			
			}
		}
	}
	class TextFieldListener implements DocumentListener {
		public void textValueChanged(DocumentEvent evt) {
			    btnSetConfig.setVisible(true);
	        	btnNewButton.setVisible(true);
	        	btnChangeConfig.setVisible(true);
		  }
		@Override
		public void changedUpdate(DocumentEvent e) {
			
			 textValueChanged(e);
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			
			textValueChanged(e);
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			
			textValueChanged(e);
		}
		  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public void actionPerformed(ActionEvent e){
	   	  boolean isaddItem=true;
	   	  int fontsize=0;
	   	  String tmp=(String)jcomboBox.getSelectedItem();
	   	  //判断用户所输入的项目是否有重复，若有重复则不增加到JComboBox中。
	   	  try{
	   	  	  fontsize=Integer.parseInt(tmp);
	   	  	  for(int i=0;i< jcomboBox.getItemCount();i++){
	   	  	  	  if (jcomboBox.getItemAt(i).equals(tmp)){
	   	  	  	  	 isaddItem=false;
	   	  	  	  	 break;
	   	  	  	  }
	   	  	  }
	   	  	  if (isaddItem){
	   	  	  	jcomboBox.insertItemAt(tmp,0);//插入项目tmp到0索引位置(第一列中).
	   	  	  }
	   	  	   	  	  
	   	  }catch(NumberFormatException ne){
	   	  	  jcomboBox.getEditor().setItem("你输入的值不是整数值，请重新输入!");
	   	  }
	   }
	   public void itemStateChanged(ItemEvent e){//ItemListener界面只有itemStateChanged()一个方法，在此实作它。
	   	  if (e.getStateChange()==ItemEvent.SELECTED){//当用户的选择改变时，则在JLabel上会显示出Swing目前字形大小信息.
	   	  	  int fontsize=0;
	   	  	  try{
	   	  	  	 fontsize=Integer.parseInt((String)e.getItem());
	   	  	  	System.out.print("Swing 目前字形大小:");	  	  	 
	   	  	  }catch(NumberFormatException ne){//若所输入的值不是整数，则不作任何的操作.
	   	  	  	
	   	  	  }
	   	  }
	   }
	
	   public void showRunButton(){
		   btnRun1.setVisible(true);
		   btnRun2.setVisible(true);
		   btnRun3.setVisible(true);
		  
	   }
	   
	   public void fadeRunButton(){
		   btnRun1.setVisible(false);
		   btnRun2.setVisible(false);
		   btnRun3.setVisible(false);
		   
	   }
}    