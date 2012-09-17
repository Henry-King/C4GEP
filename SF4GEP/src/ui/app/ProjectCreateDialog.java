package ui.app;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;

import ui.images.ImageHelper;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class ProjectCreateDialog extends JDialog {

	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("项目框架");
	DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("串行框架 ");
	DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("并行框架");
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
	JTree tree_1 ;
	JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton = new JButton("确定");
	JButton btnNewButton_1 = new JButton("取消");
	JList<String> listForParr = new JList<String>();
	JList<String> listForSer=new JList<String>();
	JTextArea introduction = new JTextArea();
	JPanel serr_Panel = new JPanel();
	JPanel parr_Panel = new JPanel();
	JPanel introduction_Panel = new JPanel();
	JPanel button_Panel = new JPanel();
	JPanel top_Panel = new JPanel();
	JPanel center_Panel = new JPanel();
	JPanel bottom_Panel = new JPanel();
	
	public ProjectCreateDialog(final Component parent) {
	
		super(JOptionPane.getFrameForComponent(parent), "New", true);
		this.setSize(800, 600);
		JPanel project_Panel = new JPanel();
		project_Panel.setBackground(Color.WHITE);
		project_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("home.gif"),"",0, 0));
		getContentPane().add(project_Panel, BorderLayout.WEST);
		initIndexTree();
		project_Panel.add(scrollPane);
		
		{
			serr_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Themes-List", 0, 0));
			getContentPane().add(serr_Panel, BorderLayout.CENTER);
			serr_Panel.setBackground(Color.WHITE);
			serr_Panel.setLayout(new BorderLayout(0, 0));
			serr_Panel.setVisible(true);
			initListSerr();
			serr_Panel.add(listForSer);
		}
		
		{
			parr_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Themes-List", 0, 0));
			parr_Panel.setLayout(new BorderLayout(0, 0));
			parr_Panel.setVisible(true);
			initListParr();
			parr_Panel.add(listForParr);

		}
		{
			introduction_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("paste.gif"),"框架简介",0, 0));
			getContentPane().add(introduction_Panel, BorderLayout.EAST);
			introduction_Panel.setLayout(new BorderLayout(0, 0));
			initIntroductionPanel();
		}
		{
			initButtonPanel();
		}
		
		
		
		
		
	}
	private void initListParr() {
		listForParr.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForParr.setBackground(Color.WHITE);
		listForParr.setSelectedIndex(0);
		listForParr.setFont(new Font("宋体", Font.PLAIN, 16));
		listForParr.setListData(new String[]{"主从式","混合式","粗粒度","细粒度"});
		listForParr.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    updateIntroduction();
                }
            }
        });
		
	}
	private void initListSerr() {
		listForSer.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForSer.setBackground(Color.WHITE);
		listForSer.setSelectedIndex(0);
		listForSer.setFont(new Font("宋体", Font.PLAIN, 16));
		listForSer.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                   updateIntroduction();
                }
            }
        });
		listForSer.setListData(new String[]{"串行式"});
		introduction.setText("主从式");
	}
	private void initIntroductionPanel(){
		
		JPanel introductionText_Panel = new JPanel();
		introductionText_Panel.setBackground(Color.WHITE);
		introduction_Panel.add(introductionText_Panel);
		
		introduction = new JTextArea();
		introduction.setPreferredSize(new Dimension(180, 436));
		introductionText_Panel.add(introduction);
	}
	private void initButtonPanel(){
		button_Panel.setBackground(Color.WHITE);
		getContentPane().add(button_Panel, BorderLayout.SOUTH);
		button_Panel.setLayout(new BorderLayout(0, 0));
	    
		
		top_Panel.setBackground(Color.WHITE);
		FlowLayout fl_top_Panel = (FlowLayout) top_Panel.getLayout();
		fl_top_Panel.setHgap(50);
		fl_top_Panel.setVgap(8);
		fl_top_Panel.setAlignment(FlowLayout.LEFT);
		button_Panel.add(top_Panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("\u9879\u76EE\u540D\u79F0");
		top_Panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textField.setColumns(40);
		textField.setBackground(Color.WHITE);
		textField.setPreferredSize(new Dimension(250, 30));
		top_Panel.add(textField);
		
		
		
		center_Panel.setBackground(Color.WHITE);
		FlowLayout fl_center_Panel = (FlowLayout) center_Panel.getLayout();
		fl_center_Panel.setVgap(8);
		fl_center_Panel.setHgap(50);
		fl_center_Panel.setAlignment(FlowLayout.LEFT);
		button_Panel.add(center_Panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("保存位置");
		center_Panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setPreferredSize(new Dimension(250, 30));
		center_Panel.add(textField_1);
		textField_1.setColumns(40);
		
		JButton btnNewButton_2 = new JButton("Browse");
		btnNewButton_2.setPreferredSize(new Dimension(80,25));
		center_Panel.add(btnNewButton_2);
		
		
		bottom_Panel.setBackground(Color.WHITE);
		FlowLayout fl_bottom_Panel = (FlowLayout) bottom_Panel.getLayout();
		fl_bottom_Panel.setVgap(0);
		fl_bottom_Panel.setHgap(10);
		fl_bottom_Panel.setAlignment(FlowLayout.RIGHT);
		button_Panel.add(bottom_Panel, BorderLayout.SOUTH);
		btnNewButton.setPreferredSize(new Dimension(60, 25));
		bottom_Panel.add(btnNewButton);
		
		
		btnNewButton_1.setPreferredSize(new Dimension(60, 25));
		bottom_Panel.add(btnNewButton_1);
	}
	private void updateIntroduction() {
		final String frameName= (String)listForParr.getSelectedValue();
		if(frameName.equals("主从式")){
			introduction.setText("主从式");
		}
		else if(frameName.equals("粗粒度")){
			introduction.setText("粗粒度");
		}
		else if(frameName.equals("细粒度")){
			introduction.setText("细粒度");
		}
		else if(frameName.equals("混合式")){
			introduction.setText("混合式");
		}
		
	}
	private void initIndexTree() {
		//树
	 	  treeModel.insertNodeInto(root1, root, root.getChildCount());
		  treeModel.insertNodeInto(root2, root, root.getChildCount());
		  UIManager.getLookAndFeelDefaults().put("ClassLoader",getClass().getClassLoader());
		  tree_1 = new JTree(treeModel);
		  tree_1.setBorder(null);
		  tree_1.setRowHeight(30);
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
		  tree_1.addMouseListener(new MouseAdapter(){});
		  tree_1.setRowHeight(20);
		  scrollPane = new JScrollPane(tree_1);
		  scrollPane.setPreferredSize(new Dimension(194, 412));
		  scrollPane.setBorder(null);
		  scrollPane.setVisible(true);
		  tree_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					 int selRow = tree_1.getRowForLocation(e.getX(), e.getY());
				      TreePath selPath = tree_1.getPathForLocation(e.getX(), e.getY());
				      if (selRow != -1)
				      {
				          if (e.getClickCount() == 1)
				          {
				          	TreeNode node = (TreeNode) selPath.getLastPathComponent();
				          	
				          	if((node.toString()).equals("并行框架")){
				          		getContentPane().add(parr_Panel, BorderLayout.CENTER);
				          	    parr_Panel.setVisible(true);
				          	    serr_Panel.setVisible(false);
				          	    introduction.setText("主从式");
				          	}
				          	else{
				          		System.out.print("在那里"+node.toString());
				          		getContentPane().add(serr_Panel, BorderLayout.CENTER);
				          		 serr_Panel.setVisible(true);
					          	 parr_Panel.setVisible(false);
					          	introduction.setText("串行式");
				          	}
				          }
				     }
				}
		 });
	 }
	 
}
