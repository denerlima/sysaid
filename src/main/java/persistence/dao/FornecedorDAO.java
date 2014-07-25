package persistence.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Fornecedor;

@Named
public class FornecedorDAO extends GenericDAO<Fornecedor> {

	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}

	@Override
	public Predicate clausulaWhere(CriteriaBuilder cb, Root<Fornecedor> rootCriteria) {
		return null;
	}
	
}