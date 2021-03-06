package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import model.entity.Log;
import model.entity.Material;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
import model.entity.Usuario;
import model.facade.FornecedorFacade;
import model.facade.MaterialFacade;
import model.facade.OrdemDeCompraFacade;
import model.facade.UsuarioFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

import util.FaceletRenderer;
import util.HibernateUtil;

@Named
@ViewScoped
public class OrdemDeCompraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemDeCompra ordemDeCompra;	
	private List<OrdemDeCompra> ordensDeCompra;
	private List<OrdemDeCompraMaterial> ordensDeComprasMateriais;
	private List<OrdemDeCompraMaterial> ordensDeComprasMateriaisFiltered;
	
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
	private List<Usuario> usuarios;
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
		if (usuarios == null) {
			usuarios = HibernateUtil.unproxy(usuarioFacade.listAll());
		}
		return usuarios;
	}
	
	public List<Fornecedor> getAllContratados(){
		if(contratados == null) {
			contratados = HibernateUtil.unproxy(fornecedorFacade.listAll());
		}
		return contratados;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}

	public String createOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().create(ordemDeCompra);			
			displayInfoMessageToUser("Criado com Sucesso. N�mero da Ordem de Compra: "+ordemDeCompra.getId());
			appendLog(Log.ACAO_CREATE, ordemDeCompra.getId(), OrdemDeCompra.class.getName(), ordemDeCompra.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel criar. ERRO");
			e.printStackTrace();
		}
		atualizaQtdeOrdemDeCompra();
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public String updateOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().update(ordemDeCompra);			
			displayInfoMessageToUser("Alterado com  Sucesso");
			appendLog(Log.ACAO_UPDATE, ordemDeCompra.getId(), OrdemDeCompra.class.getName(), ordemDeCompra.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel alterar. ERRO");
			e.printStackTrace();
		}
		atualizaQtdeOrdemDeCompra();
		return "/ordemDeCompra/ordemDeCompraList.xhtml?faces-redirect=true";
	}
	
	public void deleteOrdemDeCompra() {
		try {
			if(ordemDeCompraFacade.isOrdemDeCompraUtilizadaPorNotaFiscal(ordemDeCompra.getId())) {
				displayErrorMessageToUser("n�o � poss�vel excluir a Ordem de Compra, pois est� inclu�da em uma Nota Fiscal.");
				return;
			}
			getOrdemDeCompraFacade().delete(ordemDeCompra);			
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadOrdensDeCompra();
			appendLog(Log.ACAO_DELETE, ordemDeCompra.getId(), OrdemDeCompra.class.getName(), ordemDeCompra.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel excluir. ERRO");
			e.printStackTrace();
		}
		atualizaQtdeOrdemDeCompra();
	}

	public void cancelarOrdemDeCompra() {
		try {
			ordemDeCompra.setAtivo(2);
			getOrdemDeCompraFacade().update(ordemDeCompra);
			displayInfoMessageToUser("Cancelado com  Sucesso");
			loadOrdensDeCompra();
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel cancelar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void atualizaQtdeOrdemDeCompra() {
		try {
			materialFacade.atualizaQuantidadeSolicitadaOC(ordemDeCompra.getMateriais());			
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel alterar. ERRO");
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
			ordensDeComprasMateriaisFiltered = ordensDeComprasMateriais;
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel achar nennhuma Ordem de Compra. ERRO");
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
			displayErrorMessageToUser("Campo Material Obrigat�rio");
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

	public List<OrdemDeCompraMaterial> getOrdensDeComprasMateriaisFiltered() {
		return ordensDeComprasMateriaisFiltered;
	}

	public void setOrdensDeComprasMateriaisFiltered(List<OrdemDeCompraMaterial> ordensDeComprasMateriaisFiltered) {
		this.ordensDeComprasMateriaisFiltered = ordensDeComprasMateriaisFiltered;
	}

	public boolean isManaged() {
		return ordemDeCompra != null && ordemDeCompra.getId() != null;
	}
	
	public String imprimir(OrdemDeCompra oc) {
		setOrdemDeCompra(oc);
		FaceletRenderer renderer = new FaceletRenderer(FacesContext.getCurrentInstance());
		renderer.renderViewPDF("/ordemDeCompra/ordemDeCompraPDF.xhtml");
		return null;
	}
	
	public String imprimirRelatorioPDF() {
		FaceletRenderer renderer = new FaceletRenderer(FacesContext.getCurrentInstance());
		renderer.renderViewPDF("/ordemDeCompra/ordemDeCompraRelatorioPDF.xhtml");
		return null;
	}
	
}
