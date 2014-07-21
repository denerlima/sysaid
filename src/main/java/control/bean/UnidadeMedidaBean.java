package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import model.entity.UnidadeEntrada;
import model.entity.UnidadeMedida;
import model.facade.UnidadeEntradaFacade;
import model.facade.UnidadeMedidaFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class UnidadeMedidaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;	
	private List<UnidadeMedida> unidadesMedida;
	
	@Inject
	private UnidadeMedidaFacade unidadeMedidaFacade;
	
	public void novo() {
		resetUnidadeMedida();
	}

	public UnidadeMedidaFacade getUnidadeMedidaFacade() {
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
			getUnidadeMedidaFacade().create(unidadeMedida);
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			unidadeMedida = new UnidadeMedida(unidadeMedida.getUnidadeEntrada());
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void edit(Integer id) {
		System.out.println(id);
	}
	
	public void updateUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().update(unidadeMedida);
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
			getUnidadeMedidaFacade().delete(unidadeMedida);
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

	public List<UnidadeEntrada> getUnidadesEntradas(){
		UnidadeEntradaFacade unidadeEntradaFacade = new UnidadeEntradaFacade();
		return unidadeEntradaFacade.listAll();
	}
	
	private void loadUnidadesMedida() {
		unidadesMedida = getUnidadeMedidaFacade().listAll();
	}
	
	public void resetUnidadeMedida() {
		unidadeMedida = new UnidadeMedida();
	}

	public boolean isManaged() {
		return unidadeMedida != null && unidadeMedida.getId() != null;
	}
	
}
