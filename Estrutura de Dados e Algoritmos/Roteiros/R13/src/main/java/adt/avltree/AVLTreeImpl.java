package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	protected int calculateBalance(BSTNode<T> node) {
		int balanceFactor = 0;
		if (node.getLeft() instanceof BSTNode<?> && node.getRight() instanceof BSTNode<?> && !isEmpty()) {
			balanceFactor = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return balanceFactor;
	}

	protected void rebalance(BSTNode<T> node) {
		if (!isEmpty() && node.getLeft() instanceof BSTNode<?> && node.getRight() instanceof BSTNode<?>) {
			int balanceFactor = calculateBalance(node);
			if (balanceFactor > 1) {
				BSTNode<T> leftSon = (BSTNode<T>) node.getLeft();
				if (calculateBalance(leftSon) <= -1) {
					leftRotation(leftSon);
				}
				rightRotation(node);
			} else if (balanceFactor < -1) {
				BSTNode<T> rightSon = (BSTNode<T>) node.getRight();
				if (calculateBalance(rightSon) >= 1) {
					rightRotation(rightSon);
				}
				leftRotation(node);
			}
		}
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> balancedNode = Util.rightRotation(node);
		if (balancedNode.getParent() == null) {
			root = balancedNode;
		}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> balancedNode = Util.leftRotation(node);
		if (balancedNode.getParent() == null) {
			root = balancedNode;
		}
	}

	protected void rebalanceUp(BSTNode<T> node) {

		int balanceFactor = calculateBalance(node);
		if (Math.abs(balanceFactor) >= 2) {
			rebalance(node);
		}
		if (node.getParent() != null && node.getParent() instanceof BSTNode<?>) {
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		super.remove(element);
		rebalanceUp(node);
	}
}