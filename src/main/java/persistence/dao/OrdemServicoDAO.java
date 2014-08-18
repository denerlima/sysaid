package persistence.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Material;
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

	public BigDecimal calculaTotalSaidas(Material mat, Date dataInicial,
			Date dataFinal) {

		BigDecimal total = new BigDecimal(0);
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUM(osmh.quantidade) FROM OrdemServicoMaterialHistorico osmh LEFT JOIN osmh.ordemServicoMaterial osm WHERE osm.ativo = 1");
			sql.append(" AND osmh.tipo >=1 ");
			
			if (mat.getId() != null) {
				sql.append(" AND osm.material.id = :idMat ");
			}
			if (dataInicial != null && dataFinal != null) {
				sql.append(" AND osmh.data BETWEEN :dataInicial  AND :dataFinal ");
			}

			Query query = getEntityManager().createQuery(sql.toString());

			if (mat.getId() != null) {
				query.setParameter("idMat", mat.getId());
			}
			if (dataInicial != null && dataFinal != null) {
				query.setParameter("dataInicial", dataInicial);
				query.setParameter("dataFinal", dataFinal);
			}

			total = (BigDecimal) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
