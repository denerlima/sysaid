package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.TipoMaterial;
import model.facade.TipoMaterialFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class TipoMaterialBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private TipoMaterial tipoMaterial;	
	private List<TipoMaterial> tiposMaterial;
	
	@Inject
	private TipoMaterialFacade tipoMaterialFacade;

	
	public TipoMaterialFacade getTipoMaterialFacade() {
		return tipoMaterialFacade;
	}

	public TipoMaterial getTipoMaterial() {
		if (tipoMaterial == null) {
			tipoMaterial = new TipoMaterial();
		}

		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public void createTipoMaterial() {
		try {
			getTipoMaterialFacade().create(tipoMaterial);
			displayInfoMessageToUser("Criado com Sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		tipoMaterial = tipoMaterialFacade.find(id);
	}
	
	public void updateTipoMaterial() {
		try {
			getTipoMaterialFacade().update(tipoMaterial);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteTipoMaterial() {
		try {
			getTipoMaterialFacade().delete(tipoMaterial);
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<TipoMaterial> getAlltiposMaterial() {
		if (tiposMaterial == null) {
			loadTiposMaterial();
		}

		return tiposMaterial;
	}

	private void loadTiposMaterial() {
		tiposMaterial = getTipoMaterialFacade().listAll();
	}

	public void resetTipoMaterial() {
		tipoMaterial = new TipoMaterial();
	}

	public boolean isManaged() {
		return tipoMaterial != null && tipoMaterial.getId() != null;
	}
	
}
