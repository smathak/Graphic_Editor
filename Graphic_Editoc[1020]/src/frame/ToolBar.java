package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.Constants.EToolBar;

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
		
		for(EToolBar eToolBar : EToolBar.values()){
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eToolBar.getIconName()));
			button.setSelectedIcon(new ImageIcon(eToolBar.getSelectedIconName()));
			this.add(button);
			buttonGroup.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBar.toString()); // 이거 나중에 잘 봐둬. 나중에 오류 날 거 같으니깐.
		}
		
//		for(Constants.EToolBar2 eToolBar2 : Constants.EToolBar2.values()){
//			Image button = ImageIO.read(getClass().getResource(eToolBar2.getIconName()));
//			Image button2 = button.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//			Image buttonSel = ImageIO.read(getClass().getResource(eToolBar2.getSelectedIconName()));
//			Image buttonSel2 = buttonSel.getScaledInstance(20, 30, Image.SCALE_DEFAULT);
//			
//			JRadioButton Button = new JRadioButton();
//			Button.setIcon(new ImageIcon(button2));
//			Button.setSelectedIcon(new ImageIcon(buttonSel2));
//			this.add(Button);
//			buttonGroup.add(Button);
//			Button.setActionCommand(eToolBar2.toString());
//			Button.addActionListener(actionHandler);
//		}		
	}
	
	public void initialize() {
		JRadioButton button = (JRadioButton) this.getComponentAtIndex(EToolBar.rectangle.ordinal());
		button.doClick();
	}
	
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			drawingPanel.setESelectedTool(EToolBar.valueOf(e.getActionCommand()));
		}
		
	}
}
