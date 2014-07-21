package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.TipoMaterial;
import persistence.dao.GenericDAO;
import persistence.dao.TipoMaterialDAO;

@Named
public class TipoMaterialFacade extends GenericFacade<TipoMaterial> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoMaterialDAO tipoMaterialDAO;

	@Override
	public GenericDAO<TipoMaterial> getDAO() {
		return tipoMaterialDAO;
	}

}