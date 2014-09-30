package control.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Parametro;
import model.facade.ParametroFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ParametroBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Parametro parametro;	
	
	
	@Inject
	private ParametroFacade parametroFacade;
	
	public ParametroFacade getParametroFacade() {
		return parametroFacade;
	}

	public Parametro getParametro() {
		if (parametro == null) {
			parametro = new Parametro();
			parametro = getParametroFacade().find(1);
		}
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public void createParametro() {
		try {
			getParametroFacade().create(parametro);
			displayInfoMessageToUser("Criado com Sucesso");
			resetParametro();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		parametro = parametroFacade.find(id);
	}
	
	public void updateParametro() {
		try {
			getParametroFacade().update(parametro);
			displayInfoMessageToUser("Alterado com  Sucesso");
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteParametro() {
		try {
			getParametroFacade().delete(parametro);
			displayInfoMessageToUser("Excluído com Sucesso");
			resetParametro();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void resetParametro() {
		parametro = new Parametro();
	}

	public boolean isManaged() {
		return parametro != null && parametro.getId() != null;
	}
	
}
