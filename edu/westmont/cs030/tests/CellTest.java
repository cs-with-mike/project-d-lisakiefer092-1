package edu.westmont.cs030.tests;

import edu.westmont.cs030.maze.Cell;
import edu.westmont.cs030.maze.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

  @Test
  void constructor() {
    Cell c = new Cell(123, 456);

    assertEquals(123, c.rowIndex());
    assertEquals(456, c.colIndex());
    assertFalse(c.isPath());
  }

  @Test
  void rowIndex() {
    Cell c0 = new Cell(0, 0);
    Cell c1 = new Cell(1, 0);

    assertEquals(0, c0.rowIndex());
    assertEquals(1, c1.rowIndex());
  }

  @Test
  void colIndex() {
    Cell c0 = new Cell(0, 0);
    Cell c1 = new Cell(0, 1);

    assertEquals(0, c0.colIndex());
    assertEquals(1, c1.colIndex());
  }

  @Test
  void isPath() {
    Cell c = new Cell(123, 456);
    assertFalse(c.isPath());
  }

  @Test
  void setPath() {
    Cell c = new Cell(123, 456);

    c.setPath(true);
    assertTrue(c.isPath());

    c.setPath(false);
    assertFalse(c.isPath());
  }

  @Test
  void getNeighborRowIndexWithinBounds() {
    Cell c = new Cell(5, 5);
    assertEquals(3, c.getNeighborRowIndex(Direction.NORTH, 100));
    assertEquals(7, c.getNeighborRowIndex(Direction.SOUTH, 100));
  }

  @Test
  void getNeighborRowIndexOutOfBounds() {
    Cell c = new Cell(0, 0);
    assertThrows(IndexOutOfBoundsException.class, () -> c.getNeighborRowIndex(Direction.NORTH, 2));
    assertThrows(IndexOutOfBoundsException.class, () -> c.getNeighborRowIndex(Direction.SOUTH, 2));
  }

  @Test
  void getNeighborColIndexWithinBounds() {
    Cell c = new Cell(5, 5);
    assertEquals(7, c.getNeighborColIndex(Direction.EAST, 100));
    assertEquals(3, c.getNeighborColIndex(Direction.WEST, 100));
  }

  @Test
  void getNeighborColIndexOutOfBounds() {
    Cell c = new Cell(0, 0);
    assertThrows(IndexOutOfBoundsException.class, () -> c.getNeighborColIndex(Direction.EAST, 2));
    assertThrows(IndexOutOfBoundsException.class, () -> c.getNeighborColIndex(Direction.WEST, 2));
  }

  @Test
  void getText() {
    Cell c = new Cell(0, 0);
    assertEquals(Cell.WALL_TEXT, c.getText());

    c.setPath(true);
    assertEquals(Cell.PATH_TEXT, c.getText());

    c.setPath(false);
    assertEquals(Cell.WALL_TEXT, c.getText());
  }

  @Test
  void testToString() {
    Cell c0 = new Cell(0, 1);
    Cell c1 = new Cell(12, 23);

    assertEquals("Cell [ 0][ 1]: WALL", c0.toString());
    assertEquals("Cell [12][23]: WALL", c1.toString());

    c0.setPath(true);
    assertEquals("Cell [ 0][ 1]: PATH", c0.toString());
  }
}