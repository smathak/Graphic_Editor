package transformer;

import java.awt.Graphics2D;
import java.io.Serializable;

import shapes.GShape;

abstract public class GTransformer implements Serializable{
	protected GShape shape;
	// 반드시 shape을 반들게 함.
	public GTransformer(GShape shape){
		this.shape = shape;
	}
	protected GShape getShape(){ return this.shape; }
	abstract public void initTransforming(int x, int y, Graphics2D g2D);
	abstract public void keepTransforming(int x, int y, Graphics2D g2D);
	abstract public void finishTransforming(int x, int y, Graphics2D g2D);
	abstract public void continueTransforming(int x, int y, Graphics2D g2D);
}
