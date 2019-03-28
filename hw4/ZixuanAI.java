package hw4;
import java.util.Random;

public class ZixuanAI implements CFPlayer {
	public int nextMove(CFGame g) {
		int[][] furuya = g.getState();
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {

				//check horizontal case
				if (i < 4) {
					if(Math.abs(furuya[i][j]+furuya[i+1][j]+furuya[i+2][j]+furuya[i+3][j]) > 2) {
						for (int k = i; k < i + 4; k++) {
							if (furuya[k][j] == 0 && j == 0){
								return k + 1;
							}
							if (j != 0 && furuya[k][j-1] != 0) {
								return k + 1;
							}
						}
					}

					/*else if((furuya[i][j]+furuya[i+1][j]+furuya[i+2][j]+furuya[i+3][j]) < -2) {
						for (int k = i; k < i + 4; k++) {
							if (furuya[k][j] == 0 && j == 0){
								return k + 1;
							}
							if (j != 0 && furuya[k][j-1] != 0) {
								return k + 1;
							}
						}
					}*/
				}


				//check vertical case
				if (furuya[i][j] != 0 && j < 3) {
					if (furuya[i][j] == furuya[i][j+1] && furuya[i][j] == furuya[i][j+2] && furuya[i][j+3] ==0) {
						return i+1;
					}
				}

				//check upward diagonal case
				if (i<4 && j<3) {
					if(Math.abs(furuya[i][j]+furuya[i+1][j+1]+furuya[i+2][j+2]+furuya[i+3][j+3]) > 2) {
						for (int k = i; k < i + 4; k++) {
							int m = j + k - i;
							if (furuya[k][m] == 0){
								return k + 1;
							}
						}
					}

					/*else if((furuya[i][j]+furuya[i+1][j+1]+furuya[i+2][j+2]+furuya[i+3][j+3]) < -2) {
						for (int k = i; k < i + 4; k++) {
							int m = j + k - i;
							if (furuya[k][m] == 0){
								return k + 1;
							}
						}
					}*/
				}

				//check downward diagonal case
				if (i > 2 && j < 3) {
					if(Math.abs(furuya[i][j]+furuya[i-1][j+1]+furuya[i-2][j+2]+furuya[i-3][j+3]) > 2) {
						for (int k = i; k > i - 3; k--) {
							int m = j + i - k;
							if (furuya[k][m] == 0){
								return k + 1;
							}
						}
					}

					/*else if((furuya[i][j]+furuya[i-1][j+1]+furuya[i-2][j+2]+furuya[i-3][j+3]) < -2) {
						for (int k = i; k > i - 3; k--) {
							int m = j + i - k;
							if (furuya[k][m] == 0){
								return k + 1;
							}
						}
					}*/
				}

			}
		}

		if (furuya[2][0] == 0)
			return 3;
		if (furuya[3][0] == 0)
			return 4;
		if (furuya[4][0] == 0)
			return 5;

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



	public String getName() {
		return "Zixuan Wang's AI";
	}
}