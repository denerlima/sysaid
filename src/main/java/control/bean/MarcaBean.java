package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Marca;
import model.facade.MarcaFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

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
			displayInfoMessageToUser("Criado com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		marca = marcaFacade.find(id);
	}
	
	public void updateMarca() {
		try {
			getMarcaFacade().update(marca);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteMarca() {
		try {
			getMarcaFacade().delete(marca);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadMarcas();
			resetMarca();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
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

	public boolean isManaged() {
		return marca != null && marca.getId() != null;
	}
	
}
