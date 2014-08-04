package persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Material;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
import util.DataUtil;

@Named
public class OrdemDeCompraDAO extends GenericDAO<OrdemDeCompra> {

	private static final long serialVersionUID = 1L;

	public OrdemDeCompraDAO() {
		super(OrdemDeCompra.class);
	}
	
	
	@Override
	public void delete(OrdemDeCompra ordemDeCompra) {
		ordemDeCompra.setAtivo(0);
		super.update(ordemDeCompra);
	}

	@SuppressWarnings("unchecked")
	public List<OrdemDeCompra> listOrdensCompras(Long numeroOc) {
		String numOcStr = "";
		if(numeroOc != null) {
			numOcStr = numeroOc.toString();
		}
        Query query = getEntityManager().createQuery("From OrdemDeCompra oc WHERE ativo = 1 and to_char(oc.numeroOC) like :numOC order by oc.id ASC");
        query.setParameter("numOC", "%"+numOcStr+"%");
        return query.getResultList();
    }

	public boolean isOrdemDeCompraUtilizadaPorNotaFiscal(Integer id) {
		String sql = "Select count(ocm) FROM OrdemDeCompraMaterial ocm left join ocm.ordemDeCompra oc "
				   + " WHERE oc.ativo = 1"
				   + "   AND oc.id = :idOC "
				   + "   AND ocm.ativo = 1 "
				   + "   AND (Select count(nfm) FROM NotaFiscalMaterial nfm WHERE nfm.ordemDeCompraMaterial.id = ocm.id AND nfm.ativo = 1) > 0" ;
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("idOC", id);
        return (Long) query.getSingleResult() > 0;
    }
	
	@SuppressWarnings("unchecked")
	public List<OrdemDeCompraMaterial> listMateriaisOrdensCompras(OrdemDeCompra oc, Material mat) {	
		List<OrdemDeCompraMaterial> lista = new ArrayList<OrdemDeCompraMaterial>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("Select ocm FROM OrdemDeCompraMaterial ocm left join ocm.ordemDeCompra oc WHERE oc.ativo = 1");
			if (oc.getDataEmissao() != null) {
				sql.append(" AND oc.dataEmissao >= to_date('"+ DataUtil.formataData(oc.getDataEmissao())+ "','dd/MM/yy')");
			}
			if (oc.getDataAutorizacao() != null) {
				sql.append(" AND oc.dataEmissao <= to_date('"+ DataUtil.formataData(oc.getDataAutorizacao())+ "','dd/MM/yy')");
			}
			if (mat.getMaterial() != null) {
				sql.append(" AND ocm.material.id = :idMat");
			}
			if (mat.getTipoMaterial() != null) {
				sql.append(" AND ocm.material.tipoMaterial.id = :idTipoMat");
			}
			if (oc.getAutorizador() != null) {
				sql.append(" AND oc.autorizador.userName = :autorizador");
			}
			
			sql.append(" ORDER BY oc.id");

			Query query = getEntityManager().createQuery(sql.toString());

			
			if (mat.getMaterial() != null) {
				query.setParameter("idMat", mat.getId());
			}
			if (mat.getTipoMaterial() != null) {
				query.setParameter("idTipoMat", mat.getTipoMaterial().getId());
			}
			if (oc.getAutorizador() != null) {
				query.setParameter("autorizador", oc.getAutorizador().getUserName());
			}

			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return lista;
		
	}
	
	public Predicate clausulaWhere(CriteriaBuilder cb, Root<OrdemDeCompra> rootCriteria) {
		return cb.notEqual(rootCriteria.get("ativo"), 0);
	}
	
	
}
