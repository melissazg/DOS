package com.dos;

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
        ONE,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        WILD,
        WILD_TWO;

        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
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
}
