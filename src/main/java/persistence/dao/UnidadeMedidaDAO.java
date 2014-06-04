package persistence.dao;

import model.entity.UnidadeMedida;

public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida> {

	private static final long serialVersionUID = 1L;

	public UnidadeMedidaDAO() {
		super(UnidadeMedida.class);
	}

	public void delete(UnidadeMedida unidadeMedida) {
		super.delete(unidadeMedida.getId(), UnidadeMedida.class);
	}

}