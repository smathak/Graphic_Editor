package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import constants.Constants.*;

public class GPolygon extends GShape{
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP,  new Polygon());
		this.polygon = (Polygon) this.getShape();
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
//		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		this.draw(g2d);
		this.polygon.xpoints[this.polygon.npoints-1] = x; // 마지막 점을 변경
		this.polygon.ypoints[this.polygon.npoints-1] = y; // 마지막 점을 변경 
		this.draw(g2d);
//		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
	}
	@Override
	public void continueDrawing(int x, int y, Graphics2D g2D) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void finishDrawing(int x, int y, Graphics2D g2d) {
//		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
//		this.draw(g2d);
//	}
	
//	@Override
//	public void draw(Graphics2D g2d) {
//		g2d.draw(this.polygon);
//	}
}
