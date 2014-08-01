package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Unidade;
import model.facade.UnidadeEntradaFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UnidadeEntradaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Unidade unidadeEntrada;	
	private List<Unidade> unidadesMedida;
	
	@Inject
	private UnidadeEntradaFacade unidadeEntradaFacade;

	
	public UnidadeEntradaFacade getUnidadeEntradaFacade() {
		return unidadeEntradaFacade;
	}

	public Unidade getUnidadeEntrada() {
		if (unidadeEntrada == null) {
			unidadeEntrada = new Unidade();
		}
		return unidadeEntrada;
	}

	public void setUnidadeEntrada(Unidade unidadeEntrada) {
		this.unidadeEntrada = unidadeEntrada;
	}

	public void createUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().create(unidadeEntrada);
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void updateUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().update(unidadeEntrada);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().delete(unidadeEntrada);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void newUnidadeEntrada() {
		unidadeEntrada = new Unidade();
	}
	
	public List<Unidade> getAllUnidadesMedida() {
		if (unidadesMedida == null) {
			loadUnidadesMedida();
		}

		return unidadesMedida;
	}

	private void loadUnidadesMedida() {
		unidadesMedida = getUnidadeEntradaFacade().listAll();
	}

	public void resetUnidadeEntrada() {
		unidadeEntrada = new Unidade();
	}
	
	public boolean isManaged() {
		return unidadeEntrada != null && unidadeEntrada.getId() != null;
	}

}
