package com.dos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private int currentPlayer;
    private List<String> playerIds;

    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile1;
    private ArrayList<Card> stockPile2;


    private Card.Color validColor1;
    private Card.Value validValue1;
    private Card.Color validColor2;
    private Card.Value validValue2;
    
    public Game(List<String> pids) {
        this.deck = new Deck();
        deck.reset();
        deck.shuffle();
        this.stockPile1 = new ArrayList<Card>();
        this.stockPile2 = new ArrayList<Card>();

        this.playerIds = pids;
        this.currentPlayer = 0;

        this.playerHand = new ArrayList<ArrayList<Card>>();

        for (int i = 0; i < pids.size(); i++) {
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            this.playerHand.add(hand);
        }
    }

    public void start(Game game) {
        Card card1 = deck.drawCard();
        validColor1 = card1.getColor();
        validValue1 = card1.getValue();

        Card card2 = deck.drawCard();
        validColor2 = card2.getColor();
        validValue2 = card2.getValue();

        if (card1.getValue() == Card.Value.WILD_TWO || card2.getValue() == Card.Value.WILD_TWO) {
            start(game);
        }
        stockPile1.add(card1);
        stockPile2.add(card2);
    }
    
    public ArrayList<Card> getStockPile1() {
        return stockPile1;
    }

    public ArrayList<Card> getStockPile2() {
        return stockPile2;
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

    public boolean validCardPlay1(Card card) {
        return card.getColor() == validColor1 || card.getValue() == validValue1;
    }

    public boolean validCardPlay2(Card card) {
        return card.getColor() == validColor2 || card.getValue() == validValue2;
    }

    /**
     * submit draw
     */

    public void submitDraw(String pid) {
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile1);
            deck.shuffle();
        }
        getPlayerHand(pid).add(deck.drawCard());
    }

    /*public void setCardColor(Card.Color color) {
        validColor = color;
    }*/

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

        boolean hasPlayed = false;

        // compteur de toutes les couleurs dispo dans la main
        int red = 0;
        int yellow = 0;
        int blue = 0;
        int green = 0;
        int wild = 0;

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
            else if (card.getColor() == Card.Color.WILD) {
                wild++;
            }

            if (validCardPlay1(card)) {
                hasPlayed = true;
                invalidCards[i++] = true; 

                validColor1 = card.getColor();
                validValue1 = card.getValue();

                yellDos(pid);
                pHand.remove(card);
                stockPile1.add(card);
                System.out.println(pid + " joue la carte " + card + ".");
                break;
            }
            if (validCardPlay2(card)) {
                hasPlayed = true;
                invalidCards[i++] = true; 

                validColor2 = card.getColor();
                validValue2 = card.getValue();

                yellDos(pid);
                pHand.remove(card);
                stockPile2.add(card);
                System.out.println(pid + " joue la carte " + card + ".");
                break;
            }
        }

        int[] t = {red, yellow, blue, green, wild};

        if (t[wild] != 0 && !hasPlayed) {

            invalidCards[i++] = true; 

            int indCouleurMax = 0;
            int max = t[0];
            for (int k = 1; k < t.length - 1; k++) {
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

            validColor1 = laCouleurMax;

            for (Card card : pHand) {
                if (card.getValue() == Card.Value.WILD_TWO) {
                    pHand.remove(card);
                    yellDos(pid);
                    stockPile1.add(card);
                    System.out.println(pid + " joue la carte " + card + " et demande la couleur " + validColor1 + ".");
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