import java.io.*;
import java.util.*;

public class Game{
  private String[][] board;
  private boolean[][] wall;
  private boolean[][] secretending;
  private boolean secret;
  private int count;
  
  public Game() {
    board = new String[25][25];
    wall = new boolean[25][25];
    secretending = new boolean[25][25];
    count = 0;
    secret = false;
    generate();
    game();
  }

  //one way for java to wait?
  //Thread.sleep(int millis), parameter is int, and will be milliseconds
  //program pauses? not sure what exactly happens here, but it seems to work.
  public static void waiter(int millis) {
    try {
      Thread.sleep(millis);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
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
        secretending[i][j] = false;
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
    long timeBegin = System.currentTimeMillis();
    Monster monkey = new Monster();
    int moves = 0;
    //will gather player input
    print();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (gamer.getWin() == false){
      secretending[gamer.getYPos()][gamer.getXPos()] = true;
      try {
        System.out.println("\nWhere do you want to move?");
        System.out.println("\tW: Up. \n\tA: Left.\n\tS: Down.\n\tD: Right.");
        direction = reader.readLine();
      }
      catch (IOException e ){ }

      // Move Up
      gamer.setYTemp(gamer.getYPos());
      gamer.setXTemp(gamer.getXPos());
      secretending[gamer.getYPos()][gamer.getXPos()] = true;
      if (direction.compareTo("w") == 0){
        if (wall[gamer.getYPos()-1][gamer.getXPos()] == true){
          board[gamer.getYPos() - 1][gamer.getXPos()] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        } else
          gamer.setYPos(-1);
      }
      // Move Left
      else if (direction.compareTo("a") == 0){
        if (wall[gamer.getYPos()][gamer.getXPos()-1] == true){
          print();
          board[gamer.getYPos()][gamer.getXPos() - 1] = "X";
          System.out.println("A wall blocks you");
          continue;
        } else
          gamer.setXPos(-1);
      }
      // Move Down
      else if (direction.compareTo("s") == 0){
        if (wall[gamer.getYPos()+1][gamer.getXPos()] == true){
          board[gamer.getYPos() + 1][gamer.getXPos()] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        } else
          gamer.setYPos(1);
      } 
      else if (direction.compareTo("0") == 0){
        secret = true;
        continue;
      }
      // Move Right
      else if (direction.compareTo("d") == 0){
        if (wall[gamer.getYPos()][gamer.getXPos()+1] == true){
          board[gamer.getYPos()][gamer.getXPos() + 1] = "X";
          print();
          System.out.println("A wall blocks you");
          continue;
        } else
          gamer.setXPos(1);
      } else {
        System.out.println("Please enter w,a,s, or d to move");
      }
      String temp = board[gamer.getYPos()][gamer.getXPos()];
      board[gamer.getYTemp()][gamer.getXTemp()] = "O";
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
      //displays the monster as a star
      //condition so that player doesn't die at center of map, where monster will be at the start
      if (gamer.getXPos() != 12 && gamer.getYPos() != 12) {
        //makes sure that the monster only begins moving after a certain amount of moves
        if (moves >= 7) {
          //movement of Monster
          String boardTemp = board[monkey.getYPos()][monkey.getXPos()];
          board[monkey.getYPos()][monkey.getXPos()] = "*";
          board[monkey.getYTemp()][monkey.getXTemp()] = boardTemp;
          monkey.chase(gamer);
        }
        //monster catches
        if (gamer.getCaught() == true){
          System.out.println("You have been caught by the monster!");
          waiter(1000);
          System.out.println("sucks to suck");
          break;
        }
      }
      print();
                 
      for (int i = 0; i < secretending.length; i++){
        for (int j = 0; j < secretending[i].length; j ++){
          if (secretending[i][j] == true){
            count ++;
          }
        }
      }
      if (count >= 50){
        secret = true;
      }
                 
      // Secret Ending
      if (gamer.getXPos() == 0 || gamer.getXPos() == 24 || gamer.getYPos() == 0 || gamer.getYPos() == 24 || secret == true){
        gamer.setEscape(true);
        System.out.println("They have come a long way from home.");
        waiter(1000);
        System.out.println("Shush, they have gained enlightenment.");
        waiter(1000);
        System.out.println("You mean they can see us?");
        waiter(1000);
        System.out.println("No, perhaps that flimsy primitive machine of theirs has not reached that level.");
        waiter(1000);
        System.out.println("Have they figured it out?");
        waiter(1000);
        System.out.println("I don't think so. Perchance they might understand a little more than before.");
        waiter(1000);
        System.out.println("Surely not, they are only a mere mortal.");
        waiter(1000);
        System.out.println("But then again...");
        waiter(1000);
        System.out.println("QUIET");
        waiter(1000);
        System.out.println("Whatever the reason, they have decided to visit, it should be applauded");
        waiter(1000);
        System.out.println("Then a toast to their bravery, their chivalry, and their foolishness");
        waiter(1000);
        System.out.println("For once, we see eye to eye");
        waiter(1000);
        System.out.println("Thanks to them.");
        waiter(1000);
        System.out.println("It's time.");
        waiter(1000);
        System.out.println("Let's go then, back to the forest.");
        waiter(1000);
      }
        
      
      // If Player makes it to the edge of the map they have escaped!
      else if (gamer.getXPos() == 0 || gamer.getXPos() == 24 || gamer.getYPos() == 0 || gamer.getYPos() == 24){
        gamer.setEscape(true);
        System.out.println("You escaped the maze!");
        waiter(1000);
        System.out.println("Thank you for playing our maze!");
        waiter(1000);
        System.out.println("We hoped you enjoyed playing!");
        waiter(1000);
        System.out.println("Created by: Project Overhaul");
        waiter(1000);
        System.out.println("Credits:");
        waiter(1000);
        System.out.println("Kevin Xiao");
        waiter(1000);
        System.out.println("Jomin Zhang");
        waiter(1000);
        System.out.println("Jun Hong Wang");
        break;
      }
      moves++;
    }
  }
}
