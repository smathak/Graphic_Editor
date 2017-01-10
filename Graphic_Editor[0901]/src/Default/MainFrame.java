package Default;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//constructor
	MainFrame(){
		// attribute initialization
		this.setTitle("Graphic Editor");
		this.setSize(400, 600);
	
		// add component
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
	}

}
