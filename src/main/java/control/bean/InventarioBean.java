package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Inventario;
import model.entity.InventarioMaterial;
import model.entity.Material;
import model.entity.Usuario;
import model.facade.InventarioFacade;
import model.facade.MaterialFacade;
import model.facade.UsuarioFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class InventarioBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 5226648353758700812L;

	private Inventario inventario;	
	private List<Inventario> inventarios;
	
	@Inject
	private InventarioFacade inventarioFacade;
	
	@Inject
	private UsuarioFacade usuarioFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	private Material material;
	private List<Material> materiais;

	@PostConstruct 
	public void init() { 
		System.out.println("Bean Instanciado!"); 
	}
	
	public String novo() {
		return "/inventario/inventarioEdit.xhtml?faces-redirect=true";
	}
	
	public String edit(Integer id) {
		return "/inventario/inventarioEdit.xhtml?faces-redirect=true&id="+id;
	}
	
	public void initLoad(Integer id) {
		if(inventario != null) {
			return;
		}
		if(id == null || id == 0) {
			inventario = new Inventario();
			inventario.setMateriais(new ArrayList<InventarioMaterial>());
		} else {
			inventario = getInventarioFacade().find(id);
		}
	}
	
	public String createInventario() {
		try {
			getInventarioFacade().create(inventario);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return "/inventario/inventarioList.xhtml?faces-redirect=true";
	}
	
	public List<Material> completeMaterial(String name) {
		List<Material> queryResult = new ArrayList<Material>();

		if (materiais == null) {
			materiais = materialFacade.listAll();
		}

		//allDogs.removeAll(personWithDogs.getDogs());

		for (Material mat : materiais) {
			if (mat.getMaterial().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(mat);
			}
		}

		return queryResult;
	}
	
	public String updateInventario() {
		try {
			getInventarioFacade().update(inventario);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/inventario/inventarioList.xhtml?faces-redirect=true";
	}
	
	public void deleteInventario() {
		try {
			getInventarioFacade().delete(inventario);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}
	

	public List<Inventario> getAllInventarios() {
		if (inventarios == null) {
			loadInventarios();
		}

		return inventarios;
	}

	private void loadInventarios() {
		inventarios = getInventarioFacade().listAll();
	}

	public void resetInventario() {
		inventario = new Inventario();
	}
	
	public void addMaterial() {
		if (inventario.getMateriais() == null) {
			inventario.setMateriais(new ArrayList<InventarioMaterial>());
		}
		InventarioMaterial invMat = new InventarioMaterial();
		invMat.setInventario(inventario);
		invMat.setMaterial(material);
		invMat.setQuantidadeEstoque(material.getEstoqueCalculado());
		inventario.getMateriais().add(invMat);
		this.material = new Material();
	}
	
	public void removerMaterial(Material material) {
		inventario.getMateriais().remove(material);
	}

	public InventarioFacade getInventarioFacade() {
		return inventarioFacade;
	}

	public Inventario getInventario() {
		if (inventario == null) {
			inventario = new Inventario();
		}
		return inventario;
	}
	
	public List<Usuario> getAtendentes(){
		return usuarioFacade.listAll();
	}
	

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
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
		return inventario != null && inventario.getId() != null;
	}
	
}
