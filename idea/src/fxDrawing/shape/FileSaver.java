package fxDrawing.shape;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @see FileSaver
 * @see# 生成文件选择器，保存文件
 * @version 1.0
 * @author Flyuz
 */

public class FileSaver {
    private List<Canvas> list;
    private int canvasWidth;
    private int canvasHeigth;

    public FileSaver(List<Canvas> list, int drawingCanvasHeight, int drawingCanvasWidth) {
        if (list.isEmpty()) {
            this.list = new ArrayList<>();
            canvasHeigth = drawingCanvasHeight;
            canvasWidth = drawingCanvasWidth;
        } else {
            this.list = new ArrayList<>(list);
            canvasWidth = (int) list.get(list.size() - 1).getWidth();
            canvasHeigth = (int) list.get(list.size() - 1).getHeight();
        }
    }

    public void saveToFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
        fc.setTitle("保存图片");
        File img = fc.showSaveDialog(null);
        String type = "PNG";

        try {
            Canvas canvas = createCanvas(this.list);
            WritableImage writableImage = new WritableImage(canvasWidth, canvasHeigth);
            canvas.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            if(img != null)
                ImageIO.write(renderedImage, type, img);

        } catch (IOException ignored) {
        }
    }
    /**
     * 画布生成器，将所有图层合并成一个canvas
     * @param list 所有图层
     * @retuen canvas
     * @author Flyuz
     */
    private Canvas createCanvas(List<Canvas> list) {
        Canvas canvas = new Canvas(list.get(list.size() - 1).getWidth(), list.get(list.size() - 1).getHeight());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        for (Canvas aList : list) {
            WritableImage image = aList.snapshot(params, null);
            canvas.getGraphicsContext2D().drawImage(image, 0, 0);
        }
        return canvas;
    }
}
