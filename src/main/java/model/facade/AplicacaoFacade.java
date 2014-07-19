package model.facade;

import java.io.Serializable;

import model.entity.Aplicacao;
import persistence.dao.AplicacaoDAO;
import persistence.dao.GenericDAO;

public class AplicacaoFacade  extends GenericFacade<Aplicacao> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();

	@Override
	public GenericDAO<Aplicacao> getDAO() {
		return aplicacaoDAO;
	}
}