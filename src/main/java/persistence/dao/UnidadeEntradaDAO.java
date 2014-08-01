package persistence.dao;

import javax.inject.Named;

import model.entity.Unidade;

@Named
public class UnidadeEntradaDAO extends GenericDAO<Unidade> {

	private static final long serialVersionUID = 1L;

	public UnidadeEntradaDAO() {
		super(Unidade.class);
	}

	public void delete(Unidade unidadeEntrada) {
		unidadeEntrada.setAtivo(0);
		super.update(unidadeEntrada);
	}

}