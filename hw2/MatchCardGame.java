//created by Zixuan Wang

public class MatchCardGame {
	public final int n; //total number of cards
	private int total_flips = 0; //number of total flips
	private Card firstFlip = null;
	private Card secondFlip = null;
	private final Card[] ourCards; //our array to manage all the cards

	//constructor
	public MatchCardGame(int n) {
		this.n = n;
		ourCards = new Card[n];

		int counter = 0;
		char ctype = 'a';
		for (int i = 0; i < n; i++) {
			ourCards[i] = new Card(ctype);
			counter += 1;
			if (counter == 4) {
				counter = 0;
				ctype += 1;
			}
		}
	}

    //display the current state of the board
	public String boardToString() {
		int totalFlips = getFlips();
		System.out.println("total number of card flips performed: " + totalFlips);
		System.out.println();
		int counter = 0;
		for (int i = 0; i < n; i++) {
			Card card = ourCards[i];
			if (card.isFaceUp) {
				System.out.print("\t" + i + ": " +   card.type);
			}
			else {
				System.out.print("\t" + i + ": ?"); //we use ? to note unknown cards
			}

			counter += 1;

			//4 cards a line
			if (counter == 4) {
				counter = 0;
				System.out.println();
			}
		}
		return "";
	}

    //method to flip the card i if it is valid
	public boolean flip(int i) {
		if (i < 0 || i >= n) 
			return false;

		if (ourCards[i].isFaceUp) {
			return false;
		}
		else {
			ourCards[i].isFaceUp = true;
			if (firstFlip == null) {
				firstFlip = ourCards[i];
			}
			else {
				secondFlip = ourCards[i];
			}
			total_flips += 1;
			return true;
		}
	}
    
    //method to determine whether the previous pair is a match
	public boolean wasMatch() {
		if (firstFlip == null || secondFlip == null)
			return false;
		if (firstFlip.type == secondFlip.type) {
			firstFlip = null;
			secondFlip = null;
			return true;
		}
		return false;
	}

    //method to return the face of the previously flipped card
	public char previousFlipIdentity() {
		if (secondFlip != null)
			return secondFlip.type;
		else
			return firstFlip.type;
	}
    
    //method to revert a mismatched pair to face-down position
	public void flipMismatch() {
		if (firstFlip == null || secondFlip == null)
			return;
		firstFlip.isFaceUp = false;
		secondFlip.isFaceUp = false;
		firstFlip = null;
		secondFlip = null;
	}

    //method to determine whether the game is over
	public boolean gameOver() {
		for (Card card : ourCards) {
			if (card.isFaceUp == false)
				return false;
		}
		return true;
	}

    //method to get total number of flips
	public int getFlips() {
		return total_flips;
	}
    
    //method to shuffle the cards using the Fisher-Yates shuffle
	public void shuffleCards() {
		Random rand = new Random();
		for (int i = 0; i < n; i++)
		{
			int randomIndex = i + rand.nextInt(n-i);
			Card temp = ourCards[randomIndex];
			ourCards[randomIndex] = ourCards[i];
			ourCards[i] = temp;
		}
	}


}

//our class for a single card
class Card{
	char type;
	boolean isFaceUp;
	Card(char type) {
		this.type = type;
		isFaceUp = false;
	}
}