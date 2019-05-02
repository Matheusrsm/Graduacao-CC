package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (valida(array, leftIndex, rightIndex)) {
			int pivot = leftIndex + 1;
			int proximo = pivot + 1;
			while (pivot <= rightIndex) {
				if (array[pivot - 1].compareTo(array[pivot]) <= 0) {
					pivot = proximo;
					proximo++;
				} else {
					Util.swap(array, pivot - 1, pivot);
					pivot--;
					if (pivot == 0) {
						pivot = proximo;
						proximo++;
					}
				}
			}
		}

	}

	private boolean valida(T[] array, int leftIndex, int rightIndex) {
		return (!(array == null)) && (leftIndex >= 0) && (rightIndex < array.length) && (rightIndex > leftIndex);
	}
}
