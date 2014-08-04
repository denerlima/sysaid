package persistence.dao;

import javax.inject.Named;

import model.entity.Unidade;

@Named
public class UnidadeDAO extends GenericDAO<Unidade> {

	private static final long serialVersionUID = 1L;

	public UnidadeDAO() {
		super(Unidade.class);
	}

	public void delete(Unidade unidade) {
		unidade.setAtivo(0);
		super.update(unidade);
	}

}