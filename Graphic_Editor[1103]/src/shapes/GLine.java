package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.Constants.EDrawingType;

public class GLine extends GShape{
	private Line2D line;
	
	public GLine(){
		super(EDrawingType.TP);
		this.line = new Line2D.Double(0, 0, 0, 0);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.draw(line);
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		line.setLine(x, y, x, y);
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.line.setLine(this.line.getX1(), this.line.getY1(), x, y);
		this.draw(g2d);
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {

	}

	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {

	}

	@Override
	public void drawBoundRectangle(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}
