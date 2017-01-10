package shapes;

import java.awt.Graphics2D;

public class Ellipse extends GShape{
	private int x, y, w, h;
	public Ellipse(){
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.x = x;
		this.y = y;		
		this.w = 0;
		this.h = 0;
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		g2d.drawOval(this.x, this.y, this.w, this.h);
		this.w = x - this.x;
		this.h = y - this.y;
		g2d.drawOval(this.x, this.y, this.w, this.h);		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
	}
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawOval(this.x, this.y, this.w, this.h);
	}
}
