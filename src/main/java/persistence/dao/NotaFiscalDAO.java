package persistence.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.NotaFiscal;

@Named
public class NotaFiscalDAO extends GenericDAO<NotaFiscal> {

	private static final long serialVersionUID = 1L;

	public NotaFiscalDAO() {
		super(NotaFiscal.class);
	}

	public void delete(NotaFiscal notaFiscal) {
		notaFiscal.setAtivo(0);
		super.update(notaFiscal);
	}

	public Predicate clausulaWhere(CriteriaBuilder cb, Root<NotaFiscal> rootCriteria) {
		return cb.notEqual(rootCriteria.get("ativo"), 0);
	}
	
}
