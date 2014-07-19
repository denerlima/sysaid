package model.facade;

import java.io.Serializable;

import model.entity.TipoMaterial;
import persistence.dao.GenericDAO;
import persistence.dao.TipoMaterialDAO;

public class TipoMaterialFacade extends GenericFacade<TipoMaterial> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private TipoMaterialDAO tipoMaterialDAO = new TipoMaterialDAO();

	@Override
	public GenericDAO<TipoMaterial> getDAO() {
		return tipoMaterialDAO;
	}

}