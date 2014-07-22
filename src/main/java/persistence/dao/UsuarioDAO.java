package persistence.dao;

import javax.inject.Named;

import model.entity.Usuario;

@Named
public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

}