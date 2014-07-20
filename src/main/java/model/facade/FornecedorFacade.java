package model.facade;

import java.io.Serializable;

import model.entity.Fornecedor;
import persistence.dao.FornecedorDAO;
import persistence.dao.GenericDAO;

public class FornecedorFacade extends GenericFacade<Fornecedor> implements Serializable {
	private static final long serialVersionUID = 1L;

	private FornecedorDAO fornecedorDAO = new FornecedorDAO();

	@Override
	public GenericDAO<Fornecedor> getDAO() {
		return fornecedorDAO;
	}

}