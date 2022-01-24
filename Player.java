import java.io.*;
import java.util.*;

public class Player{
  private boolean escape = false;
  private boolean caught = false;
  private int xPos;
  private int yPos;
  private int xTemp;
  private int yTemp;
  public Player() {
    escape = false;
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

  public void setXPos(int input) {
    xPos += input;
  }

  public void setYPos(int input) {
    yPos += input;
  }

  public void setXTemp(int input) {
    xTemp = input;
  }

  public void setYTemp(int input) {
    yTemp = input; 
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
