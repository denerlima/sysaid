package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class HibernateUtil {
	 
    @SuppressWarnings("unchecked")
	public static <T> T unproxy(T entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof HibernateProxy) {
            Hibernate.initialize(entity);
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
        return entity;
    }
    
    public static <T> List<T> unproxy(List<T> entities) {
        List<T> newList = new ArrayList<T>();
    	if (entities != null) {
    		for(T entity : entities) {
    	        newList.add(unproxy(entity));
            }
    	}
        return newList;
    }
    
}
