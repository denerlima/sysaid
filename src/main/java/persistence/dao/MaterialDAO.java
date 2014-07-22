package persistence.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import model.entity.Material;

@Named
public class MaterialDAO extends GenericDAO<Material> {

	private static final long serialVersionUID = 1L;

	public MaterialDAO() {
		super(Material.class);
	}
	
    public Material findMaterialByMaterial(String material){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("material", "%"+ material +"%"); 
        return super.findOneResult(Material.FIND_BY_NOME_MATERIAL, parameters);
    }
    
//    public List<Material> findMateriaisByFilter(Material material){
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        
//        Query query = this. em.createQuery("select p from Person p where p = :person");
//        query.setParameter("person", person);
//        return (Person) query.getSingleResult();
//    }
//        parameters.put("material", "%"+ material +"%"); 
//        return super.findOneResult(Material.FIND_BY_NOME_MATERIAL, parameters);
//    }
//    
//    private static Person findPersonByPersonObject(EntityManager em, Person person) {
//        Query query = em.createQuery("select p from Person p where p = :person");
//        query.setParameter("person", person);
//        return (Person) query.getSingleResult();
//    }

	public void delete(Material material) {
		material.setAtivo(0);
		super.update(material);
	}

}
