package Default;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ImageMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	ImageMenu(){
		super("Image");
		
		JMenuItem New = new JMenuItem("New");
		this.add(New);		
		JMenuItem strechskew = new JMenuItem("Stretch/Skew");
		this.add(strechskew);
		JMenuItem invertColors = new JMenuItem("Invert Colors");
		this.add(invertColors);
		JMenuItem attributes = new JMenuItem("Attributes");
		this.add(attributes);
		JMenuItem clearImage = new JMenuItem("Clear image");
		this.add(clearImage);
		JMenuItem drawOpaque = new JMenuItem("Draw Opaque");
		this.add(drawOpaque);
	}
}
