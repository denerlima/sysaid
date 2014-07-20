package persistence.dao;

import model.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

}