package persistence.dao;

import javax.inject.Named;

import model.entity.UnidadeMedida;

@Named
public class UnidadeMedidaDAO extends GenericDAO<UnidadeMedida> {

	private static final long serialVersionUID = 1L;

	public UnidadeMedidaDAO() {
		super(UnidadeMedida.class);
	}
	
	public void delete(UnidadeMedida unidadeMedida) {
		unidadeMedida.setAtivo(0);
		super.update(unidadeMedida);
	}

}