package fxDrawing.stage;

import java.util.ArrayList;
import java.util.List;

import fxDrawing.common.Size;
import fxDrawing.shape.Shapes2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * @see Board
 * 画板容器，由多张画布（图层）合成
 * @version 1.0
 * @author Flyuz
 */
public class Board {
    private Group content;
    private VBox vbox;
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    private Shapes2D shapeDrawer = new Shapes2D();
    private List<Canvas> listCanvas;

    // 鼠标按下位置
    private double x1;
    private double y1;

    // 鼠标松开位置
    private double x2;
    private double y2;

    public Board() {
        init();
    }

    public void init() {
        content = new Group();
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 20, 0, 0));
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
    /**
     * 实现鼠标事件
     */
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
            if (Shape.toolName.equals("OVAL") || Shape.toolName.equals("RECTANGLEZ") || Shape.toolName.equals("RECTANGLEY")) {
                if (!Shape.lineSize.equals("填充")) {
                    gc.setLineWidth(Integer.valueOf(Shape.lineSize));
                    shapeDrawer.setCanvas(c, Shape.color, false);
                } else if (Shape.lineSize.equals("填充")) {
                    shapeDrawer.setCanvas(c, Shape.color, true);
                }
            }else if(Shape.toolName.equals("BARREL")){
                shapeDrawer.setCanvas(c, Shape.color, true);
            }else{
                gc.setLineWidth(Shape.rubberSize);
                shapeDrawer.setCanvas(c, Shape.color, false);
            }

            if (Shape.toolName.equals("RUBBER"))
                gc.setStroke(Color.WHITE);
            x1 = event.getX();
            y1 = event.getY();
            if(Shape.toolName.equals("TEXT")){
                gc.setLineWidth(1);
                gc.setFont(Font.font(Shape.fontFamily, Shape.fontSize));
                gc.setStroke(Shape.color);
                gc.strokeText(Shape.Text, event.getX(), event.getY());
            }
            listCanvas.add(c);
            content.getChildren().add(c);
        });

        drawingCanvas.setOnMouseDragged(event -> {
            MyText.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
            if (Shape.toolName.equals("PEN") || Shape.toolName.equals("RUBBER")) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

        drawingCanvas.setOnMouseReleased(event -> {
            x2 = event.getX();
            y2 = event.getY();
            double width = x2 - x1;
            double height = y2 - y1;
            if (Shape.toolName.equals("LINE")) {
                shapeDrawer.drawLine(x1, y1, x2, y2);
            } else if (Shape.toolName.equals("OVAL")) {
                shapeDrawer.drawOval(x1, y1, width, height);
            } else if (Shape.toolName.equals("RECTANGLEZ")) {
                shapeDrawer.drawRectangle(x1, y1, width, height);
            } else if (Shape.toolName.equals("RECTANGLEY")) {
                shapeDrawer.drawRoundRectangle(x1, y1, width, height);
            } else if (Shape.toolName.equals("BARREL")) {
                shapeDrawer.drawFillCanvas(drawingCanvasWidth, drawingCanvasHeight);
            }
            gc.stroke();
        });
    }
    /**
     * 撤销操作
     */
    void undo() {
        if (!listCanvas.isEmpty()) {
            content.getChildren().remove(listCanvas.get(listCanvas.size()-1));
            listCanvas.remove(listCanvas.size()-1);
        }
    }

    public VBox getCanvas() {
        return vbox;
    }
    /**
     * 更新画布，清空list，清空容器
     */
    public void clear() {
        content.getChildren().clear();
        listCanvas.clear();
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