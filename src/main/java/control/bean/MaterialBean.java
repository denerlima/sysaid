package control.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import util.Component;
import util.FaceletRenderer;
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
	private Boolean isValoresPositivos = true;
	
	private TreeNode root;
	private TreeNode selectedNode;
	
	public MaterialFacade getMaterialFacade() {
		return materialFacade;
	}

	public Material getMaterial() {
		if (material == null) {
			material = new Material();
		}
		return material;
	}
	
	public List<Material> getMateriais() {
		if (materiais == null) {
			materiais = new ArrayList<Material>();
		}
		return materiais;
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
			material.setEstoqueCalculado(new BigDecimal(0));
			material.setEstoque(new BigDecimal(0));
			material.setQtdSolicitada(new BigDecimal(0));
			material.setAjuste(new BigDecimal(0));
			material.setEstoqueInformado(new BigDecimal(0));
		} else {
			material = getMaterialFacade().find(id);
		}
		initRoot();
		//root.getChildren().get(0).setSelected(true);
	}
	
	public String createMaterial() {
		try {			
			getMaterialFacade().create(material);
			displayInfoMessageToUser("Criado com Sucesso");
			return "/material/materialList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateMaterial() {
		try {
			getMaterialFacade().update(material);
			displayInfoMessageToUser("Alterado com  Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel alterar. ERRO");
			e.printStackTrace();
		}
		return "/material/materialList.xhtml?faces-redirect=true";
	}
	
	public List<Material> pesquisarListaMateriaisbyNome() {
		try {			
			materiais = getMaterialFacade().pesquisarListaMateriaisbyNomeMaterial(material.getMaterial());			
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel achar nennhum material. ERRO");
			e.printStackTrace();
		}
		return materiais;
	}
	
	public List<Material> pesquisarMaterialByFilter() {
		try {
			materiais = getMaterialFacade().pesquisarMaterialByFilter(material);
			
			if (isValoresPositivos) {				
				List<Material> result = new ArrayList<Material>(); 
				for (Material material : materiais) {
					if (material.getSugestaoCompra().compareTo(new BigDecimal(0)) > 0) {
						result.add(material);						
					}					
				}
				materiais = result;					
			}
					
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel achar nennhum material. ERRO");
			e.printStackTrace();
		}
		return materiais;
	}

	public String imprimirEstoquePDF() {
		FaceletRenderer renderer = new FaceletRenderer(FacesContext.getCurrentInstance());
		renderer.renderViewPDF("/estoque/estoqueRelatorioPDF.xhtml");
		return null;
	}
	
	public String imprimirSugestaoCompraPDF() {
		FaceletRenderer renderer = new FaceletRenderer(FacesContext.getCurrentInstance());
		renderer.renderViewPDF("/estoque/sugestaoCompraRelatorioPDF.xhtml");
		return null;
	}
	
	public void deleteMaterial() {
		try {
			getMaterialFacade().delete(material);
			displayInfoMessageToUser("Exclu’do com Sucesso");
			loadMateriais();
			resetMaterial();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
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

	public TreeNode getRoot() {
		return root;
	}
	
	public void initRoot() {
    	root = new DefaultTreeNode("Grupos", null);
    	GrupoFacade grupoFacade = (GrupoFacade) Component.getInstance(GrupoFacade.class);
    	for(Grupo g : grupoFacade.listGrupoRoot()) {
    		TreeNode node = new DefaultTreeNode(g, root);
    		recursiveNoteSelectedAndExpanded(node, g);
    		recursiveNode(node, g);
    	}
	}
	
	public void recursiveNode(TreeNode root, Grupo g) {
		GrupoFacade grupoFacade = (GrupoFacade) Component.getInstance(GrupoFacade.class);
		for(Grupo subGrupo : grupoFacade.listSubGrupo(g)) {
			TreeNode node = new DefaultTreeNode(subGrupo, root);
			recursiveNoteSelectedAndExpanded(node, subGrupo);
    		recursiveNode(node, subGrupo);
		}
	}
	
	public void recursiveNoteSelectedAndExpanded(TreeNode node, Grupo g) {
		if(material.getGrupo() != null && material.getGrupo().getId().intValue() == g.getId().intValue()) {
			node.setSelected(true);
			TreeNode parent = node.getParent();
			while(!parent.getData().equals("Grupos")) {
	        	parent.setExpanded(true);
	        	parent = parent.getParent();
	        }
		}
	}
	
	public Boolean getIsValoresPositivos() {
		return isValoresPositivos;
	}

	public void setIsValoresPositivos(Boolean isValoresPositivos) {
		this.isValoresPositivos = isValoresPositivos;
	}

	public TreeNode getSelectedNode() {
        return selectedNode;
    }
 
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
        if(selectedNode != null) {
        	material.setGrupo((Grupo) selectedNode.getData());
        }
    }

}
