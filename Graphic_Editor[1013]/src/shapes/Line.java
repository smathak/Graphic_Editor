package shapes;

import java.awt.Graphics;

public class Line {
	private int x, y, w, h;
	
	public Line(){
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setDimension(int w, int h){
		this.w = w;
		this.h = h;
	}
	public int getX() {	return x;}
	public void setX(int x) {this.x = x;}
	
	public int getY() {	return y;}
	public void setY(int y) {this.y = y;}
	
	public int getW() {	return w;}
	public void setW(int w) {this.w = w;}
	
	public int getH() {	return h;}
	public void setH(int h) {this.h = h;}
	
	public void draw(Graphics g){	
		g.drawLine(x, y, x+w, y+h);
	}
}
