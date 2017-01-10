package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants.EImageMenu;

public class ImageMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	public ImageMenu(){
		super("Image");
		
		for(EImageMenu eImageMenu : EImageMenu.values()){
			JMenuItem item = new JMenuItem(eImageMenu.getValue());
			this.add(item);
			this.addSeparator();
		}
	}
}
