package shapes;

import java.awt.TextArea;

public class GTextArea extends TextArea{
	public GTextArea(boolean visible){
//		this.setLocation(50, 50);
//		this.setSize(150, 150);
		if(visible == false)
			this.setVisible(false);
		else if(visible == true)
			this.setVisible(true);
	}
}
