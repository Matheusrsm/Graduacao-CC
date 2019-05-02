package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super();
		head = last = new DoubleLinkedListNode<T>();
	}

	@Override
	public void insertFirst(T element) {
		if (!(element == null)) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
						new DoubleLinkedListNode<T>());
				setHead(newHead);
				setLast(newHead);
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) head,
						new DoubleLinkedListNode<T>());
				((DoubleLinkedListNode<T>) head).setPrevious(newHead);
				setHead(newHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			setHead(head.next);
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (head.next.isNIL()) {
				setHead(new DoubleLinkedListNode<T>());
			} else {
				SingleLinkedListNode<T> aux = head;
				while (!aux.next.isNIL()) {
					aux = aux.getNext();
				}
				aux.setData(null);
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}