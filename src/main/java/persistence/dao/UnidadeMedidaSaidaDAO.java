package persistence.dao;

import javax.inject.Named;

import model.entity.UnidadeMedidaSaida;

@Named
public class UnidadeMedidaSaidaDAO extends GenericDAO<UnidadeMedidaSaida> {

	private static final long serialVersionUID = 1L;

	public UnidadeMedidaSaidaDAO() {
		super(UnidadeMedidaSaida.class);
	}
	
	public void delete(UnidadeMedidaSaida unidadeMedida) {
		unidadeMedida.setAtivo(0);
		super.update(unidadeMedida);
	}

}
