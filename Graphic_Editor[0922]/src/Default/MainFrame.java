package Default;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//constructor
	MainFrame() throws IOException{
		// attribute initialization
		this.setTitle("Graphic Editor");
		this.setSize(1000, 600);
	
		// add component
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		// add Tool bar
		ToolBar toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);
		
		// add panels
		DrawingPanel drawingPanel = new DrawingPanel(toolBar);
		this.add(drawingPanel);
		
	
	}

}
