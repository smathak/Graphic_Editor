package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants.EFileMenu;

public class FileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	//constructor
	public FileMenu() {
		super("File");
		
		for(EFileMenu eFileMenu : EFileMenu.values()){
			JMenuItem item = new JMenuItem(eFileMenu.getValue());
			this.add(item);
			this.addSeparator();
		}
		
	}
}
