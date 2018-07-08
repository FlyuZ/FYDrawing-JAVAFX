package fYDrawing.shape;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* @see FileSaver
* @see #生成文件选择器，保存文件
* @version 1.0
* @author Flyuz
*/

public class FileSaver {
	private List<Canvas> list;
	private int canvasWidth;
	private int canvasHeigth;
	public static File img;

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
		img = fc.showSaveDialog(null);
		String type = "PNG";

		try {
			Canvas canvas = CanvasCreator.createCanvas(this.list);
			WritableImage writableImage = new WritableImage(canvasWidth, canvasHeigth);
			canvas.snapshot(null, writableImage);
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
			if(img != null)
			ImageIO.write(renderedImage, type, img);

		} catch (IOException ex) {
		}
	}
}
