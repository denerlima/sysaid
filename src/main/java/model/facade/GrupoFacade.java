package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Grupo;
import persistence.dao.GenericDAO;
import persistence.dao.GrupoDAO;

@Named
public class GrupoFacade extends GenericFacade<Grupo> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoDAO grupoDAO;

	@Override
	public GenericDAO<Grupo> getDAO() {
		return grupoDAO;
	}
	
	public List<Grupo> listGrupoRoot() {
		return grupoDAO.listGrupoRoot();
	}

	public List<Grupo> listSubGrupo(Grupo g){
		return grupoDAO.listSubGrupo(g);
	}
	
}