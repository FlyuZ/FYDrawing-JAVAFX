package fYDrawing.stage;

import java.util.ArrayList;
import java.util.List;

import fYDrawing.shape.Shapes2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Board {
	private Group content;
	private HBox hbox;
	private Canvas drawingCanvas;
	private GraphicsContext gc;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    private Shapes2D shapeDrawer = new Shapes2D();
    private List<Canvas> listCanvas;
    private int counter = -1;
    
    //on click coordinates
    private double x1;
    private double y1;

    //on release coordinates
    private double x2;
    private double y2;

    
	public Board(){
		content = new Group();
		hbox = new HBox();
		hbox.setAlignment(Pos.TOP_LEFT);
		hbox.getChildren().add(content);
		drawingCanvas = new Canvas(800, 480);
		content.getChildren().add(drawingCanvas);
		gc = drawingCanvas.getGraphicsContext2D();
		init();
	}
	public void init(){
		shapeDrawer.setCanvas(drawingCanvas, Shape.color, false);
		drawingCanvasWidth = (int) drawingCanvas.getWidth();
        drawingCanvasHeight = (int) drawingCanvas.getHeight();
        listCanvas = new ArrayList<>();
        handleDrawingCanvas();
	}
	private void handleDrawingCanvas() {
		drawingCanvas.setOnMouseMoved(event -> {
           //ÐÞ¸Ä×´Ì¬À¸
        });
        drawingCanvas.setOnMouseExited(event -> {
        	//ÐÞ¸Ä×´Ì¬À¸
        });
		drawingCanvas.setOnMousePressed(event -> {
			Canvas c = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
            c.setOnMousePressed(drawingCanvas.getOnMousePressed());
            c.setOnMouseDragged(drawingCanvas.getOnMouseDragged());
            c.setOnMouseReleased(drawingCanvas.getOnMouseReleased());
            c.setOnMouseMoved(drawingCanvas.getOnMouseMoved());
            c.setOnMouseExited(drawingCanvas.getOnMouseExited());

            try {
                if (listCanvas.contains(listCanvas.get(++counter))) {
                    for (int i = listCanvas.size() - 1; i >= counter; i--) {
                    	listCanvas.remove(i);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
            listCanvas.add(c);
            content.getChildren().add(c);
            gc = c.getGraphicsContext2D();
            if((Shape.toolName == "PEN" || Shape.toolName == "LINE" || 
            		Shape.toolName == "RUBBER" || Shape.toolName == "OVAL" ||
            		Shape.toolName == "RECTANGLEZ" || Shape.toolName == "RECTANGLEY")
            		&& Shape.lineSize != "Ìî³ä"){
                gc.setLineWidth(Integer.valueOf(Shape.lineSize));
            }
            x1 = event.getX();
            y1 = event.getY();
            if(Shape.lineSize != "Ìî³ä")
                shapeDrawer.setCanvas(c, Shape.color, false);
            else
            	shapeDrawer.setCanvas(c, Shape.color, true);
		});
		
		drawingCanvas.setOnMouseDragged(event -> {
            if (Shape.toolName == "PEN" || Shape.toolName == "RUBBER") {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });
		
		drawingCanvas.setOnMouseReleased(event -> {
			x2 = event.getX();
            y2 = event.getY();
            if (x1 == x2 && y1 == y2)
                return;
            double width = x2 - x1;
            double height = y2 - y1;
            if(Shape.toolName == "LINE"){
            	shapeDrawer.drawLine(x2, y2);
            }else if(Shape.toolName == "OVAL"){
            	shapeDrawer.drawOval(x1, y1, width, height);
            }else if(Shape.toolName == "RECTANGLEZ"){
            	shapeDrawer.drawRectangle(x1, y1, width, height);
            }else if(Shape.toolName == "RECTANGLEY"){
            	shapeDrawer.drawRoundRectangle(x1, y1, width, height);
            }
		});
	}
	public HBox getCanvas(){
		return hbox;
	}
}
