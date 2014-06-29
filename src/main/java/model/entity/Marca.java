package model.entity;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SequenceGenerator(name = "marcaSequence", sequenceName = "MARCA_ID_SEQ", allocationSize = 1)
@Table(name = "MARCA")
@Where(clause = "ativo = '1'")  
@SQLDelete(sql = "UPDATE sysaid_java.MARCA SET ativo  = 0 WHERE id = ?")

public class Marca extends GenericModelo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Marca() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marcaSequence")
	@Column
	private int id;	
	private String descricao;
	private int ativo = 1;
	
	@OneToMany(mappedBy="marca")
	private List<Material> marca;

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
		
	public List<Material> getMarca() {
		return marca;
	}

	public void setMarca(List<Material> marca) {
		this.marca = marca;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Marca) {
			Marca marca = (Marca) obj;
			return marca.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}