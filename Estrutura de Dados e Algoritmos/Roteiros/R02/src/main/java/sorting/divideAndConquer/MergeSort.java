package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && !(array == null)) {
			int middleIndex = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);
			merge(array, leftIndex, middleIndex, rightIndex);
		}
	}

	public void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = leftIndex; i <= rightIndex; i++)
			aux[i] = array[i];
		int auxLeft = leftIndex;
		int auxRight = middleIndex + 1;
		int present = leftIndex;
		while (auxLeft <= middleIndex && auxRight <= rightIndex) {
			if (aux[auxLeft].compareTo(aux[auxRight]) > 0) {
				array[present] = aux[auxRight];
				auxRight++;
			} else {
				array[present] = aux[auxLeft];
				auxLeft++;
			}
			present++;
		}
		for (int i = 0; i <= (middleIndex - auxLeft); i++) {
			array[present + i] = aux[auxLeft + i];
		}
	}
}
