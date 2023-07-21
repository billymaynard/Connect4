package com.connect.connect4;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.util.ArrayList;

public class PlayerList {
    private ArrayList<Player> currentlist = new ArrayList<>();
    public PlayerList(){
        readPlayersFromFile();
    }
    private void addPlayersToCurrentList(Player playerToAdd){
        currentlist.add(playerToAdd);
    }
    private void readPlayersFromFile(){
        BufferedReader br =null;
        br = new BufferedReader(new InputStreamReader(PlayerList.class.getResourceAsStream("/com/connect/connect4/gameinfo/playerinfo.txt")));
        try {
            String currentline = br.readLine();
            while (currentline != null){
                String[] playerInfo = currentline.split("#");
                Player newPlayer = new Player(playerInfo[0],Integer.parseInt(playerInfo[1]),Integer.parseInt(playerInfo[2]),Integer.parseInt(playerInfo[3]));
                addPlayersToCurrentList(newPlayer);
                currentline=br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Player x: currentlist){
            stringBuilder.append(x.toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
    public ArrayList<Player> getCurrentList(){
        return currentlist;
    }
}
class Player{
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    public Player(String name, int gamesPlayed, int gamesWon, int gamesLost){
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }
    public String toString(){
        return name+" "+String.valueOf(gamesPlayed)+" "+String.valueOf(gamesWon)+" "+String.valueOf(gamesLost);
    }

    public StringProperty getNameProperty() {
        return new SimpleStringProperty((String) name);
    }

    public StringProperty getGamesPlayedProperty() {
        return new SimpleStringProperty(String.valueOf((int) gamesPlayed));
    }

    public StringProperty getGamesWonProperty() {
        return new SimpleStringProperty(String.valueOf((int) gamesWon));
    }

    public StringProperty getGamesLostProperty() {
        return new SimpleStringProperty(String.valueOf((int) gamesLost));
    }
}
