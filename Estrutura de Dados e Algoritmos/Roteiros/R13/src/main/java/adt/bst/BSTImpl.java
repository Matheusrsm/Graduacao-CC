package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int height(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) {
			return null;
		}
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().equals(element)) {
			return node;
		} else if (node.getData().compareTo(element) > 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(null, root, element);
		}
	}

	private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setParent(parent);
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
		} else if (node.getData().compareTo(element) > 0) {
			insert(node, (BSTNode<T>) node.getLeft(), element);
		} else if (node.getData().compareTo(element) < 0) {
			insert(node, (BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node == null) {
			return null;
		} else {
			return sucessor(node);
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
		if (sucessor != null && !sucessor.isEmpty()) {
			return sucessor;
		} else {
			sucessor = (BSTNode<T>) node.getParent();
			while (sucessor != null && !sucessor.isEmpty() && (sucessor.getData().compareTo(node.getData()) < 0)) {
				sucessor = (BSTNode<T>) sucessor.getParent();
			}
			if (sucessor != null && sucessor.isEmpty()) {
				return null;
			} else {
				return sucessor;
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node == null) {
			return null;
		} else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = maximum((BSTNode<T>) node.getLeft());
		if (predecessor != null && !predecessor.isEmpty()) {
			return predecessor;
		} else {
			predecessor = (BSTNode<T>) node.getParent();
			while (predecessor != null && !predecessor.isEmpty()
					&& (predecessor.getData().compareTo(node.getData()) > 0)) {
				predecessor = (BSTNode<T>) predecessor.getParent();
			}
			if (predecessor != null && predecessor.isEmpty()) {
				return null;
			} else {
				return predecessor;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty()) {
				if (node.getParent() == null) {
					removeRoot(node);
				} else if (node.isLeaf()) {
					node.setData(null);
				} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
					remove2Degrees(node);
				} else {
					remove1Degree(node);
				}
			}
		}
	}

	protected void removeRoot(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			root = (BSTNode<T>) node.getLeft();
			root.setParent(null);
			node = null;
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			root = (BSTNode<T>) node.getRight();
			root.setParent(null);
			node = null;
		} else {
			remove2Degrees(node);
		}
	}

	private void remove1Degree(BSTNode<T> node) {
		if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.getLeft().setParent(node.getParent());
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getLeft());
			} else {
				node.getParent().setRight(node.getLeft());
			}
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			node.getRight().setParent(node.getParent());
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getRight());
			} else {
				node.getParent().setRight(node.getRight());
			}
		}
	}

	private void remove2Degrees(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T data = sucessor.getData();
		remove(sucessor.getData());
		node.setData(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, root, 0);
		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index++;
			index = preOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = preOrder(array, (BSTNode<T>) node.getRight(), index);
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[size()];
		order(array, root, 0);
		return array;
	}

	private int order(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = order(array, (BSTNode<T>) node.getLeft(), index);
			array[index] = node.getData();
			index++;
			index = order(array, (BSTNode<T>) node.getRight(), index);
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[size()];
		postOrder(array, root, 0);
		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.isEmpty()) {
			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
			index = postOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index++;
		}
		return index;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}