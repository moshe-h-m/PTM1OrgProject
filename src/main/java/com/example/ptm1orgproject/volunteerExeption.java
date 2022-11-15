package com.example.ptm1orgproject;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class volunteerExeption extends Exception{
    public  static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label mymessage = new Label();
        mymessage.setText(message);
        Button closeButton = new Button("\tDir volunteer!\n" +
                " close this window");
        closeButton.setOnAction(e->window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(mymessage,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout);
        window.setScene(scene1);
        window.show();

    }

    public volunteerExeption(String message) {
        display("exception!",message);

    }
}
