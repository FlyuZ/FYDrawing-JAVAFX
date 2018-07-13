package fxDrawing.stage;

import javafx.scene.control.Button;
/**
 * @see MyButton
 * 按钮类，Button，需要纪录每个按钮存的什么信息，所以加一个name属性
 * @version 1.0
 * @author Flyuz
 */
public class MyButton extends Button{
    String name;
    public MyButton(String n){
        super();
        this.name = n;
    }

    public String getName(){
        return name;
    }
}
