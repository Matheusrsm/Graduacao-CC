package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {

	protected LinkedList<T> elements;
	protected LinkedList<BNode<T>> children;
	protected BNode<T> parent;
	protected int order;

	public BNode(int order) {
		this.order = order;
		this.elements = new LinkedList<T>();
		this.children = new LinkedList<BNode<T>>();
	}

	@Override
	public String toString() {
		return elements.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj != null) {
			if (obj instanceof BNode) {
				if (this.size() == ((BNode<T>) obj).size()) {
					resp = true;
					int i = 0;
					while (i < this.size() && resp) {
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return elements.size();
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public boolean isFull() {
		return size() == order - 1;
	}

	public void addElement(T element) {
		elements.add(element);
		Collections.sort(elements);
	}

	public void removeElement(T element) {
		elements.remove(element);
	}

	public void removeElement(int position) {
		elements.remove(position);
	}

	public void addChild(int position, BNode<T> child) {
		children.add(position, child);
		child.parent = this;
	}

	public void removeChild(BNode<T> child) {
		children.remove(child);
	}

	public int indexOfChild(BNode<T> child) {
		return children.indexOf(child);
	}

	public T getElementAt(int index) {
		return elements.get(index);
	}

	protected void split() {
		int middle = (size()) / 2;
		BNode<T> left = copyL(middle);
		BNode<T> right = copyR(middle);
		if (parent == null) {
			parent = new BNode<T>(getOrder());
			parent.children.addFirst(this);
		}
		BNode<T> parent = this.parent;
		int index = parent.indexOfChild(this);
		parent.removeChild(this);
		parent.addChild(index, left);
		parent.addChild(index + 1, right);
		left.setParent(parent);
		right.setParent(parent);
		promote();
		if (parent.size() >= getOrder()) {
			parent.split();
		}
	}

	private BNode<T> copyR(int media) {
		BNode<T> node = new BNode<T>(getOrder());
		for (int i = media + 1; i < elements.size(); i++) {
			node.addElement(elements.get(i));
		}
		for (int i = media + 1; i < children.size(); i++) {
			node.addChild(i - media - 1, children.get(i));
		}
		return node;
	}

	private BNode<T> copyL(int media) {
		BNode<T> node = new BNode<T>(getOrder());
		for (int i = 0; i < media; i++) {
			node.addElement(elements.get(i));
		}
		for (int i = 0; i <= media; i++) {
			node.addChild(i, children.get(i));
		}
		return node;
	}

	protected void promote() {
		int media = (size()) / 2;
		T element = elements.get(media);
		parent.addElement(element);
	}

	public LinkedList<T> getElements() {
		return elements;
	}

	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}

	public LinkedList<BNode<T>> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}

	public BNode<T> copy() {
		BNode<T> result = new BNode<T>(order);
		result.parent = parent;
		for (int i = 0; i < elements.size(); i++) {
			result.addElement(elements.get(i));
		}
		for (int i = 0; i < children.size(); i++) {
			result.addChild(i, ((BNode<T>) children.get(i)).copy());
		}
		return result;
	}

	public BNode<T> getParent() {
		return parent;
	}

	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}

	public int getMaxKeys() {
		return order - 1;
	}

	public int getMaxChildren() {
		return order;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}