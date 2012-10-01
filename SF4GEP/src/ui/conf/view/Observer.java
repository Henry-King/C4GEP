package ui.conf.view;

import ui.conf.model.Model;

public interface Observer {
	public void dataUpdate(Model model);
}
