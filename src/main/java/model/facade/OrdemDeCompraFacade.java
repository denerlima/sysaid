package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.OrdemDeCompra;
import persistence.dao.OrdemDeCompraDAO;

public class OrdemDeCompraFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private OrdemDeCompraDAO ordemDeCompraDAO = new OrdemDeCompraDAO();

	public void createOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		ordemDeCompraDAO.beginTransaction();
		ordemDeCompraDAO.save(ordemDeCompra);
		ordemDeCompraDAO.commitAndCloseTransaction();
	}

	public void updateOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		ordemDeCompraDAO.beginTransaction();
		OrdemDeCompra persistedOrdemDeCompra = ordemDeCompraDAO.find(ordemDeCompra.getId());
		persistedOrdemDeCompra.setNumeroOC(ordemDeCompra.getNumeroOC());
		persistedOrdemDeCompra.setDataAutorizacao(ordemDeCompra.getDataAutorizacao());
		persistedOrdemDeCompra.setDataEmissao(ordemDeCompra.getDataEmissao());
		persistedOrdemDeCompra.setAutorizador(ordemDeCompra.getAutorizador());
		persistedOrdemDeCompra.setContratado(ordemDeCompra.getContratado());
		//persistedOrdemDeCompra.setMateriais(ordemDeCompra.getMateriais());
		ordemDeCompraDAO.update(persistedOrdemDeCompra);
		ordemDeCompraDAO.commitAndCloseTransaction();
	}

	public OrdemDeCompra findOrdemDeCompra(int ordemDeCompraId) {
		ordemDeCompraDAO.beginTransaction();
		OrdemDeCompra ordemDeCompra = ordemDeCompraDAO.find(ordemDeCompraId);
		ordemDeCompraDAO.closeTransaction();
		return ordemDeCompra;
	}

	public List<OrdemDeCompra> listAll() {
		ordemDeCompraDAO.beginTransaction();
		List<OrdemDeCompra> result = ordemDeCompraDAO.findAll();
		ordemDeCompraDAO.closeTransaction();
		return result;
	}

	public void deleteOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		ordemDeCompraDAO.beginTransaction();
		OrdemDeCompra persistedOrdemDeCompra = ordemDeCompraDAO.findReferenceOnly(ordemDeCompra.getId());
		ordemDeCompraDAO.delete(persistedOrdemDeCompra);
		ordemDeCompraDAO.commitAndCloseTransaction();
	}
}