package persistence.dao;

import javax.inject.Named;

import model.entity.UnidadeEntrada;

@Named
public class UnidadeEntradaDAO extends GenericDAO<UnidadeEntrada> {

	private static final long serialVersionUID = 1L;

	public UnidadeEntradaDAO() {
		super(UnidadeEntrada.class);
	}

	public void delete(UnidadeEntrada unidadeEntrada) {
		unidadeEntrada.setAtivo(0);
		super.update(unidadeEntrada);
	}

}