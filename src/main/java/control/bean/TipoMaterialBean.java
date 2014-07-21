package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.TipoMaterial;
import model.facade.TipoMaterialFacade;

import org.omnifaces.cdi.ViewScoped;

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
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			//Logger.getLogger(getClass()).info("usuario salvo no banco com sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateTipoMaterial() {
		try {
			getTipoMaterialFacade().update(tipoMaterial);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteTipoMaterial() {
		try {
			getTipoMaterialFacade().delete(tipoMaterial);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadTiposMaterial();
			resetTipoMaterial();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
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

}