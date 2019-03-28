package hw4;

import java.util.Scanner;

public class ConsoleCF extends CFGame {

	public final CFPlayer red, black;

	public ConsoleCF(CFPlayer ai) {
		CFPlayer human = new HumanPlayer();

		if (Math.round(Math.random()) == 0) {
			this.red = ai;
			this.black = human;
		}
		else {
			this.red = human;
			this.black = ai;
		}
	}

	public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
		if (Math.round(Math.random()) == 0) {
			this.red = ai1;
			this.black = ai2;
		}
		else {
			this.red = ai2;
			this.black = ai1;
		}
	}

	public void playOut() {
		while (!this.isGameOver()){
			this.play(red.nextMove(this));
			if (this.isGameOver()) {
				return;
			}
			else {
				this.play(black.nextMove(this));
			}
		}
	}

	public String getWinner() {
		if (this.winner() == 1) {
			return red.getName();
		}
		else if (this.winner() == -1) {
			return black.getName();
		}
		else {
			return "draw";
		}
	}

	private class HumanPlayer implements CFPlayer {
		public int nextMove(CFGame g) {
			System.out.println();
			int[][] furuya = g.getState();
			for (int j = 5; j >= 0; j--) {
				for (int i = 0; i < 7; i++) {
					if (furuya[i][j] == 1) {
						System.out.print(" r");
					}
					else if (furuya[i][j] == -1) {
						System.out.print(" b");
					}
					else {
						System.out.print(" -");
					}
				}
				System.out.println();
			}
			System.out.println(" 1 2 3 4 5 6 7");
			System.out.println();

			System.out.print("Which column do you want to play: ");
			Scanner reader = new Scanner(System.in);
			int p = reader.nextInt();
			while (!((furuya[p-1][5] == 0) && (p >=1) && (p <=7))) {
				System.out.println("invalid column!");
				System.out.print("Which column do you want to play: ");
				p = reader.nextInt();
			}
			return p;
		}

		public String getName() {
			return "Human Player";
		}
	}
}