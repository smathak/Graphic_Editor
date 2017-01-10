package frame;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants.Click;
import constants.Constants.EDrawingType;
import constants.Constants.EState;
import shapes.GShape;

public class DrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	private Vector<GShape> shapeVector;

	// associative attributes
	private GShape selectedShape;
	// working objects;
	private GShape currentShape;
	private EState eState;
	
	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
	}
	
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
		switch(this.selectedShape.geteDrawingType()){
		case TP: this.eState = EState.idleTP; break;
		case NP: this.eState = EState.idleNP; break;
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
	
	private void continueDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.continueDrawing(x, y, g2D);
	}
	
	private void changePointShape(int x, int y){
		for(GShape shape : this.shapeVector){
			if(shape.contains(x, y)){
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
			}else{
				Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(defaultCursor);
			}
		}
	}
	
	private void drawBoundRectangle(int x, int y){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		for(GShape shape : this.shapeVector){
			shape.drawBoundRectangle(x, y, g2D);
		}
	}

	class MouseEventHandler extends JPanel
		implements MouseInputListener, MouseMotionListener {
		private static final long serialVersionUID = 1L;
		private Click click = null;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(selectedShape.geteDrawingType() == EDrawingType.NP){
				if (e.getClickCount() == 1) {
					mouse1Clicked(e);
				} else if (e.getClickCount() == 2) {
					mouse2Clicked(e);
				}
			}
		}
		
		public void mouse1Clicked(MouseEvent e){

			if (eState == EState.idleNP) {
				if(selectedShape.geteDrawingType() == EDrawingType.NP){
					initDrawing(e.getX(), e.getY());
					eState  = EState.drawingNP;
				}
			} 
			else if (eState == EState.drawingNP) {	
				continueDrawing(e.getX(), e.getY());			
			}
		}

		public void mouse2Clicked(MouseEvent e){
			if (eState == EState.drawingNP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleNP;
			}	
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// 직사각형, 타원, 선, pencil
			if (eState == EState.idleTP) {
				initDrawing(e.getX(), e.getY());
				eState = EState.drawingTP;
			}
			
			if(click == Click.oneClick){
				drawBoundRectangle(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP){	
				keepDrawing(e.getX(), e.getY());
			}

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// 직사각형, 타원, 선, pencil
			if (eState == EState.drawingTP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP; 
				click = Click.oneClick;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP){
				keepDrawing(e.getX(), e.getY());
			}else if(eState == EState.idleNP || eState == EState.idleTP){
				changePointShape(e.getX(), e.getY());
			}
		}		
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}

	public void initialize() {
	
	}
}
