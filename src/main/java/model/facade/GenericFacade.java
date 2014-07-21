package model.facade;

import java.io.Serializable;
import java.util.List;

import persistence.dao.GenericDAO;

public abstract class GenericFacade<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract GenericDAO<T> getDAO();
	
	public void create(T entity) {
		getDAO().beginTransaction();
		getDAO().save(entity);
		getDAO().commit();
	}

	public void update(T entity) {
		getDAO().beginTransaction();
		getDAO().update(entity);
		getDAO().commit();
	}

	public T find(int id) {
		T entity = getDAO().find(id);
		return entity;
	}

	public List<T> listAll() {
		List<T> result = getDAO().findAll();
		return result;
	}

	public void delete(T entity) {
		getDAO().beginTransaction();
		getDAO().delete(entity);
		getDAO().commit();
	}
	
}
