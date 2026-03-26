/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Lisa Kiefer lkiefer@westmont.edu
 */

package edu.westmont.cs030.maze;

import java.util.ArrayList;

/**
 * Represents a maze as a 2D array of {@link Cell}s.
 */
public class Maze {

  /** 2D array of Cells to represent this maze. */
  public final Cell[][] cells;

  /** Initializes the maze given the dimensions of the maze in number of rows and columns.
   * @param numRows number of rows for the maze
   * @param numCols number of columns for the maze
   * */

  public Maze(int numRows, int numCols){
    if (numRows!=0 && numCols!=0){
      this.cells = new Cell[numRows][numCols];
      initialize();
    }
    else{
      throw new IllegalArgumentException();
    }
  }

  /** Resets every cell in this maze to a new Cell configured as a wall. */
  public void initialize(){
    for (int i =0; i< cells.length;i++){
      for (int j = 0; j< cells[0].length;j++){
        cells[i][j] = new Cell(i,j);
        cells[i][j].setPath(false);
      }
    }
  }

  /** Given a Cell, returns an ArrayList of Cell consisting of its neighbors in all Directions,
   * as long as the neighbor(s) exist within the bounds of this maze.
   * @param cell the cell to get the neighbors from
   * @return an ArrayList of Cell containing the cells neighbors
   * */

   public ArrayList<Cell> getNeighbors(Cell cell){
    ArrayList<Cell> neighbors = new ArrayList<>();
    if (getNeighbor(cell, Direction.NORTH)!= null) {neighbors.add(getNeighbor(cell, Direction.NORTH));}
    if (getNeighbor(cell, Direction.EAST)!= null) {neighbors.add(getNeighbor(cell, Direction.EAST));}
    if (getNeighbor(cell, Direction.SOUTH)!= null) {neighbors.add(getNeighbor(cell, Direction.SOUTH));}
    if (getNeighbor(cell, Direction.WEST)!= null) {neighbors.add(getNeighbor(cell, Direction.WEST));}
    return neighbors;
  }

  /** Given a Cell, returns a neighboring Cell in the given Direction.
   * @param cell the cell to get the neighbor from
   * @param dir the direction to get the neighbor from
   * @return the neighboring cell (or null if neighbor does not exist)
   * */
  public Cell getNeighbor(Cell cell, Direction dir){
    try{
      if (dir == Direction.SOUTH || dir == Direction.NORTH){
        return cells[cell.getNeighborRowIndex(dir,numRows())][cell.colIndex()];
      }
      else {return cells[cell.rowIndex()][cell.getNeighborColIndex(dir, numCols())];}
    }
    catch (IndexOutOfBoundsException exception){
      return null;
    }
  }

  /** Given two neighboring Cells, sets the Cell that is located between the neighbors as a path,
   * thus connecting the two neighboring Cells together.
   * @param origin one of the two neighboring cells
   * @param neighbor the other neighboring cell
   * */
  public void connectNeighbors(Cell origin, Cell neighbor){
    origin.setPath(true);
    neighbor.setPath(true);
    // when cells have same row index => neighbor is West/East
    if (origin.rowIndex() == neighbor.rowIndex()){
      if (origin.colIndex() < neighbor.colIndex()){
        cells[origin.rowIndex()][origin.colIndex()+1].setPath(true);
      } // east neighbor
      else {
        cells[origin.rowIndex()][origin.colIndex()-1].setPath(true);
      } //west neighbor
    }
    else {
      if (origin.rowIndex()< neighbor.rowIndex()){
        cells[origin.rowIndex()+1][origin.colIndex()].setPath(true);
      } //south neighbor
      else {
        cells[origin.rowIndex()-1][origin.colIndex()].setPath(true);
      } //north neighbor
    }
  }

  /** String representation of this maze to be used in rendering the maze.
   * @return a String representing the maze
   * */
  @Override
  public String toString(){
    String mazeStr = "";
    mazeStr += Cell.WALL_TEXT.repeat(cells[0].length+2);
    mazeStr += "\n";
    for (int i = 0; i < cells.length; i++){
      mazeStr += Cell.WALL_TEXT;
      for (int j = 0;j < cells[0].length;j++){
        mazeStr += cells[i][j].getText();
      }
      mazeStr += Cell.WALL_TEXT + "\n";
    }
    mazeStr += Cell.WALL_TEXT.repeat(cells[0].length+2);
    mazeStr += "\n";
    return mazeStr;
  }

  /** Helper function that returns the number of rows in this maze.
   * @return the number of rows as integer
   * */
  public int numRows(){return cells.length;}

  /** Helper function that returns the number of columns in this maze.
   * @return the number of columns as integer
   * */
  public int numCols(){return cells[0].length;}

}
