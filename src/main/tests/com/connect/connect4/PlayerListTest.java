package com.connect.connect4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerListTest {
    @Test
    public void readingPlayersTest(){
        PlayerList listToTest = new PlayerList();
        System.out.println(listToTest.toString());
    }
}