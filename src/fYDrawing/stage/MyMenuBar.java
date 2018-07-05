package fYDrawing.stage;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

public class MyMenuBar {

	private MenuBar menubar;
	private Menu file, edit, help;
	private MenuItem miOpen, miSave, miClose;
	private MenuItem miReturn, miClear;
	private MenuItem miAbout;
	
	private int imgNum = 0;
	
	public MyMenuBar(/*MainStage mainStage*/) {
		//this.mainStage = mainStage;
		initUI();
	}
	/**
	* @see initUI
	* @see #初始化菜单栏，菜单项
	* @version 1.0
	* @author Flyuz
	*/
	private void initUI() {
		menubar = new MenuBar();

		file = new Menu();
		file.setText("文件");
		file.setStyle("-fx-font-size:14;");

		miOpen = new MenuItem();
		miOpen.setText("打开");
		miOpen.setStyle("-fx-font-size:14;");
/*		miOpen.setOnAction((ActionEvent t) -> {
			//打开
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
			fileChooser.setTitle("打开图片");// 打开图片
			File file = fileChooser.showOpenDialog(mainStage);
			if (file != null) {
				try {
					Image image = new Image(new FileInputStream(file));
					mainStage.getBorad().addImage(image);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});*/
		
		miSave = new MenuItem();
		miSave.setText("保存");
		miSave.setStyle("-fx-font-size:14;");
		miSave.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
		miSave.setOnAction((ActionEvent t) -> {
			imgNum++;
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("保存图片");// 保存图片
			fileChooser.setInitialFileName("IMG_" + imgNum + ".png");
			//File file = fileChooser.showSaveDialog(mainStage);
			//FXImaging imager = new FXImaging();
			//imager.nodeToImage(mainStage.getBorad().getGroupBorad(), mainStage.getBorad().getChildren(), file, mainStage.getBorad().getBoradWidth(), mainStage.getBorad().getBoradHeight());
		});
		
		miClose = new MenuItem();
		miClose.setText("关闭");
		miClose.setStyle("-fx-font-size:14;");
		miClose.setOnAction((ActionEvent t) -> {
			Platform.exit();
		});
		file.getItems().add(miOpen);
		file.getItems().add(miSave);
		file.getItems().add(miClose);

		edit = new Menu();
		edit.setText("编辑");
		edit.setStyle("-fx-font-size:14;");
		miReturn = new MenuItem("撤销");
		miReturn.setStyle("-fx-font-size:14;");
		miReturn.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
/*		miReturn.setOnAction((ActionEvent t) -> {
			//撤销操作
			List<Node> listChildren = mainStage.getBorad().getGroupBorad().getChildren();
			int size = listChildren.size();
			if (size > 1) {
				listChildren.remove(size - 1);
			}
		});*/
		miClear = new MenuItem("新建画布");
		miClear.setStyle("-fx-font-size:14;");
		/*
		miClear.setOnAction((ActionEvent t) -> {
			//添加新建画布
			mainStage.getBorad().clearBorad();
		});
		*/
		edit.getItems().add(miReturn);
		edit.getItems().add(miClear);

		help = new Menu();
		help.setText("帮助");
		help.setStyle("-fx-font-size:14;");
		miAbout = new MenuItem();
		miAbout.setText("关于");
		miAbout.setStyle("-fx-font-size:14;");
		miAbout.setOnAction((ActionEvent t) -> {
			Alert aboutAlert = new Alert(AlertType.INFORMATION);
			aboutAlert.setTitle("关于画图");
			aboutAlert.setHeaderText("");
			//aboutAlert.initStyle(StageStyle.UTILITY);//隐藏图标
			//aboutAlert.setGraphic();//设置图标
			aboutAlert.setContentText("版本：\n"
					+ "开发者：Flyuz\n"
					+ "联系方式：flyuz1010@gmail.com\n"
					+ "开源协议：");
			aboutAlert.showAndWait();
		});
		help.getItems().add(miAbout);
		//menubar.getMenus().add(file);
		menubar.getMenus().add(file);
		menubar.getMenus().add(edit);
		menubar.getMenus().add(help);
	}
	public MenuBar getMenuBar()
	{
		return menubar;
	}
}
