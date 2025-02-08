package org.example;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class Board extends JPanel {

  private List<List<Cell>> cells = new ArrayList<>();
  private boolean isInitializedCells = false;
  private int[] ruleSet;

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!isInitializedCells) {
      initializeCells();
      isInitializedCells = true;
    }
    for (List<Cell> row : cells) {
      for (Cell cel : row) {
        g.drawRect((int) cel.getX(), (int) cel.getY(), (int) cel.getWidth(), (int) cel.getHeight());
        if (cel.state == 1) {
          g.fillRect((int) cel.getX(), (int) cel.getY(), (int) cel.getWidth(), (int) cel.getHeight());
        }
      }
    }
    System.out.println("Painting");
  }

  private void initializeCells() {
    int boardSize = 150;
    int widhtRectangle = this.getSize().width / boardSize;
    int heightRectangle = this.getSize().height / boardSize;
    List<Cell> row = new ArrayList<>();
    for (int j = 0; j < this.getSize().width; j += widhtRectangle) {
      row.add(new Cell(j, 0, widhtRectangle, heightRectangle, 0));
    }
    row.get(row.size() / 2 - 1).state = 1;
    cells.add(row);
    for (int i = 1; i < this.getSize().width; i++) {
      List<Cell> rows = new ArrayList<>();
      rows.add(new Cell(0, i * heightRectangle, widhtRectangle, heightRectangle, 0));
      for (int j = 1; j < cells.getLast().size() - 1; j++) {
        Integer[] currentRuleSet = Arrays.copyOfRange(cells.get(i - 1)
            .stream()
            .map((c) -> c.state)
            .toArray(Integer[]::new), j - 1, j + 2);
        rows.add(
            new Cell(j * widhtRectangle, i * heightRectangle, widhtRectangle, heightRectangle,
                ruleSet[7 - parseFromBase2(currentRuleSet)]));
      }
      rows.add(new Cell(cells.get(0).size() * (widhtRectangle - 1), i * heightRectangle, widhtRectangle,
          heightRectangle, 0));
      cells.add(rows);
    }

  }

  private int[] parseToBase2(int value, int lenght) {
    int[] result = new int[lenght + 1];
    int nextValue = value;
    for (int i = 0; i <= lenght; i++) {
      if (Math.pow(2, lenght - i) <= nextValue) {
        result[i] = 1;
        nextValue = nextValue % (int) Math.pow(2, lenght - i);
      } else {
        result[i] = 0;
      }
    }
    return result;

  }

  private int parseFromBase2(Integer[] value) {
    int result = 0;
    for (int i = value.length - 1; i >= 0; i--) {
      result += Math.pow(2, i) * value[value.length - 1 - i];
    }
    return result;
  }

  public Board(int ruleValue) {
    this.ruleSet = parseToBase2(ruleValue, 7);
  }
}
