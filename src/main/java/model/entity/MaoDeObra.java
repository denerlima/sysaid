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
@SequenceGenerator(name = "maoDeObraSequence", sequenceName = "MF_MAODEOBRA_ID_SEQ", allocationSize = 1)
@Table(name = "MF_MAODEOBRA")
public class MaoDeObra extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public MaoDeObra() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maoDeObraSequence")
	@Column
	private Integer id;		
	private String descricao;
	private int ativo = 1;
	
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valordia;
	@Column(length = 20, precision = 20, scale= 2 , nullable = false)
	private BigDecimal valorhora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaoDeObra other = (MaoDeObra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
