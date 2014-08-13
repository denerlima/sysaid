package persistence.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import util.DataUtil;

import model.entity.Material;
import model.entity.NotaFiscalMaterial;
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

	public BigDecimal calculaTotalSaidas(Material material, Date dataInicial, Date dataFinal) {
		
		BigDecimal total = new BigDecimal(100);

		return total;		
	}

	
}
