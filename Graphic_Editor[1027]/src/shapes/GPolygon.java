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
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); // �⺻������ Polyline���� �׷�����. /�ݵ�� double click�� �ؾ� �� �ٰ����� �׷��� �ȱ׷��� ����Ǽ� �׷�����. 
	}
	
	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		this.polygon.xpoints[this.polygon.npoints-1] = x; // ������ ���� ����
		this.polygon.ypoints[this.polygon.npoints-1] = y; // ������ ���� ���� 
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
