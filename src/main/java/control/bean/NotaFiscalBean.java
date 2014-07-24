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
			//notaFiscal.setMateriais(new ArrayList<NotaFiscalMaterial>());
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
			displayErrorMessageToUser("Ops, não foi possivel criar. ERRO");
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
			displayErrorMessageToUser("Ops, não foi possivel alterar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteNotaFiscal() {
		try {
			getNotaFiscalFacade().delete(notaFiscal);
			closeDialog();
			displayInfoMessageToUser("Excluído com Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, não foi possivel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void iniciarBuscaOrdensCompra() {
		ordemDeCompra = new OrdemDeCompra();
		ordensDeCompras = new ArrayList<OrdemDeCompra>();
	}
	
	public void buscarOrdensCompras() {
		ordensDeCompras = ordemDeCompraFacade.listAll();
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
	}
	
	public void calcular(NotaFiscalMaterial nfm) {
		calcular(nfm, nfm.getPrecoUnitario());
	}
	
	private void calcular(NotaFiscalMaterial nfm, BigDecimal valorUnitarioComDesconto) {
		BigDecimal total = nfm.getQuantidade().multiply(valorUnitarioComDesconto);
		nfm.setTotal(total);
	}
	
	public void calcularPorPercentual(NotaFiscalMaterial nfm) {
		BigDecimal porcentagem = nfm.getPercentualDesconto();
		BigDecimal vlrDesconto = porcentagem.multiply(nfm.getPrecoUnitario()).divide(new BigDecimal(100));  
		nfm.setValorDesconto(vlrDesconto);
		BigDecimal total = nfm.getPrecoUnitario().subtract(vlrDesconto);
		calcular(nfm, total);
	}
	
	public void calcularPorDesconto(NotaFiscalMaterial nfm) {
		BigDecimal vlrDesconto = nfm.getValorDesconto();
		BigDecimal porcentagem = vlrDesconto.divide(nfm.getPrecoUnitario()).multiply(new BigDecimal(100));
		nfm.setPercentualDesconto(porcentagem);
		BigDecimal total = nfm.getPrecoUnitario().subtract(vlrDesconto);
		calcular(nfm, total);
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
