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
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UnidadeMedidaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;	
	private List<UnidadeMedida> unidadesMedida;
	
	@Inject
	private UnidadeMedidaFacade unidadeMedidaFacade;
	
	@Inject
	private UnidadeEntradaFacade unidadeEntradaFacade;
	
	
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
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			unidadeMedida = new UnidadeMedida(unidadeMedida.getUnidadeEntrada());
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void edit(Integer id) {
		System.out.println(id);
	}
	
	public void updateUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().update(unidadeMedida);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().delete(unidadeMedida);
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
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
