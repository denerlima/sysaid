package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Grupo;
import model.facade.GrupoFacade;

@ViewScoped
@ManagedBean
public class GrupoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Grupo grupo;	
	private List<Grupo> grupos;
	private GrupoFacade grupoFacade;

	
	public GrupoFacade getGrupoFacade() {
		if (grupoFacade == null) {
			grupoFacade = new GrupoFacade();
		}

		return grupoFacade;
	}

	public Grupo getGrupo() {
		if (grupo == null) {
			grupo = new Grupo();
		}

		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void createGrupo() {
		try {
			getGrupoFacade().createGrupo(grupo);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateGrupo() {
		try {
			getGrupoFacade().updateGrupo(grupo);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteGrupo() {
		try {
			getGrupoFacade().deleteGrupo(grupo);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<Grupo> getAllGrupos() {
		if (grupos == null) {
			loadGrupos();
		}

		return grupos;
	}

	private void loadGrupos() {
		grupos = getGrupoFacade().listAll();
	}

	public void resetGrupo() {
		grupo = new Grupo();
	}

}