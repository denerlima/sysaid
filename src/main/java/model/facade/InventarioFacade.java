package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Inventario;
import persistence.dao.InventarioDAO;

public class InventarioFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private InventarioDAO inventarioDAO = new InventarioDAO();

	public void createInventario(Inventario inventario) {
		inventarioDAO.beginTransaction();
		inventarioDAO.save(inventario);
		inventarioDAO.commitAndCloseTransaction();
	}

	public void updateInventario(Inventario inventario) {
		inventarioDAO.beginTransaction();
		Inventario persistedInventario = inventarioDAO.find(inventario.getId());
		persistedInventario.setNumeroInventario(inventario.getNumeroInventario());
		persistedInventario.setDataInventario(inventario.getDataInventario());
		persistedInventario.setAtendente(inventario.getAtendente());
		persistedInventario.setJustificativa(inventario.getJustificativa());
		//persistedInventario.setMateriais(inventario.getMateriais());
		inventarioDAO.update(persistedInventario);
		inventarioDAO.commitAndCloseTransaction();
	}

	public Inventario findInventario(int inventarioId) {
		inventarioDAO.beginTransaction();
		Inventario inventario = inventarioDAO.find(inventarioId);
		inventarioDAO.closeTransaction();
		return inventario;
	}

	public List<Inventario> listAll() {
		inventarioDAO.beginTransaction();
		List<Inventario> result = inventarioDAO.findAll();
		inventarioDAO.closeTransaction();
		return result;
	}

	public void deleteInventario(Inventario inventario) {
		inventarioDAO.beginTransaction();
		Inventario persistedInventario = inventarioDAO.findReferenceOnly(inventario.getId());
		inventarioDAO.delete(persistedInventario);
		inventarioDAO.commitAndCloseTransaction();
	}
}