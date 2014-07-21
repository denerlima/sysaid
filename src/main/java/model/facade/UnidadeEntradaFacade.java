package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.UnidadeEntrada;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeEntradaDAO;

@Named
public class UnidadeEntradaFacade extends GenericFacade<UnidadeEntrada> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeEntradaDAO unidadeEntradaDAO;

	@Override
	public GenericDAO<UnidadeEntrada> getDAO() {
		return unidadeEntradaDAO;
	}

}
