package com.dos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private int currentPlayer;
    private List<String> playerIds;

    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;

    private Card.Color validColor;
    private Card.Value validValue;
    

    public Game(List<String> pids) {
        this.deck = new Deck();
        deck.reset();
        deck.shuffle();
        this.stockPile = new ArrayList<Card>();

        this.playerIds = pids;
        this.currentPlayer = 0;

        this.playerHand = new ArrayList<ArrayList<Card>>();

        for (int i = 0; i < pids.size(); i++) {
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            this.playerHand.add(hand);
        }
    }

    public void start(Game game) {
        Card card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if (card.getValue() == Card.Value.WILD_TWO) {
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
        return this.playerIds.get(this.currentPlayer);
    }

    public List<String> getPlayers() {
        return playerIds;
    }
   
    /**
     * find player hand
     */

    public ArrayList<Card> getPlayerHand(String pid) {
        int index = playerIds.indexOf(pid);
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

    public void submitPlayerCard(String pid) {

        ArrayList<Card> pHand = getPlayerHand(pid);
        int red = 0;
        int yellow = 0;
        int blue = 0;
        int green = 0;
        
        for (Card card : pHand) {

            if (card.getColor() == Card.Color.RED) {
                red++;
            }
            else if (card.getColor() == Card.Color.YELLOW) {
                yellow++;
            }
            else if (card.getColor() == Card.Color.BLUE) {
                blue++;
            }
            else if (card.getColor() == Card.Color.GREEN) {
                green++;
            }

            int[] t = {red, yellow, blue, green};

            int indCouleurMax = 0;
            int max = t[0];
            for (int i = 1; i < t.length ; i++) {
                if (max < t[i]) {
                    max = t[i];
                    indCouleurMax = i;
                }
            }
            Card.Color laCouleurMax = Card.Color.WILD;
            if (indCouleurMax == 0) {
                laCouleurMax = Card.Color.RED;
            }
            else if (indCouleurMax == 1) {
                laCouleurMax = Card.Color.YELLOW;
            }
            else if (indCouleurMax == 2) {
                laCouleurMax = Card.Color.BLUE;
            }
            else if (indCouleurMax == 3) {
                laCouleurMax = Card.Color.GREEN;
            }

            if (!validCardPlay(card)) {
                if (card.getColor() == Card.Color.WILD) {
                    validColor = laCouleurMax;
                    validValue = card.getValue();
                    stockPile.add(card);
                }
                else{
                    deck.drawCard(1);
                }
            }
            else {
                pHand.remove(card);
                validColor = card.getColor();
                validValue = card.getValue();
                stockPile.add(card);
                break;
            }
        }
    }
}