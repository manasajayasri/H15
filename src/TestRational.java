

import edu.uwm.cs.junit.LockedTestCase;
import edu.uwm.cs251.Rational;
import junit.framework.TestCase;

public class TestRational extends TestCase {
	protected void assertException(Class<? extends Throwable> c, Runnable r) {
		try {
			r.run();
			assertFalse("Exception should have been thrown",true);
		} catch (RuntimeException ex) {
			if (!c.isInstance(ex)) {
				ex.printStackTrace();
			}
			assertTrue("should throw exception of " + c + ", not of " + ex.getClass(), c.isInstance(ex));
		}
	}
	
	Rational r;
	
	
	/// testAx: gcd tests
	
	public void testA0() {
		assertEquals(0, Rational.gcd(0, 3));
		assertEquals(0, Rational.gcd(-42, 0));
	}
	
	public void testA1() {
		assertEquals(1, Rational.gcd(1, 3));
		assertEquals(1, Rational.gcd(-42, 1));
	}
	
	public void testA2() {
		assertEquals(1, Rational.gcd(2, 3));
		assertEquals(1, Rational.gcd(4, 3));
	}
	
	public void testA3() {
		assertEquals(3, Rational.gcd(3, 120));
		assertEquals(2, Rational.gcd(-42, -2));
	}
	
	public void testA4() {
		assertEquals(3, Rational.gcd(6, -21));
		assertEquals(2, Rational.gcd(14, -6));
	}
	
	public void testA5() {
		assertEquals(30, Rational.gcd(600, 630));
		assertEquals(30, Rational.gcd(1050, 720));
	}
	
	public void testA6() {
		assertEquals(256, Rational.gcd(160000, 55296));
	}
	
	public void testA7() {
		assertEquals(2, Rational.gcd(6, 1_000_000_000));
	}
	
	public void testA8() {
		assertEquals(1, Rational.gcd(Integer.MAX_VALUE, Integer.MIN_VALUE));
		assertEquals(2, Rational.gcd(Long.MIN_VALUE, 50));
	}
	
	public void testA9() {
		assertEquals(1, Rational.gcd(Long.MIN_VALUE, 20451));
		assertEquals(1, Rational.gcd(1111111, Long.MIN_VALUE));
	}
	
	/// testBx: Constructor tests
	
	public void testB0() {
		r = new Rational(0);
		assertEquals("0/1", r.toString());
	}
	
	public void testB1() {
		r = new Rational(1,-1);
		assertEquals("-1/1", r.toString());
	}
	
	public void testB2() {
		r = new Rational(42);
		assertEquals("42/1", r.toString());
	}
	
	public void testB3() {
		r = new Rational(10,-2);
		assertEquals("-5/1", r.toString());
	}
	
	public void testB4() {
		r = new Rational(1080, 1920);
		assertEquals("9/16", r.toString());
	}
	
