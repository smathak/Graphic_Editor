package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.Constants.EAnchors;
import constants.Constants.EColor;
import constants.Constants.EDrawingType;
import constants.Constants.ESideBar;
import constants.Constants.EState;
import constants.Constants.IsText;
import shapes.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;


public class DrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	// associative attributes
	public Vector<GShape> shapeVector;
	public Vector<GShape> colorVector;
	public Vector<GShape> saveVector;
	private GShape selectedShape;
	// working objects;
	private GShape currentShape;
	public EState eState;
	EColor eColor;
	IsText isText;
	TextArea textArea;
	private ESideBar eSelectedColor;
	private GTransformer currentTranformer;
	
	public DrawingPanel() {
		super();
		this.eState = EState.idle;
		this.eColor = EColor.colorNo;
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeVector = new Vector<GShape>();
		this.colorVector = new Vector<GShape>();
		this.saveVector = new Vector<GShape>();
		this.setOpaque(false);
		isText = null;
		
		this.currentTranformer = null;
	}
	
	
	public void setSelectedShape(GShape selectedShape) {
		eState = EState.idle;
		this.selectedShape = selectedShape;
	}	
	public void setSelectedText(){
		isText = IsText.textYes; // 지금 부터 TextField를 그릴 것이다. 
	}
	
	public Vector<GShape> getShapeVector() {
		for(GShape shape : colorVector){
			this.shapeVector.add(shape); // shapeVector와 ColorVector를 합쳐서 반환
		}
		this.colorVector.clear();
		return this.shapeVector;
	}
	public Vector<GShape> getColorVector() {
		return this.colorVector;
	}

	// 그림을 그릴 때는 이 함수가 반드시 계속 호출됨
	public void paint(Graphics g) {
		for (GShape shape: this.shapeVector) {
			 shape.draw((Graphics2D)g); // Casting
		}
		for (GShape shape: this.colorVector) {
			 shape.setEColor();
			 shape.draw((Graphics2D)g); // Casting
		}
	}
	
	private void resetSelected(){
		for(GShape shape : this.shapeVector){
			shape.setSelected(false);
		}
		for(GShape shape : this.colorVector){
			shape.setSelected(false);
		}
		repaint(); 
	}

	// 애니메이션이 있는 그림그리기 3단계
	// 1. 초기 좌표 설정
	private void initTransforming(int x, int y) {
		if(this.currentShape == null){ // 그림을 그린다 .
			this.currentShape= this.selectedShape.clone();
			this.currentTranformer = new GDrawer(this.currentShape);
		} else if(this.currentShape.getCurrentEAnchor() == EAnchors.MM){
			this.currentTranformer = new GMover(this.currentShape);
		}else if(this.currentShape.getCurrentEAnchor() == EAnchors.RR){
			this.currentTranformer = new GRotator(this.currentShape);
		}else{
			this.currentTranformer = new GResizer(this.currentShape);
		}
		
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		
		this.resetSelected(); // initDrawing에서 전부다 false로 만들어 다른 그림을 그릴 때는 anchors를 없애버린다. 
		this.currentTranformer.initTransforming(x, y, g2D);
	}
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTranformer.keepTransforming(x, y, g2D);
	}
	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTranformer.continueTransforming(x, y, g2D);
	}
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentTranformer.finishTransforming(x, y, g2D);
		System.out.println("finish: "+this.currentTranformer.getClass().getSimpleName().equals("GDrawer"));
		if(this.currentTranformer.getClass().getSimpleName().equals("GDrawer") || eState == EState.copyAfter){ // 이것때문에 그러구나...
			if(eColor == eColor.colorYes){
				this.colorVector.add(this.currentShape);
			}else{
				System.out.println("check add"); // 여기가 안되네....
				this.shapeVector.add(this.currentShape);
			}
			
		}
		for(GShape shape : shapeVector){
			System.out.println("shapeVector: "+shape);
		}
		System.out.println();
		this.currentShape.setSelected(true);
	
		this.repaint();
	}
	
	public void selectAll(){
		for(GShape shape : shapeVector){
			shape.setSelected(true);
		}
		repaint();
	}
	
	public void undo(){
		// shapeVector의 가장 마지막에 있는 Object(GShape)를 지움
		for(GShape shape : this.colorVector){
			this.shapeVector.add(shape);
		}
		this.colorVector.clear();

		int last = this.shapeVector.size()-1;
		this.saveVector.add(this.shapeVector.get(last));
		this.shapeVector.remove(last);
		repaint();
	}
	

	public void redo() {
		int last = this.saveVector.size()-1;
		this.shapeVector.add(this.saveVector.get(last));
		this.saveVector.remove(last);
		repaint();
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
		
		for(GShape shape : this.colorVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				shape.changeCursor(eAnchor, this);
			}else{
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	private GShape onShape(int x, int y){ // mousePressed -> 어떤 앵커가 selected?
		System.out.println("onShape");
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y); // 커서가 어떤 도형 위에 있는가? 
			if(eAnchor != null)  // 있음
				return shape; // 그 도형을 리턴
		}
		
		for(GShape shape : this.colorVector){
			EAnchors eAnchor = shape.contains(x, y); // 커서가 어떤 도형 위에 있는가? 
			if(eAnchor != null)  // 있음
				return shape; // 그 도형을 리턴
		}
		return null;
		// 함수는 절대적으로 하나만 반환함. 그런데 앵커와 shape이 둘다 필요. 따라서 shape만 돌려받고 shape 안에 anchor를 저장해서 반환함 
	}

	
	public void setSelectedColor(ESideBar eSelectedColor) {
		this.eSelectedColor = eSelectedColor;
		this.currentShape.setSelectedColor(eSelectedColor);
		eColor = EColor.colorYes;
		repaint();
	}
	public void setSelectedFill(){
		eState = EState.fill;
	}
	public void setSelectedCopy() {
		eState = EState.copy;
	}
	
	GShape copyShape;
	public void selectCopy(int x, int y){
		System.out.println("selectCopy");
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				eState = EState.copyAfter;
				copyShape = shape;
			}
		}
		
		for(GShape shape : this.colorVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				eState = EState.copyAfter;
				copyShape = shape;
			}
		}
	}
	
	private void initCopying(int x, int y, int w, int h){
		this.resetSelected(); // 이전 anchors들을 지움 
		this.currentShape= this.selectedShape.clone(); // 새 도형을 하나 만듬
		System.out.println("initCopying: "+currentShape);
		this.currentShape.initCopying(x, y, w, h); // 새 도형을 새좌표와 복사하려는 도형의 너비, 높이로 초기화
	}
	
	// initCopying for polygon 
	private void initCopying(GShape copyPolygon, int x, int y){
		this.resetSelected(); // 이전 anchors들을 지움 
		for(int i=0; i<shapeVector.size(); i++){
			System.out.println("shapeVecter: "+shapeVector.get(i));
		}
		this.currentShape= this.selectedShape.clone(); // 새 도형을 하나 만듬
		this.currentShape.initCopying(copyShape, x, y); // 새 도형을 새좌표와 복사하려는 도형의 너비, 높이로 초기화
		repaint();
	}
	
	public void paste(){ // currentShape 이 아니라, onShape을 찾아야 함 
		this.resetSelected();
		
		int px = this.currentShape.getShape().getBounds().x;
		int py = this.currentShape.getShape().getBounds().y;
		int w = this.currentShape.getShape().getBounds().width; // 그 도형의 너비
		int h = this.currentShape.getShape().getBounds().height; // 그 도형의 높이를 get
		
		this.currentShape = this.selectedShape.clone(); // 새 객체를 생성
		this.currentShape.initCopying(px+50, py+50, w, h);
		
		if(eColor == EColor.colorYes){
			this.colorVector.add(this.currentShape);
		}else{
			this.shapeVector.add(this.currentShape);
		}
		
		repaint();

	}
	
	private void drawCopy(int x, int y) { // 새 좌표
		int w = copyShape.getShape().getBounds().width;
		int h = copyShape.getShape().getBounds().height;
		
		eSelectedColor = copyShape.getColor();


		System.out.println("c: "+eSelectedColor);

		
		System.out.println("drawCopy w: "+w+" h: "+h);
		if(this.copyShape.geteDrawingType() == EDrawingType.NP){
			initCopying(copyShape, x, y);
		}else{
			initCopying(x, y, w, h); // 새 좌표로 초기화 , w,h도 넘겨줘야 함 
		}
		
		finishTransforming(x, y); // vector에 add
		System.out.println("call finish check");
	} 

	private void fillShape(int x, int y) {
		for(GShape shape : this.shapeVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				shape.setSelectedColor(eSelectedColor);
				shape.fillShape();
			}
		}
		
		for(GShape shape : this.colorVector){
			EAnchors eAnchor = shape.contains(x, y);
			if(eAnchor != null){
				shape.setSelectedColor(eSelectedColor);
				shape.fillShape();
			}
		}
		repaint();
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
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					if(selectedShape.geteDrawingType() == EDrawingType.NP){ // polygon filtering
						initTransforming(e.getX(), e.getY());
						eState  = EState.drawingNP;
					}
				}
			} 
			else if (eState == EState.drawingNP) {	
				continueTransforming(e.getX(), e.getY());			
			}
		}

		public void mouse2Clicked(MouseEvent e){
			if (eState == EState.drawingNP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}	
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idle) {
				currentShape = onShape(e.getX(), e.getY());
				if(currentShape == null){
					if (selectedShape.geteDrawingType() == EDrawingType.TP){ // polygon filtering
						initTransforming(e.getX(), e.getY());
						eState = EState.drawingTP;				
					}			
				}else{
					initTransforming(e.getX(), e.getY());
					eState = EState.Transforming;
				}
			}		
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			}else if(eState == EState.Transforming){
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP) {		
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;
			}else if(eState == EState.Transforming){
				finishTransforming(e.getX(), e.getY());
				eState = EState.idle;;
			}else if(eState == EState.fill){
				fillShape(e.getX(), e.getY());
			}else if(eState == EState.copy){
				System.out.println(eState);
				selectCopy(e.getX(), e.getY()); // copy mode 로 바꿈 
			}else if(eState == EState.copyAfter){
				System.out.println(eState);
				drawCopy(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP){
				keepTransforming(e.getX(), e.getY());
			}
			else if(eState == EState.idle){
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
