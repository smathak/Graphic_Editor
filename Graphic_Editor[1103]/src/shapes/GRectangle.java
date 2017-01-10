package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.Constants.EDrawingType;
import constants.Constants.Selected;

public class GRectangle extends GShape {
	private Selected selected;
	private Rectangle rectangle = new Rectangle();
	public GRectangle() {
		super(EDrawingType.TP);
		this.rectangle.setBounds(0, 0, 0, 0);
		this.shape = this.rectangle;
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.rectangle.setLocation(x, y);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.rectangle.setFrame(this.rectangle.getX(), this.rectangle.getY(), x-this.rectangle.getX(), y-this.rectangle.getY());
		this.draw(g2D);
		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		this.getAnchors().draw(g2D, this.rectangle.getBounds()); // Rectangle의 경계를 전달
		selected = Selected.yes;
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.draw(rectangle);
	}
	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawBoundRectangle(int x, int y, Graphics2D g2D){
		if(this.shape.contains(x, y) && selected == Selected.no){
			this.getAnchors().draw(g2D, this.rectangle.getBounds()); // Rectangle의 경계를 전달
			selected = Selected.yes;
		}else{
			if(selected == Selected.yes){
				this.getAnchors().draw(g2D, this.rectangle.getBounds()); // Rectangle의 경계를 전달
				selected = Selected.no;
			}
		}
	}
}
