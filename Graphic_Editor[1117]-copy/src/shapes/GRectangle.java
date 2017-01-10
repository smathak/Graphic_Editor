package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.Constants.EColor;
import constants.Constants.EDrawingType;
import constants.Constants.EState;

public class GRectangle extends GShape {
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle) this.getShape();
	}
	
	public void initCopying(int x, int y, int w, int h){
		eState = EState.copyAfter;
		this.rectangle.setFrame(x, y, w, h); // 반드시 여기서 초기화 해야 한다. (안그러면 0,0,0,0이 됨 )
//		System.out.println("w-GRectangle : "+w);
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
		int ox, oy;
		ox = this.rectangle.x;
		oy = this.rectangle.y;
		
		ox += x - this.getP1().x;
		oy += y - this.getP1().y;
//		this.rectangle.x += x - this.getP1().x; // 직사각형의 x의 위치를 바꿈
//		this.rectangle.y += y - this.getP1().y; // 직사각형의 y의 위치를 바꿈 
		// Transforming은 width와 height는 그래로 둔다.
		this.rectangle.setFrame(ox, oy, this.rectangle.width, this.rectangle.height);
		// redraw shape
		this.draw(g2d);
		this.setP1(x, y);
	}
	
	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		
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
