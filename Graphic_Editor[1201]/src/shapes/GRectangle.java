package shapes;

import java.awt.Rectangle;

import constants.Constants.EDrawingType;
import constants.Constants.EState;

public class GRectangle extends GShape {
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}

	public void setOrigin(int x, int y) {
		this.rectangle.setLocation(x, y);
	}
	public void setPoint(int x, int y){ // moving 을 위해서 전 점을 기억
		this.ex = x;
		this.ey = y;
	}
	public void move(int x, int y){
		this.rectangle.x += x-ex;
		this.rectangle.y += y-ey;
		this.setPoint(x, y);
	}
		
	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor() == null){
			this.rectangle.width = x - this.rectangle.x;
			this.rectangle.height = y - this.rectangle.y;
			return;
		}
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
			this.rectangle.width = x - this.rectangle.x;
			this.rectangle.height = y - this.rectangle.y;				
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
		this.setPoint(x, y);
	}
	
	public void addPoint(int x, int y) {
		
	}

	public void initCopying(int x, int y, int w, int h){
		eState = EState.copyAfter;
		System.out.println("x: "+x+" y: "+y+" w: "+w+" h: "+h);
		this.rectangle.setFrame(x, y, w, h);
	}
	
}
