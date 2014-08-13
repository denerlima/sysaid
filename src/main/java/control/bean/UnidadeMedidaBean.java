package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Unidade;
import model.entity.UnidadeMedida;
import model.entity.UnidadeMedidaSaida;
import model.facade.UnidadeFacade;
import model.facade.UnidadeMedidaFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

import util.HibernateUtil;

@Named
@ViewScoped
public class UnidadeMedidaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UnidadeMedida unidadeMedida;	
	private List<UnidadeMedida> unidadesMedida;
	private List<Unidade> unidades;
	
	@Inject
	private UnidadeMedidaFacade unidadeMedidaFacade;
	
	@Inject
	private UnidadeFacade unidadeEntradaFacade;
	
	
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
			if(!isUnidadeValida()) {
				return;
			}
			getUnidadeMedidaFacade().create(unidadeMedida);
			displayInfoMessageToUser("Criado com Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void edit(Integer id) {
		System.out.println(id);
	}
	
	public boolean isUnidadeValida() {
		for(UnidadeMedidaSaida ums : unidadeMedida.getSaidas()) {
			if(ums.getUnidade().getId().intValue() == unidadeMedida.getUnidadeEntrada().getId().intValue()) {
				return true;
			}
		}
		displayInfoMessageToUser("A Unidade deve ter pelo menos a própria unidade como saída");
		return false;
	}
	
	public void updateUnidadeMedida() {
		try {
			if(!isUnidadeValida()) {
				return;
			}
			getUnidadeMedidaFacade().update(unidadeMedida);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteUnidadeMedida() {
		try {
			getUnidadeMedidaFacade().delete(unidadeMedida);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadUnidadesMedida();
			resetUnidadeMedida();
		} catch (Exception e) {
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
	
	public void addUnidadeSaida() {
		UnidadeMedidaSaida ums = new UnidadeMedidaSaida();
		ums.setUnidadeMedida(unidadeMedida);
		getUnidadeMedida().getSaidas().add(ums);
	}
	
	public void removerUnidadeSaida(UnidadeMedidaSaida ums) {
		getUnidadeMedida().getSaidas().remove(ums);
	}
	
	public List<Unidade> getUnidades(){
		if(unidades == null) {
			unidades = HibernateUtil.unproxy(unidadeEntradaFacade.listAll());
		}
		return unidades;
	}
	
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades; 
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
