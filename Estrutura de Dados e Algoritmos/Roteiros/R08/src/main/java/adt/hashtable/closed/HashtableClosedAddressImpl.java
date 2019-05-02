package adt.hashtable.closed;

import java.util.LinkedList;
import util.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size (or the given size, if it is already prime).
	 * For example, if size=10 then the length must be 11. If size=20, the
	 * length must be 23. You must implement this idea in the auxiliary method
	 * getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;
		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize);
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. If the given number is prime, it is returned. You can use the
	 * method Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number) {
		int primeNumber = number;
		while (!Util.isPrime(primeNumber)) {
			primeNumber++;
		}
		return primeNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (!(element == null)) {
			int index = getIndexKey(element);
			if (table[index] == null) {
				table[index] = new LinkedList<T>();
			} else {
				COLLISIONS++;
			}
			if (table[index] instanceof LinkedList) {
				((LinkedList<T>) table[index]).add(element);
				elements++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (!(search(element) == null)) {
			int index = getIndexKey(element);
			if (table[index] instanceof LinkedList) {
				((LinkedList<T>) table[index]).remove(element);
				elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T searchElement = null;
		if (!(element == null)) {
			int index = getIndexKey(element);
			if (!(table[index] == null) && table[index] instanceof LinkedList) {
				if (((LinkedList<T>) table[index]).contains(element)) {
					searchElement = element;
				}
			}
		}
		return searchElement;
	}

	@Override
	public int indexOf(T element) {
		int indexOfElement = -1;
		if (!(search(element) == null)) {
			indexOfElement = getIndexKey(element);
		}
		return indexOfElement;
	}

	private int getIndexKey(T element) {
		return ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
	}
}