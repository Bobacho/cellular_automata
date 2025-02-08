package org.example;

import java.util.Scanner;

import javax.swing.JFrame;

public class Game extends JFrame {

  public Game(int value) {
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    Board board = new Board(value);
    this.add(board);
  }

}
