package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Aplicacao;
import model.facade.AplicacaoFacade;

import org.omnifaces.cdi.ViewScoped;

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
			closeDialog();
			displayInfoMessageToUser("Criado com Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void updateAplicacao() {
		try {
			getAplicacaoFacade().update(aplicacao);
			closeDialog();
			displayInfoMessageToUser("Alterado com  Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteAplicacao() {
		try {
			getAplicacaoFacade().delete(aplicacao);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
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

}