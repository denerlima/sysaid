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
			if (inv.getId() != null) {
				sql.append(" AND inv.id = :id");
			}
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
				sql.append(" AND invm.inventariante.userName = :inventariante");
			}
			if (inv.getAprovador() != null) {
				sql.append(" AND invm.aprovador.userName = :aprovador");
			}
			if (mat.getAplicacao() != null) {
				sql.append(" AND invm.material.aplicacao.id = :idAplicacao");
			}
			
			sql.append(" ORDER BY inv.id");

			Query query = getEntityManager().createQuery(sql.toString());

			if (inv.getId() != null) {
				query.setParameter("id", inv.getId());
			}
			if (mat.getMaterial() != null) {
				query.setParameter("idMat", mat.getId());
			}
			if (mat.getTipoMaterial() != null) {
				query.setParameter("idTipoMat", mat.getTipoMaterial().getId());
			}
			if (inv.getAtendente() != null) {
				query.setParameter("inventariante", inv.getAtendente().getUserName());
			}
			if (inv.getAprovador() != null) {
				query.setParameter("aprovador", inv.getAprovador().getUserName());
			}	
			if (mat.getAplicacao() != null) {
				query.setParameter("idAplicacao", mat.getAplicacao().getId());
			}
			lista = query.getResultList(); 
		
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return lista;
		
	}

}