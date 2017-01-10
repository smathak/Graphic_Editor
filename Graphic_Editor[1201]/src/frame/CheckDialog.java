package frame;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.io.IOException;

import javax.swing.JFrame;

import constants.Constants.EMainFrame;

public class CheckDialog extends JFrame { // dialog는 Dialog 가 아니라 JFrame을 상속해야 함 
	private static final long serialVersionUID = 1L;
	Button checkButton;
	public CheckDialog() throws IOException {
		super();
		this.setSize(200, 100);
		this.setLocation(EMainFrame.X.getValue()+EMainFrame.W.getValue()/2, + EMainFrame.Y.getValue()+EMainFrame.H.getValue()/2); // dialog를 가운데에 위치
		this.setLayout(new FlowLayout());
		Label checkLabel = new Label("저장했습니다.", Label.CENTER);
		checkButton = new Button("확인");
		checkButton.setActionCommand(checkButton.getLabel());

		this.add(checkLabel);
		this.add(checkButton);
		
		this.setVisible(true);

	}
	public Button getButton(){
		return this.checkButton;
	}
}