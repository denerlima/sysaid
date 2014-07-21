package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Marca;
import persistence.dao.GenericDAO;
import persistence.dao.MarcaDAO;

@Named
public class MarcaFacade extends GenericFacade<Marca> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MarcaDAO marcaDAO;

	@Override
	public GenericDAO<Marca> getDAO() {
		return marcaDAO;
	}
	
}
