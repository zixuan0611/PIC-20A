// created by Zixuan Wang
// public class MyMath: 2 member functions -- sqrt and main
public class MyMath {
	// a function to compute the square root by bisection
	public static double sqrt(double d) {
		int exp = 0; //set the exponent to be zero

		//normalizing the input to [0,1]
		if (d > 1) {
			//divide d by 4 until d is within [0,1]
			while (d > 1) {
				d = d / 4;
				exp = exp + 1; //add one to the exponent
			}
		}

		//if d is 0 or 1, no need to take the midpoint
		if (d == 0 || d == 1) {
			return d * Math.pow(2, exp); //make sure to multiply back from [0,1]
		}

		double epsil = Math.pow(10, -10); //set the error tolerance

		// the beginning interval is [0,1]
		double l = 0; 
		double u = 1;

		//initialize the final result
		double ans = 0;

		//use binary search until we reach the error tolerance
		while ( (u - l) >= epsil) {
			double m = (l + u) / 2; //take the midpoint m

			//deside whether the square root is in [l,m] or [m,u]
			if (d <= (m * m)) {
				u = m;
			}
			else {
				l = m;
			}

			//set the answer to be the value of midpoint
			ans = m;
		}

		//return the final result by multipying back from [0,1]
		return ans * Math.pow(2, exp);
	}

	//a function to compare the spped of MyMath.sqrt with Math.sqrt
	public static void main (String [] args) {

		//compute the total time for evaluation using Math.sqrt
		long t1 = 0;
		for (int i= 0; i < 10000000; i++) {
			long s1 = System.currentTimeMillis();
			double eval = 100 * Math.random();
			Math.sqrt(eval);
			long e1 = System.currentTimeMillis();
			t1 += e1 - s1; //add up the time the loop executes
		}

		//compute the total time for evaluation using MyMath.sqrt
		long t2 = 0;
		for (int i= 0; i < 10000000; i++) {
			long s2 = System.currentTimeMillis();
			double eval = 100 * Math.random();
			MyMath.sqrt(eval);
			long e2 = System.currentTimeMillis();
			t2 += e2 - s2; //add up the time the loop executes
		}

		//compute the total time for genertaing random numbers
		long t3 = 0;
		for (int i= 0; i < 10000000; i++) {
			long s3 = System.currentTimeMillis();
			double eval = 100 * Math.random();
			long e3 = System.currentTimeMillis();
			t3 += e3 - s3;  //add up the time the loop executes
		}

		//compute the average execution time for all three loops
		double t_random = ((double)(t3)) / 10000000;
		double t_1 = ((double)(t1))/10000000;
		double t_2 = ((double)(t2))/10000000 ;

		//compute the actual average execuation time of the two square root functions
		t_1 = t_1 - t_random;
		t_2 = t_2 - t_random;

		//output the result
		System.out.println("the average execution time per evaluation of Math.sqrt: " + t_1);
		System.out.println("the average execution time per evaluation of MyMath.sqrt: " + t_2);

		//double m = sqrt(4);
		//System.out.println("ans: " + m);

	}
}