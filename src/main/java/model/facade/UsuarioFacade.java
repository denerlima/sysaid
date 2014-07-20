package model.facade;

import java.io.Serializable;

import model.entity.Usuario;
import persistence.dao.GenericDAO;
import persistence.dao.UsuarioDAO;

public class UsuarioFacade extends GenericFacade<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	public GenericDAO<Usuario> getDAO() {
		return usuarioDAO;
	}

}