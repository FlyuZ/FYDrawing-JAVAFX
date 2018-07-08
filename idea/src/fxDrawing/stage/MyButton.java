package fxDrawing.stage;

import javafx.scene.control.Button;

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
