package shapes;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import constants.Constants.EAnchors;
import constants.Constants.ECursor;
import frame.DrawingPanel;

public class GCursor implements Serializable{
	public GCursor(){
	}
	ECursor eCursor;
	public DrawingPanel drawingPanel;
	public void setDrawingPanel(DrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
		eCursor = ECursor.roundArrow;
	}
	
	public void changeCursor(EAnchors eAnchor) throws IOException {
		switch(eAnchor){
		case NN:
			drawingPanel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			return;
		case NE:
			drawingPanel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			return;
		case NW:
			drawingPanel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			return;
		case SS:
			drawingPanel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			return;
		case SE:
			drawingPanel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
			return;
		case SW:
			drawingPanel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
			return;
		case RR: 
			Image cursor = ImageIO.read(new File(eCursor.getCursurImage()));
			Cursor customCursor = Toolkit.getDefaultToolkit()
					.createCustomCursor(cursor, new Point(0, 0), "CUSTOM_CURSOR");
			drawingPanel.setCursor(customCursor);
			return;
		case WW:
			drawingPanel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			return;
		case EE:
			drawingPanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			return;
		case MM:
			drawingPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			return;
		default:
			break;
		}
	}
}
	
//Component glassPane = ((RootPaneContainer) myPanel.getTopLevelAncestor()).getGlassPane();
//glassPane.setCursor(new Cursor(Cursor.MOVE_CURSOR));
//glassPane.setVisible(true);
//System.out.println(drawingPanel); 


//	class MouseEventHandler implements MouseInputListener, MouseMotionListener{
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			if(eState == EState.idleNP || eState == EState.idleTP){
//				try {
//					changeCursor(e.getX(), e.getY());
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//
//		@Override
//		public void mouseClicked(MouseEvent arg0) {
//		
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent arg0) {
//	
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//		
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//		
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//		}
//
//		@Override
//		public void mouseDragged(MouseEvent e) {
//		
//		}
//	}


