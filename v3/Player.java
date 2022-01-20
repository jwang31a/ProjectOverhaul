import java.io.*;
import java.util.*;

public class Player {
  private String[][] board;
  private boolean[][] wall;
  private boolean[][] permanent;
  private boolean escape = false;
  private boolean caught = false;
  private int xPos;
  private int yPos;
  public Player() {
    board = new String[25][25];
    wall = new boolean[25][25];
    permanent = new boolean[25][25];
    escape = false;
    xPos = 12;
    yPos = 12;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public boolean getWin() {
    return escape;
  }

  public void setCaught(){
    caught = true;
  }

  public void generate() {
    boolean endgame = false;
    boolean endpath = false;
    int x, y;
    x = 12;
    y = 12;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = "?";
        wall[i][j] = true;
        permanent[i][j] = false;
      }
    }
    board[x][y] = "!";
    while (endgame == false){
      for (int i = 0; i < 3; i ++){
        for (int k = 0; k < 90; k++) {
            wall[y][x] = false;
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
        }
        x = 12;
        y = 12;
      }
    for (int n = 0; n < 3; n ++){
        while (endpath == false){
            double b = Math.random() * 25;
            double c = Math.random() * 25;
            if ((wall[(int)(b)][(int)(c)] == false) && ((b > 20 || b < 5) || (c > 20 || c < 5))){
                endpath = true;
            }
            if (endpath == true && c > 20){
                for (int i = 20; i < 25; i ++){
                    wall[(int)(b)][i] = false;
                }
            }
            else if (endpath == true && b < 5){
                for (int i = 0; i < 5; i ++){
                    wall[i][(int)(c)] = false;
                }
            }
            else if (endpath == true && c < 5){
                for (int i = 0; i < 5; i ++){
                    wall[(int)(b)][i] = false;
                }
            }
            else if (endpath == true && b > 20){
                for (int i = 20; i < 25; i ++){
                    wall[i][(int)(c)] = false;
                }
            }
        }
        endpath = false;
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
      permanent[yPos][xPos] = true;
      if (direction.compareTo("w") == 0){
        if (wall[yPos-1][xPos] == true){
          board[yPos - 1][xPos] = "X";
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
          board[yPos][xPos - 1] = "X";
          System.out.println("A wall blocks you");
          continue;
        }
        xPos -= 1;
      }
      // Move Down
      else if (direction.compareTo("s") == 0){
        if (wall[yPos+1][xPos] == true){
          board[yPos + 1][xPos] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        yPos += 1;
      }
      // Move Right
      else {
        if (wall[yPos][xPos+1] == true){
          board[yPos][xPos + 1] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        xPos += 1;
      }
      String temp = board[yPos][xPos];
      board[ytemp][xtemp] = "O";
      board[yPos][xPos] = "!";
      // vision
      for (int i = -2;i <=2; i++){
          for(int k = -2;k <=2; k++){
              if ((xPos + k <= 24) && (xPos + k >= 0) && (yPos + i <= 24) && (yPos + i >= 0) && (board[yPos+i][xPos+k] == "?") && (wall[yPos+i][xPos+k] == true)){
                  board[yPos+i][xPos+k] = "X";
              }
              if ((xPos + k <= 24) && (xPos + k >= 0) && (yPos + i <= 24) && (yPos + i >= 0) && (board[yPos+i][xPos+k] == "?") && (wall[yPos+i][xPos+k] == false)){
                  board[yPos+i][xPos+k] = "O";
              }
          }
      }

      //monster catches
      if (caught == true){
        System.out.println("You have been caught by the monster!");
        break;
      }
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
