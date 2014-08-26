package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Aplicacao;
import model.facade.AplicacaoFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class AplicacaoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Aplicacao aplicacao;	
	private List<Aplicacao> aplicacoes;
	
	@Inject
	private AplicacaoFacade aplicacaoFacade;

	
	public AplicacaoFacade getAplicacaoFacade() {
		return aplicacaoFacade;
	}

	public Aplicacao getAplicacao() {
		if (aplicacao == null) {
			aplicacao = new Aplicacao();
		}

		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public void createAplicacao() {
		try {
			getAplicacaoFacade().create(aplicacao);
			displayInfoMessageToUser("Criado com Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void edit(Integer id) {
		aplicacao = aplicacaoFacade.find(id);
	}
	
	public void updateAplicacao() {
		try {
			getAplicacaoFacade().update(aplicacao);
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel alterar. ERRO");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void deleteAplicacao() {
		try {
			getAplicacaoFacade().delete(aplicacao);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public List<Aplicacao> getAllAplicacoes() {
		if (aplicacoes == null) {
			loadAplicacoes();
		}
		return aplicacoes;
	}

	private void loadAplicacoes() {
		aplicacoes = getAplicacaoFacade().listAll();
	}

	public void resetAplicacao() {
		aplicacao = new Aplicacao();
	}
	
	public boolean isManaged() {
		return aplicacao != null && aplicacao.getId() != null;
	}

}