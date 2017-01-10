package transformer;

import java.awt.Graphics2D;

import shapes.GShape;

public class GDrawer extends GTransformer {
	private static final long serialVersionUID = 1L;
	public GDrawer(GShape shape) {
		super(shape);
	}
	// 이 세개의 함수가 연결점이다. 
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().setOrigin(x, y);
		this.getShape().draw(g2d);
	}
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().draw(g2d); // erase 
		this.getShape().resize(x, y);
		this.getShape().draw(g2d); // draw
	}
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().draw(g2d);
	}
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().addPoint(x, y);
	}

}
