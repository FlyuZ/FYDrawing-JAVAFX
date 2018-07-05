package fYDrawing.stage;

import fYDrawing.common.Size;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DetailPanel {
	private VBox content;
	private ComboBox<Integer> fontSize;
	private ComboBox<String> fontFamily;
	private ComboBox<String> lineSize;
	private ComboBox<Integer> rubberSize;
	private ComboBox<Integer> zero1;
	private ComboBox<Integer> zero2;
	private ObservableList<Integer> fontSizeItems = FXCollections.observableArrayList();
	private ObservableList<String> fontFamilyItems = FXCollections.observableArrayList();
	private ObservableList<String> lineSizeItems = FXCollections.observableArrayList();
	private ObservableList<Integer> rubberSizeItems = FXCollections.observableArrayList();

	public DetailPanel() {
		content = new VBox();
		content.setAlignment(Pos.CENTER);
		// ��ʼ���ֺ�
		fontSize = new ComboBox<Integer>();
		fontSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		fontSize.setPrefWidth(Size.DETAIL_WIDTH);
		for (int i = 8; i <= 36; i += 2) {
			fontSizeItems.add(i);
		}
		fontSize.setItems(fontSizeItems);
		fontSize.getSelectionModel().select(2);
		fontSize.valueProperty().addListener((ov, oldv, newv) -> {
			Shape.resetFontSize(Integer.valueOf(newv));
		});
		// ��ʼ������
		fontFamily = new ComboBox<String>();
		for (int i = 0; i < Font.getFamilies().size(); i++) {
			fontFamilyItems.add(Font.getFamilies().get(i));
		}
		fontFamily.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		fontFamily.setPrefWidth(Size.DETAIL_WIDTH);
		fontFamily.setItems(fontFamilyItems);
		fontFamily.getSelectionModel().select(0);
		fontFamily.valueProperty().addListener((ov, oldv, newv) -> {
			Shape.resetFontFamily(newv);
		});
		// ��ʼ������
		lineSize = new ComboBox<String>();
		for (int i = 1; i < 14; i += 3) {
			lineSizeItems.add(Integer.toString(i));
		}
		lineSizeItems.add("���");
		lineSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		lineSize.setPrefWidth(Size.DETAIL_WIDTH);
		lineSize.setItems(lineSizeItems);
		lineSize.getSelectionModel().select(2);
		lineSize.valueProperty().addListener((ov, oldv, newv) -> {
			Shape.resetLineSize(newv);
		});
		// ��ʼ����Ƥ��
		rubberSize = new ComboBox<Integer>();
		for (int i = 1; i < 14; i += 3) {
			rubberSizeItems.add(i);
		}
		rubberSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		rubberSize.setPrefWidth(Size.DETAIL_WIDTH);
		rubberSize.setItems(rubberSizeItems);
		rubberSize.getSelectionModel().select(2);
		rubberSize.valueProperty().addListener((ov, oldv, newv) -> {
			Shape.resetRubberSize(Integer.valueOf(newv));
		});
		//��ʼ�������յ�
		zero1 = new ComboBox<Integer>();
		zero1.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		zero1.setPrefWidth(Size.DETAIL_WIDTH);
		zero2 = new ComboBox<Integer>();
		zero2.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
		zero2.setPrefWidth(Size.DETAIL_WIDTH);
	}

	public void setLine() {
		content.getChildren().clear();
		content.getChildren().add(lineSize);
		content.getChildren().add(zero1);
	}

	public void  setFont() {
		content.getChildren().clear();
		content.getChildren().add(fontSize);
		content.getChildren().add(fontFamily);
	}

	public void  setRubber() {
		content.getChildren().clear();
		content.getChildren().add(rubberSize);
		content.getChildren().add(zero1);
	}

	public void clear() {
		content.getChildren().clear();
		content.getChildren().add(zero1);
		content.getChildren().add(zero2);
	}
	public VBox getDetailPanel(){
		content.getChildren().add(zero1);
		content.getChildren().add(zero2);
		return content;
	}
}
