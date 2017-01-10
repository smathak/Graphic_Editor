package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.Constants;
import constants.Constants.EEditMenu;
import frame.DrawingPanel;

public class EditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	DrawingPanel drawingPanel;
	
	public EditMenu(){
		super(Constants.EDITMENU_TITLE); 
		ActionHandler anctionHandler = new ActionHandler();
		for(EEditMenu eEditMenu : EEditMenu.values()){
			JMenuItem item = new JMenuItem(eEditMenu.getValue());
			this.addSeparator();
			
			item.addActionListener(anctionHandler);
			item.setActionCommand(eEditMenu.name());
			this.add(item);
		}
		
	}
	public void initialize(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public void undo() {
		drawingPanel.undo();
		
	}
	
	public void selectAll() {	
		drawingPanel.selectAll();
	}

	public void redo() {
		drawingPanel.redo();
	}

	private void paste() {
//		drawingPanel.paste();
		
	}
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals(EEditMenu.Copy.name())){
				drawingPanel.setSelectedCopy();
				
			}else if(event.getActionCommand().equals(EEditMenu.Paste.name())){
				drawingPanel.paste();
			}else if(event.getActionCommand().equals(EEditMenu.Undo.name())){
				undo();
			}else if(event.getActionCommand().equals(EEditMenu.Redo.name())){
				redo();
			}else if(event.getActionCommand().equals(EEditMenu.SelectAll.name())){
				selectAll();
			}
		}


	}
}
