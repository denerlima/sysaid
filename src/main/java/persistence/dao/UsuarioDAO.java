package persistence.dao;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.entity.Usuario;

@Named
public class UsuarioDAO extends GenericDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(Usuario.class);
	}

	@Override
	public Predicate clausulaWhere(CriteriaBuilder cb, Root<Usuario> rootCriteria) {
		return null;
	}
	
	public Usuario find(String userName) {
       Query query = getEntityManager().createQuery("From Usuario user WHERE UPPER(user.userName) = UPPER(:userName)");
       query.setParameter("userName", userName);
       return (Usuario) query.getSingleResult();
   }
	
}