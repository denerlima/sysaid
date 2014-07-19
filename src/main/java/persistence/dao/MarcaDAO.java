package persistence.dao;

import model.entity.Marca;

public class MarcaDAO extends GenericDAO<Marca> {

	private static final long serialVersionUID = 1L;

	public MarcaDAO() {
		super(Marca.class);
	}

	public void delete(Marca marca) {
		super.delete(marca.getId(), Marca.class);
	}

}