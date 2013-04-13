package ui.conf;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.app.LinkLabel;
import ui.images.ImageHelper;

public class WelcomeContent_Help extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomeContent_Help() {
		setBorder(new EmptyBorder(5, 2, 5, 2));
		setSize(648,179);
		JList list = new JList();
		
		JLabel lblNewLabel = new JLabel("Documentation");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(176, 196, 222));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lblNewLabel_1 = new JLabel(ImageHelper.loadImage("help.png"));
		
		LinkLabel lblNewLabel_2 = new LinkLabel("Help Index","");
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 13));
		
		JLabel lblSelectHelpTopics = new JLabel("Selected help topics:");
		lblSelectHelpTopics.setFont(new Font("Constantia", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3 = new JLabel("Creating project with prifile",ImageHelper.loadImage("smallhelp.png"), SwingConstants.CENTER );
		lblNewLabel_3.setForeground(new Color(0, 0, 205));
		lblNewLabel_3.setFont(new Font("Constantia", Font.PLAIN, 13));
		
		JLabel lblHowToBuild = new JLabel("How to build a GEP framework", ImageHelper.loadImage("smallhelp.png"), SwingConstants.CENTER);
		lblHowToBuild.setForeground(new Color(0, 0, 205));
		lblHowToBuild.setFont(new Font("Constantia", Font.PLAIN, 13));
		
		JLabel lblConnectToUs = new JLabel("Connect to us", ImageHelper.loadImage("smallhelp.png"), SwingConstants.CENTER);
		lblConnectToUs.setForeground(new Color(0, 0, 205));
		lblConnectToUs.setFont(new Font("Constantia", Font.PLAIN, 13));
		
		JLabel lblGiveAdvise = new JLabel("Give advise", ImageHelper.loadImage("smallhelp.png"), SwingConstants.CENTER);
		lblGiveAdvise.setForeground(new Color(0, 0, 205));
		lblGiveAdvise.setFont(new Font("Constantia", Font.PLAIN, 13));
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE))
						.addComponent(list)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewLabel_1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblConnectToUs, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
											.addGap(71)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblGiveAdvise, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHowToBuild, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
										.addComponent(lblSelectHelpTopics, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSelectHelpTopics, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblHowToBuild, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConnectToUs, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGiveAdvise, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(102))
		);
		setLayout(groupLayout);
		
		
		

	}
}
