package com.connect.connect4;

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
                String[] playerinfo = currentline.split("#");
                Player newPlayer = new Player(playerinfo[0],Integer.parseInt(playerinfo[1]),Integer.parseInt(playerinfo[2]),Integer.parseInt(playerinfo[3]));
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
}