	public void testB5() {
		r = new Rational(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertEquals("1/1", r.toString());
	}
	
	public void testB6() {
		r = new Rational(1_000_000_000, 1_000_000);
		assertEquals("1000/1", r.toString());
	}
	
	public void testB7() {
		r = new Rational(10,-9);
		assertEquals("-10/9", r.toString());
	}
	
	public void testB8() {
		r = new Rational(Integer.MIN_VALUE, Integer.MIN_VALUE);
		assertEquals("1/1", r.toString());
	}
	
	public void testB9() {
		r = new Rational(-2, Integer.MIN_VALUE);
		assertEquals("1/"+(1<<30), r.toString());
	}
	
	
	/// testCx: fromString tests
	
	public void testC0() {
		assertEquals("0/1", Rational.fromString("0/-52").toString());
	}
	
	public void testC1() {
		assertEquals("1/1", Rational.fromString("2/2").toString());		
	}
	
	public void testC2() {
		assertEquals("4/3", Rational.fromString("0000000000000000000004/000000000000000003").toString());
	}
	
	public void testC3() {
		assertEquals("-3/1", Rational.fromString("-3/1").toString());
	}
	
	public void testC4() {
		assertException(IllegalArgumentException.class, () -> Rational.fromString("12"));
	}
	
	public void testC5() {
		assertException(NumberFormatException.class, () -> Rational.fromString(" 1/2"));
	}
	
	public void testC6() {
		assertException(NumberFormatException.class, () -> Rational.fromString("/12"));
	}
	
	public void testC7() {
		assertException(ArithmeticException.class, () -> Rational.fromString("42/0"));
	}
	
	public void testC8() {
		assertEquals("1/1073741824", Rational.fromString("-2/-2147483648").toString());
	}
	
	public void testC9() {
		assertException(ArithmeticException.class, () -> Rational.fromString("7/-2147483648"));
	}
	
	
	/// testDx: asDouble tests

	public void testD0() {
		assertEquals(0.0, new Rational(0,4).asDouble());
	}
	
	public void testD1() {
		assertEquals(0.333333333333333333333333, new Rational(1,3).asDouble());
	}
	
	public void testD2() {
		assertEquals(-1.25, new Rational(-5,4).asDouble());
	}
	
	public void testD3() {
		assertEquals(0.2, new Rational(1,5).asDouble());
	}
	
	public void testD4() {
		assertEquals((double)Integer.MIN_VALUE, new Rational(Integer.MIN_VALUE).asDouble());
	}
	
	
	/// testEx: equals tests
	
	public void testE0() {
		assertTrue(new Rational(0, 3).equals(new Rational(0, -3)));
	}
	
	public void testE1() {
		assertFalse(new Rational(1,2).equals(null));
	}
	
	public void testE2() {
		assertFalse(new Rational(3,5).equals(new Object()));
	}
	
	public void testE3() {
		Rational r1 = new Rational(304,771);
		Rational r2 = new Rational(30, 17);
		assertEquals(r1.hashCode(), r2.hashCode());
		assertFalse(r1.equals(r2));
	}
	
	public void testE4() {
		Rational r1 = new Rational(1, 4369);
		Rational r2 = new Rational(1, 4096);
		assertEquals(r1.hashCode(), r2.hashCode());
		assertFalse(r1.equals(r2));
	}
	
	public void testE5() {
		Rational r1 = Rational.fromString("2417/1285");
		Rational r2 = Rational.fromString("68/1285");
		assertEquals(r1.hashCode(), r2.hashCode());
		assertFalse(r1.equals(r2));
	}
	
	public void testE6() {
		Rational r1 = Rational.fromString("-1178/561");
		Rational r2 = new Rational(2356,-1122);
		assertTrue(r1.equals(r2));
	}
	
	public void testE7() {
		Rational r1 = new Rational(-1,2);
		Rational r2 = new Rational(-1,-2);
		assertFalse(r1.equals(r2));
	}
	
	public void testE8() {
		Rational r = new Rational(2,4);
		assertTrue(r.equals(Rational.HALF));
	}
	
	public void testE9() {
		Rational r = new Rational(6,3);
		assertTrue(r.equals(Rational.TWO));
	}
	
	
	
	public void assertPositive(int val) {
		assertTrue("should be positive: " + val, val > 0);
	}
	
	public void assertNegative(int val) {
		assertTrue("shoudl be negative: " + val, val < 0);
	}
	
	
	/// testGx: compareTo tests
	
	public void testG0() {
		assertEquals(0, Rational.TWO.compareTo(new Rational(2)));
	}
	
	public void testG1() {
		assertPositive(Rational.TWO.compareTo(new Rational(19,10)));
	}
	
	public void testG2() {
		assertNegative(Rational.TWO.compareTo(new Rational(21,10)));
	}
	
	public void testG3() {
		assertPositive(new Rational(Integer.MAX_VALUE).compareTo(new Rational(Integer.MIN_VALUE)));
	}
	
	public void testG4() {
		assertNegative(new Rational(Integer.MIN_VALUE+7).compareTo(new Rational(Integer.MAX_VALUE-115)));
	}
	
	public void testG5() {
		assertPositive(new Rational(5,Integer.MAX_VALUE).compareTo(new Rational(3,Integer.MAX_VALUE)));
	}
	
	public void testG6() {
		Rational r1 = new Rational(665857,470832);
		Rational r2 = new Rational(941664,665857);
		assertPositive(r1.compareTo(r2));
	}
	
	public void testG7() {
		assertPositive(Rational.TWO.compareTo(new Rational(-111,2)));
	}
	
	public void testG8() {
		assertEquals(0, new Rational(-Integer.MAX_VALUE).compareTo(new Rational(Integer.MAX_VALUE, -1)));
	}
	
	public void testG9() {
		assertNegative(new Rational(Integer.MIN_VALUE).compareTo(new Rational(1,Integer.MAX_VALUE)));
	}
	
	
	/// testHx: add tests
	
	public void testH0() {
		Rational r = new Rational(665857, 470832);
		assertEquals(r, r.add(Rational.ZERO));
	}
	
	public void testH1() {
		Rational r1 = new Rational(69, 256);
		Rational r2 = new Rational(59, 256);
		assertEquals("1/2", r1.add(r2).toString());
	}
	
	public void testH2() {
		assertEquals("1/6", Rational.HALF.add(new Rational(1,-3)).toString());
	}
	
	public void testH3() {
		assertEquals("-1/10", Rational.HALF.add(new Rational(-3,5)).toString());
	}
	
	public void testH4() {
		Rational r1 = new Rational(337,1073741827);
		Rational r2 = new Rational(3,5);
		assertException(ArithmeticException.class, () -> r1.add(r2));
	}
	
	public void testH5() {
		Rational r1 = new Rational(Integer.MAX_VALUE-835);
		Rational r2 = new Rational(Integer.MAX_VALUE-102675);
		assertException(ArithmeticException.class, () -> r1.add(r2));
	}
	
	public void testH6() {
		Rational r1 = new Rational(3, 2147483578);
		Rational r2 = new Rational(7, 2147483578);
		assertEquals("5/1073741789", r1.add(r2).toString());
	}
	
	public void testH7() {
		Rational r1 = new Rational(Integer.MAX_VALUE-17);
		Rational r2 = new Rational(Integer.MIN_VALUE+32);
		assertEquals(new Rational(14), r1.add(r2));
	}
	
	public void testH8() {
		Rational r1 = new Rational(665857,470832);
		Rational r2 = new Rational(941664,665857);
		assertException(ArithmeticException.class, () -> r1.add(r2));
	}
	
	public void testH9() {
		Rational r1 = new Rational(665857,470832);
		Rational r2 = new Rational(941665,665856);
		assertEquals("1086680017/384198912", r1.add(r2).toString());
	}
	
	
	/// testIx: neg tests
	
	public void testI0() {
		assertEquals("0/1", Rational.ZERO.neg().toString());
	}
	
	public void testI1() {
		assertEquals("-1/1", Rational.ONE.neg().toString());
	}
	
	public void testI2() {
		assertEquals("-1/2", Rational.HALF.neg().toString());
	}
	
	public void testI3() {
		assertEquals("-1/33", new Rational(1,33).neg().toString());
	}
	
	public void testI4() {
		assertEquals("377/8000", new Rational(-377,8000).neg().toString());
	}
	
	public void testI5() {
		assertEquals("-1/1000000", new Rational(1, 1000*1000).neg().toString());
	}
	
	public void testI6() {
		assertEquals("-665857/470832", new Rational(665857,470832).neg().toString());
	}
	
	public void testI7() {
		assertEquals("-3/2147483647", new Rational(3,Integer.MAX_VALUE).neg().toString());
	}
	
	public void testI8() {
		Rational r = new Rational(Integer.MAX_VALUE);
		assertEquals(new Rational(Integer.MIN_VALUE+1), r.neg());
	}
	
	public void testI9() {
		Rational r = new Rational(Integer.MIN_VALUE);
		assertException(ArithmeticException.class, () -> r.neg());
	}
	
	
	/// testMx: mul tests
	
	public void testM0() {
		Rational r1 = new Rational(343,256);
		assertEquals("0/1", r1.mul(Rational.ZERO).toString());
	}
	
	public void testM1() {
		Rational r = new Rational(343,256);
		assertEquals("343/256", r.mul(Rational.ONE).toString());
	}
	
	public void testM2() {
		Rational r = new Rational(343,256);
		assertEquals("343/128", r.mul(Rational.TWO).toString());
	}
	
	public void testM4() {
		Rational r1 = new Rational(3,17);
		Rational r2 = new Rational(-5,128);
		assertEquals("-15/2176", r1.mul(r2).toString());
	}
	
	public void testM5() {
		Rational r1 = new Rational(-343,256);
		Rational r2 = new Rational(216,-35);
		assertEquals("1323/160", r1.mul(r2).toString());
	}
	
	public void testM6() {
		Rational r = new Rational(2,Integer.MIN_VALUE);
		assertException(ArithmeticException.class, () -> r.mul(Rational.HALF));
	}
	
	public void testM7() {
		Rational r1 = new Rational(665855,470832);
		Rational r2 = new Rational(941663,665857);
		assertException(ArithmeticException.class, () -> r1.mul(r2));
	}
	
	public void testM8() {
		Rational r1 = new Rational(245,1024);
		Rational r2 = new Rational(286,9765625);
		assertEquals("7007/1000000000", r1.mul(r2).toString());
	}
	
	public void testM9() {
		Rational r = new Rational(65535, 65536);
		assertException(ArithmeticException.class, () -> r.mul(r));
	}
	
	
	/// testQx: inv tests
	
	public void testQ0() {
		assertException(ArithmeticException.class, () -> new Rational(0,16).inv());
		assertException(ArithmeticException.class, () -> Rational.ZERO.inv());
	}
	
	public void testQ1() {
		assertEquals("1/1", Rational.ONE.inv().toString());
		assertEquals(Rational.ONE, new Rational(-33,-33).inv());
	}
	
	public void testQ2() {
		assertEquals(Rational.TWO, Rational.HALF.inv());
		assertEquals(Rational.HALF, Rational.TWO.inv());
	}
	
	public void testQ3() {
		assertEquals("3/1", new Rational(1,3).inv().toString());
	}
	
	public void testQ4() {
		assertEquals("-45/64", new Rational(-64,45).inv().toString());
	}
	
	public void testQ5() {
		assertEquals("2147483647/25", new Rational(25,Integer.MAX_VALUE).inv().toString());
	}
	
	public void testQ6() {
		Rational r = new Rational(665857,470832);
		assertEquals("470832/665857", r.inv().toString());
	}
	
	public void testQ7() {
		Rational r = new Rational(1, Integer.MAX_VALUE);
		assertEquals(new Rational(Integer.MAX_VALUE), r.inv());
	}
	
	public void testQ8() {
		assertEquals("-1073741824/2147483647", new Rational(Integer.MAX_VALUE, Integer.MIN_VALUE/2).inv().toString());
	}
	
	public void testQ9() {
		Rational r = new Rational(Integer.MIN_VALUE, 233);
		assertException(ArithmeticException.class, () -> r.inv());
	}
}
