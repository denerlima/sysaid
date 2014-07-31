package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Material;
import model.entity.OrdemServico;
import model.entity.OrdemServicoMaterial;
import model.facade.MaterialFacade;
import model.facade.OrdemServicoFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class OrdemServicoBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;	
	private List<OrdemServico> ordensServicos;
	
	@Inject
	private OrdemServicoFacade ordemServicoFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	private Material material;
	private List<Material> materiais;
	
	public OrdemServicoFacade getOrdemServicoFacade() {
		return ordemServicoFacade;
	}

	public OrdemServico getOrdemServico() {
		if (ordemServico == null) {
			ordemServico = new OrdemServico();
		}
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public String addNovoMaterial() {
		return "/ordemServico/osMaterialEdit.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public String redirectMaterialList() {
		return "/ordemServico/osMaterialList.xhtml?faces-redirect=true&os="+ordemServico.getId();
	}
	
	public void initLoad(Integer id) {
		if(ordemServico != null) {
			return;
		}
		if(id == null || id == 0) {
			throw new RuntimeException("Parametro OS não encontrado");
		} else {
			ordemServico = getOrdemServicoFacade().findOrCreate(id);
		}
	}
	
	public void createOrdemServico() {
		try {
			getOrdemServicoFacade().create(ordemServico);
			displayInfoMessageToUser("Criado com Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteOrdemServico() {
		try {
			getOrdemServicoFacade().delete(ordemServico);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadOrdensServicos();
			resetOrdemServico();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public String updateMateriais() {
		try {
			getOrdemServicoFacade().update(ordemServico);
			displayInfoMessageToUser("Operação realizada com sucesso");
			return redirectMaterialList();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OrdemServico> getAllOrdensServicos() {
		if (ordensServicos == null) {
			loadOrdensServicos();
		}

		return ordensServicos;
	}
	
	private void loadOrdensServicos() {
		ordensServicos = getOrdemServicoFacade().listAll();
	}

	public void resetOrdemServico() {
		ordemServico = new OrdemServico();
	}		

	public boolean isManaged() {
		return ordemServico != null && ordemServico.getId() != null;
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
	
	public void addMaterial() {
		if(material == null) {
			displayErrorMessageToUser("Campo Material Obrigatório");
			return;
		}
		OrdemServicoMaterial osMat = new OrdemServicoMaterial();
		osMat.setOrdemServico(ordemServico);
		osMat.setMaterial(material);
		ordemServico.getMateriais().add(osMat);
		this.material = new Material();
	}
	
	
	
}
