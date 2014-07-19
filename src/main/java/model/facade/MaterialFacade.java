package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Material;
import persistence.dao.MaterialDAO;

public class MaterialFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MaterialDAO materialDAO = new MaterialDAO();

	public void createMaterial(Material material) {
		materialDAO.beginTransaction();
		materialDAO.save(material);
		materialDAO.commitAndCloseTransaction();
	}

	public void updateMaterial(Material material) {
		materialDAO.beginTransaction();
		Material persistedMaterial = materialDAO.find(material.getId());
		persistedMaterial.setDescricao(material.getDescricao());
		materialDAO.update(persistedMaterial);
		materialDAO.commitAndCloseTransaction();
	}

	public Material findMaterial(int materialId) {
		materialDAO.beginTransaction();
		Material material = materialDAO.find(materialId);
		materialDAO.closeTransaction();
		return material;
	}
	
	public Material findMaterialbyNomeMaterial(String nomeMaterial) {
		materialDAO.beginTransaction();
		Material material = materialDAO.findMaterialByMaterial(nomeMaterial);
        materialDAO.closeTransaction();
		return material;
    }
	
	public List<Material> findMateriaisByFilter(Material material) {
		materialDAO.beginTransaction();
		//List<Material> result = materialDAO.findMateriaisByFilter(material);
		List<Material> result = materialDAO.findAll();
		materialDAO.closeTransaction();
		return result;
	}
	
	public List<Material> listAllMateriais() {
		materialDAO.beginTransaction();
		List<Material> result = materialDAO.findAll();
		materialDAO.closeTransaction();
		return result;
	}

	public void deleteMaterial(Material material) {
		materialDAO.beginTransaction();
		Material persistedMaterial = materialDAO.findReferenceOnly(material.getId());
		materialDAO.delete(persistedMaterial);
		materialDAO.commitAndCloseTransaction();
	}
}