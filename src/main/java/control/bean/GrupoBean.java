package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Grupo;
import model.facade.GrupoFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class GrupoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Grupo grupo;	
	private List<Grupo> grupos;
	
	@Inject
	private GrupoFacade grupoFacade;

	
	public GrupoFacade getGrupoFacade() {
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
			getGrupoFacade().create(grupo);
			displayInfoMessageToUser("Criado com Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		grupo = grupoFacade.find(id);
	}
	
	public void updateGrupo() {
		try {
			getGrupoFacade().update(grupo);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteGrupo() {
		try {
			getGrupoFacade().delete(grupo);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadGrupos();
			resetGrupo();
		} catch (Exception e) {
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

	public boolean isManaged() {
		return grupo != null && grupo.getId() != null;
	}
	
}
