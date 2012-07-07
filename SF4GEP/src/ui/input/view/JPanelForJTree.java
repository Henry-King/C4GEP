package ui.input.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class JPanelForJTree extends JPanel {
	  
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode("菜单");
	  final DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("算法名称");
	  final DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("数据输入路径");
	  final DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("种群信息");
	  final DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("基因信息");
	  final DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("所需函数和随机数");
	  final DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("上传接口");
	  final DefaultMutableTreeNode Node[]={node0,node1,node2,node3,node4,node5};
	  DefaultTreeModel treeModel = new DefaultTreeModel(root);
	  final JTree tree_1 ;
	 public JPanelForJTree() {
	 	setBackground(Color.WHITE);
		//树
		 
		  treeModel.insertNodeInto(node0, root, root.getChildCount());
		  treeModel.insertNodeInto(node1, root, root.getChildCount());
		  treeModel.insertNodeInto(node2, root, root.getChildCount());
		  treeModel.insertNodeInto(node3, root, root.getChildCount());
		  treeModel.insertNodeInto(node4, root, root.getChildCount());
		  treeModel.insertNodeInto(node5, root, root.getChildCount());
		  
	      
		  UIManager.getLookAndFeelDefaults().put("ClassLoader",getClass().getClassLoader());
		  tree_1 = new JTree(treeModel);
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
		  tree_1.addMouseListener(new MouseAdapter(){});
		  tree_1.setRowHeight(20);
		  JScrollPane scrollPane = new JScrollPane(tree_1);
		  scrollPane.setBounds(0, 0, 155, 455);
		  scrollPane.setVisible(true);
		  setLayout(null);
		  add(scrollPane);
	}

}
