package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.UnidadeEntrada;
import model.facade.UnidadeEntradaFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UnidadeEntradaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UnidadeEntrada unidadeEntrada;	
	private List<UnidadeEntrada> unidadesMedida;
	
	@Inject
	private UnidadeEntradaFacade unidadeEntradaFacade;

	
	public UnidadeEntradaFacade getUnidadeEntradaFacade() {
		return unidadeEntradaFacade;
	}

	public UnidadeEntrada getUnidadeEntrada() {
		if (unidadeEntrada == null) {
			unidadeEntrada = new UnidadeEntrada();
		}
		return unidadeEntrada;
	}

	public void setUnidadeEntrada(UnidadeEntrada unidadeEntrada) {
		this.unidadeEntrada = unidadeEntrada;
	}

	public void createUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().create(unidadeEntrada);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void updateUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().update(unidadeEntrada);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().delete(unidadeEntrada);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void newUnidadeEntrada() {
		unidadeEntrada = new UnidadeEntrada();
	}
	
	public List<UnidadeEntrada> getAllUnidadesMedida() {
		if (unidadesMedida == null) {
			loadUnidadesMedida();
		}

		return unidadesMedida;
	}

	private void loadUnidadesMedida() {
		unidadesMedida = getUnidadeEntradaFacade().listAll();
	}

	public void resetUnidadeEntrada() {
		unidadeEntrada = new UnidadeEntrada();
	}
	
	public boolean isManaged() {
		return unidadeEntrada != null && unidadeEntrada.getId() != null;
	}

}
