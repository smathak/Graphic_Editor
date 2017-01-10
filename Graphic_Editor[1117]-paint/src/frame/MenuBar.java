package frame;

import javax.swing.JMenuBar;

import menus.EditMenu;
import menus.FileMenu;
import menus.HelpMenu;
import menus.ImageMenu;
import menus.ViewMenu;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	//constructor
	MenuBar(){
		FileMenu fileMenu = new FileMenu(); // menu creation
		this.add(fileMenu); // addition
		EditMenu editMenu = new EditMenu(); // menu creation
		this.add(editMenu); // addition
		ViewMenu viewMenu = new ViewMenu(); // menu creation
		this.add(viewMenu); // addition
		ImageMenu imageMenu = new ImageMenu(); // menu creation
		this.add(imageMenu); // addition
		HelpMenu helpMenu = new HelpMenu();
		this.add(helpMenu);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
