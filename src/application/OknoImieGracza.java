package application;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OknoImieGracza {
    private String nazwaGracza = new String("");
    private Gracz gracz;
    
	public OknoImieGracza(Gracz gracz)    {
		this.gracz = gracz;
        Stage subStage = new Stage();
        subStage.setTitle("Nadaj imie ");
        
        Group root = new Group();
        Group root2 = new Group();
        Scene scene = new Scene(root, 700, 200, Color.DARKGRAY);
        Scene scene2 = new Scene(root2,700,200,Color.BLACK);
        
        Rectangle rectangle = new Rectangle();
        rectangle.setX(200);
        rectangle.setY(25);
        rectangle.setWidth(300);
        rectangle.setHeight(75);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setFill(Color.GREY);
        
        Text txt1 = new Text("Twoje imie : ");
        txt1.setX(210);
        txt1.setY(62);      
        txt1.setFill(Color.WHITE);
        txt1.setFont(Font.font("Bell Gothic Std", FontWeight.BOLD, 20));
        
        TextField txtField = new TextField();
        txtField.setLayoutX(340);
        txtField.setLayoutY(43);


        Button btn = new Button("Zapamietaj ");
        btn.setLayoutX(210);
        btn.setLayoutY(130);
        btn.setMaxHeight(200);
        btn.setMinWidth(280);
        btn.setOnAction(event -> {
                  nazwaGracza = txtField.getText();
                  gracz.imie = nazwaGracza; 
                  System.out.println("wprowadzone imie gracza : " + nazwaGracza);
                  System.out.println(gracz.toString());});
        
        root.getChildren().add(rectangle);
        root.getChildren().add(txt1);
        root.getChildren().add(txtField);
        root.getChildren().add(btn);
        

        
        subStage.setScene(scene);
        subStage.show();
    }
}
