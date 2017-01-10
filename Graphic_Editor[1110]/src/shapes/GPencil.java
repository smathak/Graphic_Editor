package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import constants.Constants.EDrawingType;

public class GPencil extends GShape{
	// working variable (��� �ٲ�� ��, ������ �ʿ䰡 ���� ��)
	private Polygon polygon;

	public GPencil() {
		super(EDrawingType.TP, new Polygon()); // pencil�� two points�� �ƴ����� drag�� ���Ƿ� TP�� ���ߴ�. 
		this.polygon = (Polygon) this.getShape();
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		// �� ���� ���Ϳ� ����
		polygon.addPoint(x, y);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); 
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		draw(g2d); // ���� �׸��� ����
		initDrawing(x, y, g2d); // ���ο� ���� ��ǥ �߰� 
		draw(g2d); // ���ο� �׸��� �׸� 
	}
	
//	public void clearPoints(){
//		if(xPoints.size() != 0){
//			xPoints.clear();
//		}
//		if(yPoints.size() != 0){
//			yPoints.clear();
//		}
//	}

//	@Override
//	public void draw(Graphics2D g2d) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {	
	}
	
	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {	
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
}
