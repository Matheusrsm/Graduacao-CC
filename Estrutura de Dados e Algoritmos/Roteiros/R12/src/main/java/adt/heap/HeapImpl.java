package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	protected Comparator<T> comparator;
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int sonLeft(int i) {
		return (i * 2 + 1);
	}

	private int sonRight(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= index; i++) {
			if (heap[i] != null) {
				resp.add(heap[i]);
			}
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	private void heapify(int position) {
		if (!isEmpty() || !(position > index)) {
			int left = sonLeft(position);
			int right = sonRight(position);
			int max = position;
			if (left <= index && comparator.compare(heap[left], heap[max]) > 0) {
				max = left;
			}
			if (right <= index && comparator.compare(heap[right], heap[max]) > 0) {
				max = right;
			}
			if (max != position) {
				Util.swap(heap, max, position);
				heapify(max);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		if (!(element == null)) {
			heap[++index] = element;
			int i = index;
			while (i > 0 && comparator.compare(heap[parent(i)], heap[i]) < 0) {
				Util.swap(heap, parent(i), i);
				i = parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (!(array == null)) {
			heap = array;
			index = array.length - 1;
			for (int i = index; i >= 0; i--) {
				heapify(i);
			}
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) {
			return null;
		}
		T rootElement = rootElement();
		if (index >= 0) {
			Util.swap(heap, 0, index--);
			heapify(0);
		}
		return rootElement;
	}

	@Override
	public T rootElement() {
		T rootElement = null;
		if (!isEmpty()) {
			rootElement = heap[0];
		}
		return rootElement;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] heapsort(T[] array) {
		Comparator<T> comparatorAux = comparator;
		comparator = (a, b) -> b.compareTo(a);
		buildHeap(array);
		T[] newArray = (T[]) new Comparable[size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = extractRootElement();
		}
		heap = (T[]) new Comparable[INITIAL_SIZE];
		index = -1;
		comparator = comparatorAux;
		return newArray;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}
}