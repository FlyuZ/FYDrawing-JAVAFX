package fxDrawing.stage;

import fxDrawing.common.Path;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage extends Stage {
    private Group root;
    private BorderPane content;
    private Board board;

    public MainStage() {
        root = new Group();
        content = new BorderPane();

        setScene(new Scene(root, Color.web("#949494")));
        //setScene(new Scene(root, Color.web("#A9A9A9")));
        setTitle("画图");
        setResizable(true);
        getIcons().add(new Image(Path.LOGO));
        initUI();
        root.getChildren().add(content);
        show();
    }

    private void initUI() {
        MyMenuBar menuBar = new MyMenuBar(this);
        menuBar.getMenuBar().prefWidthProperty().bind(this.widthProperty());
        content.setTop(menuBar.getMenuBar());

        MyToolBar toolBar = new MyToolBar();
        content.setLeft(toolBar.getToolBar());

        board = new Board();
        content.setRight(board.getCanvas());

        StatusBar statusBar = new StatusBar();
        //statusBar.getStatusBar().prefWidthProperty().bind(this.widthProperty());
        content.setBottom(statusBar.getStatusBar());
    }
    public Board getBoard() {
        return board;
    }
}