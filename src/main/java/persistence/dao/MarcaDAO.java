package persistence.dao;

import javax.inject.Named;

import model.entity.Marca;

@Named
public class MarcaDAO extends GenericDAO<Marca> {

	private static final long serialVersionUID = 1L;

	public MarcaDAO() {
		super(Marca.class);
	}

	public void delete(Marca marca) {
		marca.setAtivo(0);
		super.update(marca);
	}

}