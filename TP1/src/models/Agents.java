package models;

public enum Agents {
    WALL('#'),
    BOX('$'),
    PLAYER('@'),
    GOALS('*');

    private char identifier;
    Agents(char id) {
        this.identifier = id;
    }

    public char getValue() {
        return identifier;
    }
}
