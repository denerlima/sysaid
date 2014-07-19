package persistence.dao;

import model.entity.UnidadeEntrada;

public class UnidadeEntradaDAO extends GenericDAO<UnidadeEntrada> {

	private static final long serialVersionUID = 1L;

	public UnidadeEntradaDAO() {
		super(UnidadeEntrada.class);
	}

	public void delete(UnidadeEntrada UnidadeEntrada) {
		super.delete(UnidadeEntrada.getId(), UnidadeEntrada.class);
	}

}