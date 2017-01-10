package Default;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;


public class DrawingPanel extends JPanel {
	private enum EState {idle, drawing};
	public enum ESelectedTool {rectangle, ellipse, line, text, polygon, pencil};
	private enum Click {oneClick, doubleClick};
	private enum Polygon {polygon, polyline};
	private enum Start {yes, no}
	
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	private ESelectedTool eSelectedTool;
		
	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void paint(Graphics g) {
	}
	// working variable (��� �ٲ�� ��, ������ �ʿ䰡 ���� ��)
	private List<Integer> xPoints = new Vector<Integer>();
	private List<Integer> yPoints = new Vector<Integer>();
	
	private int x1, y1, x2, y2;
	
	// �ִϸ��̼��� �ִ� �׸��׸��� 3�ܰ�
	// 1. �ʱ� ��ǥ ����
	private void initDrawing(int x, int y){ // �Ű������� ���콺 ������
		x1 = x;
		y1 = y;
		x2 = x; 
		y2 = y; 
	}
	// 2. keep drawing(�ִϸ��̼�)
	private void keepDrawing(int x, int y){ // ���� ����̽�(���콺)�� ��ǥ�� �̰Źۿ� ����
		draw(x1, y1, x2-x1, y2-y1); // �� ��ǥ�� �׸��� �׸�
		draw(x1, y1, x-x1, y-y1); // ����ǥ�� �׸�
		
		x2 = x; // �� ��ǥ�� �� ��ǥ�� ����
		y2 = y;
		//��� �ݺ�
	}
	
	private void keepDrawingPoly(int x, int y){
		if(eSelectedTool == ESelectedTool.pencil){
			drawPoly(xPoints, yPoints, Polygon.polyline);
			continueDrawing(x, y);
			drawPoly(xPoints, yPoints, Polygon.polyline);
		}
	}
	
	private void continueDrawing(int x, int y){ //for drawing polygon
		// �� ���� ���Ϳ� ����
		xPoints.add(x);
		yPoints.add(y);
		
		System.out.println("x: "+x+"y: "+y);
	}
	
	// 3. ���� ���� �׸��� 
	private void finishDrawing(int x, int y){
		draw(x1, y1, x-x1, y-y1); // draw �Լ� ȣ��
	}
	
	public void setESelectedTool(ESelectedTool eSelectedTool){
		this.eSelectedTool = eSelectedTool;
	}
	
	private void draw(int x, int y, int w, int h) {
		Graphics2D g = (Graphics2D)getGraphics();
		g.setXORMode(getBackground()); // �������� �׸�
		
		if (this.eSelectedTool == ESelectedTool.rectangle) {
			g.drawRect(x, y, w, h);
		}
		if (this.eSelectedTool == ESelectedTool.ellipse) {
			g.drawOval(x, y, w, h);
		}
		if (this.eSelectedTool == ESelectedTool.line) {
			g.drawLine(x, y, x+w, y+h);
		}
		if(this.eSelectedTool == ESelectedTool.text){
			JTextField textField = new JTextField();
			textField.setBackground(getBackground());
			textField.setSize(w, h);
			textField.setLocation(x, y);
			textField.setVisible(true);
			g.drawRect(x, y, w, h);
			this.add(textField);	
		}

	}
	
	private void drawPoly(List<Integer> xPoints, List<Integer> yPoints, Polygon polygon){
		Graphics2D g = (Graphics2D)this.getGraphics();
//		g.setXORMode(getBackground());
		
		int length = xPoints.size();
		System.out.println("length: "+length);
		int xArray[] = new int[length];
		int yArray[] = new int[length];
		
		for(int i=0; i<length; i++){ // ���͸� �迭�� �ٲ�
			xArray[i] = xPoints.get(i);
			yArray[i] = yPoints.get(i);
			System.out.println("xArray["+i+"]: "+xArray[i]+" yArray["+i+"]: "+yArray[i]);
		}
		if(polygon == Polygon.polyline){
			g.drawPolyline(xArray, yArray, length);
		}else if(polygon == Polygon.polygon){
			g.drawPolygon(xArray, yArray, length);
		}
	}
	
	class MouseEventHandler 
		implements MouseInputListener, MouseMotionListener {

		private EState eState = EState.idle;
		private Click click = Click.oneClick;
		private Start start = Start.no;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (eState == EState.idle && eSelectedTool != ESelectedTool.pencil) {
				initDrawing(e.getX(), e.getY());
				eState = EState.drawing;
			} else if (eState == EState.drawing) {		
				finishDrawing(e.getX(), e.getY());
				draw(x1, y1, e.getX()-x1, e.getY()-y1);
				eState = EState.idle;
			}
			
			// polygon
			if(eSelectedTool == ESelectedTool.polygon){
				if(click == Click.oneClick){
					continueDrawing(e.getX(), e.getY());
					drawPoly(xPoints, yPoints, Polygon.polyline);
					start = Start.yes;
				}else if(click == Click.doubleClick){
					xPoints.clear();
					yPoints.clear();
					click = Click.oneClick;
					start = Start.no;
				}
			} 
			
			// pencil
			if(eSelectedTool == ESelectedTool.pencil){
				if(click == Click.oneClick){
					continueDrawing(e.getX(), e.getY());
//					drawPoly(xPoints, yPoints, Polygon.polyline);
					eState = EState.drawing;
					start = Start.yes;
				}else if(click == Click.doubleClick){
					xPoints.clear();
					yPoints.clear();
					click = Click.oneClick;
					start = Start.no;
				}
			} 
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getClickCount() == 2 && eSelectedTool == ESelectedTool.polygon){
				System.out.println("double click");
				drawPoly(xPoints, yPoints, Polygon.polygon);
				click = Click.doubleClick;
			}
			
			if(e.getClickCount() == 2 && eSelectedTool == ESelectedTool.pencil){
				System.out.println("double click");
//				drawPoly(xPoints, yPoints, Polygon.polyline);
				click = Click.doubleClick;
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawing){			
				keepDrawing(e.getX(), e.getY());
			}
			
			if (eSelectedTool == ESelectedTool.pencil && eState == EState.drawing && start == Start.yes){			
				keepDrawingPoly(e.getX(), e.getY());
			}
		}		
		@Override
		public void mouseDragged(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}
}
