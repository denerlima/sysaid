package control.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class InventarioBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 5226648353758700812L;

	private Inventario inventario;	
	private List<Inventario> inventarios;
	private List<InventarioMaterial> inventariosMateriais;
	private InventarioMaterial inventarioMaterial;
	
	@Inject
	private InventarioFacade inventarioFacade;
	
	@Inject
	private UsuarioFacade usuarioFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	private Material material;
	private List<Material> materiais;
	private Date dataInicialInventario;
	private Date dataFinalInventario;

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
	
	public String view(Integer id) {
		return "/inventario/inventarioView.xhtml?faces-redirect=true&id="+id;
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
			if(inventario.getMateriais().isEmpty()) {
				displayErrorMessageToUser("Nenhum material foi informado.");
				return null;
			}
			getInventarioFacade().create(inventario);
			displayInfoMessageToUser("Criado com Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return "/inventario/inventarioList.xhtml?faces-redirect=true";
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
	
	public String updateInventario() {
		try {
			getInventarioFacade().update(inventario);
			displayInfoMessageToUser("Alterado com  Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/inventario/inventarioList.xhtml?faces-redirect=true";
	}
	
	public String concluirInventario() {
		try {
			for(InventarioMaterial im : inventario.getMateriais()) {
				if(im.isStatusNull()) {
					displayErrorMessageToUser("Para concluir todos os materiais devem ser aprovados ou rejeitados.");
					return null;
				}
			}
			getInventarioFacade().concluir(inventario);
			displayInfoMessageToUser("Alterado com  Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return "/inventario/inventarioList.xhtml?faces-redirect=true";
	}
	
	public void deleteInventario() {
		try {
			getInventarioFacade().delete(inventario);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadInventarios();
		} catch (Exception e) {
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
	
	
	public List<InventarioMaterial> pesquisarInventarioByFilter() {
		try {			
			inventariosMateriais = getInventarioFacade().listMateriaisInventarios(inventario , material , dataInicialInventario , dataFinalInventario);			
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel achar nennhum Inventário. ERRO");
			e.printStackTrace();
		}
		return inventariosMateriais;
	}

	private void loadInventarios() {
		inventarios = getInventarioFacade().listAll();
	}

	public void imprimirChecklist() throws IOException {
		StringBuffer sb = new StringBuffer().append("");
		sb.append("Material;");
		sb.append("Grupo;");
		sb.append("Unidade medida;");
		sb.append("Estoque atual;");
		sb.append("Estoque inventariado;");
		sb.append("Justificativa;");
		sb.append("\n");
		for (InventarioMaterial im : inventario.getMateriais()) {
			if(im.isSelecionado()){
				sb.append(im.getMaterial().getMaterialLabel());
				sb.append(";");
				sb.append(im.getMaterial().getGrupo().getDescricaoLabel());
				sb.append(";");
				sb.append(im.getMaterial().getUnidadeMedida().getUnidadeEntrada().getDescricao());
				sb.append(";");
				sb.append(im.getMaterial().getEstoque());
				sb.append(" ;");
				sb.append(" ;");
				sb.append("\n");
			}
		}
		geraExtractCSV(sb.toString(), "Inventário-" + inventario.getId());
	}
	
	public void removerSelecionados() {
		for(InventarioMaterial im : new ArrayList<InventarioMaterial>(inventario.getMateriais())) {
			if(im.isSelecionado()) {
				inventario.getMateriais().remove(im);
			}
		}
	}
	
	public void addTodosMateriais() {
		List<Material> mats = materialFacade.listAll();
		forMat: for(Material mat : mats) {
			for(InventarioMaterial invMat : inventario.getMateriais()) {
				if(invMat.getMaterial().getId().intValue() == mat.getId().intValue()) {
					continue forMat; 
				}
			}
			addMaterial(mat);
		}
	}
	
	public void addMaterial() {
		if(material == null) {
			displayErrorMessageToUser("Campo Material Obrigatório");
			return;
		}
		for(InventarioMaterial invMat : inventario.getMateriais()) {
			if(invMat.getMaterial().getId().intValue() == material.getId().intValue()) {
				displayErrorMessageToUser("Não é permitido adicionar material repetido");
				return;
			}
		}
		addMaterial(material);
		this.material = new Material();
	}
	
	public void addMaterial(Material mat) {
		InventarioMaterial invMat = new InventarioMaterial();
		invMat.setInventario(inventario);
		invMat.setMaterial(mat);
		invMat.setQuantidadeInventariada(mat.getEstoque());
		invMat.setQuantidadeEstoque(mat.getEstoque());
		inventario.getMateriais().add(invMat);
	}
	
	public void removerMaterial(InventarioMaterial material) {
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
	
	public Date getDataInicialInventario() {
		return dataInicialInventario;
	}

	public void setDataInicialInventario(Date dataInicialInventario) {
		this.dataInicialInventario = dataInicialInventario;
	}

	public Date getDataFinalInventario() {
		return dataFinalInventario;
	}

	public void setDataFinalInventario(Date dataFinalInventario) {
		this.dataFinalInventario = dataFinalInventario;
	}
	
	public List<InventarioMaterial> getInventariosMateriais() {
		return inventariosMateriais;
	}

	public void setInventariosMateriais(List<InventarioMaterial> inventariosMateriais) {
		this.inventariosMateriais = inventariosMateriais;
	}

	public boolean isManaged() {
		return inventario != null && inventario.getId() != null;
	}
	
	public InventarioMaterial getInventarioMaterial() {
		return inventarioMaterial;
	}

	public void setInventarioMaterial(InventarioMaterial inventarioMaterial) {
		this.inventarioMaterial = inventarioMaterial;
	}

	public void editarMaterial(InventarioMaterial im) {
		setInventarioMaterial(im);
	}
	
	public void aprovarMaterial() {
		if(inventarioMaterial.getQuantidadeAprovada().longValue() < 0) {
			displayErrorMessageToUser("A quantidade aprovada não pode ser menor que zero.");
			return;
		}
		inventarioMaterial.setStatus(InventarioMaterial.STATUS_APROVADO);
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void rejeitarMaterial() {
		inventarioMaterial.setStatus(InventarioMaterial.STATUS_REJEITADO);
		inventarioMaterial.setQuantidadeAprovada(new BigDecimal(0));
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}

	
}
