package com.devmedia.primefaces.lazyload;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.devmedia.primefaces.model.Aluno;

public class LazySorter implements Comparator<Aluno> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Aluno aluno1, Aluno aluno2) {

		String value1 = "";
		String value2 = "";
		if (this.sortField.equals("nome")) {
			value1 = aluno1.getNome();
			value2 = aluno2.getNome();
		}

		if (this.sortField.equals("curso")) {
			value1 = aluno1.getCurso();
			value2 = aluno2.getCurso();
		}

		int value = ((Comparable) value1).compareTo(value2);

		return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;

	}
}