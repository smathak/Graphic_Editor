package Default;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ViewMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	ViewMenu(){
		super("View");
		JMenuItem toolBox = new JMenuItem("Tool Box");
		this.add(toolBox);
		JMenuItem colorBox = new JMenuItem("Color Box");
		this.add(colorBox);
		JMenuItem statusBar = new JMenuItem("Status Bar");
		this.add(statusBar);
		JMenuItem textToolbar = new JMenuItem("Text Toolbar");
		this.add(textToolbar);
		JMenuItem zoom = new JMenuItem("Zoom");
		this.add(zoom);
		JMenuItem viewBitmap = new JMenuItem("View Bitmap");
		this.add(viewBitmap);
	}
}
