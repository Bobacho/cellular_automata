package org.example;

import java.awt.Rectangle;

public class Cell extends Rectangle {
  public int state = 0;

  public Cell(int x, int y, int width, int height, int state) {
    super(x, y, width, height);
    this.state = state;
  }
}
