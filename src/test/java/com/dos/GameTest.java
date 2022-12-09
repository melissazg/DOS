package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.dos.Card.Color;
import com.dos.Card.Value;

public class GameTest {
    @Test
    void testCheckPlayerTurn() {

    }

    @Test
    void testGetCurrentPlayer() {
        String[] playerIds = {"1","2","3"};
        final Deck deck = new Deck();
        //Game jeu = new Game(playerIds); 
       
        
        int currentPlayer = 3;
        
        //jeu.getCurrentPlayer();
        //assertEquals(3, jeu.getCurrentPlayer());
    }

    @Test
    void testGetPlayerCard() {

    }

    @Test
    void testGetPlayerHand() {

    }

    @Test
    void testGetPlayerHandSize() {

    }

    @Test
    void testGetPlayers() {

    }

    @Test
    void testGetTopCard() {

    }

    @Test
    void testHasEmptyHand() {

    }

    @Test
    void testIsGameOver() {

    }

    @Test
    void testSetCardColor() {
        /*Card carte = new Card (Color.RED, Value.FOUR);
        Game jeu = new Game();
        jeu.setCardColor(Color.RED);
        assertEquals(Color.RED, carte.getColor()); */


    }

    @Test
    void testStart() {

    }

    @Test
    void testSubmitDraw() {

    }

    @Test
    void testSubmitPlayerCard() {

    }

    @Test
    void testValidCardPlay() {

    }
}
