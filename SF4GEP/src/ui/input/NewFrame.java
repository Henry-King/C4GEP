package ui.input;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
import javax.swing.JButton;

import org.hibernate.cache.ReadWriteCache.Item;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathCanvas;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;




import jxl.read.biff.BiffException;
import domain.core.algmodel.configuration.GepAlgorithm;
import domain.core.algmodel.configuration.Individual;
import domain.core.algmodel.configuration.Population;
import domain.core.algmodel.genecomponent.Computable;
import domain.core.algmodel.genecomponent.GenePiece;
import domain.core.algmodel.individualcomponent.HomeoticGene;
import domain.core.algmodel.individualcomponent.NormalGene;
import domain.core.inputmodel.InputSet;
import domain.core.outputmodel.AlgInstance;
import domain.core.outputmodel.GepConfiguration;
import domain.iservice.IgepAlgService;
import domain.service.alg.configuration.GepAlgService;
import domain.service.input.DefaultGepInput;
import domain.service.input.IgepInput;
import domain.service.output.DefalutGepOutput;
import domain.service.output.IgepOutput;
import exception.Duplicated;




public class NewFrame extends JFrame {
	
	private JPanel panel_0; 
	private JPanel contentPane;
	private JPanel HostPane;
	private JComboBox jcomboBoxConfiguration;
    private JButton btnNewButton;
    private JButton btnSetConfig;
    private JButton btnChangeConfig;
    
	private JPanel panel_1;
	private JTextField txtInputPath;
	
	private JPanel panel_2;
	private JTextField txtPopulationSize;
	private JTextField txtMaxGeneration;
	private JTextField txtSelectionRange;
	
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
	private JTextField txtGeneTransportRate;
	
	private JPanel panel_4;
	private JTextField txtConstantListSize;
	private JTextField txtRandomConstantStart;
	private JTextField txtRandomConstantEnd;
	private JTextField txtAccuracy;
	private JComboBox JComboBoxAvailableCreator;
	private JComboBox JComboBoxofSelectionStrategy;
	private JComboBox JComboBoxOfAvailableModifyings;
	private JComboBox comboBox ;
	private JComboBox JComboBoxOfSelectdFunctions;
	private JComboBox JComboBoxAvailableCalculator; 
	
	private JPanel panel_5;
	private JTextField txtConfigurationName;
	private JPanel outPutPanel;
	private JTextField txtOutputPath;
	private JTextField textGeneration;
	private JTextField txtGenerationFrom;
	private JTextField txtGenerationTo;
	
	
	
	
	
	
	private JPanel panelPaint = null;
	private JPanel outPutPanel_1 = null;
	private JPanel outputPanel_2 = null;
	
	
	
	
	int count=2;//判断第几次点击下拉框
    int flag=1;//标志是否为修改配置文件
    int number=0;
	GepConfiguration myParameter=new GepConfiguration();
	IgepAlgService myGepService=new GepAlgService();	
	GepConfiguration myConfigurationFromDB;
	List<GepConfiguration> configurationsOfHistory=myGepService.readArgumentsFromDb();
	IgepInput input =new DefaultGepInput();
	InputSet is = new InputSet();
	int addTime=0;
	
	
	
	
	
	
	
	IgepOutput gepOutput = new DefalutGepOutput();
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		//启动画面
		JFrame frame1 = new JFrame("SF4GEP");
		String picPath = "src\\2.jpg";
		Icon icon = new ImageIcon(picPath);
		JLabel lab = null;
		lab = new JLabel(icon,JLabel.CENTER);
		frame1.add(lab);
		frame1.setSize(700,700);
		frame1.setLocation(300,200);
		frame1.setVisible(true);	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame1.setVisible(false);*/
		
