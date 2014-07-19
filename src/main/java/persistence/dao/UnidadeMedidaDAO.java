package persistence.dao;

import java.util.List;

import javax.persistence.Query;

import model.entity.UnidadeMedida;

public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida> {

	private static final long serialVersionUID = 1L;

	public UnidadeMedidaDAO() {
		super(UnidadeMedida.class);
	}

	@SuppressWarnings("unchecked")
	public List<UnidadeMedida> listUnidadesMedidasAtivas() {
        Query query = getEntityManager().createQuery("From UnidadeMedida um WHERE ativo = 1 order by um.unidadeEntrada.descricao ASC");
        return query.getResultList();
    }
	
	public void delete(UnidadeMedida unidadeMedida) {
		super.delete(unidadeMedida.getId(), UnidadeMedida.class);
	}

}