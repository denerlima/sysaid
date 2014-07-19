package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.UnidadeEntrada;
import model.facade.UnidadeEntradaFacade;


@ViewScoped
@ManagedBean
public class UnidadeEntradaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private UnidadeEntrada unidadeEntrada;	
	private List<UnidadeEntrada> unidadesMedida;
	private UnidadeEntradaFacade unidadeEntradaFacade;

	
	public UnidadeEntradaFacade getUnidadeEntradaFacade() {
		if (unidadeEntradaFacade == null) {
			unidadeEntradaFacade = new UnidadeEntradaFacade();
		}

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
			getUnidadeEntradaFacade().createUnidadeEntrada(unidadeEntrada);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().updateUnidadeEntrada(unidadeEntrada);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeEntrada();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteUnidadeEntrada() {
		try {
			getUnidadeEntradaFacade().deleteUnidadeEntrada(unidadeEntrada);
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

}