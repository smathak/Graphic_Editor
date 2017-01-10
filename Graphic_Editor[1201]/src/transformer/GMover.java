package transformer;

import java.awt.Graphics2D;

import shapes.GShape;

// 클래스는 하나당 100+a 를 넘지 않도록 한다. 
public class GMover extends GTransformer {

	public GMover(GShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2D) {
		this.getShape().setPoint(x, y);
		this.getShape().draw(g2D);
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.getShape().draw(g2d);	
		this.getShape().move(x,  y);
		this.getShape().draw(g2d);

	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void continueTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

}
