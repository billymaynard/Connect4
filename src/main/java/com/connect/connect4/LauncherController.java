package com.connect.connect4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LauncherController {
    @FXML
    public ComboBox colorselect;

    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        Button pressedButton = (Button) event.getSource();
        Scene currentScene = pressedButton.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("game.fxml"));
        Scene newscene = new Scene(fxmlLoader.load());
        currentStage.setScene(newscene);
        currentStage.show();
    }
    public void initialize(){
        ObservableList<String> colorScemes = FXCollections.observableArrayList("Red and Yellow", "Green and Pink", "Black and Blue");
        colorselect.setItems(colorScemes);
    }
    @FXML
    protected void selectColorButton(ActionEvent event){

    }
}