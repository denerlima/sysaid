package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.TipoMaterial;
import persistence.dao.TipoMaterialDAO;

public class TipoMaterialFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoMaterialDAO tipoMaterialDAO = new TipoMaterialDAO();

	public void createTipoMaterial(TipoMaterial tipoMaterial) {
		tipoMaterialDAO.beginTransaction();
		tipoMaterialDAO.save(tipoMaterial);
		tipoMaterialDAO.commitAndCloseTransaction();
	}

	public void updateTipoMaterial(TipoMaterial tipoMaterial) {
		tipoMaterialDAO.beginTransaction();
		TipoMaterial persistedTipoMaterial = tipoMaterialDAO.find(tipoMaterial.getId());
		persistedTipoMaterial.setDescricao(tipoMaterial.getDescricao());
		tipoMaterialDAO.update(persistedTipoMaterial);
		tipoMaterialDAO.commitAndCloseTransaction();
	}

	public TipoMaterial findTipoMaterial(int tipoMaterialId) {
		tipoMaterialDAO.beginTransaction();
		TipoMaterial tipoMaterial = tipoMaterialDAO.find(tipoMaterialId);
		tipoMaterialDAO.closeTransaction();
		return tipoMaterial;
	}

	public List<TipoMaterial> listAll() {
		tipoMaterialDAO.beginTransaction();
		List<TipoMaterial> result = tipoMaterialDAO.findAll();
		tipoMaterialDAO.closeTransaction();
		return result;
	}

	public void deleteTipoMaterial(TipoMaterial tipoMaterial) {
		tipoMaterialDAO.beginTransaction();
		TipoMaterial persistedTipoMaterial = tipoMaterialDAO.findReferenceOnly(tipoMaterial.getId());
		tipoMaterialDAO.delete(persistedTipoMaterial);
		tipoMaterialDAO.commitAndCloseTransaction();
	}
}