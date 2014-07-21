package model.facade;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Usuario;
import persistence.dao.GenericDAO;
import persistence.dao.UsuarioDAO;

@Named
public class UsuarioFacade extends GenericFacade<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public GenericDAO<Usuario> getDAO() {
		return usuarioDAO;
	}

}
