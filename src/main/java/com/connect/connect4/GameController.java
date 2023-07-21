package com.connect.connect4;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    private Circle[][] circles = new Circle[6][7];
    private Connect4Game currentGame;
    @FXML
    private GridPane pane;
    @FXML
    private StackPane coverPane;
    @FXML
    private Label winnerMSG;
    @FXML
    private Label returnButton;

    public void initialize(){
        currentGame = new Connect4Game(false,0);
        for (int i = 0; i<6; i++){
            for (int k = 0; k<7; k++){
                circles[i][k]=new Circle();
                //In grid pane column and row index are swapped.
                pane.add(circles[i][k],k+1,i+1);
                circles[i][k].setFill(Color.WHITE);
                circles[i][k].setRadius(40);
                circles[i][k].setStroke(Color.BLUE);
                circles[i][k].setStrokeWidth(3);
                circles[i][k].setStrokeType(StrokeType.OUTSIDE);
                circles[i][k].setMouseTransparent(true);
                GridPane.setValignment(circles[i][k], VPos.CENTER);
                GridPane.setHalignment(circles[i][k], HPos.CENTER);
            }
        }
    }
    @FXML
    private void mouseEnteredColumn(Event event){
        Pane currentpane = (Pane) event.getTarget();
        int column = GridPane.getColumnIndex(currentpane)-1;
        int rowToStyle = currentGame.lowestAvailableInRow(column);
        if (rowToStyle!=6){
            circles[rowToStyle][column].setFill(Color.web(getCurrentTurnsHoverColor()));
        }
        String currentStyle = getLocation(currentpane);
        currentpane.getStyleClass().remove(currentStyle);
        currentpane.getStyleClass().add(currentStyle+"_hover");
    }
    @FXML
    private void mouseExitedColumn(Event event){
        Pane currentpane = (Pane) event.getTarget();
        int column = GridPane.getColumnIndex(currentpane)-1;
        int rowToStyle = currentGame.lowestAvailableInRow(column);
        if (rowToStyle!=6){
            circles[rowToStyle][column].setFill(Color.WHITE);
        }
        String defaultStyle = getLocation(currentpane);
        currentpane.getStyleClass().remove(defaultStyle+"_hover");
        currentpane.getStyleClass().add(defaultStyle);
    }
    @FXML
    public void handlePaneClicked(Event event){
        Pane clickedPane = (Pane) event.getSource();
        int playedColumn = GridPane.getColumnIndex(clickedPane)-1;
        int potentialRow = currentGame.lowestAvailableInRow(playedColumn);
        int player=currentGame.getPlayerCode();
        if (currentGame.playMove(GridPane.getColumnIndex(clickedPane)-1)){
            setCircleColor(potentialRow,playedColumn);
            int gameState = Connect4Game.checkForEnd(currentGame.getBoard(),potentialRow,playedColumn,player);
            if (gameState!=7){
                gameOver(gameState);
            }else{
                currentGame.swapTurn();
            }
        }else{
            //playsound
        }
    }
    @FXML
    public void handleOkButton(){
        Scene scene = pane.getScene();
        Stage stage = (Stage) scene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource("Landing-Page.fxml"));
        Scene newscene;
        try {
            newscene=new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(newscene);
        stage.show();
    }
    private void gameOver(int winner){
        coverPane.setVisible(true);
        coverPane.toFront();
        if (winner==0){
            winnerMSG.setText("DRAW");
        }else if(winner==1){
            winnerMSG.setText("YELLOW IS THE WINNER");
        }else {
            winnerMSG.setText("RED IS THE WINNER");
        }
        winnerMSG.setVisible(true);
        winnerMSG.toFront();
        returnButton.setVisible(true);
        returnButton.toFront();
    }
    private String getLocation(Pane paneToCheck){
        int column = GridPane.getColumnIndex(paneToCheck);
        switch (column){
            case 1:
                return "left_column";
            case 2, 3, 4, 5, 6:
                return "inner_column";
            case 7:
                return "right_column";
            default:
                return null;
        }
    }
    private String getCurrentTurnsHoverColor(){
        if (currentGame.isRedsTurn()){
            return "#f54e49";
        }else{
            return "#eed875";
        }
    }
    public void setCircleColor(int row, int column){
        circles[row][column].setFill(Color.web(getCurrentTurnsColor()));
    }
    private String getCurrentTurnsColor(){
        if (currentGame.isRedsTurn()){
            return "#ff0000";
        }else {
            return "#ffff00";
        }
    }
}
