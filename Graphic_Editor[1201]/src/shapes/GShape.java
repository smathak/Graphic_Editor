package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.IOException;
import java.io.Serializable;

import constants.Constants.EAnchors;
import constants.Constants.EColor;
import constants.Constants.EDrawingType;
import constants.Constants.ESideBar;
import constants.Constants.EState;
import frame.DrawingPanel;

abstract public class GShape implements Serializable{
	private static final long serialVersionUID = 1L;
	// attributes
	private EDrawingType eDrawingType;
	protected Anchors anchors;
	public boolean selected;
	private GCursor cursor;
	public EAnchors currentEAnchor;
	GColor color;
	Color selectedColor;
	ESideBar eSelectedColor;
	ESideBar thisColor;
	EColor eColor;
	EState eState;
	int x, y, w, h, dx, dy; // for copy
	// components
	protected Shape shape;
	
	public int ex, ey;
	
	// getter && setter
	public void setAnchors(Anchors anchors) {	this.anchors = anchors;}
	public void setSelected(boolean selected) {	this.selected = selected; }
	public Anchors getAnchors() { return anchors;	}
	public EDrawingType geteDrawingType() {	return eDrawingType;}
	public Shape getShape(){ return this.shape; }
	public boolean isSelected() { return selected; }
	public GCursor getCursor() { return cursor; }
	public EAnchors getCurrentEAnchor() {	return this.currentEAnchor; }
	public void setEColor() { eColor = EColor.colorYes; }
	
	public void setSelectedColor(ESideBar eSelectedColor){
		this.eSelectedColor = eSelectedColor;
		selectedColor = color.getSelectedColor(eSelectedColor); // 색깔을 가져옴 
		eColor = EColor.colorYes;
	}
	public ESideBar getColor(){
		return thisColor;
	}
	
	public ESideBar getSelectedColor(){
		return this.eSelectedColor;
	}

	// constructor
	public GShape(EDrawingType eDrawingType, Shape shape){
		this.eDrawingType = eDrawingType;
		this.anchors = new Anchors();
		this.shape = shape;
		setSelected(false);
		cursor = new GCursor();
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
	
//	public void finishDrawing(int x, int y, Graphics2D g2D) {
//		setSelected(true);
//		this.getAnchors().draw(g2D, this.getShape().getBounds()); // Rectangle의 경계를 전달
//		
//		if(eState == EState.copyAfter){
//			this.draw(g2D);
//		}
//	}
	
	
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
//			System.out.println("확인: "+this.getShape());
			g2d.draw(this.getShape());
			if(this.selected){
				this.getAnchors().draw(g2d, this.getShape().getBounds());
			}
		}
		if(eState == EState.fill){
			selectedColor = color.getSelectedColor(eSelectedColor);
			g2d.setColor(selectedColor); 
			g2d.fill(this.getShape());
		}
		this.thisColor = eSelectedColor;
	}
	
	public void fillShape() {
		eState = EState.fill;
	}
	
	public void initCopying(int x, int y, int w, int h){
		eState = EState.copyAfter;
	}

	public void initCopying(GShape copyShape, int x, int y){
		eState = EState.copyAfter;
	}
	
	abstract public void setPoint(int x2, int y2);
	abstract public void move(int x2, int y2);
	abstract public void resize(int x2, int y2);
	abstract public void setOrigin(int x2, int y2);
	abstract public void addPoint(int x2, int y2);

}

