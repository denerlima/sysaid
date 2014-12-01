package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Log;
import persistence.dao.GenericDAO;
import persistence.dao.LogDAO;

@Named
public class LogFacade extends GenericFacade<Log> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LogDAO logDAO;

	@Override
	public GenericDAO<Log> getDAO() {
		return logDAO;
	}

}
