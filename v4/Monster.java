public class Monster {
  private int xPos, yPos, xTemp, yTemp;
  public Monster() {
    xPos = 12;
    yPos = 12;
    xTemp = 12;
    yTemp = 12;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

  public int getXTemp() {
    return xTemp;
  }

  public int getYTemp() {
    return yTemp;
  }

  public void chase(Player input) {
    int pXPos = input.getXPos();
    int pYPos = input.getYPos();
    xTemp = xPos;
    yTemp = yPos;
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
    if ((xPos == pXPos && yPos == pYPos) || ((xPos == input.getXTemp() && yPos == input.getYTemp() ) && (xTemp == input.getXPos() && yTemp == input.getYPos()))) {
      input.setCaught();
    }
  }
}
