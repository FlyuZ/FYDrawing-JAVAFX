package fYDrawing.stage;

import javafx.scene.control.Label;


public interface MyText {
	public static Label curLocation = new Label("");
	
	public static void setText(String text){
		curLocation.setText(text);
	}
}
