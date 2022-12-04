package com.dos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.dos.Card.Color;
import com.dos.Card.Value;

public class CardTest {
    /**
     * 
     */
    @Test
    void testGetColor() {
       
        Card carte = new Card (Color.RED, Value.FOUR);
        carte.getColor();
        assertEquals(Color.RED, carte.getColor());


    }

    @Test
    void testGetValue() {
        Card carte = new Card (Color.RED, Value.FOUR);
        carte.getValue();
        assertEquals(Value.FOUR, carte.getValue());
    }

    @Test
    void testToString() {

    }
}
