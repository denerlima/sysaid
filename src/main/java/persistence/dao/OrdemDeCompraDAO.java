package persistence.dao;

import model.entity.OrdemDeCompra;

public class OrdemDeCompraDAO extends GenericDAO<OrdemDeCompra> {

	private static final long serialVersionUID = 1L;

	public OrdemDeCompraDAO() {
		super(OrdemDeCompra.class);
	}

	public void delete(OrdemDeCompra ordemDeCompra) {
		super.delete(ordemDeCompra.getId(), OrdemDeCompra.class);
	}

}