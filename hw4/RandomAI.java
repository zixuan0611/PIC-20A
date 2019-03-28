package hw4;

import java.util.Random;

public class RandomAI implements CFPlayer{
	public int nextMove(CFGame g) {
		int[][] furuya = g.getState();
		int[] possible = new int[7];
		int c = 0;
		for (int i = 0; i < 7; i++) {
			if (furuya[i][5] == 0) {
				possible[c] = i;
				c++;
			}
		}

		long seed = System.currentTimeMillis();
		Random randInt = new Random(seed);
		int a = randInt.nextInt(c);
		int b = possible[a];

		return b+1;
	}

	public String getName(){
		return "Random Player";
	}
}