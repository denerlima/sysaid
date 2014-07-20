package persistence.dao;

import model.entity.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor> {

	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}

}