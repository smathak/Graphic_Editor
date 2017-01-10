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
		
		this.addSeparator(); // 메뉴를 보기 좋기 분리 하기 
		
		JMenuItem save = new JMenuItem("Save");
		this.add(save);
		JMenuItem saveAs = new JMenuItem("Save As");
		this.add(saveAs);
		
		this.addSeparator();
		
		JMenuItem print = new JMenuItem("Print");
		this.add(print);
		JMenuItem fromScannerOrCamera = new JMenuItem("From scanner or camera");
		this.add(fromScannerOrCamera);
		JMenuItem sendInEmail = new JMenuItem("Send in email");
		this.add(sendInEmail);
		
		this.addSeparator();
		
		JMenuItem setAsDesktopBackground = new JMenuItem("Set as desktop background");
		this.add(setAsDesktopBackground);
		
		this.addSeparator();
		
		JMenuItem properties = new JMenuItem("Properties");
		this.add(properties);
		
		this.addSeparator();
		
		JMenuItem aboutPaint = new JMenuItem("About Paint");
		this.add(aboutPaint);
		
		this.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		this.add(exit);
	}
}
