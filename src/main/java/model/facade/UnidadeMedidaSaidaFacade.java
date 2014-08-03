package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.UnidadeMedidaSaida;
import persistence.dao.GenericDAO;
import persistence.dao.UnidadeMedidaSaidaDAO;

@Named
public class UnidadeMedidaSaidaFacade extends GenericFacade<UnidadeMedidaSaida> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeMedidaSaidaDAO unidadeMedidaSaidaDAO;

	@Override
	public GenericDAO<UnidadeMedidaSaida> getDAO() {
		return unidadeMedidaSaidaDAO;
	}
	
}
