package persistence.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	public List<Grupo> listGrupoRoot(){
		String sql = "From Grupo g WHERE ativo = 1 and g.grupoPai is null order by g.descricao ASC";
		Query query = getEntityManager().createQuery(sql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> listSubGrupo(Grupo g){
		String sql = "From Grupo g WHERE ativo = 1 and g.grupoPai.id = :gId order by g.descricao ASC";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("gId", g.getId());
		return query.getResultList();
	}
	
}