/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Lisa Kiefer lkiefer@westmont.edu
 */

package edu.westmont.cs030.maze;

/**
 * Represents a cell of a maze, which may be a wall or path.
 * <br>
 * This class has three (3) private instance variables:
 * <ul>
 *   <li><code>private boolean isPath</code> indicates whether this cell is a path or a wall (mutable)</li>
 *   <li><code>private final int r</code>this cell's row index in the maze</li>
 *   <li><code>private final int c</code>this cell's column index in the maze</li>
 * </ul>
 */
public class Cell {

  /**
   * Text to render for the cell if it's a "wall."
   */
  public static final String WALL_TEXT = "██";  //

  /**
   * Text to render for the cell if it's a "path."
   */
  public static final String PATH_TEXT = "  ";

  /**
   * indicates whether this cell is a path or a wall (mutable)
   */

  private boolean isPath;
  /**
   * Cell's row index for the maze
   */
  private final int r;
  /**
   * Cell's column index for the maze
   */
  private final int c;

  /**
   * Construct a cell with the given row and column indices regarding its location in a maze.
   * @param rowIndex row index of the cell
   * @param colIndex column index of the cell
   */
  public Cell(int rowIndex, int colIndex){
    this.r = rowIndex;
    this.c = colIndex;
  }

  /**
   * Getter method for the column index
   * @return the column index of the cell it was invoked on (an integer)
   */
  public int colIndex(){return this.c;}

  /**
   * Getter method for the row index
   * @return the row index of the cell it was invoked on (an integer)
   */
  public int rowIndex(){return this.r;}

  /**
   * Getter method for the isPath variable
   * @return true if the cell is a path, false if the cell is a well
   */
  public boolean isPath(){return this.isPath;}

  /**
   * Setter method for the isPath variable
   * @param path true to set the cell as a path, false if it should be set as a wall
   */
  public void setPath(boolean path){
    this.isPath = path;
  }

  /**
   * Computes the row index of the neighbor in the given direction (to account for wall cells in between, each neighbor is two cells away)
   * @param dir Direction that indicates which neighbors row index to compute
   * @param rowLimit number of rows in the maze to be used as the exclusive upper bound
   * @return an integer representing the row index of the neighbor
   * @throws IndexOutOfBoundsException
   */
  public int getNeighborRowIndex(Direction dir, int rowLimit) throws IndexOutOfBoundsException{
    if (dir == Direction.NORTH && (this.r - 2 >= 0)) {return this.r -2;}
    else if (dir == Direction.SOUTH &&(this.r + 2 < rowLimit)) {return this.r +2;}
    else {throw new IndexOutOfBoundsException();}
  }
  /**
   * Computes the column index of the neighbor in the given direction (to account for wall cells in between, each neighbor is two cells away)
   * @param dir Direction that indicates which neighbors column index to compute
   * @param colLimit number of columns in the maze to be used as the exclusive upper bound
   * @return an integer representing the column index of the neighbor
   * @throws IndexOutOfBoundsException
   */
  public int getNeighborColIndex(Direction dir, int colLimit){
    if (dir == Direction.WEST && (this.c - 2 >= 0)) {return this.c - 2;}
    else if (dir == Direction.EAST && (this.c + 2 < colLimit)) {return this.c + 2;}
    else {throw new IndexOutOfBoundsException();}
  }

  /**
   * Returns the text used to render this cell as a portion of a maze.
   * @return PATH_TEXT if this cell is a path, else WALL_TEXT
   */
  public String getText(){
    if (isPath) {return PATH_TEXT;}
    else {return WALL_TEXT;}
  }

  /**
   * Return the text representation of this cell for debugging (not rendering) purposes.
   * @return a string that is the text representation of this cell
   */
  @Override
  public String toString() {
    if (isPath) {return String.format("Cell [%2d][%2d]: PATH", this.r, this.c);}
    else {return String.format("Cell [%2d][%2d]: WALL", this.r, this.c);}
  }
}


