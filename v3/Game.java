public class Game extends Player{
  public Game{
    super();
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
      int ytemp = yPos;  public void game(){
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
        System.out.println("You have been caught by the monster!")
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
}
