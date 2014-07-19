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
		getDAO().commitAndCloseTransaction();
	}

	public void update(T entity) {
		getDAO().beginTransaction();
		getDAO().update(entity);
		getDAO().commitAndCloseTransaction();
	}

	public T find(int id) {
		getDAO().beginTransaction();
		T entity = getDAO().find(id);
		getDAO().closeTransaction();
		return entity;
	}

	public List<T> listAll() {
		getDAO().beginTransaction();
		List<T> result = getDAO().findAll();
		getDAO().closeTransaction();
		return result;
	}

	public void delete(T entity) {
		getDAO().beginTransaction();
		getDAO().delete(entity);
		getDAO().commitAndCloseTransaction();
	}
	
}
