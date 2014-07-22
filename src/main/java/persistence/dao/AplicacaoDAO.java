package persistence.dao;

import javax.inject.Named;

import model.entity.Aplicacao;

@Named
public class AplicacaoDAO extends GenericDAO<Aplicacao> {

	private static final long serialVersionUID = 1L;

	public AplicacaoDAO() {
		super(Aplicacao.class);
	}

	public void delete(Aplicacao aplicacao) {
		aplicacao.setAtivo(0);
		super.update(aplicacao);
	}

}
