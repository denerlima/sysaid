package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import model.entity.TipoMaterial;
import model.facade.TipoMaterialFacade;

@ViewScoped
@ManagedBean
public class TipoMaterialBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoMaterial tipoMaterial;	
	private List<TipoMaterial> tiposMaterial;
	private TipoMaterialFacade tipoMaterialFacade;

	
	public TipoMaterialFacade getTipoMaterialFacade() {
		if (tipoMaterialFacade == null) {
			tipoMaterialFacade = new TipoMaterialFacade();
		}

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
			getTipoMaterialFacade().createTipoMaterial(tipoMaterial);
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
			getTipoMaterialFacade().updateTipoMaterial(tipoMaterial);
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
			getTipoMaterialFacade().deleteTipoMaterial(tipoMaterial);
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