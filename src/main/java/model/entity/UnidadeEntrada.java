/*package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "unidadeEntradaSequence", sequenceName = "UNIDADEENTRADA_ID_SEQ", allocationSize = 1)
@Table(name = "UNIDADEENTRADA")
public class UnidadeEntrada extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UnidadeEntrada() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidadeEntradaSequence")
	@Column
	private int id;	
	private String descricao;
	
	@OneToMany(mappedBy="unidadeEntrada")
	private List<UnidadeMedida> listaUnidadeMedida;

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
		
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UnidadeEntrada) {
			UnidadeEntrada aplicacao = (UnidadeEntrada) obj;
			return aplicacao.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
*/