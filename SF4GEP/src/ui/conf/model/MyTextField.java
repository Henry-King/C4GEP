package ui.conf.model;

import javax.swing.JTextField;

public final class MyTextField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7133548073099875505L;
	public MyTextField() {
		// TODO Auto-generated constructor stub
		super(null, null, 12);
		setMinimumSize(getPreferredSize());
		setHorizontalAlignment(LEFT);
	}
	

}
