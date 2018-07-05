package fYDrawing;

import javafx.application.Application;
import javafx.stage.Stage;
import fYDrawing.stage.*;

public class DrawingMain extends Application {

	public void start(Stage arg0) throws Exception {
		new MainStage();
	}

	public static void main(String args[]) {
		launch(args);
	}
}