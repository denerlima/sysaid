package model.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class CustoPorEnderecoFilterVO implements Serializable {

	private static final long serialVersionUID = -4485228963879083271L;
	
	private Integer nivelDetalhe;
	private boolean incluirSubOS;
	private Date emissaoInicio;
	private Date emissaoFim;
	private ItemVO demandante;
	private ItemVO agrupador;
	private ItemVO endereco;
	private String complementoEndereco;
	
	public Integer getNivelDetalhe() {
		return nivelDetalhe;
	}
	
	public void setNivelDetalhe(Integer nivelDetalhe) {
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
	
	public ItemVO getDemandante() {
		return demandante;
	}
	
	public void setDemandante(ItemVO demandante) {
		this.demandante = demandante;
	}
	
	public ItemVO getAgrupador() {
		return agrupador;
	}
	
	public void setAgrupador(ItemVO agrupador) {
		this.agrupador = agrupador;
	}
	
	public ItemVO getEndereco() {
		return endereco;
	}
	
	public void setEndereco(ItemVO endereco) {
		this.endereco = endereco;
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	
	public String getNivelDetalheLabel() {
		if(nivelDetalhe == null) {
			return "";
		}
		else if(nivelDetalhe.intValue() == 1) { 
			return "Consolidado por Agrupador de Endereço";
		}
		else if(nivelDetalhe.intValue() == 2) { 
			return "Consolidado por Endereço";
		}
		else if(nivelDetalhe.intValue() == 3) {
			return "Consolidado por Complemento de endereço";
		}
		else if(nivelDetalhe.intValue() == 4) {
			return "Consolidado por Demandante";
		}
		else if(nivelDetalhe.intValue() == 5) {
			return "Lista de OS";
		}
		return "";
	}
	
}
