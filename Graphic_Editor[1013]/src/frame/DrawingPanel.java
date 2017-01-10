package frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants;
import constants.Constants.EToolBar;
import shapes.*;

public class DrawingPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	
		
	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	private EToolBar eSelectedTool;
	public void setESelectedTool(Constants.EToolBar eToolBar){
		this.eSelectedTool = eToolBar;
	}
	
	public void paint(Graphics g) {
	}
	// working variable (계속 바뀌는 값, 저장할 필요가 없는 값)
	private List<Integer> xPoints = new Vector<Integer>();
	private List<Integer> yPoints = new Vector<Integer>();
	
	private int x1, y1, x2, y2;
	
	// 애니메이션이 있는 그림그리기 3단계
	// 1. 초기 좌표 설정
	private void initDrawing(int x, int y){ // 매개변수는 마우스 포지션
		x1 = x;
		y1 = y;
		x2 = x; 
		y2 = y; 
	}
	// 2. keep drawing(애니메이션)
	private void keepDrawing(int x, int y){ // 공간 디바이스(마우스)의 좌표는 이거밖에 없다
		draw(x1, y1, x2-x1, y2-y1); // 전 좌표로 그림을 그림
		draw(x1, y1, x-x1, y-y1); // 새좌표로 그림
		
		x2 = x; // 새 좌표를 전 좌표에 저장
		y2 = y;
		//계속 반복
	}
	
	private void keepDrawingPoly(int x, int y){
		if(eSelectedTool == EToolBar.pencil){
			drawPoly(xPoints, yPoints, Constants.Polygon.polyline);
			continueDrawing(x, y);
			drawPoly(xPoints, yPoints, Constants.Polygon.polyline);
		}
	}
	
	private void continueDrawing(int x, int y){ //for drawing polygon
		// 새 점을 벡터에 저장
		xPoints.add(x);
		yPoints.add(y);
		
		System.out.println("x: "+x+"y: "+y);
	}
	
	// 3. 최종 도형 그리기 
	private void finishDrawing(int x, int y){
		draw(x1, y1, x-x1, y-y1); // draw 함수 호출
	}

	private void draw(int x, int y, int w, int h) {
		Graphics2D g = (Graphics2D)getGraphics();
		g.setXORMode(getBackground()); // 배경색으로 그림
		
		if (this.eSelectedTool == EToolBar.rectangle) { // 과제: 여기서 그림을 그리지 말고 Rectangle 객체에서 그리게 해라
//			g.drawRect(x, y, w, h);
			Rectangle rectangle = new Rectangle();
			rectangle.setLocation(x, y);
			rectangle.setDimension(w, h);
			rectangle.draw(g);
		}
		if (this.eSelectedTool ==  EToolBar.ellipse) {
			Ellipse ellipse = new Ellipse();
			ellipse.setLocation(x, y);
			ellipse.setDimension(w, h);
			ellipse.draw(g);
		}
		if (this.eSelectedTool == EToolBar.line) {
			Line line = new Line();
			line.setLocation(x, y);
			line.setDimension(w, h);
			line.draw(g);
		}
		if(this.eSelectedTool == EToolBar.text){
			Text text = new Text();
			text.setLocation(x, y);
			text.setDimension(w, h);
			
			this.add(text);
			text.draw(g);
			
		}

	}
	
	private void drawPoly(List<Integer> xPoints, List<Integer> yPoints, Constants.Polygon polygon){
		Graphics2D g = (Graphics2D)this.getGraphics();
//		g.setXORMode(getBackground());
		
		int length = xPoints.size();
		System.out.println("length: "+length);
		int xArray[] = new int[length];
		int yArray[] = new int[length];
		
		for(int i=0; i<length; i++){ // 벡터를 배열로 바꿈
			xArray[i] = xPoints.get(i);
			yArray[i] = yPoints.get(i);
			System.out.println("xArray["+i+"]: "+xArray[i]+" yArray["+i+"]: "+yArray[i]);
		}
		if(polygon == Constants.Polygon.polyline){
			g.drawPolyline(xArray, yArray, length);
		}else if(polygon == Constants.Polygon.polygon){
			g.drawPolygon(xArray, yArray, length);
		}
	}
	
	class MouseEventHandler 
		implements MouseInputListener, MouseMotionListener {

		private Constants.EState eState = Constants.EState.idle;
		private Constants.Click click = Constants.Click.oneClick;
		private Constants.Start start = Constants.Start.no;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (eState == Constants.EState.idle && eSelectedTool != EToolBar.pencil) {
				initDrawing(e.getX(), e.getY());
				eState = Constants.EState.drawing;
			} else if (eState == Constants.EState.drawing) {		
				finishDrawing(e.getX(), e.getY());
				draw(x1, y1, e.getX()-x1, e.getY()-y1);
				eState = Constants.EState.idle;
			}
			
			// polygon
			if(eSelectedTool == EToolBar.polygon){
				if(click == Constants.Click.oneClick){
					continueDrawing(e.getX(), e.getY());
					drawPoly(xPoints, yPoints, Constants.Polygon.polyline);
					start = Constants.Start.yes;
				}else if(click == Constants.Click.doubleClick){
					xPoints.clear();
					yPoints.clear();
					click = Constants.Click.oneClick;
					start = Constants.Start.no;
				}
			} 
			
			// pencil
			if(eSelectedTool == EToolBar.pencil){
				if(click == Constants.Click.oneClick){
					continueDrawing(e.getX(), e.getY());
//					drawPoly(xPoints, yPoints, Polygon.polyline);
					eState = Constants.EState.drawing;
					start = Constants.Start.yes;
				}else if(click == Constants.Click.doubleClick){
					xPoints.clear();
					yPoints.clear();
					click = Constants.Click.oneClick;
					start = Constants.Start.no;
				}
			} 
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getClickCount() == 2 && eSelectedTool == EToolBar.polygon){
				System.out.println("double click");
				drawPoly(xPoints, yPoints, Constants.Polygon.polygon);
				click = Constants.Click.doubleClick;
			}
			
			if(e.getClickCount() == 2 && eSelectedTool == EToolBar.pencil){
				System.out.println("double click");
//				drawPoly(xPoints, yPoints, Polygon.polyline);
				click = Constants.Click.doubleClick;
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == Constants.EState.drawing){			
				keepDrawing(e.getX(), e.getY());
			}
			
			if (eSelectedTool == EToolBar.pencil && eState == Constants.EState.drawing && start == Constants.Start.yes){			
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
