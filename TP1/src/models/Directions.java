package models;

public enum Directions {
        UP(-1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        DOWN(1, 0);

        private int x;
        private int y;
     Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
         return x;
    }

    public int getY() {
         return y;
    }

    public Directions getOpossite() {
        switch (this) {
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return UP;
    }

    public Directions getPartner() {
        switch (this) {
            case UP:
                return RIGHT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            case DOWN:
                break;
        }
        return LEFT;
    }


}
