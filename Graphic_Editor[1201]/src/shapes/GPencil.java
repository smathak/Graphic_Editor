package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import constants.Constants.EColor;
import constants.Constants.EDrawingType;

public class GPencil extends GShape{
	// working variable (계속 바뀌는 값, 저장할 필요가 없는 값)
	private Polygon polygon;

	public GPencil() {
		super(EDrawingType.TP, new Polygon()); // pencil이 two points는 아니지만 drag를 쓰므로 TP로 정했다. 
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
//		this.polygon.invalidate(); // 이 좌표가 적용이 됨 
//		ex = x;
//		ey = y;
	}

	@Override
	public void resize(int x, int y) {
		if(this.getCurrentEAnchor() == null){
//			this.polygon.xpoints[this.polygon.npoints-1] = x - ex; // 마지막 점을 수정
//			this.polygon.ypoints[this.polygon.npoints-1] = y - ey; // 마지막 점을 수정
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
//		// 새 점을 벡터에 저장
//		polygon.addPoint(x, y);
//	}
//	

//	@Override
//	public void keepDrawing(int x, int y, Graphics2D g2d) {
//		draw(g2d); // 이전 그림을 지움
//		initDrawing(x, y, g2d); // 새로운 점의 좌표 추가 
//		draw(g2d); // 새로운 그림을 그림 
//	}
//	


}
