package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;

import constants.Constants.EDrawingType;

public class GLine extends GShape{
	private int x, y, w, h;
	
	public GLine(){
		super(EDrawingType.TP);
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}


	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawLine(x, y, x+w, y+h);
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;		
		this.w = 0;
		this.h = 0;
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawLine(this.x, this.y, this.x+this.w, this.y+this.h);
		this.w = x - this.x;
		this.h = y - this.y;
		g2d.drawLine(this.x, this.y, this.x+this.w, this.y+this.h);
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
	}


	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}
