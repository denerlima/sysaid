package model.facade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
	
	public void updateMateriais(OrdemServico ordemServico, List<OrdemServicoMaterial> materiais) throws Exception {
		try {
			getDAO().beginTransaction();
			for(OrdemServicoMaterial osm : materiais) {
				if(osm.getId() == null) {
					if(osm.getQuantidadeEntregue().longValue() > 0) {
						OrdemServicoMaterialHistorico osmh = new OrdemServicoMaterialHistorico();
						osmh.setOrdemServicoMaterial(osm);
						osmh.setData(new Date());
						osmh.setTipo(0);
						osmh.setQuantidade(osm.getQuantidadeEntregue());
						osmh.setQuantidadeAnterior(new BigDecimal(0));
						osm.getEntregas().add(osmh);
					}
					ordemServico.getMateriais().add(osm);
					materialFacade.retirarEstoque(osm.getMaterial(), osm.getQuantidadeEntregue(), osm.getUnidadeMedidaSaida());
					materialFacade.atualizaEstoqueMinimoCalculado(osm.getMaterial());
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
				materialFacade.retirarEstoque(osmh.getOrdemServicoMaterial().getMaterial(), osmh.getQuantidade(), osmh.getOrdemServicoMaterial().getUnidadeMedidaSaida());
				osmh.getOrdemServicoMaterial().setQuantidadeEntregue(osmh.getOrdemServicoMaterial().getQuantidadeEntregue().add(osmh.getQuantidade()));
				materialFacade.atualizaEstoqueMinimoCalculado(osmh.getOrdemServicoMaterial().getMaterial());

			}
			getDAO().update(ordemServico);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
	public void updateDevolucoes(OrdemServico ordemServico, List<OrdemServicoMaterialHistorico> devolucoes) throws Exception {
		try {
			getDAO().beginTransaction();
			for(OrdemServicoMaterialHistorico osmh : devolucoes) {
				if(osmh.getQuantidade().longValue() > 0) {
					materialFacade.adicionarEstoque(osmh.getOrdemServicoMaterial().getMaterial(), osmh.getQuantidade(), osmh.getOrdemServicoMaterial().getUnidadeMedidaSaida());
					osmh.getOrdemServicoMaterial().setQuantidadeDevolvida(osmh.getOrdemServicoMaterial().getQuantidadeDevolvida().add(osmh.getQuantidade()));
					materialFacade.atualizaEstoqueMinimoCalculado(osmh.getOrdemServicoMaterial().getMaterial());
				}
			}
			getDAO().update(ordemServico);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
	public void updateRealizados(OrdemServico ordemServico, List<OrdemServicoMaterialHistorico> realizados) throws Exception {
		try {
			getDAO().beginTransaction();
			for(OrdemServicoMaterialHistorico osmh : realizados) {
				osmh.getOrdemServicoMaterial().setQuantidadeUtilizada(osmh.getOrdemServicoMaterial().getQuantidadeUtilizada().add(osmh.getQuantidade()));
			}
			getDAO().update(ordemServico);
			getDAO().commit();
		} catch (Exception e) {
			getDAO().rollback();
			throw e;
		}
	}
	
}
