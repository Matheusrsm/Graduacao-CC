package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (!(element == null)) {
			if (!isEmpty()) {
				next = new RecursiveDoubleLinkedListImpl<T>(data, next, this);
				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious((RecursiveDoubleLinkedListImpl<T>) next);
				setData(element);
				setNext(next);
			} else {
				next = new RecursiveDoubleLinkedListImpl<T>();
				setData(element);
				setNext(next);
				setPrevious((RecursiveDoubleLinkedListImpl<T>) next);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			setData(next.data);
			setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			setNext(next.next);
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (next.data == null) {
				setData(null);
			} else {
				RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>) next;
				aux.removeLast();
			}
		}
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			if (isEmpty()) {
				insertFirst(element);
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && !(element == null)) {
			if (data.equals(element)) {
				removeFirst();
			} else {
				next.remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}