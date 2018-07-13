package fxDrawing.stage;

import fxDrawing.common.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @see DetailPanel
 * 细节选择工具，设置线条粗细，设置字体字号等
 * @version 1.0
 * @author Flyuz
 */
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
        // 初始化字号
        fontSize = new ComboBox<Integer>();
        fontSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        fontSize.setPrefWidth(Size.DETAIL_WIDTH);
        for (int i = 8; i <= 36; i += 2) {
            fontSizeItems.add(i);
        }
        fontSize.setItems(fontSizeItems);
        fontSize.getSelectionModel().select(0);
        fontSize.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetFontSize(Integer.valueOf(newv));
        });
        // 初始化字体
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
        // 初始化线条
        lineSize = new ComboBox<String>();
        for (int i = 1; i < 14; i += 3) {
            lineSizeItems.add(Integer.toString(i));
        }
        lineSizeItems.add("填充");
        lineSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        lineSize.setPrefWidth(Size.DETAIL_WIDTH);
        lineSize.setItems(lineSizeItems);
        lineSize.getSelectionModel().select(0);
        lineSize.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetLineSize(newv);
        });
        // 初始化橡皮擦
        rubberSize = new ComboBox<Integer>();
        for (int i = 1; i < 14; i += 3) {
            rubberSizeItems.add(i);
        }
        rubberSize.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        rubberSize.setPrefWidth(Size.DETAIL_WIDTH);
        rubberSize.setItems(rubberSizeItems);
        rubberSize.getSelectionModel().select(0);
        rubberSize.valueProperty().addListener((ov, oldv, newv) -> {
            Shape.resetRubberSize(Integer.valueOf(newv));
        });
        //初始化两个空的
        zero1 = new ComboBox<Integer>();
        zero1.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        zero1.setPrefWidth(Size.DETAIL_WIDTH);
        zero2 = new ComboBox<Integer>();
        zero2.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        zero2.setPrefWidth(Size.DETAIL_WIDTH);
    }

    public ComboBox<String> getLine(){
        return lineSize;
    }
    public ComboBox<Integer> getFontSize(){
        return fontSize;
    }
    public ComboBox<Integer> getRubber(){
        return rubberSize;
    }
    public ComboBox<String> getFontFamily(){
        return fontFamily;
    }

    public void setLine() {
        content.getChildren().clear();
        lineSize.getSelectionModel().select(0);
        Shape.resetLineSize("1");
        content.getChildren().add(lineSize);
        content.getChildren().add(zero1);
    }

    public void  setFont() {
        content.getChildren().clear();
        fontFamily.getSelectionModel().select(0);
        fontSize.getSelectionModel().select(0);
        Shape.resetFontFamily("AIGDT");
        Shape.resetFontSize(8);
        content.getChildren().add(fontSize);
        content.getChildren().add(fontFamily);
    }

    public void  setRubber() {
        content.getChildren().clear();
        rubberSize.getSelectionModel().select(0);
        Shape.resetRubberSize(1);
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