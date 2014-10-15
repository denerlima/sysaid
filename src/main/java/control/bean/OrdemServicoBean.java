package control.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.entity.MaoDeObra;
import model.entity.Material;
import model.entity.OrdemServico;
import model.entity.OrdemServicoMaoDeObra;
import model.entity.OrdemServicoMaterial;
import model.entity.OrdemServicoMaterialHistorico;
import model.entity.UnidadeMedidaSaida;
import model.facade.MaoDeObraFacade;
import model.facade.MaterialFacade;
import model.facade.OrdemServicoFacade;

import org.omnifaces.cdi.ViewScoped;

import util.DataUtil;
import util.FaceletRenderer;

@Named
@ViewScoped
public class OrdemServicoBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;	
	private List<OrdemServico> ordensServicos;
	private List<OrdemServicoMaterial> ordemServicoMateriais;
	private List<OrdemServicoMaterialHistorico> pendencias;
	private List<OrdemServicoMaterialHistorico> devolucoes;
	private OrdemServicoMaterial ordemServicoMaterial;
	private OrdemServicoMaoDeObra ordemServicoMaoDeObra;
	
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
	
	public String redirectRealizado() {
		return "/ordemServico/osRealizadoEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectMaoDeObraList() {
		return "/ordemServico/osMaoDeObraList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectMaterialList() {
		return "/ordemServico/osMaterialList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectRealizadoList() {
		return "/ordemServico/osRealizadoList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public void initLoad(Integer id) {
		if(ordemServico != null) {
			return;
		}
		if(id == null || id == 0) {
			throw new RuntimeException("Parametro OS n‹o encontrado");
		} else {
			ordemServico = getOrdemServicoFacade().findOrCreate(id);
		}
	}
	
	public void initLoadMateriais(Integer id) {
		if(ordemServico != null) {
			return;
		}
		initLoad(id);
		ordemServicoMateriais = new ArrayList<OrdemServicoMaterial>();
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
	
	public void initLoadDevolucao(Integer id) {
		if(ordemServico != null) {
			return;
		}
		initLoad(id);
		devolucoes = new ArrayList<OrdemServicoMaterialHistorico>();
		for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
			//if(osm.getQuantidadePendente().longValue() > 0) {
				OrdemServicoMaterialHistorico osmh = new OrdemServicoMaterialHistorico();
				osmh.setOrdemServicoMaterial(osm);
				osmh.setData(new Date());
				osmh.setTipo(2);
				//osmh.setQuantidadeAnterior(osm.getQuantidadePendente());
				osm.getDevolucoes().add(osmh);
				devolucoes.add(osmh);
			//}
		}
	}
	
	public void initLoadRealizados(Integer id) {
		if(ordemServico != null) {
			return;
		}
		initLoad(id);
		for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
			if(osm.getQuantidadeUtilizada().longValue() == 0l) {
				osm.setQuantidadeUtilizada(osm.getQuantidadeRetirada());
			}
		}
	}
	
	public void createOrdemServico() {
		try {
			getOrdemServicoFacade().create(ordemServico);
			displayInfoMessageToUser("Criado com Sucesso");
			loadOrdensServicos();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
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
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteMaoDeObra() {
		try {
			getOrdemServico().getMaosDeObras().remove(getOrdemServicoMaoDeObra());
			getOrdemServicoFacade().update(ordemServico);
			displayInfoMessageToUser("Excluído com Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public String updateMateriais() {
		try {
			for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
				if(osm.getQuantidadeEntregue().longValue() > osm.getQuantidadeSolicitada().longValue()) {
					displayErrorMessageToUser("A quantidade entregue não pode ser maior que a solicitada");
					return null;
				}
			}			
			getOrdemServicoFacade().updateMateriais(ordemServico, ordemServicoMateriais);
			displayInfoMessageToUser("Operação realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
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
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updatePendencias() {
		try {
			for(OrdemServicoMaterialHistorico osmh : pendencias) {
				BigDecimal qtdePendente = osmh.getQuantidadeAnterior();
				if(osmh.getQuantidade().longValue() > qtdePendente.longValue()) {
					displayErrorMessageToUser("A quantidade a ser baixada n‹o pode ser maior que a quantidade pendente");
					return null;
				}
			}
			getOrdemServicoFacade().updatePendencias(ordemServico, pendencias);
			displayInfoMessageToUser("Baixa de Pendências realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel baixar pendências. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateDevolucoes() {
		try {
			getOrdemServicoFacade().updateDevolucoes(ordemServico, devolucoes);
			displayInfoMessageToUser("Baixa de Pendências realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel baixar pendências. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateRealizados() {
		try {
			getOrdemServicoFacade().updateRealizados(ordemServico);
			displayInfoMessageToUser("Baixa de Pendências realizada com sucesso");
			return redirectRealizadoList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel baixar pendências. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String imprimirRealizado() {
		FaceletRenderer renderer = new FaceletRenderer(FacesContext.getCurrentInstance());
		renderer.renderViewPDF("/ordemServico/osRealizadoPDF.xhtml");
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

	public OrdemServicoMaoDeObra getOrdemServicoMaoDeObra() {
		return ordemServicoMaoDeObra;
	}

	public void setOrdemServicoMaoDeObra(OrdemServicoMaoDeObra ordemServicoMaoDeObra) {
		this.ordemServicoMaoDeObra = ordemServicoMaoDeObra;
	}

	public OrdemServicoMaterial getOrdemServicoMaterial() {
		return ordemServicoMaterial;
	}

	public void setOrdemServicoMaterial(OrdemServicoMaterial ordemServicoMaterial) {
		this.ordemServicoMaterial = ordemServicoMaterial;
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
		osMat.setData(new Date());
		osMat.setOrdemServico(ordemServico);
		osMat.setMaterial(materialFacade.find(material.getId()));
		for(UnidadeMedidaSaida ums : material.getUnidadeMedida().getSaidas()) {
			if(ums.getUnidade().getId().intValue() == material.getUnidadeMedida().getUnidadeEntrada().getId().intValue()) {
				osMat.setUnidadeMedidaSaida(ums);
				break;
			}
		}
		//ordemServico.getMateriais().add(osMat);
		ordemServicoMateriais.add(osMat);
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
		osMaoDeObra.setValorUnitario(maoDeObra.getValorhora());
		osMaoDeObra.setUnidadeMedida(1);
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
	
	public void calcularPendencia(OrdemServicoMaterial osm) {
		BigDecimal qtdeEntregue = osm.getQuantidadeEntregue().multiply(osm.getUnidadeMedidaSaida().getFatorConversao());
		//if(osm.getQuantidadeEntregue().longValue() > osm.getQuantidadeSolicitada().longValue()) {
		//	displayErrorMessageToUser("A quantidade solicitada n‹o pode ser maior que a quantidade entregue");
		//	return;
		//}
		osm.setQuantidadePendente(osm.getQuantidadeSolicitada().subtract(qtdeEntregue));
	}
	
	public void calcularBaixaDePendencia(OrdemServicoMaterialHistorico osmh) {
		BigDecimal qtdePendente = osmh.getOrdemServicoMaterial().getQuantidadePendente();
		if(osmh.getQuantidade().longValue() > qtdePendente.longValue()) {
			displayErrorMessageToUser("A quantidade a ser baixada n‹o pode ser maior que a quantidade pendente");
			return;
		}
		osmh.getOrdemServicoMaterial().setQuantidadePendente(qtdePendente.subtract(osmh.getQuantidade()));
	}
	
	public void calcularMaoDeObra(OrdemServicoMaoDeObra osmo) {
		BigDecimal total = osmo.getQuantidade().multiply(osmo.getPeriodo()).multiply(osmo.getValorUnitario());
		osmo.setTotal(total);
	}
	
	public void changeUnidadeMaoDeObra(OrdemServicoMaoDeObra osmo) {
		if(osmo.getUnidadeMedida() == 1) {
			osmo.setValorUnitario(osmo.getMaoDeObra().getValorhora());
		}
		else if(osmo.getUnidadeMedida() == 2) {
			osmo.setValorUnitario(osmo.getMaoDeObra().getValordia());
		}
		calcularMaoDeObra(osmo);
	}
	
	public List<OrdemServicoMaterialHistorico> getDevolucoes() {
		return devolucoes;
	}

	public List<OrdemServicoMaterial> getOrdemServicoMateriais() {
		return ordemServicoMateriais;
	}
	
	public String getCustoTotalRealizado() {
		BigDecimal custo = new BigDecimal(0);
		for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
			custo = custo.add(osm.getPrecoTotal());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getCustoTotalMaoDeObra() {
		BigDecimal custo = new BigDecimal(0);
		for(OrdemServicoMaoDeObra osmo : ordemServico.getMaosDeObras()) {
			custo = custo.add(osmo.getTotal());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getCustoTotalOrdemDeServico() {
		BigDecimal custo = new BigDecimal(0);
		for(OrdemServicoMaterial osm : ordemServico.getMateriais()) {
			custo = custo.add(osm.getPrecoTotal());
		}
		for(OrdemServicoMaoDeObra osmo : ordemServico.getMaosDeObras()) {
			custo = custo.add(osmo.getTotal());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getDataHora() throws Exception {
		return DataUtil.converterDateParaStringDataHora(new Date());
	}
	
}
