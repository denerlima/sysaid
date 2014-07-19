package model.facade;

import java.io.Serializable;

import model.entity.Grupo;
import persistence.dao.GenericDAO;
import persistence.dao.GrupoDAO;

public class GrupoFacade extends GenericFacade<Grupo> implements Serializable {
	private static final long serialVersionUID = 1L;

	private GrupoDAO grupoDAO = new GrupoDAO();

	@Override
	public GenericDAO<Grupo> getDAO() {
		return grupoDAO;
	}

}