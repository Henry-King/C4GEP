package ui.conf.model;

import javax.swing.JLabel;

public abstract class MyBaseLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1688314970617839782L;
	public MyBaseLabel() {
		// TODO Auto-generated constructor stub
		this("");
	}
	public MyBaseLabel(String text){
		this(text,LEFT);
	}
	public MyBaseLabel(String text,int aliagnment){
		super(text, aliagnment);
	}
}
