package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Material;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;

public class MaterialFacade extends GenericFacade<Material> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MaterialDAO materialDAO = new MaterialDAO();


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
	
	@Override
	public GenericDAO<Material> getDAO() {
		return materialDAO;
	}

}