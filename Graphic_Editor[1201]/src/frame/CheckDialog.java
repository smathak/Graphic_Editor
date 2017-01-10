package frame;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.io.IOException;

import javax.swing.JFrame;

import constants.Constants.EMainFrame;

public class CheckDialog extends JFrame { // dialog�� Dialog �� �ƴ϶� JFrame�� ����ؾ� �� 
	private static final long serialVersionUID = 1L;
	Button checkButton;
	public CheckDialog() throws IOException {
		super();
		this.setSize(200, 100);
		this.setLocation(EMainFrame.X.getValue()+EMainFrame.W.getValue()/2, + EMainFrame.Y.getValue()+EMainFrame.H.getValue()/2); // dialog�� ����� ��ġ
		this.setLayout(new FlowLayout());
		Label checkLabel = new Label("�����߽��ϴ�.", Label.CENTER);
		checkButton = new Button("Ȯ��");
		checkButton.setActionCommand(checkButton.getLabel());

		this.add(checkLabel);
		this.add(checkButton);
		
		this.setVisible(true);

	}
	public Button getButton(){
		return this.checkButton;
	}
}