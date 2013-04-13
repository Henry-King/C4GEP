package ui.conf;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ui.app.JTBorderFactory;
import ui.app.MainWnd;
import ui.images.ImageHelper;

public class NewGEPDialog2 extends JDialog {


	DefaultMutableTreeNode root = new DefaultMutableTreeNode("��Ŀ���");
	DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("���п�� ");
	DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("���п��");
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
	JTree tree_1 ;
	JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	JList<String> listForParr = new JList<String>();
	JList<String> listForSer=new JList<String>();
	JTextArea introduction = new JTextArea();
	JPanel serr_Panel = new JPanel();
	JPanel parr_Panel = new JPanel();
	JPanel introduction_Panel = new JPanel();
	JPanel button_Panel = new JPanel();
	JPanel top_Panel = new JPanel();
	JPanel center_Panel = new JPanel();
	
	
	public NewGEPDialog mainDialog;
	public NewGEPDialog pre;
	public NewGEPDialog2 cur;
	public NewGEPDialog3 late;
	
	public MainWnd mainWnd;
	
	public NewGEPDialog2(final MainWnd mainWnd,NewGEPDialog step1) {
		super(JOptionPane.getFrameForComponent(mainWnd.frame), "Configuration Parameter", true);
		this.setSize(800, 600);
		this.mainWnd = mainWnd;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(mainWnd.frame);
		this.setResizable(false);
		pre = step1;
		mainDialog = step1;
		cur = this;
		addWindowListener(new WindowAdapter() {
	 		@Override
	 		public void windowClosing(WindowEvent arg0) {
	 			if (late!=null) {
					late.dispose();
				}
	 		}
	 	});
		
		JPanel project_Panel = new JPanel();
		project_Panel.setBackground(Color.WHITE);
		project_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("home.gif"),"Framework Type",0, 0));
		getContentPane().add(project_Panel, BorderLayout.WEST);
		initIndexTree();
		project_Panel.add(scrollPane);
		
		{
			serr_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Detail Item", 0, 0));
			getContentPane().add(serr_Panel, BorderLayout.CENTER);
			serr_Panel.setBackground(Color.WHITE);
			serr_Panel.setLayout(new BorderLayout(0, 0));
			serr_Panel.setVisible(true);
			initListSerr();
			serr_Panel.add(listForSer);
		}
		
		{
			parr_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Iarameter", 0, 0));
			parr_Panel.setLayout(new BorderLayout(0, 0));
			parr_Panel.setVisible(true);
			initListParr();
			parr_Panel.add(listForParr);

		}
		{
			introduction_Panel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("paste.gif"),"Iarameter",0, 0));
			getContentPane().add(introduction_Panel, BorderLayout.EAST);
			introduction_Panel.setLayout(new BorderLayout(0, 0));
			initIntroductionPanel();
		}
		{
			initButtonPanel();
		}
		
		
		
		
		
	}
	private void initListParr() {
		//listForParr.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForParr.setBackground(Color.WHITE);
		listForParr.setSelectedIndex(0);
		listForParr.setFont(new Font("����", Font.PLAIN, 16));
		listForParr.setListData(new String[]{"����ʽ","���ʽ","������","ϸ����"});
		listForParr.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    updateIntroduction();
                    pre.getData().setFrameworkType((String)listForParr.getSelectedValue());
                }
            }
        });
		
	}
	private void initListSerr() {
		//listForSer.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		listForSer.setBackground(Color.WHITE);
		listForSer.setSelectedIndex(0);
		listForSer.setFont(new Font("����", Font.PLAIN, 16));
		listForSer.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                   updateIntroduction();
                   pre.getData().setFrameworkType((String)listForSer.getSelectedValue());
                }
            }
            
            
            
        });
		listForSer.setListData(new String[]{"����ʽ"});
		introduction.setText("����ʽ");
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
	
		
		
		
		
		/*��ť���*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(800, 48));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        
        
        
        //buttonPanel.setLayout(new BorderLayout(0, 0));
        
        
     
        
        buttonPanel.setLayout(null);
        buttonPanel.setSize(800, 48);
        //buttonPanel.setBounds(0, 424, 550, 48);
        buttonPanel.setVisible(true);
        
        
        
        JButton btn_Back = new JButton("< Back");
        btn_Back.setContentAreaFilled(false);
        btn_Back.setBounds(327, 10, 100, 30);
        btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		pre.setVisible(true);
            		cur.setVisible(false);
            }
        });
        
        JButton btn_Next = new JButton("Next >");
        btn_Next.setContentAreaFilled(false);
        //btn_Next.setSize(new Dimension(100, 30));
        btn_Next.setBounds(437, 10, 100, 30);
        btn_Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (late==null) {
            		late = new NewGEPDialog3(mainWnd,cur);
				}
            	
            	cur.setVisible(false);
            	late.setVisible(true);
            }
        });
        
        
        JButton btn_Finish = new JButton("Finish");
        btn_Finish.setContentAreaFilled(false);
        //btn_Finish.setSize(new Dimension(100, 30));
        btn_Finish.setBounds(577, 10, 100, 30);
        btn_Finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/*if (JTattooUtilities.getJavaVersion() >= 1.6) {
            		JTabbedPane jtp = mainWnd.mainTabbedPane;
            		//String title = txt_projectName.getText().toString();
                    int tabCount = jtp.getTabCount();
                    jtp.add("Tab", new ConfPanel());
                    jtp.setTabComponentAt(tabCount, new CloseableTabComponent(jtp,title));
                    jtp.setToolTipTextAt(tabCount, "This is tab No. " + (tabCount + 1));
                }
            	*/
            	
            }
        });
        
        
        
        
        JButton btn_Cancel = new JButton("Cancel");
       // btn_Cancel.setSize(new Dimension(100, 30));
        btn_Cancel.setContentAreaFilled(false);
       btn_Cancel.setBounds(687, 10, 100, 30);
        btn_Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
        buttonPanel.add(new Label());
        buttonPanel.add(new Label());
        buttonPanel.add(new Label());
        buttonPanel.add(new Label());
        buttonPanel.add(btn_Back);
        buttonPanel.add(btn_Next);
        buttonPanel.add(btn_Finish);
        buttonPanel.add(btn_Cancel);
        
        
        
	}
	
	
	private void updateIntroduction() {
		final String frameName= (String)listForParr.getSelectedValue();
		if(frameName!=null){
			if(frameName.equals("����ʽ")){
				introduction.setText("����ʽ");
			}
			else if(frameName.equals("������")){
				introduction.setText("������");
			}
			else if(frameName.equals("ϸ����")){
				introduction.setText("ϸ����");
			}
			else if(frameName.equals("���ʽ")){
				introduction.setText("���ʽ");
			}
		}
		
	}
	private void initIndexTree() {
		//��
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
				          	
				          	if((node.toString()).equals("���п��")){
				          		getContentPane().add(parr_Panel, BorderLayout.CENTER);
				          	    parr_Panel.setVisible(true);
				          	    serr_Panel.setVisible(false);
				          	    introduction.setText("����ʽ");
				          	}
				          	else{
				          		System.out.print("������"+node.toString());
				          		getContentPane().add(serr_Panel, BorderLayout.CENTER);
				          		 serr_Panel.setVisible(true);
					          	 parr_Panel.setVisible(false);
					          	introduction.setText("����ʽ");
				          	}
				          }
				     }
				}
		 });
	 }
}
