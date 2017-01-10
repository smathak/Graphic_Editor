package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ViewMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public ViewMenu(){
		super("View");
		JMenuItem toolBox = new JMenuItem("Tool Box");
		this.add(toolBox);

		this.addSeparator();
		
		JMenuItem colorBox = new JMenuItem("Color Box");
		this.add(colorBox);

		this.addSeparator();
		
		JMenuItem statusBar = new JMenuItem("Status Bar");
		this.add(statusBar);
		
		this.addSeparator();
		
		JMenuItem textToolbar = new JMenuItem("Text Toolbar");
		this.add(textToolbar);
		
		this.addSeparator();
		
		JMenuItem zoom = new JMenuItem("Zoom");
		this.add(zoom);
		
		this.addSeparator();
		
		JMenuItem viewBitmap = new JMenuItem("View Bitmap");
		this.add(viewBitmap);
	}
}
