package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
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
	public HashFunctionLinearProbing<T> getHashFunction() {
		if (hashFunction instanceof HashFunctionLinearProbing<?>) {
			return (HashFunctionLinearProbing<T>) hashFunction;
		} else {
			return null;
		}
	}
}