package edu.uwm.cs251;

/**
 * An immutable class representing rational numbers.
 * It can only represent rationals in which both the 
 * numerator and denominator are 32 bit integers, 
 * and the denominator positive.
 */
public final class Rational implements Comparable<Rational> {

	private final int den, num;
	// Data Structure invariant:
	// den must be positive and if num is zero, must be 1.
	// If num is not zero than the gcd of the two
	// must be one.
	
	/**
	 * Create the rational number n/1.
	 * @param n integer value
	 */
	public Rational(int n) {
		num = n;
		den = 1;
	}
	
	/**
	 * Return the (positive) greatest common divisor
	 * of two non-negative integers, or return zero if
	 * one of the two numbers is zero.
	 * In other words, return the largest positive number n
	 * such that n divides both a and b.  
	 * For example the GCD of -12 and 18 is 6.
	 * As a special exception trhe gcd of Long.MIN_VALUE with itself is itself.
	 * @param a first number
	 * @param b second number
	 * @return GCD of the two arguments
	 */
	public static long gcd(long a, long b) {
		return 0; // TODO
	}
	
	/**
	 * Create the representation of m/n as a rational number.
	 * @param m numerator
	 * @param n denominator, must not be zero
	 * @exception ArithmeticException if m/n cannot be represented as a ration of two ints,
	 * where the denominator is positive.  For example, if n is zero, or if m is 1, and
	 * n is Integer.MIN_VALUE.
	 * 
	 */
	public Rational(int m, int n) {
		this((long)m, (long)n); // let the private constructor handle all the work.
	}
	
	// Internal constructor so that intermediate computatiosn can get bigger than
	// the size of an int.
	private Rational (long m, long n) {
		// TODO: Error checks and canonicalization (n cannot be negative and m,n must be in lowest terms)
		// Leave these last lines in place:
		num = Math.toIntExact(m); // will throw ArithmeticException if out of range
		den = Math.toIntExact(n);
	}
	
	public static final Rational ZERO = new Rational(0,1);
	public static final Rational ONE = null; // TODO
	public static final Rational TWO = null; // TODO
	public static final Rational HALF = null; // TODO;	
	
	@Override
	public String toString() {
		return num + "/" + den;
	}
	
	/**
	 * Return the rational with the given output.
	 * This method should accept strings of the form m/n
	 * so that <code>r.equals(fromString(r.toString())</code> returns true
	 * @param s string, must not be null
	 * @exception IllegalArgumentException if there is no '/' in the string, or the denominator is zero
	 * @exception NumberFormatException if either number is badly formatted
	 * @return rational described by the string
	 * @exception ArithmeticException if the rational number cannot be represented.
	 */
	public static Rational fromString(String s) {
		return null;
	}
	
	/**
	 * Convert this rational to its closest double approximation.
	 * @return the closest double approximation
	 */
	public double asDouble() {
		return 0.0; // TODO
	}

	@Override
	public boolean equals(Object x) {
		// TODO
		return false;
	}
	
	@Override
	public int hashCode() {
		return Double.hashCode(asDouble());
	}

	@Override
	public int compareTo(Rational r) {
		return 0; // TODO
	}

	/**
	 * Return the sum of this rational and the argument.
	 * @param r rational to add, must not be null
	 * @return rational that is the number of this and r.
	 * @exception ArithmeticException if the resulting rational cannot be
	 * represented as m/n where both m and n are ints and n is positive.
	 */
	public Rational add(Rational r) {
		return null; // TODO
	}
	
	/** TODO: Write this documentation comment */
	public Rational neg() {
		return null; // TODO
	}
	
	/** TODO: Write this documentation comment */
	public Rational mul(Rational r) {
		return null; // TODO
	}
	
	/** TODO: Write this documentation comment */
	public Rational inv() {
		return null; // TODO
	}
}
