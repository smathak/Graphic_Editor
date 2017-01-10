package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.Constants.ESideBar;

public class SideBar extends JToolBar{
	private static final long serialVersionUID = 1L;
	JRadioButton button;
	DrawingPanel drawingPanel;
	
//	public void setDrawingPanel(DrawingPanel drawingPanel) {
//		this.drawingPanel = drawingPanel;
//	}
	
	public SideBar(){
		this.setFloatable(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		ActionHandler actionHandler = new ActionHandler();
		Box verticalBox = Box.createVerticalBox();
		
		for(ESideBar eSideBar : ESideBar.values()){
			button = new JRadioButton();
			button.setIcon(new ImageIcon(eSideBar.getColorName()));
			button.setSelectedIcon(new ImageIcon(eSideBar.getColorName()));
			verticalBox.add(button);
			buttonGroup.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eSideBar.toString());
			this.add(verticalBox);
		}	
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// 컬러명을 전달  -> DrawingPanel -> GShape -> each shape
			System.out.println(ESideBar.valueOf(e.getActionCommand()));
			drawingPanel.setSelectedColor(ESideBar.valueOf(e.getActionCommand()));
		}
	}

	public void initialize(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
}
