package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && !(array == null)) {
			int[] extremos = procuraExtremos(array, leftIndex, rightIndex);
			int menor = extremos[0];
			int maior = extremos[1];
			int[] output = new int[rightIndex - leftIndex + 1];
			int[] count = new int[(maior + 1) - menor];
			int posicaoCorretaFinal = maior - menor;

			for (int i = 0; i <= posicaoCorretaFinal; i++)
				count[i] = 0;
			for (int i = leftIndex; i <= rightIndex; i++)
				count[array[i] - menor]++;
			for (int i = 1; i <= posicaoCorretaFinal; i++)
				count[i] += count[i - 1];
			for (int i = leftIndex; i <= rightIndex; i++) {
				output[count[array[i] - menor] - 1] = array[i];
				count[array[i] - menor]--;
			}
			for (int i = leftIndex; i <= rightIndex; i++)
				array[i] = output[i];
		}
	}

	private int[] procuraExtremos(Integer[] array, int leftIndex, int rightIndex) {
		int maior = leftIndex;
		int menor = leftIndex;
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > array[maior])
				maior = i;
			if (array[i] < array[menor])
				menor = i;
		}
		int[] saida = { array[menor], array[maior] };
		return saida;
	}
}