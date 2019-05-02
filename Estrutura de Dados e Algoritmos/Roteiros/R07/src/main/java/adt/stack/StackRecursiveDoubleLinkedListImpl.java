package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else if (!(element == null)) {
			top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		T element = top();
		top.removeLast();
		return element;
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}
		RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) top;
		if (aux.getNext().isEmpty()) {
			return aux.getData();
		} else {
			return top(aux);
		}
	}

	private T top(RecursiveDoubleLinkedListImpl<T> aux) {
		if (aux.getNext().isEmpty()) {
			return aux.getData();
		} else {
			return top((RecursiveDoubleLinkedListImpl<T>) aux.getNext());
		}
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return size == top.size();
	}
}