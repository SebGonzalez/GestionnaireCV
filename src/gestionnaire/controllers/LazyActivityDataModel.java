package gestionnaire.controllers;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import gestionnaire.entities.Activity;
import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;

public class LazyActivityDataModel extends LazyDataModel<Activity> {

	private static final long serialVersionUID = 1L;
	private ICvManager cm;
	private int taille;

	public LazyActivityDataModel(ICvManager cm, int tailleData) {
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
	public Object getRowKey(Activity activity) {
		System.out.println("non");
		return activity.getId();
	}

	@Override
	public List<Activity> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		if (first + pageSize > taille) {
			return cm.getRangeActivities(first, taille - first, filters);
		} else {
			return cm.getRangeActivities(first, pageSize, filters);
		}
	}
}