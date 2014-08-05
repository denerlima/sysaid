package model.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import model.entity.OrdemServico;
import model.entity.OrdemServicoMaterial;
import model.entity.OrdemServicoMaterialHistorico;
import persistence.dao.GenericDAO;
import persistence.dao.OrdemServicoDAO;

@Named
public class OrdemServicoFacade extends GenericFacade<OrdemServico> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemServicoDAO ordemServicoDAO;
	
	@Inject
	private MaterialFacade materialFacade;
	
	@Override
	public GenericDAO<OrdemServico> getDAO() {
		return ordemServicoDAO;
	}
	
	public OrdemServico findOrCreate(Integer id) {
		try {
			return getDAO().find(id);
		} catch (NoResultException e) {
			OrdemServico os = new OrdemServico();
			os.setId(id);
			getDAO().save(os);
			return os;
		}
	}
	
	public void updateMateriais(OrdemServico ordemServico) throws Exception {
		try {
			getDAO().beginTransaction();
			for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
				if(osm.getId() == null) {
					materialFacade.retirarEstoque(osm.getMaterial(), osm.getQuantidadeEntregue());
				}
			}
			getDAO().update(ordemServico);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
	public void updatePendencias(OrdemServico ordemServico, List<OrdemServicoMaterialHistorico> pendencias) throws Exception {
		try {
			getDAO().beginTransaction();
			for(OrdemServicoMaterialHistorico osmh : pendencias) {
				materialFacade.retirarEstoque(osmh.getOrdemServicoMaterial().getMaterial(), osmh.getQuantidade());
				osmh.getOrdemServicoMaterial().setQuantidadeEntregue(osmh.getOrdemServicoMaterial().getQuantidadeEntregue().add(osmh.getQuantidade()));
			}
			getDAO().update(ordemServico);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	} 
	
}
