package persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import model.entity.Material;
import model.entity.OrdemDeCompra;
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
	public List<OrdemDeCompra> listUnidadesMedidasAtivas(Long numeroOc) {
		String numOcStr = "";
		if(numeroOc != null) {
			numOcStr = numeroOc.toString();
		}
        Query query = getEntityManager().createQuery("From OrdemDeCompra oc WHERE ativo = 1 and to_char(oc.numeroOC) like :numOC order by oc.id ASC");
        query.setParameter("numOC", "%"+numOcStr+"%");
        return query.getResultList();
    }

	public List<OrdemDeCompra> pesquisarListaOCbyFilter(OrdemDeCompra oc , Material mat) {	
		List<OrdemDeCompra> lista = new ArrayList<OrdemDeCompra>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("FROM OrdemDeCompra oc WHERE ativo = 1" );	 
		 if(oc.getDataEmissao() != null ) {
			 sql.append(" AND oc.dataEmissao >= to_date('"+ DataUtil.formataData(oc.getDataEmissao())+ "','dd/MM/yy')");					 
		 	}				
		 if(oc.getDataAutorizacao() != null ) {
			 sql.append(" AND oc.dataEmissao <= to_date('"+ DataUtil.formataData(oc.getDataAutorizacao())+ "','dd/MM/yy')");
			}
		if(mat.getMaterial() != null) {
			sql.append(" AND :material IN oc.materiais ");
			//sql.append(" AND UPPER(mat.material) like UPPER(:mat) ");
		}
		if(mat.getTipoMaterial() != null) {
			//sql.append(" AND UPPER(mat.tipoMaterial) like UPPER(:mat) ");
		}
		if(oc.getAutorizador() != null) {
			sql.append(" AND oc.autorizador = :autorizador");
		}
		sql.append(" ORDER BY oc.id");		
		
		Query query = getEntityManager().createQuery(sql.toString());

		if(mat.getMaterial() != null ) {
			query.setParameter("material", mat);
		}
		if(oc.getAutorizador() != null) {
			query.setParameter("autorizador", oc.getAutorizador());
		}
		
//		List<Object[]> list = query.getResultList();
//		
//		if (list != null) {
//			OrdemDeCompra ordemCompra = null;
//			for (Object[] objeto : list) {
//				ordemCompra = new OrdemDeCompra();
//				ordemCompra.setTipoSolicitacao((TipoSolicitacao) objeto[0]);
//				solicitacao.setId(new Long(objeto[1].toString()));
//				solicitacao.setData((Date) objeto[2]);
//				solicitacao.getAplicacoes().add((SolicitacaoAplicacao) objeto[3]);
//				solicitacao.setSolicitante(objeto[4].toString());
//				solicitacao.setNomeSolicitante(objeto[5].toString());
//				solicitacao.setKit((Kit)objeto[6]);
//				lista.add(solicitacao);
//			}
//		}
		
		lista = query.getResultList(); 
		
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return lista;
		
	}
}
