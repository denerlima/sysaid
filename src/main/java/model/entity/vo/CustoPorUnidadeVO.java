package model.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustoPorUnidadeVO implements Serializable  {

	private static final long serialVersionUID = -7970789801807471057L;
	
	private String codEstrutural;
	private String sigla;
	private String nomeUnidade;
	private BigDecimal custoMaterial;
	private BigDecimal custoMaoDeObra;
	private BigDecimal custoTotal;
	private BigDecimal osPrincipal;
	private BigDecimal subOs;
	private Date data;
	private String demandante;
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
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
	
	public BigDecimal getOsPrincipal() {
		return osPrincipal;
	}
	
	public void setOsPrincipal(BigDecimal osPrincipal) {
		this.osPrincipal = osPrincipal;
	}
	
	public BigDecimal getSubOs() {
		return subOs;
	}

	public void setSubOs(BigDecimal subOs) {
		this.subOs = subOs;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDemandante() {
		return demandante;
	}
	
	public void setDemandante(String demandante) {
		this.demandante = demandante;
	}

	public String getCodEstrutural() {
		return codEstrutural;
	}

	public void setCodEstrutural(String codEstrutural) {
		this.codEstrutural = codEstrutural;
	}
	
}
