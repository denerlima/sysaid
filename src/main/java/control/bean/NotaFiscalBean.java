package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Material;
import model.entity.NotaFiscal;
import model.facade.MaterialFacade;
import model.facade.NotaFiscalFacade;

@ViewScoped
@ManagedBean
public class NotaFiscalBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal;	
	private List<NotaFiscal> notasFiscais;
	private NotaFiscalFacade notaFiscalFacade;
	private Material material;
	private List<Material> materiais;

	
	public NotaFiscalFacade getNotaFiscalFacade() {
		if (notaFiscalFacade == null) {
			notaFiscalFacade = new NotaFiscalFacade();
		}

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

	public void createNotaFiscal() {
		try {
			getNotaFiscalFacade().create(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadOrdensDeCompra();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateNotaFiscal() {
		try {
			getNotaFiscalFacade().update(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadOrdensDeCompra();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteNotaFiscal() {
		try {
			getNotaFiscalFacade().delete(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadOrdensDeCompra();
			resetNotaFiscal();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<NotaFiscal> getAllOrdensDeCompra() {
		if (notasFiscais == null) {
			loadOrdensDeCompra();
		}

		return notasFiscais;
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
		notasFiscais = getNotaFiscalFacade().listAll();
	}

	public void resetNotaFiscal() {
		notaFiscal = new NotaFiscal();
	}
	
	public void newMaterial() {
		if (notaFiscal.getMateriais() == null) {
			notaFiscal.setMateriais(new ArrayList<Material>());
		}		
		notaFiscal.getMateriais().add(material);
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