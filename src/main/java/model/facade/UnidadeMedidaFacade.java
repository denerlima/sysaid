package model.facade;

import java.io.Serializable;

import model.entity.UnidadeMedida;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeMedidaDAO;

public class UnidadeMedidaFacade extends GenericFacade<UnidadeMedida> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

	@Override
	public GenericDAO<UnidadeMedida> getDAO() {
		return unidadeMedidaDAO;
	}
}