package ui.alginputdataprocess.controller;

import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreePanelController {
   public static int  treeMouseListener(MouseEvent e,JPanel[] panels,JButton btnBefore,JButton btnNext,int count){
	   JTree tree = (JTree) e.getSource();
       int selRow = tree.getRowForLocation(e.getX(), e.getY());
      TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
      if (selRow != -1)
      {
          if (e.getClickCount() == 1)
          {
          	TreeNode node = (TreeNode) selPath.getLastPathComponent();
          	if(node.toString()=="�㷨ϵͳ"){
          		panels[0].setVisible(true);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(false);
          		btnNext.setEnabled(true);
          		count=0;
          	}
          	if(node.toString()=="�㷨����"){
          		panels[0].setVisible(true);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(true);
          		btnNext.setEnabled(true);
          		count=0;
          	}
          	if(node.toString()=="�㷨��ֹ����"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(true);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(true);
          		btnNext.setEnabled(true);
          		count=1;
          	}
          	if(node.toString()=="��Ⱥ��Ϣ"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(true);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(true);
          		btnNext.setEnabled(true);
          		count=2;
          	}
          	if(node.toString()=="������Ϣ"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(true);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(true);
          		btnNext.setEnabled(true);
          		count=3;
          	}
          	if(node.toString()=="���躯���������"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(true);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(true);
          		panels[9].setVisible(true);
          		btnBefore.setEnabled(true);
          		btnNext.setEnabled(false);
          		count=4;
          	}
          	if(node.toString()=="����ϵͳ"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(true);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(false);
          		panels[9].setVisible(false);
          	} 
          	if(node.toString()=="�ϴ�/���ؽӿ�"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(true);
          		panels[6].setVisible(false);
          		panels[7].setVisible(false);
          		panels[8].setVisible(false);
          		panels[9].setVisible(false);
          	} 
          	if(node.toString()=="����·��"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(true);
          		panels[7].setVisible(false);
          		panels[8].setVisible(false);
          		panels[9].setVisible(false);
          	}
          	if(node.toString()=="���ϵͳ"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[8].setVisible(false);
          	    panels[9].setVisible(false);
          		panels[7].setVisible(true);
          		panels[10].setVisible(false);
          	}  
          	if(node.toString()=="������"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(false);
          		panels[6].setVisible(false);
          		panels[8].setVisible(false);
          	    panels[9].setVisible(false);
          		panels[7].setVisible(true);
          		panels[10].setVisible(false);
          	}  
          } 
      }
	return count;

   }
}
