package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants.EAnchors;
import constants.Constants.EDrawingType;
import constants.Constants.EState;
import shapes.GShape;

public class DrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	public Vector<GShape> shapeVector;
	private GShape selectedShape;
	// working objects;
	private GShape currentShape;
	public EState eState;
	
	public DrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
		this.setOpaque(false);
	}
	
	public EState getEState(){
		return this.eState;
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
	
	private void resetSelected(){
		for(GShape shape : this.shapeVector){
			shape.setSelected(false);
		}
		repaint(); 
	}

	// 애니메이션이 있는 그림그리기 3단계
	// 1. 초기 좌표 설정
	private void initDrawing(int x, int y) {
		this.resetSelected();
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
		this.currentShape.setSelected(true);
	}
	
	private void continueDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.continueDrawing(x, y, g2D);
	}
	
	public void changeCursor(int x, int y) throws IOException {
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				shape.changeCursor(eAnchor, this);
			}else{
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	private GShape onShape(int x, int y){ // mousePressed -> 어떤 앵커가 selected?
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y); // 커서가 어떤 도형 위에 있는가? 
			if(eAnchor != null)  // 있음
				return shape; // 그 도형을 리턴
		}
		return null;
		// 함수는 절대적으로 하나만 반환함. 그런데 앵커와 shape이 둘다 필요. 따라서 shape만 돌려받고 shape 안에 anchor를 저장해서 반환함 
	}
	
	private void initTransforming(int x, int y) { // currentShape에서 mousePressed가 일어남 
		this.resetSelected(); 
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		switch(this.currentShape.getCurrentEAnchor()){ // 여기서 오류
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SW:
			break;
		case RR:
			break;
		case WW:
			break;
		case EE:
			break;
		case MM:
			this.currentShape.initTransforming(x, y, g2D);
			break;
		default:
			break;
		}
	}
	
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
	
		switch(this.currentShape.getCurrentEAnchor()){
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SW:
			break;
		case RR:
			break;
		case WW:
			break;
		case EE:
			break;
		case MM:
			this.currentShape.keepTransforming(x, y, g2D);
			break;
		default:
			break;
		}
	}
	
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());

		switch(this.currentShape.getCurrentEAnchor()){
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SW:
			break;
		case RR:
			break;
		case WW:
			break;
		case EE:
			break;
		case MM:
			this.currentShape.finishTransforming(x, y, g2D);
			break;
		}
		this.currentShape.setSelected(true);
		this.repaint();
	}
	
	class MouseEventHandler extends JPanel
		implements MouseInputListener, MouseMotionListener {
		private static final long serialVersionUID = 1L;
		
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
			if (eState == EState.idleTP) {
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					initDrawing(e.getX(), e.getY());
					eState = EState.drawingTP;				
				}else{
					initTransforming(e.getX(), e.getY());
					eState = EState.Transforming;
				}
			}		
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepDrawing(e.getX(), e.getY());
			}else if(eState == EState.Transforming){
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {		
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP;
			}else if(eState == EState.Transforming){
				finishTransforming(e.getX(), e.getY());
				eState = EState.idleTP;;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP){
				keepDrawing(e.getX(), e.getY());
			}
			else if(eState == EState.idleNP || eState == EState.idleTP){
				try {
					changeCursor(e.getX(), e.getY());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
