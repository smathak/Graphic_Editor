package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import constants.Constants.EColor;
import constants.Constants.EDrawingType;

public class GPencil extends GShape{
	// working variable (��� �ٲ�� ��, ������ �ʿ䰡 ���� ��)
	private Polygon polygon;

	public GPencil() {
		super(EDrawingType.TP, new Polygon()); // pencil�� two points�� �ƴ����� drag�� ���Ƿ� TP�� ���ߴ�. 
		this.polygon = (Polygon) this.getShape();
	}
	@Override
	public void setOrigin(int x, int y) {
//		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	@Override
	public void setPoint(int x, int y) {
		ex = x;
		ey = y;
	}

	@Override
	public void move(int x, int y) {
//		for(int i=0; i<this.polygon.npoints; i++){
//			this.polygon.xpoints[i] += x - ex;
//			this.polygon.ypoints[i] += y- ey;
//		}
//		this.polygon.invalidate(); // �� ��ǥ�� ������ �� 
//		ex = x;
//		ey = y;
	}

	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor() == null){
//			this.polygon.xpoints[this.polygon.npoints-1] = x - ex; // ������ ���� ����
//			this.polygon.ypoints[this.polygon.npoints-1] = y - ey; // ������ ���� ����
			this.setOrigin(x, y);
			return;
		}
	}

	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if(eColor == EColor.colorYes){
			g2d.setColor(selectedColor);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); 
		}else{
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); 
		}
	}

//	@Override
//	public void initDrawing(int x, int y, Graphics2D g2d) {
//		// �� ���� ���Ϳ� ����
//		polygon.addPoint(x, y);
//	}
//	

//	@Override
//	public void keepDrawing(int x, int y, Graphics2D g2d) {
//		draw(g2d); // ���� �׸��� ����
//		initDrawing(x, y, g2d); // ���ο� ���� ��ǥ �߰� 
//		draw(g2d); // ���ο� �׸��� �׸� 
//	}
//	


}
