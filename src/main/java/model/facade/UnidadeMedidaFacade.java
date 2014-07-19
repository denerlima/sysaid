package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.UnidadeMedida;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeMedidaDAO;

public class UnidadeMedidaFacade extends GenericFacade<UnidadeMedida> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

	public List<UnidadeMedida> listAll() {
		unidadeMedidaDAO.beginTransaction();
		List<UnidadeMedida> result = unidadeMedidaDAO.listUnidadesMedidasAtivas();
		unidadeMedidaDAO.closeTransaction();
		return result;
	}

	@Override
	public GenericDAO<UnidadeMedida> getDAO() {
		return unidadeMedidaDAO;
	}
}