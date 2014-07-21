package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Aplicacao;
import persistence.dao.AplicacaoDAO;
import persistence.dao.GenericDAO;

@Named
public class AplicacaoFacade  extends GenericFacade<Aplicacao> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AplicacaoDAO aplicacaoDAO;

	@Override
	public GenericDAO<Aplicacao> getDAO() {
		return aplicacaoDAO;
	}
	
}
