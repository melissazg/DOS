package com.dos;

import java.util.HashMap;
import java.util.Map;

/**
 * List the possible colors and values of a card
 */

public class Card {

    public enum Color {
        BLUE,
        RED,
        GREEN,
        YELLOW,
        WILD;

        private static final Color[] colors = Color.values();

        public static Color getColor(int i) {
            return Color.colors[i];
        } 
    }

    public enum Value {
        ONE(1),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        WILD(0),
        WILD_TWO(2);

        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }

        private int value;
        private static Map map = new HashMap<>();
    
        private Value (int value) {
            this.value = value;
        }
    
        static {
            for (Value valeur : Value.values()) {
                map.put(valeur.value, valeur);
            }
        }
    
        public static Value valueOf(int valeur) {
            return (Value) map.get(valeur);
        }
    
        public int getValue1() {
            return value;
        }
    }

    private final Color color;
    private final Value value;

    public Card (final Color color, final Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }

    public String toString() {
        return color + "_" + value;
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Card){
            Card card = (Card)other;
            return this.color.equals(card.getColor()) && this.value.equals(card.getValue());
        }
        return false;
    }
}
