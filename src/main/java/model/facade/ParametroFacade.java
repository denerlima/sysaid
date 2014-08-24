package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Parametro;
import persistence.dao.GenericDAO;
import persistence.dao.ParametroDAO;

@Named
public class ParametroFacade extends GenericFacade<Parametro> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ParametroDAO parametroDAO;

	@Override
	public GenericDAO<Parametro> getDAO() {
		return parametroDAO;
	}
	
}
