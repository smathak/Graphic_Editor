package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import constants.Constants.EColor;
import constants.Constants.EDrawingType;
import constants.Constants.EState;

public class GEllipse extends GShape{
	private Ellipse2D ellipse;
	public GEllipse(){
		super(EDrawingType.TP, new Ellipse2D.Double(0, 0, 0, 0));
		this.ellipse = (Ellipse2D) this.getShape();
	}
	public void initCopying(int x, int y, int w, int h){
		eState = EState.copyAfter;
		this.ellipse.setFrame(x, y, w, h);
//		System.out.println("w-GRectangle : "+w);
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		ellipse.setFrame(x, y, 0, 0);
		this.setP0(x, y);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
		this.draw(g2d);
	}

	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.setP1(x, y); // ���ο� ��ǥ ����
		this.draw(g2d); // �׸�
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// erase shape
		this.draw(g2d);
		double ox, oy;
		ox = this.ellipse.getX();
		oy = this.ellipse.getY();
		
		ox += x - this.getP1().x;
		oy += y - this.getP1().y;
		
		// Transforming�� width�� height�� �׷��� �д�.
		this.ellipse.setFrame(ox, oy, this.ellipse.getWidth(), this.ellipse.getHeight());
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
		// erase shape
		double ow, oh;
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
			ow = this.ellipse.getWidth();
			oh = this.ellipse.getHeight();
			ow += x - this.getP1().x;
			oh += y - this.getP1().y;	
			this.ellipse.setFrame(this.ellipse.getX(),
					this.ellipse.getY(), ow, oh);
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
