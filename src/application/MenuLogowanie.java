package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MenuLogowanie {
	MenuLogowanie()
    {
        Stage subStage = new Stage();
        subStage.setTitle("logowanie");
                
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);
        
        root.getChildren().add(new Button("przycisk do niczego "));
        subStage.setScene(scene);
        subStage.show();
    }
}
