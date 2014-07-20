package model.facade;

import java.io.Serializable;

import model.entity.Inventario;
import model.entity.InventarioMaterial;
import persistence.dao.GenericDAO;
import persistence.dao.InventarioDAO;
import persistence.dao.MaterialDAO;

public class InventarioFacade extends GenericFacade<Inventario> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private InventarioDAO inventarioDAO = new InventarioDAO();
	private MaterialDAO materialDAO = new MaterialDAO();

	public void create(Inventario entity) {
		getDAO().beginTransaction();
		materialDAO.joinTransaction(inventarioDAO.getEntityManager());
		definirQuantidades(entity);
		getDAO().save(entity);
		getDAO().commitAndCloseTransaction();
	}
	
	public void update(Inventario entity) {
		getDAO().beginTransaction();
		materialDAO.joinTransaction(inventarioDAO.getEntityManager());
		definirQuantidades(entity);
		getDAO().update(entity);
		getDAO().commitAndCloseTransaction();
	}
	
	private void definirQuantidades(Inventario inventario) {
		for (InventarioMaterial invMat : inventario.getMateriais()) {
			invMat.setQuantidadeEstoque(invMat.getMaterial().getEstoqueInformado());
			invMat.getMaterial().setEstoqueInformado(invMat.getQuantidadeInventariada());
			materialDAO.update(invMat.getMaterial());
		}
	}
	
	@Override
	public GenericDAO<Inventario> getDAO() {
		return inventarioDAO;
	}
	
}