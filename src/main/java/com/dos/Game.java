package com.dos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dos.Card.Value;

public class Game {

    private int currentPlayer;
    private List<String> playerIds;

    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<ArrayList<Card>> stockPile;

    private Card.Color[] validColor;
    private Card.Value[] validValue;
    
    /**
     * @param pids
     */
    public Game(List<String> pids) {
        this.deck = new Deck();
        deck.reset();
        deck.shuffle();
        this.stockPile = new ArrayList<ArrayList<Card>>();

        this.playerIds = pids;
        this.currentPlayer = 0;

        this.playerHand = new ArrayList<ArrayList<Card>>();

        this.validColor = new Card.Color[2];
        this.validValue = new Card.Value[2];

        for (int i = 0; i < pids.size(); i++) {

            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            this.playerHand.add(hand);
        }
    }

    public void start(Game game) {
        Card[] cards = new Card[2];
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawCard();
            cards[i] = card;
            validColor[i] = card.getColor();
            validValue[i] = card.getValue();
            ArrayList<Card> stockPileBis = new ArrayList<Card>();
            stockPileBis.add(card);
            stockPile.add(stockPileBis);
        }
        if (validValue[0] == Card.Value.WILD_TWO || validValue[1] == Card.Value.WILD_TWO) {
            System.out.println("Restart");
            start(game);
        }
    }
    
    public ArrayList<ArrayList<Card>> getStockPile() {
        return stockPile;
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

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public boolean validCardPlay(Card card, Card.Color [] validColor, Card.Value [] validValue) {
        return (card.getColor() == validColor[0] || card.getValue() == validValue[0]) 
        || (card.getColor() == validColor[1] || card.getValue() == validValue[1]);
    }

    /**
     * submit draw
     */

    public void submitDraw(String pid) {
        if (deck.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                deck.replaceDeckWith(stockPile.get(0));
            }
            deck.shuffle();
        }
        getPlayerHand(pid).add(deck.drawCard());
    }

    public void yellDos(String pid) {
        if (getPlayerHand(pid).size() == 1) {
            System.out.println(pid + " crie \"DOS\" ! \n");
        }
    }

    public boolean hasThreeIdenticalCards(ArrayList<Card> pHand, Card.Value [] validValue) {
        int countValueStockPile = 1;

        for (int i = 0; i < pHand.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (pHand.get(i).getValue() == validValue[j] && pHand.get(i).getValue() != Card.Value.WILD_TWO) {
                    for (int k = i + 1; k < pHand.size(); k++) {
                        if (pHand.get(k).equals(pHand.get(i))) {
                            countValueStockPile++;
                            if (countValueStockPile == 3) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean hasTwoIdenticalCards(ArrayList<Card> pHand, Card.Value [] validValue) {
        int countValueStockPile = 1;

        for (int i = 0; i < pHand.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (pHand.get(i).getValue() == validValue[j] && pHand.get(i).getValue() != Card.Value.WILD_TWO) {
                    for (int k = i + 1; k < pHand.size(); k++) {
                        if (pHand.get(k).equals(pHand.get(i))) {
                            countValueStockPile++;
                            if (countValueStockPile == 2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param pid
     */

    public void submitPlayerCard(String pid) {

        ArrayList<Card> pHand = getPlayerHand(pid);
  
        // si toutes les cartes de la main du joueur ne sont pas valides (tout le tableau contient des false), il piochera
        boolean [] validCards = new boolean [getPlayerHandSize(getCurrentPlayer())];
        int counterFalse = 0;
        int indexValidCards = 0; 

        boolean hasPlayed = false;

        // compteur de toutes les couleurs dispo dans la main
        int blue = 0;
        int red = 0;
        int green = 0;
        int yellow = 0;
        int wild = 0;
        int[] t = {blue, red, green, yellow, wild};

        Card.Color[] colors = Card.Color.values();

        for (Card card : pHand) {
            for (int i = 0; i < colors.length; i++) {   
                if (card.getColor() == colors[i]) {
                    t[i]++;
                }
            }
        }

        if (hasThreeIdenticalCards(pHand, validValue)) {
            validCards[indexValidCards++] = true; 

            for (int z = 0; z < pHand.size(); z++) {
                for (int i = 0; i < 2; i++) {
                    if (pHand.get(z).getValue() == validValue[i]) {

                        for (int y = z + 1; y < pHand.size(); y++) {
                            if (pHand.get(y).equals(pHand.get(z))) {
                               
                                for (int x = y + 1; x < pHand.size(); x++) {
                                    if (pHand.get(x).equals(pHand.get(z))){
                                        validColor[i] = pHand.get(z).getColor();
                                        Card[] removeCards = {pHand.get(z), pHand.get(y), pHand.get(x)};
                                        System.out.println("\n" + pid + " joue trois cartes " + pHand.get(z) + " dans la stockPile " + (i + 1) + ". \n");
                                        for (int j = 0; j < 3; j++) {
                                            yellDos(pid);
                                            pHand.remove(removeCards[j]);
                                            stockPile.get(i).add(removeCards[j]);
                                        }
                                        hasPlayed = true;
                                        break;
                                    }
                                }
                            break;
                            }
                        }
                    break;
                    }
                }
            }
        }

        else if (hasTwoIdenticalCards(pHand, validValue) && hasPlayed == false) {
            validCards[indexValidCards++] = true; 
            
            for (int z = 0; z < pHand.size(); z++) {
                for (int i = 0; i < 2; i++) {
                    if (pHand.get(z).getValue() == validValue[i]) {
                        for (int y = z + 1; y < pHand.size(); y++) {
                            if (pHand.get(y).equals(pHand.get(z))) {
                                validColor[i] = pHand.get(z).getColor();
                                Card[] removeCards = {pHand.get(z), pHand.get(y)};
                                System.out.println("\n" + pid + " joue deux cartes " + pHand.get(z)  + " dans la stockPile " + (i + 1) + ".");

                                for (int j = 0; j < 2; j++) {
                                    yellDos(pid);
                                    pHand.remove(removeCards[j]);
                                    stockPile.get(i).add(removeCards[j]);
                                }
                                hasPlayed = true;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

        for (Card card : pHand) {
            /* somme 2 cartes de la même couleur que stockpile */
            for (int i = 0; i < 2; i++) {
                if (card.getColor() == validColor[i]) {

                    Value value = card.getValue();
                    int valueInt = value.getValue1();
                    int validValue1Int = validValue[i].getValue1();
                    int z = pHand.indexOf(card);

                    for (int h = z + 1; h < pHand.size(); h++) {
                        Value value2 = pHand.get(h).getValue();
                        int valueInt2 = value2.getValue1();

                        if (pHand.get(h).getColor() == validColor[i] && valueInt + valueInt2 == validValue1Int) {
                            hasPlayed = true;
                            validCards[indexValidCards++] = true;
                            yellDos(pid);

                            Card myCard = pHand.get(h);
                            stockPile.get(i).add(pHand.get(h));

                            pHand.remove(pHand.get(h - 1));

                            pHand.remove(card);

                            stockPile.get(i).add(card);

                            System.out.println("\n" + pid + " joue les cartes " + myCard + " et " + card + " dans la stockPile " + (i + 1) + ".");
                            break;
                        }
                    }
                break;
                }
            }
            if (hasPlayed){
                break;
            }
            else if (validCardPlay(card, validColor, validValue)) {
                hasPlayed = true;
                validCards[indexValidCards++] = true; 
                for (int i = 0; i < 2; i++) {
                    if (card.getColor() == validColor[i] || card.getValue() == validValue[i]) {
                        validColor[i] = card.getColor();
                        validValue[i] = card.getValue();
                        System.out.println();
                        yellDos(pid);
                        pHand.remove(card);
                        stockPile.get(i).add(card);
                        System.out.println(pid + " joue la carte " + card + " dans la stockPile " + (i + 1) + ".");
                        break;
                    }
                }
            break;
            }
        }

        if (t[wild] > 0 && hasPlayed == false) {

            validCards[indexValidCards++] = true; 

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
                laCouleurMax = Card.Color.BLUE;
            }
            else if (indCouleurMax == 1) {
                laCouleurMax = Card.Color.RED;
            }
            else if (indCouleurMax == 2) {
                laCouleurMax = Card.Color.GREEN;
            }
            else if (indCouleurMax == 3) {
                laCouleurMax = Card.Color.YELLOW;
            }

            validColor[0] = laCouleurMax;

            for (Card card : pHand) {
                if (card.getValue() == Card.Value.WILD_TWO) {
                    System.out.println();
                    yellDos(pid);
                    pHand.remove(card);
                    stockPile.get(0).add(card);
                    System.out.println(pid + " joue la carte " + card + " dans le stockPile 1 et demande la couleur " + laCouleurMax + ".");
                    break;
                }
            }
        }

        for (int j = 0; j < validCards.length; j++){
            if (validCards[j] == false) {
                ++counterFalse;
            }
        }

        if (counterFalse == validCards.length) {
            submitDraw(playerIds.get(currentPlayer));
            System.out.println("\n" + pid + " pioche une nouvelle carte.");
        }
        currentPlayer = (currentPlayer + 1) % playerIds.size();
    }
}