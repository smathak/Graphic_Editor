package frame;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.io.IOException;

import javax.swing.JFrame;

import constants.Constants.EMainFrame;

public class SaveDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	Button saveButton;
	Button cancelButton;
	Button donsaveButton;
	
	public SaveDialog() throws IOException {
		super();
		this.setSize(300, 150);
		this.setLocation(EMainFrame.X.getValue()+EMainFrame.W.getValue()/2, + EMainFrame.Y.getValue()+EMainFrame.H.getValue()/2); // dialog�� ����� ��ġ
		this.setLayout(new FlowLayout());
		Label saveLabel = new Label("������ ���� �� ����ϴ�. �����Ͻðڽ��ϱ�?", Label.CENTER);
		saveButton = new Button("����");
		donsaveButton = new Button("���� ����");
		cancelButton = new Button("���");
		
		saveButton.setActionCommand(saveButton.getLabel());
		cancelButton.setActionCommand(cancelButton.getLabel());
		
		
		this.add(saveLabel);
		this.add(saveButton);
		this.add(donsaveButton);
		this.add(cancelButton);
		
		this.setVisible(true);
	}
	
	public Button getSaveButton(){
		return this.saveButton;
	}
	public Button getCancelButton(){
		return this.cancelButton;
	}
	
	public Button getDonSaveButton(){
		return this.donsaveButton;
	}

}