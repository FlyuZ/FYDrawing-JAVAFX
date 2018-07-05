package fYDrawing.stage;


import fYDrawing.common.Path;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage extends Stage{
	private Group root;
	private BorderPane content;
	private MyMenuBar menuBar;
	private ToolBar toolBar;
	private Board board;

	private Timeline delayTimeline; // 垃圾回收计时器

	public MainStage() {
		root = new Group();
		content = new BorderPane();
		
		setScene(new Scene(root,Color.web("#949494")));
		setTitle("画图");
		setResizable(true);
		getIcons().add(new Image(Path.LOGO));
		initUI();
		root.getChildren().add(content);
		show();
		//startGC();
	}
	private void initUI() {
		menuBar = new MyMenuBar();
		content.setTop(menuBar.getMenuBar());
		menuBar.getMenuBar().prefWidthProperty().bind(this.widthProperty());
		
		toolBar = new ToolBar();
		content.setLeft(toolBar.getToolBar());
		toolBar.getToolBar().prefHeightProperty().bind(this.heightProperty());
		
		board = new Board();
		content.setRight(board.getCanvas());
		
		//statusGroup = new StatusGroup(this);
		//content.setBottom(statusGroup);
	}
}
