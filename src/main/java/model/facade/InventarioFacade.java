package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
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
			entity.setStatus(Inventario.STATUS_CONCLUIDO);
			getDAO().update(entity);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
		}
	}
	
	public void aprovarInventario(Inventario inventario, InventarioMaterial inventarioMaterial) {
		try {
			getDAO().beginTransaction();
			inventarioMaterial.setQuantidadeEstoque(inventarioMaterial.getMaterial().getEstoque());
			BigDecimal diferenca = inventarioMaterial.getDiferenca();
			//if(diferenca.longValue() < 0) {
			//	diferenca = diferenca.multiply(new BigDecimal(-1));
			//}
			inventarioMaterial.getMaterial().setEstoque(inventarioMaterial.getMaterial().getEstoque().subtract(diferenca));
			materialDAO.update(inventarioMaterial.getMaterial());
			getDAO().update(inventario);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
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
