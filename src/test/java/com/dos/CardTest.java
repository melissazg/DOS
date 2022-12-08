package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.dos.Card.Color;
import com.dos.Card.Value;

public class CardTest {

    @Test
    void testGetColor() {
        Card card = new Card (Color.RED, Value.FOUR);
        card.getColor();
        assertEquals(Color.RED, card.getColor());
    }

    @Test
    void testGetValue() {
        Card card = new Card (Color.RED, Value.FOUR);
        card.getValue();
        assertEquals(Value.FOUR, card.getValue());
    }

    @Test
    void testToString() {
        Card card = new Card (Color.RED, Value.FOUR);
        assertEquals("RED_FOUR", card.toString());
    }
}
