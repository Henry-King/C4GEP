package ui.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import ui.conf.view.ProjectTabPane;
import ui.images.ImageHelper;

public class CloseableTabComponent extends JPanel {
    private static ImageIcon closerImage = ImageHelper.loadImage("closer.gif");
    private static ImageIcon closerRolloverImage = ImageHelper.loadImage("closer_rollover.gif");
    private static ImageIcon closerPressedImage = ImageHelper.loadImage("closer_pressed.gif");
    private JLabel titleLabel = null;
    private JButton closeButton = null; 
    private JTabbedPane tabbedPane = null;

	public CloseableTabComponent(JTabbedPane aTabbedPane,String title) {
        super(new BorderLayout());
        tabbedPane = aTabbedPane;
        setFont(new Font("Courier",Font.PLAIN,14));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
        
        titleLabel = new JLabel(title);
        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font("Courier",Font.PLAIN,14));
        titleLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
        
        closeButton = new JButton(closerImage);
        closeButton.setRolloverIcon(closerRolloverImage);
        closeButton.setPressedIcon(closerPressedImage);
        closeButton.setBorderPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFocusPainted(false);
        closeButton.setRolloverEnabled(true);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setPreferredSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
        closeButton.setSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                	JPanel panel = (JPanel) tabbedPane.getComponentAt(i);
                	if (panel.getName().equals("FittingCurveDetail")) {
                		ProjectTabPane projectTabPane = (ProjectTabPane) tabbedPane;
                		projectTabPane.confPanel.setHasFittingCurve(false);
					}else if (panel.getName().equals("EvolutionDetail")) {
						ProjectTabPane projectTabPane = (ProjectTabPane) tabbedPane;
                		projectTabPane.confPanel.setHasEvolution(false);
					}
                    if (CloseableTabComponent.this.equals(tabbedPane.getTabComponentAt(i))) {
                        tabbedPane.removeTabAt(i);
                        break;
                    }
                }
            }
        });
        
        add(titleLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.EAST);
    }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
