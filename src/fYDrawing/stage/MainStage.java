package fYDrawing.stage;

import fYDrawing.common.Path;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainStage extends Stage {
	private Group root;
	private BorderPane content;
	private MyMenuBar menuBar;
	private ToolBar toolBar;
	private Board board;
	private StatusBar statusBar;

	private Timeline delayTimeline; // 垃圾回收计时器

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
		startGC();
	}

	private void initUI() {
		menuBar = new MyMenuBar(this);
		menuBar.getMenuBar().prefWidthProperty().bind(this.widthProperty());
		content.setTop(menuBar.getMenuBar());
		
		toolBar = new ToolBar();
		content.setLeft(toolBar.getToolBar());
		
		board = new Board();
		content.setRight(board.getCanvas());
		
		statusBar = new StatusBar();
		//statusBar.getStatusBar().prefWidthProperty().bind(this.widthProperty());
		content.setBottom(statusBar.getStatusBar());
	}
	public Board getBoard() {
		return board;
	}
	// 开始垃圾回收
	public void startGC() {
		int delay = 1000;// 延迟时间
		delayTimeline = new Timeline();
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.gc();
			}
		};
		delayTimeline.getKeyFrames().add(new KeyFrame(new Duration(delay), onFinished, null, null));
		delayTimeline.play();
	}
}
