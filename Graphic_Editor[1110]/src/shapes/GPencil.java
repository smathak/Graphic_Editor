package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;
import constants.Constants.EDrawingType;

public class GPencil extends GShape{
	// working variable (계속 바뀌는 값, 저장할 필요가 없는 값)
	private Polygon polygon;

	public GPencil() {
		super(EDrawingType.TP, new Polygon()); // pencil이 two points는 아니지만 drag를 쓰므로 TP로 정했다. 
		this.polygon = (Polygon) this.getShape();
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		// 새 점을 벡터에 저장
		polygon.addPoint(x, y);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints); 
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		draw(g2d); // 이전 그림을 지움
		initDrawing(x, y, g2d); // 새로운 점의 좌표 추가 
		draw(g2d); // 새로운 그림을 그림 
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
