package edu.westmont.cs030.tests;

import edu.westmont.cs030.maze.Cell;
import edu.westmont.cs030.maze.Direction;
import edu.westmont.cs030.maze.Maze;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

  @Test
  void constructorValid() {
    Maze m = new Maze(10, 10);
    assertNotNull(m.cells);
    assertEquals(10, m.cells.length);
    assertEquals(10, m.cells[0].length);
    assertEquals(10, m.cells[9].length);

    for (int r = 0; r < m.numCols(); r++) {
      for (int c = 0; c < m.numCols(); c++) {
        assertNotNull(m.cells[r][c]);
        assertEquals(r, m.cells[r][c].rowIndex());
        assertEquals(c, m.cells[r][c].colIndex());
      }
    }
  }

  @Test
  void constructorInvalid() {
    assertThrows(IllegalArgumentException.class, () -> new Maze(0, 0));
  }

  @Test
  void initialize() {
    Maze m = new Maze(10, 10);

    for (int r = 0; r < m.numCols(); r++) {
      for (int c = 0; c < m.numCols(); c++) {
        assertNotNull(m.cells[r][c]);
        assertEquals(r, m.cells[r][c].rowIndex());
        assertEquals(c, m.cells[r][c].colIndex());
        assertFalse(m.cells[r][c].isPath());
      }
    }
  }

  @Test
  void getNeighborsAllFour() {
    Maze m = new Maze(10, 10);
    ArrayList<Cell> neighbors = m.getNeighbors(m.cells[3][3]);

    assertEquals(4, neighbors.size());
    assertTrue(neighbors.contains(m.cells[1][3]));
    assertTrue(neighbors.contains(m.cells[3][5]));
    assertTrue(neighbors.contains(m.cells[5][3]));
    assertTrue(neighbors.contains(m.cells[3][1]));
  }

  @Test
  void getNeighborsOnlyOne() {
    Maze m = new Maze(10, 1);
    ArrayList<Cell> neighbors = m.getNeighbors(m.cells[0][0]);

    assertEquals(1, neighbors.size());
    assertTrue(neighbors.contains(m.cells[2][0]));
  }

  @Test
  void getNeighbor() {
    Maze m = new Maze(10, 10);
    assertEquals(m.cells[0][2], m.getNeighbor(m.cells[0][0], Direction.EAST));
    assertEquals(m.cells[2][0], m.getNeighbor(m.cells[0][0], Direction.SOUTH));
    assertNull(m.getNeighbor(m.cells[0][0], Direction.NORTH));
    assertNull(m.getNeighbor(m.cells[0][0], Direction.WEST));
  }

  @Test
  void connectNeighbors() {
    Maze m = new Maze(10, 10);

    m.connectNeighbors(m.cells[0][0], m.getNeighbor(m.cells[0][0], Direction.EAST));
    assertTrue(m.cells[0][1].isPath());

    m.connectNeighbors(m.cells[0][0], m.getNeighbor(m.cells[0][0], Direction.SOUTH));
    assertTrue(m.cells[1][0].isPath());
  }

  @Test
  void testToString() {
    Maze m1x1 = new Maze(1, 1);
    String m1x1row = Cell.WALL_TEXT.repeat(3) + "\n";
    String m1x1String = m1x1row.repeat(3);
    assertEquals(m1x1String, m1x1.toString());

    Maze m2x2 = new Maze(2, 2);
    String m2x2row = Cell.WALL_TEXT.repeat(4) + "\n";
    String m2x2String = m2x2row.repeat(4);
    assertEquals(m2x2String, m2x2.toString());

    Maze m3x3 = new Maze(3, 3);
    String m3x3row = Cell.WALL_TEXT.repeat(5) + "\n";
    String m3x3String = m3x3row.repeat(5);
    assertEquals(m3x3String, m3x3.toString());
  }

  @Test
  void numRows() {
    for (int i = 1; i < 10; i++) {
      assertEquals(i, (new Maze(i, 1)).numRows());
    }
  }

  @Test
  void numCols() {
    for (int i = 1; i < 10; i++) {
      assertEquals(i, (new Maze(1, i)).numCols());
    }
  }
}