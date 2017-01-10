package frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants;
import constants.Constants.EToolBar;
import constants.Constants.Poly;
import constants.Constants.Start;
import shapes.*;

public class DrawingPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	private Vector<GShape> shapeVector;
	private Vector<Polygon> polyVector;
	
	// associative attributes
	private GShape selectedShape;
	// working objects;
	private GShape currentShape;
		
	Polygon polygon;

	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
		this.polyVector = new Vector<Polygon>();
	}
	
	private EToolBar eSelectedTool;
	public void setESelectedTool(EToolBar eSelectedTool) {
		this.eSelectedTool = eSelectedTool;
		switch (eSelectedTool) {
		case rectangle:
			this.selectedShape = new Rectangle();
			break;
		case ellipse:
			this.selectedShape = new Ellipse();
			break;
		case line:
			this.selectedShape = new Line();
			break;
		case polygon:
			this.polygon = new Polygon();
			break;
		case pencil:
			this.polygon= new Polygon();
			this.selectedShape = new Pencil();
			break;
		case text:
			break;
		default:
			break;
		}	
	}	

	public void paint(Graphics g) {
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g); // Casting
		}
	}

	// 애니메이션이 있는 그림그리기 3단계
	// 1. 초기 좌표 설정
	private void initDrawing(int x, int y) {
		this.currentShape= this.selectedShape.clone();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.initDrawing(x, y, g2D);
	}
	private void keepDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.keepDrawing(x, y, g2D);
	}
	private void finishDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.finishDrawing(x, y, g2D);
		this.shapeVector.add(this.currentShape);
	}
	
//	private void keepDrawingPoly(int x, int y){
//		if(eSelectedTool == EToolBar.pencil){
//			drawPoly(xPoints, yPoints, Constants.Polygon.polyline);
//			continueDrawing(x, y);
//			drawPoly(xPoints, yPoints, Constants.Polygon.polyline);
//		}
//	}
	


	private void drawPoly(Poly poly){
		Graphics2D g2d = (Graphics2D)this.getGraphics();
		polygon.drawPoly(g2d, poly);
		shapeVector.add(polygon);
	}
	
	private void clearPoints(){
		polygon.clearPoints();
	}

	class MouseEventHandler extends JPanel
		implements MouseInputListener, MouseMotionListener {
		private static final long serialVersionUID = 1L;
		private Constants.EState eState = Constants.EState.idle;
		private Constants.Click click = Constants.Click.oneClick;
		private Constants.Start start = Constants.Start.no;
		Graphics2D g2d = (Graphics2D)this.getGraphics(); // 여기서 애초에 널이네....
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// polygon
			if(eSelectedTool == EToolBar.polygon){
				System.out.println(g2d);
				if(click == Constants.Click.oneClick){
					polygon.initDrawing(e.getX(), e.getY(), g2d);
					drawPoly(Poly.polyline);
					start = Constants.Start.yes;
				}else if(click == Constants.Click.doubleClick){
					clearPoints();
					click = Constants.Click.oneClick;
					start = Constants.Start.no;
				}
			} 
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// 직사각형, 타원, 선
			if (eState == Constants.EState.idle && eSelectedTool != EToolBar.polygon) {
				initDrawing(e.getX(), e.getY());
				eState = Constants.EState.drawing;
			}

						
			// double click 하면 polygon 그리기 끝
			if(e.getClickCount() == 2 && eSelectedTool == EToolBar.polygon){
				System.out.println("double click");
				drawPoly(Poly.polygon);
				click = Constants.Click.doubleClick;
			}
			
			// pencil
			if(eSelectedTool == EToolBar.pencil){
				initDrawing(e.getX(), e.getY());
//				drawPoly(xPoints, yPoints, Polygon.polyline);
//				eState = Constants.EState.drawing;
				start = Constants.Start.yes;
			}

			//pencil
//			if(e.getClickCount() == 2 && eSelectedTool == EToolBar.pencil){
//				System.out.println("double click");
////				drawPoly(xPoints, yPoints, Polygon.polyline);
//				click = Constants.Click.doubleClick;
//			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == Constants.EState.drawing){	
				keepDrawing(e.getX(), e.getY());
			}

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// 직사각형, 타원, 선
			if (eState == Constants.EState.drawing) {		
				finishDrawing(e.getX(), e.getY());
//				draw(x1, y1, e.getX()-x1, e.getY()-y1);
				eState = Constants.EState.idle;
			}
			
			// pencil
			if(eSelectedTool == EToolBar.pencil){
//				click = Constants.Click.oneClick;
				start = Constants.Start.no;
				clearPoints();
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
//			if (eSelectedTool == EToolBar.pencil && eState == Constants.EState.drawing && start == Constants.Start.yes){			
//				keepDrawingPoly(e.getX(), e.getY());
//			}
		}		
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
