package model.facade;

import java.io.Serializable;

import model.entity.Inventario;
import persistence.dao.GenericDAO;
import persistence.dao.InventarioDAO;

public class InventarioFacade extends GenericFacade<Inventario> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private InventarioDAO inventarioDAO = new InventarioDAO();

	@Override
	public GenericDAO<Inventario> getDAO() {
		return inventarioDAO;
	}
}