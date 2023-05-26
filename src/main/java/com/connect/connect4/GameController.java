package com.connect.connect4;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameController {
    @FXML
    private void mouseEnteredColumn(Event event){
        Pane currentpane = (Pane) event.getTarget();
        String currentStyle = getLocation(currentpane);
        currentpane.getStyleClass().remove(currentStyle);
        currentpane.getStyleClass().add(currentStyle+"_hover");
    }
    @FXML
    private void mouseExitedColumn(Event event){
        Pane currentpane = (Pane) event.getTarget();
        String defaultStyle = getLocation(currentpane);
        currentpane.getStyleClass().remove(defaultStyle+"_hover");
        currentpane.getStyleClass().add(defaultStyle);
    }
    private String getLocation(Pane paneToCheck){
        int column = GridPane.getColumnIndex(paneToCheck);
        switch (column){
            case 1:
                return "left_column";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "inner_column";
            case 7:
                return "right_column";
            default:
                return null;
        }
    }
}
