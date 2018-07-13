package fxDrawing.stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import fxDrawing.shape.FileSaver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

/**
 * @see MyMenuBar
 * 菜单栏
 * @version 1.0
 * @author Flyuz
 */
public class MyMenuBar {

    private MainStage mainStage;
    private MenuBar menubar;
    private Menu file, edit, help;
    private MenuItem miOpen, miSave, miClose;
    private MenuItem miReturn, miClear;
    private MenuItem miAbout;


    public MyMenuBar(MainStage mainStage) {
        this.mainStage = mainStage;
        initUI();
    }

    private void initUI() {
        menubar = new MenuBar();

        file = new Menu();
        file.setText("文件");
        file.setStyle("-fx-font-size:14;");
/*
//打开功能暂时废弃
        miOpen = new MenuItem();
        miOpen.setText("打开");
        miOpen.setStyle("-fx-font-size:14;");
		miOpen.setOnAction((ActionEvent t) -> {
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
		});
*/

        miSave = new MenuItem();
        miSave.setText("保存");
        miSave.setStyle("-fx-font-size:14;");
        miSave.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        miSave.setOnAction((ActionEvent t) -> {
            List<Canvas> saveList = mainStage.getBoard().getList();
            FileSaver fileSaver = new FileSaver(saveList, mainStage.getBoard().getW(), mainStage.getBoard().getH());
            fileSaver.saveToFile();
            fileSaver = null;
            saveList = null;
            System.gc();
        });

        miClose = new MenuItem();
        miClose.setText("关闭");
        miClose.setStyle("-fx-font-size:14;");
        miClose.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
        //file.getItems().add(miOpen);
        file.getItems().add(miSave);
        file.getItems().add(miClose);

        edit = new Menu();
        edit.setText("编辑");
        edit.setStyle("-fx-font-size:14;");
        miReturn = new MenuItem("撤销");
        miReturn.setStyle("-fx-font-size:14;");
        miReturn.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
        miReturn.setOnAction((ActionEvent t) -> {
            //撤销操作
            mainStage.getBoard().undo();
        });
        miClear = new MenuItem("新建画布");
        miClear.setStyle("-fx-font-size:14;");
        miClear.setOnAction((ActionEvent t) -> {
            //添加新建画布
            mainStage.getBoard().clear();
        });
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
            aboutAlert.setHeaderText("欢迎使用FX画图软件");
            aboutAlert.initStyle(StageStyle.UTILITY);
            aboutAlert.setContentText("版本：\n"
                    + "开发者：Flyuz\n"
                    + "联系方式：flyuz1010@gmail.com\n"
                    + "开源协议：GPL");
            aboutAlert.showAndWait();
        });
        help.getItems().add(miAbout);
        menubar.getMenus().add(file);
        menubar.getMenus().add(edit);
        menubar.getMenus().add(help);
    }
    public MenuBar getMenuBar()
    {
        return menubar;
    }
}