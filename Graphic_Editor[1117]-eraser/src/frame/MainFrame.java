package frame;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import constants.Constants;
import constants.Constants.EMainFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	//components
	private MenuBar menuBar;
	private ToolBar toolBar;
	private SideBar sideBar;
	private DrawingPanel drawingPanel;

	//constructor
	public MainFrame() throws IOException{
		// attribute initialization
		this.setTitle(Constants.MAINFRAME_TITLE);
		this.setSize(EMainFrame.W.getValue(), EMainFrame.H.getValue());
		this.setLocation(EMainFrame.X.getValue(), EMainFrame.Y.getValue());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// add component
		menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		// add Tool bar
		toolBar = new ToolBar();
		this.add(toolBar, BorderLayout.PAGE_START);
		sideBar = new SideBar();
		this.add(sideBar, BorderLayout.EAST);
		
		// add panels
		drawingPanel = new DrawingPanel();
		this.add(drawingPanel);
	}
	
	public void initialize(){
//		cursor.setDrawingPanel(drawingPanel);
		toolBar.setDrawingPanel(drawingPanel);
		sideBar.setDrawingPanel(drawingPanel);
		// component initialization
		this.menuBar.initialize();			
		this.toolBar.initialize();		
		this.drawingPanel.initialize();		
	}
}
