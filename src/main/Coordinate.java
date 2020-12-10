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

    /**
     * Print this coordinate to terminal.
     */
    public void print() { System.out.println(toString()); }

    /**
     * @return This coordinate as a string.
     */
    public String toString() {
        return x + " " + y;
    }
}
