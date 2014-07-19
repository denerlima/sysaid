package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Aplicacao;
import persistence.dao.AplicacaoDAO;

public class AplicacaoFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();

	public void createAplicacao(Aplicacao aplicacao) {
		aplicacaoDAO.beginTransaction();
		aplicacaoDAO.save(aplicacao);
		aplicacaoDAO.commitAndCloseTransaction();
	}

	public void updateAplicacao(Aplicacao aplicacao) {
		aplicacaoDAO.beginTransaction();
		Aplicacao persistedAplicacao = aplicacaoDAO.find(aplicacao.getId());
		persistedAplicacao.setDescricao(aplicacao.getDescricao());
		aplicacaoDAO.update(persistedAplicacao);
		aplicacaoDAO.commitAndCloseTransaction();
	}

	public Aplicacao findAplicacao(int aplicacaoId) {
		aplicacaoDAO.beginTransaction();
		Aplicacao aplicacao = aplicacaoDAO.find(aplicacaoId);
		aplicacaoDAO.closeTransaction();
		return aplicacao;
	}

	public List<Aplicacao> listAll() {
		aplicacaoDAO.beginTransaction();
		List<Aplicacao> result = aplicacaoDAO.findAll();
		aplicacaoDAO.closeTransaction();
		return result;
	}

	public void deleteAplicacao(Aplicacao aplicacao) {
		aplicacaoDAO.beginTransaction();
		Aplicacao persistedAplicacao = aplicacaoDAO.findReferenceOnly(aplicacao.getId());
		aplicacaoDAO.delete(persistedAplicacao);
		aplicacaoDAO.commitAndCloseTransaction();
	}
}