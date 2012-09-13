package ui;

import javax.swing.JTabbedPane;

/**
 *
 * @author Michael Hagen
 */
public interface IDemoApp {
    public GUIProperties getGuiProps();
    public void setMainTabbedPane(JTabbedPane tabPane);
    public JTabbedPane getMainTabbedPane();
    public void updateLookAndFeel(String lf);
    public void updateTheme(String theme);
    public void setTextAntiAliasing(boolean taa);
    public void setBackgroundPattern(boolean pattern);
    public void performExit();
}
