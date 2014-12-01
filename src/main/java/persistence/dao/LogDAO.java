package persistence.dao;

import javax.inject.Named;

import model.entity.Log;

@Named
public class LogDAO extends GenericDAO<Log> {

	private static final long serialVersionUID = 1L;

	public LogDAO() {
		super(Log.class);
	}

}