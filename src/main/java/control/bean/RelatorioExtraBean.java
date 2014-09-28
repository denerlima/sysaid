package control.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
import model.entity.vo.CustoPorUnidadeFilterVO;
import model.entity.vo.CustoPorUnidadeVO;
import model.entity.vo.ItemVO;
import model.facade.ExtraFacade;

import org.omnifaces.cdi.ViewScoped;

import util.DataUtil;

@Named
@ViewScoped
public class RelatorioExtraBean implements Serializable {

	private static final long serialVersionUID = 5450418928675700315L;

	@Inject
	private ExtraFacade extraFacade;
	
	private List<ItemVO> demandantes;
	private List<ItemVO> agrupadores;
	private List<ItemVO> enderecos;
	private List<ItemVO> niveis1;
	private List<ItemVO> niveis2;
	private List<ItemVO> niveis3;
	private List<ItemVO> niveis4;
	private List<ItemVO> niveis5;
	private List<ItemVO> niveis6;
	private List<ItemVO> niveis7;
	private List<ItemVO> niveis8;
	private List<CustoPorEnderecoVO> custosPorEnderecos = new ArrayList<CustoPorEnderecoVO>();
	private List<CustoPorUnidadeVO> custosPorUnidades = new ArrayList<CustoPorUnidadeVO>();
	
	private CustoPorEnderecoFilterVO custoPorEnderecoFilter;
	private CustoPorUnidadeFilterVO custoPorUnidadeFilter;
	
	public void initCustoPorEndereco() {
		if(custoPorEnderecoFilter == null) {
			custoPorEnderecoFilter = new CustoPorEnderecoFilterVO();
			demandantes = extraFacade.consultarDemandantes();
			agrupadores = extraFacade.consultarAgrupadores();
			enderecos = extraFacade.consultarEnderecos();
		}
	}

	public void initCustoPorUnidade() {
		if(custoPorUnidadeFilter == null) {
			custoPorUnidadeFilter = new CustoPorUnidadeFilterVO();
			demandantes = extraFacade.consultarDemandantesCustoPorUnidade();
			niveis1 = extraFacade.consultarNiveis(1, "00.00.00.00.00.00.00.00");
			niveis2 = new ArrayList<ItemVO>();
			niveis3 = new ArrayList<ItemVO>();
			niveis4 = new ArrayList<ItemVO>();
			niveis5 = new ArrayList<ItemVO>();
			niveis6 = new ArrayList<ItemVO>();
			niveis7 = new ArrayList<ItemVO>();
			niveis8 = new ArrayList<ItemVO>();
		}
	}
	
	public void initCustoPorUnidadePDF() throws Exception {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getParameter("RenderOutputType");
		if(custoPorUnidadeFilter == null) {
			custoPorUnidadeFilter = new CustoPorUnidadeFilterVO();
			custoPorUnidadeFilter.setEmissaoInicio(DataUtil.converterStringParaDate(request.getParameter("emissaoInicio")));
			custoPorUnidadeFilter.setEmissaoFim(DataUtil.converterStringParaDate(request.getParameter("emissaoFim")));
			custoPorUnidadeFilter.setImprimirOrdemServico(new Boolean(request.getParameter("imprimirOrdemServico")));
			custoPorUnidadeFilter.setDemandante(request.getParameter("demandante"));
			custoPorUnidadeFilter.setNivel1(request.getParameter("nivel1"));
			custoPorUnidadeFilter.setNivel2(request.getParameter("nivel2"));
			custoPorUnidadeFilter.setNivel3(request.getParameter("nivel3"));
			custoPorUnidadeFilter.setNivel4(request.getParameter("nivel4"));
			custoPorUnidadeFilter.setNivel5(request.getParameter("nivel5"));
			custoPorUnidadeFilter.setNivel6(request.getParameter("nivel6"));
			custoPorUnidadeFilter.setNivel7(request.getParameter("nivel7"));
			custoPorUnidadeFilter.setNivel8(request.getParameter("nivel8"));
			consultarCustoPorUnidade();
		}
	}
	
	public void consultarCustoPorEndereco() {
		custosPorEnderecos = extraFacade.consultarCustoPorEndereco(custoPorEnderecoFilter);
	}
	
	public void consultarCustoPorUnidade() {
		custosPorUnidades = extraFacade.consultarCustoPorUnidade(custoPorUnidadeFilter);
	}
	
