package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.Constants.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle) this.getShape();
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
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
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
		this.rectangle.x += x - this.getP1().x;
		this.rectangle.y += y - this.getP1().y;
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
			this.rectangle.width += x - this.getP1().x;
			this.rectangle.height += y - this.getP1().y;				
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
}
