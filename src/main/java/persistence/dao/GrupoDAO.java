package persistence.dao;

import model.entity.Grupo;

public class GrupoDAO extends GenericDAO<Grupo> {

	private static final long serialVersionUID = 1L;

	public GrupoDAO() {
		super(Grupo.class);
	}

	public void delete(Grupo grupo) {
		super.delete(grupo.getId(), Grupo.class);
	}

}