package frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants;
import constants.Constants.EDrawingType;
import constants.Constants.EState;
import constants.Constants.EToolBar;
import constants.Constants.Start;
import shapes.*;

public class DrawingPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	private Vector<GShape> shapeVector;

	// associative attributes
	private GShape selectedShape;
	// working objects;
	private GShape currentShape;

	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
	}
	
	private EToolBar eSelectedTool;
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
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




	class MouseEventHandler extends JPanel
		implements MouseInputListener, MouseMotionListener {
		private static final long serialVersionUID = 1L;
		private EState eState = EState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(selectedShape.geteDrawingType() == EDrawingType.NP){
				if (e.getClickCount() == 1) {
					mouse1Clicked(e);
				} else if (e.getClickCount() == 2) {
					System.out.println("double clicked");
					mouse2Clicked(e);
				}
			}
		}
		
		public void mouse1Clicked(MouseEvent e){
			if (eState == EState.idle) {
				initDrawing(e.getX(), e.getY());
				System.out.println("mouse1Clicked/"+eState);
				eState  = EState.drawing;
			} 
			else if (eState == EState.drawing) {	
				continueDrawing(e.getX(), e.getY());			
				System.out.println("mouse1Clicked/drawing");
			}
		}
		

		public void mouse2Clicked(MouseEvent e){
			if (eState == EState.drawing) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idle;
				System.out.println("mouse2Clicked/drawing");
			}	
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// 직사각형, 타원, 선, pencil
			if (eState == EState.idle && selectedShape.geteDrawingType() != EDrawingType.NP) {
				initDrawing(e.getX(), e.getY());
				eState = EState.drawing;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawing){	
				keepDrawing(e.getX(), e.getY());
			}

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// 직사각형, 타원, 선, pencil
			if (eState == EState.drawing && selectedShape.geteDrawingType() != EDrawingType.NP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idle; // 여기서 idle로 바뀌는 구나....
			}

		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawing) 
			{
				keepDrawing(e.getX(), e.getY());
				System.out.println("mouseMoved/"+eState);
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
		// TODO Auto-generated method stub
		
	}
}
