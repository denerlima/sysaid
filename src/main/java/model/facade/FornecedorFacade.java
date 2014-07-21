package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import persistence.dao.FornecedorDAO;
import persistence.dao.GenericDAO;

@Named
public class FornecedorFacade extends GenericFacade<Fornecedor> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;

	@Override
	public GenericDAO<Fornecedor> getDAO() {
		return fornecedorDAO;
	}

}