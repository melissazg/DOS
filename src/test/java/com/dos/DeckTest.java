package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
        Deck deck = new Deck();
        deck.reset();
        Card card = new Card (Card.Color.RED, Card.Value.FOUR);
        Card card1 = new Card (Card.Color.BLUE, Card.Value.THREE);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(card);
        cards.add(card1);

        deck.replaceDeckWith(cards);
        assertEquals(2, cards.size());
    }

    @Test
    void testReset() {
        Deck deck = new Deck();
        deck.reset();
        assertEquals(false, deck.isEmpty());
    }
}

   