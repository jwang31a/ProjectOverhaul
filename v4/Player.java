import java.io.*;
import java.util.*;

public class Player{
  private boolean escape = false;
  private boolean caught = false;
  private int xPos;
  private int yPos;
  public Player() {
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

  public void setXPos(int input) {
    xPos += input;
  }

  public void setYPos(int input) {
    yPos += input;
  }

  public boolean getWin() {
    return escape;
  }

  public boolean getCaught() {
    return caught;
  }

  public void setCaught(){
    caught = true;
  }

  public void setEscape(boolean input){
    escape = input;
  }
}
