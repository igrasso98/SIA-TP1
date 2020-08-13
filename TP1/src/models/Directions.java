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

    public void move(int steps, Directions direction) {
        this.x += direction.x*steps;
        this.y += direction.y*steps;
    }

    public void move(Directions direction) {
        this.x += direction.x;
        this.y += direction.y;
    }
}
