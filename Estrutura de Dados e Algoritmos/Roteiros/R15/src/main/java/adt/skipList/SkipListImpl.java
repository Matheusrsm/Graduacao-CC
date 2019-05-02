package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;
	protected int maxHeight;
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		this.root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		this.NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	private void updateRefers(int height, SkipListNode<T>[] update, SkipListNode<T> auxNode) {
		for (int i = 0; i < height; i++) {
			if (update[i].getForward(i) == null) {
				auxNode.getForward()[i] = NIL;
			} else {
				auxNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = auxNode;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue != null) {
			SkipListNode<T>[] update = new SkipListNode[height];
			SkipListNode<T> auxNode = root;
			for (int i = height - 1; i >= 0; i--) {
				while (auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
					auxNode = auxNode.getForward(i);
				}
				update[i] = auxNode;
			}
			auxNode = auxNode.getForward(0);
			if (auxNode.getKey() == key) {
				auxNode.setValue(newValue);
			} else {
				if (height > maxHeight) {
					for (int i = maxHeight; i < height; i++) {
						root.getForward()[i] = NIL;
					}
					maxHeight = height;
				}
				auxNode = new SkipListNode<T>(key, height, newValue);
				updateRefers(height, update, auxNode);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> auxNode = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			if (!auxNode.forward[i].equals(NIL)) {
				while (auxNode.forward[i].value != null && auxNode.forward[i].key < key)
					auxNode = auxNode.forward[i];
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward()[0];
		if (auxNode.key == key) {
			for (int i = 0; i < maxHeight; i++) {
				if (update[i].getForward()[i] != auxNode) {
					break;
				}
				update[i].getForward()[i] = auxNode.getForward()[i];
			}
		}
	}

	@Override
	public int height() {
		int height = maxHeight - 1;
		while (height >= 0 && root.getForward(height).equals(NIL)) {
			if (height == 0) {
				height--;
				break;
			} else {
				height--;
			}
		}
		return height + 1;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> auxNode = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
		}
		SkipListNode<T> searchedNode = auxNode.getForward(0);
		if (searchedNode.getKey() != key) {
			searchedNode = null;
		}
		return searchedNode;
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> auxNode = root.getForward(0);
		while (!auxNode.equals(NIL)) {
			size++;
			auxNode = auxNode.getForward(0);
		}
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		int size = size() + 2;
		SkipListNode<T>[] toArray = new SkipListNode[size];
		SkipListNode<T> auxNode = root;
		int index = 0;
		while (index < size) {
			toArray[index++] = auxNode;
			auxNode = auxNode.getForward(0);
		}
		return toArray;
	}
}