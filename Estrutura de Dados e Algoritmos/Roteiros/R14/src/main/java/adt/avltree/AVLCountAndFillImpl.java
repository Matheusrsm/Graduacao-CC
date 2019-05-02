package adt.avltree;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		this.LLcounter = 0;
		this.RRcounter = 0;
		this.LRcounter = 0;
		this.RLcounter = 0;
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void rebalance(BSTNode<T> node) {
		if (!isEmpty() && node.getLeft() instanceof BSTNode<?> && node.getRight() instanceof BSTNode<?>) {
			int balanceFactor = calculateBalance(node);
			if (balanceFactor > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					LLcounter++;
				} else {
					LRcounter++;
					leftRotation((BSTNode<T>) node.getLeft());
				}
				rightRotation(node);
			} else if (balanceFactor < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					RRcounter++;
				} else {
					RLcounter++;
					rightRotation((BSTNode<T>) node.getRight());
				}
				leftRotation(node);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			T[] newArray = (T[]) new Comparable[array.length + size()];
			int i, j, k;
			for (i = 0; i < preOrder().length; i++) {
				newArray[i] = preOrder()[i];
			}
			k = 0;
			for (j = i; j < newArray.length; j++) {
				newArray[j] = array[k];
				k++;
			}
			sort(newArray);
			fillWithoutRebalanceAux(root, newArray, 0, newArray.length - 1);
		}
	}

	private void fillWithoutRebalanceAux(BSTNode<T> node, T[] array, int leftIndex, int rightIndex) {
		if (rightIndex >= leftIndex && rightIndex < array.length && leftIndex >= 0) {
			int middleIndex = (leftIndex + rightIndex) / 2;
			node.setData(array[middleIndex]);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			fillWithoutRebalanceAux((BSTNode<T>) node.getLeft(), array, leftIndex, middleIndex - 1);
			fillWithoutRebalanceAux((BSTNode<T>) node.getRight(), array, middleIndex + 1, rightIndex);
		}
	}

	private void sort(T[] array) {
		boolean notSwapped = true;
		while (notSwapped) {
			notSwapped = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					swap(array, i, i + 1);
					notSwapped = true;
				}
			}
		}
	}

	private void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}