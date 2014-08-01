package persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Material;
import model.entity.NotaFiscal;
import model.entity.NotaFiscalMaterial;
import model.entity.OrdemDeCompra;

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

	@SuppressWarnings("unchecked")
	public List<NotaFiscalMaterial> listMateriaisNotasFiscais(NotaFiscal nf, OrdemDeCompra oc,
			Material mat, Date dtEmissaoInicial, Date dtEmissaoFinal,Date dtEntregaInicial, Date dtEntregaFinal) {
		
		List<NotaFiscalMaterial> lista = new ArrayList<NotaFiscalMaterial>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("Select nfm FROM NotaFiscalMaterial nfm left join nfm.notaFiscal nf WHERE nf.ativo = 1");
			
			if (nf.getNrNotaFiscal() != null) {
				sql.append(" AND nf.nrNotaFiscal = :nrNotaFiscal");
			}
			if (oc.getNumeroOC() != null) {
				sql.append(" AND nfm.ordemDeCompraMaterial.ordemDeCompra.numeroOC = :nrOC");
			}
			if (mat.getMaterial() != null) {
				sql.append(" AND nfm.ordemDeCompraMaterial.material.material LIKE :mMat");
			}
			if (mat.getTipoMaterial() != null) {
				sql.append(" AND nfm.ordemDeCompraMaterial.material.tipoMaterial.id = :idTipoMat");
			}
			if (nf.getFornecedor() != null) {
				sql.append(" AND nf.fornecedor.id = :fornecedor");
			}
			
			sql.append(" ORDER BY nf.id");

			Query query = getEntityManager().createQuery(sql.toString());

			if (nf.getNrNotaFiscal() != null) {
				query.setParameter("nrNotaFiscal", nf.getNrNotaFiscal());
			}
			if (oc.getNumeroOC() != null) {
				query.setParameter("nrOC", oc.getNumeroOC());
			}
			if (mat.getMaterial() != null) {
				query.setParameter("mMat", "%"+mat.getMaterial()+"%");
			}		
			if (mat.getTipoMaterial() != null) {
				query.setParameter("idTipoMat", mat.getTipoMaterial().getId());
			}
			if (nf.getFornecedor() != null) {
				query.setParameter("fornecedor", nf.getFornecedor().getId());
			} 

			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return lista;
		
	}
	
}
