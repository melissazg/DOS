package com.dos;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;

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

    public String getPreviousPlayer(int i) {
        int index = this.currentPlayer - i;
        if (index == -1) {
            index = playerIds.length - 1;
        }
        return this.playerIds[index];
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

    public Card.Value decompositionValue (int value){
        Card.Value [] values = Card.Value.values();
        for (int i=0; i<= value/2; i++){
            value = value-1;
            values[0], values[value];
        }


    }
    public boolean validCardPlay(Card card) {
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (this.playerIds[this.currentPlayer] != pid) {
            throw new InvalidPlayerTurnException("it is not " + pid + " 's turn", pid);
        }
    }

    /**
     * submit draw
     */

    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());

        currentPlayer = (currentPlayer + 1) % playerIds.length;
    }

    public void setCardColor(Card.Color color) {
        validColor = color;
    }

    public void submitPlayerCard(String pid, Card card, Card.Color declaredColor)
        throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException {
            checkPlayerTurn(pid);

            ArrayList<Card> pHand = getPlayerHand(pid);

            if (!validCardPlay(card)) {
                if (card.getColor() == Card.Color.WILD) {
                    validColor = card.getColor();
                    validValue = card.getValue();
                }
                if (card.getColor() != validColor) {
                    throw new InvalidColorSubmissionException(message, card.getColor(), validColor);
                }
                if (card.getValue() != validValue) {
                    throw new InvalidValueSubmissionException(message, card.getColor(), validColor);
                }
            }
            pHand.remove(card);

            if (hasEmptyHand(this.playerIds[currentPlayer])) {

            }

            validColor = card.getColor();
            validValue = card.getValue();
            stockPile.add(card);
    }
}

class InvalidPlayerTurnException extends Exception {
    String playerId;

    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId = pid;
    }

    public String getPid() {
        return playerId;
    }
}

class InvalidColorSubmissionException extends Exception {
    private Card.Color expected;
    private Card.Color actual;

    public InvalidColorSubmissionException(String message, Card.Color actual, Card.Color expected) {
        this.actual = actual;
        this.expected = expected;
    }
}

class InvalidValueSubmissionException extends Exception {
    private Card.Value expected;
    private Card.Value actual;

    public InvalidColorSubmissionException(String message, Card.Color actual, Card.Color expected) {
        this.actual = actual;
        this.expected = expected;
    }
}
