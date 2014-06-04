package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.entity.Aplicacao;
import model.entity.Material;
import model.entity.TipoMaterial;
import model.entity.UnidadeMedida;
import model.facade.AplicacaoFacade;
import model.facade.MaterialFacade;
import model.facade.TipoMaterialFacade;
import model.facade.UnidadeMedidaFacade;

@ViewScoped
@ManagedBean
public class MaterialBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Material material;	
	private List<Material> materiais;
	private MaterialFacade materialFacade;
	private List<Aplicacao> listaAplicacoes;
	private Aplicacao selectedAplicacao;
	private TipoMaterial selectedTipoMaterial;
	private UnidadeMedida selectedUnidadeMedida;
	
	public MaterialBean(){
		selectedAplicacao = new Aplicacao();
		selectedTipoMaterial = new TipoMaterial();
		selectedUnidadeMedida = new UnidadeMedida();
	}
	
	public MaterialFacade getMaterialFacade() {
		if (materialFacade == null) {
			materialFacade = new MaterialFacade();
		}
		return materialFacade;
	}

	public Material getMaterial() {
		if (material == null) {
			material = new Material();
		}
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}	
	
	public List<Aplicacao> getListaAplicacoes() {
		if (listaAplicacoes == null) {
			listaAplicacoes = new ArrayList<Aplicacao>();
		}
		return listaAplicacoes;
	}	

	public void createMaterial() {
		try {			
			material.setAplicacao(selectedAplicacao);
			material.setTipoMaterial(selectedTipoMaterial);
			material.setUnidadeMedida(selectedUnidadeMedida);
			getMaterialFacade().createMaterial(material);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateMaterial() {
		try {
			material.setAplicacao(selectedAplicacao);
			material.setTipoMaterial(selectedTipoMaterial);
			material.setUnidadeMedida(selectedUnidadeMedida);
			getMaterialFacade().updateMaterial(material);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteMaterial() {
		try {
			getMaterialFacade().deleteMaterial(material);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<Material> getAllMateriais() {
		if (materiais == null) {
			loadMateriais();
		}
		return materiais;
	}

	private void loadMateriais() {
		materiais = getMaterialFacade().listAllMateriais();
	}
	
	public List<SelectItem> getSelectItensAplicacoes(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		AplicacaoFacade aplicacaoFacade = new AplicacaoFacade();
		for(Aplicacao aplicacao : aplicacaoFacade.listAll()){
			lista.add(new SelectItem(aplicacao.getId(), aplicacao.getDescricao()));
		}
		return lista;
	}
	
	public List<SelectItem> getSelectItensTipoMaterial(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		TipoMaterialFacade tipoMaterialFacade = new TipoMaterialFacade();
		for(TipoMaterial tipoMaterial : tipoMaterialFacade.listAll()){
			lista.add(new SelectItem(tipoMaterial.getId(), tipoMaterial.getDescricao()));
		}
		return lista;
	}
	
	public List<SelectItem> getSelectItensUnidadeMedida(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		UnidadeMedidaFacade unidadeMedidaFacade = new UnidadeMedidaFacade();
		for(UnidadeMedida unidadeMedida : unidadeMedidaFacade.listAll()){
			lista.add(new SelectItem(unidadeMedida.getId(), unidadeMedida.getUnEntrada()));
		}
		return lista;
	}
	
	public Aplicacao getSelectedAplicacao() {
		return selectedAplicacao;
	}

	public void setSelectedAplicacao(Aplicacao selectedAplicacao) {
		this.selectedAplicacao = selectedAplicacao;
	}	
	
	public TipoMaterial getSelectedTipoMaterial() {
		return selectedTipoMaterial;
	}

	public void setSelectedTipoMaterial(TipoMaterial selectedTipoMaterial) {
		this.selectedTipoMaterial = selectedTipoMaterial;
	}
	
	public UnidadeMedida getSelectedUnidadeMedida() {
		return selectedUnidadeMedida;
	}

	public void setSelectedUnidadeMedida(UnidadeMedida selectedUnidadeMedida) {
		this.selectedUnidadeMedida = selectedUnidadeMedida;
	}

	public void resetMaterial() {
		material = new Material();
	}

}