package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.Constants.EPaint;
import constants.Constants.EText;
import constants.Constants.EToolBar;
import constants.Constants.IsText;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	DrawingPanel drawingPanel;
	JRadioButton button;
	JRadioButton textButton;
	JRadioButton paintButton;
	IsText isText = IsText.textNo;
	
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
			button = new JRadioButton();
			button.setIcon(new ImageIcon(eToolBar.getIconName()));
			button.setSelectedIcon(new ImageIcon(eToolBar.getSelectedIconName()));
			this.add(button);
			buttonGroup.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBar.toString());
			isText = IsText.textNo;
		}
		for(EText eText : EText.values()){
			textButton = new JRadioButton();
			textButton.setIcon(new ImageIcon(eText.getTextName()));
			textButton.setSelectedIcon(new ImageIcon(eText.getSelectedTextName()));
			this.add(textButton);
			buttonGroup.add(textButton);
			textButton.addActionListener(actionHandler);
			textButton.setActionCommand(eText.toString());
		}		
		
		for(EPaint ePaint : EPaint.values()){
			paintButton = new JRadioButton();
			paintButton.setIcon(new ImageIcon(ePaint.getPaintName()));
			paintButton.setSelectedIcon(new ImageIcon(ePaint.getSelectedPaintName()));
			this.add(paintButton);
			buttonGroup.add(paintButton);
			paintButton.addActionListener(actionHandler);
			paintButton.setActionCommand(ePaint.toString());
		}	
	}
	
	public void initialize() {
		JRadioButton button = (JRadioButton) this.getComponentAtIndex(EToolBar.rectangle.ordinal());
		button.doClick();
	}
	
	class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// drawingPanel에  선택된 도형의 객체를 전달 
			if(e.getActionCommand().equals("text") == false && e.getActionCommand().equals("paint") == false){
				drawingPanel.setSelectedShape(EToolBar.valueOf(e.getActionCommand()).getShape()); // EToolBar에 text 가 없어서 IllegarArgumentExeption이 남 
			}else if(textButton.isSelected() == true){
					isText = IsText.textYes;
					// 뭔 전달할 지 생각중. TextField를 전달할지 그냥 string만 전달할지.... 일단 GRectangle 전달.(네모 그리게)''
					drawingPanel.setSelectedShape(EText.valueOf(e.getActionCommand()).getRectangle());
					drawingPanel.setSelectedText();
					// 여기서 e.getActionCommand가 문제
			}
			if(paintButton.isSelected() == true){
				System.out.println(paintButton.isSelected());
				drawingPanel.setSelectedFill();
			}
		}
		
	}
}
