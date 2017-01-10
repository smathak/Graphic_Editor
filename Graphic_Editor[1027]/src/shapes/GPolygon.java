package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import constants.Constants.*;

public class GPolygon extends GShape{
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP);
		this.polygon = new Polygon();
	}
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); // 기본적으로 Polyline으로 그려진다. /반드시 double click을 해야 새 다각형이 그려짐 안그려면 연결되서 그려진다. 
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		this.polygon.xpoints[this.polygon.npoints-1] = x; // 마지막 점을 변경
		this.polygon.ypoints[this.polygon.npoints-1] = y; // 마지막 점을 변경 
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		g2d.drawPolygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
	}
	@Override
	public void continueDrawing(int x, int y, Graphics2D g2D) {
		this.polygon.addPoint(x, y);
	}
	
	
}
