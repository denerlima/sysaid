package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Marca;
import model.facade.MarcaFacade;

@ViewScoped
@ManagedBean
public class MarcaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Marca marca;	
	private List<Marca> marcas;
	private MarcaFacade marcaFacade;

	
	public MarcaFacade getMarcaFacade() {
		if (marcaFacade == null) {
			marcaFacade = new MarcaFacade();
		}

		return marcaFacade;
	}

	public Marca getMarca() {
		if (marca == null) {
			marca = new Marca();
		}

		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void createMarca() {
		try {
			getMarcaFacade().createMarca(marca);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateMarca() {
		try {
			getMarcaFacade().updateMarca(marca);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteMarca() {
		try {
			getMarcaFacade().deleteMarca(marca);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<Marca> getAllMarcas() {
		if (marcas == null) {
			loadMarcas();
		}

		return marcas;
	}

	private void loadMarcas() {
		marcas = getMarcaFacade().listAll();
	}

	public void resetMarca() {
		marca = new Marca();
	}

}