package ui.alginputdataprocess.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import exception.Duplicated;

import ui.algconfiguration.model.ConfigModel;
import ui.algconfiguration.model.FunctionModel;
import ui.algconfiguration.model.GeneModel;
import ui.algconfiguration.model.PopulationModel;
import ui.alginputdataprocess.controller.GepConfiguration;
import ui.alginputdataprocess.controller.IgepAlgService;
import ui.alginputdataprocess.controller.JPanelForFooter;
import ui.alginputdataprocess.controller.ModelForJPanelInputPath;


public class FooterView extends JPanel {
	
	public JButton btnNext = new JButton("��һ�� >");
    public JButton btnBefore = new JButton("< ��һ��");
    public JButton btnRun = new JButton("\u6267\u884C\u7B97\u6CD5");
    
    MainFrame parent;
	public FooterView(MainFrame parent) {
		this.parent = parent;
	      setBorder(new LineBorder(new Color(0, 0, 0)));
	      setBackground(Color.WHITE);
	      setBounds(160, 523, 669, 39);
	      setLayout(null);
	      btnNext.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		//btnNextController(DefaultMutableTreeNode[] node,JTree tree_1,JPanelForFooter footPanel,JPanel panel_0,CardLayout card,int count)
	      		//count���ڱ��cardlayout�����ڼ������
	      		footPanel.btnBefore.setEnabled(true);
	      		card.next(panel_0);
	      		switch(count){
	      		  case 0:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[1]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("��-��Node"+node[1].toString());
	        		break; 
	      		  }
	      		  case 1:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[2]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("�ڶ���Node"+node[2].toString());
	        		break;
	      		  }
	      		  case 2:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("������Node"+node[3].toString());
	        		break;
	      		  }
	      		  case 3:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[4]));
	        		tree_1.setSelectionPath(visiblePath);
	        	    System.out.println("���ĸ�Node"+node[4].toString());
	        		break;
	      		  }
	      		 default:{
	      			break;
	      			
	      		  }
	      		}
	      		if(count==3){
	      			footPanel.btnNext.setEnabled(false);
	      			footPanel.btnRun.setEnabled(true);
	      		}
	      		if(count==4){
	      			return count;
	      		}
	      		count++;
	      		System.out.println(count);
	      		return count;
	      	}
	      });
	      	      
	      btnNext.setBounds(523, 10, 93, 23);
	      add(btnNext);
	      btnBefore.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		//btnBeforeController(DefaultMutableTreeNode node[],JTree tree_1,JButton  btnNext,JButton btnBefore,JPanel panel_0,CardLayout card,int count)
	      		count--;
	      		if(count==0){
	      			btnBefore.setEnabled(false);
	      		}
	      			btnNext.setEnabled(true);
	      		
	      		System.out.println(count);
	      		switch(count){
	      		  case 0:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[0]));
	        		tree_1.setSelectionPath(visiblePath);
	        	    System.out.println("��0��Node"+node[0].toString());
	        		break;
	      		  }
	      		  case 1:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[1]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("��1��Node"+node[1].toString());
	        		break;
	      		  }
	      		  case 2:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[2]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("��2��Node"+node[2].toString());
	        		break;
	      		  }
	      		  case 3:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("��3��Node"+node[3].toString());
	        		break;
	      		  }
	      		 default:{
	      			TreePath visiblePath=new TreePath(((DefaultTreeModel)tree_1.getModel()).getPathToRoot(node[3]));
	        		tree_1.setSelectionPath(visiblePath);
	        		System.out.println("��3��Node"+node[3].toString());
	        		break;
	      		  }
	      		}
	      		card.previous(panel_0);
	      		return count;
	      	}
	      });
	      
	      
	      btnBefore.setEnabled(false);
	      
	      btnBefore.setBounds(105, 10, 93, 23);
	      add(btnBefore);
	      btnRun.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		//���г���
	      		/*btnRunController(ModelForJPanelConfig configuration,ModelForJPanelInputPath inputPath,
	      			   ModelForJPanelPopulation population,ModelForJPanelGEne gene,ModelForJPanelFunction function,
	      			   GepConfiguration myParameter,IgepAlgService myGepService,GepConfiguration myConfigurationFromDB,int flag)*/
	      		myParameter.setInputFile(inputPath.getInputPath());
	    		myParameter.setName(configuration.getConfig());
	    		myParameter.setAccuray(inputPath.getAccuracy());
	    		myParameter.setNormalGeneNumber(gene.getNormalGeneNumber());
	    		myParameter.setGeneRecombineRate(gene.getGeneRecombineRate());
	    		myParameter.setGeneTransportRate(gene.getGeneTransportRate());
	    		myParameter.setNormalHeaderLength(gene.getNormalHeaderLength());
	    		myParameter.setIsTransportRate(gene.getIsTransportRate());
	    		myParameter.setMaxGeneration(inputPath.getMaxgeneration());
	    		myParameter.setOnePointRecombineRate(gene.getGeneOnePointRecombineRate());
	    		myParameter.setPopulationSize(population.getPopulationSize());
	    		myParameter.setRisTransportRate(gene.getRisTransportRate());
	    		myParameter.setSelectionRange(population.getSelectionRange());
	    		myParameter.setTwoPointRecombineRate(gene.getTwoPointRecombineRate());
	    		myParameter.setIsElement(gene.getIsElement());
	            myParameter.setRisElement(gene.getRisElement());
	    		myParameter.setHomeoticGeneNumber(gene.getHomeoticGeneNums());
	    	    myParameter.setHomeoticHeaderLength(gene.getHomeoticHeaderLength());
	    		myParameter.setFunctionList(function.getFunctions().toString());
	    		myParameter.setMutateRate(gene.getMutateRate());

	    				
	    	    try {
	    	    	System.out.println(myGepService.getAvailableSelector().get(population.getSelectionStrategyIndex()).getClass().getName());
	    			myParameter.setSelector(myGepService.getAvailableSelector().get(population.getSelectionStrategyIndex()).getClass().getName());
	    			myParameter.setCalculator(myGepService.getAvailableCalculator().get(population.getAvailableCalculatorIndex()).getClass().getName());
	    			myParameter.setCreator(myGepService.getAvailableCreator().get(population.getPopulationCreatorIndex()).getClass().getName());
	    			myParameter.setModify(myGepService.getAvailableModifyings().get(gene.getAvailableModifyingsIndex()).getClass().getName());
	    			
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
	           
	           
	           
	           if (flag == -1) {
	    				if (myParameter.equals(myConfigurationFromDB) == false) {
	    					return true;
	    					}
	    				else{
	    					return false;
	    				}
	    		} 
	    		else {
	    				try {
	    					myGepService.saveArgumentsToDb(myParameter);
	    				} catch (Duplicated e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				return true;
	    				
	    		}
	      	}
	      });
	      
	      
	      btnRun.setBounds(312, 10, 93, 23);
	      add(btnRun);
	}

}
