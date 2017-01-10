package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.IOException;

import constants.Constants.EAnchors;
import constants.Constants.EColor;
import constants.Constants.EDrawingType;
import constants.Constants.ESideBar;
import constants.Constants.EState;
import frame.DrawingPanel;

abstract public class GShape {
	// attributes
	private EDrawingType eDrawingType;
	private Anchors anchors;
	public boolean selected;
	private GCursor cursor;
	public EAnchors currentEAnchor;
	private Point p0, p1;
	GColor color;
	Color selectedColor;
	ESideBar eSelectedColor;
	EColor eColor;
	EState eState;
	int x, y, w, h, dx, dy; // for copy
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
	public void setEColor() { eColor = EColor.colorYes; }
	
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
		color = new GColor();
		eColor = EColor.colorNo;
		eState = null;
	}
	
	// constructor2
	public GShape(){}

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
			this.currentEAnchor = EAnchors.MM; // line은 인식 못함 
		}
		return this.currentEAnchor;
	}
	
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		setSelected(true);
		this.getAnchors().draw(g2D, this.getShape().getBounds()); // Rectangle의 경계를 전달
		
	}
	
	public void setSelectedColor(ESideBar eSelectedColor){
		System.out.println("setSelectedColor: "+eSelectedColor);
		this.eSelectedColor = eSelectedColor;
		selectedColor = color.getSelectedColor(eSelectedColor);
		this.setEColor();
	}
	
	public void draw(Graphics2D g2d){
	    // paint가 이 메소드를 호출하므로 여기서 g2d.setColor를 호출해야 된다.
		if(eColor == EColor.colorYes){
			g2d.setColor(selectedColor);
			g2d.setStroke(new BasicStroke(1));
			g2d.draw(this.getShape());
			if(this.selected){
				this.getAnchors().draw(g2d, this.getShape().getBounds());
			}
		}
		else{
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(1));
			g2d.draw(this.getShape());
			if(this.selected){
				this.getAnchors().draw(g2d, this.getShape().getBounds());
			}
		}
		if(eState == EState.fill){
//			System.out.println("selectedColor: "+selectedColor); // 이때 selectedColor가 null임 
//			System.out.println("eSelectedColor: "+eSelectedColor);
			selectedColor = color.getSelectedColor(eSelectedColor);
			g2d.setColor(selectedColor); 
			g2d.fill(this.getShape());
		}
		if(eState == EState.copyAfter){
			System.out.println("copy 호출됨");
			System.out.println("x: "+x+" y: "+y+" w: "+w+" h: "+h+" dx: "+dx+" dy: "+dy);

			g2d.copyArea(x, y, w, h, dx, dy);
		}
	}
	
	public void fillShape() {
		eState = EState.fill;
	}

	public void drawCopy(int ox, int oy, int w, int h, int dx, int dy){
		eState = EState.copyAfter;
		this.x = ox;
		this.y = oy;
		System.out.println("y: "+y+" oy: "+oy);
		this.w = w;
		this.h = h;
		this.dx = dx;
		this.dy = dy;
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

