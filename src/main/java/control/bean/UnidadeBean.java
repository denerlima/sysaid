package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Log;
import model.entity.Unidade;
import model.facade.UnidadeFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UnidadeBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Unidade unidade;	
	private List<Unidade> unidades;
	
	@Inject
	private UnidadeFacade unidadeFacade;
	
	@Inject
	private UnidadeMedidaBean unidadeMedidaBean; 

	
	public UnidadeFacade getUnidadeFacade() {
		return unidadeFacade;
	}

	public Unidade getUnidade() {
		if (unidade == null) {
			unidade = new Unidade();
		}
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public void createUnidade() {
		try {
			//getUnidadeFacade().create(unidade);
			unidadeMedidaBean.getUnidades().add(unidade);
			displayInfoMessageToUser("Criado com Sucesso");
			//loadUnidades();
			resetUnidade();
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
			appendLog(Log.ACAO_CREATE, unidade.getId(), Unidade.class.getName(), unidade.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateUnidade() {
		try {
			getUnidadeFacade().update(unidade);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidades();
			resetUnidade();
			unidadeMedidaBean.setUnidades(null);
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
			appendLog(Log.ACAO_UPDATE, unidade.getId(), Unidade.class.getName(), unidade.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteUnidade() {
		try {
			getUnidadeFacade().delete(unidade);
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadUnidades();
			resetUnidade();
			unidadeMedidaBean.setUnidades(null);
			appendLog(Log.ACAO_DELETE, unidade.getId(), Unidade.class.getName(), unidade.toJson());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi poss�vel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void newUnidade() {
		unidade = new Unidade();
	}
	
	public List<Unidade> getAllUnidadesMedida() {
		if (unidades == null) {
			loadUnidades();
		}
		return unidades;
	}

	private void loadUnidades() {
		unidades = getUnidadeFacade().listAll();
	}

	public void resetUnidade() {
		unidade = new Unidade();
	}
	
	public boolean isManaged() {
		return unidade != null && unidade.getId() != null;
	}

}
