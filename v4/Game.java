import java.io.*;
import java.util.*;

public class Game{
  private String[][] board;
  private boolean[][] wall;
  private boolean[][] permanent;

  public Game() {
    board = new String[25][25];
    wall = new boolean[25][25];
    permanent = new boolean[25][25];
    generate();
    game();
  }

  //maze generation
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

  //prints out maze for player to see
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

  //will be used to begin a game
  public void game(){
    Player gamer = new Player();
    String direction = "";
    // Manually generated "Walls" for testing
    print();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (gamer.getWin() == false){
      try {
        System.out.println("\nWhere do you want to move?");
        System.out.println("\tW: Up. \n\tA: Left.\n\tS: Down.\n\tD: Right.");
        direction = reader.readLine();
      }
      catch (IOException e ){ }

      // Move Up
      int ytemp = gamer.getYPos();
      int xtemp = gamer.getXPos();
      permanent[gamer.getYPos()][gamer.getXPos()] = true;
      if (direction.compareTo("w") == 0){
        if (wall[gamer.getYPos()-1][gamer.getXPos()] == true){
          board[gamer.getYPos() - 1][gamer.getXPos()] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        gamer.setYPos(-1);
      }
      // Move Left
      else if (direction.compareTo("a") == 0){
        if (wall[gamer.getYPos()][gamer.getXPos()-1] == true){
          print();
          board[gamer.getYPos()][gamer.getXPos() - 1] = "X";
          System.out.println("A wall blocks you");
          continue;
        }
        gamer.setXPos(-1);
      }
      // Move Down
      else if (direction.compareTo("s") == 0){
        if (wall[gamer.getYPos()+1][gamer.getXPos()] == true){
          board[gamer.getYPos() + 1][gamer.getXPos()] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        gamer.setYPos(1);
      }
      // Move Right
      else {
        if (wall[gamer.getYPos()][gamer.getXPos()+1] == true){
          board[gamer.getYPos()][gamer.getXPos() + 1] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        }
        gamer.setXPos(1);
      }
      String temp = board[gamer.getYPos()][gamer.getXPos()];
      board[ytemp][xtemp] = "O";
      board[gamer.getYPos()][gamer.getXPos()] = "!";
      // vision
      for (int i = -2;i <=2; i++){
          for(int k = -2;k <=2; k++){
              if ((gamer.getXPos() + k <= 24) && (gamer.getXPos() + k >= 0) && (gamer.getYPos() + i <= 24) && (gamer.getYPos() + i >= 0) && (board[gamer.getYPos()+i][gamer.getXPos()+k] == "?") && (wall[gamer.getYPos()+i][gamer.getXPos()+k] == true)){
                  board[gamer.getYPos()+i][gamer.getXPos()+k] = "X";
              }
              if ((gamer.getXPos() + k <= 24) && (gamer.getXPos() + k >= 0) && (gamer.getYPos() + i <= 24) && (gamer.getYPos() + i >= 0) && (board[gamer.getYPos()+i][gamer.getXPos()+k] == "?") && (wall[gamer.getYPos()+i][gamer.getXPos()+k] == false)){
                  board[gamer.getYPos()+i][gamer.getXPos()+k] = "O";
              }
          }
      }

      //monster catches
      if (gamer.getCaught() == true){
        System.out.println("You have been caught by the monster!");
        break;
      }
      print();
      // If Player makes it to the edge of the map they have escaped!
      if (gamer.getXPos() == 0 || gamer.getXPos() == 24 || gamer.getYPos() == 0 || gamer.getYPos() == 24){
        gamer.setEscape(true);
      }
    }
    System.out.println("You escaped the maze!");
  }
}
