package Default;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	DrawingPanel drawingPanel;
	
	public void setDrawingPanel(DrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public ToolBar() throws IOException{
//		Tool bar fixed
		this.setFloatable(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		
//		 add buttons
		JRadioButton Rectangle = new JRadioButton();
		Rectangle.setIcon(new ImageIcon("rsc/rectangle.png"));
		Rectangle.setSelectedIcon(new ImageIcon("rsc/rectangleSelected.PNG"));
		this.add(Rectangle);
		buttonGroup.add(Rectangle);
		Rectangle.setActionCommand("rectangle");
		Rectangle.addActionListener(actionHandler);
		
		Image ellipse = ImageIO.read(getClass().getResource("ellipse.png"));
		Image ellipse2 = ellipse.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		Image ellipseSelected = ImageIO.read(getClass().getResource("ellipseSelected.png"));
		Image ellipseSelected2 = ellipseSelected.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		JRadioButton Ellipse = new JRadioButton();
		Ellipse.setIcon(new ImageIcon(ellipse2));
		Ellipse.setSelectedIcon(new ImageIcon(ellipseSelected2));
		this.add(Ellipse);
		buttonGroup.add(Ellipse);
		Ellipse.setActionCommand("ellipse");
		Ellipse.addActionListener(actionHandler);
		
		JRadioButton Line = new JRadioButton();
		Line.setIcon(new ImageIcon("rsc/line.png"));
		Line.setSelectedIcon(new ImageIcon("rsc/lineSelected.PNG"));
		this.add(Line);
		buttonGroup.add(Line);
		Line.setActionCommand("line");
		Line.addActionListener(actionHandler);
		
		JRadioButton Polygon = new JRadioButton();
		Polygon.setIcon(new ImageIcon("rsc/polygon.PNG"));
		Polygon.setSelectedIcon(new ImageIcon("rsc/polygonSelected.PNG"));
		this.add(Polygon);
		buttonGroup.add(Polygon);
		Polygon.setActionCommand("polygon");
		Polygon.addActionListener(actionHandler);
		
		JRadioButton Curve = new JRadioButton();
		Curve.setIcon(new ImageIcon("rsc/curve.PNG"));
		Curve.setSelectedIcon(new ImageIcon("rsc/curveSelected.PNG"));
		this.add(Curve);
		buttonGroup.add(Curve);
		Curve.setActionCommand("curve");
		Curve.addActionListener(actionHandler);
		
		JRadioButton Eraser = new JRadioButton();
		Eraser.setIcon(new ImageIcon("rsc/eraser.PNG"));
		Eraser.setSelectedIcon(new ImageIcon("rsc/eraserSelected.PNG"));
		this.add(Eraser);
		buttonGroup.add(Eraser);
		Eraser.setActionCommand("eraser");
		Eraser.addActionListener(actionHandler);
		
		JRadioButton Paint = new JRadioButton();
		Paint.setIcon(new ImageIcon("rsc/paint.jpg"));
		Paint.setSelectedIcon(new ImageIcon("rsc/paintSelected.PNG"));
		this.add(Paint);	
		buttonGroup.add(Paint);
		Paint.setActionCommand("paint");
		Paint.addActionListener(actionHandler);
		
		Image brush = ImageIO.read(getClass().getResource("brush.jpg"));
		Image brush2 = brush.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		Image brushSelected = ImageIO.read(getClass().getResource("brushSelected.jpg"));
		Image brushSelected2 = brushSelected.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
		JRadioButton Brush = new JRadioButton();
		Brush.setIcon(new ImageIcon(brush2));
		Brush.setSelectedIcon(new ImageIcon(brushSelected2));
		this.add(Brush);
		buttonGroup.add(Brush);
		Brush.setActionCommand("brush");
		Brush.addActionListener(actionHandler);
		
		JRadioButton Text = new JRadioButton();
		Text.setIcon(new ImageIcon("rsc/text.png"));
		Text.setSelectedIcon(new ImageIcon("rsc/textSelected.PNG"));
		this.add(Text);
		buttonGroup.add(Text);
		Text.setActionCommand("text");
		Text.addActionListener(actionHandler);
		
		JRadioButton Pencil = new JRadioButton();
		Pencil.setIcon(new ImageIcon("rsc/pencil.PNG"));
		Pencil.setSelectedIcon(new ImageIcon("rsc/pencilSelected.PNG"));
		this.add(Pencil);
		buttonGroup.add(Pencil);
		Pencil.setActionCommand("pencil");
		Pencil.addActionListener(actionHandler);
		
		JRadioButton MagnifyingGlass = new JRadioButton();
		MagnifyingGlass.setIcon(new ImageIcon("rsc/magnifyingGlass.png"));
		MagnifyingGlass.setSelectedIcon(new ImageIcon("rsc/magnifyingSelected.PNG"));
		this.add(MagnifyingGlass);
		buttonGroup.add(MagnifyingGlass);
		MagnifyingGlass.setActionCommand("magnifyingGlass");
		MagnifyingGlass.addActionListener(actionHandler);
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("rectangle")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.rectangle);
			}else if(e.getActionCommand().equals("ellipse")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.ellipse);
			}else if(e.getActionCommand().equals("line")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.line);
			}
			else if(e.getActionCommand().equals("text")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.text);
			}else if(e.getActionCommand().equals("polygon")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.polygon);
			}else if(e.getActionCommand().equals("pencil")){
				drawingPanel.setESelectedTool(DrawingPanel.ESelectedTool.pencil);
			}
		}
		
	}
}
