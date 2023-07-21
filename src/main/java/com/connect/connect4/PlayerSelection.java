package com.connect.connect4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PlayerSelection {
    private PlayerList newList = new PlayerList();
    private ObservableList<Player> observableList = FXCollections.observableArrayList(newList.getCurrentList());
    @FXML
    private TableView<Player> tableView;
    @FXML
    public TableColumn<Player,String> nameColumn;
    @FXML
    public TableColumn<Player,String> gamesPlayedColumn;
    @FXML
    public TableColumn<Player,String> gamesWonColumn;
    @FXML
    public TableColumn<Player,String> gamesLostColumn;
    public void initialize(){
        tableView.setItems(observableList);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        gamesPlayedColumn.setCellValueFactory(cellData -> cellData.getValue().getGamesPlayedProperty());
        gamesWonColumn.setCellValueFactory(cellData -> cellData.getValue().getGamesWonProperty());
        gamesLostColumn.setCellValueFactory(cellData -> cellData.getValue().getGamesLostProperty());
    }
}
