package shapes;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.Constants.EColor;
import constants.Constants.EDrawingType;

public class GLine extends GShape{
	private Line2D line;
	
	public GLine(){
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D) this.getShape();
	}

	public void draw(Graphics2D g2d) {
		if(eColor == EColor.colorYes){
			g2d.setColor(selectedColor);
			g2d.setStroke(new BasicStroke(6));
			g2d.draw(this.getShape());
			if(this.selected){
				g2d.setStroke(new BasicStroke(1));
				this.getAnchors().draw(g2d, this.getShape().getBounds());
			}
		}else{
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(6));
			g2d.draw(this.getShape());
			if(this.selected){
				g2d.setStroke(new BasicStroke(1));
				this.getAnchors().draw(g2d, this.getShape().getBounds());
			}
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
		this.setP1(x, y); // 새로운 좌표 저장
		this.draw(g2d); // 그림
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// erase shape
		this.draw(g2d);
		this.line.setLine(this.getP0(), this.getP1());

		// redraw shape
		this.draw(g2d);
		this.setP1(x, y);
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initResizing(int x, int y, Graphics2D g2d) {
		this.setP1(x, y);
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}
	}

	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		// erase line
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			this.line.setLine(this.line.getX1(), this.line.getY1(), this.getP1().x, this.getP1().y);
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}
		// redraw shape
		this.draw(g2d);
		this.setP1(x, y);
	}

	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		
	}

}
