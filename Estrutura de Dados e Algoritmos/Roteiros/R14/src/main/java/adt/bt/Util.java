package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = null;
		if (node.right instanceof BSTNode<?> && !node.isEmpty()) {
			BTNode<T> parent = node.parent;
			pivot = (BSTNode<T>) node.right;
			if (parent != null) {
				if (node.equals(parent.left)) {
					parent.left = pivot;
				} else {
					parent.right = pivot;
				}
			}
			pivot.parent = parent;
			node.parent = pivot;
			node.right = pivot.left;
			node.right.parent = node;
			pivot.left = node;
		}
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = null;
		if (node.left instanceof BSTNode<?> && !node.isEmpty()) {
			BTNode<T> parent = node.parent;
			pivot = (BSTNode<T>) node.left;
			if (parent != null) {
				if (node.equals(parent.left)) {
					parent.left = pivot;
				} else {
					parent.right = pivot;
				}
			}
			pivot.parent = parent;
			node.parent = pivot;
			node.left = pivot.right;
			node.left.parent = node;
			pivot.right = node;
		}
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}