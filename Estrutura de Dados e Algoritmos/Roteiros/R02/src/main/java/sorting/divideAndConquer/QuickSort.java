package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && !(array == null)) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotIndex - 1);
			sort(array, pivotIndex + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int auxLeft = leftIndex + 1;
		int auxRight = rightIndex;
		while (auxLeft <= auxRight) {
			if (array[auxLeft].compareTo(pivot) <= 0)
				auxLeft++;
			else if (pivot.compareTo(array[auxRight]) < 0)
				auxRight--;
			else {
				Util.swap(array, auxLeft, auxRight);
				auxLeft++;
				auxRight--;
			}
		}
		Util.swap(array, leftIndex, auxRight);
		return auxRight;
	}
}