		//程序进入画面
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFrame frame = new NewFrame();
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
	public NewFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 686);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		NewFrame.this.setTitle("算法名称");

		// 树
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("菜单");
		DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("算法名称");
		final DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(
				"数据输入路径");
		final DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("种群信息");
		final DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("基因信息");
		final DefaultMutableTreeNode node4 = new DefaultMutableTreeNode(
				"所需函数和随机数");
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		treeModel.insertNodeInto(node0, root, root.getChildCount());
		treeModel.insertNodeInto(node1, root, root.getChildCount());
		treeModel.insertNodeInto(node2, root, root.getChildCount());
		treeModel.insertNodeInto(node3, root, root.getChildCount());
		treeModel.insertNodeInto(node4, root, root.getChildCount());
		JRadioButton ra = new JRadioButton("+");

		// this line needs to be implemented in order to make JWS work properly
		UIManager.getLookAndFeelDefaults().put("ClassLoader",
				getClass().getClassLoader());
		final JTree tree_1 = new JTree(treeModel);
		tree_1.setRowHeight(20);
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree_1
				.getCellRenderer();
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
		contentPane.setLayout(null);

		panel_0 = new JPanel();
		panel_0.setBackground(Color.WHITE);
		panel_0.setBounds(0, 0, 791, 648);
		panel_0.setVisible(true);
		contentPane.add(panel_0);

		tree_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JTree tree = (JTree) e.getSource();
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 1) {
						TreeNode node = (TreeNode) selPath
								.getLastPathComponent();
						if (node.toString() == "算法名称") {
							HostPane.setVisible(true);
							panel_1.setVisible(false);
							panel_2.setVisible(false);
							panel_3.setVisible(false);
							panel_4.setVisible(false);
							jcomboBoxConfiguration.grabFocus();

						}
						if (node.toString() == "数据输入路径") {
							HostPane.setVisible(false);
							panel_1.setVisible(true);
							panel_2.setVisible(false);
							panel_3.setVisible(false);
							panel_4.setVisible(false);
							txtInputPath.grabFocus();

						}
						if (node.toString() == "种群信息") {
							HostPane.setVisible(false);
							panel_1.setVisible(false);
							panel_2.setVisible(true);
							panel_3.setVisible(false);
							panel_4.setVisible(false);
							txtPopulationSize.grabFocus();
						}
						if (node.toString() == "基因信息") {

							HostPane.setVisible(false);
							panel_1.setVisible(false);
							panel_2.setVisible(false);
							panel_3.setVisible(true);
							panel_4.setVisible(false);
							txtNormalGeneNumber.grabFocus();
						}
						if (node.toString() == "所需函数和随机数") {
							HostPane.setVisible(false);
							panel_1.setVisible(false);
							panel_2.setVisible(false);
							panel_3.setVisible(false);
							panel_4.setVisible(true);
						}
						// 单击
					} else if (e.getClickCount() == 2) {

					}
				}
			}
		});
		tree_1.setRowHeight(20);
		panel_0.setLayout(null);
		final JScrollPane scrollPane = new JScrollPane(tree_1);
		scrollPane.setBounds(0, 115, 150, 447);
		panel_0.add(scrollPane);

		TreePath visiblePath = new TreePath(
				((DefaultTreeModel) tree_1.getModel()).getPathToRoot(node0));
		tree_1.setSelectionPath(visiblePath);

		// 主页------------------------------------------------------------
		String configurations[] = new String[configurationsOfHistory.size()];
		for (int i = 0; i < configurationsOfHistory.size(); i++) {
			String configName = configurationsOfHistory.get(i).getName();
			configurations[i] = configName;
		}
		HostPane = new JPanel();
		HostPane.setBounds(155, 115, 631, 447);
		HostPane.setBackground(Color.WHITE);
		HostPane.setLayout(null);
		HostPane.setVisible(true);
		JLabel lblNewLabel_28 = new JLabel("\u7B97\u6CD5\u540D\u79F0");
		lblNewLabel_28.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_28.setBounds(20, 60, 83, 33);
		HostPane.add(lblNewLabel_28);
		HostPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		jcomboBoxConfiguration = new JComboBox(configurations);
		jcomboBoxConfiguration.setEditable(true);
		jcomboBoxConfiguration.setFont(new Font("宋体", Font.PLAIN, 14));
		jcomboBoxConfiguration.setSelectedIndex(-1);
		jcomboBoxConfiguration.setBackground(Color.WHITE);
		jcomboBoxConfiguration.setBounds(140, 60, 362, 33);
		jcomboBoxConfiguration.grabFocus();

		final ComboBoxEditor editor = jcomboBoxConfiguration.getEditor();
		jcomboBoxConfiguration
				.configureEditor(editor, "请选择或直接输入算法名称!输入完请按回车结束");
		HostPane.add(jcomboBoxConfiguration);

		Component scrollBtn = jcomboBoxConfiguration.getComponent(0);
		scrollBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (number == 0) {
					count = 1;
					number++;
				} else {
					count = 2;
					number--;
				}
			}

		});

		jcomboBoxConfiguration.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				setTitle(jcomboBoxConfiguration.getSelectedItem().toString());
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					if (count != 2) {
						btnNewButton.setVisible(true);
						btnChangeConfig.setVisible(true);
						btnSetConfig.setVisible(false);
						count = 2;
					}

				}

			}
		});

		jcomboBoxConfiguration.getEditor().getEditorComponent()
				.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnSetConfig.setVisible(true);
							btnNewButton.setVisible(false);
							btnChangeConfig.setVisible(false);
							count = 2;
							TreePath visiblePath = new TreePath(
									((DefaultTreeModel) tree_1.getModel())
											.getPathToRoot(node1));
							tree_1.setSelectionPath(visiblePath);
							HostPane.setVisible(false);
							panel_1.setVisible(true);
							panel_2.setVisible(false);
							panel_3.setVisible(false);
							panel_4.setVisible(false);
							setTitle(editor.getItem().toString());

						}
					}
				});
		editor.getEditorComponent().addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {
				jcomboBoxConfiguration.configureEditor(editor, null);
			}
		});

		btnSetConfig = new JButton("\u914D\u7F6E\u53C2\u6570");
		btnSetConfig.setBounds(515, 67, 93, 23);
		btnSetConfig.setVisible(true);
		btnSetConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editor.getItem().equals("请选择或直接输入算法名称!输入完请按回车结束")
						|| editor.getItem().equals("")) {
					JOptionPane.showMessageDialog(null, "配置文件名无效");
					jcomboBoxConfiguration.grabFocus();
				} else {
					HostPane.setVisible(false);
					panel_0.setVisible(true);
					panel_1.setVisible(true);
					TreePath visiblePath = new TreePath(
							((DefaultTreeModel) tree_1.getModel())
									.getPathToRoot(node1));
					tree_1.setSelectionPath(visiblePath);
					txtInputPath.grabFocus();
				}
			}

		});
		HostPane.add(btnSetConfig);

		btnNewButton = new JButton("执行算法");
		btnNewButton.setBounds(193, 120, 93, 23);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle(jcomboBoxConfiguration.getSelectedItem().toString());

				myConfigurationFromDB = configurationsOfHistory
						.get(jcomboBoxConfiguration.getSelectedIndex());// 等于所选到的

				try {
					myGepService.setParameters(myConfigurationFromDB);
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				myGepService.run();
				outPutPanel.setVisible(true);
				HostPane.setVisible(false);
				scrollPane.setVisible(false);
				
				
				
				/**
				 * 画图运算
				 */
				KernelLink ml = null;	//Mathematica 内核
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
				//
				 //mathCanvasA = new MathCanvas(ml);	//构成器含有KernelLink参数
				 //mathCanvasB = new MathCanvas(ml);
				
				MathCanvas mathCanvasA = new MathCanvas(ml);	//拟合曲线图
				MathCanvas mathCanvasB = new MathCanvas(ml);	//每代最佳个体、最差个体的演化曲线图
				
				 ml.evaluateToInputForm("Needs[\"" + KernelLink.PACKAGE_CONTEXT + "\"]", 0);
				 ml.evaluateToInputForm("ConnectToFrontEnd[]", 0);
				
				 
				 
				
				/* mathCanvasA.setMathCommand("<< Graphics`MultipleListPlot`");
				 mathCanvasA.setMathCommand("data1 = {2.7, 3.6, 5.9, 5.7, 5.9, 5.9, 6.1, 6.7, 6.8, 6.0, 4.7, 3.1};" +
					  		"data2 = {2.4, 4.3, 4.7, 6.2, 6.2, 6.2, 6.3, 6.8, 6.8, 5.8, 3.7, 2.2};" +
					"MultipleListPlot[data1, data2, PlotJoined -> True,AxesLabel -> {代数, 适应值}, GridLines -> Automatic, PlotLabel -> Style[Framed[每代最佳个体、最差个体的演化曲线图], 16, Blue, Background -> Lighter[Yellow]]]");
				 
				 //mathCanvasA.setMathCommand("123");
				 */
				 
				 
				 AlgInstance algInstance = myGepService.getMyAlgInstance();
				 
				 mathCanvasB = gepOutput.drawImageB(algInstance,ml);
				
				 /*画图区域*/
					
				 
				    panelPaint = new JPanel();
					panelPaint.setBounds(1, 1, 784, 268);
					panelPaint.setLayout(null);
					outPutPanel.add(panelPaint);
					panelPaint.setBorder(new LineBorder(new Color(0, 0, 0)));
					outPutPanel_1 = new JPanel();
					// 画图区1---------------------------------------------------------------------------------------------------
					outPutPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
					outPutPanel_1.setBackground(Color.WHITE);
					outPutPanel_1.setBounds(0, 0, 382, 268);
					panelPaint.add(outPutPanel_1);
					
					mathCanvasA.setBounds(0, 0, 382, 268);
					outPutPanel_1.add(mathCanvasA);
					

					// 画图区2--------------------------------------------------------------------------------------------------------------
					outputPanel_2 = new JPanel();
					outputPanel_2.setBackground(Color.WHITE);
					outputPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
					outputPanel_2.setBounds(382, 0, 403, 268);
					panelPaint.add(outputPanel_2);
					outputPanel_2.setLayout(null);
					mathCanvasB.setBounds(0, 0, 403, 268);
					outputPanel_2.add(mathCanvasB);

			}

		});

		HostPane.add(btnNewButton);

		btnChangeConfig = new JButton("\u66F4\u6539\u914D\u7F6E");
		btnChangeConfig.setBounds(398, 120, 93, 23);
		btnChangeConfig.setVisible(false);
		btnChangeConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				myConfigurationFromDB = configurationsOfHistory
						.get(jcomboBoxConfiguration.getSelectedIndex());

				try {
					myGepService.setParameters(myConfigurationFromDB);
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				txtAccuracy.setText(myConfigurationFromDB.getAccuray());
				txtConstantListSize.setText(myConfigurationFromDB
						.getConstantListSize());
				txtGeneOnePointRecombineRate.setText(myConfigurationFromDB
						.getOnePointRecombineRate());
				txtGeneRecombineRate.setText(myConfigurationFromDB
						.getGeneRecombineRate());
				txtGeneTransportRate.setText(myConfigurationFromDB
						.getGeneTransportRate());
				txtHomeoticGeneNums.setText(myConfigurationFromDB
						.getHomeoticGeneNumber());
				txtHomeoticHeaderLength.setText(myConfigurationFromDB
						.getHomeoticHeaderLength());
				txtInputPath.setText(myConfigurationFromDB.getInputFile());// 文件输入路径
				txtIsTransportRate.setText(myConfigurationFromDB
						.getIsTransportRate());
				txtMaxGeneration.setText(myConfigurationFromDB
						.getMaxGeneration());
				txtMutateRate.setText(myConfigurationFromDB.getMutateRate());
				txtNormalGeneNumber.setText(myConfigurationFromDB
						.getNormalGeneNumber());
				txtNormalHeaderLength.setText(myConfigurationFromDB
						.getNormalHeaderLength());
				txtofIsElement.setText(myConfigurationFromDB.getIsElement());
				txtofRisElement.setText(myConfigurationFromDB.getRisElement());
				txtPopulationSize.setText(myConfigurationFromDB
						.getPopulationSize());
				txtRandomConstantEnd.setText(myConfigurationFromDB
						.getRandomConstantEnd());
				txtRandomConstantStart.setText(myConfigurationFromDB
						.getRandomConstantStart());
				txtRisTransportRate.setText(myConfigurationFromDB
						.getRisTransportRate());
				txtSelectionRange.setText(myConfigurationFromDB
						.getSelectionRange());
				txtTwoPointRecombineRate.setText(myConfigurationFromDB
						.getTwoPointRecombineRate());
				jcomboBoxConfiguration.setSelectedItem(myConfigurationFromDB
						.getName());

				String[] functions = myConfigurationFromDB.getFunctionList()
						.split(",");
				for (int i = 0; i < functions.length; i++) {
					System.out.println(functions[i].toString());
					if (functions[i]
							.toString()
							.equals("domain.service.alg.userdefined.function.Additioin")) {
						JComboBoxOfSelectdFunctions.addItem("+");
					} else if (functions[i].toString().equals(
							"domain.service.alg.userdefined.function.Minus")) {
						JComboBoxOfSelectdFunctions.addItem("-");
					} else if (functions[i].toString().equals(
							"domain.service.alg.userdefined.function.Multiply")) {
						JComboBoxOfSelectdFunctions.addItem("*");
					} else {
						JComboBoxOfSelectdFunctions.addItem("/");
					}
				}
				JComboBoxAvailableCreator.setSelectedItem(myConfigurationFromDB
						.getCreator());
				// JComboBoxofSelectionStrategy.setSelectedItem(myConfiguration.getSelector());
				JComboBoxOfAvailableModifyings
						.setSelectedItem(myConfigurationFromDB.getModify());
				JComboBoxAvailableCalculator
						.setSelectedItem(myConfigurationFromDB.getCalculator());

				HostPane.setVisible(false);
				panel_0.setVisible(true);
				panel_1.setVisible(true);
				flag = -1;
				setTitle(jcomboBoxConfiguration.getSelectedItem().toString());
				txtInputPath.grabFocus();
			}

		});
		HostPane.add(btnChangeConfig);
		panel_0.add(HostPane);

		JLabel lblNewLabel_27 = new JLabel(
				"\u8F93\u5165\u7B97\u6CD5\u540D\u79F0/\u9009\u62E9\u7B97\u6CD5");
		lblNewLabel_27.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_27.setBounds(20, 20, 305, 25);
		HostPane.add(lblNewLabel_27);
		JButton btnNext0 = new JButton("\u4E0B\u4E00\u6B65");
		btnNext0.setBounds(528, 414, 93, 23);
		HostPane.add(btnNext0);
		btnNext0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) tree_1
						.getModel()).getPathToRoot(node1));
				tree_1.setSelectionPath(visiblePath);
				HostPane.setVisible(false);
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				txtInputPath.grabFocus();
			}

		});

		// 文件输入路径----------------------------------------------------
		panel_1 = new JPanel();
		panel_1.setBounds(155, 115, 631, 447);
		panel_0.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);

		JLabel lblNewLabel_25 = new JLabel(
				"\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		lblNewLabel_25.setBackground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_25.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel_25.setBounds(20, 60, 120, 33);
		panel_1.add(lblNewLabel_25);

		txtInputPath = new JTextField();
		txtInputPath.setBounds(160, 60, 362, 33);
		panel_1.add(txtInputPath);
		txtInputPath.setColumns(10);

		JButton btnInputBrowse = new JButton("\u6D4F\u89C8");
		btnInputBrowse.addActionListener(new OpenInputHandler());
		btnInputBrowse.setBounds(532, 60, 93, 23);
		panel_1.add(btnInputBrowse);

		JButton btnNext1 = new JButton("\u4E0B\u4E00\u6B65");
		btnNext1.setBounds(528, 414, 93, 23);
		btnNext1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) tree_1
						.getModel()).getPathToRoot(node2));
				tree_1.setSelectionPath(visiblePath);
				HostPane.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				txtPopulationSize.grabFocus();
			}

		});
		panel_1.add(btnNext1);

		JLabel lblNewLabel_26 = new JLabel(
				"\u586B\u5199\u6240\u9700\u7684\u6570\u636E\u8F93\u5165\u8DEF\u5F84");
		lblNewLabel_26.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_26.setBounds(20, 20, 208, 25);
		panel_1.add(lblNewLabel_26);

		// 种群的panel-------------------------------------------------------------------
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(155, 115, 631, 447);
		panel_0.add(panel_2);
		panel_2.setForeground(Color.DARK_GRAY);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setVisible(false);

		JLabel lblNewLabel = new JLabel(
				"\u586B\u5199\u6240\u9700\u7684\u79CD\u7FA4\u4FE1\u606F");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 20, 189, 25);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"\u79CD\u7FA4\u521D\u59CB\u5316\u5927\u5C0F");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 60, 120, 25);
		panel_2.add(lblNewLabel_1);

		txtPopulationSize = new JTextField();
		txtPopulationSize.setBounds(160, 60, 120, 25);
		panel_2.add(txtPopulationSize);
		txtPopulationSize.setColumns(10);

		txtPopulationSize.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtMaxGeneration.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_2 = new JLabel(
				"\u79CD\u7FA4\u6700\u5927\u6F14\u5316\u4EE3\u6570");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(340, 60, 120, 25);
		panel_2.add(lblNewLabel_2);

		txtMaxGeneration = new JTextField();
		txtMaxGeneration.setBounds(485, 60, 120, 25);
		panel_2.add(txtMaxGeneration);
		txtMaxGeneration.setColumns(10);
		txtMaxGeneration.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtSelectionRange.grabFocus();
				}
			}
		});

		JLabel lblNewLabel_3 = new JLabel(
				"\u79CD\u7FA4\u521D\u59CB\u5316\u65B9\u5F0F");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(20, 100, 134, 25);
		panel_2.add(lblNewLabel_3);

		JComboBoxAvailableCreator = new JComboBox();
		JComboBoxAvailableCreator.setBounds(160, 100, 120, 21);
		panel_2.add(JComboBoxAvailableCreator);

		try {
			for (int i = 0; i < myGepService.getAvailableCreator().size(); i++) {
				JComboBoxAvailableCreator.addItem(myGepService
						.getAvailableCreator().get(i).toString());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblNewLabel_5 = new JLabel(
				"\u9009\u62E9\u4E2A\u4F53\u7684\u8303\u56F4");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(340, 100, 120, 25);
		panel_2.add(lblNewLabel_5);

		txtSelectionRange = new JTextField();
		txtSelectionRange.setBounds(485, 100, 120, 25);
		panel_2.add(txtSelectionRange);
		txtSelectionRange.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel(
				"\u9009\u62E9\u4E2A\u4F53\u7684\u7B56\u7565");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(20, 140, 120, 25);
		panel_2.add(lblNewLabel_4);

		final JComboBox JComboBoxofSelectionStrategy = new JComboBox();
		JComboBoxofSelectionStrategy.setBounds(160, 140, 120, 21);
		panel_2.add(JComboBoxofSelectionStrategy);

		try {
			for (int i = 0; i < myGepService.getAvailableSelector().size(); i++) {
				JComboBoxofSelectionStrategy.addItem(myGepService
						.getAvailableSelector().get(i).toString());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblNewLabel_33 = new JLabel(
				"\u9002\u5E94\u503C\u8BA1\u7B97\u65B9\u5F0F");
		lblNewLabel_33.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_33.setBounds(340, 140, 120, 25);
		panel_2.add(lblNewLabel_33);

		JComboBoxAvailableCalculator = new JComboBox();
		JComboBoxAvailableCalculator.setBounds(485, 140, 120, 21);

		try {
			for (int i = 0; i < myGepService.getAvailableCalculator().size(); i++) {
				JComboBoxAvailableCalculator.addItem(myGepService
						.getAvailableCalculator().get(i).toString());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel_2.add(JComboBoxAvailableCalculator);

		JButton btnNext2 = new JButton("\u4E0B\u4E00\u6B65");
		btnNext2.setBounds(528, 414, 93, 23);
		panel_2.add(btnNext2);
		btnNext2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) tree_1
						.getModel()).getPathToRoot(node3));
				tree_1.setSelectionPath(visiblePath);
				HostPane.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_3.setVisible(true);
				panel_4.setVisible(false);
				txtNormalGeneNumber.grabFocus();
			}

		});
		// 基因的----------------------------------------------------------
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(155, 115, 631, 447);
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		JLabel lblNewLabel_6 = new JLabel(
				"\u586B\u5199\u6240\u9700\u7684\u57FA\u56E0\u4FE1\u606F");
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

		txtNormalGeneNumber.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtNormalHeaderLength.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_8 = new JLabel(
				"\u57FA\u56E0\u5934\u90E8\u5927\u5C0F");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(340, 60, 100, 25);
		panel_3.add(lblNewLabel_8);

		txtNormalHeaderLength = new JTextField();
		txtNormalHeaderLength.setBounds(485, 62, 120, 25);
		panel_3.add(txtNormalHeaderLength);
		txtNormalHeaderLength.setColumns(10);
		txtNormalHeaderLength.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtHomeoticGeneNums.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_9 = new JLabel(
				"\u540C\u6E90\u67D3\u8272\u4F53\u4E2A\u6570");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(20, 100, 120, 25);
		panel_3.add(lblNewLabel_9);

		txtHomeoticGeneNums = new JTextField();
		txtHomeoticGeneNums.setBounds(160, 100, 120, 25);
		panel_3.add(txtHomeoticGeneNums);
		txtHomeoticGeneNums.setColumns(10);
		txtHomeoticGeneNums.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtHomeoticHeaderLength.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_10 = new JLabel(
				"\u540C\u6E90\u67D3\u8272\u4F53\u5934\u90E8\u5927\u5C0F");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(340, 100, 142, 25);
		panel_3.add(lblNewLabel_10);

		txtHomeoticHeaderLength = new JTextField();
		txtHomeoticHeaderLength.setBounds(485, 100, 120, 25);
		panel_3.add(txtHomeoticHeaderLength);
		txtHomeoticHeaderLength.setColumns(10);
		txtHomeoticHeaderLength.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtGeneRecombineRate.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_11 = new JLabel(
				"\u57FA\u56E0\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(20, 140, 100, 25);
		panel_3.add(lblNewLabel_11);

		txtGeneRecombineRate = new JTextField();
		txtGeneRecombineRate.setBounds(160, 140, 120, 25);
		panel_3.add(txtGeneRecombineRate);
		txtGeneRecombineRate.setColumns(10);
		txtGeneRecombineRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtGeneOnePointRecombineRate.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_12 = new JLabel(
				"\u57FA\u56E0\u5355\u70B9\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(340, 140, 142, 25);
		panel_3.add(lblNewLabel_12);

		txtGeneOnePointRecombineRate = new JTextField();
		txtGeneOnePointRecombineRate.setBounds(485, 140, 120, 25);
		panel_3.add(txtGeneOnePointRecombineRate);
		txtGeneOnePointRecombineRate.setColumns(10);
		txtGeneOnePointRecombineRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtTwoPointRecombineRate.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_13 = new JLabel(
				"\u57FA\u56E0\u591A\u70B9\u91CD\u7EC4\u6982\u7387");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(20, 180, 131, 25);
		panel_3.add(lblNewLabel_13);

		txtTwoPointRecombineRate = new JTextField();
		txtTwoPointRecombineRate.setBounds(160, 180, 120, 25);
		panel_3.add(txtTwoPointRecombineRate);
		txtTwoPointRecombineRate.setColumns(10);
		txtTwoPointRecombineRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtMutateRate.grabFocus();
				}
			}
		});

		JLabel lblNewLabel_30 = new JLabel(
				"\u57FA\u56E0\u7A81\u53D8\u6982\u7387");
		lblNewLabel_30.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_30.setBounds(340, 180, 100, 25);
		panel_3.add(lblNewLabel_30);

		txtMutateRate = new JTextField();
		txtMutateRate.setBounds(485, 180, 120, 25);
		panel_3.add(txtMutateRate);
		txtMutateRate.setColumns(10);
		txtMutateRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtGeneTransportRate.grabFocus();
				}
			}
		});

		JLabel lblNewLabel_31 = new JLabel(
				"\u57FA\u56E0\u8F6C\u5EA7\u6982\u7387");
		lblNewLabel_31.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_31.setBounds(20, 220, 100, 25);
		panel_3.add(lblNewLabel_31);

		txtGeneTransportRate = new JTextField();
		txtGeneTransportRate.setBounds(160, 220, 120, 25);
		panel_3.add(txtGeneTransportRate);
		txtGeneTransportRate.setColumns(10);
		txtGeneTransportRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtofIsElement.grabFocus();
				}
			}
		});
		JComboBoxOfAvailableModifyings = new JComboBox();
		JComboBoxOfAvailableModifyings.setBounds(485, 220, 120, 25);
		try {
			for (int i = 0; i < myGepService.getAvailableModifyings().size(); i++) {
				JComboBoxOfAvailableModifyings.addItem(myGepService
						.getAvailableModifyings().get(i).toString());
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		panel_3.add(JComboBoxOfAvailableModifyings);

		JLabel lblNewLabel_32 = new JLabel(
				"\u57FA\u56E0\u9009\u62E9\u53D8\u5F02\u7B56\u7565");
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
		txtofIsElement.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

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
		txtIsTransportRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtofRisElement.grabFocus();
				}
			}
		});
		txtofRisElement = new JTextField();
		txtofRisElement.setBounds(160, 300, 120, 25);
		panel_3.add(txtofRisElement);
		txtofRisElement.setColumns(10);
		txtofRisElement.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		txtRisTransportRate.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					TreePath visiblePath = new TreePath(
							((DefaultTreeModel) tree_1.getModel())
									.getPathToRoot(node4));
					tree_1.setSelectionPath(visiblePath);
					HostPane.setVisible(false);
					panel_1.setVisible(false);
					panel_2.setVisible(false);
					panel_3.setVisible(false);
					panel_4.setVisible(true);
				}
			}
		});
		JButton btnNext3 = new JButton("\u4E0B\u4E00\u6B65");
		btnNext3.setBounds(528, 414, 93, 23);
		btnNext3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath visiblePath = new TreePath(((DefaultTreeModel) tree_1
						.getModel()).getPathToRoot(node4));
				tree_1.setSelectionPath(visiblePath);
				HostPane.setVisible(false);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				panel_3.setVisible(false);
				panel_4.setVisible(true);

			}

		});
		panel_3.add(btnNext3);

		panel_0.add(panel_3);

		// 函数与随机数的----------------------------------------------------------------------
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(155, 115, 631, 447);
		panel_4.setVisible(false);
		panel_4.setLayout(null);

		JLabel lblNewLabel_24 = new JLabel(
				"\u586B\u5199\u6240\u9700\u7684\u51FD\u6570\u96C6\u548C\u968F\u673A\u6570");
		lblNewLabel_24.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_24.setBounds(20, 20, 216, 25);
		panel_4.add(lblNewLabel_24);

		JLabel lblNewLabel_18 = new JLabel(
				"\u53EF\u4F9B\u9009\u62E9\u7684\u51FD\u6570");
		lblNewLabel_18.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_18.setBounds(20, 60, 120, 25);
		panel_4.add(lblNewLabel_18);

		comboBox = new JComboBox();
		comboBox.setBounds(160, 62, 141, 25);
		panel_4.add(comboBox);
		try {
			for (int i = 0; i < myGepService.getAvailableFunctions().size(); i++) {
				comboBox.addItem(myGepService.getAvailableFunctions().get(i)
						.toString());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel lblNewLabel_19 = new JLabel(
				"\u5DF2\u9009\u62E9\u7684\u51FD\u6570");
		lblNewLabel_19.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_19.setBounds(20, 100, 100, 25);
		panel_4.add(lblNewLabel_19);

		JComboBoxOfSelectdFunctions = new JComboBox();
		JComboBoxOfSelectdFunctions.setBounds(160, 102, 141, 25);
		panel_4.add(JComboBoxOfSelectdFunctions);
		final ArrayList<String> items = new ArrayList<String>();

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isaddItem = true;
				if (addTime == 0) {
					items.add(comboBox.getSelectedItem().toString());
					JComboBoxOfSelectdFunctions.addItem(comboBox
							.getSelectedItem().toString());
					JComboBoxOfSelectdFunctions.setSelectedItem(comboBox
							.getSelectedItem().toString());
				} else {
					for (int i = 0; i < items.size(); i++) {
						String item = comboBox.getSelectedItem().toString();
						if (item.equals(items.get(i))) {
							isaddItem = false;
						}
					}
					if (isaddItem == true) {
						items.add(comboBox.getSelectedItem().toString());
						JComboBoxOfSelectdFunctions.addItem(comboBox
								.getSelectedItem().toString());
						JComboBoxOfSelectdFunctions.setSelectedItem(comboBox
								.getSelectedItem().toString());
					}
				}

				addTime++;

			}

		});
		JButton btnDelFunction = new JButton(
				"\u5220\u9664\u6240\u9009\u51FD\u6570");
		btnDelFunction.setBounds(323, 102, 104, 20);
		btnDelFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectItem = JComboBoxOfSelectdFunctions
						.getSelectedItem();
				JComboBoxOfSelectdFunctions.removeItem(selectItem);
			}
		});
		panel_4.add(btnDelFunction);

		JButton btnDelAllFunction = new JButton(
				"\u5220\u9664\u5168\u90E8\u51FD\u6570");
		btnDelAllFunction.setBounds(473, 102, 110, 20);
		btnDelAllFunction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBoxOfSelectdFunctions.removeAllItems();
			}
		});

		panel_4.add(btnDelAllFunction);

		JLabel lblNewLabel_20 = new JLabel(
				"\u968F\u673A\u53D8\u91CF\u6570\u7EC4\u5927\u5C0F");
		lblNewLabel_20.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_20.setBounds(20, 140, 120, 25);
		panel_4.add(lblNewLabel_20);

		txtConstantListSize = new JTextField();
		txtConstantListSize.setBounds(160, 140, 141, 25);
		panel_4.add(txtConstantListSize);
		txtConstantListSize.setColumns(10);
		txtConstantListSize.grabFocus();
		txtConstantListSize.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtRandomConstantStart.grabFocus();
				}
			}
		});
		JLabel lblNewLabel_21 = new JLabel(
				"\u968F\u673A\u5E38\u91CF\u53D6\u503C\u8303\u56F4");
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
		txtRandomConstantStart.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		txtRandomConstantEnd.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtAccuracy.grabFocus();
				}

			}
		});

		JLabel lblNewLabel_29 = new JLabel(
				"\u7B97\u6CD5\u7ED3\u679C\u7684\u7CBE\u5EA6");
		lblNewLabel_29.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_29.setBounds(23, 300, 117, 25);
		panel_4.add(lblNewLabel_29);

		txtAccuracy = new JTextField();
		txtAccuracy.setBounds(160, 300, 140, 25);
		panel_4.add(txtAccuracy);
		txtAccuracy.setColumns(10);
		txtAccuracy.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// run
			}
		});

		JButton btnRun = new JButton("运行");
		btnRun.setBounds(528, 414, 93, 23);
		panel_4.add(btnRun);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myParameter.setInputFile(txtInputPath.getText());	//设置输入文件的路径
				myParameter.setName(jcomboBoxConfiguration.getSelectedItem()
						.toString());
				myParameter.setAccuray(txtAccuracy.getText());
				myParameter.setNormalGeneNumber(txtNormalGeneNumber.getText());
				myParameter.setGeneRecombineRate(txtGeneRecombineRate.getText());
				myParameter.setGeneTransportRate(txtGeneTransportRate.getText());
				myParameter.setNormalHeaderLength(txtNormalHeaderLength
						.getText());
				myParameter.setIsTransportRate(txtIsTransportRate.getText());
				myParameter.setMaxGeneration(txtMaxGeneration.getText());
				myParameter
						.setOnePointRecombineRate(txtGeneOnePointRecombineRate
								.getText());
				myParameter.setPopulationSize(txtPopulationSize.getText());
				myParameter.setRisTransportRate(txtRisTransportRate.getText());
				myParameter.setSelectionRange(txtSelectionRange.getText());
				myParameter.setTwoPointRecombineRate(txtTwoPointRecombineRate
						.getText());
				myParameter.setMutateRate(txtMutateRate.getText());

				String isElement = txtofIsElement.getText().trim();
				String strIsElement = "";
				for (int i = 0; i < isElement.length(); i++) {
					if (i != 0) {
						if (isElement.charAt(i) != ','
								&& (isElement.substring(0, i).indexOf(
										isElement.charAt(i)) < 0)) {
							strIsElement = strIsElement + isElement.charAt(i);
						}
					} else {
						if (isElement.charAt(i) != ',') {
							strIsElement = strIsElement + isElement.charAt(i);
						}
					}
				}
				myParameter.setIsElement(strIsElement);
				String risElement = txtofRisElement.getText().trim();
				String strRisElement = "";
				for (int i = 0; i < risElement.length(); i++) {
					if (i != 0) {
						if (risElement.charAt(i) != ','
								&& (risElement.substring(0, i).indexOf(
										risElement.charAt(i)) < 0)) {
							strRisElement = strRisElement
									+ risElement.charAt(i);
						}
					} else {
						if (risElement.charAt(i) != ',') {
							strRisElement = strRisElement
									+ risElement.charAt(i);
						}
					}
				}

				myParameter.setRisElement(strRisElement);
				myParameter.setHomeoticGeneNumber(txtHomeoticGeneNums.getText());
				myParameter.setHomeoticHeaderLength(txtHomeoticHeaderLength
						.getText());
				myParameter.setRandomConstantStart(txtRandomConstantStart
						.getText());
				myParameter.setRandomConstantEnd(txtRandomConstantEnd.getText());
				myParameter.setConstantListSize(txtConstantListSize.getText());

				try {
					int selectedIndex = JComboBoxAvailableCreator
							.getSelectedIndex();
					myParameter.setCreator(myGepService.getAvailableCreator()
							.get(selectedIndex).getClass().getName());

					selectedIndex = JComboBoxAvailableCalculator
							.getSelectedIndex();
					myParameter.setCalculator(myGepService
							.getAvailableCalculator().get(selectedIndex)
							.getClass().getName());

					selectedIndex = JComboBoxOfAvailableModifyings
							.getSelectedIndex();
					myParameter.setModify(myGepService.getAvailableModifyings()
							.get(selectedIndex).getClass().getName());
					StringBuffer result = new StringBuffer();
					String str = new String();
					for (int i = 0; i < JComboBoxOfSelectdFunctions
							.getItemCount(); i++) {
						if (JComboBoxOfSelectdFunctions.getItemAt(i).toString()
								.equals("+")) {
							str = "domain.service.alg.userdefined.function.Additioin";

						} else if (JComboBoxOfSelectdFunctions.getItemAt(i)
								.toString().equals("-")) {
							str = "domain.service.alg.userdefined.function.Minus";

						} else if (JComboBoxOfSelectdFunctions.getItemAt(i)
								.toString().equals("*")) {
							str = "domain.service.alg.userdefined.function.Multiply";

						} else {
							str = "domain.service.alg.userdefined.function.Divide";

						}

						result.append(str + ",");
					}
					myParameter.setFunctionList(result.toString());

					selectedIndex = JComboBoxofSelectionStrategy
							.getSelectedIndex();
					myParameter.setSelector(myGepService.getAvailableSelector()
							.get(selectedIndex).getClass().getName());

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}

				try {
					myGepService.setParameters(myParameter);
				} catch (BiffException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (InstantiationException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IllegalAccessException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (ClassNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}


				myGepService.run();

				
				try {
					if (flag == -1) {
						if (myParameter.equals(myConfigurationFromDB) == false) {
							JOptionPane.showMessageDialog(null, "配置文件已更改请重新保存");
							// 建一个panel5；
							panel_5.setVisible(true);
							panel_4.setVisible(false);
						}
					} else {
						myGepService.saveArgumentsToDb(myParameter);
						outPutPanel.setVisible(true);
						panel_4.setVisible(false);
						scrollPane.setVisible(false);
					}
				} catch (Duplicated e2) {
					e2.printStackTrace();
				}

			}
		});
		panel_0.add(panel_4);

		// 保存配置文件名
		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(155, 115, 631, 447);
