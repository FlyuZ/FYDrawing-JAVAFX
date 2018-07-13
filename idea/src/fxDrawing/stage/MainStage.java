package fxDrawing.stage;

import fxDrawing.common.Path;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * @see MainStage
 * 主界面类，继承Stage，布局为BorderPane，创建菜单栏，工具栏，状态栏和画布
 * @version 1.0
 * @author Flyuz
 */
public class MainStage extends Stage {
    private Group root;
    private BorderPane content;
    private Board board;

    public MainStage() {
        root = new Group();
        content = new BorderPane();

        setScene(new Scene(root, Color.web("#949494")));
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
        content.setBottom(statusBar.getStatusBar());
    }
    public Board getBoard() {
        return board;
    }
}