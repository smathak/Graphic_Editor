package Default;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class HelpMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	public HelpMenu() {
		super("Help");
		
		JMenuItem helpTopics = new JMenuItem("Help Topics");
		this.add(helpTopics);
		
		this.addSeparator();
	
		JMenuItem aboutPaint = new JMenuItem("About Paint");
		this.add(aboutPaint);
	}
}
