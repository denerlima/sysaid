package persistence.dao;

import model.entity.Material;

public class MaterialDAO extends GenericDAO<Material> {

	private static final long serialVersionUID = 1L;

	public MaterialDAO() {
		super(Material.class);
	}

	public void delete(Material material) {
		super.delete(material.getId(), Material.class);
	}

}