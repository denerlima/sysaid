package model.entity.vo;

import java.math.BigDecimal;

public class CustoPorEnderecoVO {

	private String agrupador;
	private String endereco;
	private BigDecimal ordemServico;
	private BigDecimal subOs;
	private String data;
	private String demandante;
	private String complementoEndereco;
	private BigDecimal custoMaterial;
	private BigDecimal custoMaoDeObra;
	private BigDecimal custoTotal;
	
	public String getAgrupador() {
		return agrupador;
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public BigDecimal getOrdemServico() {
		return ordemServico;
	}
	
	public void setOrdemServico(BigDecimal ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public BigDecimal getSubOs() {
		return subOs;
	}
	
	public void setSubOs(BigDecimal subOs) {
		this.subOs = subOs;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getDemandante() {
		return demandante;
	}
	
	public void setDemandante(String demandante) {
		this.demandante = demandante;
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
	public BigDecimal getCustoMaterial() {
		return custoMaterial;
	}
	
	public void setCustoMaterial(BigDecimal custoMaterial) {
		this.custoMaterial = custoMaterial;
	}
	
	public BigDecimal getCustoMaoDeObra() {
		return custoMaoDeObra;
	}
	
	public void setCustoMaoDeObra(BigDecimal custoMaoDeObra) {
		this.custoMaoDeObra = custoMaoDeObra;
	}
	
	public BigDecimal getCustoTotal() {
		return custoTotal;
	}
	
	public void setCustoTotal(BigDecimal custoTotal) {
		this.custoTotal = custoTotal;
	}
	
}
