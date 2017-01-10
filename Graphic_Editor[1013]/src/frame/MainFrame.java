package frame;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import constants.Constants;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//constructor
	public MainFrame() throws IOException{
		// attribute initialization
		this.setTitle(Constants.MAINFRAME_TITLE);
		this.setSize(Constants.EMainFrame.W.getValue(), Constants.EMainFrame.H.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// add component
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		// add Tool bar
		ToolBar toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);
		
		// add panels
		DrawingPanel drawingPanel = new DrawingPanel();
		this.add(drawingPanel);
		
		toolBar.setDrawingPanel(drawingPanel);
	}

}
