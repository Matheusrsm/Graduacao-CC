package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		stack1 = new StackImpl<Integer>(4);
		stack2 = new StackImpl<Integer>(2);
		stack3 = new StackImpl<Integer>(10);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(new Integer(3), stack1.top());
		assertEquals(new Integer(2), stack2.top());
		assertEquals(null, stack3.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(stack1.isFull());
		assertTrue(stack2.isFull());
	}

	@Test
	public void testPush() {
		try {
			stack1.push(new Integer(5));
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(new Integer(5));
		stack1.push(new Integer(6));
	}

	@Test
	public void testPop() {
		try {
			assertEquals(new Integer(3), stack1.pop());
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals(new Integer(2), stack2.pop());
		assertEquals(new Integer(1), stack2.pop());
		stack2.pop();
		
	}
}