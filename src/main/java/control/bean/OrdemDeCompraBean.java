package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import model.entity.Material;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
import model.entity.Usuario;
import model.facade.FornecedorFacade;
import model.facade.MaterialFacade;
import model.facade.OrdemDeCompraFacade;
import model.facade.UsuarioFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class OrdemDeCompraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemDeCompra ordemDeCompra;	
	private List<OrdemDeCompra> ordensDeCompra;
	private List<OrdemDeCompraMaterial> ordensDeComprasMateriais;
	
	@Inject
	private OrdemDeCompraFacade ordemDeCompraFacade;
	
	@Inject
	private UsuarioFacade usuarioFacade;
	
	@Inject
	private FornecedorFacade fornecedorFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	private Material material;
	private List<Material> materiais;
	private List<Fornecedor> contratados;

	public String novo() {
		return "/ordemDeCompra/ordemDeCompraEdit.xhtml?faces-redirect=true";
	}
	
	public String edit(Integer id) {
		return "/ordemDeCompra/ordemDeCompraEdit.xhtml?faces-redirect=true&id="+id;
	}
	
	public void initLoad(Integer id) {
		if(ordemDeCompra != null) {
			return;
		}
		if(id == null || id == 0) {
			ordemDeCompra = new OrdemDeCompra();
			ordemDeCompra.setMateriais(new ArrayList<OrdemDeCompraMaterial>());
		} else {
			ordemDeCompra = getOrdemDeCompraFacade().find(id);
		}
	}
	
	public OrdemDeCompraFacade getOrdemDeCompraFacade() {
		return ordemDeCompraFacade;
	}

	public OrdemDeCompra getOrdemDeCompra() {
		if (ordemDeCompra == null) {
			ordemDeCompra = new OrdemDeCompra();
		}
		return ordemDeCompra;
	}
	
	public List<Usuario> getAutorizadores(){
		return usuarioFacade.listAll();
	}
	
	public List<Fornecedor> getAllContratados(){
		if(contratados == null) {
			contratados = fornecedorFacade.listAll();
		}
		return contratados;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}

	public String createOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().create(ordemDeCompra);
			displayInfoMessageToUser("Criado com Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public String updateOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().update(ordemDeCompra);
			displayInfoMessageToUser("Alterado com  Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public void deleteOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().delete(ordemDeCompra);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadOrdensDeCompra();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<OrdemDeCompra> getAllOrdensDeCompra() {
		if (ordensDeCompra == null) {
			loadOrdensDeCompra();
		}
		return ordensDeCompra;
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
	
	public List<OrdemDeCompraMaterial> pesquisarOrdemCompraByFilter() {
		try {			
			ordensDeComprasMateriais = getOrdemDeCompraFacade().listMateriaisOrdensCompras(ordemDeCompra , material);			
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel achar nennhuma Ordem de Compra. ERRO");
			e.printStackTrace();
		}
		return ordensDeComprasMateriais;
	}
	
	private void loadOrdensDeCompra() {
		ordensDeCompra = getOrdemDeCompraFacade().listAll();
	}
	
	public List<OrdemDeCompra> getOrdensDeCompra() {
		if (ordensDeCompra == null) {
			ordensDeCompra = new ArrayList<OrdemDeCompra>();
		}
		return ordensDeCompra;
	}

	public void resetOrdemDeCompra() {
		ordemDeCompra = new OrdemDeCompra();
	}
	
	public void addMaterial() {
		if(material == null) {
			displayErrorMessageToUser("Campo Material Obrigatório");
			return;
		}
		OrdemDeCompraMaterial ordemMat = new OrdemDeCompraMaterial();
		ordemMat.setOrdemDeCompra(ordemDeCompra);
		ordemMat.setMaterial(material);
		ordemDeCompra.getMateriais().add(ordemMat);
		this.material = new Material();
	}
	
	public void removerMaterial(OrdemDeCompraMaterial material) {
		ordemDeCompra.getMateriais().remove(material);
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

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
	
	public List<OrdemDeCompraMaterial> getOrdensDeComprasMateriais() {
		return ordensDeComprasMateriais;
	}

	public void setOrdensDeComprasMateriais(List<OrdemDeCompraMaterial> ordensDeComprasMateriais) {
		this.ordensDeComprasMateriais = ordensDeComprasMateriais;
	}

	public boolean isManaged() {
		return ordemDeCompra != null && ordemDeCompra.getId() != null;
	}
	
}
