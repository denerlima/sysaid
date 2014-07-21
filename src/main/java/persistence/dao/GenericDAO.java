package persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

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
		return getEntityManager().find(entityClass, entityID);
	}

	public T findReferenceOnly(int entityID) {
		return getEntityManager().getReference(entityClass, entityID);
	}

	// Using the unchecked because JPA does not have a
	// getEntityManager().getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
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
		return em;
	}
	
}
