package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.MaoDeObra;
import model.entity.Material;
import model.entity.OrdemServico;
import model.entity.OrdemServicoMaoDeObra;
import model.entity.OrdemServicoMaterial;
import model.entity.OrdemServicoMaterialHistorico;
import model.facade.MaoDeObraFacade;
import model.facade.MaterialFacade;
import model.facade.OrdemServicoFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class OrdemServicoBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;	
	private List<OrdemServico> ordensServicos;
	private List<OrdemServicoMaterialHistorico> pendencias;
	
	@Inject
	private OrdemServicoFacade ordemServicoFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	@Inject
	private MaoDeObraFacade maoDeObraFacade;
	
	private Material material;
	private List<Material> materiais;
	
	private MaoDeObra maoDeObra;
	private List<MaoDeObra> maosDeObras;
	
	public OrdemServicoFacade getOrdemServicoFacade() {
		return ordemServicoFacade;
	}

	public OrdemServico getOrdemServico() {
		if (ordemServico == null) {
			ordemServico = new OrdemServico();
		}
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public String redirectAddMaterial() {
		return "/ordemServico/osMaterialEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectAddMaoDeObra() {
		return "/ordemServico/osMaoDeObraEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectPendencia() {
		return "/ordemServico/osPendenciaEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectDevolucao() {
		return "/ordemServico/osDevolucaoEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectMaterialList() {
		return "/ordemServico/osMaterialList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectMaoDeObraList() {
		return "/ordemServico/osMaoDeObraList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	
	
	public void initLoad(Integer id) {
		if(ordemServico != null) {
			return;
		}
		if(id == null || id == 0) {
			throw new RuntimeException("Parametro OS não encontrado");
		} else {
			ordemServico = getOrdemServicoFacade().findOrCreate(id);
		}
	}
	
	public void initLoadPendencias(Integer id) {
		if(ordemServico != null) {
			return;
		}
		initLoad(id);
		pendencias = new ArrayList<OrdemServicoMaterialHistorico>();
		for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
			if(osm.getQuantidadePendente().longValue() > 0) {
				OrdemServicoMaterialHistorico osmh = new OrdemServicoMaterialHistorico();
				osmh.setOrdemServicoMaterial(osm);
				osmh.setData(new Date());
				osmh.setTipo(1);
				osmh.setQuantidadeAnterior(osm.getQuantidadePendente());
				osm.getBaixasPendencias().add(osmh);
				pendencias.add(osmh);
			}
		}
	}
	
	
	public void createOrdemServico() {
		try {
			getOrdemServicoFacade().create(ordemServico);
			displayInfoMessageToUser("Criado com Sucesso");
			loadOrdensServicos();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteOrdemServico() {
		try {
			getOrdemServicoFacade().delete(ordemServico);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadOrdensServicos();
			resetOrdemServico();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public String updateMateriais() {
		try {
			getOrdemServicoFacade().update(ordemServico);
			displayInfoMessageToUser("Operação realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateMaosDeObras() {
		try {
			getOrdemServicoFacade().update(ordemServico);
			displayInfoMessageToUser("Operação realizada com sucesso");
			return redirectMaoDeObraList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updatePendencias() {
		try {
			getOrdemServicoFacade().update(ordemServico);
			displayInfoMessageToUser("Baixa de Pendências realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel baixar pendências. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OrdemServico> getAllOrdensServicos() {
		if (ordensServicos == null) {
			loadOrdensServicos();
		}

		return ordensServicos;
	}
	
	private void loadOrdensServicos() {
		ordensServicos = getOrdemServicoFacade().listAll();
	}

	public void resetOrdemServico() {
		ordemServico = new OrdemServico();
	}		

	public boolean isManaged() {
		return ordemServico != null && ordemServico.getId() != null;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public List<Material> completeMaterial(String name) {
		List<Material> queryResult = new ArrayList<Material>();
		if (materiais == null) {
			materiais = materialFacade.listAll();
		}
		for (Material mat : materiais) {
			if (mat.getMaterial().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(mat);
			}
		}
		return queryResult;
	}
	
	public void addMaterial() {
		if(material == null) {
			displayErrorMessageToUser("Campo Material Obrigatório");
			return;
		}
		OrdemServicoMaterial osMat = new OrdemServicoMaterial();
		osMat.setOrdemServico(ordemServico);
		osMat.setMaterial(material);
		ordemServico.getMateriais().add(osMat);
		this.material = new Material();
	}
	
	public void removerMaterial(OrdemServicoMaterial osm) {
		ordemServico.getMateriais().remove(osm); 
	}
	
	public List<MaoDeObra> completeMaoDeObra(String name) {
		List<MaoDeObra> queryResult = new ArrayList<MaoDeObra>();
		if (maosDeObras == null) {
			maosDeObras = maoDeObraFacade.listAll();
		}
		for (MaoDeObra mao : maosDeObras) {
			if (mao.getDescricao().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(mao);
			}
		}
		return queryResult;
	}
	
	public MaoDeObra getMaoDeObra() {
		return maoDeObra;
	}

	public void setMaoDeObra(MaoDeObra maoDeObra) {
		this.maoDeObra = maoDeObra;
	}

	public List<MaoDeObra> getMaosDeObras() {
		return maosDeObras;
	}

	public void addMaoDeObra() {
		if(maoDeObra == null) {
			displayErrorMessageToUser("Campo Mão de Obra Obrigatório");
			return;
		}
		OrdemServicoMaoDeObra osMaoDeObra = new OrdemServicoMaoDeObra();
		osMaoDeObra.setOrdemServico(ordemServico);
		osMaoDeObra.setMaoDeObra(maoDeObra);
		ordemServico.getMaosDeObras().add(osMaoDeObra);
		this.maoDeObra = new MaoDeObra();
	}
	
	public void removerMaoDeObra(OrdemServicoMaoDeObra osmo) {
		ordemServico.getMaosDeObras().remove(osmo); 
	}

	public List<OrdemServicoMaterialHistorico> getPendencias() {
		return pendencias;
	}

	public void setPendencias(List<OrdemServicoMaterialHistorico> pendencias) {
		this.pendencias = pendencias;
	}
	
}
