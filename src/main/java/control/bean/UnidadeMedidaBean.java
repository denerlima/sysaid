package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.UnidadeMedida;
import model.facade.UnidadeMedidaFacade;


@ViewScoped
@ManagedBean
public class UnidadeMedidaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;	
	private List<UnidadeMedida> unidadesMedida;
	private UnidadeMedidaFacade unidadeMedidaFacade;

	
	public UnidadeMedidaFacade getUnidadeMedidaFacade() {
		if (unidadeMedidaFacade == null) {
			unidadeMedidaFacade = new UnidadeMedidaFacade();
		}

		return unidadeMedidaFacade;
	}

	public UnidadeMedida getUnidadeMedida() {
		if (unidadeMedida == null) {
			unidadeMedida = new UnidadeMedida();
		}

		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public void createUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().createUnidadeMedida(unidadeMedida);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().updateUnidadeMedida(unidadeMedida);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().deleteUnidadeMedida(unidadeMedida);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<UnidadeMedida> getAllUnidadesMedida() {
		if (unidadesMedida == null) {
			loadUnidadesMedida();
		}

		return unidadesMedida;
	}

	private void loadUnidadesMedida() {
		unidadesMedida = getUnidadeMedidaFacade().listAll();
	}

	public void resetUnidadeMedida() {
		unidadeMedida = new UnidadeMedida();
	}

}