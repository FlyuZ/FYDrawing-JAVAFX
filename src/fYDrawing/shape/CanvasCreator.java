package fYDrawing.shape;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.List;

/**
* @see CanvasCreator
* @see #画布生成器，将所有图层合并成一个canvas
* @retuen canvas
* @version 1.0
* @author Flyuz
*/

public class CanvasCreator {
	public static Canvas createCanvas(List<Canvas> list) {
		Canvas canvas = new Canvas(list.get(list.size() - 1).getWidth(), list.get(list.size() - 1).getHeight());
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		for (int i = 0; i < list.size(); i++) {
			WritableImage image = list.get(i).snapshot(params, null);
			canvas.getGraphicsContext2D().drawImage(image, 0, 0);
		}
		return canvas;
	}
}