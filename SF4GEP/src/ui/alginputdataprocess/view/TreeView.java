package ui.alginputdataprocess.view;

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

public class TreeView extends JPanel {
	  
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode("����ϵͳ");
	  DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("�㷨ϵͳ");
	  final DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("�㷨����");
	  final DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�㷨��ֹ����");
	  final DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("��Ⱥ��Ϣ");
	  final DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("������Ϣ");
	  final DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("���躯���������");
	  DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("����ϵͳ");
	  final DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("�ϴ�/���ؽӿ�");
	  final DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("����·��");
	  DefaultMutableTreeNode root3 = new DefaultMutableTreeNode("���ϵͳ");
	  final DefaultMutableTreeNode node7 = new DefaultMutableTreeNode("������");
	  
	  
	  final DefaultMutableTreeNode Node[]={node0,node1,node2,node3,node4,node5,node6,node7};
	  DefaultTreeModel treeModel = new DefaultTreeModel(root);
	  final JTree tree_1 ;
	  public TreeView() {
	 	setBackground(Color.WHITE);
		//��
	 	  treeModel.insertNodeInto(root1, root, root.getChildCount());
		  treeModel.insertNodeInto(root2, root, root.getChildCount());
		  treeModel.insertNodeInto(root3, root, root.getChildCount());
		  
		  treeModel.insertNodeInto(node0, root1, root1.getChildCount());
		  treeModel.insertNodeInto(node1, root1, root1.getChildCount());
		  treeModel.insertNodeInto(node2, root1, root1.getChildCount());
		  treeModel.insertNodeInto(node3, root1, root1.getChildCount());
		  treeModel.insertNodeInto(node4, root1, root1.getChildCount());
		 
		  
		  treeModel.insertNodeInto(node5, root2, root2.getChildCount());
		  treeModel.insertNodeInto(node6, root2, root2.getChildCount());
	      
		  treeModel.insertNodeInto(node7, root3, root3.getChildCount());
		  
		  
		  
		  UIManager.getLookAndFeelDefaults().put("ClassLoader",getClass().getClassLoader());
		  tree_1 = new JTree(treeModel);
		  tree_1.setRowHeight(20);
		  DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree_1.getCellRenderer();
		  cellRenderer.setLeafIcon(new ImageIcon("//leaf.jpg"));
		  cellRenderer.setOpenIcon(new ImageIcon("//leaf.jpg"));
		  cellRenderer.setClosedIcon(new ImageIcon("//leaf.jpg"));

		  cellRenderer.setFont(new Font("����", Font.PLAIN, 15));// ��������.
		  cellRenderer.setBackgroundNonSelectionColor(Color.white);
		  cellRenderer.setBorderSelectionColor(Color.red);
		  cellRenderer.setTextNonSelectionColor(Color.black);
		  cellRenderer.setTextSelectionColor(Color.WHITE);
		  cellRenderer.setBorderSelectionColor(Color.blue);
		
		
		/*
		 * ����ѡʱ��ѡʱ�����ֵı仯��ɫ
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