//		contentPane.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(false);

		JLabel lblNewLabel_34 = new JLabel(
				"\u8BF7\u4E3A\u66F4\u6539\u7684\u914D\u7F6E\u6587\u4EF6\u91CD\u547D\u540D");
		lblNewLabel_34.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_34.setBounds(20, 20, 216, 25);
		panel_5.add(lblNewLabel_34);

		JLabel lblNewLabel_35 = new JLabel("\u914D\u7F6E\u6587\u4EF6\u540D");
		lblNewLabel_35.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_35.setBounds(20, 60, 106, 25);
		panel_5.add(lblNewLabel_35);

		txtConfigurationName = new JTextField();
		txtConfigurationName.setBounds(132, 62, 271, 25);
		panel_5.add(txtConfigurationName);
		txtConfigurationName.setColumns(10);

		JButton btnSave = new JButton("\u4FDD\u5B58");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myParameter.setName(txtConfigurationName.getText());
				try {
					myGepService.saveArgumentsToDb(myParameter);
					outPutPanel.setVisible(true);
					// panel_0.setVisible(false);
					panel_5.setVisible(false);
					scrollPane.setVisible(false);

				} catch (Duplicated e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(423, 62, 78, 23);
		panel_5.add(btnSave);
		panel_0.add(panel_5);

		JLabel lblNewLabel_0 = new JLabel(" GEP \u7B97\u6CD5\u7CFB\u7EDF");
		lblNewLabel_0.setForeground(Color.RED);
		lblNewLabel_0.setFont(new Font("华文新魏", Font.PLAIN, 30));
		lblNewLabel_0.setBounds(267, 10, 206, 63);
		panel_0.add(lblNewLabel_0);

		JPanel panel = new JPanel();
		panel.setBounds(0, 561, 813, 87);
		panel_0.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_38 = new JLabel(
				"-----------1.0\u7248\u672C\uFF08c\uFF09Miscrosoft Cooperation------------");
		lblNewLabel_38.setBounds(155, 25, 463, 28);
		lblNewLabel_38.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_38.setForeground(Color.RED);
		panel.add(lblNewLabel_38);

		// 输出面板
		outPutPanel = new JPanel();
		outPutPanel.setBackground(Color.WHITE);
		outPutPanel.setBounds(1, 115, 785, 445);
		panel_0.add(outPutPanel);
		outPutPanel.setLayout(null);
		outPutPanel.setVisible(false);
		outPutPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		// 计算结果的输出路径
		JLabel label = new JLabel(
				"\u7B97\u6CD5\u8BA1\u7B97\u7ED3\u679C\u7684\u8F93\u51FA\u8DEF\u5F84");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(25, 310, 174, 26);
		outPutPanel.add(label);

		txtOutputPath = new JTextField();
		txtOutputPath.grabFocus();
		txtOutputPath.setBounds(20, 340, 290, 25);
		outPutPanel.add(txtOutputPath);
		txtOutputPath.setColumns(10);

		JButton btnBrowseOfOutput = new JButton("\u6D4F\u89C8");
		btnBrowseOfOutput.addActionListener(new OpenOutputHandler());
		btnBrowseOfOutput.setFont(new Font("宋体", Font.PLAIN, 15));
		btnBrowseOfOutput.setBounds(207, 400, 93, 23);
		outPutPanel.add(btnBrowseOfOutput);

		// 输出代数
		JLabel label_37 = new JLabel("\u8F93\u51FA\u4EE3\u6570");
		label_37.setFont(new Font("宋体", Font.PLAIN, 15));
		label_37.setBounds(440, 310, 82, 18);
		outPutPanel.add(label_37);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(440, 340, 307, 60);
		outPutPanel.add(tabbedPane);

		// 输出后多少代
		JPanel panelForGeneration = new JPanel();
		panelForGeneration.setBorder(null);
		panelForGeneration.setBackground(Color.WHITE);
		tabbedPane.addTab("后xx代", null, panelForGeneration, null);
		panelForGeneration.setLayout(null);

		textGeneration = new JTextField();
		textGeneration.setBounds(0, 2, 302, 26);
		panelForGeneration.add(textGeneration);
		textGeneration.setColumns(10);
		textGeneration.grabFocus();

		// 输出从多少代到多少代
		JPanel panelForGeneRationFrom = new JPanel();
		panelForGeneRationFrom.setBorder(null);
		panelForGeneRationFrom.setBackground(Color.WHITE);
		tabbedPane.addTab("从XX代到XX代", null, panelForGeneRationFrom, null);
		panelForGeneRationFrom.setLayout(null);

		txtGenerationFrom = new JTextField();
		txtGenerationFrom.setBounds(0, 2, 121, 26);
		panelForGeneRationFrom.add(txtGenerationFrom);
		txtGenerationFrom.setColumns(10);
		txtGenerationFrom.grabFocus();
		JLabel lblTo = new JLabel("----->");
		lblTo.setBackground(Color.LIGHT_GRAY);
		lblTo.setForeground(Color.BLACK);
		lblTo.setBounds(131, 3, 42, 25);
		lblTo.setBorder(null);
		panelForGeneRationFrom.add(lblTo);

		txtGenerationTo = new JTextField();
		txtGenerationTo.setBounds(173, 2, 129, 26);
		panelForGeneRationFrom.add(txtGenerationTo);
		txtGenerationTo.setColumns(10);
		txtGenerationTo.grabFocus();
		JButton btnRunGeneration = new JButton("\u786E\u5B9A");
		btnRunGeneration.setFont(new Font("宋体", Font.PLAIN, 15));
		btnRunGeneration.setBounds(655, 400, 93, 23);
		outPutPanel.add(btnRunGeneration);

		
		
		
		
		
		
		/*画图区域*/
		
		/*JPanel panelPaint = new JPanel();
		panelPaint.setBounds(1, 1, 784, 268);
		panelPaint.setLayout(null);
		outPutPanel.add(panelPaint);
		panelPaint.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel outPutPanel_1 = new JPanel();
		// 画图区1---------------------------------------------------------------------------------------------------
		outPutPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		outPutPanel_1.setBackground(Color.WHITE);
		outPutPanel_1.setBounds(0, 0, 382, 268);
		panelPaint.add(outPutPanel_1);
		

		// 画图区2--------------------------------------------------------------------------------------------------------------
		JPanel outputPanel_2 = new JPanel();
		outputPanel_2.setBackground(Color.WHITE);
		outputPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputPanel_2.setBounds(382, 0, 403, 268);
		panelPaint.add(outputPanel_2);
		outputPanel_2.setLayout(null);
		
		mathCanvasB.setBounds(0, 0, 403, 268);
		outputPanel_2.add(mathCanvasB);*/
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	class OpenInputHandler implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(NewFrame.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
				File dir=jc.getCurrentDirectory();
				File file=jc.getSelectedFile();
				txtInputPath.setText(file.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 txtInputPath.setText("You pressed cancel");
			}
		}
	}
	
	class OpenOutputHandler implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
			JFileChooser jc=new JFileChooser();
			int rVal=jc.showOpenDialog(NewFrame.this);
			if(rVal==JFileChooser.APPROVE_OPTION){
				File dir=jc.getCurrentDirectory();
				File file=jc.getSelectedFile();
				txtOutputPath.setText(dir.toString()+file.toString());
			}
			if(rVal==JFileChooser.CANCEL_OPTION){
				 txtOutputPath.setText("You pressed cancel");
			
			}
		}
	}
	
}



