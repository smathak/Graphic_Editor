package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants.EHelpMenu;

public class HelpMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	public HelpMenu() {
		super("Help");
		
		for(EHelpMenu eHelpMenu : EHelpMenu.values()){
			JMenuItem item = new JMenuItem(eHelpMenu.getValue());
			this.add(item);
			this.addSeparator();
		}
	}
}
