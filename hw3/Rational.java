//created by Zixuan Wang

import java.lang.Number;
import java.lang.Object;
import java.math.BigInteger;

//a class Rational to represent an immutable rational number
public final class Rational {
	private int num; // the numerator
	private int den; // the denominator

	// a constructor with two int
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new ArithmeticException("denominator is zero!");
		}

		num = numerator;
		den = denominator;
		
		int a = gcd(num, den);
		num = num / a;
		den = den / a;

		if (den < 0) {
			den = -den;
			num = -num;
		}
	}

	// a constructor with two BigInteger
	public Rational(BigInteger numerator, BigInteger denominator) {
		num = numerator.intValue();
		den = denominator.intValue();
		if (den == 0) {
			throw new ArithmeticException("denominator is zero!");
		}
		int a = gcd(num, den);
		num = num / a;
		den = den / a;

		if (den < 0) {
			den = -den;
			num = -num;
		}
	}

    //find the greatest common divisor of two numbers
	private int gcd(int a, int b) {
		if (a < 0) {
			a = -a;
		}
		if (b < 0) {
			b = -b;
		}
		if (b == 0) {
			return a;
		}
		else {
			return gcd(b, a % b);
		}
	}

    //override doubleValue() method from java.lang.Number
	public double doubleValue() {
		return (double) num / den;
	}

	//override floatValue() method from java.lang.Number
	public float floatValue() {
		return (float) num / den;
	}

	//override intValue() method from java.lang.Number
	public int intValue() {
		return (int) num / den;
	}

	//override longValue() method from java.lang.Number
	public long longValue() {
		return (long) num / den;
	}

	//equals method to compare two rationals inherited from java.lang.Object
	public boolean equals(Rational r) {
		if (this.num == r.num && this.den == r.den) {
			return true;
		}
		else {
			return false;
		}	
	}

    //toString method for the use of output inherited from java.lang.Object
	public String toString() {
		if (den == 1) {
			return "" + num;
		}
		else {
			return num + "/" + den;
		}
	}
    
    //add method to add two rationals
	public Rational add(Rational r) {
		return new Rational(this.num*r.den+r.num*this.den, this.den*r.den);
	}
    
    //subtract method to subtract two rationals
	public Rational subtract(Rational r) {
		return new Rational(this.num*r.den-r.num*this.den, this.den*r.den);	
	}

    //multiply method to multiply two rationals
	public Rational multiply(Rational r) {
		return new Rational(this.num*r.num, this.den*r.den);	
	}

    //divide method to divide this rational by another rational
	public Rational divide(Rational r) {
		return new Rational(this.num*r.den, this.den*r.num);	
	}

    //get the numerator of this rational
	public int getNumerator() {
		return num;
	}

    //get the denominator of this rational
	public int getDenominator() {
		return den;
	}

    //a factory method to convert an int input to a rational
	public static Rational intToRational(int num) {
		return new Rational(num ,1);
	}
    
    //a factory method to convert a BigInteger input to rational
	public static Rational BigIntegerToRational(BigInteger num) {
		BigInteger a = new BigInteger("1");
		return new Rational(num, a);
	}

    //main function to test our rational class
	public static void main(String[] args) {
		Rational r1 = new Rational (3,-2);
		Rational r2 = new Rational (-2,4);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r1.equals(r2));
		System.out.println(r1.doubleValue());
		System.out.println(r1.add(r2));
		System.out.println(r1.subtract(r2));
		System.out.println(r1.multiply(r2));
		System.out.println(r1.divide(r2));
		System.out.println(intToRational(4));

		BigInteger a1, a2, a3, a4, a5;

		a1 = new BigInteger("1");
		a2 = new BigInteger("2");
		a3 = new BigInteger("-2");
		a4 = new BigInteger("-4");
		a5 = new BigInteger("4");
		Rational r3 = new Rational (a1,a2);
		Rational r4 = new Rational (a3,a4);
		System.out.println(r3);
		System.out.println(r4);
		System.out.println(r3.equals(r4));
		System.out.println(r3.longValue());
		System.out.println(r3.add(r4));
		System.out.println(r3.subtract(r4));
		System.out.println(r3.multiply(r4));
		System.out.println(r3.divide(r4));
		System.out.println(BigIntegerToRational(a5));
	}

}
