import java.util.Random;

public class PlayCard {
  public static void main(String[] args) {
    /*//set up reader to take inputs
    java.util.Scanner reader = new java.util.Scanner (System.in);
    
    int n = 16; //game size
    MatchCardGame g1 = new MatchCardGame(n);
    //g1.shuffleCards();
    
    while(!g1.gameOver()) {
      //print board status
      System.out.println(g1.boardToString());
      
      //ask for a card to flip until we get a valid one
      System.out.println("Which card to play?");
      while(!g1.flip(reader.nextInt())) {}
      
      //print board status
      System.out.println(g1.boardToString());
      
      //ask for a card to flip until we get a valid one
      while(!g1.flip(reader.nextInt())) {}
      
      //say whether the 2 cards were a match
      if(g1.wasMatch()) {
        System.out.println("Was a match!");
      } else {
        //print board to show mismatched cards
        System.out.println(g1.boardToString());		
        System.out.println("Was not a match.");
        //flip back the mismatched cards
        g1.flipMismatch();
      }
    }
    
    //Report the score
    System.out.println("The game took " + g1.getFlips() + " flips.");*/
    
    //Using the AIs
    int n =  16;
    int count;
    MatchCardGame g2 = new MatchCardGame(n);
    g2.shuffleCards();
    count = playRandom(g2);
    System.out.println("The bad AI took " + count + " flips.");
    MatchCardGame g3 = new MatchCardGame(n);
    g3.shuffleCards();
    count = playGood(g3);
    System.out.println("The good AI took " + count + " flips.");
    
    //Using MCs
    int N = 1000;
    System.out.println("The bad AI took " + randomMC(N) + " flips on average.");
    System.out.println("The good AI took " + goodMC(N) + " flips on average.");
  }

  //method to play randomly two cards a time
  public static int playRandom(MatchCardGame g) {
    while(!g.gameOver()) {
      //first card
      Random rand1 = new Random();
      boolean check1 = false;
      while(!check1) {
        check1 = g.flip(rand1.nextInt(g.n));
      }

      //second card
      Random rand2 = new Random();
      boolean check2 = false;
      while(!check2) {
        check2 = g.flip(rand2.nextInt(g.n));
      }
      if (!g.wasMatch()) {
        g.flipMismatch();
      }
    }
    return g.getFlips();
  }
  
  //method to play perfectly
  public static int playGood(MatchCardGame g) {
    char[] flipped = new char[g.n];
    for (int i = 0; i < g.n; i++) {
      flipped[i] = '*';
    }
    while(!g.gameOver()) {
      //System.out.println(g.boardToString());
      //System.out.println("0");
      boolean pair = false;

      //if there is a pair, flip them
      do {
        pair = false;
        for (int i = 0; i < g.n; i++) {
          for (int j = 0; j < g.n; j++) {
            if (flipped[i] == flipped[j] && flipped[i]!= '*' && i != j) {
              if (g.flip(i) && g.flip(j)){
                flipped[i] = '*';
                flipped[j] = '*';
                pair = true;
                //System.out.println(g.boardToString());
              }             
            }
          }
        }
        //System.out.println(g.boardToString());
      } while(pair);

      if (g.gameOver())
        return g.getFlips();

      Random rand1 = new Random();
      boolean check1 = false;
      int card1 = 0;
      while(!check1) {
        card1 = rand1.nextInt(g.n);
        check1 = g.flip(card1);
      }
      //System.out.println(g.boardToString());
      if (g.gameOver())
          return g.getFlips();
      //System.out.println(g.boardToString());

      char temp1 = g.previousFlipIdentity();
      boolean help = false;
      //if there is a single card matched
      for (int i = 0; i < g.n; i++) {

        if (flipped[i] == temp1) {
          if (g.flip(i)){
            flipped[i] = '*';
            help = true;
            //System.out.println(g.boardToString());
            break;
          }
          
        }
      }

      if (help == true) {
        if (g.gameOver())
          return g.getFlips();
      }
      else {
        if (g.gameOver())
          return g.getFlips();
        Random rand2 = new Random();
        boolean check2 = false;
        int card2 = 0;
        while(!check2) {
          card2 = rand2.nextInt(g.n);
          check2 = g.flip(card2);
        }
        //System.out.println(g.boardToString());

        char temp2 = g.previousFlipIdentity();
        if (!g.wasMatch()) {
          g.flipMismatch();
          flipped[card2] = temp2;
          flipped[card1] = temp1;
          //System.out.println(g.boardToString());

        }
        if (g.gameOver())
          return g.getFlips();
        
      }
    }
    return g.getFlips();
  }

  //evaluate playrandom
  public static double randomMC(int N) {
    MatchCardGame g2 = new MatchCardGame(32);
    double total = 0;
    for (int i = 0; i < N; i++) {
      g2 = new MatchCardGame(32);
      g2.shuffleCards();
      total += playRandom(g2);
    }
    return total / N;
  }

    //evaluate playgood
  public static double goodMC(int N) {
    MatchCardGame g2 = new MatchCardGame(32);
    double total = 0;
    for (int i = 0; i < N; i++) {
      g2 = new MatchCardGame(32);
      g2.shuffleCards();
      total += playGood(g2);
    }
    return total / N;
  }
}