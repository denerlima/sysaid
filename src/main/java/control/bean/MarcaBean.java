package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Marca;
import model.facade.MarcaFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class MarcaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Marca marca;	
	private List<Marca> marcas;
	
	@Inject
	private MarcaFacade marcaFacade;

	
	public MarcaFacade getMarcaFacade() {
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
			getMarcaFacade().create(marca);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateMarca() {
		try {
			getMarcaFacade().update(marca);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteMarca() {
		try {
			getMarcaFacade().delete(marca);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
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