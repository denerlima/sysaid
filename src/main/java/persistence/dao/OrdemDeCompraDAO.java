package persistence.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import model.entity.OrdemDeCompra;

@Named
public class OrdemDeCompraDAO extends GenericDAO<OrdemDeCompra> {

	private static final long serialVersionUID = 1L;

	public OrdemDeCompraDAO() {
		super(OrdemDeCompra.class);
	}

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
	
}