package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.entity.Aplicacao;
import model.entity.Grupo;
import model.entity.Marca;
import model.entity.Material;
import model.entity.TipoMaterial;
import model.entity.UnidadeMedida;
import model.facade.AplicacaoFacade;
import model.facade.GrupoFacade;
import model.facade.MarcaFacade;
import model.facade.MaterialFacade;
import model.facade.TipoMaterialFacade;
import model.facade.UnidadeMedidaFacade;

@ViewScoped
@ManagedBean
public class MaterialBean extends AbstractBean implements Serializable {
	@ManagedProperty(value = InventarioBean.INJECTION_NAME)
    private InventarioBean inventarioBean;
	
	private static final long serialVersionUID = 1L;

	private Material material;	
	private List<Material> materiais;
	private MaterialFacade materialFacade;
	private List<Aplicacao> listaAplicacoes;
	private List<Marca> listaMarcas;
	private List<Grupo> listaGrupos;
	private Aplicacao selectedAplicacao;
	private Marca selectedMarca;
	private Grupo selectedGrupo;
	private TipoMaterial selectedTipoMaterial;
	private UnidadeMedida selectedUnidadeMedida;

	
	public MaterialBean(){
		selectedAplicacao = new Aplicacao();
		selectedMarca = new Marca();
		selectedGrupo = new Grupo();
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
	
	public List<Marca> getListaMarcas() {
		if (listaMarcas == null) {
			listaMarcas = new ArrayList<Marca>();
		}
		return listaMarcas;
	}
	
	public List<Grupo> getListaGrupos() {
		if (listaGrupos == null) {
			listaGrupos = new ArrayList<Grupo>();
		}
		return listaGrupos;
	}

	public void createMaterial() {
		try {			
			material.setAplicacao(selectedAplicacao);
			material.setMarca(selectedMarca);
			material.setGrupo(selectedGrupo);
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
			material.setMarca(selectedMarca);
			material.setGrupo(selectedGrupo);
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
	
	
	public List<Material> complete(String name) {
		List<Material> queryResult = new ArrayList<Material>();

		if (materiais == null) {
			materialFacade = new MaterialFacade();
			materiais = materialFacade.listAllMateriais();
		}

		//allDogs.removeAll(personWithDogs.getDogs());

		for (Material mat : materiais) {
			if (mat.getMaterial().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(mat);
			}
		}

		return queryResult;
	}
	
	
	
	public Material pesquisarMaterial() {
		try {			
			material = getMaterialFacade().findMaterialbyNomeMaterial(material.getMaterial());
			closeDialog();
			//displayInfoMessageToUser("Alterado com  Sucesso");
			//loadMateriais();
			//resetMaterial();			
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return material;
	}
	
	public List<Material> pesquisarListaMateriais() {
		try {			
			materiais = getMaterialFacade().findMateriaisByFilter(material);
			inventarioBean.getInventario().setMateriais(getAllMateriais());
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel achar nennhum material. ERRO");
			e.printStackTrace();
		}
		return materiais;
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
	
	public List<SelectItem> getSelectItensMarcas(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		MarcaFacade marcaFacade = new MarcaFacade();
		for(Marca marca : marcaFacade.listAll()){
			lista.add(new SelectItem(marca.getId(), marca.getDescricao()));
		}
		return lista;
	}
	
	public List<SelectItem> getSelectItensGrupos(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		GrupoFacade grupoFacade = new GrupoFacade();
		for(Grupo grupo : grupoFacade.listAll()){
			lista.add(new SelectItem(grupo.getId(), grupo.getDescricao()  + " - " + grupo.getGrupopai()));
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
			lista.add(new SelectItem(unidadeMedida.getId(), unidadeMedida.getUnidadeEntrada().getDescricao() + " - " + unidadeMedida.getUnSaida() ));
		}
		return lista;
	}
	
	public Aplicacao getSelectedAplicacao() {
		return selectedAplicacao;
	}

	public void setSelectedAplicacao(Aplicacao selectedAplicacao) {
		this.selectedAplicacao = selectedAplicacao;
	}
		
	public Marca getSelectedMarca() {
		return selectedMarca;
	}

	public void setSelectedMarca(Marca selectedMarca) {
		this.selectedMarca = selectedMarca;
	}

	public Grupo getSelectedGrupo() {
		return selectedGrupo;
	}

	public void setSelectedGrupo(Grupo selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
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
	
    public void setInventarioBean(InventarioBean inventarioBean) {
        this.inventarioBean = inventarioBean;
    } 

}