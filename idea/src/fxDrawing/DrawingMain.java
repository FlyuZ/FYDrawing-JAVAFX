package fxDrawing;

import fxDrawing.stage.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @see DrawingMain
 * 程序入口
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