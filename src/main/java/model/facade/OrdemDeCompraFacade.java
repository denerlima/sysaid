package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import model.entity.Material;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
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

	public List<OrdemDeCompra> listOrdensCompras(Integer numeroOc) {
		return ordemDeCompraDAO.listOrdensCompras(numeroOc);
	}

	public List<OrdemDeCompraMaterial> listMateriaisOrdensCompras(OrdemDeCompra ordemDeCompra, Material material) {
		List<OrdemDeCompraMaterial> result = ordemDeCompraDAO.listMateriaisOrdensCompras(ordemDeCompra, material);        
		return result;		
	}
	
	public boolean isOrdemDeCompraUtilizadaPorNotaFiscal(Integer id) {
		return ordemDeCompraDAO.isOrdemDeCompraUtilizadaPorNotaFiscal(id);
	}
	
}
