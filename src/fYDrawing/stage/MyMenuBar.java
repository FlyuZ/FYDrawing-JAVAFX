package fYDrawing.stage;


import java.util.List;

import fYDrawing.shape.FileSaver;
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
import javafx.stage.StageStyle;

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
	/**
	* @see initUI
	* @see #��ʼ���˵������˵���
	* @version 1.0
	* @author Flyuz
	*/
	private void initUI() {
		menubar = new MenuBar();

		file = new Menu();
		file.setText("�ļ�");
		file.setStyle("-fx-font-size:14;");

		miOpen = new MenuItem();
		miOpen.setText("��");
		miOpen.setStyle("-fx-font-size:14;");
/*		miOpen.setOnAction((ActionEvent t) -> {
			//��
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
			fileChooser.setTitle("��ͼƬ");// ��ͼƬ
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
		miSave.setText("����");
		miSave.setStyle("-fx-font-size:14;");
		miSave.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
		miSave.setOnAction((ActionEvent t) -> {
			List<Canvas> list = mainStage.getBoard().getList();
			FileSaver fileSaver = new FileSaver(list, mainStage.getBoard().getW(), mainStage.getBoard().getH());
			fileSaver.saveToFile();
			
		});
		
		miClose = new MenuItem();
		miClose.setText("�ر�");
		miClose.setStyle("-fx-font-size:14;");
		miClose.setOnAction((ActionEvent t) -> {
			Platform.exit();
		});
		file.getItems().add(miOpen);
		file.getItems().add(miSave);
		file.getItems().add(miClose);

		edit = new Menu();
		edit.setText("�༭");
		edit.setStyle("-fx-font-size:14;");
		miReturn = new MenuItem("����");
		miReturn.setStyle("-fx-font-size:14;");
		miReturn.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
		miReturn.setOnAction((ActionEvent t) -> {
			//��������
			 mainStage.getBoard().undo();
		});
		miClear = new MenuItem("�½�����");
		miClear.setStyle("-fx-font-size:14;");
		miClear.setOnAction((ActionEvent t) -> {
			//����½�����
			mainStage.getBoard().clear();
		});
		edit.getItems().add(miReturn);
		edit.getItems().add(miClear);

		help = new Menu();
		help.setText("����");
		help.setStyle("-fx-font-size:14;");
		miAbout = new MenuItem();
		miAbout.setText("����");
		miAbout.setStyle("-fx-font-size:14;");
		miAbout.setOnAction((ActionEvent t) -> {
			Alert aboutAlert = new Alert(AlertType.INFORMATION);
			aboutAlert.setTitle("���ڻ�ͼ");
			aboutAlert.setHeaderText("��ӭʹ��FY��ͼ���");
			aboutAlert.initStyle(StageStyle.UTILITY);
			aboutAlert.setContentText("�汾��\n"
					+ "�����ߣ�Flyuz\n"
					+ "��ϵ��ʽ��flyuz1010@gmail.com\n"
					+ "��ԴЭ�飺GPL");
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
