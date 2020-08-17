package models;

public class Coordinate {
    private Integer x;
    private Integer y;

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
        return otherCoordinate.x.equals(this.x) && otherCoordinate.y.equals(this.y);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + "," + " " + y + ")";
    }
}
