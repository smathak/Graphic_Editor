package constants;

import shapes.GEllipse;
import shapes.GEraser;
import shapes.GLine;
import shapes.GPencil;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;

public class Constants {
	public final static String MAINFRAME_TITLE = "GrahpicsEditor";
	
	public static enum EMainFrame{
		X(100),
		Y(100),
		W(1000),
		H(600);
		private int value;
		private EMainFrame(int value){
			this.value = value;
		}
		public int getValue(){
			return value;
		}
	}
	
	public static enum ECursor{
		roundArrow("rsc/arrow.png");
		private String cursorImage;
		private ECursor(String cursorImage){
			this.cursorImage = cursorImage;
		}
		public String getCursurImage(){
			return this.cursorImage;
		}
	}
	
	public static enum ESideBar{
		red("rsc/red.png", "rsc/redSelected.png"),
		yellow("rsc/yellow.png", "rsc/yellowSelected.png"),
		blue("rsc/blue.png", "rsc/blueSelected.png"),
		green("rsc/green.png", "rsc/greenSelected.png"),
		black("rsc/black.png", "rsc/blackSelected.png");
		
		private String colorName;
		private String selectedColorName;
		
		private ESideBar(String colorName, String selectedColorName){
			this.colorName = colorName;
			this.selectedColorName = selectedColorName;
		}
		
		public String getColorName(){
			return this.colorName;
		}
		public String getSelectedColorName(){
			return this.selectedColorName;
		}
	}
	
	public static enum EToolBar{
		rectangle("rsc/rectangle.png", "rsc/rectangleSelected.PNG", new GRectangle()),
		ellipse("rsc/ellipse.png", "rsc/ellipseSelected.png", new GEllipse()),
		line("rsc/line.png", "rsc/lineSelected.PNG", new GLine()),
		polygon("rsc/polygon.PNG", "rsc/polygonSelected.PNG", new GPolygon()),
		pencil("rsc/pencil.PNG", "rsc/pencilSelected.PNG", new GPencil()),
		eraser("rsc/eraser.PNG", "rsc/eraserSelected.PNG", new GEraser());
//		text("rsc/text.png", "rsc/textSelected.PNG", new GRectangle());
//		curve("rsc/curve.PNG", "rsc/curveSelected.PNG", new Rectangle()),

//		MagnifyingGlass("rsc/magnifyingGlass.png", "rsc/magnifyingSelected.PNG", new Rectangle());
		
		private String iconName, selectedIconName;
		private GShape shape;
		
		private EToolBar(String i, String s, GShape shape){
			this.iconName = i;
			this.selectedIconName = s;
			this.shape = shape;
		}
		public String getIconName(){
			return iconName;
		}
		public String getSelectedIconName(){
			return selectedIconName;
		}
		public GShape getShape(){
			return this.shape;
		}
	}
	
	public static enum EPaint{
		paint("rsc/paint.jpg", "rsc/paintSelected.PNG");
		private String paintName, selectedPaintName;
		private EPaint(String paintName, String selectedPaintName){
			this.paintName = paintName;
			this.selectedPaintName = selectedPaintName;
		}
		public String getPaintName(){
			return this.paintName;
		}
		public String getSelectedPaintName(){
			return this.selectedPaintName;
		}
	}
	
	public static enum ECopy{
		copy("rsc/copy_30x30.jpg", "rsc/copySelected_30x30.jpg");
		private String copyName, selectedCopyName;
		private ECopy(String copyName, String selectedCopyName){
			this.copyName = copyName;
			this.selectedCopyName = selectedCopyName;
		}
		public String getCopyName(){
			return this.copyName;
		}
		public String getSelectedCopyName(){
			return this.selectedCopyName;
		}
	}
	
	
	public static enum EText{
		text("rsc/text.png", "rsc/textSelected.PNG", new GRectangle());
//		curve("rsc/curve.PNG", "rsc/curveSelected.PNG", new Rectangle()),
//		eraser("rsc/eraser.PNG", "rsc/eraserSelected.PNG", new Rectangle()),
//		paint("rsc/paint.jpg", "rsc/paintSelected.PNG", new Rectangle()),
//		MagnifyingGlass("rsc/magnifyingGlass.png", "rsc/magnifyingSelected.PNG", new Rectangle());
		
		private String textName, selectedTextName;
		private GShape shape;
		
		private EText(String t, String ts, GRectangle rectangle){
			this.textName = t;
			this.selectedTextName = ts;
			this.shape = rectangle;
		}
		public String getTextName(){
			return textName;
		}
		public String getSelectedTextName(){
			return selectedTextName;
		}
		public GShape getRectangle(){
			return this.shape;
		}
	}
	
	public static enum EDrawingType {
		NP, TP;
	}
	
	public static enum EEditMenu{
		Undo("Undo"),
		Repead("Repeat"),
		Cut("Cut"),
		Copy("Copy"),
		Paste("Paste"),
		ClearSelection("Clear Selection"),
		SelectAll("SelectAll"),
		CopyTo("CopyTo"),
		PasteFrom("PasteFrom");
		private String value;
		private EEditMenu(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	public static enum EFileMenu{
		New("New"),
		Open("Open"),
		Save("Save"),
		SaveAs("SaveAs"),
		Print("Print"),
		Properties("Properties"),
		AboutPrint("About Print"),
		Exit("Exit");
		
		private String value;
		private EFileMenu(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	public static enum EHelpMenu{
		HelpTopics("Help Topics"),
		AboutPaint("About Paint");
		
		private String value;
		private EHelpMenu(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	public static enum EImageMenu{
		New("New"),
		strechskew("Stretch/Skew"),
		invertColors("Invert Colors"),
		attributes("Attributes"),
		clearImage("Clear Image"),
		drawOpaque("Draw Opaque");
		
		private String value;
		private EImageMenu(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	public static enum EViewMenu{
		toolBox("Tool Box"),
		colorBox("Color Box"),
		statusBar("Status Bar"),
		textToolBar("Text Toolbar"),
		zoom("Zoom"),
		viewBitmap("View Bitmap");
		
		private String value;
		private EViewMenu(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	
	public enum EState {idle, drawingNP, drawingTP, Transforming, fill, copy, copyAfter};
	public enum ESelectedTool {rectangle, ellipse, line, text, polygon, pencil};
	public enum Click {oneClick, doubleClick};
	public enum Poly {polygon, polyline};
	
	public static enum Selected { yes, no};
	public enum EAnchors {NN, NE, NW, SS, SW, SE, EE, WW, RR, MM}; //  10개의 점을 쓴다. 
	public enum EColor {colorYes, colorNo}
	public enum IsText {textYes, textNo}
	
	// brush 일단 제외
//	public static enum EToolBar2{
//	
//		brush("brush.jpg", "brushSelected.jpg");
//		
//		private String iconName, selectedIconName;
//		private EToolBar2(String i, String s){
//			this.iconName = i;
//			this.selectedIconName = s;
//		}
//		public String getIconName(){
//			return iconName;
//		}
//		public String getSelectedIconName(){
//			return selectedIconName;
//		}
//	}
	
}
