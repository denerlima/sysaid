package model.facade;

import java.io.Serializable;

import model.entity.OrdemDeCompra;
import persistence.dao.GenericDAO;
import persistence.dao.OrdemDeCompraDAO;

public class OrdemDeCompraFacade extends GenericFacade<OrdemDeCompra> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private OrdemDeCompraDAO ordemDeCompraDAO = new OrdemDeCompraDAO();

	@Override
	public GenericDAO<OrdemDeCompra> getDAO() {
		return ordemDeCompraDAO;
	}
}