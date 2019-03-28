package hw4;

public class CFGame {
  //state[i][j]= 0 means the i,j slot is empty
  //state[i][j]= 1 means the i,j slot has red
  //state[i][j]=-1 means the i,j slot has black
  private final int[][] state;
  private boolean isRedTurn;
  private int win = 0;
  private int count = 0;
  
  {
    state = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        state[i][j] = 0;
    isRedTurn = true; //red goes first
  }
    
  public int[][] getState() {
    int[][] ret_arr = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        ret_arr[i][j] = state[i][j];
    return ret_arr;
  }
  
  public boolean isRedTurn() {
    return isRedTurn;
  }
  
  public boolean play(int c) {
    if (c > 7 || c < 1) {
      return false;
    }

    c = c-1;

    for (int i = 0; i < 6; i++) {
      if (state[c][i] == 0) {
        if (isRedTurn == true) {
          state[c][i] = 1;
          count++;
          isRedTurn = false;
          return true;
        }
        else {
          state[c][i] = -1;
          count++;
          isRedTurn = true;
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isGameOver() {

    //check the horizontal four
    for (int j = 0; j < 6; j++) {
      for (int i = 0; i < 4; i++) {
        if (state[i][j]==state[i+1][j] && state[i][j]==state[i+2][j] && state[i][j]==state[i+3][j]) {
          if (state[i][j] == 1) {
            win = 1;
            return true;
          }
          else if (state[i][j] == -1) {
            win = -1;
            return true;
          }
        }
      }
    }

    //check the vertical four
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 3; j++) {
        if (state[i][j]==state[i][j+1] && state[i][j]==state[i][j+2] && state[i][j]==state[i][j+3]) {
          if (state[i][j] == 1) {
            win = 1;
            return true;
          }
          else if (state[i][j] == -1) {
            win = -1;
            return true;
          }
        }
      }
    }

    //check the upward diagonal four
    for (int i = 3; i < 7; i++) {
      for (int j = 0; j < 3; j++) {
        if (state[i][j]==state[i-1][j+1] && state[i][j]==state[i-2][j+2] && state[i][j]==state[i-3][j+3]) {
          if (state[i][j] == 1) {
            win = 1;
            return true;
          }
          else if (state[i][j] == -1) {
            win = -1;
            return true;
          }
        }
      }
    }

    //check the downward diagonal four
    for (int i = 3; i < 7; i++) {
      for (int j = 3; j < 6; j++) {
        if (state[i][j]==state[i-1][j-1] && state[i][j]==state[i-2][j-2] && state[i][j]==state[i-3][j-3]) {
          if (state[i][j] == 1) {
            win = 1;
            return true;
          }
          else if (state[i][j] == -1) {
            win = -1;
            return true;
          }
        }
      }
    }

    if (count == 42) {
      win = 0;
      return true;
    }

    return false;
  }
  
  public int winner() {
    return win;
  }
}
