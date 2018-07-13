package fxDrawing.stage;

import javafx.scene.paint.Color;

/**
 * @see Shape
 * 当前图形类，纪录当前图形需要的属性
 * @version 1.0
 * @author Flyuz
 */
public class Shape {
    static String toolName = "PEN";
    static String lineSize = "7";
    static int rubberSize = 7;
    static int fontSize = 12;
    static String fontFamily = "AIGDT";
    static Color color = Color.BLACK;
    static String Text = "";
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