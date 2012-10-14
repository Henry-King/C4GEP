package ui.conf;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javassist.tools.framedump;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.multi.*;

import ui.app.*;

import com.jtattoo.plaf.JTattooUtilities;

import data.dao.HibernateDataContext;
import domain.core.algInputDataProcess.DataColumn;
import domain.core.algInputDataProcess.DataRow;
import domain.core.algInputDataProcess.DataSet;

import ui.app.MainFrame;
import ui.app.MainWnd;
import ui.app.VTextIcon;
import ui.conf.controller.InputDataController;
import ui.conf.model.NewProjectModel;
import ui.conf.view.ConfPanel;
import ui.images.ImageHelper;

public class NewGEPDialog extends JDialog {



	private static final long serialVersionUID = 3843396641685701638L;
	private MainWnd mainWnd;
	private NewGEPDialog cur;
	private NewGEPDialog2 late;
	private HibernateDataContext hibernateDataContext;
	private NewProjectModel data = new NewProjectModel();
	private final JTextField txt_projectName;
	public NewGEPDialog(String title,final MainWnd mainWnd) {
		 	super(mainWnd.frame, title, false);
		 	
		 	this.mainWnd = mainWnd;
		 	this.setResizable(false);
		 	cur = this;
		 	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        setSize(550, 500);
	        setLocationRelativeTo(mainWnd.frame);
	        getContentPane().setLayout(new BorderLayout());
	        
	        
	        
            
            /*主内容面板*/
            JPanel contentPanel = new JPanel();
    		contentPanel.setLayout(null);
    		
    		
    		/*Logo Content*/
    		JLabel logoLabel = new JLabel(ImageHelper.loadImage("NewProjectLogo.jpg"));
            logoLabel.setBounds(0, 0, 550, 100);
            logoLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
            
            JLabel logoTipLabel = new JLabel("Please enter a project name and a input path");
            logoTipLabel.setFont(new Font("Century", Font.PLAIN, 14));
            
            logoTipLabel.setBounds(31, 48, 385, 24);
            
            
            
            /*Project Content*/
            JLabel lbl_projectName = new JLabel("Project Name:");
    		lbl_projectName.setBounds(31, 128, 100, 30);
    		lbl_projectName.setFont(new Font("宋体", Font.PLAIN, 13));
    		
    		
    		txt_projectName = new JTextField();
    		txt_projectName.addFocusListener(new FocusAdapter() {
    			@Override
    			public void focusLost(FocusEvent arg0) {
    				data.setProfileName(txt_projectName.getText());
    			}
    		});
    		txt_projectName.setBounds(139, 132, 345, 24);
    		
    		txt_projectName.setColumns(25);
    		txt_projectName.setMaximumSize(new Dimension(126, 21));
            
            
            
    		JLabel lbl_savePath = new JLabel("Save Path:");
    		lbl_savePath.setFont(new Font("宋体", Font.PLAIN, 13));
    		lbl_savePath.setBounds(31, 168, 100, 30);
            
            
            final JTextField txt_savePath = new JTextField();
            txt_savePath.addFocusListener(new FocusAdapter() {
            	@Override
            	public void focusLost(FocusEvent e) {
            		data.setProjectPath(txt_savePath.getText());
            	}
            });
            txt_savePath.setMaximumSize(new Dimension(126, 21));
            txt_savePath.setColumns(25);
            txt_savePath.setBounds(111, 172, 289, 24);
            
            
            JButton btn_browsePath = new JButton("Browse...");
            btn_browsePath.setContentAreaFilled(false);
            btn_browsePath.setFont(new Font("Calibri", Font.PLAIN, 14));
            btn_browsePath.setBounds(419, 170, 100, 28);
            
            
            
            
            
            
            /*按钮面板*/
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBounds(0, 424, 550, 48);
            
            JButton btn_Back = new JButton("< Back");
            btn_Back.setEnabled(false);
            btn_Back.setContentAreaFilled(false);
            btn_Back.setBounds(67, 10, 100, 30);
            btn_Back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //setTitle("New Title");
                }
            });
            
            JButton btn_Next = new JButton("Next >");
            btn_Next.setContentAreaFilled(false);
            btn_Next.setBounds(177, 10, 100, 30);
            btn_Next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if (late==null) {
                		late = new NewGEPDialog2(mainWnd,cur);
					}
                	cur.setVisible(false);
                	late.setVisible(true);
                	
                	
                }
            });
            
            
            JButton btn_Finish = new JButton("Finish");
            btn_Finish.setContentAreaFilled(false);
            btn_Finish.setBounds(317, 10, 100, 30);
            btn_Finish.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	loadNewProject();
                }
            });
            
            
            
            
            JButton btn_Cancel = new JButton("Cancel");
            btn_Cancel.setContentAreaFilled(false);
            btn_Cancel.setBounds(427, 10, 100, 30);
            btn_Cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            
            
            
            
            
            buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
            buttonPanel.setLayout(null);
            
            buttonPanel.add(btn_Back);
            buttonPanel.add(btn_Next);
            buttonPanel.add(btn_Finish);
            buttonPanel.add(btn_Cancel);
            
            
            
            
            
            contentPanel.add(logoTipLabel);
            contentPanel.add(logoLabel);
            contentPanel.add(lbl_projectName);
            contentPanel.add(txt_projectName);
            contentPanel.add(lbl_savePath);
            contentPanel.add(txt_savePath);
            contentPanel.add(btn_browsePath);
            contentPanel.add(buttonPanel);
            setContentPane(contentPanel);
            setVisible(true);
            
            
            
            
	 }
	public void loadNewProject(){
		if (JTattooUtilities.getJavaVersion() >= 1.6) {
    		JTabbedPane jtp = mainWnd.frame.mainTabbedPane;
    		String title = txt_projectName.getText().toString();
            int tabCount = jtp.getTabCount();
            ConfPanel newConfPanel = new ConfPanel(mainWnd);
            VTextIcon newVTextIcon=new VTextIcon(newConfPanel, title,VTextIcon.ROTATE_LEFT);
            //jtp.addTab(null,newVTextIcon, newConfPanel, null);
            //jtp.addTab("Tab", null,welcomeVTextIcon,null);
           
            //CloseableTabComponent ctc = new CloseableTabComponent(jtp,newVTextIcon);
            //ctc.setSize(new Dimension(14, 14));
            //jtp.add(newConfPanel,tabCount);
            jtp.addTab(null,newVTextIcon, newConfPanel, null);
            jtp.setSelectedIndex(tabCount);
            //jtp.setTabComponentAt(tabCount, ctc);
            jtp.setToolTipTextAt(tabCount, "This is project " + (tabCount + 1) + "  "+title);
            
            
            if (cur.late!=null&&cur.late.late!=null) {
            	String inputPath = cur.late.late.txt_InputPath.getText();
            	if (inputPath.equals("")||inputPath.equals(null)) {
            		//未设置输入数据集路径
				}else{
					data.setInputDataPath(inputPath);
                    hibernateDataContext = mainWnd.getHibernateDataContext();
                    InputDataController inputDataController = new InputDataController(hibernateDataContext);
                    DataSet inputDataSet = inputDataController.getInputSet(inputPath);
                    data.setInputDataSet(inputDataSet);
                    System.out.println("inputdata");
                    List<DataRow> rows = inputDataSet.getDataRows();
                    for (int i = 0; i < rows.size()-1; i++) {
						DataRow row = rows.get(i);
						List<DataColumn> dc =row.getDataColumns();
						
						for (int j = 0; j < dc.size(); j++) {
							System.out.print(dc.get(j).getValue()+ " ");
						}
						DataColumn dcr = row.getResultColumn();
						System.out.println(dcr.getValue());
						System.out.println();
					}
                    
                    newConfPanel.setNewProjectModel(data);	//为新建算法项目设置配置文件
				}
                
			}
            
            
            
            
            
            cur.dispose();
            if (late!=null) {
            	if (late.late!=null) {
    				late.late.dispose();
    			}
				late.dispose();
			}
            
        }
		
	}
	
	
	
	
	
	
	
	
	
	
	


	public NewProjectModel getData() {
		return data;
	}


	public void setData(NewProjectModel data) {
		this.data = data;
	}
}
