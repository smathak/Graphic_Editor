package Default;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	int x1, y1;
	int x2, y2;
	int width, height;
	boolean clicked;
	ToolBar toolBar;

	public DrawingPanel(ToolBar toolBar) throws IOException{
		this.toolBar = toolBar;
		clicked=false;

		MouseEventHandler mouse = new MouseEventHandler();
		addMouseListener(mouse);
	}
	
	public void paint(Graphics g){
		width = Math.abs(x2-x1);
		height = Math.abs(y2-y1);

		if(x1<=x2 && y1<=y2){
			g.drawRect(x1, y1, width, height);
		}else if(x1>=x2 && y1>=y2){
			g.drawRect(x2, y2, width, height);
		}else if(x1<=x2 && y1>=y2){
			g.drawRect(x1, y2, width, height);
		}else if(x1>=x2 && y1<=y2){
			g.drawRect(x2, y1, width, height);
		}
	}
	
	class MouseEventHandler implements MouseInputListener, MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
			
			if(clicked==false){
				x1 = event.getX();
				y1 = event.getY();
				clicked = true;
				
				System.out.println("clicked1 x1: "+x1+" y1: "+y1);
			}else{
				x2 = event.getX();
				y2 = event.getY();
				clicked = false;
				
				System.out.println("clicked2 x2: "+x2+" y2: "+y2);
			}
			
			if(clicked == false && toolBar.Rectangle.isSelected() == true){
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			x2 = e.getX();
			y2 = e.getY();
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
