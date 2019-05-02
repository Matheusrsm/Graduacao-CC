package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) {
			throw new HashtableOverflowException();
		}
		if (element != null && indexOf(element) == -1) {
			int probe = 0;
			while (probe < capacity()) {
				int keyIndex = getHashFunction().hash(element, probe);
				if (table[keyIndex] == null || table[keyIndex].equals(deletedElement)) {
					table[keyIndex] = element;
					elements++;
					break;
				} else {
					COLLISIONS++;
					probe++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int keyIndex = indexOf(element);
			if (keyIndex != -1) {
				table[keyIndex] = deletedElement;
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T searchedElement = null;
		if (indexOf(element) != -1) {
			searchedElement = element;
		}
		return searchedElement;
	}

	@Override
	public int indexOf(T element) {
		int indexOfElement = -1;
		if (element != null && !isEmpty()) {
			int probe = 0;
			while (probe < capacity()) {
				int keyIndex = getHashFunction().hash(element, probe);
				if (table[keyIndex] != null && table[keyIndex].equals(element)) {
					indexOfElement = keyIndex;
					break;
				} else {
					probe++;
				}
			}
		}
		return indexOfElement;
	}

	@Override
	public HashFunctionQuadraticProbing<T> getHashFunction() {
		if (hashFunction instanceof HashFunctionQuadraticProbing<?>) {
			return (HashFunctionQuadraticProbing<T>) hashFunction;
		} else {
			return null;
		}
	}
}