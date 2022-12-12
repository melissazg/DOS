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
        assertEquals("Dida", game.getCurrentPlayer());
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
        assertEquals(7, game.getPlayerHandSize(playerIds.get(0)));
    }

    @Test
    void testGetPlayers() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        assertEquals(playerIds, game.getPlayers());
    }

    @Test
    void testHasEmptyHand() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        assertEquals(false, game.hasEmptyHand(playerIds.get(0)));
    }

    @Test
    void testCountColor() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card4 = new Card(Card.Color.YELLOW, Card.Value.NINE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card4);
        int blue = 0;
        int red = 0;
        int green = 0;
        int yellow = 0;
        int wild = 0;
         int [] t = new int [] {blue, red, green, yellow, wild}; 
        game.countColor(pHand,t);
        assertEquals(2, t[1]);


    }



    @Test
    void testGetStockpile() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        ArrayList<Card> stockPile = new ArrayList<Card>();
        assertEquals(stockPile, game.getStockPile());
    }

    @Test
    void testSubmitDraw() {
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("Dida");
        playerIds.add("Didou");
        playerIds.add("Lili");
        Game game = new Game(playerIds);
        game.submitDraw(playerIds.get(0));
        assertEquals(8, game.getPlayerHandSize(playerIds.get(0)));
    }

    @Test
    void testValidCardPlay() { 
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card.Color[] validColor = {Card.Color.BLUE, Card.Color.GREEN};
        Card.Value[] validValue= {Card.Value.TEN, Card.Value.NINE};
        Card card = new Card(Card.Color.GREEN, Card.Value.ONE);
        assertEquals(true, game.validCardPlay(card, validColor, validValue));
    }

    @Test
    void testValidCardPlay2() { 
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card.Color[] validColor = {Card.Color.BLUE, Card.Color.GREEN};
        Card.Value[] validValue= {Card.Value.TEN, Card.Value.NINE};
        Card card = new Card(Card.Color.RED, Card.Value.ONE);
        assertEquals(false, game.validCardPlay(card, validColor, validValue));
    }

    @Test
    void testHasThreeIdenticalCards(){
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card3 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card4 = new Card(Card.Color.YELLOW, Card.Value.NINE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card3);
        pHand.add(card4);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.NINE};
        assertEquals(true, game.hasThreeIdenticalCards(pHand, validValue));
    }
    @Test
    void testHasThreeIdenticalCards2(){
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card3 = new Card(Card.Color.GREEN, Card.Value.ONE);
        Card card4 = new Card(Card.Color.YELLOW, Card.Value.NINE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card3);
        pHand.add(card4);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.NINE};
        assertEquals(false, game.hasThreeIdenticalCards(pHand, validValue));
    }

    @Test
    void testHasTwoIdenticalCards(){
    List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card4 = new Card(Card.Color.YELLOW, Card.Value.NINE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card4);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.NINE};
        assertEquals(true, game.hasTwoIdenticalCards(pHand, validValue));
    }
    @Test
    void testHasTwoIdenticalCards2(){
    List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.GREEN, Card.Value.ONE);
        Card card4 = new Card(Card.Color.YELLOW, Card.Value.NINE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card4);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.NINE};
        assertEquals(false, game.hasTwoIdenticalCards(pHand, validValue));
    }
    @Test
    void testSubmiThreeCards(){
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        game.start(game);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card3 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card5 = new Card(Card.Color.GREEN, Card.Value.THREE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card3);
        pHand.add(card5);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.FOUR};
        game.submitThreeCards(playerIds.get(0), pHand, validValue);
        assertEquals(1, pHand.size());
    }

    @Test
    void testSubmiTwoCards(){
        List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        game.start(game);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card5 = new Card(Card.Color.GREEN, Card.Value.THREE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card5);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.FOUR};
        game.submitTwoCards(playerIds.get(0), pHand, validValue);
        assertEquals(1, pHand.size());
    }

    @Test
    void testSumCards(){
    List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        game.start(game);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.GREEN, Card.Value.ONE);
        Card card5 = new Card(Card.Color.RED, Card.Value.THREE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card5);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.FOUR};
        Card.Color [] validColor = {Card.Color.BLUE, Card.Color.RED};
        game.sumCards(playerIds.get(0), pHand, card1, validValue, validColor);
        assertEquals(1, pHand.size());


    }

    @Test
    void testAddCard(){
    List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        Game game = new Game(playerIds);
        game.start(game);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.GREEN, Card.Value.ONE);
        Card card5 = new Card(Card.Color.RED, Card.Value.THREE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card5);
        Card.Value [] validValue = {Card.Value.ONE , Card.Value.FOUR};
        Card.Color [] validColor = {Card.Color.BLUE, Card.Color.RED};
        game.addCard(card1, validValue, validColor, playerIds.get(0), pHand);
        assertEquals(2, pHand.size());
    }

    @Test
    void testPlayWildTwo(){
    List<String> playerIds = new ArrayList<String>();
        playerIds.add("joueur 1");
        playerIds.add("joueur 2");
        playerIds.add("joueur 3");
        int blue = 0;
        int red = 0;
        int green = 0;
        int yellow = 0;
        int wild = 0;
         int [] t = new int [] {blue, red, green, yellow, wild}; 
        Game game = new Game(playerIds);
        game.start(game);
        Card card1 = new Card(Card.Color.RED, Card.Value.ONE);
        Card card2 = new Card(Card.Color.WILD, Card.Value.WILD_TWO);
        Card card5 = new Card(Card.Color.RED, Card.Value.THREE);
        ArrayList<Card> pHand = new ArrayList<>();
        pHand.add(card1);
        pHand.add(card2);
        pHand.add(card5);
        Card.Color [] validColor = {Card.Color.BLUE, Card.Color.GREEN};
        game.playWildTwo(t, validColor, playerIds.get(0), pHand);
        assertEquals(2, pHand.size());
    }
     


}