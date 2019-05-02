package adt.bst.extended;

import java.util.Comparator;
import adt.bst.*;

public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		SortComparatorBSTImpl<T> tree = new SortComparatorBSTImpl<T>(comparator);
		for (int i = 0; i < array.length; i++) {
			tree.insert(array[i]);
		}
		return tree.order();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[size()];
		reverseOrder(array, root, 0);
		return array;
	}

	private int reverseOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = reverseOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index++;
			index = reverseOrder(array, (BSTNode<T>) node.getLeft(), index);
		}
		return index;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return null;
		} else {
			return search(root, element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().equals(element)) {
			return node;
		} else if (comparator.compare(node.getData(), element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			insert(null, root, element);
		}
	}

	private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setParent(parent);
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
		} else if (comparator.compare(node.getData(), element) > 0) {
			insert(node, (BSTNode<T>) node.getLeft(), element);
		} else if (comparator.compare(node.getData(), element) < 0) {
			insert(node, (BSTNode<T>) node.getRight(), element);
		}
	}
}