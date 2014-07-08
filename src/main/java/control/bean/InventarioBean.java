package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Inventario;
import model.entity.Material;
import model.facade.InventarioFacade;

@ViewScoped
@ManagedBean
public class InventarioBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{inventarioBean}";

	private Inventario inventario;	
	private List<Inventario> inventarios;
	private InventarioFacade inventarioFacade;
	private Material material;	

	
	public InventarioFacade getInventarioFacade() {
		if (inventarioFacade == null) {
			inventarioFacade = new InventarioFacade();
		}

		return inventarioFacade;
	}

	public Inventario getInventario() {
		if (inventario == null) {
			inventario = new Inventario();
		}

		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public void createInventario() {
		try {
			getInventarioFacade().createInventario(inventario);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateInventario() {
		try {
			getInventarioFacade().updateInventario(inventario);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteInventario() {
		try {
			getInventarioFacade().deleteInventario(inventario);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadInventarios();
			resetInventario();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
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
	
	public void newMaterial() {
		this.material = new Material();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	

}