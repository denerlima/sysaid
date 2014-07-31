package persistence.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.OrdemServico;

@Named
public class OrdemServicoDAO extends GenericDAO<OrdemServico> {

	private static final long serialVersionUID = 1L;

	public OrdemServicoDAO() {
		super(OrdemServico.class);
	}

	public void delete(OrdemServico ordemServico) {
		ordemServico.setAtivo(0);
		super.update(ordemServico);
	}

	public Predicate clausulaWhere(CriteriaBuilder cb, Root<OrdemServico> rootCriteria) {
		return cb.notEqual(rootCriteria.get("ativo"), 0);
	}
	
}
