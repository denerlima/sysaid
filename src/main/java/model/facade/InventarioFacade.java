package model.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Inventario;
import model.entity.InventarioMaterial;
import model.entity.Material;
import persistence.dao.GenericDAO;
import persistence.dao.InventarioDAO;
import persistence.dao.MaterialDAO;

@Named
public class InventarioFacade extends GenericFacade<Inventario> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InventarioDAO inventarioDAO;
	
	@Inject
	private MaterialDAO materialDAO;

	public void create(Inventario entity) {
		getDAO().beginTransaction();
		//definirQuantidades(entity);
		getDAO().save(entity);
		getDAO().commit();
	}
	
	public void update(Inventario entity) {
		getDAO().beginTransaction();
		//definirQuantidades(entity);
		getDAO().update(entity);
		getDAO().commit();
	}
	
	public void concluir(Inventario entity) {
		try {
			getDAO().beginTransaction();
			definirQuantidades(entity);
			entity.setStatus(Inventario.STATUS_CONCLUIDO);
			getDAO().update(entity);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
		}
	}
	
	private void definirQuantidades(Inventario inventario) {
		for (InventarioMaterial invMat : inventario.getMateriais()) {
			if(invMat.isAprovado()) {
				invMat.setQuantidadeEstoque(invMat.getMaterial().getEstoque());
				invMat.getMaterial().setEstoque(invMat.getQuantidadeAprovada());
				materialDAO.update(invMat.getMaterial());
			}
		}
	}
	
	public List<InventarioMaterial> listMateriaisInventarios(Inventario inventario, Material material , Date dtInicial , Date dtFinal) {
		List<InventarioMaterial> result = inventarioDAO.listMateriaisInventarios(inventario, material, dtInicial, dtFinal);        
		return result;		
	}
	
	@Override
	public GenericDAO<Inventario> getDAO() {
		return inventarioDAO;
	}
	
}
