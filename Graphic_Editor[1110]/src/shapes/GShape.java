package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.IOException;

import constants.Constants.EAnchors;
import constants.Constants.EDrawingType;
import frame.DrawingPanel;

abstract public class GShape {
	// attributes
	private EDrawingType eDrawingType;
	private Anchors anchors;
	public boolean selected;
	private GCursor cursor;
	public EAnchors currentEAnchor;
	private Point p0, p1;
	
	// components
	protected Shape shape;
	
	// getter && setter
	public void setAnchors(Anchors anchors) {	this.anchors = anchors;}
	public void setSelected(boolean selected) {	this.selected = selected; }
	public Anchors getAnchors() { return anchors;	}
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	public Shape getShape(){ return this.shape; }
	public boolean isSelected() { return selected; }
	public GCursor getCursor() { return cursor; }
	public EAnchors getCurrentEAnchor() {	return this.currentEAnchor; }
	public Point getP0() { return p0; }
	public void setP0(int x, int y) { this.p0.x = x; this.p0.y = y; }
	public Point getP1() { return p1; }
	public void setP1(int x, int y) { this.p1.x = x; this.p1.y = y; }
	
	// constructor
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.anchors = new Anchors();
		this.shape = shape;
		setSelected(false);
		cursor = new GCursor();
		this.p1 = new Point(0, 0);
		this.p0 = new Point(0, 0);
		this.currentEAnchor = null;
	}

	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void changeCursor(EAnchors eAnchor, DrawingPanel drawingPanel) throws IOException{
		cursor.setDrawingPanel(drawingPanel);
		cursor.changeCursor(eAnchor);
	}
	
	public EAnchors contains(int x, int y) {
		this.currentEAnchor = null;
		if(this.selected){
			currentEAnchor = this.anchors.contains(x, y); // NN, NE, NW ... 중에 하나를 리턴
			if(currentEAnchor != null){
				return currentEAnchor;
			}
		}
		
		if(this.shape.contains(x, y)){
			this.currentEAnchor = EAnchors.MM;
		}
		return this.currentEAnchor;
	}
	
	
	
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		setSelected(true);
		this.getAnchors().draw(g2D, this.getShape().getBounds()); // Rectangle의 경계를 전달
		
	}
	
	public void draw(Graphics2D g2d) {
		g2d.draw(this.getShape());
		if(this.selected){
			this.getAnchors().draw(g2d, this.getShape().getBounds());
		}
	}
	
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2d);
	abstract public void initTransforming(int x, int y, Graphics2D g2d);
	abstract public void keepTransforming(int x, int y, Graphics2D g2d);
	abstract public void finishTransforming(int x, int y, Graphics2D g2d);
	abstract public void initResizing(int x, int y, Graphics2D g2d);
	abstract public void keepResizing(int x, int y, Graphics2D g2d);
	abstract public void finishResizing(int x, int y, Graphics2D g2d);

}

