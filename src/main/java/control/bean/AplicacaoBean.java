package control.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entity.Aplicacao;
import model.facade.AplicacaoFacade;

@ViewScoped
@ManagedBean
public class AplicacaoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Aplicacao aplicacao;	
	private List<Aplicacao> aplicacoes;
	private AplicacaoFacade aplicacaoFacade;

	
	public AplicacaoFacade getAplicacaoFacade() {
		if (aplicacaoFacade == null) {
			aplicacaoFacade = new AplicacaoFacade();
		}

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
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
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
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void deleteAplicacao() {
		try {
			getAplicacaoFacade().delete(aplicacao);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadAplicacoes();
			resetAplicacao();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
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