package persistence.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Parametro;

@Named
public class ParametroDAO extends GenericDAO<Parametro> {

	private static final long serialVersionUID = 1L;

	public ParametroDAO() {
		super(Parametro.class);
	}
	
	@Override
	public Predicate clausulaWhere(CriteriaBuilder cb, Root<Parametro> rootCriteria) {
		return null;
	}
}
