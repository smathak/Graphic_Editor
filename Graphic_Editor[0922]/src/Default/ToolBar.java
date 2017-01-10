package Default;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	JRadioButton Rectangle;
	
	public ToolBar() throws IOException{
//		Tool bar fix
		this.setFloatable(false);
		
//		 add buttons
		Rectangle = new JRadioButton("Rectangle");
		Rectangle.setIcon(new ImageIcon("rsc/rectangle.png"));
		Rectangle.setSelectedIcon(new ImageIcon("rsc/rectangleSelected.PNG"));
		this.add(Rectangle);
		

		JRadioButton Ellipse = new JRadioButton("Ellipse");
		Ellipse.setIcon(new ImageIcon("rsc/elllipse.png"));
		Ellipse.setSelectedIcon(new ImageIcon("rsc/ellipseSelected.PNG"));
		this.add(Ellipse);
		
		JRadioButton Line = new JRadioButton("Line");
		Line.setIcon(new ImageIcon("rsc/line.png"));
		Line.setSelectedIcon(new ImageIcon("rsc/lineSelected.PNG"));
		this.add(Line);
		
		JRadioButton Polygon = new JRadioButton("Polygon");
		Polygon.setIcon(new ImageIcon("rsc/polygon.PNG"));
		Polygon.setSelectedIcon(new ImageIcon("rsc/polygonSelected.PNG"));
		this.add(Polygon);
		

		JRadioButton Curve = new JRadioButton("Curve");
		Curve.setIcon(new ImageIcon("rsc/curve.PNG"));
		Curve.setSelectedIcon(new ImageIcon("rsc/curveSelected.PNG"));
		this.add(Curve);
		
		JRadioButton Eraser = new JRadioButton("Eraser");
		Eraser.setIcon(new ImageIcon("rsc/eraser.PNG"));
		Eraser.setSelectedIcon(new ImageIcon("rsc/eraserSelected.PNG"));
		this.add(Eraser);
		
		JRadioButton Paint = new JRadioButton("Paint");
		Paint.setIcon(new ImageIcon("rsc/paint.jpg"));
		Paint.setSelectedIcon(new ImageIcon("rsc/paintSelected.PNG"));
		this.add(Paint);	
		
		Image brush = ImageIO.read(getClass().getResource("brush.jpg"));
		Image brush2 = brush.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		Image brushSelected = ImageIO.read(getClass().getResource("brushSelected.jpg"));
		Image brushSelected2 = brushSelected.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		JRadioButton Brush = new JRadioButton("Brush");
		Brush.setIcon(new ImageIcon(brush2));
		Brush.setSelectedIcon(new ImageIcon(brushSelected2));
		this.add(Brush);
		
		JRadioButton Text = new JRadioButton("Text");
		Text.setIcon(new ImageIcon("rsc/text.png"));
		Text.setSelectedIcon(new ImageIcon("rsc/textSelected.PNG"));
		this.add(Text);
		
		JRadioButton Pencil = new JRadioButton("Pencil");
		Pencil.setIcon(new ImageIcon("rsc/pencil.PNG"));
		Pencil.setSelectedIcon(new ImageIcon("rsc/pencilSelected.PNG"));
		this.add(Pencil);
		
		JRadioButton MagnifyingGlass = new JRadioButton("MagnifyingGlass");
		MagnifyingGlass.setIcon(new ImageIcon("rsc/magnifyingGlass.png"));
		MagnifyingGlass.setSelectedIcon(new ImageIcon("rsc/magnifyingSelected.PNG"));
		this.add(MagnifyingGlass);
	}
	
	class ButtonEventHandler implements ItemListener{
		boolean clicked; // 맨 처음에는 아무것도 안 눌렀으므로 false
		
		@Override
		public void itemStateChanged(ItemEvent e) {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
				clicked = true;
				System.out.println("clicked:" +clicked);
		    }
		    else if (e.getStateChange() == ItemEvent.DESELECTED) {
		    	clicked = false;
				System.out.println("clicked:" +clicked);
		    }
		}
		public boolean getClicked(){
			return clicked;
		}
	}
}
