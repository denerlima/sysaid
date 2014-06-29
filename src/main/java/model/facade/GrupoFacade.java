package model.facade;

import java.io.Serializable;
import java.util.List;

import model.entity.Grupo;
import persistence.dao.GrupoDAO;

public class GrupoFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private GrupoDAO grupoDAO = new GrupoDAO();

	public void createGrupo(Grupo grupo) {
		grupoDAO.beginTransaction();
		grupoDAO.save(grupo);
		grupoDAO.commitAndCloseTransaction();
	}

	public void updateGrupo(Grupo grupo) {
		grupoDAO.beginTransaction();
		Grupo persistedGrupo = grupoDAO.find(grupo.getId());
		persistedGrupo.setDescricao(grupo.getDescricao());
		persistedGrupo.setGrupopai(grupo.getGrupopai());
		grupoDAO.update(persistedGrupo);
		grupoDAO.commitAndCloseTransaction();
	}

	public Grupo findGrupo(int grupoId) {
		grupoDAO.beginTransaction();
		Grupo grupo = grupoDAO.find(grupoId);
		grupoDAO.closeTransaction();
		return grupo;
	}

	public List<Grupo> listAll() {
		grupoDAO.beginTransaction();
		List<Grupo> result = grupoDAO.findAll();
		grupoDAO.closeTransaction();
		return result;
	}

	public void deleteGrupo(Grupo grupo) {
		grupoDAO.beginTransaction();
		Grupo persistedGrupo = grupoDAO.findReferenceOnly(grupo.getId());
		grupoDAO.delete(persistedGrupo);
		grupoDAO.commitAndCloseTransaction();
	}
}