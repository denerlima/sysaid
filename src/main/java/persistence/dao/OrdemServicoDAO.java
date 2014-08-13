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

import model.entity.Material;
import model.entity.OrdemServico;
import model.entity.OrdemServicoMaterialHistorico;
import util.DataUtil;

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

	public BigDecimal calculaTotalSaidas(Material mat, Date dataInicial, Date dataFinal) {
		
		BigDecimal total = new BigDecimal(100);
		try {
			StringBuffer sql = new StringBuffer();
//	Example	sql.append("Select nfm FROM NotaFiscalMaterial nfm left join nfm.notaFiscal nf WHERE nf.ativo = 1");
			sql.append("Select osmh FROM OrdemServicoMaterialHistorico osmh left join osmh.OrdemServicoMaterial osm WHERE osm.ativo = 1");

			
			if (mat.getId() != null) {
				sql.append(" AND osm.material.id = :idMat");
			}		
			if (dataInicial != null) {
				sql.append(" AND osmh.data >= to_date('"+ DataUtil.formataData(dataInicial)+ "','dd/MM/yy')");
			}
			if (dataFinal != null) {
				sql.append(" AND osmh.data <= to_date('"+ DataUtil.formataData(dataFinal)+ "','dd/MM/yy')");
			}		
			
			sql.append(" ORDER BY osmh.id");

			Query query = getEntityManager().createQuery(sql.toString());

			if (mat.getId() != null) {
				query.setParameter("idMat", mat.getId());
			}			

			List<OrdemServicoMaterialHistorico> lista = new ArrayList<OrdemServicoMaterialHistorico>();
			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return total;		
	}

	
}
