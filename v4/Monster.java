public class Monster {
  private int xPos, yPos;
  public Monster() {
    xPos = 12;
    yPos = 12;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public void chase(Player input) {
    int pXPos = input.getXPos();
    int pYPos = input.getYPos();
    int randomness = (int)(Math.random()*100);
    if (randomness < 35) {
      if (xPos < pXPos) {
        xPos++;
      } else if (xPos > pXPos){
        xPos--;
      } else if (yPos < pYPos){
        yPos++;
      } else {
        yPos--;
      }
    } else if (randomness >= 35 && randomness < 70) {
      if (yPos < pYPos) {
        yPos++;
      } else if (yPos > pYPos){
        yPos--;
      } else if (xPos < pXPos){
        xPos++;
      } else {
        xPos--;
      }
    } else if (randomness >= 70 && randomness < 85) {
      if (xPos < pXPos) {
        xPos--;
      } else if (xPos > pXPos){
        xPos++;
      } else if (yPos < pYPos){
        yPos--;
      } else {
        yPos++;
      }
    } else {
      if (yPos < pYPos) {
        yPos--;
      } else if (yPos > pYPos){
        yPos++;
      } else if (xPos < pXPos){
        xPos--;
      } else {
        xPos++;
      }
    }
    if (xPos == pXPos && yPos == pYPos){
      input.setCaught();
    }
  }
}
