package die;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class DieTest {

	Die die;
	
	@Test
	public void testDieNumSidesEquals1() {
		try {
			this.die = new Die(1);
			fail("Não deve ser possivel cadastrar com numero de lados igual a 1");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition: numSides = 1numSides must be greater than 1", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesEqualsZero() {
		try {
			this.die = new Die(0);
			fail("Não deve ser possivel cadastrar com numero de lados igual a 0");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition: numSides = 0numSides must be greater than 1", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesNegative() {
		try {
			this.die = new Die(-1);
			fail("Não deve ser possivel cadastrar com numero de lados negativos");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition: numSides = -1numSides must be greater than 1", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesEquals1AndResultValid() {
		try {
			this.die = new Die(1, 1);
			fail("Não deve ser possivel cadastrar com numero de lados igual a 1");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesEquals0AndResultValid() {
		try {
			this.die = new Die(0, 1);
			fail("Não deve ser possivel cadastrar com numero de lados igual a 0");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesNegativeAndResultValid() {
		try {
			this.die = new Die(-1, 1);
			fail("Não deve ser possivel cadastrar com numero de lados negativos");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesValidAndResultEqualsZero() {
		try {
			this.die = new Die(6, 0);
			fail("Não deve ser possivel cadastrar o resultado como 0");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesValidAndResultNegative() {
		try {
			this.die = new Die(6, -1);
			fail("Não deve ser possivel cadastrar um resultado negativo");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieResultGreaterNumSides() {
		try {
			this.die = new Die(6, 7);
			fail("Não deve ser possivel cadastrar o resultado maior que o numero de lados");
		} catch(AssertionError ae) {
			assertEquals("Violation of precondition", ae.getMessage());
		}
	}
	
	@Test
	public void testDieNumSidesAndResultValids() {
		this.die = new Die(6,4);
		assertEquals(6, die.getNumSides());
		assertEquals(4, die.getResult());
	}
	
	@Test
	public void testRollDifferentZero() {
		this.die = new Die(6);
		assertNotEquals(0, die.roll());
		assertNotEquals(0, die.getResult());
	}
	
	@Test
	public void testRollLessThatNumSides() {
		this.die = new Die(6);
		boolean test = die.roll() <= die.getNumSides();
		assertTrue(test);
	}
	
	@Test
	public void testRollBiggerThenZero() {
		this.die = new Die(6);
		boolean test = die.roll() > 0;
		assertTrue(test);
	}
	
	@Test
	public void testDieEqualsTrue() {
		Die die01 = new Die(6,2);
		Die die02 = new Die(6,2);
		assertEquals(true, die01.equals(die02));
	}
	
	@Test
	public void testDieEqualsFalse() {
		Die die01 = new Die(6,2);
		Die die02 = new Die(6,3);
		assertEquals(false, die01.equals(die02));
	}
	
	@Test
	public void testDieNumSidesEqualsDefault() {
		this.die = new Die();
		assertEquals(6, die.getNumSides());
	}
	
	@Test
	public void testToStringDieNumSidesValid() {
		this.die = new Die();
		assertEquals("Num sides 6 result 1", die.toString());
		this.die = new Die(3, 2);
		assertEquals("Num sides 3 result 2", die.toString());
		this.die = new Die(4);
		assertEquals("Num sides 4 result 1", die.toString());
	}
	
	@Test
	public void testEqualsOtherDieNull() {
		this.die = new Die();
		assertFalse(die.equals(null));
	}
	
	@Test
	public void testEqualsSameDie() {
		this.die = new Die();
		assertTrue(die.equals(die));
	}
	
	@Test
	public void testEqualsDifferentObjects() {
		this.die = new Die();
		int otherObject = 1;
		assertFalse(die.equals(otherObject));
	}
}