package models;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(Directions direction) {
        this.x += direction.getX();
        this.y += direction.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate otherCoordinate = (Coordinate) o;
        return otherCoordinate.x == this.x && otherCoordinate.y == this.y;
    }
}
