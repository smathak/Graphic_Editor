package frame;

import javax.swing.JMenuBar;

import menus.EditMenu;
import menus.FileMenu;
import menus.HelpMenu;
import menus.ImageMenu;
import menus.ViewMenu;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private DrawingPanel drawingPanel;
	private MainFrame mainFrame;
	
	FileMenu fileMenu;
	EditMenu editMenu;
	
	//constructor
	MenuBar(){ // 이걸 먼저하고
		fileMenu = new FileMenu(); // menu creation
		this.add(fileMenu); // addition
		
		editMenu = new EditMenu(); // menu creation
		this.add(editMenu); // addition

		
		ViewMenu viewMenu = new ViewMenu(); // menu creation
		this.add(viewMenu); // addition
		ImageMenu imageMenu = new ImageMenu(); // menu creation
		this.add(imageMenu); // addition
		HelpMenu helpMenu = new HelpMenu();
		this.add(helpMenu);
	}

	public void initialize(DrawingPanel drawingPanel, MainFrame mainFrame) { // 이걸 실행하므로 editMenu.initialize에 들어가는 drawingPanel은 null 일 수 밖에 없음 
		this.drawingPanel = drawingPanel;
		editMenu.initialize(drawingPanel);
		fileMenu.initialize(drawingPanel, mainFrame);
	}
}
