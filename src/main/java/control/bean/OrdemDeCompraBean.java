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
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public String updateOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().update(ordemDeCompra);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public void deleteOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().delete(ordemDeCompra);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
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
	
	private void loadOrdensDeCompra() {
		ordensDeCompra = getOrdemDeCompraFacade().listAll();
	}

	public void resetOrdemDeCompra() {
		ordemDeCompra = new OrdemDeCompra();
	}
	
	public void addMaterial() {
		if (ordemDeCompra.getMateriais() == null) {
			ordemDeCompra.setMateriais(new ArrayList<OrdemDeCompraMaterial>());
		}
		OrdemDeCompraMaterial ordemMat = new OrdemDeCompraMaterial();
		ordemMat.setOrdemDeCompra(ordemDeCompra);
		ordemMat.setMaterial(material);
		ordemDeCompra.getMateriais().add(ordemMat);
		this.material = new Material();
	}
	
	public void removerMaterial(Material material) {
		ordemDeCompra.getMateriais().remove(material);
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

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
		
	public boolean isManaged() {
		return ordemDeCompra != null && ordemDeCompra.getId() != null;
	}
	
}
