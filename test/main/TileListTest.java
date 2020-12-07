package main;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TileList.java and its iterator TileIterator.
 */
class TileListTest {

    /**
     * Test the add() method, which adds a tile to the list with
     * the precondition that the tile is not already in the list.
     */
    @Test
    public void testAdd() {
        TileList tileList = new TileList();

        Tile tile = tileList.pop(4);
        tileList.add(tile);

        // An exception occurs if a tile is added to a list while the
        // tile is already in the list.
        Exception exception = null;
        try {
            tileList.add(tile);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getClass(), InvalidParameterException.class);
    }

    /**
     * Test the pop() method, which returns a Tile with the specified id
     * and removes it from the list.
     */
    @Test
    public void testPop() {
        TileList tileList = new TileList();

        Tile tile1 = tileList.pop(5);
        assertEquals(tile1.getId(), 5);
        Tile tile2 = tileList.pop(17);
        assertEquals(tile2.getId(), 17);

        // The same tile cannot be popped twice, otherwise, an exception will occur.
        Exception exception = null;
        try {
            tileList.pop(5);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getClass(), InvalidParameterException.class);
    }

    // Test the inner-class iterator of TileList.
    @Test
    public void testIterator() {
        TileList tileList = new TileList();
        Iterator<Tile> tileIterator = tileList.iterator();
        tileList.pop(2);
        tileList.pop(17);
        assertTrue(tileIterator.hasNext());
        assertEquals(0, tileIterator.next().getId());
        assertEquals(1, tileIterator.next().getId());
        assertEquals(3, tileIterator.next().getId());
        assertTrue(tileIterator.hasNext());
        for (int i = 4; i < 17; i++) {
            assertEquals(i, tileIterator.next().getId());
        }
        assertEquals(18, tileIterator.next().getId());
        assertEquals(19, tileIterator.next().getId());
        assertTrue(tileIterator.hasNext());
        assertEquals(20, tileIterator.next().getId());
        assertFalse(tileIterator.hasNext());

        Exception exception = null;
        try {
            tileIterator.next();
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(exception.getClass(), NoSuchElementException.class);
    }
}