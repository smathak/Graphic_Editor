package menus;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.Constants;
import constants.Constants.EDialogButton;
import constants.Constants.EFileButton;
import constants.Constants.EFileMenu;
import constants.Constants.EOpen;
import constants.Constants.ESave;
import frame.CheckDialog;
import frame.DrawingPanel;
import frame.MainFrame;
import frame.SaveDialog;
import shapes.GShape;

public class FileMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	private DrawingPanel drawingPanel;
	private MainFrame mainFrame;
	
	Vector<Object> shapes = null;
	ESave eSave;
	EOpen eOpen;
	EFileButton eFileButton;
	
	String directoryName;
	String fileName;
	CheckDialog checkDialog = null;
	Button checkButton = null;
	ActionHandler actionHandler = null;
	
	SaveDialog saveDialog = null;
	Button cancelButton = null;
	Button saveButton = null;
	Button donsaveButton = null;
	
	int previousLength;
	
	//constructor
	public FileMenu() {
		super(Constants.FILEMENU_TITLE);
		
		actionHandler = new ActionHandler();
		for(EFileMenu eFileMenu : EFileMenu.values()){
			JMenuItem item = new JMenuItem(eFileMenu.getValue());
			this.addSeparator();

			item.addActionListener(actionHandler);
			item.setActionCommand(eFileMenu.name());
			this.add(item);
		}
		eSave = ESave.unsaved;
		eOpen = EOpen.unopened;
		previousLength = 0;
	}
	public void initialize(DrawingPanel drawingPanel, MainFrame mainFrame) {
		this.drawingPanel = drawingPanel;
		this.mainFrame = mainFrame;
	}
	
	// Dialog를 여는 함수
	public void makeSaveDialog() throws IOException{
		saveDialog = new SaveDialog();
		saveButton = saveDialog.getSaveButton();
		saveButton.addActionListener(actionHandler);
		cancelButton = saveDialog.getCancelButton();
		cancelButton.addActionListener(actionHandler);
		donsaveButton = saveDialog.getDonSaveButton();
		donsaveButton.addActionListener(actionHandler);
	}
	
	// 대화 상자
	public String showSaveDialog(){
		JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphics Editor", "gps");
	    fileChooser.setFileFilter(filter);
	    int returnVal = fileChooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println(fileChooser.getSelectedFile().getPath());
	        return fileChooser.getSelectedFile().getPath(); // 파일 디렉토리 반
	    }
	    return null;
	}
	
	public String showOpenDialog(){
		JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphics Editor", "gps");
	    fileChooser.setFileFilter(filter);
	    int returnVal = fileChooser.showOpenDialog(this);
	    System.out.println(returnVal);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println(fileChooser.getSelectedFile().getPath());
	        return fileChooser.getSelectedFile().getPath(); // 파일 디렉토리 반
	    }
	    return null;
	}
	
	// save를 하고 나서 checkDialog 띄우기 
	public void save() throws FileNotFoundException, IOException { // 한번 저장되었으면 다시 다이얼로그 안 뜨고 저장했던 디렉토리에 그대로 저장 
		File file = null;
		
		if(eSave == ESave.unsaved && eOpen != EOpen.opened){ // 저장이 안 된 상태이면이고 저장한 파일을 연 상태가 아니라면 새로운 파일에 저장
			directoryName = showSaveDialog(); // 다이얼로그도에서 저장할 directoryName을 얻어옴 
			System.out.println("directoryName_saved: "+directoryName);
			if (directoryName == null) {
				return;
			}
			file = new File(directoryName);
			eSave = ESave.saved;
			previousLength = this.drawingPanel.getShapeVector().size();
		}else if(eSave == ESave.saved){ // 이미 저장된 상태이면 그전 디렉토리에 그대로 저장
			// 다이얼로그 부르지 않음 
			System.out.println("directoryName_unsaved: "+directoryName);
			file = new File(directoryName); // 이전 directoryName을 그대로 씀
			previousLength = this.drawingPanel.getShapeVector().size();
		}else if(eOpen == EOpen.opened){  // 이미 저장했던 파일이면 saveDialog 안 띄우고 해당 파일 디렉토리에 저장 
			directoryName = fileName;
			file = new File(directoryName);
			previousLength = this.drawingPanel.getShapeVector().size();
		}
//		file = new File(directoryName); // directoryName 에 파일을 생성 
		ObjectOutputStream outputStream;
		outputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file))); 
		outputStream.writeObject(drawingPanel.getShapeVector()); //shapeVector write
		outputStream.close();
		
		checkDialog = new CheckDialog(); // 생성과 동시에 다이얼로그가 뜸 
		checkButton = checkDialog.getButton();
		checkButton.addActionListener(actionHandler);
	}
	
	// saveAs를 하고 나서 checkDialog 띄우기 
	public void saveAs() throws IOException { // 한번 저장했더라도 계속 다이얼로그 뜨게 함 
		String directoryName = showSaveDialog();
		if (directoryName == null) {
			return;
		}
		try {
			File file = new File(directoryName);
			ObjectOutputStream outputStream;
			outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapeVector());
			outputStream.close();
			previousLength = this.drawingPanel.getShapeVector().size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		checkDialog = new CheckDialog(); // 생성과 동시에 다이얼로그가 뜸 
		checkButton = checkDialog.getButton();
		checkButton.addActionListener(actionHandler);
	}

	@SuppressWarnings("unchecked")
	public void open() throws ClassNotFoundException, IOException {
		// open을 할 때 기존의 그림이 저장되어 있지 않은 상태이면 saveDialog를 띄움 
		if(eSave == ESave.unsaved && this.drawingPanel.getShapeVector().size() > 0){
			makeSaveDialog();
			System.out.println("test1");
		}
		// 저장을 이미 한번 했어도 변경이 일어났으면 saveDialog를 띄움
		else if(eSave == ESave.saved && previousLength < drawingPanel.getShapeVector().size()){
			makeSaveDialog();
			System.out.println("test2");
		}else{ // 그리고 나서 openDialog 띄우기
			fileName = showOpenDialog();
			if (fileName == null) {
				return;
			}
			try {
				File file = new File(fileName);
				ObjectInputStream inputStream;
				inputStream = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(file)));
				// repaint 하기 전에 기존의 그림은 싹 지워야 함 
				this.drawingPanel.getShapeVector().clear();
				this.drawingPanel.repaint();
				
			    shapes = (Vector<Object>) inputStream.readObject();
				
				for(Object shapeObject : shapes){
					this.drawingPanel.getShapeVector().add((GShape) shapeObject);
				}

				this.drawingPanel.repaint();
				eOpen = EOpen.opened;
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void justOpen() throws ClassNotFoundException{
		fileName = showOpenDialog();
		if (fileName == null) {
			return;
		}
		try {
			File file = new File(fileName);
			ObjectInputStream inputStream;
			inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			// repaint 하기 전에 기존의 그림은 싹 지워야 함 
			this.drawingPanel.getShapeVector().clear();
			this.drawingPanel.repaint();
			
		    shapes = (Vector<Object>) inputStream.readObject();
			
			for(Object shapeObject : shapes){
				this.drawingPanel.getShapeVector().add((GShape) shapeObject);
			}

			this.drawingPanel.repaint();
			eOpen = EOpen.opened;
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exit() throws IOException{
		if(eSave == ESave.unsaved && this.drawingPanel.getShapeVector().size() > 0){
			makeSaveDialog();
			System.out.println("test1");
		}
		// 저장을 이미 한번 했어도 변경이 일어났으면 saveDialog를 띄움
		else if(eSave == ESave.saved && previousLength < drawingPanel.getShapeVector().size()){
			makeSaveDialog();
			System.out.println("test2");
		}					
		else if(eSave == ESave.saved){// 저장이 다 됬으면 나가도 됨 
			mainFrame.exit();
		}
	}

	// new 
	public void newDrawingPanel() throws IOException{
		if(eSave == ESave.unsaved && this.drawingPanel.getShapeVector().size() > 0){
			makeSaveDialog();
			System.out.println("test1");
		}
		// 저장을 이미 한번 했어도 변경이 일어났으면 saveDialog를 띄움
		else if(eSave == ESave.saved && previousLength < drawingPanel.getShapeVector().size()){
			makeSaveDialog();
			System.out.println("test2");
		}else{
			this.drawingPanel.getShapeVector().clear();
			this.drawingPanel.repaint();
		}
		eSave = ESave.unsaved;
	}
	
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals(EFileMenu.Open.name())){
				eFileButton = EFileButton.Open;
				try {
					open();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(event.getActionCommand().equals(EFileMenu.Save.name())){
				try {
					save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(event.getActionCommand().equals(EFileMenu.SaveAs.name())){
				try {
					saveAs();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(event.getActionCommand().equals(EFileMenu.Exit.name())){
				eFileButton = EFileButton.Exit;
				try {
					exit();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(event.getActionCommand().equals(EFileMenu.New.name())){
				eFileButton = EFileButton.New;
				try {
					newDrawingPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(event.getActionCommand().equals(EFileMenu.Close.name())){
				
			}else if(event.getActionCommand().equals(EDialogButton.check.getLabel())){ // 확인 버튼을 누르면
				checkDialog.setVisible(false); // 다이얼로그를 끈다(버린다)
			}else if(event.getActionCommand().equals(EDialogButton.save.getLabel())){ // 저장 버튼을 누르면
				 try {
					save();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 저장 다이얼로그를 연다
				 saveDialog.setVisible(false); // 다이얼로그 화면 닫고
				 if(eFileButton == EFileButton.Exit){ // 메인 프레임도 저장과 동시에 닫기
					 checkDialog.setVisible(false);
					 mainFrame.exit();
				 }
			}else if(event.getActionCommand().equals(EDialogButton.cancel.getLabel())){ // 취소 버튼을 누르면
				saveDialog.setVisible(false); // 다이얼로그를 끈다(버린다)
			}else if(event.getActionCommand().equals(EDialogButton.donsave.getLabel())){ // 저장 안함 버튼을 누르면
				if(eFileButton == EFileButton.New){ 	// new 일 경우 새 화면
					drawingPanel.getShapeVector().clear();
					drawingPanel.repaint();
				}else if(eFileButton == EFileButton.Open){ // open 일 경우 원래 열려던 파일을 열기
					try {
						justOpen();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(eFileButton == EFileButton.Exit){  // exit 일 경우 그냥 끄기
					mainFrame.exit();
				}
				
				saveDialog.setVisible(false); // 다이얼로그를 끈다(버린다)
			}
			
			
		}


	}
}
