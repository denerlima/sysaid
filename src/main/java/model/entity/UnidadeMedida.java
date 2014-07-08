package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "unidadeMedidaSequence", sequenceName = "UNIDADEMEDIDA_ID_SEQ", allocationSize = 1)
@Table(name = "UNIDADEMEDIDA")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.UNIDADEMEDIDA SET ativo  = 0 WHERE id = ?")

public class UnidadeMedida extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UnidadeMedida() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidadeMedidaSequence")
	@Column
	private int id;	
	private String unSaida;
	private int ativo = 1;
	
	@Column(length = 20, precision = 20, scale= 3 , nullable = true)
	private BigDecimal fatorConversao;
	
	@OneToMany(mappedBy="unidadeMedida")
	private List<Material> listaMaterial;
	
	@ManyToOne
	@JoinColumn(name="id_unidadeentrada", referencedColumnName="id")
	private UnidadeEntrada unidadeEntrada;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUnSaida() {
		return this.unSaida;
	}
	
	public UnidadeEntrada getUnidadeEntrada() {
		return unidadeEntrada;
	}
	public void setUnidadeEntrada(UnidadeEntrada unidadeEntrada) {
		this.unidadeEntrada = unidadeEntrada;
	}

	public void setUnSaida(String unSaida) {
		this.unSaida = unSaida;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getFatorConversao() {	
		if (fatorConversao != null) {
			return this.fatorConversao.setScale(3,BigDecimal.ROUND_DOWN) ;
		}
		return this.fatorConversao;
	}

	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UnidadeMedida) {
			UnidadeMedida unidadeMedida = (UnidadeMedida) obj;
			return unidadeMedida.getId() == id;
		}

		return false;
	}
}