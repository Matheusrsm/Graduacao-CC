package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			for (int j = leftIndex; j < rightIndex; j = j + 1) {
				if (array[j + 1].compareTo(array[j]) < 0) {
					Util.swap(array, j, j + 1);
				}
			}
			for (int j = rightIndex - 1; j > leftIndex; j = j - 1) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					Util.swap(array, j, j - 1);
				}
			}
			sort(array, leftIndex + 1, rightIndex - 1);
		}
	}
}
