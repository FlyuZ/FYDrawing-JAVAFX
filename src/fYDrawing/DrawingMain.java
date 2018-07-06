package fYDrawing;

import javafx.application.Application;
import javafx.stage.Stage;
import fYDrawing.stage.*;

/**
* @see DrawingMain
* @see #³ÌÐòÈë¿Ú
* @version 1.0
* @author Flyuz
*/
public class DrawingMain extends Application {

	public void start(Stage arg0) throws Exception {
		new MainStage();
	}
	public static void main(String args[]) {
		launch(args);
	}
}