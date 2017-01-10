package Default;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	//constructor
	public FileMenu() {
		super("File");
		
		JMenuItem New = new JMenuItem("New");
		this.add(New);
		JMenuItem open = new JMenuItem("Open");
		this.add(open);
		JMenuItem save = new JMenuItem("Save");
		this.add(save);
		JMenuItem saveAs = new JMenuItem("Save As");
		this.add(saveAs);
		JMenuItem print = new JMenuItem("Print");
		this.add(print);
		JMenuItem fromScannerOrCamera = new JMenuItem("From scanner or camera");
		this.add(fromScannerOrCamera);
		JMenuItem sendInEmail = new JMenuItem("Send in email");
		this.add(sendInEmail);
		JMenuItem setAsDesktopBackground = new JMenuItem("Set as desktop background");
		this.add(setAsDesktopBackground);
		JMenuItem properties = new JMenuItem("Properties");
		this.add(properties);
		JMenuItem aboutPaint = new JMenuItem("About Paint");
		this.add(aboutPaint);
		JMenuItem exit = new JMenuItem("Exit");
		this.add(exit);
	}
}
