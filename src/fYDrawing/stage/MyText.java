package fYDrawing.stage;

import javafx.scene.text.Text;


public interface MyText {
	public static Text curLocation = new Text("");
	
	public static void setText(String text){
		curLocation.setText(text);
	}
}
