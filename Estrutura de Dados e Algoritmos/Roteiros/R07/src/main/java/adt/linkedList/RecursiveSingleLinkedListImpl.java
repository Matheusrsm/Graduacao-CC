package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		if (data == null) {
			return 0;
		} else {
			return next.size() + 1;
		}
	}

	@Override
	public T search(T element) {
		if (element == null) {
			return null;
		} else if (!(data == null)) {
			if (data.equals(element)) {
				return data;
			} else {
				return next.search(element);
			}
		} else {
			return null;
		}
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			if (data == null) {
				setData(element);
				setNext(new RecursiveSingleLinkedListImpl<T>());
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!(element == null) && !(data == null)) {
			if (data.equals(element)) {
				setData(next.data);
				setNext(next.next);
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		if (!(data == null)) {
			int cont = 0;
			array[cont] = data;
			toArray(array, cont, next);
		}
		return array;
	}

	private void toArray(T[] array, int cont, RecursiveSingleLinkedListImpl<T> node) {
		if (!(node.getData() == null)) {
			cont++;
			array[cont] = node.data;
			toArray(array, cont, node.next);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}