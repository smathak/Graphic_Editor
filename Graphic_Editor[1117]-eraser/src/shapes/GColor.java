package shapes;

import java.awt.Color;
import constants.Constants.ESideBar;

public class GColor{
	public GColor(){
		
	}
	
	public Color getSelectedColor(ESideBar eSelectedColor) {
		switch(eSelectedColor){
		case red:
			return Color.red;
		case blue:
			return Color.blue;
		case yellow:
			return Color.yellow;
		case green:
			return Color.green;
		case black:
			return Color.black;
		default:
			return Color.black;
		}
	}
}
