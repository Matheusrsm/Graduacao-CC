package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> queue;
	private Stack<T> auxiliar;

	public QueueUsingStack(int size) {
		queue = new StackImpl<T>(size);
		auxiliar = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		try {
			queue.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element = null;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		try {
			move(queue, auxiliar);
			element = auxiliar.pop();
			move(auxiliar, queue);
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty()) {
			move(queue, auxiliar);
			element = auxiliar.top();
			move(auxiliar, queue);
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean isFull() {
		return queue.isFull();
	}

	private void move(Stack<T> stack1, Stack<T> stack2) {
		while (!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}
}