package shapes;

import java.awt.Graphics2D;

abstract public class GShape {

	abstract public void draw(Graphics2D g2D);
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}

