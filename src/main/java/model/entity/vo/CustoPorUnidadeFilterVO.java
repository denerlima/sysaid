package model.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class CustoPorUnidadeFilterVO implements Serializable {

	private static final long serialVersionUID = 2614272192439156472L;
	
	private Date emissaoInicio;
	private Date emissaoFim;
	private boolean imprimirOrdemServico;
	private ItemVO demandante;
	private ItemVO nivel1;
	private ItemVO nivel2;
	private ItemVO nivel3;
	private ItemVO nivel4;
	private ItemVO nivel5;
	private ItemVO nivel6;
	private ItemVO nivel7;
	private ItemVO nivel8;
	
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
	
	public boolean isImprimirOrdemServico() {
		return imprimirOrdemServico;
	}
	
	public void setImprimirOrdemServico(boolean imprimirOrdemServico) {
		this.imprimirOrdemServico = imprimirOrdemServico;
	}
	
	public ItemVO getDemandante() {
		return demandante;
	}
	
	public void setDemandante(ItemVO demandante) {
		this.demandante = demandante;
	}
	
	public ItemVO getNivel1() {
		return nivel1;
	}
	
	public void setNivel1(ItemVO nivel1) {
		this.nivel1 = nivel1;
	}
	
	public ItemVO getNivel2() {
		return nivel2;
	}
	
	public void setNivel2(ItemVO nivel2) {
		this.nivel2 = nivel2;
	}
	
	public ItemVO getNivel3() {
		return nivel3;
	}
	
	public void setNivel3(ItemVO nivel3) {
		this.nivel3 = nivel3;
	}
	
	public ItemVO getNivel4() {
		return nivel4;
	}
	
	public void setNivel4(ItemVO nivel4) {
		this.nivel4 = nivel4;
	}
	
	public ItemVO getNivel5() {
		return nivel5;
	}
	
	public void setNivel5(ItemVO nivel5) {
		this.nivel5 = nivel5;
	}
	
	public ItemVO getNivel6() {
		return nivel6;
	}
	
	public void setNivel6(ItemVO nivel6) {
		this.nivel6 = nivel6;
	}
	
	public ItemVO getNivel7() {
		return nivel7;
	}
	
	public void setNivel7(ItemVO nivel7) {
		this.nivel7 = nivel7;
	}
	
	public ItemVO getNivel8() {
		return nivel8;
	}
	
	public void setNivel8(ItemVO nivel8) {
		this.nivel8 = nivel8;
	}
		
}
