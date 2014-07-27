package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import persistence.dao.GenericDAO;
import persistence.dao.MaterialDAO;

@Named
public class MaterialFacade extends GenericFacade<Material> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaterialDAO materialDAO;

	public Material findMaterialbyNomeMaterial(String nomeMaterial) {
		//materialDAO.beginTransaction();
		Material material = materialDAO.findMaterialByMaterial(nomeMaterial);
        //materialDAO.closeTransaction();
		return material;
    }
	
	public List<Material> findMateriaisByFilter(Material material) {
		//materialDAO.beginTransaction();
		//List<Material> result = materialDAO.findMateriaisByFilter(material);
		List<Material> result = materialDAO.findAll();
		//materialDAO.closeTransaction();
		return result;
	}
	
	public List<Material> pesquisarListaMateriaisbyNomeMaterial(String nomeMaterial) {		
		List<Material> result = materialDAO.findListMaterialByMaterial(nomeMaterial);        
		return result;
    }
	
	@Override
	public GenericDAO<Material> getDAO() {
		return materialDAO;
	}

}
