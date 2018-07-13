package fxDrawing.stage;

import java.util.ArrayList;
import java.util.List;
import fxDrawing.common.Size;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @see ColorPanel
 * 颜色选择工具，布局为VBox，包括两个部分，上面为颜色选择器，下面为快捷颜色选择区
 * @version 1.0
 * @author Flyuz
 */
public class ColorPanel {
    private VBox content;
    private TilePane tilePane;
    private  Color presentColor = Color.BLACK;
    private  ColorPicker colorPicker = new ColorPicker();
    private List<MyButton> colorButton = new ArrayList<MyButton>();
    private String[] color = {"#ffffff", "#000000", "#848683", "#c4c3be", "#cdbedb", "#b97b56",
            "#ffadd6", "#f01e1f", "#89010d", "#fef007", "#ffc80c", "#ff7c26",
            "#efe2ab", "#b6e51d", "#24b04f", "#93dceb", "#6997bb", "#07a0e6",
            "#d9a2dc", "#9c4ca1","#3b46e0"};
    public ColorPanel(){
        initUI();
    }
    private void initUI(){

        content = new VBox();
        content.setAlignment(Pos.CENTER);

        colorPicker = new ColorPicker();
        colorPicker.setPrefWidth(Size.COLOR_PICKER_WIDTH);
        colorPicker.setStyle("-fx-background-color:orange;-fx-color-label-visible:false;");
        colorPicker.setValue(presentColor);
        colorPicker.setOnAction((ActionEvent t) -> {
            presentColor = colorPicker.getValue();
            Shape.resetColor(presentColor);
        });

        tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(10,10,10,10));
        tilePane.setPrefColumns(2);
        tilePane.setHgap(5);
        tilePane.setVgap(5);

        DropShadow shadow = new DropShadow();
        for(int i=0; i<color.length; i++){
            MyButton cButton = new MyButton(color[i]);
            cButton.setStyle("-fx-base: " + color[i] + ";");
            cButton.setPrefSize(Size.COLOR_BUTTON_WIDTH, Size.COLOR_BUTTON_HEIGHT);
            cButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                cButton.setEffect(shadow);
            });

            cButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                cButton.setEffect(null);
            });

            cButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                presentColor = Color.web( ((MyButton) e.getSource()).getName() );
                colorPicker.setValue(presentColor);
                Shape.resetColor(presentColor);
            });
            colorButton.add(cButton);
        }
        tilePane.getChildren().addAll(colorButton);
        content.getChildren().add(colorPicker);
        content.getChildren().add(tilePane);
    }
    public VBox getColorPanel(){
        return content;
    }
}
