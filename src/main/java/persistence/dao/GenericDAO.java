package persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	//private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sysaid");
	
	@Inject
	private EntityManager em;

	private Class<T> entityClass;

	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	public void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public void flush() {
		getEntityManager().flush();
	}

	//public void joinTransaction(EntityManager newEm) {
	//	em = newEm;
	//}

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		getEntityManager().persist(entity);
	}

	public void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = getEntityManager().getReference(classe, id);
		 
        getEntityManager().remove(entityToBeRemoved);
	}
	
	public void delete(T entityClass) {
        getEntityManager().remove(entityClass);
	}

	public T update(T entity) {
		return getEntityManager().merge(entity);
	}

	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}
	
	public T find(int entityID) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> c = cb.createQuery(entityClass);
        Root<T> rootCriteria = c.from(entityClass);
        c.where(cb.equal(rootCriteria.get("id"), cb.parameter(Integer.class, "id")));
        clausulaWhere(cb, c, rootCriteria);
        TypedQuery<T> q = em.createQuery(c);
        q.setParameter("id", entityID);
        return (T) q.getSingleResult();
	}

	public T findReferenceOnly(int entityID) {
		return getEntityManager().getReference(entityClass, entityID);
	}

	public List<T> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> c = cb.createQuery(entityClass);
        Root<T> rootCriteria = c.from(entityClass);
        clausulaWhere(cb, c, rootCriteria);
        TypedQuery<T> q = em.createQuery(c);
        return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public EntityManager getEntityManager() {
		/*
		if(em == null) {
			em = emf.createEntityManager();
		} else {
			if(!em.isOpen()) {
				em = emf.createEntityManager();
			}
		}
		*/
		System.out.println(em.toString());
		return em;
	}
	
	public CriteriaQuery<T> clausulaWhere(CriteriaBuilder cb, CriteriaQuery<T> c, Root<T> rootCriteria) {
		return c.where(cb.equal(rootCriteria.get("ativo"), 1));
	}
	
}
