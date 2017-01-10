package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

import constants.Constants.EAnchors;
import constants.Constants.EDrawingType;

abstract public class GShape {
	private EDrawingType eDrawingType;
	private Anchors anchors;
	protected Shape shape;
	private boolean selected;
	
	public Anchors getAnchors() { return anchors;	}
	public void setAnchors(Anchors anchors) {	this.anchors = anchors;}
	
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	
	public GShape(EDrawingType eDrawingType){
		this.eDrawingType = eDrawingType;
		this.anchors = new Anchors();
	}
	abstract public void draw(Graphics2D g2D);
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	abstract public void continueDrawing(int x, int y, Graphics2D g2d);
	
	public boolean contains(int x, int y) {
		return this.shape.contains(x, y);
	}
	
	abstract public void drawBoundRectangle(int x, int y, Graphics2D g2d);
}

