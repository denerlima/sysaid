package control.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import model.entity.NotaFiscal;
import model.entity.NotaFiscalMaterial;
import model.entity.OrdemDeCompra;
import model.entity.OrdemDeCompraMaterial;
import model.facade.FornecedorFacade;
import model.facade.NotaFiscalFacade;
import model.facade.OrdemDeCompraFacade;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class NotaFiscalBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal;	
	private List<NotaFiscal> notasFiscais;
	
	@Inject
	private NotaFiscalFacade notaFiscalFacade;
	
	@Inject
	private FornecedorFacade fornecedorFacade;
	
	@Inject
	private OrdemDeCompraFacade ordemDeCompraFacade;
	
	private OrdemDeCompra ordemDeCompra;
	private List<OrdemDeCompra> ordensDeCompras;
	private List<Fornecedor> fornecedores;
	
	public NotaFiscalFacade getNotaFiscalFacade() {
		return notaFiscalFacade;
	}

	public NotaFiscal getNotaFiscal() {
		if (notaFiscal == null) {
			notaFiscal = new NotaFiscal();
		}
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String novo() {
		return "/notaFiscal/notaFiscalEdit.xhtml?faces-redirect=true";
	}
	
	public String edit(Integer id) {
		return "/notaFiscal/notaFiscalEdit.xhtml?faces-redirect=true&id="+id;
	}
	
	public void initLoad(Integer id) {
		if(notaFiscal != null) {
			return;
		}
		if(id == null || id == 0) {
			notaFiscal = new NotaFiscal();
			notaFiscal.setAcrescimos(new BigDecimal(0));
			notaFiscal.setTotalProdutos(new BigDecimal(0));
			notaFiscal.setTotalGeralNota(new BigDecimal(0));
		} else {
			notaFiscal = getNotaFiscalFacade().find(id);
		}
	}
	
	public String createNotaFiscal() {
		try {
			getNotaFiscalFacade().create(notaFiscal);
			displayInfoMessageToUser("Criado com Sucesso");
			return "/notaFiscal/notaFiscalList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateNotaFiscal() {
		try {
			getNotaFiscalFacade().update(notaFiscal);
			displayInfoMessageToUser("Alterado com  Sucesso");
			return "/notaFiscal/notaFiscalList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteNotaFiscal() {
		try {
			getNotaFiscalFacade().delete(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Exclu�do com Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, n�o foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void iniciarBuscaOrdensCompra() {
		ordemDeCompra = new OrdemDeCompra();
		ordensDeCompras = new ArrayList<OrdemDeCompra>();
	}
	
	public void buscarOrdensCompras() {
		ordensDeCompras = ordemDeCompraFacade.listOrdensCompras(ordemDeCompra.getNumeroOC());
	}
	
	public void adicionarMateriais() {
		for (OrdemDeCompra oc : ordensDeCompras) {
			for (OrdemDeCompraMaterial ocm : oc.getMateriais()) {
				if(ocm.isSelecionado()) {
					NotaFiscalMaterial nfm = new NotaFiscalMaterial();
					nfm.setNotaFiscal(notaFiscal);
					nfm.setOrdemDeCompraMaterial(ocm);
					notaFiscal.getMateriais().add(nfm);
				}
			}
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public void calcular(NotaFiscalMaterial nfm) {
		verificarNotaFiscalMaterial(nfm);
		calcular(nfm, nfm.getPrecoUnitario());
	}
	
	public void calcularPorPercentual(NotaFiscalMaterial nfm) {
		verificarNotaFiscalMaterial(nfm);
		BigDecimal porcentagem = nfm.getPercentualDesconto();
		BigDecimal vlrDesconto = porcentagem.multiply(nfm.getPrecoUnitario()).divide(new BigDecimal(100));  
		nfm.setValorDesconto(vlrDesconto);
		BigDecimal total = nfm.getPrecoUnitario().subtract(vlrDesconto);
		calcular(nfm, total);
	}
	
	public void calcularPorDesconto(NotaFiscalMaterial nfm) {
		verificarNotaFiscalMaterial(nfm);
		BigDecimal vlrDesconto = nfm.getValorDesconto();
		BigDecimal porcentagem = vlrDesconto.divide(nfm.getPrecoUnitario()).multiply(new BigDecimal(100));
		nfm.setPercentualDesconto(porcentagem);
		BigDecimal total = nfm.getPrecoUnitario().subtract(vlrDesconto);
		calcular(nfm, total);
	}

	private void calcular(NotaFiscalMaterial nfm, BigDecimal valorUnitarioComDesconto) {
		BigDecimal total = nfm.getQuantidade().multiply(valorUnitarioComDesconto);
		nfm.setTotal(total);
		calcularTotais();
	}
	
	public void calcularTotais() {
		BigDecimal totalProdutos = new BigDecimal(0);
		for (NotaFiscalMaterial notaFiscalMaterial : notaFiscal.getMateriais()) {
			totalProdutos = totalProdutos.add(notaFiscalMaterial.getTotal());
		}
		notaFiscal.setTotalProdutos(totalProdutos);
		notaFiscal.setTotalGeralNota(totalProdutos.add(notaFiscal.getAcrescimos()));
	}
	
	public void verificarNotaFiscalMaterial(NotaFiscalMaterial nfm) {
		if(nfm.getQuantidade() == null) {
			nfm.setQuantidade(new BigDecimal(0));
		}
		if(nfm.getPrecoUnitario() == null) {
			nfm.setPrecoUnitario(new BigDecimal(0));
		}
		if(nfm.getPercentualDesconto() == null) {
			nfm.setPercentualDesconto(new BigDecimal(0));
		}
		if(nfm.getValorDesconto() == null) {
			nfm.setValorDesconto(new BigDecimal(0));
		}
	}
	
	public List<NotaFiscal> getAllNotasFiscais() {
		if (notasFiscais == null) {
			loadNotasFiscais();
		}

		return notasFiscais;
	}
	
	public List<Fornecedor> getAllFornecedores(){
		if(fornecedores == null) {
			fornecedores = fornecedorFacade.listAll();
		}
		return fornecedores;
	}
	
	private void loadNotasFiscais() {
		notasFiscais = getNotaFiscalFacade().listAll();
	}

	public void resetNotaFiscal() {
		notaFiscal = new NotaFiscal();
	}

	public List<OrdemDeCompra> getOrdensDeCompras() {
		return ordensDeCompras;
	}

	public void setOrdensDeCompras(List<OrdemDeCompra> ordemsDeCompras) {
		this.ordensDeCompras = ordemsDeCompras;
	}

	public OrdemDeCompra getOrdemDeCompra() {
		return ordemDeCompra;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}		

	public boolean isManaged() {
		return notaFiscal != null && notaFiscal.getId() != null;
	}
	
}
