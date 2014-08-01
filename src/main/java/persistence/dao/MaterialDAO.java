package persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.Query;

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
    
	@SuppressWarnings("unchecked")
	 public List<Material> findListMaterialByMaterial(String material){
        Query query = getEntityManager().createQuery("From Material mat WHERE ativo = 1 and UPPER(mat.material) like UPPER(:material) order by mat.id ASC");
        query.setParameter("material", "%"+material+"%");
        return query.getResultList();
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

	@SuppressWarnings("unchecked")
	public List<Material> listMateriais(Material material) {
		List<Material> lista = new ArrayList<Material>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("Select m FROM Material m  WHERE m.ativo = 1");
			if (material.getMaterial() != null) {
				sql.append(" AND m.material LIKE :material");
			}			
			if (material.getTipoMaterial() != null) {
				sql.append(" AND m.material.tipoMaterial.id = :idTipoMat");
			}
			
			sql.append(" ORDER BY m.material");
			Query query = getEntityManager().createQuery(sql.toString());
			
			if (material.getMaterial() != null) {
				query.setParameter("material", "%"+material.getMaterial()+"%");	}
			
			if (material.getTipoMaterial() != null) {
				query.setParameter("idTipoMat", material.getTipoMaterial().getId());
			}
			
			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return lista;
		
	}


}
