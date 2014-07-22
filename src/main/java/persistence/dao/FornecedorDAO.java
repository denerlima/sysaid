package persistence.dao;

import javax.inject.Named;

import model.entity.Fornecedor;

@Named
public class FornecedorDAO extends GenericDAO<Fornecedor> {

	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}

}