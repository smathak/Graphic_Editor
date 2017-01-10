package Default;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public ToolBar() throws IOException{
		//Tool bar fix
		this.setFloatable(false);
		
		// add buttons
		Image rectangle = ImageIO.read(getClass().getResource("rectangular.png"));
		Image recIcon = rectangle.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Rectangle = new JButton("Rectangle");
		Rectangle.setIcon(new ImageIcon("rsc/rectangle.png"));
		this.add(Rectangle);
		
		Image ellipse = ImageIO.read(getClass().getResource("ellipse.png"));
		Image ellIcon = ellipse.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Ellipse = new JButton("Ellipse");
		Ellipse.setIcon(new ImageIcon(ellIcon));
		this.add(Ellipse);
		
		Image line = ImageIO.read(getClass().getResource("line.png"));
		Image lineIcon = line.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Line = new JButton("Line");
		Line.setIcon(new ImageIcon(lineIcon));
		this.add(Line);
		
		Image polygon = ImageIO.read(getClass().getResource("polygon.gif"));
		Image polyIcon = polygon.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Polygon = new JButton("Polygon");
		Polygon.setIcon(new ImageIcon(polyIcon));
		this.add(Polygon);
		
		Image curve = ImageIO.read(getClass().getResource("curve.png"));
		Image curveIcon = curve.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Curve = new JButton("Curve");
		Curve.setIcon(new ImageIcon(curveIcon));
		this.add(Curve);
		
		Image eraser = ImageIO.read(getClass().getResourceAsStream("eraser.png"));
		Image eraserIcon = eraser.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Eraser = new JButton("Eraser");
		Eraser.setIcon(new ImageIcon(eraserIcon));
		this.add(Eraser);
		
		Image paint = ImageIO.read(getClass().getResource("paint.jpg"));
		Image paintIcon = paint.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Paint = new JButton("Paint");
		Paint.setIcon(new ImageIcon(paintIcon));
		this.add(Paint);
		
		Image brush = ImageIO.read(getClass().getResource("brush.jpg"));
		Image brushIcon = brush.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Brush = new JButton("Brush");
		Brush.setIcon(new ImageIcon(brushIcon));
		this.add(Brush);
		
		Image text = ImageIO.read(getClass().getResource("text.png"));
		Image textIcon = text.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Text = new JButton("Text");
		Text.setIcon(new ImageIcon(textIcon));
		this.add(Text);
		
		Image pencil = ImageIO.read(getClass().getResource("pencil.jpg"));
		Image pencilIcon = pencil.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton Pencil = new JButton("Pencil");
		Pencil.setIcon(new ImageIcon(pencilIcon));
		this.add(Pencil);
		
		Image magnifyingGlass = ImageIO.read(getClass().getResource("magnifyingGlass.png"));
		Image magIcon = magnifyingGlass.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		JButton MagnifyingGlass = new JButton("MagnifyingGlass");
		MagnifyingGlass.setIcon(new ImageIcon(magIcon));
		this.add(MagnifyingGlass);
	}
	
}
