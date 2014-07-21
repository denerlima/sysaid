package model.facade;

import java.io.Serializable;

import javax.inject.Inject;

import model.entity.OrdemDeCompra;
import persistence.dao.GenericDAO;
import persistence.dao.OrdemDeCompraDAO;

public class OrdemDeCompraFacade extends GenericFacade<OrdemDeCompra> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemDeCompraDAO ordemDeCompraDAO;

	@Override
	public GenericDAO<OrdemDeCompra> getDAO() {
		return ordemDeCompraDAO;
	}
	
}
