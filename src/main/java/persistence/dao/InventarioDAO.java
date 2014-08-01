package persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import model.entity.Inventario;
import model.entity.InventarioMaterial;
import model.entity.Material;
import util.DataUtil;

@Named
public class InventarioDAO extends GenericDAO<Inventario> {

	private static final long serialVersionUID = 1L;

	public InventarioDAO() {
		super(Inventario.class);
	}

	public void delete(Inventario inventario) {
		inventario.setAtivo(0);
		super.update(inventario);
	}

	@SuppressWarnings("unchecked")
	public List<InventarioMaterial> listMateriaisInventarios(Inventario inv, Material mat, Date dtInicial, Date dtFinal) {	
		List<InventarioMaterial> lista = new ArrayList<InventarioMaterial>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("Select invm FROM InventarioMaterial invm left join invm.inventario inv WHERE inv.ativo = 1");
			if (dtInicial != null) {
				sql.append(" AND inv.dataInventario >= to_date('"+ DataUtil.formataData(dtInicial)+ "','dd/MM/yy')");
			} 
			if (dtFinal != null) {
				sql.append(" AND inv.dataInventario <= to_date('"+ DataUtil.formataData(dtFinal)+ "','dd/MM/yy')");
			}
			if (mat.getMaterial() != null) {
				sql.append(" AND invm.material.id = :idMat");
			}
			if (mat.getTipoMaterial() != null) {
				sql.append(" AND invm.material.tipoMaterial.id = :idTipoMat");
			}
			if (inv.getAtendente() != null) {
				sql.append(" AND inv.atendente.id = :atendente");
			}
			
			sql.append(" ORDER BY inv.id");

			Query query = getEntityManager().createQuery(sql.toString());

			
			if (mat.getMaterial() != null) {
				query.setParameter("idMat", mat.getId());
			}
			if (mat.getTipoMaterial() != null) {
				query.setParameter("idTipoMat", mat.getTipoMaterial().getId());
			}
			if (inv.getAtendente() != null) {
				query.setParameter("atendente", inv.getAtendente().getId());
			}

			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return lista;
		
	}

}