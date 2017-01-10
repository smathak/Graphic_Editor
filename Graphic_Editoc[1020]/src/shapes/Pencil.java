package shapes;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

import constants.Constants.Poly;

public class Pencil extends Polygon{
	// working variable (��� �ٲ�� ��, ������ �ʿ䰡 ���� ��)
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
		drawPoly(g2d, Poly.polyline); // ���� �׸��� �׸�
		initDrawing(x, y, g2d); // ���ο� ���� ��ǥ �߰� 
		drawPoly(g2d, Poly.polyline); // ���ο� �׸��� �׸� 
	}
}
