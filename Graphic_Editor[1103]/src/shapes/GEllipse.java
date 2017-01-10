package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import constants.Constants.EDrawingType;

public class GEllipse extends GShape{
	private Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 0, 0);
	public GEllipse(){
		super(EDrawingType.TP);
		this.shape = this.ellipse;
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		ellipse.setFrame(x, y, 0, 0);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
		this.draw(g2d);
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
	}
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.draw(this.ellipse);
	}

	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBoundRectangle(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}
