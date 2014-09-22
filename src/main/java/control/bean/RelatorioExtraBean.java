package control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import model.entity.vo.CustoPorEnderecoFilterVO;
import model.entity.vo.CustoPorEnderecoVO;
import model.entity.vo.ItemVO;
import model.facade.ExtraFacade;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class RelatorioExtraBean implements Serializable {

	private static final long serialVersionUID = 5450418928675700315L;

	@Inject
	private ExtraFacade extraFacade;
	
	private List<ItemVO> demandantes;
	private List<ItemVO> agrupadores;
	private List<ItemVO> enderecos;
	private List<CustoPorEnderecoVO> custosPorEnderecos = new ArrayList<CustoPorEnderecoVO>();
	
	private CustoPorEnderecoFilterVO custoPorEnderecoFilter;

	public void initCustoPorEndereco() {
		if(custoPorEnderecoFilter == null) {
			custoPorEnderecoFilter = new CustoPorEnderecoFilterVO();
			demandantes = extraFacade.consultarDemandantes();
			agrupadores = extraFacade.consultarAgrupadores();
			enderecos = extraFacade.consultarEnderecos();
		}
	}

	public void consultarCustoPorEndereco() {
		custosPorEnderecos = extraFacade.consultarCustoPorEndereco(custoPorEnderecoFilter);
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

}
