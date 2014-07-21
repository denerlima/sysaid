package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.UnidadeMedida;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeMedidaDAO;

@Named
public class UnidadeMedidaFacade extends GenericFacade<UnidadeMedida> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeMedidaDAO unidadeMedidaDAO;

	@Override
	public GenericDAO<UnidadeMedida> getDAO() {
		return unidadeMedidaDAO;
	}
	
}
