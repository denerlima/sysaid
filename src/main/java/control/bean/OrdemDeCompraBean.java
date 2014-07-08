package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.OrdemDeCompra;
import model.facade.OrdemDeCompraFacade;

@ViewScoped
@ManagedBean
public class OrdemDeCompraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemDeCompra ordemDeCompra;	
	private List<OrdemDeCompra> ordensDeCompra;
	private OrdemDeCompraFacade ordemDeCompraFacade;

	
	public OrdemDeCompraFacade getOrdemDeCompraFacade() {
		if (ordemDeCompraFacade == null) {
			ordemDeCompraFacade = new OrdemDeCompraFacade();
		}

		return ordemDeCompraFacade;
	}

	public OrdemDeCompra getOrdemDeCompra() {
		if (ordemDeCompra == null) {
			ordemDeCompra = new OrdemDeCompra();
		}

		return ordemDeCompra;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}

	public void createOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().createOrdemDeCompra(ordemDeCompra);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().updateOrdemDeCompra(ordemDeCompra);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteOrdemDeCompra() {
		try {
			getOrdemDeCompraFacade().deleteOrdemDeCompra(ordemDeCompra);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadOrdensDeCompra();
			resetOrdemDeCompra();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<OrdemDeCompra> getAllOrdensDeCompra() {
		if (ordensDeCompra == null) {
			loadOrdensDeCompra();
		}

		return ordensDeCompra;
	}

	private void loadOrdensDeCompra() {
		ordensDeCompra = getOrdemDeCompraFacade().listAll();
	}

	public void resetOrdemDeCompra() {
		ordemDeCompra = new OrdemDeCompra();
	}

}