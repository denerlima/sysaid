package model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Fornecedor extends GenericModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	public Fornecedor() {
		super();
	}

	@Id
	@Column
	private int id;
	@Column(name = "COMPANY_NAME")
	private String nome;

	@OneToMany(mappedBy="fornecedor")
	private List<NotaFiscal> notasFiscais;
	
	@OneToMany(mappedBy="contratado")
	private List<OrdemDeCompra> ordensCompra;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}	

	public List<OrdemDeCompra> getOrdensCompra() {
		return ordensCompra;
	}

	public void setOrdensCompra(List<OrdemDeCompra> ordensCompra) {
		this.ordensCompra = ordensCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Fornecedor other = (Fornecedor) obj;
		if (id != other.id)
			return false;
		return true;
	}

}