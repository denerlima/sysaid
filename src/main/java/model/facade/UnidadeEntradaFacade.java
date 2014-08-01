package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Unidade;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeEntradaDAO;

@Named
public class UnidadeEntradaFacade extends GenericFacade<Unidade> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeEntradaDAO unidadeEntradaDAO;

	@Override
	public GenericDAO<Unidade> getDAO() {
		return unidadeEntradaDAO;
	}

}
