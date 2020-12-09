package main;

/**
 * An x-y coordinate.
 */
public class Coordinate implements Cloneable {

    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return A deep copy of this coordinate.
     */
    public Coordinate clone() {
        return new Coordinate(this.x, this.y);
    }

    public void print() { System.out.printf("%d %d\n", x, y); }
}
