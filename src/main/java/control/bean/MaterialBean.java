package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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

import org.omnifaces.cdi.ViewScoped;

import util.Component;
import util.HibernateUtil;

@Named
@ViewScoped
public class MaterialBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Material material;	
	private List<Material> materiais;
	
	@Inject
	private MaterialFacade materialFacade;

	private List<Aplicacao> aplicacoes;
	private List<Marca> marcas;
	private List<Grupo> grupos;
	private List<TipoMaterial> tiposMateriais;
	private List<UnidadeMedida> unidadesMedidas;
	
	public MaterialFacade getMaterialFacade() {
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

	public String novo() {
		return "/material/materialEdit.xhtml?faces-redirect=true";
	}
	
	public String edit(Integer id) {
		return "/material/materialEdit.xhtml?faces-redirect=true&id="+id;
	}
	
	public void initLoad(Integer id) {
		if(material != null) {
			return;
		}
		if(id == null || id == 0) {
			material = new Material();
		} else {
			material = getMaterialFacade().find(id);
		}
	}
	
	public String createMaterial() {
		try {			
			getMaterialFacade().create(material);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadMateriais();
			resetMaterial();
			return "/material/materialList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateMaterial() {
		try {
			getMaterialFacade().update(material);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/material/materialList.xhtml?faces-redirect=true";
	}
	
	
	public List<Material> complete(String name) {
		List<Material> queryResult = new ArrayList<Material>();

		if (materiais == null) {
			materialFacade = new MaterialFacade();
			materiais = materialFacade.listAll();
		}

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
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return material;
	}
	
	public List<Material> pesquisarListaMateriais() {
		try {			
			materiais = getMaterialFacade().findMateriaisByFilter(material);
			//inventarioBean.getInventario().setMateriais(getAllMateriais());
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel achar nennhum material. ERRO");
			e.printStackTrace();
		}
		return materiais;
	}
	
	public void deleteMaterial() {
		try {
			getMaterialFacade().delete(material);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<Aplicacao> getAllAplicacoes(){
		if(aplicacoes == null) {
			AplicacaoFacade aplicacaoFacade = (AplicacaoFacade) Component.getInstance(AplicacaoFacade.class);
			aplicacoes = HibernateUtil.unproxy(aplicacaoFacade.listAll());
		}
		return aplicacoes;
	}
	
	public List<Marca> getAllMarcas(){
		if(marcas == null) {
			MarcaFacade marcaFacade = (MarcaFacade) Component.getInstance(MarcaFacade.class);
			marcas = HibernateUtil.unproxy(marcaFacade.listAll());
		}
		return marcas;
	}
	
	public List<Grupo> getAllGrupos(){
		if(grupos == null) {
			GrupoFacade grupoFacade = (GrupoFacade) Component.getInstance(GrupoFacade.class);
			grupos = HibernateUtil.unproxy(grupoFacade.listAll());
		}
		return grupos;
	}
	
	public List<TipoMaterial> getAllTiposMateriais(){
		if(tiposMateriais == null) {
			TipoMaterialFacade tipoMaterialFacade = (TipoMaterialFacade) Component.getInstance(TipoMaterialFacade.class);
			tiposMateriais = HibernateUtil.unproxy(tipoMaterialFacade.listAll());
		} 
		return tiposMateriais;
	}
	
	public List<UnidadeMedida> getAllUnidadesMedidas(){
		if(unidadesMedidas == null) {
			UnidadeMedidaFacade unidadeFacade = (UnidadeMedidaFacade) Component.getInstance(UnidadeMedidaFacade.class);
			unidadesMedidas = HibernateUtil.unproxy(unidadeFacade.listAll());
		}
		return unidadesMedidas;
	}
	
	public List<Material> getAllMateriais() {
		if (materiais == null) {
			loadMateriais();
		}
		return materiais;
	}

	private void loadMateriais() {
		materiais = getMaterialFacade().listAll();
	}
	
	public void resetMaterial() {
		material = new Material();
	}
	
	public boolean isManaged() {
		return material != null && material.getId() != null;
	}
	
}
