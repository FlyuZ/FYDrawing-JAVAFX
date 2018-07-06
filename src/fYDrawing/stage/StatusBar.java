package fYDrawing.stage;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class StatusBar implements MyText{
	private HBox hbox;
	
	public StatusBar(){
		hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		curLocation.setFont(Font.font ("Verdana", 18));

		hbox.getChildren().add(curLocation);
	}
	public HBox getStatusBar(){
		return hbox;
	}
}
