package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (valida(array, leftIndex, rightIndex)) {
			int gap = (int) (rightIndex / 1.25);
			boolean trocou = true;
			while (gap > 1 || trocou) {
				if (gap > 1)
					gap = (int) (gap / 1.25);
				trocou = false;
				for (int i = leftIndex; (i + gap) <= rightIndex; i++) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						trocou = true;
					}
				}
			}
		}
	}

	private boolean valida(T[] array, int leftIndex, int rightIndex) {
		return (!(array == null)) && (leftIndex >= 0) && (rightIndex < array.length) && (rightIndex > leftIndex);
	}
}
