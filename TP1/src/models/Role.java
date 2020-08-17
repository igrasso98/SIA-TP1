package models;

public enum Role {
    WALL('#'),
    BOX('$'),
    PLAYER('@'),
    SPACE(' ');

    private char identifier;
    Role(char id) {
        this.identifier = id;
    }
}
