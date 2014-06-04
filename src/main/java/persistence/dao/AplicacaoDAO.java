package persistence.dao;

import model.entity.Aplicacao;

public class AplicacaoDAO extends GenericDAO<Aplicacao> {

	private static final long serialVersionUID = 1L;

	public AplicacaoDAO() {
		super(Aplicacao.class);
	}

	public void delete(Aplicacao aplicacao) {
		super.delete(aplicacao.getId(), Aplicacao.class);
	}

}