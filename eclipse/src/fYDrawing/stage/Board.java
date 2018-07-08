package fYDrawing.stage;

import java.util.ArrayList;
import java.util.List;

import fYDrawing.common.Size;
import fYDrawing.shape.Shapes2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Board {
	private Group content;
	private VBox vbox;
	private Canvas drawingCanvas;
	private GraphicsContext gc;
	public static int drawingCanvasWidth;
	public static int drawingCanvasHeight;
	private Shapes2D shapeDrawer = new Shapes2D();
	private List<Canvas> listCanvas;
	private int counter = -1;

	// on click coordinates
	private double x1;
	private double y1;

	// on release coordinates
	private double x2;
	private double y2;

	public Board() {
		init();
	}

	public void init() {
		content = new Group();
		vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10, 25, 0, 0));
		vbox.getChildren().add(content);
		drawingCanvas = new Canvas(Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
		gc = drawingCanvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
		gc.restore();
		content.getChildren().add(drawingCanvas);

		shapeDrawer.setCanvas(drawingCanvas, Shape.color, false);
		drawingCanvasWidth = (int) drawingCanvas.getWidth();
		drawingCanvasHeight = (int) drawingCanvas.getHeight();
		listCanvas = new ArrayList<>();
		handleDrawingCanvas();
	}

	private void handleDrawingCanvas() {
		drawingCanvas.setOnMouseMoved(event -> {
			MyText.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
		});
		drawingCanvas.setOnMouseExited(event -> {
			MyText.setText("");
		});
		drawingCanvas.setOnMousePressed(event -> {
			Canvas c = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
			gc = c.getGraphicsContext2D();

			c.setOnMousePressed(drawingCanvas.getOnMousePressed());
			c.setOnMouseDragged(drawingCanvas.getOnMouseDragged());
			c.setOnMouseReleased(drawingCanvas.getOnMouseReleased());
			c.setOnMouseMoved(drawingCanvas.getOnMouseMoved());
			c.setOnMouseExited(drawingCanvas.getOnMouseExited());
			if (Shape.toolName == "OVAL" || Shape.toolName == "RECTANGLEZ" || Shape.toolName == "RECTANGLEY") {
				if (Shape.lineSize != "野割") {
					gc.setLineWidth(Integer.valueOf(Shape.lineSize));
					shapeDrawer.setCanvas(c, Shape.color, false);
				} else if (Shape.lineSize == "野割") {
					shapeDrawer.setCanvas(c, Shape.color, true);
				}
			}else if(Shape.toolName == "BARREL"){
				shapeDrawer.setCanvas(c, Shape.color, true);
			}else{
				gc.setLineWidth(Shape.rubberSize);
				shapeDrawer.setCanvas(c, Shape.color, false);
			}

			if (Shape.toolName == "RUBBER")
				gc.setStroke(Color.WHITE);
			if(Shape.toolName == "TEXT"){
				gc.setFont(Font.font(Shape.fontFamily, Shape.fontSize));
				gc.setFill(Shape.color);
				gc.strokeText(Shape.Text, event.getX(), event.getY());
			}

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

			x1 = event.getX();
			y1 = event.getY();
			gc.beginPath();
			gc.moveTo(x1, y1);
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
			double width = x2 - x1;
			double height = y2 - y1;
			if (Shape.toolName == "LINE") {
				shapeDrawer.drawLine(x2, y2);
			} else if (Shape.toolName == "OVAL") {
				shapeDrawer.drawOval(x1, y1, width, height);
			} else if (Shape.toolName == "RECTANGLEZ") {
				shapeDrawer.drawRectangle(x1, y1, width, height);
			} else if (Shape.toolName == "RECTANGLEY") {
				shapeDrawer.drawRoundRectangle(x1, y1, width, height);
			} else if (Shape.toolName == "BARREL") {
				shapeDrawer.drawFillCanvas(drawingCanvasWidth, drawingCanvasHeight);
			}
			gc.stroke();
		});
	}

	void undo() {
		if (counter >= 0) {
			content.getChildren().remove(listCanvas.get(counter--));
		}
	}

	public VBox getCanvas() {
		return vbox;
	}

	public void clear() {
		content.getChildren().clear();
		listCanvas.clear();
		counter = -1;
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
		gc.restore();
		content.getChildren().add(drawingCanvas);
	}

	public List<Canvas> getList() {
		return listCanvas;
	}

	public int getW() {
		return drawingCanvasWidth;
	}

	public int getH() {
		return drawingCanvasHeight;
	}
}
