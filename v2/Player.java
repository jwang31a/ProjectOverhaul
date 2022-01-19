import java.io.*;
import java.util.*;

public class Player {
  int[][] board;
  boolean[][] wall;
  boolean escape = false;
  int xPos;
  int yPos;
  public Player() {
    board = new int[25][25];
    wall = new boolean[25][25];
    escape = false;
    xPos = 12;
    yPos = 12;
  }

  public void generate() {
    boolean endgame = false;
    int x, y;
    x = 12;
    y = 12;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 1;
        wall[i][j] = true;
      }
    }
    board[x][y] = 3;
    while (endgame == false){
      for (int k = 0; k < 200; k++) {
        double a = Math.random() * 4;
        if ((a < 1) && (wall[y-1][x-1] == true) && (wall[y-2][x] == true) && (wall[y-1][x+1] == true && y > 2)) {
          y = y - 1;
        }
        else if (a >= 1 && a < 2  && wall[y-1][x-1] == true && wall[y][x-2] == true && wall[y+1][x-1] == true && x > 2){
          x = x - 1;
        }
        else if (a >= 2 && a < 3 && wall[y+1][x-1] == true && wall[y+2][x] == true && wall[y+1][x+1] == true && y < 22){
          y = y + 1;
        }
        else if (a >= 3 && a < 4 && wall[y-1][x+1] == true && wall[y][x+2] == true && wall[y+1][x+1] == true && x < 22){
          x = x + 1;
        }
        wall[y][x] = false;
      }
      endgame = true;
    }
  }

  public void print() {
    String out = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (j == board[i].length - 1) {
          out += board[i][j];
        } else
          out += board[i][j] + ", ";
      }
      out += "\n";
    }
    System.out.println(out);
  }
  public void game(){
    String direction = "";
// Manually generated "Walls" for testing
    print();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (escape == false){
      try {
        System.out.println("\nWhere do you want to move?");
        System.out.println("\tW: Up. \n\tA: Left.\n\tS: Down.\n\tD: Right.");
        direction = reader.readLine();
      }
      catch (IOException e ){ }

      // Move Up
      int ytemp = yPos;
      int xtemp = xPos;
      if (direction.compareTo("w") == 0){
        if (wall[yPos-1][xPos] == true){
          board[yPos - 1][xPos] = 2;
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        yPos -= 1;
      }
      // Move Left
      else if (direction.compareTo("a") == 0){
        if (wall[yPos][xPos-1] == true){
          print();
          board[yPos][xPos - 1] = 2;
          System.out.println("A wall blocks you");
          continue;
        }
        xPos -= 1;
      }
      // Move Down
      else if (direction.compareTo("s") == 0){
        if (wall[yPos+1][xPos] == true){
          board[yPos + 1][xPos] = 2;
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        yPos += 1;
      }
      // Move Right
      else {
        if (wall[yPos][xPos+1] == true){
          board[yPos][xPos + 1] = 2;
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        xPos += 1;
      }
      int temp = board[yPos][xPos];
      board[ytemp][xtemp] = 0;
      board[yPos][xPos] = 3;
      print();
      // If Player makes it to the edge of the map they have escaped!
      if (xPos == 0 || xPos == 24 || yPos == 0 || yPos == 24){
        escape = true;
      }
    }
    System.out.println("You escaped the maze!");
  }
  public static void main(String[] args) {
    Player test = new Player();
    test.generate();
    test.game();
    test.print();
  }
}
