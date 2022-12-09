package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DeckTest {
    @Test
    void testDrawCard() {
        
        Deck deck = new Deck();
        deck.reset();
        Card [] cards = deck.drawCard(7);
        assertEquals(7, cards.length);
    }

    @Test
    void testIsEmpty() {
        Deck deck = new Deck();
        assertEquals(true, deck.isEmpty());
    }

    @Test
    void testReplaceDeckWith() {
        /*Deck deck = new Deck();
        deck.reset();
        //int cardsInDeck =0;
        Card card = new Card (Color.RED, Value.FOUR);
        Card card1 = new Card (Color.BLUE, Value.THREE);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card1);

        deck.replaceDeckWith(cards);
        assertEquals(2, cards.length);*/
    }

    @Test
    void testReset() {

    }

    @Test
    void testShuffle() {

    }
}
