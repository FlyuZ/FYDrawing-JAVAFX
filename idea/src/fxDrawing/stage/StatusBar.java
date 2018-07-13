package fxDrawing.stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
/**
 * @see StatusBar
 * 状态栏，继承MyText，HBox布局，1.0版本只显示当前画笔位置
 * @version 1.0
 * @author Flyuz
 */
public class StatusBar implements MyText{
    private HBox hbox;

    public StatusBar(){
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5, 30, 5, 100));
        curLocation.setFont(Font.font ("Microsoft YaHei", 16));
        hbox.getChildren().add(curLocation);
    }

    public HBox getStatusBar(){
        return hbox;
    }
}