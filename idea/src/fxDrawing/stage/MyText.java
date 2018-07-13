package fxDrawing.stage;

import javafx.scene.control.Label;
/**
 * @see MyText
 * 状态栏鼠标位置接口
 * @version 1.0
 * @author Flyuz
 */

public interface MyText {
    public static Label curLocation = new Label("");
    /**
     * 状态栏位置信息
     */
    public static void setText(String text){
        curLocation.setText(text);
    }
}
