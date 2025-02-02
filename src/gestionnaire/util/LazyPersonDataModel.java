package gestionnaire.util;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;

public class LazyPersonDataModel extends LazyDataModel<Person> {

	private static final long serialVersionUID = 1L;
	private List<Person> datasource;
	private ICvManager cm;
	private int taille;

	public LazyPersonDataModel(List<Person> datasource) {
		System.out.println("TAILLE : " + datasource.size());
		this.datasource = datasource;
		this.setRowCount(datasource.size());
	}

	public LazyPersonDataModel(ICvManager cm, int tailleData) {
		this.cm = cm;
		this.taille = tailleData;
		this.setRowCount(tailleData);
	}

	@Override
	public void setRowCount(int rowCount) {
		super.setRowCount(rowCount);
		this.taille = rowCount;
	}

	@Override
	public Person getRowData(String rowKey) {
		System.out.println("oui");
		for (Person car : datasource) {
			if (car.getId() == Long.parseLong(rowKey)) {
				return car;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(Person car) {
		System.out.println("non");
		return car.getId();
	}

	@Override
	public List<Person> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		if (first + pageSize > taille) {
			return cm.getRangePersons(first, taille - first, filters);
		} else {
			return cm.getRangePersons(first, pageSize, filters);
		}
	}
}