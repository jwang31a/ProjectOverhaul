import java.io.*;
import java.util.*;

public class Player {
  int[][] board;
  boolean escape = false;
  int xPos;
  int yPos;
  public Player() {
    board = new int[25][25];
    escape = false;
    xPos = 12;
    yPos = 12;
  }

  public void generate() {
    int x, y;
    x = 12;
    y = 12;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 1;
      }
    }
    board[x][y] = 0;

    for (int k = 0; k < 3; k++) {
      if ((int)(Math.random()*3)== 0) {

      }
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
    String direction = "w";
// Manually generated "Walls" for testing
    board[5][12] = 2;
    board[17][12] = 2;
    board[12][5] = 2;
    board[12][17] = 2;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (escape == false){
      try {
        System.out.println("\nWhere do you want to move?");
        System.out.println("\tW: Up. \n\tA: Left.\n\tS: Down.\n\tD: Right.");
        direction = reader.readLine();
      }
      catch (IOException e ){ }
      // Move Up
      System.out.print(direction.compareTo("w"));
      if (direction.compareTo("w") == 0){
        if (board[yPos-1][xPos] == 2){
          System.out.println("A wall blocks you");
          continue;
        }
        yPos -= 1;
      }
      // Move Left
      else if (direction.compareTo("a") == 0){
        if (board[yPos][xPos-1] == 2){
          System.out.println("A wall blocks you");
          continue;
        }
        xPos -= 1;
      }
      // Move Down
      else if (direction.compareTo("s") == 0){
        if (board[yPos+1][xPos] == 2){
          System.out.println("A wall blocks you");
          continue;
        }
        yPos += 1;
      }
      // Move Right
      else {
        if (board[yPos][xPos+1] == 2){
          System.out.println("A wall blocks you");
          continue;
        }
        xPos += 1;
      }
      int temp = board[yPos][xPos];
      board[yPos][xPos] = 0;
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
