package ui.alginputdataprocess.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreeView extends JPanel {
	  
	  DefaultMutableTreeNode root = new DefaultMutableTreeNode("����ϵͳ");
	  DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("�㷨ϵͳ");
	  final DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("�㷨����");
	  public final DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�㷨��ֹ����");
	  final DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("��Ⱥ��Ϣ");
	  final DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("������Ϣ");
	  final DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("���躯���������");
	  DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("����ϵͳ");
	  final DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("����·��");
	  DefaultMutableTreeNode root3 = new DefaultMutableTreeNode("���ϵͳ");
	  final DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("������");
	  
	  
	  final DefaultMutableTreeNode Node[]={node0,node1,node2,node3,node4,node5,node6};
	  DefaultTreeModel treeModel = new DefaultTreeModel(root);
	  public final JTree tree_1 ;
	  public TreeView(final MainFrame frame) {
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
		
		  treeModel.insertNodeInto(node6, root3, root3.getChildCount());
		  
		  
		  
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
		  tree_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					 //JPanel[] panels={configurationPanel,stopSettingPanel,populationPanel,genePanel,functionPanel,inputFilePanel,outputPanel,panel_0,footPanel,treePanel};
					 int selRow = tree_1.getRowForLocation(e.getX(), e.getY());
				      TreePath selPath = tree_1.getPathForLocation(e.getX(), e.getY());
				      if (selRow != -1)
				      {
				          if (e.getClickCount() == 1)
				          {
				          	TreeNode node = (TreeNode) selPath.getLastPathComponent();
				          	if(node.toString()=="�㷨ϵͳ"){
				          		frame.panels[0].setVisible(true);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(false);
				          		frame.footPanel.btnNext.setEnabled(true);
				          		frame.count=0;
				          	}
				          	if(node.toString()=="�㷨����"){
				          		frame.panels[0].setVisible(true);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(true);
				          		frame.footPanel.btnNext.setEnabled(true);
				          		frame.count=0;
				          	}
				          	if(node.toString()=="�㷨��ֹ����"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(true);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(false);
				          		frame.footPanel.btnNext.setEnabled(true);
				          		frame.count=1;
				          	
				          	}
				          	if(node.toString()=="��Ⱥ��Ϣ"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(true);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(true);
				          		frame.footPanel.btnNext.setEnabled(true);
				          		frame.count=2;
				          	
				          	}
				          	if(node.toString()=="������Ϣ"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(true);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(true);
				          		frame.footPanel.btnNext.setEnabled(true);
				          		frame.count=3;
				          		
				          	}
				          	if(node.toString()=="���躯���������"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(true);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(true);
				          		frame.panels[8].setVisible(true);
				          		frame.panels[9].setVisible(true);
				          		
				          		//footPanel��btnBefroe��btnNext
				          		frame.footPanel.btnBefore.setEnabled(true);
				          		frame.footPanel.btnNext.setEnabled(false);
				          		frame.count=4;
				          	}
				          	if(node.toString()=="����ϵͳ"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(true);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(false);
				          		frame.panels[8].setVisible(false);
				          		frame.panels[9].setVisible(true);
				          	} 
				          	
				          	if(node.toString()=="����·��"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(true);
				          		frame.panels[6].setVisible(false);
				          		frame.panels[7].setVisible(false);
				          		frame.panels[8].setVisible(false);
				          		frame.panels[9].setVisible(true);
				          	}
				          	if(node.toString()=="���ϵͳ"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(true);
				          		frame.panels[7].setVisible(false);
				          		frame.panels[8].setVisible(false);
				          		frame.panels[9].setVisible(false);
				          	}  
				          	if(node.toString()=="������"){
				          		frame.panels[0].setVisible(false);
				          		frame.panels[1].setVisible(false);
				          		frame.panels[2].setVisible(false);
				          		frame.panels[3].setVisible(false);
				          		frame.panels[4].setVisible(false);
				          		frame.panels[5].setVisible(false);
				          		frame.panels[6].setVisible(true);
				          		frame.panels[7].setVisible(false);
				          		frame.panels[8].setVisible(false);
				          		frame.panels[9].setVisible(false);
				          	}  
				          } 
				      }
					
				}
			});
	}

}
