package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Material;
import model.entity.OrdemDeCompra;
import model.facade.MaterialFacade;
import model.facade.OrdemDeCompraFacade;

@ViewScoped
@ManagedBean
public class OrdemDeCompraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemDeCompra ordemDeCompra;	
	private List<OrdemDeCompra> ordensDeCompra;
	private OrdemDeCompraFacade ordemDeCompraFacade;
	private Material material;
	private List<Material> materiais;

	
	public OrdemDeCompraFacade getOrdemDeCompraFacade() {
		if (ordemDeCompraFacade == null) {
			ordemDeCompraFacade = new OrdemDeCompraFacade();
		}

		return ordemDeCompraFacade;
	}

	public OrdemDeCompra getOrdemDeCompra() {
		if (ordemDeCompra == null) {
			ordemDeCompra = new OrdemDeCompra();
		}

		return ordemDeCompra;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}

	public void createOrdemDeCompra() {
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
	}
	
	public void updateOrdemDeCompra() {
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
			MaterialFacade materialFacade = new MaterialFacade();
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
	
	public void newMaterial() {
		if (ordemDeCompra.getMateriais() == null) {
			ordemDeCompra.setMateriais(new ArrayList<Material>());
		}		
		ordemDeCompra.getMateriais().add(material);
		this.material = new Material();
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
		

}