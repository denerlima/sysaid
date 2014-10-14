package control.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.Fornecedor;
import model.entity.Material;
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
	private List<NotaFiscalMaterial> notasFiscaisMateriais;
	
	@Inject
	private NotaFiscalFacade notaFiscalFacade;
	
	@Inject
	private FornecedorFacade fornecedorFacade;
	
	@Inject
	private OrdemDeCompraFacade ordemDeCompraFacade;
	
	private OrdemDeCompra ordemDeCompra;
	private List<OrdemDeCompra> ordensDeCompras;
	private List<Fornecedor> fornecedores;
	private Material material;
	private Date dtEmissaoInicial , dtEmissaoFinal , dtEntregaInicial , dtEntregaFinal ;
	
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
			for(NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
				if(nfm.getQuantidade().longValue() > nfm.getOrdemDeCompraMaterial().getQuantidadeAutorizada().longValue()) {
					displayErrorMessageToUser("A quantidade da NF não pode ser maior que a autorizada.");
					return null;
				}
			}
			getNotaFiscalFacade().create(notaFiscal);
			displayInfoMessageToUser("Criado com Sucesso");
			return "/notaFiscal/notaFiscalList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel criar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateNotaFiscal() {
		try {
			for(NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
				if(nfm.getQuantidade().longValue() >= nfm.getOrdemDeCompraMaterial().getQuantidadeAutorizada().longValue()) {
					displayErrorMessageToUser("A quantidade da NF não pode ser maior que a autorizada.");
					return null;
				}
			}
			getNotaFiscalFacade().updateNota(notaFiscal);
			displayInfoMessageToUser("Alterado com  Sucesso");
			return "/notaFiscal/notaFiscalList.xhtml?faces-redirect=true";
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel alterar. ERRO");
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteNotaFiscal() {
		try {
			getNotaFiscalFacade().delete(notaFiscal);
			displayInfoMessageToUser("Excluído com Sucesso");
			loadNotasFiscais();
			resetNotaFiscal();
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel excluir. ERRO");
			e.printStackTrace();
		}
	}

	public void cancelarNotaFiscal() {
		try {
			notaFiscal.setAtivo(2);
			getNotaFiscalFacade().update(notaFiscal);
			displayInfoMessageToUser("Cancelado com  Sucesso");
			loadNotasFiscais();
			RequestContext.getCurrentInstance().addCallbackParam("success", true);
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel cancelar. ERRO");
			e.printStackTrace();
		}
	}
	
	public void iniciarBuscaOrdensCompra() {
		ordemDeCompra = new OrdemDeCompra();
		ordensDeCompras = new ArrayList<OrdemDeCompra>();
	}
	
	public void buscarOrdensCompras() {
		ordensDeCompras = ordemDeCompraFacade.listOrdensCompras(ordemDeCompra.getId());
	}
	
	public void adicionarMateriais() {
		for (OrdemDeCompra oc : ordensDeCompras) {
			for (OrdemDeCompraMaterial ocm : oc.getMateriais()) {
				if(ocm.isSelecionado()) {
					if(!isMaterialExistente(ocm)) {
						NotaFiscalMaterial nfm = new NotaFiscalMaterial();
						nfm.setNotaFiscal(notaFiscal);
						nfm.setOrdemDeCompraMaterial(ocm);
						notaFiscal.getMateriais().add(nfm);
					}
				}
			}
		}
		RequestContext.getCurrentInstance().addCallbackParam("success", true);
	}
	
	public boolean isMaterialExistente(OrdemDeCompraMaterial ocm) {
		for(NotaFiscalMaterial nfm : notaFiscal.getMateriais()) {
			if(nfm.getOrdemDeCompraMaterial().getId().intValue() == ocm.getId().intValue()) {
				return true;
			}
		}
		return false;
	}
	
	public void removerMaterial(NotaFiscalMaterial nfm) {
		notaFiscal.getMateriais().remove(nfm);
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
	
	public List<NotaFiscalMaterial> pesquisarNotaFiscalbyFilters() {
		try {			
			notasFiscaisMateriais = getNotaFiscalFacade().pesquisarNotaFiscalbyFilters(notaFiscal,
					ordemDeCompra, material, dtEmissaoInicial , dtEmissaoFinal , dtEntregaInicial , dtEntregaFinal);			
		} catch (Exception e) {
			displayErrorMessageToUser("Ops, nã‹o foi possí’vel achar nennhuma Ordem de Compra. ERRO");
			e.printStackTrace();
		}
		return notasFiscaisMateriais;
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
		if (ordemDeCompra == null) {
			ordemDeCompra = new OrdemDeCompra();
		}
		return ordemDeCompra;
	}

	public void setOrdemDeCompra(OrdemDeCompra ordemDeCompra) {
		this.ordemDeCompra = ordemDeCompra;
	}
	
	public Material getMaterial() {
		if (material == null) {
			material = new Material();
		}
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public boolean isManaged() {
		return notaFiscal != null && notaFiscal.getId() != null;
	}

	public Date getDtEmissaoInicial() {
		return dtEmissaoInicial;
	}

	public void setDtEmissaoInicial(Date dtEmissaoInicial) {
		this.dtEmissaoInicial = dtEmissaoInicial;
	}

	public Date getDtEmissaoFinal() {
		return dtEmissaoFinal;
	}

	public void setDtEmissaoFinal(Date dtEmissaoFinal) {
		this.dtEmissaoFinal = dtEmissaoFinal;
	}

	public Date getDtEntregaInicial() {
		return dtEntregaInicial;
	}

	public void setDtEntregaInicial(Date dtEntregaInicial) {
		this.dtEntregaInicial = dtEntregaInicial;
	}

	public Date getDtEntregaFinal() {
		return dtEntregaFinal;
	}

	public void setDtEntregaFinal(Date dtEntregaFinal) {
		this.dtEntregaFinal = dtEntregaFinal;
	}

	public List<NotaFiscalMaterial> getNotasFiscaisMateriais() {
		return notasFiscaisMateriais;
	}

	public void setNotasFiscaisMateriais(
			List<NotaFiscalMaterial> notasFiscaisMateriais) {
		this.notasFiscaisMateriais = notasFiscaisMateriais;
	}
	
}
