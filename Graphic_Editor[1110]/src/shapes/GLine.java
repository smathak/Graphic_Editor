package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.Constants.EDrawingType;

public class GLine extends GShape{
	private Line2D line;
	
	public GLine(){
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D) this.getShape();
	}

	public void draw(Graphics2D g2d) {
		g2d.draw(this.getShape());
//		g2d.setStroke(new BasicStroke(3));
		if(this.selected){
			this.getAnchors().draw(g2d, this.getShape().getBounds());
		}
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
	public void continueDrawing(int x, int y, Graphics2D g2d) {

	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}
