package com.dos;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private int currentPlayer;
    private String[] playerIds;

    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;

    private Card.Color validColor;
    private Card.Value validValue;
    

    public Game(String[] pids) {
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<Card>();

        playerIds = pids;
        currentPlayer = 0;

        playerHand = new ArrayList<ArrayList<Card>>();

        for (int i = 0; i < pids.length; i++) {
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }

    public void start(Game game) {
        Card card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if (card.getValue() == Card.Value.WILD || card.getValue() == Card.Value.WILD_TWO) {
            start(game);
        }

        stockPile.add(card);
    }

    public Card getTopCard() {
        return new Card(validColor, validValue);
    }

    /**
     * game over methods
     */

    public boolean isGameOver() {
        for (String player : this.playerIds) {
            if (hasEmptyHand(player)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer];
    }

    public String[] getPlayers() {
        return playerIds;
    }
   
    /**
     * find player hand
     */

    public ArrayList<Card> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    /**
     * check player hand
     */

    public Card getPlayerCard(String pid, int choice) {
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public boolean validCardPlay(Card card) {
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    /**
     * submit draw
     */

    public void submitDraw(String pid) {

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
    }

    public void setCardColor(Card.Color color) {
        validColor = color;
    }

    public void submitPlayerCard(String pid, Card card) {

            ArrayList<Card> pHand = getPlayerHand(pid);

            if (!validCardPlay(card)) {
                if (card.getColor() == Card.Color.WILD) {
                    validColor = card.getColor();
                    validValue = card.getValue();
                }
                if (card.getColor() != validColor) {
                    System.out.println("Veuillez mettre une autre carte.");
                }
                if (card.getValue() != validValue) {
                    System.out.println("Veuillez mettre une autre carte.");
                }
            }
            pHand.remove(card);

            if (hasEmptyHand(this.playerIds[currentPlayer])) {
                System.out.println(currentPlayer + "a gagné.");
            }

            validColor = card.getColor();
            validValue = card.getValue();
            stockPile.add(card);
    }
}