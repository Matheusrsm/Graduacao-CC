package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && !(array == null)) {
			int maior = procuraMaior(array, leftIndex, rightIndex);
			int[] output = new int[rightIndex - leftIndex + 1];
			int[] count = new int[maior + 1];

			for (int i = 0; i <= maior; i++)
				count[i] = 0;
			for (int i = leftIndex; i <= rightIndex; i++)
				count[array[i]]++;
			for (int i = 1; i <= maior; i++)
				count[i] += count[i - 1];
			for (int i = leftIndex; i <= rightIndex; i++) {
				output[count[array[i]] - 1] = array[i];
				count[array[i]]--;
			}
			for (int i = leftIndex; i <= rightIndex; i++)
				array[i] = output[i];
		}
	}

	private int procuraMaior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = leftIndex;
		for (int i = leftIndex + 1; i <= rightIndex; i++)
			if (array[i] > array[maior])
				maior = i;
		return array[maior];
	}
}
