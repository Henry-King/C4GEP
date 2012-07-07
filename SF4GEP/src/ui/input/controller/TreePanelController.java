package ui.input.controller;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreePanelController {
   public static void  treeMouseListener(MouseEvent e,JPanel[] panels){
	   JTree tree = (JTree) e.getSource();
       int selRow = tree.getRowForLocation(e.getX(), e.getY());
      TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
      if (selRow != -1)
      {
          if (e.getClickCount() == 1)
          {
          	TreeNode node = (TreeNode) selPath.getLastPathComponent();
          	if(node.toString()=="算法名称"){
          		panels[0].setVisible(true);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="数据输入路径"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(true);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="种群信息"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(true);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="基因信息"){
          		//txtNormalGeneNumber.grabFocus();
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(true);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="所需函数和随机数"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(true);
          	}
          	if(node.toString()=="上传接口"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(true);
          	}
              //单击
          } else if (e.getClickCount() == 2)
          {
          	
          }
      }

   }
}
