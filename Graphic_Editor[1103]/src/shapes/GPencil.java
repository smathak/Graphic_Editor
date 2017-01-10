package shapes;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

import constants.Constants.EDrawingType;
import constants.Constants.Poly;

public class GPencil extends GShape{
	// working variable (계속 바뀌는 값, 저장할 필요가 없는 값)
	private List<Integer> xPoints = new Vector<Integer>();
	private List<Integer> yPoints = new Vector<Integer>();
	private int xArray[] = null;
	private int yArray[] = null;

	public GPencil() {
		super(EDrawingType.TP);
	}


	@Override
	public void initDrawing(int x, int y, Graphics2D g2d) {
		// 새 점을 벡터에 저장
		xPoints.add(x);
		yPoints.add(y);
		System.out.println("x: "+x+" y: "+y);
	}

	public void drawPoly(Graphics2D g2d, Poly polyline) {
		int length = xPoints.size();
		xArray = new int[length];
		yArray = new int[length];
		
		for(int i=0; i<length; i++){
			xArray[i] = xPoints.get(i);
		}
		for(int i=0; i<length; i++){
			yArray[i] = yPoints.get(i);
		}
		
		if(polyline == Poly.polyline){
			g2d.drawPolyline(xArray, yArray, length); //g2d가 아직 생성이 아
			System.out.println("drawing");
		}else if(polyline == Poly.polygon){
			g2d.drawPolygon(xArray, yArray, length);
		}
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		drawPoly(g2d, Poly.polyline); // 이전 그림을 그림
		initDrawing(x, y, g2d); // 새로운 점의 좌표 추가 
		drawPoly(g2d, Poly.polyline); // 새로운 그림을 그림 
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
	public void clearPoints(){
		if(xPoints.size() != 0){
			xPoints.clear();
		}
		if(yPoints.size() != 0){
			yPoints.clear();
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBoundRectangle(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
