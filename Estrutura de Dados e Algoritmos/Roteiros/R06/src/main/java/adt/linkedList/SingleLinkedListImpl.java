package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			size = 1;
			while (!aux.next.isNIL()) {
				size++;
				aux = aux.next;
			}
		}
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!(element == null)) {
			SingleLinkedListNode<T> aux = head;
			while (!aux.next.isNIL()) {
				if (aux.data.equals(element)) {
					result = element;
				}
				aux = aux.next;
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			SingleLinkedListNode<T> aux = head;
			while (!aux.isNIL()) {
				aux = aux.next;
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if (!(element == null) && !isEmpty()) {
			if (head.data.equals(element)) {
				head = head.next;
			} else {
				SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
				SingleLinkedListNode<T> aux = head;
				while (!aux.isNIL() && !aux.data.equals(element)) {
					previous = aux;
					aux = aux.next;
				}
				if (!aux.isNIL()) {
					previous.setNext(aux.next);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		int index = 0;
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			array[index] = aux.data;
			aux = aux.next;
			index++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}