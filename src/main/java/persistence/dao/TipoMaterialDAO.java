package persistence.dao;

import model.entity.TipoMaterial;

public class TipoMaterialDAO extends GenericDAO<TipoMaterial> {

	private static final long serialVersionUID = 1L;

	public TipoMaterialDAO() {
		super(TipoMaterial.class);
	}

	public void delete(TipoMaterial tipoMaterial) {
		super.delete(tipoMaterial.getId(), TipoMaterial.class);
	}

}