package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.entity.UnidadeEntrada;
import model.entity.UnidadeMedida;
import model.facade.UnidadeEntradaFacade;
import model.facade.UnidadeMedidaFacade;


@ViewScoped
@ManagedBean
public class UnidadeMedidaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;	
	private List<UnidadeMedida> unidadesMedida;
	private UnidadeMedidaFacade unidadeMedidaFacade;
	private UnidadeEntrada selectedUnidadeEntrada;
	private Boolean isSalvaUnidadeEntrada ;

	
	public UnidadeMedidaBean(){
		selectedUnidadeEntrada = new UnidadeEntrada();
	}
	
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
			unidadeMedida.setUnidadeEntrada(selectedUnidadeEntrada);
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
			unidadeMedida.setUnidadeEntrada(selectedUnidadeEntrada);
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
	
	public List<SelectItem> getSelectItensUnidadeEntrada(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		UnidadeEntradaFacade unidadeEntradaFacade = new UnidadeEntradaFacade();
		for(UnidadeEntrada unidadeEntrada : unidadeEntradaFacade.listAll()){
			lista.add(new SelectItem(unidadeEntrada.getId(), unidadeEntrada.getDescricao()));
		}
		return lista;
	}	

	private void loadUnidadesMedida() {
		unidadesMedida = getUnidadeMedidaFacade().listAll();
	}
	
	 public void onUnidadeChange() {
		Integer  idObj = selectedUnidadeEntrada.getId();
	        if(idObj != null)
	        	isSalvaUnidadeEntrada = true;
	        else
	        	isSalvaUnidadeEntrada = false;
	    }
	 
	 
	
	public Boolean getIsSalvaUnidadeEntrada() {
		return isSalvaUnidadeEntrada;
	}

	public void setIsSalvaUnidadeEntrada(Boolean isSalvaUnidadeEntrada) {
		this.isSalvaUnidadeEntrada = isSalvaUnidadeEntrada;
	}

	public UnidadeEntrada getSelectedUnidadeEntrada() {
		return selectedUnidadeEntrada;
	}

	public void setSelectedUnidadeEntrada(UnidadeEntrada selectedUnidadeEntrada) {
		this.selectedUnidadeEntrada = selectedUnidadeEntrada;
	}

	public void resetUnidadeMedida() {
		unidadeMedida = new UnidadeMedida();
	}

}