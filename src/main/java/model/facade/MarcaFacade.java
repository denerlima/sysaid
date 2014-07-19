package model.facade;

import java.io.Serializable;

import model.entity.Marca;
import persistence.dao.GenericDAO;
import persistence.dao.MarcaDAO;

public class MarcaFacade extends GenericFacade<Marca> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MarcaDAO marcaDAO = new MarcaDAO();

	@Override
	public GenericDAO<Marca> getDAO() {
		return marcaDAO;
	}
}