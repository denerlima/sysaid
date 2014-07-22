package persistence.dao;

import javax.inject.Named;

import model.entity.OrdemDeCompra;

@Named
public class OrdemDeCompraDAO extends GenericDAO<OrdemDeCompra> {

	private static final long serialVersionUID = 1L;

	public OrdemDeCompraDAO() {
		super(OrdemDeCompra.class);
	}

	public void delete(OrdemDeCompra ordemDeCompra) {
		ordemDeCompra.setAtivo(0);
		super.update(ordemDeCompra);
	}

}