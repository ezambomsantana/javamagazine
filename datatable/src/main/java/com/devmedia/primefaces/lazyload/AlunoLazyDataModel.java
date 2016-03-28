package com.devmedia.primefaces.lazyload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.devmedia.primefaces.model.Aluno;

public class AlunoLazyDataModel extends LazyDataModel<Aluno> {

	private List<Aluno> datasource;

	public AlunoLazyDataModel(List<Aluno> datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<Aluno> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Aluno> data = new ArrayList<Aluno>();

		// filter
		for (Aluno aluno : datasource) {
			boolean match = true;
			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					String filterProperty = it.next();
					Object filterValue = filters.get(filterProperty);

					String fieldValue = "";
					if (filterProperty.equals("nome")) {
						fieldValue = aluno.getNome();
					}

					if (filterProperty.equals("curso")) {
						fieldValue = aluno.getCurso();
					}

					if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
						match = true;
					} else {
						match = false;
						break;
					}

				}
			}

			if (match) {
				data.add(aluno);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}