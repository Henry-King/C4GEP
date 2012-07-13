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
          	if(node.toString()=="算法系统"){
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
          	if(node.toString()=="算法名称"){
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
          	if(node.toString()=="算法终止条件"){
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
          	if(node.toString()=="种群信息"){
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
          	if(node.toString()=="基因信息"){
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
          	if(node.toString()=="所需函数和随机数"){
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
          	if(node.toString()=="输入系统"){
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
          	if(node.toString()=="上传/下载接口"){
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
          	if(node.toString()=="输入路径"){
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
          	if(node.toString()=="输出系统"){
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
          	if(node.toString()=="输出结果"){
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
