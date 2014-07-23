package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import model.entity.Material;
import model.entity.NotaFiscal;
import model.facade.FornecedorFacade;
import model.facade.MaterialFacade;
import model.facade.NotaFiscalFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class NotaFiscalBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal;	
	private List<NotaFiscal> notasFiscais;
	
	@Inject
	private NotaFiscalFacade notaFiscalFacade;
	
	@Inject
	private FornecedorFacade fornecedorFacade;
	
	@Inject
	private MaterialFacade materialFacade;
	
	private Material material;
	private List<Material> materiais;
	private List<Fornecedor> fornecedores;
	
	public NotaFiscalFacade getNotaFiscalFacade() {
		return notaFiscalFacade;
	}

	public NotaFiscal getNotaFiscal() {
		if (notaFiscal == null) {
			notaFiscal = new NotaFiscal();
		}
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String novo() {
		return "/notaFiscal/notaFiscalEdit.xhtml?faces-redirect=true";
	}
	
	public String edit(Integer id) {
		return "/notaFiscal/notaFiscalEdit.xhtml?faces-redirect=true&id="+id;
	}
	
	public void initLoad(Integer id) {
		if(notaFiscal != null) {
			return;
		}
		if(id == null || id == 0) {
			notaFiscal = new NotaFiscal();
			//notaFiscal.setMateriais(new ArrayList<NotaFiscalMaterial>());
		} else {
			notaFiscal = getNotaFiscalFacade().find(id);
		}
	}
	
	public void createNotaFiscal() {
		try {
			getNotaFiscalFacade().create(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateNotaFiscal() {
		try {
			getNotaFiscalFacade().update(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteNotaFiscal() {
		try {
			getNotaFiscalFacade().delete(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<NotaFiscal> getAllNotasFiscais() {
		if (notasFiscais == null) {
			loadNotasFiscais();
		}

		return notasFiscais;
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
	
	public List<Fornecedor> getAllFornecedores(){
		if(fornecedores != null) {
			fornecedores = fornecedorFacade.listAll();
		}
		return fornecedores;
	}
	
	private void loadNotasFiscais() {
		notasFiscais = getNotaFiscalFacade().listAll();
	}

	public void resetNotaFiscal() {
		notaFiscal = new NotaFiscal();
	}
	
	public void newMaterial() {
		if (notaFiscal.getMateriais() == null) {
			//notaFiscal.setMateriais(new ArrayList<Material>());
		}		
		//notaFiscal.getMateriais().add(material);
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
