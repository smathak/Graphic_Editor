package Default;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	EditMenu(){
		super("Edit"); 
		JMenuItem undo = new JMenuItem("Undo");
		this.add(undo);
		JMenuItem repeat = new JMenuItem("Repeat");
		this.add(repeat);
		JMenuItem cut = new JMenuItem("Cut");
		this.add(cut);
		JMenuItem copy = new JMenuItem("Copy");
		this.add(copy);
		JMenuItem paste = new JMenuItem("Paste");
		this.add(paste);
		JMenuItem clearSelection = new JMenuItem("Clear Selection");
		this.add(clearSelection);
		JMenuItem selectAll = new JMenuItem("Select All");
		this.add(selectAll);
		JMenuItem copyTo = new JMenuItem("Copy To");
		this.add(copyTo);
		JMenuItem pasteFrom = new JMenuItem("Paste From");
		this.add(pasteFrom);
	}
}
