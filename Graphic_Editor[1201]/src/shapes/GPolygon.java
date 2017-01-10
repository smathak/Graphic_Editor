package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import constants.Constants.*;

public class GPolygon extends GShape{
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
//		this.polygon = new Polygon();
		this.polygon = (Polygon)this.getShape();
	}
	
	@Override
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public void setPoint(int x, int y) {
		ex = x;
		ey = y;
	}

	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor() == null){
			this.polygon.xpoints[this.polygon.npoints-1] = x - ex; // ������ ���� ����
			this.polygon.ypoints[this.polygon.npoints-1] = y - ey; // ������ ���� ����
			return;
		}
	}
	
	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y); // ����Ŭ���ص� ȣ��� 
	}
	public void move(int x, int y){
		for(int i=0; i<this.polygon.npoints; i++){
			this.polygon.xpoints[i] += x - ex;
			this.polygon.ypoints[i] += y- ey;
		}
		this.polygon.invalidate(); // �� ��ǥ�� ������ �� 
		ex = x;
		ey = y;
	}
	
	public void initCopying(GShape shapePolygon, int x, int y){
		Polygon copyPolygon = (Polygon) shapePolygon.getShape();
		eState = EState.copyAfter;
//		System.out.println("���� polygon�� ����: "+this.polygon.npoints); // ���⼭ this.polygon�� �� polygon�̴�. 
//		for(int i=0; i<this.polygon.npoints; i++){
//			System.out.println("���� polygon: "+this.polygon.xpoints[i]);
//		}
		int xdistance = x-copyPolygon.xpoints[0];
		int ydistance = y-copyPolygon.ypoints[0];
		int px, py;
		
		for(int i=0; i<copyPolygon.npoints; i++){
			px= copyPolygon.xpoints[i] + xdistance;
			py = copyPolygon.ypoints[i] + ydistance;
			this.polygon.addPoint(px, py);
			
//			System.out.println("������: "+ this.polygon.xpoints[i]);
//			this.polygon.xpoints[i] += xdistance;
//			this.polygon.ypoints[i] += ydistance;
//			System.out.println("������: "+this.polygon.xpoints[i]);
		}
		
//		for(int i=0; i<this.polygon.npoints; i++){
//			System.out.println("xpoints["+i+"]: "+this.polygon.xpoints[i]);
//		}
	}

}
