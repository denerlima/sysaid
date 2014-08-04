package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Unidade;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeDAO;

@Named
public class UnidadeFacade extends GenericFacade<Unidade> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeDAO unidadeEntradaDAO;

	@Override
	public GenericDAO<Unidade> getDAO() {
		return unidadeEntradaDAO;
	}

}
