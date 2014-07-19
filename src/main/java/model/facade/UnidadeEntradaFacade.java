package model.facade;

import java.io.Serializable;

import model.entity.UnidadeEntrada;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeEntradaDAO;

public class UnidadeEntradaFacade extends GenericFacade<UnidadeEntrada> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UnidadeEntradaDAO unidadeEntradaDAO = new UnidadeEntradaDAO();

	@Override
	public GenericDAO<UnidadeEntrada> getDAO() {
		return unidadeEntradaDAO;
	}

}