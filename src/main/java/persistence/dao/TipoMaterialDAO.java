package persistence.dao;

import javax.inject.Named;

import model.entity.TipoMaterial;

@Named
public class TipoMaterialDAO extends GenericDAO<TipoMaterial> {

	private static final long serialVersionUID = 1L;

	public TipoMaterialDAO() {
		super(TipoMaterial.class);
	}

	public void delete(TipoMaterial tipoMaterial) {
		tipoMaterial.setAtivo(0);
		super.update(tipoMaterial);
	}

}