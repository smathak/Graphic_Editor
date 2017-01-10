package shapes;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

import constants.Constants.Poly;

public class Pencil extends Polygon{
	// working variable (계속 바뀌는 값, 저장할 필요가 없는 값)
	private List<Integer> xPoints = new Vector<Integer>();
	private List<Integer> yPoints = new Vector<Integer>();
	private int xArray[] = null;
	private int yArray[] = null;
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2d) {
		drawPoly(g2d, Poly.polyline); // 이전 그림을 그림
		initDrawing(x, y, g2d); // 새로운 점의 좌표 추가 
		drawPoly(g2d, Poly.polyline); // 새로운 그림을 그림 
	}
}
