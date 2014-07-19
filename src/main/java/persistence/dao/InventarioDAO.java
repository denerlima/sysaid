package persistence.dao;

import model.entity.Inventario;


public class InventarioDAO extends GenericDAO<Inventario> {

	private static final long serialVersionUID = 1L;

	public InventarioDAO() {
		super(Inventario.class);
	}

	public void delete(Inventario inventario) {
		super.delete(inventario.getId(), Inventario.class);
	}

}