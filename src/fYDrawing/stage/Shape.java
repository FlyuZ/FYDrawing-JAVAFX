package fYDrawing.stage;

import javafx.scene.paint.Color;

public class Shape {
	public static String toolName = "PEN";
	public static String lineSize = "7";
	public static int rubberSize = 7;
	public static int fontSize = 12;
	public static String fontFamily = "AIGDT";
	public static Color color = Color.BLACK;
	public static String Text = "";
	static void resetToolName(String name){
		Shape.toolName = name;
	}
	static void resetLineSize(String size){
		Shape.lineSize = size;
	}
	static void resetRubberSize(int size){
		Shape.rubberSize = size;
	}
	static void resetFontSize(int size){
		Shape.fontSize = size;
	}
	static void resetFontFamily(String font){
		Shape.fontFamily = font;
	}
	static void resetColor(Color c){
		Shape.color = c;
	}
	static void resetText(String s){
		Shape.Text = s;
	}
}
