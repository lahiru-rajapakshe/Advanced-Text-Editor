package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutusController {

    public AnchorPane aboutPane;
    public AnchorPane rootPane;

    private Scene scene;
    public void cedits_OnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(this.getClass().getResource("./view/Credits.fxml"));
    rootPane.getChildren().setAll(load);
     

    }

    public void aboutus_OnAction(ActionEvent event) {
    }



}
