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
          	if(node.toString()=="�㷨����"){
          		panels[0].setVisible(true);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="��������·��"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(true);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="��Ⱥ��Ϣ"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(true);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="������Ϣ"){
          		//txtNormalGeneNumber.grabFocus();
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(true);
          		panels[4].setVisible(false);
          	}
          	if(node.toString()=="���躯���������"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(true);
          	}
          	if(node.toString()=="�ϴ��ӿ�"){
          		panels[0].setVisible(false);
          		panels[1].setVisible(false);
          		panels[2].setVisible(false);
          		panels[3].setVisible(false);
          		panels[4].setVisible(false);
          		panels[5].setVisible(true);
          	}
              //����
          } else if (e.getClickCount() == 2)
          {
          	
          }
      }

   }
}
