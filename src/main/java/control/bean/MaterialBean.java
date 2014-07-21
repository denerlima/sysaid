package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.facade.MaterialFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class MaterialBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Material material;	
	private List<Material> materiais;
	
	@Inject
	private MaterialFacade materialFacade;
	
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

	public void createMaterial() {
		try {			
			getMaterialFacade().create(material);
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
			getMaterialFacade().update(material);
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
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
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
			displayErrorMessageToUser("Ops, não foi possivel achar nennhum material. ERRO");
			e.printStackTrace();
		}
		return materiais;
	}
	
	public void deleteMaterial() {
		try {
			getMaterialFacade().delete(material);
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
		materiais = getMaterialFacade().listAll();
	}
	
	public void resetMaterial() {
		material = new Material();
	}
	
}
