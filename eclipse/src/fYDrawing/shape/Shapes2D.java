package fYDrawing.shape;

import fYDrawing.stage.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shapes2D {
	private GraphicsContext gc;
	private boolean fill;
	
    public void setCanvas(Canvas c, Color color, boolean f){
        gc = c.getGraphicsContext2D();
        fill = f;
        if(fill == false)
            gc.setStroke(color);
        else 
        	gc.setFill(color);
    }

    public void drawLine(double x2, double y2) {
        gc.lineTo(x2, y2);
        gc.stroke();
    }

    public void drawOval(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if(fill == true)
        	gc.fillOval(x1, y1, width, height);
        else
            gc.strokeOval(x1, y1, width, height);
    }

    public void drawRectangle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if(fill == true)
            gc.fillRect(x1, y1, width, height);
        else
        	gc.strokeRect(x1, y1, width, height);
    }


    public void drawRoundRectangle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if(fill == true)
            gc.fillRoundRect(x1, y1, width, height, 30, 30);
        else
        	gc.strokeRoundRect(x1, y1, width, height, 30, 30);
    }
    public void drawFillCanvas(double w, double h){
    	gc.fillRect(0, 0, w, h);
    }
}
