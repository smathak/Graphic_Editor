package shapes;

import java.awt.Graphics;

import javax.swing.JTextField;

public class Text extends JTextField{
	private static final long serialVersionUID = 1L;
	
	int x, y, w, h;
	public Text(){
		this.setBackground(getBackground());
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setDimension(int w, int h){
		this.w = w;
		this.h = h;
	}
	
	public void draw(Graphics g){
		this.setLocation(this.x, this.y);
		this.setSize(this.w, this.h);
		this.setVisible(true);
	}
}
