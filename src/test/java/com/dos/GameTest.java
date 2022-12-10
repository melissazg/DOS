package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void testGetCurrentPlayer() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        //currentPlayer = (currentPlayer + 1) % playerIds.size();
        assertEquals("Dida",game.getCurrentPlayer());
    }

    @Test
    void testGetPlayerCard() {

    }

    @Test
    void testGetPlayerHand() {

    }

    @Test
    void testGetPlayerHandSize() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        assertEquals(7,game.getPlayerHandSize(playerIds.get(0)));
    }

    @Test
    void testGetPlayers() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        assertEquals(playerIds,game.getPlayers());
    }

    @Test
    void testHasEmptyHand() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        assertEquals(false,game.hasEmptyHand(playerIds.get(0)));
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
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Card carte = new Card (Card.Color.RED, Card.Value.FOUR);
        Game game = new Game(playerIds);
        game.submitDraw(playerIds.get(0));
        assertEquals(8, game.getPlayerHandSize(playerIds.get(0)));
    }

    @Test
    void testValidCardPlay1() { // probleme ne marche pas pour true
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Card carte = new Card (Card.Color.RED, Card.Value.FOUR);
        Game game = new Game(playerIds);
        Card.Color validColor1 = Card.Color.BLUE;
        Card.Value valiValue1 = Card.Value.FIVE;
        assertEquals(false, game.validCardPlay1(carte));

    }

    @Test
    void testValidCardPlay2() { // probleme ne marche pas pour true
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Card carte = new Card (Card.Color.RED, Card.Value.FOUR);
        Game game = new Game(playerIds);
        assertEquals(false, game.validCardPlay2(carte));

    }
}
