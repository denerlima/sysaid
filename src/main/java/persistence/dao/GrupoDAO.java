package persistence.dao;

import javax.inject.Named;

import model.entity.Grupo;

@Named
public class GrupoDAO extends GenericDAO<Grupo> {

	private static final long serialVersionUID = 1L;

	public GrupoDAO() {
		super(Grupo.class);
	}

	public void delete(Grupo grupo) {
		grupo.setAtivo(0);
		super.update(grupo);
	}

}