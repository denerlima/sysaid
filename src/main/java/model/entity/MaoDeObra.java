package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "maoDeObraSequence", sequenceName = "MAODEOBRA_ID_SEQ", allocationSize = 1)
@Table(name = "MAODEOBRA")
public class MaoDeObra extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public MaoDeObra() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maoDeObraSequence")
	@Column
	private int id;		
	private String descricao;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valordia;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valorhora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
			
	public BigDecimal getValordia() {
		if (valordia != null) {
			return this.valordia.setScale(2,BigDecimal.ROUND_DOWN) ;
		}
		return this.valordia;
	}

	public void setValordia(BigDecimal valordia) {
		this.valordia = valordia;
	}
	
	public BigDecimal getValorhora() {
		if (valorhora != null) {
			return this.valorhora.setScale(2,BigDecimal.ROUND_DOWN) ;
		}
		return this.valorhora;
	}
	
	public void setValorhora(BigDecimal valorhora) {
		this.valorhora = valorhora;
	}

/*
	public BigDecimal getValordia() {
		return valordia;
	}

	public void setValordia(BigDecimal valordia) {
		this.valordia = valordia;
	}
	
	public BigDecimal getValorhora() {
		return valorhora;
	}

	public void setValorhora(BigDecimal valorhora) {
		this.valorhora = valorhora;
	}
	*/

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MaoDeObra) {
			MaoDeObra maoDeObra = (MaoDeObra) obj;
			return maoDeObra.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}