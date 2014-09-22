package model.entity.vo;

import java.util.Date;

public class CustoPorEnderecoFilterVO {

	private String nivelDetalhe;
	private boolean incluirSubOS;
	private Date emissaoInicio;
	private Date emissaoFim;
	private String demandante;
	private String agrupador;
	private String endereco;
	private String complementoEndereco;
	
	public String getNivelDetalhe() {
		return nivelDetalhe;
	}
	
	public void setNivelDetalhe(String nivelDetalhe) {
		this.nivelDetalhe = nivelDetalhe;
	}
	
	public boolean isIncluirSubOS() {
		return incluirSubOS;
	}
	
	public void setIncluirSubOS(boolean incluirSubOS) {
		this.incluirSubOS = incluirSubOS;
	}
	
	public Date getEmissaoInicio() {
		return emissaoInicio;
	}
	
	public void setEmissaoInicio(Date emissaoInicio) {
		this.emissaoInicio = emissaoInicio;
	}
	
	public Date getEmissaoFim() {
		return emissaoFim;
	}
	
	public void setEmissaoFim(Date emissaoFim) {
		this.emissaoFim = emissaoFim;
	}
	
	public String getDemandante() {
		return demandante;
	}
	
	public void setDemandante(String demandante) {
		this.demandante = demandante;
	}
	
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
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
}