	public void changeNivel(Integer nivel) {
		switch (nivel) {
		case 1:
			niveis2 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel1());
			break;
		case 2:
			niveis3 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel2());		
			break;
		case 3:
			niveis4 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel3());
			break;
		case 4:
			niveis5 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel4());
			break;
		case 5:
			niveis6 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel5());
			break;
		case 6:
			niveis7 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel6());
			break;
		case 7:
			niveis8 = extraFacade.consultarNiveis(nivel+1, custoPorUnidadeFilter.getNivel7());
			break;
		default:
			break;
		}
	}
	
	public CustoPorEnderecoFilterVO getCustoPorEnderecoFilter() {
		return custoPorEnderecoFilter;
	}

	public void setCustoPorEnderecoFilter(CustoPorEnderecoFilterVO custoPorEndereco) {
		this.custoPorEnderecoFilter = custoPorEndereco;
	}

	public List<ItemVO> getDemandantes() {
		return demandantes;
	}

	public void setDemandantes(List<ItemVO> demandantes) {
		this.demandantes = demandantes;
	}

	public List<ItemVO> getAgrupadores() {
		return agrupadores;
	}

	public void setAgrupadores(List<ItemVO> agrupadores) {
		this.agrupadores = agrupadores;
	}

	public List<ItemVO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<ItemVO> enderecos) {
		this.enderecos = enderecos;
	}

	public List<CustoPorEnderecoVO> getCustosPorEnderecos() {
		return custosPorEnderecos;
	}

	public void setCustosPorEnderecos(List<CustoPorEnderecoVO> custosPorEnderecos) {
		this.custosPorEnderecos = custosPorEnderecos;
	}

	public List<CustoPorUnidadeVO> getCustosPorUnidades() {
		return custosPorUnidades;
	}

	public void setCustosPorUnidades(List<CustoPorUnidadeVO> custosPorUnidades) {
		this.custosPorUnidades = custosPorUnidades;
	}

	public CustoPorUnidadeFilterVO getCustoPorUnidadeFilter() {
		return custoPorUnidadeFilter;
	}

	public void setCustoPorUnidadeFilter(
			CustoPorUnidadeFilterVO custoPorUnidadeFilter) {
		this.custoPorUnidadeFilter = custoPorUnidadeFilter;
	}

	public List<ItemVO> getNiveis1() {
		return niveis1;
	}

	public void setNiveis1(List<ItemVO> niveis1) {
		this.niveis1 = niveis1;
	}

	public List<ItemVO> getNiveis2() {
		return niveis2;
	}

	public void setNiveis2(List<ItemVO> niveis2) {
		this.niveis2 = niveis2;
	}

	public List<ItemVO> getNiveis3() {
		return niveis3;
	}

	public void setNiveis3(List<ItemVO> niveis3) {
		this.niveis3 = niveis3;
	}

	public List<ItemVO> getNiveis4() {
		return niveis4;
	}

	public void setNiveis4(List<ItemVO> niveis4) {
		this.niveis4 = niveis4;
	}

	public List<ItemVO> getNiveis5() {
		return niveis5;
	}

	public void setNiveis5(List<ItemVO> niveis5) {
		this.niveis5 = niveis5;
	}

	public List<ItemVO> getNiveis6() {
		return niveis6;
	}

	public void setNiveis6(List<ItemVO> niveis6) {
		this.niveis6 = niveis6;
	}

	public List<ItemVO> getNiveis7() {
		return niveis7;
	}

	public void setNiveis7(List<ItemVO> niveis7) {
		this.niveis7 = niveis7;
	}

	public List<ItemVO> getNiveis8() {
		return niveis8;
	}

	public void setNiveis8(List<ItemVO> niveis8) {
		this.niveis8 = niveis8;
	}
	
	public String getTotalMaterialCustoPorUnidade() {
		BigDecimal custo = new BigDecimal(0);
		for(CustoPorUnidadeVO cu : custosPorUnidades) {
			custo = custo.add(cu.getCustoMaterial());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getTotalMaoDeObraCustoPorUnidade() {
		BigDecimal custo = new BigDecimal(0);
		for(CustoPorUnidadeVO cu : custosPorUnidades) {
			custo = custo.add(cu.getCustoMaoDeObra());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getTotalCustoPorUnidade() {
		BigDecimal custo = new BigDecimal(0);
		for(CustoPorUnidadeVO cu : custosPorUnidades) {
			custo = custo.add(cu.getCustoMaterial());
		}
		for(CustoPorUnidadeVO cu : custosPorUnidades) {
			custo = custo.add(cu.getCustoMaoDeObra());
		}
		final NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(Double.valueOf(custo.toString()));
	}
	
	public String getParametrosUrlImprimirCustoPorUnidade() {
		StringBuffer parametros = new StringBuffer();
		parametros.append("/extras/custoPorUnidadeRelatorioPDF.xhtml?faces-redirect=true&RenderOutputType=pdf");
		parametros.append("&emissaoInicio="+DataUtil.converterDateParaString(custoPorUnidadeFilter.getEmissaoInicio()));
		parametros.append("&emissaoFim="+DataUtil.converterDateParaString(custoPorUnidadeFilter.getEmissaoFim()));
		parametros.append("&imprimirOrdemServico="+custoPorUnidadeFilter.isImprimirOrdemServico());
		parametros.append("&demandante="+custoPorUnidadeFilter.getDemandante());
		parametros.append("&nivel1="+custoPorUnidadeFilter.getNivel1());
		parametros.append("&nivel2="+custoPorUnidadeFilter.getNivel2());
		parametros.append("&nivel3="+custoPorUnidadeFilter.getNivel3());
		parametros.append("&nivel4="+custoPorUnidadeFilter.getNivel4());
		parametros.append("&nivel5="+custoPorUnidadeFilter.getNivel5());
		parametros.append("&nivel6="+custoPorUnidadeFilter.getNivel6());
		parametros.append("&nivel7="+custoPorUnidadeFilter.getNivel7());
		parametros.append("&nivel8="+custoPorUnidadeFilter.getNivel8());
		return parametros.toString().replace("=null", "=");
	}
	
}
