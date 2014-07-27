package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.entity.Material;
import model.entity.OrdemDeCompra;
import persistence.dao.GenericDAO;
import persistence.dao.OrdemDeCompraDAO;

public class OrdemDeCompraFacade extends GenericFacade<OrdemDeCompra> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemDeCompraDAO ordemDeCompraDAO;

	@Override
	public GenericDAO<OrdemDeCompra> getDAO() {
		return ordemDeCompraDAO;
	}

	public List<OrdemDeCompra> listUnidadesMedidasAtivas(Long numeroOc) {
		return ordemDeCompraDAO.listUnidadesMedidasAtivas(numeroOc);
	}

	public List<OrdemDeCompra> pesquisarListaOCbyFilter(OrdemDeCompra ordemDeCompra , Material material) {
		List<OrdemDeCompra> result = ordemDeCompraDAO.pesquisarListaOCbyFilter(ordemDeCompra , material);        
		return result;		
	}
	
}
