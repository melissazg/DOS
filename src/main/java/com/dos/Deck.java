package com.dos;

import com.dos.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Deck of cards
 * 
 * Nous possédons les cartes suivantes (par couleur) : 
 * 4 cartes de 1
 * 4 cartes de 3
 * 3 cartes de 4 
 * 3 cartes de 5 
 * 2 cartes de 6 
 * 2 cartes de 7 
 * 2 cartes de 8 
 * 2 cartes de 9 
 * 2 cartes de 10
 * 2 jokers
 * 1 carte de 2
 */

public class Deck {

    private Card[] cards;
    private int cardsInDeck;

    public Deck() {
        cards = new Card[108];
    }

    public void reset() {
        Card.Color[] colors = Card.Color.values();
        cardsInDeck = 0;

        for (int i = 0; i < colors.length-1; i++) {
            Card.Color color = colors[i];

            for (int j = 0; j < 2 ; j++) {
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
            }

            for (int j = 2; j < 4 ; j++) {
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
            }

            for (int j = 4; j < 10 ; j++) {
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
                cards[cardsInDeck++] = new Card(color, Card.Value.getValue(j));
            }

            for (int j = 0; j < 2 ; j++) {
                cards[cardsInDeck++] = new Card(Card.Color.WILD, Card.Value.WILD);
            }

            cards[cardsInDeck++] = new Card(Card.Color.WILD, Card.Value.WILD_TWO);
        }
    }

    public void replaceDeckWith(ArrayList<Card> cards) {
        this.cards = cards.toArray(new Card[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }

    public boolean isEmpty() {
        return cardsInDeck == 0;
    }

    public void shuffle() {
        int n = cards.length;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }

    public Card drawCard() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException("There is no card in the Deck.");
        }
        return cards[--cardsInDeck];
    }

    public Card[] drawCard(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Must draw positive cards but tried to draw " + n + " cards.");
        }

        if (n > cardsInDeck) {
            throw new IllegalArgumentException("Can't draw " + n + " cars since there are only " + cardsInDeck + " cards in the deck");
        }

        Card[] ret = new Card[n];

        for (int i = 0; i < n; i++) {
            ret[i] = cards[--cardsInDeck];
        }
        return ret;
    }
}