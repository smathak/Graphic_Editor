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
	
	// Dialog�� ���� �Լ�
	public void makeSaveDialog() throws IOException{
		saveDialog = new SaveDialog();
		saveButton = saveDialog.getSaveButton();
		saveButton.addActionListener(actionHandler);
		cancelButton = saveDialog.getCancelButton();
		cancelButton.addActionListener(actionHandler);
		donsaveButton = saveDialog.getDonSaveButton();
		donsaveButton.addActionListener(actionHandler);
	}
	
	// ��ȭ ����
	public String showSaveDialog(){
		JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphics Editor", "gps");
	    fileChooser.setFileFilter(filter);
	    int returnVal = fileChooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println(fileChooser.getSelectedFile().getPath());
	        return fileChooser.getSelectedFile().getPath(); // ���� ���丮 ��
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
	        return fileChooser.getSelectedFile().getPath(); // ���� ���丮 ��
	    }
	    return null;
	}
	
	// save�� �ϰ� ���� checkDialog ���� 
	public void save() throws FileNotFoundException, IOException { // �ѹ� ����Ǿ����� �ٽ� ���̾�α� �� �߰� �����ߴ� ���丮�� �״�� ���� 
		File file = null;
		
		if(eSave == ESave.unsaved && eOpen != EOpen.opened){ // ������ �� �� �����̸��̰� ������ ������ �� ���°� �ƴ϶�� ���ο� ���Ͽ� ����
			directoryName = showSaveDialog(); // ���̾�α׵����� ������ directoryName�� ���� 
			System.out.println("directoryName_saved: "+directoryName);
			if (directoryName == null) {
				return;
			}
			file = new File(directoryName);
			eSave = ESave.saved;
			previousLength = this.drawingPanel.getShapeVector().size();
		}else if(eSave == ESave.saved){ // �̹� ����� �����̸� ���� ���丮�� �״�� ����
			// ���̾�α� �θ��� ���� 
			System.out.println("directoryName_unsaved: "+directoryName);
			file = new File(directoryName); // ���� directoryName�� �״�� ��
			previousLength = this.drawingPanel.getShapeVector().size();
		}else if(eOpen == EOpen.opened){  // �̹� �����ߴ� �����̸� saveDialog �� ���� �ش� ���� ���丮�� ���� 
			directoryName = fileName;
			file = new File(directoryName);
			previousLength = this.drawingPanel.getShapeVector().size();
		}
//		file = new File(directoryName); // directoryName �� ������ ���� 
		ObjectOutputStream outputStream;
		outputStream = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file))); 
		outputStream.writeObject(drawingPanel.getShapeVector()); //shapeVector write
		outputStream.close();
		
		checkDialog = new CheckDialog(); // ������ ���ÿ� ���̾�αװ� �� 
		checkButton = checkDialog.getButton();
		checkButton.addActionListener(actionHandler);
	}
	
	// saveAs�� �ϰ� ���� checkDialog ���� 
	public void saveAs() throws IOException { // �ѹ� �����ߴ��� ��� ���̾�α� �߰� �� 
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
		
		checkDialog = new CheckDialog(); // ������ ���ÿ� ���̾�αװ� �� 
		checkButton = checkDialog.getButton();
		checkButton.addActionListener(actionHandler);
	}

	@SuppressWarnings("unchecked")
	public void open() throws ClassNotFoundException, IOException {
		// open�� �� �� ������ �׸��� ����Ǿ� ���� ���� �����̸� saveDialog�� ��� 
		if(eSave == ESave.unsaved && this.drawingPanel.getShapeVector().size() > 0){
			makeSaveDialog();
			System.out.println("test1");
		}
		// ������ �̹� �ѹ� �߾ ������ �Ͼ���� saveDialog�� ���
		else if(eSave == ESave.saved && previousLength < drawingPanel.getShapeVector().size()){
			makeSaveDialog();
			System.out.println("test2");
		}else{ // �׸��� ���� openDialog ����
			fileName = showOpenDialog();
			if (fileName == null) {
				return;
			}
			try {
				File file = new File(fileName);
				ObjectInputStream inputStream;
				inputStream = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(file)));
				// repaint �ϱ� ���� ������ �׸��� �� ������ �� 
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
			// repaint �ϱ� ���� ������ �׸��� �� ������ �� 
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
		// ������ �̹� �ѹ� �߾ ������ �Ͼ���� saveDialog�� ���
		else if(eSave == ESave.saved && previousLength < drawingPanel.getShapeVector().size()){
			makeSaveDialog();
			System.out.println("test2");
		}					
		else if(eSave == ESave.saved){// ������ �� ������ ������ �� 
			mainFrame.exit();
		}
	}

	// new 
	public void newDrawingPanel() throws IOException{
		if(eSave == ESave.unsaved && this.drawingPanel.getShapeVector().size() > 0){
			makeSaveDialog();
			System.out.println("test1");
		}
		// ������ �̹� �ѹ� �߾ ������ �Ͼ���� saveDialog�� ���
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
				
			}else if(event.getActionCommand().equals(EDialogButton.check.getLabel())){ // Ȯ�� ��ư�� ������
				checkDialog.setVisible(false); // ���̾�α׸� ����(������)
			}else if(event.getActionCommand().equals(EDialogButton.save.getLabel())){ // ���� ��ư�� ������
				 try {
					save();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // ���� ���̾�α׸� ����
				 saveDialog.setVisible(false); // ���̾�α� ȭ�� �ݰ�
				 if(eFileButton == EFileButton.Exit){ // ���� �����ӵ� ����� ���ÿ� �ݱ�
					 checkDialog.setVisible(false);
					 mainFrame.exit();
				 }
			}else if(event.getActionCommand().equals(EDialogButton.cancel.getLabel())){ // ��� ��ư�� ������
				saveDialog.setVisible(false); // ���̾�α׸� ����(������)
			}else if(event.getActionCommand().equals(EDialogButton.donsave.getLabel())){ // ���� ���� ��ư�� ������
				if(eFileButton == EFileButton.New){ 	// new �� ��� �� ȭ��
					drawingPanel.getShapeVector().clear();
					drawingPanel.repaint();
				}else if(eFileButton == EFileButton.Open){ // open �� ��� ���� ������ ������ ����
					try {
						justOpen();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(eFileButton == EFileButton.Exit){  // exit �� ��� �׳� ����
					mainFrame.exit();
				}
				
				saveDialog.setVisible(false); // ���̾�α׸� ����(������)
			}
			
			
		}


	}
}
