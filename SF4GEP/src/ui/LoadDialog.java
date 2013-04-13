package ui;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class LoadDialog extends javax.swing.JDialog {
 private JPanel outer;
 private JLabel one;
 private JLabel three;
 private JPasswordField pw3;
 private JPasswordField pw2;
 private JPasswordField pw1;
 private JLabel two;

 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   public void run() {
    LoadDialog inst = new LoadDialog(null);
    inst.setResizable(false);
    inst.setLocationRelativeTo(null);
    inst.setVisible(true);
   }
  });
 }
 
 public LoadDialog(JFrame frame) {
  super(frame);
  initGUI();
 }
 
 private void initGUI() {
  try {
   setTitle("�޸����룺");
   getContentPane().setLayout(null);
   {
    outer = new JPanel();
    getContentPane().add(outer);
    outer.setBounds(41, 34, 313, 194);
    outer.setBorder(BorderFactory.createTitledBorder("�޸���Ϣ:"));
    outer.setLayout(null);
    {
     one = new JLabel();
     outer.add(one);
     one.setText("����ԭ����:");
     one.setBounds(44, 56, 69, 15);
    }
    {
     two = new JLabel();
     outer.add(two);
     two.setText("����������:");
     two.setBounds(44, 90, 69, 15);
    }
    {
     three = new JLabel();
     outer.add(three);
     three.setText("�ظ�ԭ����:");
     three.setBounds(44, 126, 69, 15);
    }
    {
     pw1 = new JPasswordField();
     outer.add(pw1);
     pw1.setText("");
     pw1.setBounds(119, 52, 133, 22);
    }
    {
     pw2 = new JPasswordField();
     outer.add(pw2);
     pw2.setText("");
     pw2.setBounds(119, 86, 133, 22);
    }
    {
     pw3 = new JPasswordField();
     outer.add(pw3);
     pw3.setText("");
     pw3.setBounds(119, 122, 133, 22);
    }
   }
   setSize(400, 300);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

}