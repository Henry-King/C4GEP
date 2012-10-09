package ui;



import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JProgressBar_1 extends JFrame {

	private static final long serialVersionUID = 6823378424400186364L;
	
	static JProgressBar_1 frame;

	public JProgressBar_1() {
		// TODO Auto-generated constructor stub
		JLabel label = new JLabel("欢迎使用在线升级功能！");
		JProgressBar progressBar = new JProgressBar();
		JButton button = new JButton("完成");
		button.setEnabled(false);
		Container container = getContentPane();
		container.setLayout(new GridLayout(3, 1));
		JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel3= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(label);
		panel2.add(progressBar);
		panel3.add(button);
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		
		progressBar.setStringPainted(true);
		progressBar.setString("升级进行中...");
//		progressBar.setIndeterminate(true);
		new Progress(progressBar, button).start();
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				System.exit(0);
			}
			
		});
	}
	
	private class Progress extends Thread {
		JProgressBar progressBar;
		JButton button;
		int[] progressValues = {6, 18, 27, 39, 51, 66, 81, 100};
		
		Progress(JProgressBar progressBar, JButton button) {
			this.progressBar = progressBar;
			this.button = button;
		}
		
		public void run() {
			for (int i = 0; i < progressValues.length; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				progressBar.setValue(progressValues[i]);
			}
			progressBar.setIndeterminate(false);
			progressBar.setString("升级完成！");
			button.setEnabled(true);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JProgressBar_1();
		frame.setTitle("使用进度条");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

}
