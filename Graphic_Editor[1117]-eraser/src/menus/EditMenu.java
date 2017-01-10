package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants.EEditMenu;

public class EditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public EditMenu(){
		super("Edit"); 
		
		for(EEditMenu eEditMenu : EEditMenu.values()){
			JMenuItem item = new JMenuItem(eEditMenu.getValue());
			this.add(item);
			this.addSeparator();
		}
		
	}
}
