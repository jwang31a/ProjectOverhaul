public class Woo {
  int[][] board;

  public Woo() {
    board = new int[25][25];
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

  public static void main(String[] args) {
    Player test = new Player();
    test.generate();
    test.print();
  }
}
