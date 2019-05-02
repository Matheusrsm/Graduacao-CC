package liMaCor;

import java.util.Comparator;

public class ComparadorQtdProjetos implements Comparator<Funcionario> {

	@Override
	public int compare(Funcionario f1, Funcionario f2) {
		if(f1.getQtdProjetos() > f2.getQtdProjetos()) return -1;
		else if(f1.getQtdProjetos() < f2.getQtdProjetos()) return 1;
		return 0;
	}
}