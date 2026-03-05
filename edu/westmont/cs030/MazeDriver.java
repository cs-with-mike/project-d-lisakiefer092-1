/**
 * Westmont College Spring 2024
 * CS 030 Project D
 *
 * @author peterpeng https://stackoverflow.com/a/24416418
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 */

/* ***************************************
 *   DO NOT MODIFY ANYTHING IN THIS FILE
 * **************************************/

package edu.westmont.cs030;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.JTextComponent;

import edu.westmont.cs030.maze.*;

public class MazeDriver {

  private static final int DEFAULT_VALUE = 25;

  public static void main(String[] args) {
    MazeGenerator mazeGen = initializeMazeGeneratorFromArgs(args);
    JTextComponent textPane = mazeGen.getRenderTarget();

    if (textPane != null) {
      Font font = new Font("Courier New", Font.PLAIN, 13);
      JFrame frame = new JFrame("MazeDriver");
      JButton button = new JButton("Generate New Maze");

      frame.setLayout(new BorderLayout());
      frame.add(button, BorderLayout.NORTH);
      frame.add(textPane, BorderLayout.CENTER);

      textPane.setMargin(new Insets(20, 33, 20, 33));
      textPane.setFont(font);

      button.addActionListener(e -> new SwingWorker<Void, Object>() {
        @Override
        protected Void doInBackground() {
          button.setEnabled(false);
          mazeGen.generateMaze();
          button.setEnabled(true);
          return null;
        }
      }.execute());

      frame.setSize(500, 500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    } else {
      mazeGen.generateMaze();
    }
  }

  private static MazeGenerator initializeMazeGeneratorFromArgs(String[] args) {
    boolean isRandom = false;
    int mazeHeight = DEFAULT_VALUE;
    int mazeWidth = DEFAULT_VALUE;
    int initRow = mazeHeight / 2;
    int initCol = mazeWidth / 2;
    int renderDelayMillis = DEFAULT_VALUE;
    JTextComponent renderTarget = null;

    try {
      if (!(args[0].equalsIgnoreCase("default") || args[0].equalsIgnoreCase("test"))) {
        isRandom = args[0].equalsIgnoreCase("random");
        mazeHeight = Integer.parseInt(args[1]);
        mazeWidth = Integer.parseInt(args[2]);
        initRow = Integer.parseInt(args[3]);
        initCol = Integer.parseInt(args[4]);
        renderDelayMillis = Integer.parseInt(args[5]);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Not enough arguments");
    } catch (NumberFormatException e) {
      System.out.println("Incorrect argument format");
    }

    if (renderDelayMillis == -1 || args[0].equalsIgnoreCase("test")) {
      renderDelayMillis = 0;
    } else {
      renderTarget = new JTextPane();
    }

    return new MazeGenerator(mazeHeight, mazeWidth, initRow, initCol,
        isRandom, renderTarget, renderDelayMillis);
  }
}