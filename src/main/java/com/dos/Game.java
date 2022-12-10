package com.dos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    
    public ArrayList<Card> getStockPile() {
        return stockPile;
    }

    public Card getTopCard() {
        return new Card(validColor, validValue);
    }

    /**
     * game over methods
     */

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

    public void yellDos(String pid) {
        if (getPlayerHand(pid).size() == 1) {
            System.out.println(pid + " crie \"DOS\" ! \n");
        }
    }

    public void submitPlayerCard(String pid) {

        ArrayList<Card> pHand = getPlayerHand(pid);
  
        // si toutes les cartes de la main du joueur ne sont pas valides (tout le tableau contient des false), il piochera
        boolean [] invalidCards = new boolean [getPlayerHandSize(playerIds.get(currentPlayer))];
        int counterFalse = 0;
        int i = 0; 

        for (Card card : pHand) {

            if (validCardPlay(card)) {

                invalidCards[i++] = true; 

                validColor = card.getColor();
                validValue = card.getValue();

                yellDos(pid);
                pHand.remove(card);
                stockPile.add(card);
                System.out.println(pid + " joue la carte " + card + ".");
                break;
            }
            else {
                if (card.getColor() == Card.Color.WILD) {

                    invalidCards[i++] = true; 

                    int red = 0;
                    int yellow = 0;
                    int blue = 0;
                    int green = 0;
    
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
                    for (int k = 1; k < t.length ; k++) {
                        if (max < t[k]) {
                            max = t[k];
                            indCouleurMax = k;
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

                    validColor = laCouleurMax;
                    validValue = card.getValue();
                    pHand.remove(card);
                    yellDos(pid);
                    stockPile.add(card);
                    System.out.println(pid + " joue la carte " + card.toString() + ".");
                    break;
                }
            }
        }
        for (int j = 0; j < invalidCards.length; j++){
            if (invalidCards[j] == false) {
                ++counterFalse;
            }
        }

        if (counterFalse == invalidCards.length) {
            submitDraw(playerIds.get(currentPlayer));
            System.out.println(pid + " pioche une nouvelle carte.");
        }
        currentPlayer = (currentPlayer + 1) % playerIds.size();
    }
}