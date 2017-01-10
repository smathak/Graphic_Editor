package constants;

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
	
	public static enum EToolBar{
		rectangle("rsc/rectangle.png", "rsc/rectangleSelected.PNG"),
		ellipse("rsc/ellipse.png", "rsc/ellipseSelected.png"),
		line("rsc/line.png", "rsc/lineSelected.PNG"),
		polygon("rsc/polygon.PNG", "rsc/polygonSelected.PNG"),
		curve("rsc/curve.PNG", "rsc/curveSelected.PNG"),
		eraser("rsc/eraser.PNG", "rsc/eraserSelected.PNG"),
		paint("rsc/paint.jpg", "rsc/paintSelected.PNG"),
		text("rsc/text.png", "rsc/textSelected.PNG"),
		pencil("rsc/pencil.PNG", "rsc/pencilSelected.PNG"),
		MagnifyingGlass("rsc/magnifyingGlass.png", "rsc/magnifyingSelected.PNG");
		
		private String iconName, selectedIconName;
		private EToolBar(String i, String s){
			this.iconName = i;
			this.selectedIconName = s;
		}
		public String getIconName(){
			return iconName;
		}
		public String getSelectedIconName(){
			return selectedIconName;
		}
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
	
	
	public enum EState {idle, drawing};
	public enum ESelectedTool {rectangle, ellipse, line, text, polygon, pencil};
	public enum Click {oneClick, doubleClick};
	public enum Poly {polygon, polyline};
	public enum Start {yes, no}
	
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
